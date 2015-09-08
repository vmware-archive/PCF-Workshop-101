package com.gopivotal.cf.workshop.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gopivotal.cf.workshop.entity.Attendee;
import com.gopivotal.cf.workshop.entity.Session;

/**
 * JPA repository for the Session entity.
 * 
 * @author Brian Jimerson
 *
 */
public interface SessionRepository extends CrudRepository<Session, Long> {
	
	List<Session> findByAttendee(Attendee attendee);
	
}
