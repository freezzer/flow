package server.actions;

import de.ama.util.Util;

/**
 * Created by IntelliJ IDEA.
 * User: ama
 * Date: 31.01.2009
 * Time: 18:20:18
 * To change this template use File | Settings | File Templates.
 */
public class ActionScriptAction {

    public String serverActionName;
    public String userId;
    public String catalog;

    public Object data;
    public String message;
    public String detailErrorMessage;



    


    public String getServerActionName() {
        return Util.saveToString(serverActionName,"server.actions.ServerAction");
    }

    public String getCatalog() {
        return Util.saveToString(catalog,"gap");
    }
}
