package org.launchcode.codingevents.models;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Objects;

//  Chptr 17.2.3 Creating Persistent Models - Adding @ annotations to allow Hibernate to find/create database tables for MySQL
@Entity     // Tells SpringBoot that this is a persistent class/object, so Event objects can be stored outside of our app in a database.
public class Event extends AbstractEntity {

//  Chptr 17.5 Studio removes duplicate id field and places it in AbstractEntity class that Event extends. //
//  Chptr 17.2.3 - Adding @ annotations to allow Hibernate to set up Primary Key in MySQL
//    @Id    // Tells app that this is a PRIMARY KEY
//    @GeneratedValue     //  Tells app that the database will do the work of assigning/generating the primary key value.
//    private int id;

//    Chpt 17.2.3 - Delete manual id increment code b/c the @GeneratedValue above will now handle that in the Database.
//    private static int nextId = 1;

    //   Chptr 15.2 - Added Validation Annotations for name, description, email. //
    @NotBlank(message = "Name is required.")
    @Size(min = 3, max = 50, message = "Named must be between 3 and 50 characters.")
    private String name;

/*    Chptr 18.4 - Creating a One-to-One Relationship - Made a new EventDetails class and moved detail fields below to
                   the EventDetails model class. */

//    @Size(max = 500, message = "Description is too long!")
//    private String description;
//
//    //   Chptr 15.2 - Added contact email field, and added this to the constructor as well as getters/setters below.  //
//    @NotBlank(message = "Email is required.")
//    @Email(message = "Invalid email. Try again!")
//    private String contactEmail;
//
//
//    // Cptr 15.5 Exercises - Model Validation - Added 3 more fields to check validation.
//    @NotBlank(message="Location may not be left blank.")
//    private String location;
//
//    @AssertTrue(message = "Registration is required.")
//    private Boolean register;
//
//    @Positive(message="Number of attendees must be one or more.")
//    private int numberOfAttendees;

//  Chptr 16.2 Enums Practice/Video
//    private EventType type;

//  Chptr 18.2 - Many-to-One Relationship - Replace EventType Enum code above with eventCategory field below w/ validation.
    @ManyToOne
    @NotNull(message = "An Event Category is required.") //This actually prevents EventCategoryController from using Delete methods.
    private EventCategory eventCategory;

    /*    Chptr 18.4 - Creating a One-to-One Relationship - Made a new EventDetails class and moved detail fields there.
                Add a field for EventDetails to establish the 1-to-1 relationship with EventDetails class. */
    /*    Chptr 18.4.2.4 Cascade ORM Operations - This parameter (cascade = CascadeType.ALL) allows you to specify in the
    *       relationship between Event and its sub-object EventDetails, that whatever operations happen to the Event object
    *       also cascade down/apply to the related sub-object EventDetails, whether that be to save or delete the objects.
    *       There are various CascadeType values (.ALL, .PERSIST, .REMOVE, etc.), but ALL is the most commonly used and
    *       allows all operations.
    *   - NOTE: @OneToOne establishes a Foreign Key (event_details_id) on the event table in MySQL. */
    @OneToOne(cascade = CascadeType.ALL) // Cascade = what happens to Event obj is also applied to its EventDetails sub-object. //
    @Valid
    @NotNull
    private EventDetails eventDetails;


/*    Chptr 18.4 - Creating a One-to-One Relationship - Made a new EventDetails class and moved detail fields there.
                Then modified the constructor to remove those fields and add the eventDetails field parameter. */
     public Event(String name, EventCategory eventCategory, EventDetails eventDetails) {
            this.name = name;
    //  Chptr 18.2 - Many-to-One Relationship - Update constructor to remove (EventType type) and add an eventCategory field instead.
            this.eventCategory = eventCategory;
     // Chptr 18.4 - One-to-One Relationship - I added eventDetails field to the constructor. Video said not necessary //
         // here since we'll use model binding and a setter to populate the eventDetails field. But may be useful in other situations. //
            this.eventDetails = eventDetails;
        }

    //  Chptr 17.2.3 Creating Persistent Models
    //  Delete extra id constructor and replace with empty/no arg constructor below it.
//    public Event() {
//        this.id = nextId;
//        nextId++;
//    }

    public Event() {}   // Entity class MUST have both regular constructor AND this EMPTY/NO ARG constructor for database use.

/*  Chptr 18.4 - Creating a One-to-One Relationship - Made a new EventDetails class and moved detail fields there.
                    Also, moved the getters/setters for those fields. */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    //    18.2 - Many-to-One Relationship - Removed type get/sets and added getter/setter for eventCategory instead.
    public EventCategory getEventCategory(){
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }

    /*    Chptr 18.4 - Creating a One-to-One Relationship - Made a new EventDetails class and moved detail fields there.
                Added a field for EventDetails to establish the 1-to-1 relationship with EventDetails class.
                Added a Getter/Setter for the eventDetails field. */

    public EventDetails getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(EventDetails eventDetails) {
        this.eventDetails = eventDetails;
    }
    //  Chptr 17.5 Studio removes duplicate id field and places it in AbstractEntity class that Event extends. //
//    public int getId() {
//        return id;
//    }

//  Custom toString, equals, hashCode methods:
    @Override
    public String toString() {
        return name;
    }

    //  Chptr 17.5 Studio removes duplicate equals() method and places it in AbstractEntity class that Event extends. //
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Event event = (Event) o;
//        return id == event.id;
//    }

    //  Chptr 17.5 Studio removes duplicate hashCode() method and places it in AbstractEntity class that Event extends. //
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }

}