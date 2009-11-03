package de.ama.services.permission;

import de.ama.services.PermissionService;
import de.ama.services.Environment;
import de.ama.db.Query;
import de.ama.util.Util;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

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
        PermissionContext ret = (PermissionContext) Environment.getPersistentService().getObject(q_context.and(q_userId),true);

        if(ret!=null){
           Environment.getPersistentService().releaseObject(ret);
           return ret;
        }

        return (PermissionContext) Util.createObject(permissionContexts.get(context));
    }

    public List<PermissionContext> getUserPermissionContexts(String userId) {
        return Environment.getPersistentService().getObjects(new Query(PermissionContext.class, "userId", Query.EQ, userId));
    }

    public List<String> getAllPermissionContextKeys() {
        return new ArrayList<String>(permissionContexts.keySet());
    }

    public void registerPermissionContext(String context, Class  c ){
        permissionContexts.put(context,c);
    }


}
