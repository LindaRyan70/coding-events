package org.launchcode.codingevents.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Objects;

//  Chptr 17.2.3 Creating Persistent Models - Adding @ annotations to allow Hibernate to find/create database tables for MySQL
@Entity     // Tells SpringBoot that this is a persistent class/object, so Event objects can be stored outside of our app in a database.
public class Event {

//  Chptr 17.2.3
    @Id    // Tells app that this is a PRIMARY KEY
    @GeneratedValue     //  Tells app that the database will do the work of assigning/generating the primary key value.
    private int id;

//    Chpt 17.2.3 - Delete manual id increment code b/c the @GeneratedValue above will now handle that in the Database.
//    private static int nextId = 1;

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
//  Chptr 17.2.3 Creating Persistent Models
//  Delete this() line below, calling the empty constructor b/c not calling getters/setters or doing anything anymore.
//        //        this();
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

//  Chptr 17.2.3 Creating Persistent Models
//  Delete extra id constructor and replace with empty/no arg constructor below it.

//    public Event() {
//        this.id = nextId;
//        nextId++;
//    }
    public Event() {}   // Entity class MUST have both regular constructor AND this EMPTY/NO ARG constructor for database use.

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