package org.launchcode.codingevents.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {

    @GetMapping
    public String displayAllEvents(Model model) {
        List<String> theEvents = new ArrayList<>();
        theEvents.add("Code With Pride");
        theEvents.add("Strange Loop");
        theEvents.add("Apple WWOC");
        theEvents.add("SpringOne Platform");
        model.addAttribute("events", theEvents);
        return "events/index";
    }

    // Lives at /events/create
    @GetMapping("create")
    public String renderCreateEventForm() {
        return "events/create";
    }

}
