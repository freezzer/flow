package de.ama.framework.gui.fields {
import de.ama.framework.util.Util;

import mx.controls.TextArea;

public class TextAreaField extends EditField{

    public function TextAreaField(caption:String="TextAreaField", path:String=null) {
        super(caption,path);
    }

    public override function createInput():void{
        _input = new TextArea();
        addChild(_input);
        layout();
    }

    public override function setValue(val:Object):void {
        TextArea(_input).text = Util.saveToString(val);
    }

    override public function layout():void {
        super.layout();
        TextArea(_input).height = super.height;
    }


    override public function getValue():Object {
        return TextArea(_input).text;
    }

    override public function initDefaultValue():void {
    }
    
    override public function set height(h:Number):void {
        super.height = h;
        layout();
    }

    override public function getSourceCode(xml:Boolean):String {
        if(xml){
            return "<input x=\""+x+"\" y=\""+y+"\" w=\""+width+"\" labelwidth=\""+labelWidth+"\" h=\""+height+"\" label=\""+label+"\" path=\""+localpath+"\" type=\"area\" />";
        } else {
           return "insertTextAreaField(\""+label+"\",\""+localpath+"\","+x+","+y+","+labelWidth+","+width+","+height+");";
        }
    }
}
}