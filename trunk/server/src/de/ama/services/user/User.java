package de.ama.services.user;

import de.ama.db.PersistentMarker;
import de.ama.services.Environment;

/**
 * Created by IntelliJ IDEA.
 * User: x
 * Date: 28.02.2009
 * Time: 14:53:26
 * To change this template use File | Settings | File Templates.
 */
public class User implements PersistentMarker {
    private String name;
    private String pwd;
    private String email;

    public User(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    public String getId() {
        return Environment.getPersistentService().getOidString(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}