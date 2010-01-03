package de.ama.services.event;

import de.ama.services.EventService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: x
 * Date: 03.01.2010
 * Time: 20:52:53
 * To change this template use File | Settings | File Templates.
 */
public class EventServiceImpl implements EventService {

    private List consumerList = new ArrayList();

    public void emit(Event e){
        for (int i = 0; i < consumerList.size(); i++) {
            EventConsumer consumer = (EventConsumer) consumerList.get(i);
            if(consumer.isResponsible(e)){
               consumer.consume(e);
            }
        }
    }
    
    public void registerConsumer(EventConsumer consumer){
        consumerList.add(consumer);
    }
}
