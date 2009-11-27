package de.ama.services.user {
import de.ama.framework.data.BusinessObject;
[RemoteClass(alias="de.ama.services.user.UserData")]
public class UserData extends BusinessObject{

   public var name:String = "";
   public var pwd:String = "";
   public var email:String = "";
   public var id:String = "";
   public var oidString:String = "";
}
}