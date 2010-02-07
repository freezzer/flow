package de.ama.framework.data {
import de.ama.framework.action.ActionStarter;
import de.ama.framework.action.LoadBoAction;
import de.ama.framework.util.Callback;
import de.ama.framework.util.Util;

[RemoteClass(alias="de.ama.framework.data.BoReference")]
public class BoReference {
    public var oid:int;
    private var _type:String;
	public function set bo(d:Object):void{}; // nur für streaming

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
        if (_bo == null && hasReference()) {
            var sa:LoadBoAction = new LoadBoAction();
            sa.data = this;
            ActionStarter.instance.execute(sa, new Callback(this, resulthandler));
        } else {
            _cb.execute(_bo);
        }
    }

    private function hasReference():Boolean {
        if(Util.isEmpty(type)) return false;
        if(oid<=0) return false;
        return true;
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

    public function equals(other:BoReference):Boolean{
        if(other==null) return false;
        if(other==this) return true;
        if(other.oid != this.oid) return false;
        if(other._type != this._type) return false;
        return true;
    }
}
}