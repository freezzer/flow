package de.ama.services.permission {
import de.ama.framework.data.BusinessObject;

[RemoteClass(alias="de.ama.services.permission.PermissionSwitch")]
public class PermissionSwitch extends BusinessObject{
    public var key :String ;
    public var isOn:Boolean;
}}