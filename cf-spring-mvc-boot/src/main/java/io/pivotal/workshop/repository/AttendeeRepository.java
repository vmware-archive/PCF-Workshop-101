package io.pivotal.workshop.repository;

import io.pivotal.workshop.entity.Attendee;
import org.springframework.data.repository.CrudRepository;

/**
 * JPA repository for the Attendee entity.
 * 
 * @author Brian Jimerson
 *
 */
public interface AttendeeRepository extends CrudRepository<Attendee, Long> {

}
