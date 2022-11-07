package org.launchcode.codingevents.data;

import org.launchcode.codingevents.models.Tag;
import org.springframework.data.repository.CrudRepository;

// Chptr 18.5.1 Persistent Tags (Creating a ManyToMany Relationship)
/* Video sets up a new TagRepository class that extends CrudRepository below. Replicated EventCategoryController code. */
public interface TagRepository extends CrudRepository<Tag, Integer> {
}
