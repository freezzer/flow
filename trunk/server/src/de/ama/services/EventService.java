package de.ama.services;

import de.ama.services.event.Event;
import de.ama.services.event.EventConsumer;

/**
 * Created by IntelliJ IDEA.
 * User: x
 * Date: 03.01.2010
 * Time: 21:52:03
 * To change this template use File | Settings | File Templates.
 */
public interface EventService {
    public static final String NAME = "EventService";

    public void emit(Event e);

    public void registerConsumer(EventConsumer consumer);

}