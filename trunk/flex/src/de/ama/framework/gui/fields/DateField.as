package de.ama.framework.gui.fields {
import de.ama.framework.util.Util;

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
        layout();
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
    
    override public function initDefaultValue():void {
    	if(isEmpty() && !Util.isEmpty(defaultValue)){
            if(defaultValue.indexOf("{system.date}")==0){
               setValue(new Date());
            } else {
               setValue(defaultValue);
            }
            writeToData();
        }

    }
      
    override public function getSourceCode(xml:Boolean):String {
        if(xml){
            return "<input x=\""+x+"\" y=\""+y+"\" w=\""+width+"\" labelwidth=\""+labelWidth+"\" h=\""+height+"\" label=\""+label+"\" path=\""+localpath+"\" type=\"date\" />";
        } else {
           return "insertDateField(\""+label+"\",\""+localpath+"\","+x+","+y+","+labelWidth+","+width+");";
        }
    }
}
}