package de.ama.services.impl;

/**
 * User: x
 * Date: 16.05.2008
 */

//import com.caucho.hessian.server.HessianServlet;
import de.ama.framework.action.ActionScriptAction;
import de.ama.services.ActionService;
import de.ama.services.Environment;
import de.ama.util.Util;


public class ActionServiceImpl /*extends HessianServlet*/ implements ActionService {


    public ActionScriptAction execute(ActionScriptAction a) {
        System.out.println("ActionServiceImpl.remote_execute " + a.getName());

        try {
            Environment.getPersistentService().join(a.getCatalog());
            ActionScriptAction.setCurrent(a);

            if (a.needsLogin) {
                if(Environment.getUserService().checkSession(a.userSessionId)){
                    a.setUser(Environment.getUserService().getActiveUser(a.userSessionId));
                } else {
                    System.out.println(" login missing !!!");
                    a.setMessage("bitte erneut anmelden");
                    a.userSessionId="dead";
                    return a;
                }
            }

            a.execute();

            System.out.println("ActionServiceImpl.execute OK " + a.getName());
            return a;

        } catch (Exception e) {
            e.printStackTrace();
            a.detailErrorMessage = Util.getAllExceptionInfos(e);
            return a;
        } finally {
            Environment.getPersistentService().rollback();
            Environment.getPersistentService().leave();
            ActionScriptAction.setCurrent(null);
        }

    }

}