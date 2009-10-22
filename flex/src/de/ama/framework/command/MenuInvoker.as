package de.ama.framework.command {
import de.ama.framework.data.Data;

public class MenuInvoker implements Invoker{
    private var data:Data;


    public function MenuInvoker(data:Data=null) {
        this.data = data;
    }

    public function getData():Data {
        return data;
    }

    public function setData(value:Data):void {
        data = value;
    }
}
}