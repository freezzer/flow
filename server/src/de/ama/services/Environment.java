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
    private static Map beanDictionary = new HashMap();
    private static Map singletons = new HashMap();

    /**
     * Common Umgebung "hochfahren"
     */
    public static void initCommon(){

        PreMainInitializer.initForServer();
        
        singletons.put(UserService.NAME         ,getBean("de.ama.services.impl.UserServiceImpl"));
        singletons.put(ActionService.NAME       ,getBean("de.ama.services.impl.ActionServiceImpl"));
        singletons.put(MailService.NAME         ,getBean("de.ama.services.impl.MailServiceImpl"));
        singletons.put(GoogleService.NAME         ,getBean("de.ama.services.impl.GoogleServiceImpl"));
    }
    /**
     * Produktionsumgebung "hochfahren"
     */
    public static void initProduction(){
        if(!beanDictionary.isEmpty()) return ;
        singletons.put(PersistentService.NAME   ,getBean("de.ama.services.impl.PersistentServiceImpl"));

        initCommon();
    }

    /**
     * Testumgebung "hochfahren"
     * Hier k�nnen andere Services instantiiert werden die f�r Tests geeigneter sind (Mocks, etc)
     * In der TEstclasse muss eben initTest anstatt initProduction()  gerufen werden.
     */
    public static void initTest(){
        if(!singletons.isEmpty()) return ;
        singletons.put(PersistentService.NAME   ,getBean("de.ama.services.impl.PersistentServiceTestImpl"));

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

    public static UserService getUserService(){
        return (UserService) getSingleton(UserService.NAME);
    }

    public static ActionService getActionService() {
        return (ActionService) getSingleton(ActionService.NAME);
    }

    public static MailService getMailService() {
        return (MailService) getSingleton(MailService.NAME);
    }

    public static PictureService getPictureService() {
        return (PictureService) getSingleton(PictureService.NAME);
    }

    public static GoogleService getGoogleService() {
        return (GoogleService) getSingleton(GoogleService.NAME);
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
        String className = (String) beanDictionary.get(name);

        if(className==null && !name.contains(".")){
            className="server.beans."+Util.firstCharToUpper(name);
        }

        if(className==null ){
            className=name;
        }

        Class ret = null;
        try {
            ret = Util.createClass(className);
        } catch (Exception e) {
            throw new IllegalArgumentException("can not create bean-class for name ["+ name+"]",e);
        }
        if(ret==null){
            throw new IllegalArgumentException("can not create bean-class for name ["+ name+"]");
        }
        return ret;
    }


}
