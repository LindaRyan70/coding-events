package org.launchcode.codingevents.data;

import org.launchcode.codingevents.models.EventCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//  Chptr 17.4 Exercises OMG the ORM! (Creating a new persistent model/controller/templates to relate to Event. //
@Repository
public interface EventCategoryRepository extends CrudRepository<EventCategory, Integer> {
}
