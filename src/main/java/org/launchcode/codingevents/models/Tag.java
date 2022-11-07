package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

// Chptr 18.5.1 Persistent Tags (Creating a ManyToMany Relationship)
/* Video sets up a new persistent entity class called Tag and instructor says we could do this on our own, but no where
    does it guide us as to how to know what fields to create, what validation we need, or that we should create a
    special custom accessor method called getDisplayName() in the getters/setters below. So, copied video code. */
@Entity
public class Tag extends AbstractEntity {

    @Size(min = 1, max = 25)
    @NotBlank
    private String name;

    public Tag(String name) {
        this.name = name;
    }

    public Tag() {}

    public String getName() {
        return name;
    }

//    Extra method that returns the name with a hashtag symbol.
    public String getDisplayName() {
        return "#" + name + " ";
    }

    public void setName(String name) {
        this.name = name;
    }
}

