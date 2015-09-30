package io.pivotal.workshop.web;

import io.pivotal.workshop.entity.Attendee;
import io.pivotal.workshop.repository.AttendeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements InitializingBean {
	
	private static Logger LOG = LoggerFactory.getLogger(DataInitializer.class);
	
	@Autowired
	private AttendeeRepository _repo;

	@Override
	public void afterPropertiesSet() {
		LOG.info("Verifying if data needs to be created");
		if(_repo.count() > 0) {
			LOG.info("Sample Data already loaded.");
		} else {
			Attendee attendee1 = new Attendee();
			attendee1.setId(5001L);
			attendee1.setFirstName("John");
			attendee1.setLastName("Smith");
			attendee1.setAddress("123 Main St");
			attendee1.setCity("Akron");
			attendee1.setState("OH");
			attendee1.setZipCode("44321");
			attendee1.setPhoneNumber("330-123-4567");
			attendee1.setEmailAddress("jsmith@gopivotal.com");
			_repo.save(attendee1);
			
			Attendee attendee2 = new Attendee();
			attendee2.setId(5002L);
			attendee2.setFirstName("Sally");
			attendee2.setLastName("Struthers");
			attendee2.setAddress("321 Oak St");
			attendee2.setCity("Akron");
			attendee2.setState("OH");
			attendee2.setZipCode("44321");
			attendee2.setPhoneNumber("614-123-4567");
			attendee2.setEmailAddress("sstruthers@gopivotal.com");
			_repo.save(attendee2);
			
			Attendee attendee3 = new Attendee();
			attendee3.setId(5003L);
			attendee3.setFirstName("Jane");
			attendee3.setLastName("doe");
			attendee3.setAddress("4444 High St");
			attendee3.setCity("Akron");
			attendee3.setState("OH");
			attendee3.setZipCode("44321");
			attendee3.setPhoneNumber("220-123-4567");
			attendee3.setEmailAddress("jdoe@gopivotal.com");
			_repo.save(attendee3);
			LOG.info("Sample Data loaded...  3 attendees");
		}
	}

}
