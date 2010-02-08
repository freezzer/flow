/* 

 generated code by flow "flex on wings"
 this code is property of Andreas Marochow

 */

package praxis.view.kalender {
import de.ama.framework.data.*;
import de.ama.framework.gui.fields.*;
import de.ama.framework.gui.frames.*;
import de.ama.framework.util.Util;

import flash.events.MouseEvent;

import mx.containers.Canvas;
import mx.controls.Label;
import mx.core.Application;
import mx.core.DragSource;
import mx.managers.DragManager;

import praxis.bom.CalendarEntry;

public class ItemPanel  extends Canvas {
	
	private var _resizeMode:Boolean;
	private var _label:Label;
	private var _timeLine:TimeLine;
	private var _data:CalendarEntry;
	
    public function ItemPanel(data:CalendarEntry) {
		this._data = data;
        height = 30;
        setStyle("left", 2);
        setStyle("right", 2);
        setStyle("borderStyle", "solid");
        setStyle("borderThickness", 2);
        setStyle("borderColor", Application.application.getStyle("themeColor"));
        setStyle("cornerRadius",3);
        setStyle("backgroundColor", 0xffffff);
        clipContent = true;
        verticalScrollPolicy = "off";
        horizontalScrollPolicy = "off";
		useHandCursor = true;       	
		buttonMode = true;       	
        
        addEventListener(MouseEvent.MOUSE_DOWN, mouseDownHandler);
        addEventListener(MouseEvent.MOUSE_UP,   mouseUpHandler);
        addEventListener(MouseEvent.MOUSE_MOVE, doDragging);
        
        _label = new Label();
        _label.setStyle("left",2);
        _label.setStyle("right",2);
        _label.setStyle("textAlign","left");
		
        addChild(_label);
        refreshDisplay();
    }
    
    public function set timeLine(tl:TimeLine):void{
    	this._timeLine = tl;
    }

    public function keepInSight():void {
    	if(this.y < _timeLine.labelOffset){
    		this.y = _timeLine.labelOffset;
    	}
    	if(this.y >= _timeLine.height-5){
    		this.y = _timeLine.height-5;
    	}
    }
    
    private function mouseDownHandler(event:MouseEvent):void {
       _resizeMode = event.localY > height*.75;
    }    

    private function mouseUpHandler(event:MouseEvent):void {
       _resizeMode = false;	
       snapSizeToGrid();
    }    
    
    private function doDragging(event:MouseEvent):void {
       if(!event.buttonDown) return;	
       
       if(_resizeMode) {
       	  height = event.localY+5;
       } else {
          var dragInitiator:ItemPanel = event.currentTarget as ItemPanel;
          var ds:DragSource = new DragSource();
          ds.addData(event.localY,"offset");
          DragManager.doDrag(dragInitiator, ds , event);
       }
    }

    public function moveToTimePosition():void {
    	if(_timeLine==null) return;
    	var timeInMinutes:int =Util.toMinutes(_data.time);
    	var snapTime:int = Math.round(timeInMinutes / _timeLine.deltaTimeInMinutes)* _timeLine.deltaTimeInMinutes;
    	y = _timeLine.calcPos(snapTime);
  		recalcValues();
    }

    public function snapToGrid():void {
    	if(_timeLine==null) return;
    	var timeInMinutes:int =_timeLine.calcTimeInMinutes(y);
    	var snapTime:int = Math.round(timeInMinutes / _timeLine.deltaTimeInMinutes)* _timeLine.deltaTimeInMinutes;
    	y = _timeLine.calcPos(snapTime);
  		recalcValues();
    }
    
    public function snapSizeToGrid():void {
    	if(_timeLine==null) return;
    	var dur:Number = Math.ceil(_timeLine.factor * height / _timeLine.deltaTimeInMinutes) * _timeLine.deltaTimeInMinutes;
    	height = dur/_timeLine.factor;
  		recalcValues();
    }
    
    public function recalcValues():void {
  		_data.resource = new BoReference(_timeLine.resource);
  		_data.time = _timeLine.calcTimeString(y);
  		_data.durationInMinutes = height * _timeLine.factor;
  		refreshDisplay();
    }   
    
    private function refreshDisplay():void {
        _label.text = _data.time +"("+Util.toHourMinutesString(_data.durationInMinutes)+") "+_data.label;
    }


    public function getCalendarEntry():CalendarEntry {
        return _data;
    }
}
}
