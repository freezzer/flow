package de.ama.framework.data {
import de.ama.framework.action.ActionStarter;
import de.ama.framework.action.LoadTableAction;
import de.ama.framework.data.SelectionModel;
import de.ama.framework.util.Callback;
import de.ama.services.Factory;

import flash.utils.setTimeout;

public class LookupCache {

    private static var _instance:LookupCache = null;

    private var table_dictionary:Object = new Object();
    private var wait:Boolean = false;

    public static function get instance ():LookupCache{
        if(_instance==null){
            _instance = new LookupCache();
        }
        return _instance;
    }

    public function getTable(selectionModel:SelectionModel):Array {
        var key:String = selectionModel.type;
        var table:Array = table_dictionary[key];
        if(table == null){
            var lta:LoadTableAction = new LoadTableAction();
            lta.data = Factory.createBean(selectionModel.type);
            lta.selectionModel = selectionModel;
            wait = true;
            ActionStarter.instance.execute(lta, new Callback(this, resulthandler));
        }
        waitForResponse();
        return table;
    }

    private function waitForResponse():void {
        while(wait) {
            setTimeout(waitForResponse,100);
        }
    }

    private function resulthandler(a:LoadTableAction):void {
        var key:String = a.selectionModel.type;
        table_dictionary[key] = a.data as Array;
        wait = false;
    }

}}