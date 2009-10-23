package de.ama.framework.gui.frames {
import de.ama.framework.data.Data;
import de.ama.framework.gui.fields.BoolField;
import de.ama.framework.gui.fields.DateField;
import de.ama.framework.gui.fields.EditField;

import de.ama.framework.util.Util;

import flash.utils.describeType;

import mx.containers.Canvas;
import mx.effects.easing.Back;

public class EditPanel extends Canvas{

    private var _path:String;
    private var _generic:Boolean;
    private var _data:Data;

    public function EditPanel(path:String = "", generic:Boolean = false) {
        _path = path;
        _generic = generic;
        // setStyle("backgroundColor","0xf0f0f0");
        setStyle("paddingLeft","10");
        setStyle("paddingRight","10");
        setStyle("paddingTop","10");
        setStyle("paddingBottom","10");
    }

    public function get path():String {
        return _path;
    }

    public function set path(val:String):void {
        _path = val;
    }

    public function getData():Data {
        return _data;
    }


    public function setData(data:Data):void {
        _data = data;

        if (_generic) {
            createDefaultFields(_data);
        }

        var childs:Array = getChildren();
        var i:int;
        var len:int = childs.length;
        var f:EditField = null;
        for (i = 0; i < len; i++) {
            if (childs[i] is EditField) {
                f = childs[i];
                f.setValue(data.getValue(f.localpath));
            }
        }
    }

    public function createDefaultFields(data:Data):void {
        var classInfo:XML = describeType(data);
        for each (var v:XML in classInfo..variable) {
        	var t:String = v.@type;
            switch(t){
              case "String": {
              	 insertTextField(Util.firstCharToUpper(v.@name), v.@name);
              	 break;
              }
              case "Boolean":{
              	  insertBoolField(Util.firstCharToUpper(v.@name), v.@name);
              	 break;
              }
              case "Date": {
              	 insertDateField(Util.firstCharToUpper(v.@name), v.@name);
              	 break;
              }
            }
        }
    }


    public function insertTextField(label:String, path:String = null, x:int = -1, y:int = -1):EditField {
        var editField:EditField = new EditField(label, path);
        insertField(editField, x, y);
        return editField;
    }

    public function insertBoolField(label:String, path:String = null, x:int = -1, y:int = -1):BoolField {
        var boolField:BoolField = new BoolField(label, path);
        insertField(boolField, x, y);
        return boolField;
    }

    public function insertDateField(label:String, path:String = null, x:int = -1, y:int = -1):DateField {
        var dateField:DateField = new DateField(label, path);
        insertField(dateField, x, y);
        return dateField;
    }

    public function insertField(f:EditField, x:int = -1, y:int = -1):void {
        f.x = x > 0 ? x : 10;
        f.y = y > 0 ? y : (numChildren * 30);
        addChild(f);
    }

    public function getEditField(path:String):EditField {
        var childs:Array = getChildren();
        var i:int;
        var len:int = childs.length;
        var f:EditField = null;
        for (i = 0; i < len; i++) {
            if (childs[i] is EditField) {
                f = EditField(childs.getItemAt(i));
                if (f.localpath == path) return f;
            }
        }
        return null;
    }


    public function endEditing():void {

    }


    public function startEditing():void {
    }


    public function reset():void {
    }

}
}