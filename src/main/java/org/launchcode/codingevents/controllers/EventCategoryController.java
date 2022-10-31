package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.models.EventCategory;
// Chptr 18.2 - Many-to-One Relationship - Replaced EventType import below with EventCategory and EventCategoryRepository.
//import org.launchcode.codingevents.models.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("eventCategories")
public class EventCategoryController {

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @GetMapping
    public String displayAllEventCategories(Model model) {
        model.addAttribute("title", "All Categories");
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "eventCategories/index";
    }

    @GetMapping("create")
    public String renderCreateEventCategoryForm(Model model) {
        model.addAttribute("title", "Create Category");
        model.addAttribute(new EventCategory());
        return "eventCategories/create";
    }

    @PostMapping("create")
    public String processCreateEventCategoryForm(@ModelAttribute @Valid EventCategory newEventCategory, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Category");
            model.addAttribute(new EventCategory()); // Why is this needed here if not used in the EventController's method?
            return "eventCategories/create";
        }
        eventCategoryRepository.save(newEventCategory);
        return "redirect:";  // redirects by default to root path for this controller ("eventCategories".
    }

/*  ADDED MY OWN EXTRA Chptr 17.4 Exercises - I decided to practice more by adding Delete Category methods to controller,
           along with creating an eventCategories/delete.html template AND adding a hyperlink to the delete page in the
           header fragment.html. The first one handles the page to select which items to delete with check boxes. */
/* UPDATE Chptr 18.2 - It displays fine, however, the second method won't actually delete the category due to an SQL foreign key
           constraint because event_category_id references event_categoy (id) and it cannot be Null. A parent key
           cannot be deleted. So I turned off these methods. */

//    @GetMapping("delete")
//    public String displayDeleteEventCategoryForm(Model model) {
//        model.addAttribute("title", "Delete Event Categories");
//        model.addAttribute("categories", eventCategoryRepository.findAll());
//        return "eventCategories/delete";
//    }
//// Handles the actual deleting of the category data from the database/MySQL
//    @PostMapping("delete")
//    public String processDeleteEventCategoryForm(@RequestParam(required = false) int[] eventCategoryIds) {
//
//        if (eventCategoryIds != null) {
//            for (int id : eventCategoryIds) {
//                eventCategoryRepository.deleteById(id);
//            }
//        }
//        return "redirect:";
//    }

}

