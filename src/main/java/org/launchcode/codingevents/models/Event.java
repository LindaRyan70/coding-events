package org.launchcode.codingevents.models;

import javax.validation.constraints.*;
import java.util.Objects;

public class Event {

    private int id;
    private static int nextId = 1;

    //   Chptr 15.2 - Added Validation Annotations for name, description, email. //
    @NotBlank(message = "Name is required.")
    @Size(min = 3, max = 50, message = "Named must be between 3 and 50 characters.")
    private String name;

    @Size(max = 500, message = "Description is too long!")
    private String description;

    //   Chptr 15.2 - Added contact email field, and added this to the constructor as well as getters/setters below.  //
    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid email. Try again!")
    private String contactEmail;


    // Cptr 15.5 Exercises - Model Validation - Added 3 more fields to check validation.
    @NotBlank(message="Location may not be left blank.")
    private String location;

    @AssertTrue(message = "Registration is required.")
    private Boolean register;

    @Positive(message="Number of attendees must be one or more.")
    private int numberOfAttendees;

//  Chptr 16.2 Enums Practice/Video
    private EventType type;

    public Event(String name, String description, String contactEmail, String location, Boolean register, int numberOfAttendees, EventType type) {
        this();
        this.name = name;
        this.description = description;
        this.contactEmail = contactEmail;
//  Chptr 15.5 Exercises - Model Validation - Added 3 more fields to the constructor to check validation.
        this.location = location;
        this.register = register;
        this.numberOfAttendees = numberOfAttendees;
//  Chptr 16.2 Enums Practice/Video
        this.type = type;

    }

    public Event() {
        this.id = nextId;
        nextId++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //   Chptr 15.2 - Added get/set for the new email filed above. //
    public String getContactEmail() {return contactEmail;}

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }


    //  Chapter 15.6 Exercises - Model Validation - Adding get/set for 3 new fields at the top, along with adding to constructor.

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getRegister() {
        return register;
    }

    public void setRegister(Boolean register) {
        this.register = register;
    }

    public int getNumberOfAttendees() {
        return numberOfAttendees;
    }

    public void setNumberOfAttendees(int numberOfAttendees) {
        this.numberOfAttendees = numberOfAttendees;
    }


//  Chptr 16.2 Enums Practice/Video
    public EventType getType(){
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

//  Getter for ID from starter code.
    public int getId() {
        return id;
    }


//  Custom toString, equals, hashCode methods:
    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}