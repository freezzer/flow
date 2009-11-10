package de.ama.services.permission;

import de.ama.framework.action.ActionScriptAction;
import de.ama.services.Environment;

/**
 * Created by IntelliJ IDEA.
 * User: x
 * Date: 04.11.2009
 * Time: 23:09:15
 * To change this template use File | Settings | File Templates.
 */
public class GetPermissionAction extends ActionScriptAction {

    public String context;

    @Override
    public void execute() throws Exception {
        if("keys".equals(context)){
            data = Environment.getPermissionService().getAllPermissionContextKeys();
        } else

        if("all".equals(context)){
            data = Environment.getPermissionService().getUserPermissionContexts(getUser().getId());
        }

        else {
            data = Environment.getPermissionService().getPermissionContext(getUser().getId(), context);
        }

        if(data!=null){
            Environment.getPersistentService().releaseObject(data);
        }
    }
}
