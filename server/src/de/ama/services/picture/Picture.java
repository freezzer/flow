package de.ama.services.picture;

import de.ama.db.PersistentMarker;
import de.ama.services.user.User;

/**
 * Created by IntelliJ IDEA.
 * User: x
 * Date: 09.10.2009
 * Time: 14:05:55
 * To change this template use File | Settings | File Templates.
 */
public class Picture implements PersistentMarker {

    private User owner;
    private String description;
    private String fileName;
    private String thumbName;

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setThumbName(String thumbName) {
        this.thumbName = thumbName;
    }

    public String getFileName() {
        return fileName;
    }

    public String getThumbName() {
        return thumbName;
    }
}
