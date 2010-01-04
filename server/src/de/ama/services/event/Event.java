package de.ama.services.event;

import de.ama.util.Util;

/**
 * Created by IntelliJ IDEA.
 * User: x
 * Date: 03.01.2010
 * Time: 20:58:10
 * To change this template use File | Settings | File Templates.
 */
public class Event {
    public String name;
    public Object data;

    public Event(String name, Object data) {
        this.name = name;
        this.data = data;
    }

    @Override
    public String toString() {
        return name;
    }
}
