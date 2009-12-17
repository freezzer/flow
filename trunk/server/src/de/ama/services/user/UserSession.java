package de.ama.services.user;

/**
 * Created by IntelliJ IDEA.
 * User: x
 * Date: 09.07.2009
 * Time: 14:06:11
 * To change this template use File | Settings | File Templates.
 */
public class UserSession {
    private static long minute = 1000*60;
    private String id;
    private User user;
    private long timeStamp;

    public UserSession(User user) {
        this.user=user;
        timeStamp = System.currentTimeMillis();
        this.id = user.getId() + timeStamp;
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public boolean isTimedOut() {
        long gap = System.currentTimeMillis() - timeStamp;

        if(gap < (30*minute)){
            timeStamp = System.currentTimeMillis();
            return false;
        }

        return true;
    }

    void setUser(User user) {
        this.user = user;
    }
}
