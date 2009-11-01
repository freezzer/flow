package de.ama.services.permission;

import de.ama.services.PermissionService;
import de.ama.services.Environment;
import de.ama.db.Query;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: x
 * Date: 01.11.2009
 * Time: 21:49:27
 * To change this template use File | Settings | File Templates.
 */
public class PermissionServiceImpl implements PermissionService{

    public PermissionContext getPermissionContext(String userId, String context) {
        Query q_context = new Query(PermissionContext.class, "context", Query.EQ, context);
        Query q_userId = new Query(PermissionContext.class, "userId", Query.EQ, userId);
        return (PermissionContext) Environment.getPersistentService().getObject(q_context.and(q_userId),true);
    }

    public List<PermissionContext> getPermissionContexts(String userId) {
        return Environment.getPersistentService().getObjects(new Query(PermissionContext.class, "userId", Query.EQ, userId));
    }

}
