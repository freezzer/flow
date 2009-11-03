package de.ama.services;

import de.ama.services.permission.PermissionContext;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: x
 * Date: 01.11.2009
 * Time: 21:48:25
 * To change this template use File | Settings | File Templates.
 */
public interface PermissionService {
    public static String NAME = "PermissionService";

    public PermissionContext getPermissionContext(String userId, String context);
    public List<PermissionContext> getUserPermissionContexts(String userId);
    public List<String> getAllPermissionContextKeys();
    public void registerPermissionContext(String context, Class  c );
    
}
