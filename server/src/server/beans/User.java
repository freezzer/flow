package server.beans;

import de.ama.db.PersistentMarker;

/**
 * Created by IntelliJ IDEA.
 * User: ama
 * Date: 21.08.2008
 * Time: 18:10:20
 * To change this template use File | Settings | File Templates.
 */
public class User implements PersistentMarker {
    private String name;
    private String pwd;
    private String id;


    public User(String name, String pwd, String id) {
        this.name = name;
        this.pwd = pwd;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPwd() {
        return pwd;
    }

    public String getId() {
        return id;
    }

}
