package de.ama.framework.gui.fields {
import de.ama.framework.util.Util;

import flash.events.Event;

import mx.controls.RichTextEditor;


public class RichTextEditorField extends EditField{

    public var enableLinkInput:Boolean;
    public var enableBulletButton:Boolean;
    public var enableAlignButtons:Boolean;
    public var enableColorPicker:Boolean;

    public function RichTextEditorField(caption:String="RichTextEditorField", path:String=null) {
        super(caption,path);
    }

    public override function createInput():void{
        _input = new RichTextEditor();
        _input.addEventListener("creationComplete",configureEditor)
        addChild(_input);
        layout();
    }


    private function configureEditor(event:Event):void {
        if(!enableLinkInput){
            RichTextEditor(_input).toolbar.removeChild(RichTextEditor(_input).linkTextInput);
        }
        if(!enableBulletButton){
            RichTextEditor(_input).toolbar.removeChild(RichTextEditor(_input).bulletButton);
        }
        if(!enableAlignButtons){
            RichTextEditor(_input).toolbar.removeChild(RichTextEditor(_input).alignButtons);
        }
        if(!enableColorPicker){
            RichTextEditor(_input).toolbar.removeChild(RichTextEditor(_input).colorPicker);
        }
    }

    public override function setValue(val:Object):void {
        RichTextEditor(_input).htmlText = Util.saveToString(val);
    }

    override public function getValue():Object {
        return RichTextEditor(_input).htmlText;
    }

    override public function getInputText():String {
        return RichTextEditor(_input).text;
    }


    override public function replaceText(_start:int, _stop:int,  key:String, text:String):void {
        var htmlText:String = String(getValue());
        setInputText(htmlText.replace(key,text));
        setCaretPosition(_stop+text.length+1);
    }

    override public function layout():void {
        super.layout();
        RichTextEditor(_input).height = super.height;
    }


    override public function set height(h:Number):void {
        super.height = h;
        layout();
    }
    
    override public function set selectionBeginIndex(pos:int):void{
       RichTextEditor(inputComponent).textArea.selectionBeginIndex=pos;
    }

    override public function get selectionBeginIndex():int{
       return RichTextEditor(inputComponent).textArea.selectionBeginIndex;
    }

    override public function set selectionEndIndex(pos:int):void{
       RichTextEditor(inputComponent).textArea.selectionEndIndex=pos;
    }

    override public function get selectionEndIndex():int{
       return RichTextEditor(inputComponent).textArea.selectionEndIndex;
    }



    override public function getSourceCode(xml:Boolean):String {
        if(xml){
            return "<input x=\""+x+"\" y=\""+y+"\" w=\""+width+"\" labelwidth=\""+labelWidth+"\" h=\""+height+"\" label=\""+label+"\" path=\""+localpath+"\" type=\"editor\" />";
        } else {
           return "insertRichTextEditorField(\""+label+"\",\""+localpath+"\","+x+","+y+","+labelWidth+","+width+","+height+");";
        }
    }
}
}