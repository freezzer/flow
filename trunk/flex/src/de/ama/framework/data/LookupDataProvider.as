package de.ama.framework.data {
import de.ama.framework.util.Callback;

public class LookupDataProvider extends DataProvider {


    override public function getTable(cb:Callback) : void {
       LookupCache.instance.getTable(invoker.getSelectionModel() ,cb);
    }

}
}