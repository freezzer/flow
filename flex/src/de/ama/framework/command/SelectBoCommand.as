package de.ama.framework.command {
import de.ama.framework.data.DataProvider;
import de.ama.framework.data.LookupDataProvider;
import de.ama.framework.gui.fields.ProxyField;
import de.ama.framework.gui.frames.LookupDialog;
import de.ama.framework.util.Callback;
import de.ama.framework.util.Util;
import de.ama.services.Factory;

import mx.core.Application;
import mx.core.Container;
import mx.managers.PopUpManager;

public class SelectBoCommand extends Command{
    private var _dlg:LookupDialog

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

            _dlg.setListPanel(pf.getListPanel());
            _dlg.reload();
        }

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