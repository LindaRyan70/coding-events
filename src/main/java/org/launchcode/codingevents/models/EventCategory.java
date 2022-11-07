package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//  Chptr 17.4 Exercises OMG the ORM! (Creating a new persistent model/controller/templates to relate to Event. //
@Entity
public class EventCategory extends AbstractEntity {

//  Chptr 18.3 One-to-Many (One EventCategory can have access to the Many Event objects belonging to it.)
    @OneToMany(mappedBy = "eventCategory")  // This tells hibernate where to find the events in a given category using variable from Event class.
    private final List<Event> events = new ArrayList<>(); // creates an ArrayList of the various events.
    // Using the final keyword above means the List itself cannot change, but events in it still can.

    //  Chptr 17.5 Studio removes duplicate id field and places it in AbstractEntity class that EventCategory extends. //
//    @Id
//    @GeneratedValue
//    private int id;

    @NotBlank(message = "Name is required.")
    @Size(min = 3, max = 50, message = "Named must be between 3 and 50 characters.")
    private String name;

//  Chptr 18.3 Many-To-One - Do NOT need to add the List event to constructor, since initialized in field declaration and is final.
    public EventCategory(@Size(min = 3, max = 50, message = "Named must be between 3 and 50 characters.") String name) {
        this.name = name;
    }

    public EventCategory() {}

    //  Chptr 17.5 Studio removes duplicate getId() method and places it in AbstractEntity class that EventCategory extends. //
//    public int getId() {
//        return id;
//    }

    public String getName() {
            return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//  Chpter 18.3 -Many-to-One - Only need a getter for the List of events, not a setter, since the field is set to be final.
    public List<Event> getEvents() {
        return events;
    }

    @Override
    public String toString() {
        return name;
    }

    //  Chptr 17.5 Studio removes duplicate equals() method and places it in AbstractEntity class that EventCategory extends. //
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        EventCategory that = (EventCategory) o;
//        return id == that.id;
//    }

    //  Chptr 17.5 Studio removes duplicate hashCod() method and places it in AbstractEntity class that EventCategory extends. //
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }

}
