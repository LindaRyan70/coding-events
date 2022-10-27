package org.launchcode.codingevents.data;

import org.launchcode.codingevents.models.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


// Chapter 17.2.3 Creating Persistent Models - This replaces the EventData class we used as a temporary in-app database.
@Repository     // Tells SpringBoot this is a database repository
public interface EventRepository extends CrudRepository<Event, Integer> {

}
/* The EventRepository is an interface and it extends the CrudRepository base class that gives access to all methods
needed for our EventRepository interface to add, delete data. CrudRepository is a parameterized interface that requires
you to pass in first the type of the database class you are using and second the type of that object's primary kay.
In this example, we pass <Event, Integer> */