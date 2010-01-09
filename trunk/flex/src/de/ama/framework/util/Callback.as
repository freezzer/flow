package de.ama.framework.util {
public class Callback {
    private var _target:Object;
    private var _functionHome:Object;
    private var _function:Function;


    public function Callback(obj:Object, func:Function, target:Object=null){
        this._function = func;
        this._functionHome = obj;
        this._target = target;
    }


    public function execute(args:Object):void{
        if(_target!=null){
            _function.call(_functionHome,args,_target);
        } else {
            _function.call(_functionHome,args);
        }
    }
    
}
}