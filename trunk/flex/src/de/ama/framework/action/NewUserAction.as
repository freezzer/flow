package de.ama.framework.action {
import de.ama.framework.util.Util;

[RemoteClass(alias="de.ama.framework.action.NewUserAction")]
public class NewUserAction extends ActionScriptAction{
	public static var TYPE_UPDATE:int = 1; 
	public static var TYPE_GET:int    = 2; 
	public static var TYPE_NEW:int    = 3; 
 
    public var name:String;
    public var pwd:String;
    public var email:String;
    public var imageUrl:String;
    public var type:int;


    public function NewUserAction() {
        needsLogin = type!=TYPE_NEW;
    }

}
}