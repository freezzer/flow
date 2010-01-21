package de.ama.framework.data {
import de.ama.framework.action.ActionStarter;
import de.ama.framework.action.LoadTableAction;
import de.ama.framework.command.MenuInvoker;
import de.ama.framework.gui.frames.ListPanel;
import de.ama.framework.util.Callback;

public class QueryDataProvider extends DataProvider {


    public function QueryDataProvider(bo:BusinessObject=null) {
        invoker = new MenuInvoker(bo);
    }

    override public function getTable(cb:Callback) : void {
        callback = cb;
        var lta:LoadTableAction = new LoadTableAction();
        lta.selectionModel = invoker.getSelectionModel();
        ActionStarter.instance.execute(lta, new Callback(this, receiveData));
    }

    private function receiveData(sa:LoadTableAction):void {
        callback.execute(sa.data);
    }

}
}