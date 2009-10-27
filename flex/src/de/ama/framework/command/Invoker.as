package de.ama.framework.command {
import de.ama.framework.data.Data;
import de.ama.framework.data.SelectionModel;

public interface Invoker {

    function setData(data:Data):void;
    function getData():Data;
    function getSelectionModel():SelectionModel;

}
}