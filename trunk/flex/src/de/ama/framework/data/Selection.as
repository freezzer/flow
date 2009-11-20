package de.ama.framework.data {
import de.ama.framework.action.ActionStarter;
import de.ama.framework.action.LoadBoAction;
import de.ama.framework.util.Callback;
import de.ama.framework.util.Util;

public class Selection {
    private var _data:Data;
    public var className:String;
    public var oid:int = 0;

    public function Selection(data:Data=null) {
        this.data = data;
    }

    public function set data(d:Data):void {
        if(data!=null){
           oid = data.oid;
           className  = Util.getClassName(data);
           _data = data;
        } else {
           _data = null;
           className = "";
           oid = 0;
        }
    }

    public function get data():Data {
    	if(_data==null){
            if(oid>0 && !Util.isEmpty(className)){
                var sa:LoadBoAction = new LoadBoAction();
                sa.selectionModel = new SelectionModel();
                sa.selectionModel.addSelection(this);
                ActionStarter.instance.execute(sa , new Callback(this, resulthandler ));
            }
        }
        return _data;
    }

    private function resulthandler(action:LoadBoAction): void {
         data = Data(action.data);
    }

    public function toString():String {
        if(hasData()){
          return _data.getName()  
        }
        return "";
    }

    public function hasData():Boolean {
        return _data != null;
    }

}}