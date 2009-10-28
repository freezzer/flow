package de.ama.framework.gui.fields {
import mx.controls.DateField;

public class DateField extends EditField{
    public static var FORMAT:String = "DD.MM.YYYY";

    public function DateField(caption:String="DateField", path:String = null) {
        super(caption, path);
    }

    public override function createInput():void {
        var df:mx.controls.DateField = new mx.controls.DateField();
        df.formatString=FORMAT;
        df.editable = true;
        _input=df;
        addChild(_input);
        labelWidth=super.labelWidth; // resize
    }

    public override function setValue(val:Object):void {
    	if(val==null) return;
        if(val is Date){
            mx.controls.DateField(_input).selectedDate = val as Date;
        } else if(val is String){
        	var str:String = val as String;
        	if(str.length == 10){
               mx.controls.DateField(_input).selectedDate = mx.controls.DateField.stringToDate(String(val),FORMAT);
            }
        }
    }

    override public function getValue():Object {
        return mx.controls.DateField.dateToString(mx.controls.DateField(_input).selectedDate,FORMAT);
    }
}
}