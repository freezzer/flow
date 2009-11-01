package de.ama.framework.action;

import de.ama.services.Environment;
import de.ama.services.user.UserData;


public class NewUserAction extends ActionScriptAction {
    public static int TYPE_UPDATE = 1;
	public static int TYPE_GET    = 2;
	public static int TYPE_NEW    = 3;

    public String name;
    public String pwd;
    public String email;
    public String imageUrl;
    public int type;

    public void execute() {

        if (type==TYPE_UPDATE) {
            data = Environment.getUserService().updateUser(userSessionId, name, pwd, email);
        } else if(type==TYPE_NEW){
            data = Environment.getUserService().newUser(name, pwd, email);
        } else {
            UserData ud = new UserData();
            mapBoToData(getUser(),ud);
            data = ud;
            rollback();
        }

        commit();
    }

 
}
