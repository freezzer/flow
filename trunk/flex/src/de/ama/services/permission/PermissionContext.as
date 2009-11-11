package de.ama.services.permission {
import de.ama.framework.data.Data;
import de.ama.framework.util.Util;

public class PermissionContext extends Data{

public var permitted:Boolean;

public var userId:String;
public var userName:String;
public var context:String;
public var switches:Array ;

public function isPermitted(key:String):Boolean{
    for each(var s:PermissionSwitch in switches) {
        if(Util.isEqual(s.key,key)) {
            return s.isOn;
        }
    }
    return false;
}

public function allPermitted(b:Boolean):void{
    for each(var s:PermissionSwitch in switches) {
       s.isOn = b;
    }
}



}
}