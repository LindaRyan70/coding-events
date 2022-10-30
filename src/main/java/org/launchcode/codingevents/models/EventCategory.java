package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class EventCategory extends AbstractEntity {

    //  Chptr 17.5 Studio removes duplicate id field and places it in AbstractEntity class that EventCategory extends. //
//    @Id
//    @GeneratedValue
//    private int id;

    @NotBlank(message = "Name is required.")
    @Size(min = 3, max = 50, message = "Named must be between 3 and 50 characters.")
    private String name;

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
