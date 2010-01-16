package de.ama.framework.gui.fields {
import de.ama.framework.data.BusinessObject;
import de.ama.framework.gui.frames.EditPanel;
import de.ama.framework.gui.frames.Editor;
import de.ama.framework.util.Callback;
import de.ama.framework.util.Util;
import de.ama.services.Environment;
import de.ama.services.text.SelectTextBausteinCommand;

import flash.events.Event;
import flash.events.FocusEvent;
import flash.events.KeyboardEvent;
import flash.events.MouseEvent;
import flash.ui.Keyboard;

import mx.containers.Canvas;
import mx.controls.Label;
import mx.controls.TextInput;
import mx.core.UIComponent;
import mx.utils.StringUtil;

public class EditField extends Canvas implements GUIComponent {

    protected var _label:Label;
    protected var _input:UIComponent;

    private var _defaultValue:String;

    private var _fullpath:String;
    private var _localpath:String;
    private var _labelWidth:int = 160;
    private var _editable:Boolean = true;
    private var _changeCallback:Callback=null;
    private var _enterCallback:Callback=null;
    private var _useTextService:Boolean =true;


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

        _input.addEventListener(Event.CHANGE, onInputChanged);
        _input.addEventListener(KeyboardEvent.KEY_UP, onKeyPressed);
        _input.addEventListener(FocusEvent.FOCUS_OUT, onFocusLost);

		if(_label!=null){
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

    public function get parentEditPanel():EditPanel {
        return EditPanel(parent);
    }
    
    public function get parentEditor():Editor {
        return Util.findParentEditor(this);
    }


    public function set changeCallback(value:Callback):void {
        _changeCallback = value;
    }


    public function set enterCallback(value:Callback):void {
        _enterCallback = value;
    }

    ////////////////////////////////////// layout ///////////////////////////////////////////////

    public function layout():void{
        _input.x = _labelWidth +10;
        _input.width = super.width - 15 - _labelWidth;
        _label.width = _labelWidth;
    }

    override public function set width(w:Number):void {
        super.width = w;
        labelWidth = _labelWidth;
    }

    ////////////////////////////////////// editable ///////////////////////////////////////////////

    public function get editable():Boolean{
        return _editable;
    }

    public function set editable(b:Boolean):void{
        _editable = b;
        inputComponent.setStyle("editable",_editable);
    }

    public function setReadOnly():void{
        inputComponent.setStyle("editable",false);
    }

    public function resetReadOnly():void{
        inputComponent.setStyle("editable",_editable);
    }



    ////////////////////////////////////// input ///////////////////////////////////////////////

    public function createInput():void{
        _input = new TextInput();
        _input.x = _labelWidth+10;
        _input.width = super.width - 15 - _labelWidth;
        addChild(_input);
    }

    public function get inputComponent():UIComponent {
        if(_input==null){
           createInput();
        }

        return _input;
    }

    protected function onInputChanged(e:Event):void {
        if(_changeCallback!=null){
            _changeCallback.execute(this);
        }
    }

    protected function onKeyPressed(e:KeyboardEvent):void {

        if(_enterCallback!=null && e.keyCode == Keyboard.ENTER){
            _enterCallback.execute(this);
        }

        if(_useTextService && e.keyCode == Keyboard.SPACE && e.ctrlKey){
            var cmd:SelectTextBausteinCommand = new SelectTextBausteinCommand();
            cmd.start(new EditFieldInvoker(this));
        }
    }

    public function getTextAtCaret():Object {
        var ret:Object = null;
        var start:int = selectionBeginIndex-1;
        var stop:int = selectionBeginIndex-1;
        var string:String = getInputText();
        var c:String = null;
        var len:int = string.length;
        
        while ( start > 0 ) {
        	c = string.charAt(start);
            if((c != " ") && (c != "\r")) { break};
            start--;
        }

        while ( start >= 0 ) {
        	c = string.charAt(start);
            if(c == " ") { start++ ; break};
            if(c == "\n") { start++ ; break};
            if(c == "\r") { start++ ; break};
            start--;
        }
		
		if(start<0) start = 0;
		
        stop = start;
        while (stop < len) {
        	c = string.charAt(stop);
            if(c == " ") { break; }
            if(c == "\n") { break; }
            if(c == "\r") { break; }
            stop++;
        }

        ret = new Object();
        ret.start = start;
        ret.stop = stop;
        var tmp:String = string.substr(start, stop-start);
        ret.key = StringUtil.trim(tmp);

        return ret;
    }

    protected function onFocusLost(e:FocusEvent):void {
        writeToData();
    }

    public function setCaretPosition(pos:int):void {
        selectionBeginIndex = pos;
        selectionEndIndex = pos;
    }

    public function replaceText(_start:int, _stop:int, key:String, text:String):void {
        var fieldText:String = getInputText();
        var pre:String = fieldText.substr(0, _start);
        var post:String = fieldText.substr(_stop);
        setInputText(pre + text + post);
        setCaretPosition(_stop+text.length+1);
    }

    public function set selectionBeginIndex(pos:int):void{
        if (inputComponent is TextInput) {
            TextInput(inputComponent).selectionBeginIndex=pos;
        }
    }

    public function get selectionBeginIndex():int{
        if (inputComponent is TextInput) {
            return TextInput(inputComponent).selectionBeginIndex;
        }
        return 0;
    }

    public function set selectionEndIndex(pos:int):void{
        if (inputComponent is TextInput) {
            TextInput(inputComponent).selectionEndIndex=pos;
        }
    }

    public function get selectionEndIndex():int{
        if (inputComponent is TextInput) {
            return TextInput(inputComponent).selectionEndIndex;
        }
        return 0;
    }
    ////////////////////////////////////// label ///////////////////////////////////////////////

    public function createLabel(caption:String):void{
        _label = new Label();
        _label.x = 5;
        _label.width = _labelWidth;

        label = caption;
        addChild(_label);
    }

    public function get labelComponent():UIComponent {
        return _label;
    }

    public function set labelWidth(w:int):void{
        _labelWidth = w;
        layout();
    }

    public function get labelWidth():int {
        return _labelWidth;
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

    ////////////////////////////////////// value ///////////////////////////////////////////////

    public function getValue():Object {
       return TextInput(_input).text;
    }

    public function setValue(val:Object):void {
       TextInput(_input).text = Util.saveToString(val);
    }

    public function isEmpty():Boolean {
       return Util.isEmpty(getInputText());
    }

    public function getInputText():String {
       return String(getValue());
    }

    public function setInputText(txt:String ):void {
       setValue(txt);
       writeToData();
    }

    public function get defaultValue():String {
        return _defaultValue;
    }

    public function set defaultValue(value:String):void {
        _defaultValue = value;
    }

    public function initDefaultValue():void {
        if(isEmpty() && !Util.isEmpty(defaultValue)){
            if(defaultValue.indexOf("{system.user.name}")==0){
               setValue(Environment.getUserName());
            } else {
               setValue(defaultValue);
            }
            writeToData();
        }
    }

    public function writeToData():void {
        var d:BusinessObject = parentEditPanel.getData();
        if(d!=null){
           if(localpath.indexOf("#")==0){
              parentEditor.getData().setValue(localpath.substring(1), getValue());
           } else {
               d.setValue(localpath, getValue());
           }
        }
    }

    public function get localpath():String {
        return _localpath;
    }

    public function get fullpath():String {
        if(Util.isEmpty(_fullpath)){
            _fullpath = parentEditPanel.path +"/"+ _localpath
        }
        return _fullpath;
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