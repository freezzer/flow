package de.ama.framework.gui.fields {
import de.ama.framework.util.Util;

import mx.controls.CheckBox;

public class BoolField extends EditField{


    public function BoolField(caption:String="BoolField", path:String=null) {
        super(caption,path);
    }

    public override function createInput():void{
        _input = new CheckBox();
        _input.x = labelWidth+10;
        _input.y = 2;
        addChild(_input);
        layout();
    }

    public override function setValue(val:Object):void {
        var sel:Boolean;
        if(val is Boolean) sel = Boolean(val);
        if(val is String)  sel = Util.isEqual(val,"true", true);
        if(val is Number)  sel = Util.isEqual(val,1);

        CheckBox(_input).selected = sel;
    }


    override public function getValue():Object {
        return CheckBox(_input).selected;
    }

    override public function initDefaultValue():void {
    }
    
    override public function getSourceCode(xml:Boolean):String {
        if(xml){
            return "<input x=\""+x+"\" y=\""+y+"\" w=\""+width+"\" labelwidth=\""+labelWidth+"\" h=\""+height+"\" label=\""+label+"\" path=\""+localpath+"\" type=\"boolean\" />";
        } else {
           return "insertBoolField(\""+label+"\",\""+localpath+"\","+x+","+y+","+labelWidth+","+width+");";
        }
    }

}
}