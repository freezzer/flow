package de.ama.services.event;

/**
 * Created by IntelliJ IDEA.
 * User: x
 * Date: 03.01.2010
 * Time: 20:56:55
 * To change this template use File | Settings | File Templates.
 */
public abstract class EventConsumer {
    public String events;


    protected EventConsumer(String events) {
        this.events = events;
    }

    public void consume(Event e){
        System.out.println("Event consume "+e);
    }

    public boolean isResponsible(Event e) {
        return events.indexOf(e.name)>=0 ;
    }
}
