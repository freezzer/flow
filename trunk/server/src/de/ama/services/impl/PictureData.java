package de.ama.services.impl;

import de.ama.services.PersistentService;
import de.ama.db.PersistentMarker;
import de.ama.framework.data.Data;

/**
 * Created by IntelliJ IDEA.
 * User: x
 * Date: 09.10.2009
 * Time: 14:05:55
 * To change this template use File | Settings | File Templates.
 */
public class PictureData extends Data {

    public User owner;
    public String description;
    public String fileName;
    public String thumbName;

}