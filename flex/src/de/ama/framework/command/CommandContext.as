package de.ama.framework.command {
import de.ama.framework.data.Data;
import de.ama.framework.data.SelectionModel;
import de.ama.framework.util.Util;

public class CommandContext {
    private var _properties:Array = null;
    private var _invoker:Invoker = null;
    private var _selectionModel:SelectionModel = null;


    public function getData(required:Boolean):Data{
        if(required && _invoker == null){
            throw new Error("CommandContext Illegal state: invoker is required in getData()");
        }

        if(required && _invoker.getData() == null){
            throw new Error("invoker has null Data");
        }

        return _invoker.getData();
    }

    //////////////////////////////////////////////////////////////////////

    public function setProperty(key:String, value:String):void {
        _properties[key] = value;
    }

    public function getProperty(key:String, def:String=""):String {
        if(_properties==null){
            return def;
        }
        return Util.saveToString(properties[key],def);
    }

    public function get selectionModel():SelectionModel {
        return _selectionModel;
    }

    public function set selectionModel(value:SelectionModel):void {
        _selectionModel = value;
    }

    public function get properties():Array {
        return _properties;
    }

    public function set properties(value:Array):void {
        _properties = value;
    }

    public function get invoker():Invoker {
        return _invoker;
    }

    public function set invoker(value:Invoker):void {
        _invoker = value;
    }
}
}