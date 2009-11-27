package de.ama.framework.command {
import de.ama.framework.data.BusinessObject;
import de.ama.framework.data.SelectionModel;

public class MenuInvoker implements Invoker{
    private var bo:BusinessObject;


    public function MenuInvoker(bo:BusinessObject=null) {
        this.bo = bo;
    }

    public function getData():BusinessObject {
        return bo;
    }

    public function setData(value:BusinessObject):void {
        bo = value;
    }

    public function getSelectionModel():SelectionModel {
        return new SelectionModel(getData());
    }
}
}