package de.ama.framework.data {
import de.ama.framework.util.Util;

import mx.collections.ArrayCollection;

public class DataTable2  {
	public var collection:Array = new Array();
    public var templateClassName:String;

    public function DataTable2(protoType:String, create:int=0) {
        this.templateClassName = protoType;
        for(var i:int=0; i<create; i++){
            addNewItem();
        }
    }

    public function fromArrayCollection(src:ArrayCollection):void{
        collection = src.source;
    }

    public function toArrayCollection(): ArrayCollection {
        return new ArrayCollection(collection);
    }


    public function transformFromArrayCollection(src:ArrayCollection, clazz:Class=null):void{
    	if(clazz==null) {clazz = getTypeClass(); }
        clear();
        var data:Data;
        for each (var obj:Object in src){
            data = new clazz();
            data.readProperties(obj);
            collection.push(data);
        }
    }

    public function transformToArrayCollection(dst:ArrayCollection, clazz:Class):void{
        dst.removeAll();
        var obj:Object;
        for each (var data:Data in collection){
            obj = new clazz();
            data.writeProperties(obj);
            dst.addItem(obj);
        }
    }

    public function addNewItem():Data{
        var clazz:Class = getTypeClass();
        var data:Data = new clazz();
        collection.push(data);
        return data;
    }

    public function addItem(data:Data):void{
        collection.push(data);
    }

    public function removeItem(data:Data):void{
        var tmp:ArrayCollection = toArrayCollection();
        tmp.removeItemAt(tmp.getItemIndex(data));
    }

    public function get length():int{
        return collection.length;
    }

    public function getTypeClass():Class{
        return Util.classForName(templateClassName);
    }


    public function getItemAt(i:int):Data {
        return Data(collection[i]);
    }

    public function clear():void{
        while(collection.length>0){ collection.pop(); }
    }

    public function clone(dst:DataTable=null):DataTable {
        if(dst==null){
            dst = new DataTable(templateClassName);
        }

        for each (var data:Data in collection){
            dst.addItem(data.clone());
        }
        
        return dst;
    }

    public function asString(indent:String):String {
 		indent += "  ";

        var ret:String = "\n"+indent+"DataTable of " + templateClassName+" " + length;
        
        for each (var data:Data in collection){
            ret += data.asString(indent);
        }

        indent.substr(0,indent.length-3);
        return ret;
    }

}
}