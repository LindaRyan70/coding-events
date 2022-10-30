package org.launchcode.codingevents.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

/*    Chptr 17.5 Studio creates abstract AbstractEntity class to hold duplicate id field, getId(), equals(), hashCode()
        and remove it from the Event and EventCategory classes that extend it. Makes the code more DRY.
        @MappedSuperClass annotation ensures id values are stored in Event and EventCategory tables of database since
        the id fields are no longer in the Event and EventCategory classes themselves.*/

@MappedSuperclass
public abstract class AbstractEntity {

    @Id    // Tells app that this is a PRIMARY KEY
    @GeneratedValue     //  Tells app that the database will do the work of assigning/generating the primary key value.
    private int id;

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        //  Chptr 17.5 Studio removes duplicate equals() method and places it in AbstractEntity class but must then
        //  change the type casting b/c id is no longer in the Event class. It is in the AbstractEntity class. //
//        Event event = (Event) o;
        AbstractEntity entity = (AbstractEntity) o;
        // Also need to update the variable name below.
//        return id == event.id;
        return id == entity.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
