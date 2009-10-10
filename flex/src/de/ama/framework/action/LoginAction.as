package de.ama.framework.action {
import de.ama.framework.util.Util;

public class LoginAction extends ActionScriptAction{
    public var _user:String;
    public var _pwd:String;


    public function get success():Boolean {
        return !Util.isEmpty(userSessionId);
    }


}
}