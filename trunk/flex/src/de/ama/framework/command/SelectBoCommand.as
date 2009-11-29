package de.ama.framework.command {
import de.ama.framework.data.DataProvider;
import de.ama.framework.data.LookupCache;
import de.ama.framework.gui.fields.ProxyField;
import de.ama.framework.gui.frames.LookupDialog;
import de.ama.framework.util.Callback;

import mx.core.Application;
import mx.core.Container;
import mx.managers.PopUpManager;

public class SelectBoCommand extends Command{
    private var _dlg:LookupDialog
    private var _dataProvider:DataProvider=null;

    public function SelectBoCommand(label:String="auswaehlen",icon:String="search") {
        super(label,icon);
    }


    public function set dataProvider(value:DataProvider):void {
        _dataProvider = value;
    }

    override protected function execute():void {
        _dlg = LookupDialog(PopUpManager.createPopUp(Container(Application.application), LookupDialog, true));
        _dlg.x = (Application.application.width / 2 - (_dlg.width / 2));
        _dlg.y = (Application.application.height / 2 - (_dlg.height / 2));
        _dlg.lister = getProperty("lister");
        _dlg.setCallback(new Callback(this, lookupDone));
        if(_dataProvider!=null){
            insertArray(_dataProvider.getTable(invoker));
        } else {
            LookupCache.instance.getTable(selectionModel, new Callback(this, insertArray));
        }
    }

    public function insertArray(array:Array):void {
        _dlg.setDataTable(array);
    }

    private function lookupDone(dlg:LookupDialog):void {
       if(invoker is ProxyField){
       	  var pf:ProxyField = ProxyField(invoker);
          pf.setValue(_dlg.selection); 
          pf.writeToData();
       } 
       _dlg = null;
    }

}
}