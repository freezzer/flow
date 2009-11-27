package de.ama.services.permission {
import comp.permission.PermissionMainMenu;
import comp.permission.PermissionOrganisation;
import comp.permission.PermissionPerson;

import de.ama.framework.action.ActionScriptAction;
import de.ama.framework.data.BusinessObject;

[RemoteClass(alias="de.ama.services.permission.GetPermissionAction")]
public class GetPermissionAction extends ActionScriptAction{


    public function GetPermissionAction() {
//        registry.push(new BusinessObject());
//        registry.push(new PermissionContext());
//        registry.push(new PermissionMainMenu());
//        registry.push(new PermissionOrganisation());
//        registry.push(new PermissionPerson());
//        registry.push(new PermissionSwitch());
//        registry.push(new PermissionsData());
    }

    public var context:String;
}
}