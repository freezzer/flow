package de.ama.services.permission {
import de.ama.framework.action.ActionStarter;
import de.ama.framework.command.Command;
import de.ama.framework.util.Callback;
import de.ama.framework.util.Util;
import de.ama.services.Factory;

public class PermissionService {
    private static var _instance:PermissionService=null; 

    public static function get instance():PermissionService {
        if(_instance==null){
           _instance = new PermissionService();
        }
        return _instance;
    }

    private var permissionContexts:Array = new Array;

    public function isPermitted(cmd:Command):Boolean{
        for each(var s:PermissionContext in permissionContexts) {
            if(Util.isEqual(s.context , cmd.permissionContext)) {
                return s.isPermitted(cmd.permissionKey);
            }
        }
        return false;
    }


    public function setPermissionContexts(pcs:Array):void {
       permissionContexts=pcs;
    }

    public function load():void{
        if(permissionContexts.length==0){
            var sa:GetPermissionAction = new GetPermissionAction();
            sa.context = "all";
            ActionStarter.instance.execute(sa , new Callback(this, resulthandler ));
        }
    }

    private function resulthandler(action:GetPermissionAction): void {
        if(action.data is Array){
            setPermissionContexts(action.data as Array);
        }
    }

//    public function loadPermissionContext(ctxt:String):void{
//        if(permissionContexts[ctxt]==null){
//            var sa:GetPermissionAction = new GetPermissionAction();
//            sa.context = ctxt;
//            ActionStarter.instance.execute(sa , new Callback(this, resulthandler ));
//        }
//    }


    public function getPermissionsData():PermissionsData {
        var data:PermissionsData = PermissionsData(Factory.createBean("PermissionsData"));
        data.contexts = permissionContexts;
        return data;
    }}


}