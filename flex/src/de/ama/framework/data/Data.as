package de.ama.framework.data {
import de.ama.framework.gui.frames.TreeEditor;
import de.ama.framework.util.Util;

import mx.utils.ObjectUtil;

public class Data{
    public var oid:int;
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

    public function getGuiRepresentation():String {
       return getName(); 
    }

    public function clone():Data {
        var info:Object = ObjectUtil.getClassInfo(this);
        var c:Class = Util.getClass(this);
        var dst:Data = new c();

        for each(var key:String in info.properties) {
            var value:* = this[key];
            if(value is Data){
               dst[key] = Data(value).clone();
            } else if(value is Array){
               var sourceTable:Array = value as Array;
               var destTable:Array = new Array();
               var d:Data = null;
               for (var i:int=0; i< sourceTable.length; i++ ) {
  					d= Data(sourceTable[i]);
               	    destTable.push(d.clone());
               }
               dst[key] = destTable;
            } else {
               dst[key] = value;
            }
        }

        dst.oid=0;
        dst.version=0;
        return dst;
    }


    public function asString(indent:String):String {
  		indent += "  ";
    	
        var ret:String="\n"+indent+"Data ("+getName()+") " + oid+":"+version;
        var info:Object = ObjectUtil.getClassInfo(this);

        for each(var key:String in info.properties) {
            var value:* = this[key];
            if(value is Data){
               ret += Data(value).asString(indent);
            } else if(value is Array){
               var table:Array = value as Array;
               ret += indent+ "Array ("+table.length+")";	
               for each(var d:Data in table) {
               	  ret += d.asString(indent);
               }

            }
        }
        
        indent.substr(0,indent.length-3);
        return ret;
    }


}
}