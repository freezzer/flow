package de.ama.framework.gui.fields {
import de.ama.framework.data.Data;
import de.ama.framework.gui.frames.EditPanel;
import de.ama.framework.util.Util;

import flash.events.Event;
import flash.events.FocusEvent;

import mx.containers.Canvas;
import mx.controls.Label;
import mx.controls.TextInput;
import mx.core.UIComponent;

public class EditField extends Canvas{

    protected var _label:Label;
    protected var _input:UIComponent;

    private var _fullpath:String;
    private var _localpath:String;
    private var _labelWidth:int = 160;


    public function EditField(caption:String="EditField", path:String=null) {
        super.width=400;
        super.height=25;

        createLabel(caption);
        createInput();
        _localpath = path;

        _input.setStyle("color","0x202020");
        _input.addEventListener(Event.CHANGE, onInputCanged);
        _input.addEventListener(FocusEvent.FOCUS_OUT, onFocusLost);

        _label.setStyle("color","0x202020");
        _label.setStyle("textAlign","left");
        _label.y=3;

    }


    protected function onInputCanged(e:Event):void {
    }

    protected function onFocusLost(e:FocusEvent):void {
        var d:Data = editPanel.getData();
        d.setValue(localpath, getValue());
    }

    public function createLabel(caption:String):void{
        _label = new Label();
        _label.x = 5;
        _label.width = _labelWidth;

        label = caption;
        addChild(_label);
    }

    public function createInput():void{
        _input = new TextInput();
        _input.x = _labelWidth+10;
        _input.width = super.width - 15 - _labelWidth;
        addChild(_input);
    }

    public function set labelWidth(w:int):void{
        _labelWidth = w;
        _input.x = _labelWidth +10;
        _input.width = super.width - 15 - _labelWidth;
        _label.width = labelWidth;
    }

    public function get labelWidth():int {
        return _labelWidth;
    }

    override public function set width(w:Number):void {
        super.width = w+5;
        labelWidth = _labelWidth;
    }

    public override function get label():String {
        return _label.text;
    }

    public override function set label(val:String):void {
        _label.text = val;
        if(Util.isEmpty(_localpath)){
            _localpath = label.toLowerCase();
        }
    }

    public function get inputComponent():UIComponent {
        if(_input==null){
           createInput();
        }

        return _input;
    }

    public function get labelComponent():UIComponent {
        return _label;
    }


    public function get localpath():String {
        return _localpath;
    }

    public function get fullpath():String {
        if(Util.isEmpty(_fullpath)){
            _fullpath = editPanel.path +"/"+ _localpath
        }
        return _fullpath;
    }

    public function getValue():Object {
       return TextInput(_input).text;
    }

    public function setValue(val:Object):void {
       TextInput(_input).text = Util.saveToString(val);
    }

    public function get editPanel():EditPanel {
        return EditPanel(parent);
    }

}
}