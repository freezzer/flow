package de.ama.framework.data {
import de.ama.framework.action.ActionStarter;
import de.ama.framework.action.LoadBoAction;
import de.ama.framework.util.Callback;
import de.ama.framework.util.Util;

[RemoteClass(alias="de.ama.framework.data.BoReference")]
public class BoReference {
    public var oid:int;
    private var _type:String;

    [Transient]
    private var _bo:BusinessObject;
    [Transient]
    private var _cb:Callback;

    public function BoReference(bo:BusinessObject = null) {
        setBusinessObject(bo);
    }

    public function setBusinessObject(value:BusinessObject):void {
        _bo = value;
        if (_bo != null) {
            oid = _bo.oid;
            type = Util.getClassName(_bo);
        }
    }

    public function hasBusinessObject():Boolean {
       return _bo !=null;
    }

    public function getBusinessObject(cb:Callback):void {
        _cb = cb;
        if (_bo == null) {
            var sa:LoadBoAction = new LoadBoAction();
            sa.data = this;
            ActionStarter.instance.execute(sa, new Callback(this, resulthandler));
        } else {
            _cb.execute(_bo);
        }
    }


    private function resulthandler(action:LoadBoAction): void {
        setBusinessObject(BusinessObject(action.data));
        _cb.execute(_bo);
    }


    public function get type():String {
        return _type;
    }

    public function set type(value:String):void {
        _type = value;
    }
}
}