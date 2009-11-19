package de.ama.framework.command {
import de.ama.framework.data.LookupCache;
import de.ama.framework.gui.frames.LookupDialog;
import de.ama.framework.util.Callback;

import mx.core.Application;
import mx.core.Container;
import mx.managers.PopUpManager;

public class SelectBoCommand extends Command{


    public function SelectBoCommand(label:String="auswaehlen",icon:String="search") {
        super(label,icon);
    }

    override protected function execute():void {
        var dlg:LookupDialog = LookupDialog(PopUpManager.createPopUp(Container(Application.application), LookupDialog, true));
        dlg.x = (Application.application.width / 2 - (dlg.width / 2));
        dlg.y = (Application.application.height / 2 - (dlg.height / 2));
        dlg.setDataTable(LookupCache.instance.getTable(selectionModel));
        dlg.setCallback(new Callback(this, lookupDone));
    }

    private function lookupDone(dlg:LookupDialog):void {
        
    }

}
}