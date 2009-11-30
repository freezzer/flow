package de.ama.framework.data {
import de.ama.framework.command.Invoker;
import de.ama.framework.util.Callback;

public interface DataProvider {

    function setInvoker(invoker:Invoker) : void;
    function getTable(cb:Callback) : void;

}
}