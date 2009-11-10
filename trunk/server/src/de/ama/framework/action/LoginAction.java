package de.ama.framework.action;

import de.ama.services.Environment;
import de.ama.services.permission.PermissionContext;
import de.ama.services.user.User;
import de.ama.services.user.UserData;
import de.ama.util.Util;

import java.util.List;


public class LoginAction extends ActionScriptAction {

    public String _user;
    public String _pwd;
    public List<PermissionContext> permissionContexts;


    public void execute() {
        userSessionId = Environment.getUserService().login(_user, _pwd);
        if(!Util.isEmpty(userSessionId)){
            User u = Environment.getUserService().getActiveUser(userSessionId);
            UserData ud = new UserData();
            mapBoToData(u,ud);
            data = ud;

            permissionContexts = Environment.getPermissionService().getUserPermissionContexts(u);

        }
    }

}
