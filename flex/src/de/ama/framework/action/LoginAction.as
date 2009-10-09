package de.ama.framework.action {
import de.ama.framework.gui.*;
import de.ama.framework.util.Environment;
import de.ama.framework.util.Util;

[RemoteClass(alias="de.ama.framework.action.LoginAction")]
public class LoginAction extends ActionScriptAction{
    public var _user:String;
    public var _pwd:String;


    public function get success():Boolean {
        return !Util.isEmpty(userSessionId);
    }


}
}