package org.launchcode.codingevents.controllers;

//import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.models.Event;
// Chptr 18.2 - Many-to-One Relationship - Replaced EventType import below with EventCategory and EventCategoryRepository.
//import org.launchcode.codingevents.models.EventType;
import org.launchcode.codingevents.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("events")
public class EventController {

    // Created new static field List<String> to hold/handle form data submitted. Use in createEvent() below.

//    private static List<String> events = new ArrayList<>();

/* No longer need this List<Event> data structure below b/c created a separate EventData class abstraction that
encapsulates all the data-related behavior for Events in a Map<Integer, Event>  structure). */

//    private static List<Event> events = new ArrayList<>();

/*   17.3 Repositories: By adding @Repository to EventRepository interface, it allows an instance to be made/managed
          if/when called for by situations like @Autowired. Create field using @Autowired to hold instances of EvenRepository
          from our dynamic relational database INSTEAD of the static EventData file we had been using. */

    @Autowired  // Tells Springboot to look for EventRepository object to populate the eventRepository field (throws exception if not).
    private EventRepository eventRepository; // Create field to hold dynamic EventRepository instances from database (replaces static EventData).

    //  Chptr 18.2 - Many-to-One Relationship - Create an instance of EventCategoryRepository in our controller to replace EventType enums..
    @Autowired
    private EventCategoryRepository eventCategoryRepository;

/*  17.3 Refactor methods below to use eventRepository field to connect to database instead of static EventData file and
            to access EventRepository interface which implements CrudRepository interface methods:
            findAll(), save(), findById(), etc. */
/*  18.3 - Refactor method to remove All from method title and allow for submitting a search parameter as an
            option (not required). Then provide result options: IF user does not provide a categoryId parameter (null)
            ELSE if they provide an INVALID categoryId parameter, ELSE a VALID categoryId parameter. */
    @GetMapping
    public String displayEvents(@RequestParam(required = false) Integer categoryId, Model model) {
        if (categoryId == null) {
            model.addAttribute("title", "All Events");
        // Refactor line below to update model value to call the getAll() static method on the EventData object.
//        model.addAttribute("events", events);
//        model.addAttribute("events", EventData.getAll());
//      17.3 - Refactor line above to replace static EventData.getAll() with dynamic EventRepository field.
            model.addAttribute("events", eventRepository.findAll());
        } else {
           Optional<EventCategory> result = eventCategoryRepository.findById(categoryId);
           if (result.isEmpty()) {
               model.addAttribute("title", "Invalid Category Id: " + categoryId);
           } else {
                EventCategory category = result.get();
                model.addAttribute("title", "Events in category: " + category.getName());
                model.addAttribute("events", category.getEvents());
           }
        }
        return "events/index";
    }

    // Lives at /events/create
    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        model.addAttribute(new Event());    // same as saying:    model.addAttribute('event', new Event());
        // Chptr 16.2 - add a new attribute below that returns an Array of the 4 different enum type constant values.
//        model.addAttribute("types", EventType.values());
        // Chptr 18.2 - Many-to-One Relationship - Replace EventType above to use eventCategoryRepository instead.
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "events/create";
    }

    // Handles the form submission data.
    // Lives at /events/create
    @PostMapping("create")
//    public String processCreateEventForm(@RequestParam String eventName, @RequestParam String eventDescription) { // variable MUST match form input name/value in html file
//    public String processCreateEventForm(@ModelAttribute Event newEvent) { // Added model binding
// Added @Valid validation annotation to ensure rules on Model fields are followed.
// Also added Errors object
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
//            model.addAttribute("errorMsg", "Bad data!");  // No longer need this general error message since using custom messages from Event.
           // Chptr 16.2 - Enums - Line below will reload EventType enums in Model if page reloads due to validation error.
//            model.addAttribute("types", EventType.values());
            // Chptr 18.2 - Many-to-One Relationship - Replace EventType above to use eventCategoryRepository instead.
            model.addAttribute("categories", eventCategoryRepository.findAll());
            return "events/create";
        }
//      Original code line.
//        events.add(new Event(eventName, eventDescription));

//      Refactor line to add a single event into its internal collection on the EventData object.
//        EventData.add(new Event(eventName, eventDescription));

//      Refactor line after utilizing model-binding annotation in method param above.
//        EventData.add(newEvent);

//      17.3 - Refactor line above to replace static EventData with dynamic EventRepository ORM.
        eventRepository.save(newEvent);
        return "redirect:";  // redirects by default to root path for this controller (same as 'redirect:/events')
    }


    // Handles the page to select which items to delete with check boxes.
    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Events");
//        model.addAttribute("events", EventData.getAll());

//    17.3 - Refactor line above to replace static EventData with dynamic EventRepository
        model.addAttribute("events", eventRepository.findAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventForm(@RequestParam(required = false) int[] eventIds) {

        if (eventIds != null) {
            for (int id : eventIds) {
//                EventData.remove(id);

//  17.3 - Refactor line above to replace static EventData with dynamic EventRepository
                eventRepository.deleteById(id);
            }
        }
        return "redirect: ";
    }

/*  NOTE 17.3: Turned OFF 2 Edit Form Methods below b/c not sure how to refactor to replace static EventData with
              dynamic EventRepository. Though I WAS able to try and replace the EventType type enums with
              eventCategoryRepository and eventCategory respectively. But not sure if that will work and need to figure
              out Event eventToEdit = EventData.getById() lines.... So, keeping commented off for now.
              */

//    @GetMapping("edit/{eventId}")
//    public String displayEditForm(Model model, @PathVariable int eventId) {
//        // controller code will go here
//        Event eventToEdit = EventData.getById(eventId);
//        model.addAttribute("event", eventToEdit);
//        String title = "Edit Event " + eventToEdit.getName() + " (id=" + eventToEdit.getId() + ")";
//        model.addAttribute("title", title);
//        // Chptr 16.2 - Enums - Line below will reload EventType enums in Model if page reloads due to validation error.
////        model.addAttribute("types", EventType.values());
//        // Chptr 18 - Replaced EventType above with eventCategoryRepository below.
//        model.addAttribute("categories", eventCategoryRepository.findAll());
//        return "events/edit";
//    }
////
//    @PostMapping("edit")
//    public String processEditForm(int eventId, String name, String description, String contactEmail, Boolean register, int numberOfAttendees, EventCategory eventCategory) {
//        // controller code will go here
//        Event eventToEdit = EventData.getById(eventId);
//        eventToEdit.setName(name);
//        eventToEdit.setDescription(description);
//        eventToEdit.setContactEmail(contactEmail);
//        eventToEdit.setRegister(register);
//        eventToEdit.setNumberOfAttendees(numberOfAttendees);
////        eventToEdit.setType(type);
//        // Chptr 18 - Replaced EventType type line above with eventCategory. Not sure if it will work.
//        eventToEdit.setEventCategory(eventCategory);
//        return "redirect:";
//    }

}