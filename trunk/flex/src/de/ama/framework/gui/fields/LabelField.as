package de.ama.framework.gui.fields {
import de.ama.framework.util.Util;

import mx.controls.CheckBox;
import mx.controls.Label;

public class LabelField extends EditField{


    public function LabelField(caption:String="LabelField", path:String=null) {
        super(caption,path);
    }

    public override function createInput():void{
        _input = new Label();
        _input.x = labelWidth+10;
        _input.y = 2;
        addChild(_input);
        layout();
    }

    override public function getValue():Object {
       return Label(_input).text;
    }

    override public function setValue(val:Object):void {
       Label(_input).text = Util.saveToString(val);
    }



    override public function getSourceCode(xml:Boolean):String {
        if(xml){
            return "<input x=\""+x+"\" y=\""+y+"\" w=\""+width+"\" labelwidth=\""+labelWidth+"\" h=\""+height+"\" label=\""+label+"\" path=\""+localpath+"\" type=\"label\" />";
        } else {
           return "insertLabelField(\""+label+"\",\""+localpath+"\","+x+","+y+","+labelWidth+","+width+");";
        }
    }

}
}