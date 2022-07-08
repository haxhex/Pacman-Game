package ir.ac.kntu.engine.core;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Manage programmable events
 */
public class EventManager {
    private static EventManager eventManager;
    private final List<Event> events;

    private EventManager(){
        events = new ArrayList<>();
    }

    public static EventManager getEventManager(){
        if(eventManager == null){
            eventManager = new EventManager();
        }
        return eventManager;
    }

    /**
     * addition of a programmable event in the manager's list
     * @param e added event
     */
    public void addEvent(Event e){
        events.add(e);
    }

    /**
     * call of events
     * destruction of expired vents
     */
    public void manage() throws FileNotFoundException {
        List<Event> toRemove = new ArrayList<>();
        for(Event event : events){
            if(event.getTime() > 0)
                event.update();
            else
                toRemove.add(event);
        }
        for(Event event : toRemove){
            event.handle();
            events.remove(event);
        }
    }
}
