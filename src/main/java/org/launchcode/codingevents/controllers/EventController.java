package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {

    // Created new static field List<String> to hold/handle form data submitted. Use in createEvent() below.

//    private static List<String> events = new ArrayList<>();

/* No longer need this List<Event> data structure below b/c created a separate EventData class abstraction that
encapsulates all the data-related behavior for Events in a Map<Integer, Event>  structure). */

//    private static List<Event> events = new ArrayList<>();

    @GetMapping
    public String displayAllEvents(Model model) {
        model.addAttribute("title", "All Events");

        // Refactor line below to update model value to call the getAll() static method on the EventData object.
//        model.addAttribute("events", events);
        model.addAttribute("events", EventData.getAll());
        return "events/index";
    }

    // Lives at /events/create
    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        return "events/create";
    }

    // Handles the form submission data.
    // Lives at /events/create
    @PostMapping("create")
//    public String createEvent(@RequestParam String eventName, @RequestParam String eventDescription) { // variable MUST match form input name/value in html file
    public String createEvent(@ModelAttribute Event newEvent) { // variable MUST match form input name/value in html file

//      Original code line.
        //  events.add(new Event(eventName, eventDescription));

        //  Refactor line above per below to add a single event into its internal collection on the EventData object.
//        EventData.add(new Event(eventName, eventDescription));

        // Refactor line below after utilizing model-binding annotation in method param above.
        EventData.add(newEvent);
        return "redirect:";  // redirects by default to root path for this controller (same as 'redirect:/events')
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", EventData.getAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String deleteEvent(@RequestParam(required = false) int[] eventIds) {

        if (eventIds != null) {
            for (int id : eventIds) {
                EventData.remove(id);
            }
        }
        return "redirect: ";
    }

}
