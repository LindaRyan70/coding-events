package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.validation.constraints.*;

/*  Chptr 18.4 - Creating a One-to-One Relationship - Made a new EventDetails class and moved 'metadata' detail fields
                here from Event. Also extended AbstractEntity and added constructors (parameterized and no arg) & get/sets. */
@Entity     // Persists the class to store in our database.
public class EventDetails extends AbstractEntity{

    @Size(max = 500, message = "Description is too long!")
    private String description;

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

    /* Chptr 18.4.2.5 Setting up the Inverse/Opposite OneToOne Relationship
    *       Currently commented off the event field below b/c instructor said not needed for this application.
    *       Added an Event event field in EventDetails that maps to 'eventDetails'.
    *       - This allows EventDetails to ALSO know which Event obj it is related to instead of just Event knowing which
    *           EventDetails obj it is related to (with the original OneToOne eventDetails field on Event class.)
    *       - Tells Hibernate: In order to populate the event field, it must find the Event obj with the 'eventDetails'
    *           field that points to the given EventDetails object. */
//    @OneToOne(mappedBy = "eventDetails")
//    private Event event;


    public EventDetails(@Size(max = 500, message = "Description is too long!") String description, String contactEmail,
                        String location, Boolean register, int numberOfAttendees) {
        this.description = description;
        this.contactEmail = contactEmail;
        this.location = location;
        this.register = register;
        this.numberOfAttendees = numberOfAttendees;
    }

    public EventDetails() {}


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

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
}
