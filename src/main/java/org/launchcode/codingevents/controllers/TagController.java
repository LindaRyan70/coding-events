package org.launchcode.codingevents.controllers;

import org.apache.catalina.mbeans.SparseUserDatabaseMBean;
import org.launchcode.codingevents.data.TagRepository;
import org.launchcode.codingevents.models.EventCategory;
import org.launchcode.codingevents.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


// Chptr 18.5.1 Persistent Tags (Creating a ManyToMany Relationship)
/* Video sets up a new TagController class with a special custom accessor method called getDisplayName() in the
   getters/setters below. Referenced/Replicated EventCategoryController code with some tweaks. */
@Controller
@RequestMapping("tags")
public class TagController {

    @Autowired
    private TagRepository tagRepository;

    //  Handles the initial tags main page showing all tag options (tags/index.html).
    @GetMapping
    public String displayTags(Model model) {
        model.addAttribute("title", "All Tags");
        model.addAttribute("tags", tagRepository.findAll());
        return "tags/index";
    }

    //  Handles displaying the form to create tags at tags/create page.
    @GetMapping("create")
    public String displayCreateTagForm(Model model) {
        model.addAttribute("title", "Create Tag");
        model.addAttribute(new Tag()); // Cannot usse tag variable here since not declared in the param list like other methods below. //
        return "tags/create";
    }

    // Handles when the form is submitted to add a new Tag coming from 'tag/create' and redirecting to root (localhost:8080/tags).
    @PostMapping("create")
    public String processCreateTagForm(@ModelAttribute @Valid Tag tag, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Tag");
//            model.addAttribute(new Tag()); // Creates an instance of Tag for the model.
            model.addAttribute(tag); // This variable from param above works like line above to create (new Tag()) obj instance.
            return "tags/create";
        }
            tagRepository.save(tag);
            return "redirect:";  // redirects by default to root path for this controller ('tags').
    }

}
