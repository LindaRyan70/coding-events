package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String renderCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        return "events/create";
    }

    // Handles the form submission data.
    // Lives at /events/create
    @PostMapping("create")
    public String createEvent(@RequestParam String eventName, @RequestParam String eventDescription) { // variable MUST match form input name/value in html file

        //  Refactor line below to add a single event into its internal collection on the EventData object.
        //  events.add(new Event(eventName, eventDescription));
        EventData.add(new Event(eventName, eventDescription));
        return "redirect:";  // redirects by default to root path for this controller (same as 'redirect:/events')
    }

}
