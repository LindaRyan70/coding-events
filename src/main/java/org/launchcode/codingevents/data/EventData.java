
/*NOTE: Chapter 17.3 -  CODE TURNED OFF INSTEAD OF DELETING TO HAVE AS REFERENCE SHOULD I NEED IT.
        This file is no longer being used after connecting to the MySQL relational database via EventRepository,
        b/c I turned OFF all code related to Edit Forms from the Chptr 14.5 Exercise (createEditForm() & processEditForm())
        methods in EventController until/unless I figure out how to refactor those controller methods to connect to
        the EventRepository ORM database instead of this EventData data layer. So, no longer utilizing this or the edit.html template. */


// package org.launchcode.codingevents.data;
//
///*  unique class that just contains static properties and methods to have a single point of operation
// for how our Event objects are stored. */
//
//import org.launchcode.codingevents.models.Event;
//
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//
//public class EventData {
//
//    // Created this EventData as a separate 'Data Layer' b/c need a place to put events (a data structure/database of some type)
//    private static final Map<Integer, Event> events = new HashMap<>();
//
//    //  get all events
//    public static Collection<Event> getAll() {
//        return events.values();
//    }
//
//    // get a single event
//    public static Event getById(int id) {
//        return events.get(id);
//    }
//
//    // add an event
//    public static void add(Event event) {
//        events.put(event.getId(), event);
//    }
//
//    // remove an event
//    public static void remove(int id) {
//        events.remove(id);
//    }
//
//}