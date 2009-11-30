package de.ama.framework.data {
import de.ama.framework.action.ActionStarter;
import de.ama.framework.action.LoadTableAction;
import de.ama.framework.command.Invoker;
import de.ama.framework.util.Callback;

public class QueryDataProvider implements DataProvider {

    private var _invoker:Invoker;
    private var _cb:Callback;

    public function setInvoker(value:Invoker):void {
        _invoker = value;
    }

    public function getTable(cb:Callback) : void {
        _cb = cb;
        var lta:LoadTableAction = new LoadTableAction();
        lta.selectionModel = _invoker.getSelectionModel();
        ActionStarter.instance.execute(lta, new Callback(this, receiveData));
    }

    private function receiveData(sa:LoadTableAction):void {
        _cb.execute(sa.data);
    }

}
}