package de.ama.framework.util {
import de.ama.framework.action.ActionStarter;
import de.ama.framework.action.LoginAction;
import de.ama.framework.action.LogoutAction;
import de.ama.services.impl.UserData;

import flash.net.SharedObject;

import mx.core.Application;
import mx.core.Container;
import mx.managers.PopUpManager;
import mx.utils.URLUtil;

public class Environment {

    private static var _hostAdress:String;
    private static var _hostPort:int;
    private static var _hostContext:String;
    private static var _catalog:String;


    public static function initForProduction():void{
        _hostAdress     = "localhost";
        _hostPort       = 0;
        _hostContext    = "huyua";
        _catalog        = "huyua_production";
    }


    public static function getServerUrl():String {
        var port:String = _hostPort>0 ? ":"+_hostPort : "" ;
        return "http://"+_hostAdress+port+"/"+_hostContext;
    }

    public static function get hostContext():String {
        return _hostContext;
    }

    public static function get hostAdress():String {
        return hostAdress;
    }

    public static function get hostPort():String {
        return hostPort;
    }

    public static function getUserSessionId():String {
        var so:SharedObject = SharedObject.getLocal("userdata");
        return so.data.userSessionId;
    }

    public static function getUserId():String {
        var so:SharedObject = SharedObject.getLocal("userdata");
        return so.data.userId;
    }

    public static function getUserName():String {
        var so:SharedObject = SharedObject.getLocal("userdata");
        return so.data.userName;
    }

    public static function getUserEmail():String {
        var so:SharedObject = SharedObject.getLocal("userdata");
        return so.data.userEmail;
    }


    private static function setUserData(d:UserData, us:String):void {
        var so:SharedObject = SharedObject.getLocal("userdata");
        so.data.userSessionId = us;
        so.data.userId = d.id;
        so.data.userName = d.name;
        so.data.userEmail = d.email;
        so.flush();
    }

    public static function isLoggedIn():Boolean {
        return !Util.isEmpty(getUserId());
    }

    public static  function login(allways:Boolean = false):void {
        if(!isLoggedIn() || allways){
	        var dlg:LoginDialog = LoginDialog(PopUpManager.createPopUp(Container(Application.application), LoginDialog, true));
	        dlg.x = (Application.application.width / 2 - (dlg.width / 2));
	        dlg.y = (Application.application.height / 2 - (dlg.height / 2));
        }
    }

    public static function registerLoginData(la:LoginAction):void{
        setUserData(UserData(la.data), la.userSessionId);
    }


    public static function logout():void {
        var la:LogoutAction = new LogoutAction();
        ActionStarter.instance.execute(la);
        eraseLoginData();
        Util.showMessage("Vielen Dank für die Teilnahme bei huyua. \n" + 
        		         "Bitte erzählen Sie Ihren Freunden von huyua.");
    }

    public static function registerHostAdress():void{
       _hostAdress  = URLUtil.getServerName(Application.application.loaderInfo.url);
       _hostPort    = URLUtil.getPort(Application.application.loaderInfo.url);
    }

    public static function eraseLoginData():void{
        setUserData(new UserData(), "");
    }


    public static function useHessianProtocoll():Boolean {
        return true;
    }

    public static function get catalog():String {
        return _catalog;
    }


}
}