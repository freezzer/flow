
package server.services.impl;

/**
 * User: x
 * Date: 16.05.2008
 */

import server.services.ActionService;
import server.services.Environment;
import server.actions.ServerAction;
import server.actions.ActionScriptAction;
import de.ama.util.Util;


public class ActionServiceImpl implements ActionService {


    private ServerAction createAction(String name) {
        return (ServerAction) Environment.getBean("server.actions."+name);
    }

   public ActionScriptAction execute(ActionScriptAction asa) {
        System.out.println("ActionServiceImpl.remote_execute " +asa.serverActionName);

        ServerAction a = createAction(asa.getServerActionName());
        if (a == null) {
            throw new RuntimeException("cant find ServerAction for [" + asa.serverActionName + "]");
        }

        ServerAction.setCurrent(a);

        if(a.needsUser()) {
           a.setUser(Environment.getUserService().getActiveUser(asa.userId));
        }

        try {
            Environment.getPersistentService().join(asa.getCatalog());
            a.setData(asa.data);
            a.execute() ;
            asa.data = a.getData();

            System.out.println("ActionServiceImpl.execute OK " +asa.serverActionName);
            return asa;

        } catch (Exception e) {
            e.printStackTrace();
            asa.message=e.getMessage();
            asa.detailErrorMessage = Util.getAllExceptionInfos(e);
        } finally {
            Environment.getPersistentService().rollback();
            Environment.getPersistentService().leave();
            ServerAction.setCurrent(null);
        }

       return asa;
    }

}