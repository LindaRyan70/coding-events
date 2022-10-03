package org.launchcode.codingevents.controllers;

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

    // Create new static field to hold/handle form data submitted. Use in createEvent() below.
    private static List<String> events = new ArrayList<>();

    @GetMapping
    public String displayAllEvents(Model model) {

        // Removed the set/unchanging list of data and refactor to allow for user inputted form data.
//        List<String> theEvents = new ArrayList<>();    // List refers to the interface level that contains ArrayList.
//        theEvents.add("Code With Pride");
//        theEvents.add("Strange Loop");
//        theEvents.add("Apple WWDC");
//        theEvents.add("SpringOne Platform");
//        model.addAttribute("events", theEvents);

        model.addAttribute("events", events);
        return "events/index";
    }

    // Lives at /events/create
    @GetMapping("create")
    public String renderCreateEventForm() {
        return "events/create";
    }

    // Handles the form submission data.
    // Lives at /events/create
    @PostMapping("create")
    public String createEvent(@RequestParam String eventName) { // variable MUST match form input name/value in html file
        events.add(eventName);
        return "redirect:";  // redirects by default to root path for this controller (same as 'redirect:/events')
    }

}
