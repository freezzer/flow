package de.ama.framework.action;

import de.ama.services.Environment;
import de.ama.services.impl.User;
import de.ama.services.impl.UserData;
import de.ama.util.Util;


public class LoginAction extends ActionScriptAction {

    public String _user;
    public String _pwd;


    public void execute() {
        userSessionId = Environment.getUserService().login(_user, _pwd);
        if(!Util.isEmpty(userSessionId)){
            User u = Environment.getUserService().getActiveUser(userSessionId);
            UserData ud = new UserData();
            mapBoToData(u,ud);
            data = ud;
        }
    }

}
