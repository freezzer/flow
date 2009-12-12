package de.ama.framework.data {
import de.ama.framework.command.Invoker;
import de.ama.framework.util.Callback;

public class DataProvider {
    private var _invoker:Invoker;
    private var _callback:Callback;
    private var _path:String;
    private var _query:String;
    private var _type:String;


    public function get invoker():Invoker {
        return _invoker;
    }

    public function set invoker(value:Invoker):void {
        _invoker = value;
    }

    public function get callback():Callback {
        return _callback;
    }

    public function set callback(value:Callback):void {
        _callback = value;
    }

    public function get path():String {
        return _path;
    }

    public function set path(value:String):void {
        _path = value;
    }

    public function get query():String {
        return _query;
    }

    public function set query(value:String):void {
        _query = value;
    }

    public function get type():String {
        return _type;
    }

    public function set type(value:String):void {
        _type = value;
    }

    public function getTable(cb:Callback) : void{

    }
}
}