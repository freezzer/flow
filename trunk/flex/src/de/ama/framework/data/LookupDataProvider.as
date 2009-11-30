package de.ama.framework.data {
import de.ama.framework.command.Invoker;
import de.ama.framework.util.Callback;

public class LookupDataProvider implements DataProvider {

    private var _invoker:Invoker;


    public function setInvoker(value:Invoker):void {
        _invoker = value;
    }

    public function getTable(cb:Callback) : void {
       LookupCache.instance.getTable(_invoker.getSelectionModel() ,cb);
    }

}
}