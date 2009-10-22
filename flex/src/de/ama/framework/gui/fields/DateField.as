package de.ama.framework.gui.fields {
import mx.controls.DateField;

public class DateField extends EditField{


    public function DateField(caption:String="DateField", path:String = null) {
        super(caption, path);
    }

    public override function createInput():void {
        _input = new mx.controls.DateField();
        _input.x = labelWidth + 10;
        addChild(_input);
    }

    public override function setValue(val:Object):void {
        DateField(_input).data = val;
    }

    override public function getValue():Object {
        return DateField(_input).data;
    }
}
}