package praxis.view.kalender {
import de.ama.framework.command.MenuInvoker;
import de.ama.framework.command.OpenEditorCommand;
import de.ama.framework.gui.fields.CommandButton;
import de.ama.framework.gui.frames.*;

import flash.events.MouseEvent;

import mx.containers.Canvas;

import praxis.bom.CalendarEntry;
import praxis.bom.Resource;

public class TimeLine extends Canvas {
    private var _resourceLink:CommandButton;
    private var _resource:Resource;
    private var _startTime:Number;
    private var _endTime:Number;
    private var _deltaTime:int;


    public function TimeLine(resource:Resource, startTime:Number, endTime:Number, deltaTime:int) {
        this.resource = resource;
        _startTime = startTime;
        _endTime = endTime;
        _deltaTime = deltaTime;

        percentWidth = 100;
        percentHeight = 100;
        setStyle("borderStyle","solid");
        horizontalScrollPolicy = "off";

        addEventListener(MouseEvent.CLICK, insertItem);

    }

    private function insertItem(e:MouseEvent):void {
        if(e.ctrlKey) {
            var item:ItemEditPanel = new ItemEditPanel();
            addChild(item);
            item.setData(new CalendarEntry());
        }
    }

    public function get duration():Number {
        return _endTime - _startTime;
    }


    public function get resource():Resource {
        return _resource;
    }

    public function set resource(r:Resource):void {
		if(_resourceLink==null){
        _resourceLink = new CommandButton(new OpenEditorCommand(r.name,null,"ResourceEditor"),new MenuInvoker(r),CommandButton.MEDIUM);
        _resourceLink.x = 0;
        _resourceLink.y = 0;
        _resourceLink.percentWidth=100;
        _resourceLink.setStyle("borderStyle","solid");
        _resourceLink.setStyle("backgroundColor",0xffffff);

        addChild(_resourceLink);
        }

        _resourceLink.label = r.name;
        _resource = r;
    }

    private function openResource():void {

    }

    public function get startTime():Number {
        return _startTime;
    }

    public function set startTime(value:Number):void {
        _startTime = value;
    }

    public function get endTime():Number {
        return _endTime;
    }

    public function set endTime(value:Number):void {
        _endTime = value;
    }

    public function get deltaTime():int {
        return _deltaTime;
    }

    public function set deltaTime(value:int):void {
        _deltaTime = value;
    }

}
}

