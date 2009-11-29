package de.ama.framework.gui.fields {
import de.ama.framework.data.BusinessObject;
import de.ama.framework.gui.frames.EditPanel;
import de.ama.framework.util.Util;

import de.ama.services.Environment;

import flash.events.Event;
import flash.events.EventPhase;
import flash.events.FocusEvent;
import flash.events.MouseEvent;

import mx.containers.Canvas;
import mx.controls.Label;
import mx.controls.TextInput;
import mx.core.UIComponent;

public class EditField extends Canvas implements GUIComponent {

    protected var _label:Label;
    protected var _input:UIComponent;

    private var _fullpath:String;
    private var _localpath:String;
    private var _labelWidth:int = 160;


    public function EditField(caption:String="EditField", path:String=null) {
        super.width=300;
        super.height=25;
        super.setStyle("horizontalScrollPolicy","off");
        super.setStyle("verticalScrollPolicy","off");
        super.setStyle("clipContent","false");

        createLabel(caption);
        createInput();
        createAditionals();
        _localpath = path;

        _input.setStyle("color","0x202020");
        _input.addEventListener(Event.CHANGE, onInputCanged);
        _input.addEventListener(FocusEvent.FOCUS_OUT, onFocusLost);

		if(_label!=null){
	        _label.setStyle("color","0x202020");
	        _label.setStyle("textAlign","left");
	        _label.y=3;
        }
        
        if(_label){
           _label.addEventListener(MouseEvent.CLICK,onClick);
        }

        super.addEventListener(MouseEvent.MOUSE_DOWN, startDragging);
        super.addEventListener(MouseEvent.MOUSE_UP, stopDragging);

    }

    protected function createAditionals():void {
    }


    protected function onInputCanged(e:Event):void {
    }

    protected function onFocusLost(e:FocusEvent):void {
        writeToData();
    }

    public function writeToData():void {
        var d:BusinessObject = editPanel.getData();
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
        layout();
    }

    public function layout():void{
        _input.x = _labelWidth +10;
        _input.width = super.width - 15 - _labelWidth;
        _label.width = _labelWidth;
    }

    public function get labelWidth():int {
        return _labelWidth;
    }

    override public function set width(w:Number):void {
        super.width = w;
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
    
    ////////////////////////////////////// design mode ///////////////////////////////////////////////

    protected function onClick(event:MouseEvent):void {
        if(Environment.designer ){
            Environment.designer.addGuiComponent(this,event.ctrlKey);
        }
    }

    private function startDragging(event:MouseEvent):void {
        if(Environment.designer ){
           super.startDrag();
        }
    }

    private function stopDragging(event:MouseEvent):void {
        if(Environment.designer ){
            super.stopDrag();
        }
    }

    public function getSourceCode(xml:Boolean):String {
        if(xml){
           return "<input x=\""+x+"\" y=\""+y+"\" w=\""+width+"\" labelwidth=\""+labelWidth+"\" h=\""+height+"\" label=\""+label+"\" path=\""+localpath+"\" type=\"string\" />";
        } else {
           return "insertTextField(\""+label+"\",\""+localpath+"\","+x+","+y+","+labelWidth+","+width+");";
        }
    }
    

}
}