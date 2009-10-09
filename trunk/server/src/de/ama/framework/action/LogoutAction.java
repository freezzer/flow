package de.ama.framework.action;

import de.ama.services.Environment;


public class LogoutAction extends ActionScriptAction {

    public boolean success;


    public void execute() {
        Environment.getUserService().logout(userSessionId);
        success = true;
    }

}

