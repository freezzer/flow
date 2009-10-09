package de.ama.services.impl;

import de.ama.db.Query;
import de.ama.services.Environment;
import de.ama.services.UserService;
import de.ama.util.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserServiceImpl implements UserService {
    Map<String , UserSession> activeUsers = new HashMap<String , UserSession>() ;

    public String newUser(String name, String pwd, String email) {

        if(Util.isEmpty(name)){
            throw new IllegalArgumentException("name required") ;
        }
        if(Util.isEmpty(pwd)){
            throw new IllegalArgumentException("password required") ;
        }

        long count = Environment.getPersistentService().getObjectCount(new Query(User.class, "name", Query.EQ, name));

        if (count == 0) {
            User user = new User(name, pwd);
            user.setEmail(email);
            Environment.getPersistentService().makePersistent(user);
            return "ok";
        } else {
            return "failed";
        }
    }

    public String updateUser(String userSessionId, String name, String pwd, String email) {

        if(Util.isEmpty(name)){
            throw new IllegalArgumentException("name required") ;
        }
        if(Util.isEmpty(pwd)){
            throw new IllegalArgumentException("password required") ;
        }

        User user = getActiveUser(userSessionId);

        if(user==null){
            throw new IllegalArgumentException("you need to be logged in") ;
        }

        user = (User) Environment.getPersistentService().getObject(user.getId());

        if (!Util.saveToString(user.getName()).equals(name)) {
            long count = Environment.getPersistentService().getObjectCount(new Query(User.class, "name", Query.EQ, name));
            if(count>0){
                return "failed";
            }

            user.setName(name);
        }

        if (!Util.saveToString(user.getPwd()).equals(pwd)) {
            user.setPwd(pwd);
        }

        if (!Util.saveToString(user.getEmail()).equals(email)) {
            user.setEmail(email);
        }


        UserSession us = activeUsers.get(userSessionId);
        us.setUser(user);

        return "ok";

    }

    public void removeUser(User user) {
        Environment.getPersistentService().delete(Environment.getPersistentService().getOidString(user));
    }

    public boolean checkSession(String id){
        UserSession us = activeUsers.get(id);

        if(us==null) { return false; }

        if(us.isTimedOut()) {
            activeUsers.remove(id);
            return false;
        }

        return true;
    }

    public User getActiveUser(String id) {
        UserSession us = activeUsers.get(id);
        if(us==null) {
            throw new RuntimeException("no active user / session dead") ;
        }
        return us.getUser();
    }

    public void logout(String userid){
        activeUsers.remove(userid);
    }

    public String login(String name, String pwd) {

        if(Util.isEmpty(name)){
            throw new IllegalArgumentException("name required") ;
        }
        if(Util.isEmpty(pwd)){
            throw new IllegalArgumentException("password required") ;
        }

        List users = Environment.getPersistentService().getObjects(new Query(User.class, "name", Query.EQ, name).
                                                                    and(new Query(User.class, "pwd", Query.EQ, pwd)));
        if (users.size() == 0) {
            return null;
        }

        User user = (User) users.get(0);

        UserSession userSession = new UserSession(user);
        activeUsers.put(userSession.getId() , userSession);

        return userSession.getId();
    }
}
