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
	private var _selected:Boolean;
	
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
        
        addEventListener(MouseEvent.MOUSE_MOVE, doDragging);
        addEventListener(MouseEvent.MOUSE_DOWN, mouseDownHandler);
        addEventListener(MouseEvent.MOUSE_UP, mouseUpHandler);
        addEventListener(MouseEvent.MOUSE_OUT, mouseOutHandler);
        
        _label = new Label();
        _label.setStyle("left",2);
        _label.setStyle("right",15);
        _label.setStyle("textAlign","left");
        addChild(_label);
    }
    
    public function set timeLine(tl:TimeLine):void{
    	this._timeLine = tl;
    	this._data.resource = new BoReference(_timeLine.resource);
    }

    public function keepInSight():void {
    	if(this.y < _timeLine.labelOffset){
    		this.y = _timeLine.labelOffset;
    	}
    	if(this.y >= _timeLine.height-5){
    		this.y = _timeLine.height-5;
    	}
    }
    
    public function moreTime():void {
       _data.durationInMinutes += _timeLine.deltaTimeInMinutes;
       height = _data.durationInMinutes/_timeLine.factor;
       snapSizeToGrid();
    }    
    
    public function lessTime():void {
    	if(_data.durationInMinutes > (_timeLine.deltaTimeInMinutes*2)) {
	       _data.durationInMinutes -= _timeLine.deltaTimeInMinutes;
	       height = _data.durationInMinutes/_timeLine.factor;
	       snapSizeToGrid();
    	}
    }    
    
    private function mouseDownHandler(event:MouseEvent):void {
 	    _resizeMode = (event.localY > (height-20) && event.localY <= height);
 	    select();
    }    
    
    private function mouseUpHandler(event:MouseEvent):void {
    	unselect();
    }
    
    private function mouseOutHandler(event:MouseEvent):void {
    	if(!event.buttonDown){
           setStyle("borderColor", Application.application.getStyle("themeColor"));
    	}
    }
    
    public function unselect():void {
        setStyle("borderColor", Application.application.getStyle("themeColor"));
       _timeLine.selectedItem = null;
       _selected = false;
       _resizeMode = false;
       snapSizeToGrid();
    }    
    
    public function select():void {
        setStyle("borderColor", 0xffaaaa);
       _timeLine.selectedItem = this;
       _selected = true;
    }    
    
    private function doDragging(event:MouseEvent):void {
       if(_selected) return;	
       if(!event.buttonDown) {
       	  if(event.localY > (height-15)){
       	  	setStyle("borderColor", 0xffaaaa);
       	  } else {
            setStyle("borderColor", Application.application.getStyle("themeColor"));
       	  }
       } 	
    }


    public function moveToTimePosition():void {
    	if(_timeLine==null) return;
    	var timeInMinutes:int =Util.toMinutes(_data.time);
    	var snapTime:int = Math.round((timeInMinutes-_timeLine.startInMinutes) * _timeLine.factor);
    	y = _timeLine.calcPos(snapTime);
  		snapToGrid();
  		keepInSight();
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
  		_data.time = _timeLine.calcTimeString(y);
  		_data.durationInMinutes = height * _timeLine.factor;
        _label.text = _data.time +"("+Util.toHourMinutesString(_data.durationInMinutes)+") h="+height;
    }   

    public function getCalendarEntry():CalendarEntry {
        return _data;
    }

    public function get resizeMode():Boolean {
        return _resizeMode;
    }
}
}
