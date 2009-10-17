package de.ama.framework.data {
import de.ama.framework.gui.frames.TreeEditor;
import de.ama.framework.util.Util;

import mx.utils.ObjectUtil;

public class Data{
    public var oidString:String;
    public var version:int;

    public function readProperties(src:Object):void{
        Util.mapProperties(src,this);
    }

    public function writeProperties(dst:Object):void{
        Util.mapProperties(this,dst);
    }

    public function getProperty(path:String,def:String=""):String{
        return Util.getObjectProperty(this,path,def);
    }

    public function getValue(path:String):Object{
        return Util.getObjectValue(this,path);
    }

    public function setValue(path:String, val:Object):void{
        return Util.setObjectValue(this,path,val);
    }

    public function getName():String {
       return Util.getUnqualifiedClassName(this); 
    }

    public function createEditor():TreeEditor {
       return null;
    }

    public function clone():Data {
        var info:Object = ObjectUtil.getClassInfo(this);
        var c:Class = Util.getClass(this);
        var dst:Data = new c();

        for each(var key:String in info.properties) {
            var value:* = this[key];
            if(value is Data){
               value = Data(value).clone();
            } else if(value is DataTable){
               var table:DataTable = DataTable(value);
               value = table.clone();
            }
            dst[key] = value;
        }

        dst.oidString=null;
        dst.version=0;
        return dst;
    }


    public function asString(indent:String):String {
  		indent += "  ";
    	
        var ret:String="\n"+indent+"Data ("+getName()+")";
        var info:Object = ObjectUtil.getClassInfo(this);

        for each(var key:String in info.properties) {
            var value:* = this[key];
            if(value is Data){
               ret += Data(value).asString(indent);
            } else if(value is DataTable){
               var table:DataTable = DataTable(value);
               ret += table.asString(indent);
            }
        }
        
        indent.substr(0,indent.length-3);
        return ret;
    }

}
}