package praxis.view.kalender {
import de.ama.framework.command.MenuInvoker;
import de.ama.framework.command.OpenEditorCommand;
import de.ama.framework.gui.fields.CommandButton;
import de.ama.framework.gui.frames.*;
import de.ama.framework.util.Util;

import flash.events.MouseEvent;

import mx.containers.Canvas;
import mx.controls.Label;
import mx.core.DragSource;
import mx.events.DragEvent;
import mx.events.FlexEvent;
import mx.events.ResizeEvent;
import mx.managers.DragManager;

import praxis.bom.CalendarEntry;
import praxis.bom.Resource;

public class TimeLine extends Canvas {
    private var _resourceLink:CommandButton;
    private var _resource:Resource;
    private var _startTime:Date;
    private var _endTime:Date;
    private var _deltaTimeInMinutes:int;
    private var _labelOffset:int=20;
    
    private var _selectedItem:ItemPanel = null;
    private var _startPosY:int = 0;
    private var _startPosX:int = 0;
    
    public function set selectedItem(itempanel:ItemPanel):void{
    	_selectedItem = itempanel;
    }
    
    
    private function mouseUpHandler(e:MouseEvent):void {
    	if(_selectedItem){
        	_selectedItem.unselect();
         }
    }
    
    private function mouseMoveHandler(e:MouseEvent):void {
    	if(_selectedItem && e.buttonDown){
    		var diff:int = e.stageY-_startPosY;
    		if(Math.abs(diff) > (deltaTimeInMinutes*factor)){
    		   if(_selectedItem.resizeMode){
	    		   if(diff>0){
 	    		       _selectedItem.moreTime();
	    		   } else {
	    		   	   _selectedItem.lessTime();
	    		   }
	    	   } else {
	    	   	 _selectedItem.y += diff;
	    	   	 _selectedItem.snapToGrid();
	    	   	 if(Math.abs(e.stageX-_startPosX)>30){
		    	   	 var dragInitiator:ItemPanel = _selectedItem;
                     var ds:DragSource = new DragSource();
                     ds.addData(e.localY,"offset");
                     DragManager.doDrag(dragInitiator, ds , e);
                 }
	    	   }
    		   _startPosY = e.stageY;
    		   _startPosX = e.stageX;
    		}
    	}
    }
    
    private function mouseDownHandler(e:MouseEvent):void {
        _startPosY = e.stageY;
	    _startPosX = e.stageX;
    	if(e.ctrlKey) {
    		var ip:ItemPanel = new ItemPanel(new CalendarEntry());
    	    insertItemPanel(ip, e.localY-labelOffset);
    	}
    }
    
    public function TimeLine(resource:Resource, startTime:Date, endTime:Date, deltaTime:int) {
        this.resource = resource;
        _startTime = startTime;
        _endTime = endTime;
        _deltaTimeInMinutes = deltaTime;
        percentHeight = 100;
		if(resource){
	        percentWidth = 100;
        } else {
        	width = 43;
        }
        setStyle("borderStyle","solid");
        horizontalScrollPolicy = "off";
        verticalScrollPolicy = "off";

        addEventListener(MouseEvent.MOUSE_DOWN, mouseDownHandler);
        addEventListener(MouseEvent.MOUSE_MOVE, mouseMoveHandler);
        addEventListener(MouseEvent.MOUSE_UP, mouseUpHandler);
        
        addEventListener(DragEvent.DRAG_ENTER, dragEnterHandler);
        addEventListener(DragEvent.DRAG_DROP, dragDropHandler);
        addEventListener(DragEvent.DRAG_EXIT, dragExitHandler);
        addEventListener(FlexEvent.CREATION_COMPLETE, creationCompleteHandler);
        addEventListener(ResizeEvent.RESIZE, resizeHandler);
        
    }
    
    private function resizeHandler(event:ResizeEvent):void {
    	paintTimeGrid();
    }

    private function creationCompleteHandler(event:FlexEvent):void {
    	paintTimeGrid();
    }

    
    // ---------------------------------------------------------------------------
    
    private function dragEnterHandler(event:DragEvent):void {
        var dropTarget:TimeLine=event.currentTarget as TimeLine;
        
        if (event.dragInitiator is ItemPanel 
            /* && ItemEditPanel(event.dragInitiator).timeLine != this */) {
        	
        	showReceiveBorder(true);
            DragManager.acceptDragDrop(dropTarget);
        }
    }

    private function dragExitHandler(event:DragEvent):void     {
        var dropTarget:TimeLine=event.currentTarget as TimeLine;
        showReceiveBorder(false);
    }                    
    
    private function dragDropHandler(event:DragEvent):void 
    {
        var timeLine:TimeLine=event.currentTarget as TimeLine;
        var itemPanel:ItemPanel = ItemPanel(event.dragInitiator);
        if (event.dragSource.hasFormat("offset")){
        	 var offset:int = int(event.dragSource.dataForFormat('offset'));
	         timeLine.insertItemPanel(itemPanel,event.localY-offset);
	         showReceiveBorder(false);
        }
    }

    private var oldBorderColor:int = 0;
    private function showReceiveBorder(b:Boolean):void {
    	if(b){
    	   if(oldBorderColor==0){
    	   	  oldBorderColor = getStyle("borderColor");
    	   }	
           setStyle("borderColor", getStyle("themeColor"));
           setStyle("borderThickness", 4);
    	} else {
    	   if(oldBorderColor > 0){
              setStyle("borderColor", oldBorderColor);
           }
           setStyle("borderThickness", 1);
    	}
    }

    // ---------------------------------------------------------------------------

    public function insertItemPanel(ip:ItemPanel, y:int):void {
    	if(y>0){
           ip.getCalendarEntry().time = calcTimeString(y);
        }
        ip.timeLine = this;
        addChild(ip);
        ip.moveToTimePosition();
        ip.keepInSight();
    }

    public function get labelOffset():int {
    	return _labelOffset;
    }

    public function get startInMinutes():int {
    	return _startTime.hours * 60 + _startTime.minutes;
    }

    public function get stopInMinutes():int {
    	return _endTime.hours * 60 + _endTime.minutes;
    }

    public function get durationInMinutes():int {
        return stopInMinutes - startInMinutes;
    }

    public function get factor():Number {
       return  durationInMinutes / timeLineHeight;
    }

    public function get timeLineHeight():int {
        return height - _labelOffset;
    }

    public function calcTimeInMinutes(y:int):int {
    	return Math.ceil((Math.max(y-_labelOffset,1) / timeLineHeight) * durationInMinutes);
    }

    public function calcTimeString(y:int):String {
    	var minutes:int = calcTimeInMinutes(y);
        return Util.toHourMinutesString(minutes, startInMinutes);  
    }

    public function calcPos(minutes:int):int {
    	var pos:int = (( minutes / durationInMinutes) * timeLineHeight) + _labelOffset;
    	return pos;
    }

    public function get resource():Resource {
        return _resource;
    }

    public function set resource(r:Resource):void {
		if(r==null) return;
		if(_resourceLink==null){
        _resourceLink = new CommandButton(new OpenEditorCommand(r.name,null,"ResourceEditor"),new MenuInvoker(r),CommandButton.MEDIUM);
        _resourceLink.x = 0;
        _resourceLink.y = 0;
        _resourceLink.percentWidth=100;
        _resourceLink.setStyle("fontWeight","bold");

        addChild(_resourceLink);
        }

        _resourceLink.label = r.name;
        _resource = r;
    }



    private function paintTimeGrid():void {
    	graphics.clear();
    	if(resource==null){
    	    removeAllChildren();
    	}
    	
		var n:int = durationInMinutes / deltaTimeInMinutes;
		var label:Label;
		var t:int;
		var y:int;
		for (var i:int=0; i<n ; i++){
			t=i*deltaTimeInMinutes;
			if(i%2==0 && resource==null){
				label = new Label();
				label.text = Util.toHourMinutesString(t,startInMinutes);
				label.x=0;
				label.y=calcPos(t)-9;
				label.width = 42;
				label.setStyle("textAlign","center");
				addChild(label);
			} 
			if(resource){
				y = calcPos(t);			
				graphics.lineStyle(1,getStyle("themeColor"),t%60==0 ? 0.85 : 0.35);
				graphics.moveTo(3,y);
				graphics.lineTo(width-3,y);
			}
		}
    }

    public function get startTime():Date {
        return _startTime;
    }

    public function set startTime(value:Date):void {
        _startTime = value;
    }

    public function get endTime():Date {
        return _endTime;
    }

    public function set endTime(value:Date):void {
        _endTime = value;
    }

    public function get deltaTimeInMinutes():int {
        return _deltaTimeInMinutes;
    }

    public function set deltaTimeInMinutes(value:int):void {
        _deltaTimeInMinutes = value;
    }

    public function getCalendarEntries():Array {
       var ret:Array = new Array();
       var children:Array = getChildren();
       for each (var o:Object in children){
           if(o is ItemPanel){
              // todo dirty check
              ret.push(ItemPanel(o).getCalendarEntry());
           }
       }
       return ret;
    }


    public function removeAllItemPanels():void {
        var children:Array = getChildren();
        for each (var o:Object in children){
            if(o is ItemPanel){
               removeChild(ItemPanel(o));
            }
        }

    }}
}

