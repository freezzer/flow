package de.ama.services.permission;

import de.ama.db.Query;
import de.ama.services.Environment;
import de.ama.services.PermissionService;
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


    public PermissionContext getPermissionContext(String userId, String context) {
        Query q_context = new Query(PermissionContext.class, "context", Query.EQ, context);
        Query q_userId = new Query(PermissionContext.class, "userId", Query.EQ, userId);
        PermissionContext ret = (PermissionContext) Environment.getPersistentService().getObject(q_context.and(q_userId),false);

        if(ret!=null){
           Environment.getPersistentService().releaseObject(ret);
           return ret;
        }

        ret = (PermissionContext) Util.createObject(permissionContexts.get(context));
        ret.onAfterLoad();
        return ret;
    }

    public List<PermissionContext> getUserPermissionContexts(String userId) {
        List<PermissionContext> ret = new ArrayList<PermissionContext>();
        List<String> allPermissionContextKeys = getAllPermissionContextKeys();
        for (int i = 0; i < allPermissionContextKeys.size(); i++) {
            String key = allPermissionContextKeys.get(i);
            ret.add(getPermissionContext(userId,key));
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
