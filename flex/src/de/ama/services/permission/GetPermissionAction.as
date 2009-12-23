package de.ama.services.permission {
import de.ama.framework.action.ActionScriptAction;
import de.ama.framework.data.BoReference;

[RemoteClass(alias="de.ama.services.permission.GetPermissionAction")]
public class GetPermissionAction extends ActionScriptAction{


    public function GetPermissionAction() {
        var boReference:BoReference = new BoReference();
        boReference.type = "Notiz"
        boReference.oid = 100;
        registry.push(boReference);
    }

    public var context:String;
}
}