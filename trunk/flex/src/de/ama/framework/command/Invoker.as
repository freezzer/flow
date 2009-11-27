package de.ama.framework.command {
import de.ama.framework.data.BusinessObject;
import de.ama.framework.data.SelectionModel;

public interface Invoker {

    function setData(bo:BusinessObject):void;
    function getData():BusinessObject;
    function getSelectionModel():SelectionModel;

}
}