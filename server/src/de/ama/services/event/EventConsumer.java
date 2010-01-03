package de.ama.services.event;

/**
 * Created by IntelliJ IDEA.
 * User: x
 * Date: 03.01.2010
 * Time: 20:56:55
 * To change this template use File | Settings | File Templates.
 */
public abstract class EventConsumer {
    public String name;

    public void consume(Event e){

    }

    public boolean isResponsible(Event e) {
        return false;  //To change body of created methods use File | Settings | File Templates.
    }
}
