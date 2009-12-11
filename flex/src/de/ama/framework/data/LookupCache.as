package de.ama.framework.data {
import de.ama.framework.action.ActionStarter;
import de.ama.framework.action.LoadTableAction;
import de.ama.framework.util.Callback;
import de.ama.services.Factory;

import flash.utils.setTimeout;

public class LookupCache {

    private static var _instance:LookupCache = null;

    private var table_dictionary:Object = new Object();
    private var _cb:Callback = null;

    public static function get instance ():LookupCache{
        if(_instance==null){
            _instance = new LookupCache();
        }
        return _instance;
    }

    public function getTable(selectionModel:SelectionModel, cb:Callback):void {
        _cb = cb;
        var key:String = selectionModel.type.replace("::",".");
        var table:Array = table_dictionary[key];
        if(table == null){
            var lta:LoadTableAction = new LoadTableAction();
            lta.data = Factory.createBean(selectionModel.type);
            lta.selectionModel = selectionModel;
            ActionStarter.instance.execute(lta, new Callback(this, resulthandler));
        } else {
            _cb.execute(table);
        }
    }

    private function resulthandler(a:LoadTableAction):void {
        var key:String = a.selectionModel.type;
        var array:Array = a.data as Array;
        table_dictionary[key] = array;
        _cb.execute(array);
    }

}}