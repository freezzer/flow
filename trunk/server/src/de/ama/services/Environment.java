package de.ama.services;

import de.ama.framework.util.PreMainInitializer;
import de.ama.util.Util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * User: x
 * Date: 19.05.2008
 */
public class Environment {
    private static Map<String,Class> beanDictionary = new HashMap<String,Class>();
    private static Map<String,Object> singletons = new HashMap<String,Object>();

    /**
     * Common Umgebung "hochfahren"
     */
    public static void initCommon(){

        singletons.put(UserService.NAME         , Util.createObject("de.ama.services.user.UserServiceImpl"));
        singletons.put(PermissionService.NAME  , Util.createObject("de.ama.services.permission.PermissionServiceImpl"));
        singletons.put(ActionService.NAME       ,Util.createObject("de.ama.services.impl.ActionServiceImpl"));
        singletons.put(MailService.NAME         ,Util.createObject("de.ama.services.impl.MailServiceImpl"));
        singletons.put(GoogleService.NAME         ,Util.createObject("de.ama.services.impl.GoogleServiceImpl"));
        singletons.put(EventService.NAME         ,Util.createObject("de.ama.services.event.EventServiceImpl"));
        singletons.put(TextService.NAME         ,Util.createObject("de.ama.services.text.TextServiceImpl"));

        PreMainInitializer.initForServer();
    }
    /**
     * Produktionsumgebung "hochfahren"
     */
    public static void initProduction(){
        if(!beanDictionary.isEmpty()) return ;
        singletons.put(PersistentService.NAME   ,Util.createObject("de.ama.services.impl.PersistentServiceImpl"));

        initCommon();
    }

    /**
     * Testumgebung "hochfahren"
     * Hier können andere Services instantiiert werden die für Tests geeigneter sind (Mocks, etc)
     * In der TEstclasse muss eben initTest anstatt initProduction()  gerufen werden.
     */
    public static void initTest(){
        if(!singletons.isEmpty()) return ;
        singletons.put(PersistentService.NAME   ,Util.createObject("de.ama.services.impl.PersistentServiceTestImpl"));

        initCommon();
    }

    public static File getHomeDir(){
        return de.ama.util.Environment.getHomeDir();
    }

    public static File getHomeRelativDir(String path){
        return de.ama.util.Environment.getHomeRelativDir(path);
    }

    public static PersistentService getPersistentService(){
        return (PersistentService) getSingleton(PersistentService.NAME);
    }

    public static EventService getEventService(){
        return (EventService) getSingleton(EventService.NAME);
    }

    public static UserService getUserService(){
        return (UserService) getSingleton(UserService.NAME);
    }

    public static ActionService getActionService() {
        return (ActionService) getSingleton(ActionService.NAME);
    }

    public static MailService getMailService() {
        return (MailService) getSingleton(MailService.NAME);
    }

    public static TextService getTextService() {
        return (TextService) getSingleton(TextService.NAME);
    }

    public static PictureService getPictureService() {
        return (PictureService) getSingleton(PictureService.NAME);
    }

    public static GoogleService getGoogleService() {
        return (GoogleService) getSingleton(GoogleService.NAME);
    }

    public static PermissionService getPermissionService() {
        return (PermissionService) getSingleton(PermissionService.NAME);
    }

    public static Object getSingleton(String name) {
        return singletons.get(name);
    }

    public static Object getBean(String name) {
        Object singleton = singletons.get(name);
        if(singleton != null){
           return singleton;
        }

        Object ret = null;
        try {
            Class c = getBeanClass(name);
            ret = Util.createObject(c);
        } catch (Exception e) {
            throw new IllegalArgumentException("can not create bean for name ["+ name+"]",e);
        }
        if(ret==null){
            throw new IllegalArgumentException("can not create bean for name ["+ name+"]");
        }
        return ret;
    }

    public static Class getBeanClass(String name) {
        Class c =  beanDictionary.get(name);
        if(c==null){
            throw new IllegalArgumentException("bean not registered for name ["+ name+"]");
        }
        return c;
    }


    public static void registerBean(String name, Class c ){
        beanDictionary.put(name,c);
    }

}
