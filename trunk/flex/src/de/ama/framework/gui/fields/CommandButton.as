package de.ama.framework.gui.fields {
import de.ama.framework.command.Command;
import de.ama.framework.command.Invoker;
import de.ama.framework.util.Util;

import flash.events.MouseEvent;

import mx.controls.LinkButton;

public class CommandButton extends LinkButton {


    public var _size:int = 0 ;
    public var _invoker:Invoker ;

    public static var SMALL:int = 1;
    public static var MEDIUM:int = 2;
    public static var LARGE:int = 3;


    public function CommandButton(cmd:Command=null, invoker:Invoker=null,size:int=0) {
    	if(cmd){
    		command = cmd;
    	}	
        _size = size;
        _invoker = invoker;
        addEventListener(MouseEvent.CLICK,onButtonClick);
    }


    public function set invoker(value:Invoker):void {
        _invoker = value;
    }

    private function onButtonClick(event:MouseEvent):void {
        if (_invoker == null) {
            throw new Error("CommandButton without invoker is not allowed");
        }
        command.start(_invoker);
    }

    public function get command():Command {
        return Command(data);
    }

    public function set command(cmd:Command):void {
        data = cmd;
        setStyle("icon", cmd.icon);
        setStyle("fontWeight", "normal");
        setStyle("textAlign", "center");
        enabled = cmd.isPermitted();
        mouseEnabled = cmd.isPermitted();
        size = _size;
    }


    public function set size(s:int):void {
        _size = s;
        if (command == null) return;

        if (_size == CommandButton.SMALL) {
            label = "";
            toolTip = command.label;
            height = 18;
            width = 18;
            setStyle("cornerRadius", "2");
        } else if (_size == CommandButton.MEDIUM) {
            label = "";
            toolTip = command.label;
            width = 25;
        } else {
            label = command.label;
            //toolTip = command.label;
        }
    }


    public function set labelWidth(val:int):void {
    }

    public function get labelWidth():int {
        return 0;
    }

    public function getSourceCode(xml:Boolean):String {
        if (xml) {
            return "<button x=\"" + x + "\" y=\"" + y + "\" w=\"" + width + "\" h=\"" + height + "\" label=\"" + label + "\" />";
        } else {
            return "insertCommandButton(\"" + Util.getUnqualifiedClassName(command) + "\"," + x + "," + y + "," + width + ");";
        }
    }
}
}
