package de.ama.framework.action {
import de.ama.framework.util.Util;
import de.ama.services.user.UserData;

[RemoteClass(alias="de.ama.framework.action.LoginAction")]
public class LoginAction extends ActionScriptAction{
    public var _userData:UserData = new UserData();
    public var _user:String;
    public var _pwd:String;
    public var permissionContexts:Array;

    public function LoginAction() {
     needsLogin = false;
 }

    public function get success():Boolean {
        return !Util.isEmpty(userSessionId);
    }

}
}