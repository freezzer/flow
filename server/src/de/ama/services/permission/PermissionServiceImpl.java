package de.ama.services.permission;

import de.ama.framework.data.Condition;
import de.ama.framework.data.Query;
import de.ama.services.Environment;
import de.ama.services.PermissionService;
import de.ama.services.user.User;
import de.ama.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: x
 * Date: 01.11.2009
 * Time: 21:49:27
 * To change this template use File | Settings | File Templates.
 */
public class PermissionServiceImpl implements PermissionService{
    private Map<String,Class> permissionContexts = new HashMap<String,Class>();


    public PermissionContext getPermissionContext(User user, String context) {
        Condition q_context = new Condition("context", de.ama.db.Query.EQ, context);
        Condition q_userId = new Condition("userId", de.ama.db.Query.EQ, user.getId());
        PermissionContext ret = (PermissionContext) Environment.getPersistentService().
                getObject(new Query(PermissionContext.class,q_context.and(q_userId)),false);

        if(ret!=null){
           Environment.getPersistentService().releaseObject(ret);
           return ret;
        }

        ret = (PermissionContext) Util.createObject(permissionContexts.get(context));
        ret.onAfterLoad();
        ret.setUserId(user.getId());
        ret.setUserName(user.getName());
        return ret;
    }

    public List<PermissionContext> getUserPermissionContexts(User user) {
        List<PermissionContext> ret = new ArrayList<PermissionContext>();
        List<String> allPermissionContextKeys = getAllPermissionContextKeys();
        for (int i = 0; i < allPermissionContextKeys.size(); i++) {
            String key = allPermissionContextKeys.get(i);
            ret.add(getPermissionContext(user , key));
        }
        return ret;
    }

    public List<String> getAllPermissionContextKeys() {
        return new ArrayList<String>(permissionContexts.keySet());
    }

    public void registerPermissionContext(String context, Class  c ){
        permissionContexts.put(context,c);
    }


}
