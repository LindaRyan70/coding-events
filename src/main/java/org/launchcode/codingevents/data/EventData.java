package org.launchcode.codingevents.data;

/*  unique class that just contains static properties and methods to have a single point of operation
 for how our Event objects are stored. */

import org.launchcode.codingevents.models.Event;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EventData {

    //need a place to put events (a data structure of some type)
    private static final Map<Integer, Event> events = new HashMap<>();

    //  get all events
    public static Collection<Event> getAll() {
        return events.values();
    }

    // get a single event
    public static Event getById(int id) {
        return events.get(id);
    }

    // add an event
    public static void add(Event event) {
        events.put(event.getId(), event);
    }

    // remove an event
    public static void remove(int id) {
        events.remove(id);
    }

}
