package de.ama.framework.command {
import de.ama.framework.data.BoReference;
import de.ama.framework.data.LookupDataProvider;
import de.ama.framework.gui.fields.ProxyField;
import de.ama.framework.gui.frames.LookupDialog;
import de.ama.framework.util.Callback;

import mx.core.Application;
import mx.core.Container;
import mx.managers.PopUpManager;

public class SelectBoCommand extends Command{
    private var _dlg:LookupDialog;
    private var _dataTable:Array;
    private var _callBack:Callback;

    public function SelectBoCommand(label:String="auswaehlen",icon:String="search") {
        super(label,icon);
    }

    override protected function execute():void {
        _dlg = LookupDialog(PopUpManager.createPopUp(Container(Application.application), LookupDialog, true));
        _dlg.x = (Application.application.width / 2 - (_dlg.width / 2));
        _dlg.y = (Application.application.height / 2 - (_dlg.height / 2));
        _dlg.setCallback(new Callback(this, lookupDone));

        if(invoker is ProxyField){
            var pf:ProxyField = ProxyField(invoker);
            if(!pf.getListPanel().hasDataProvider()){
                var lookupDataProvider:LookupDataProvider = new LookupDataProvider();
                pf.getListPanel().setDataProvider(lookupDataProvider)
            }

            pf.getListPanel().getDataProvider().invoker = pf;

            _dlg.callLater(function ():void {
                _dlg.setListPanel(pf.getListPanel());
                _dlg.reload();
            });
        }

        if(_dataTable!=null){
            _dlg.callLater(function ():void {
                _dlg.getListPanel().setDataTable(_dataTable);
            });
        }

    }


    private function lookupDone(dlg:LookupDialog):void {
       var result:BoReference =  _dlg.selection;

       if(invoker is ProxyField){
       	  var pf:ProxyField = ProxyField(invoker);
          pf.setValue(result);
          pf.writeToData();
       }

        if(_callBack!=null){
           _callBack.execute(result); 
        }

       _dlg = null;
    }


    public function set callBack(value:Callback):void {
        _callBack = value;
    }


    public function set dataTable(value:Array):void {
        _dataTable = value;
    }
}
}