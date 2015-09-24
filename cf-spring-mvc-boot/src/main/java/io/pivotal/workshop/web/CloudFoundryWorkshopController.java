package io.pivotal.workshop.web;

import io.pivotal.workshop.entity.Attendee;
import io.pivotal.workshop.repository.AttendeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Controller for the Cloud Foundry workshop - Spring MVC version.
 * 
 */
@Controller
public class CloudFoundryWorkshopController {
	
	private static final Logger logger = LoggerFactory.getLogger(CloudFoundryWorkshopController.class);

	@Autowired
	private AttendeeRepository attendeeRepository;

	/**
	 * Gets basic environment information.  This is the application's
	 * default action.
	 * @param model The model for this action.
	 * @return The path to the view.
	 * @throws IOException 
	 * @throws org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.databind.JsonMappingException
	 * @throws org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.core.JsonParseException
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) throws Exception {
		setModel(model);
		return "index";
	}
	
	/**
	 * Action to get a list of all attendees.
	 * @param model The model for this action.
	 * @return The path to the view.
	 */
	@RequestMapping(value = "/attendees", method = RequestMethod.GET)
	public String attendees(Model model) {

		Iterable<Attendee> attendees = attendeeRepository.findAll();
		model.addAttribute("attendees", attendees);
		return "attendees";
	}

	private void setModel(Model model) throws Exception {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy h:mm a");
		String serverTime = dateFormat.format(date);
		model.addAttribute("serverTime", serverTime);

		String port = System.getenv("PORT");
		model.addAttribute("port", port);

		String vcapApplication = System.getenv("VCAP_APPLICATION");
		ObjectMapper mapper = new ObjectMapper();
		if (vcapApplication != null) {
			Map vcapMap = mapper.readValue(vcapApplication, Map.class);
			model.addAttribute("vcapApplication", vcapMap);
		} else {
			model.addAttribute("vcapApplication", new HashMap<>());
		}

		String vcapServices = System.getenv("VCAP_SERVICES");
		model.addAttribute("vcapServices", vcapServices);

		Iterable<Attendee> attendees = attendeeRepository.findAll();
		model.addAttribute("attendees", attendees);
	}

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam("attendeeId") Long attendeeId, Model model) throws Exception {
		attendeeRepository.delete(attendeeId);
		setModel(model);
		return "redirect:/";
	}
	
	/**
	 * Action to initiate shutdown of the system.  In CF, the application 
	 * <em>should</em>f restart.  In other environments, the application
	 * runtime will be shut down.
	 */
	@RequestMapping(value = "/kill", method = RequestMethod.GET)
	public void kill() {
		
		logger.warn("*** The system is shutting down. ***");
		System.exit(-1);
		
	}
	
	/**
	 * Action to place memory load on system
	 */
	@RequestMapping(value = "/mem", method = RequestMethod.GET)
	public String memory(@RequestParam(required=true, value="value") Long size) {
		
		size = size * 1024;
		
		//allocate specified memory in jvm
		char[] chars = new char[(size.intValue()/2)]; //divide by 2 since a char is 2 bytes
		Arrays.fill(chars, 'a');
		logger.info("Consumed " + size + "kb");
		return "index";
	}
	
	/**
	 * Action to place cpu load on system
	 */
	@RequestMapping(value = "/cpu", method = RequestMethod.GET)
	public String cpu(@RequestParam(required=true, value="value") Long time) throws Exception{

		StringBuilder sb = new StringBuilder("abcdefghijklmnopqrstuvwxyz");
		
		//allocate consume CPU for specified time
		long start = System.currentTimeMillis();
		while((System.currentTimeMillis() - start) < time) {
			sb.reverse();
			Thread.sleep(0,2);
		}
		logger.info("Consumed CPU for " + time + " millis");
		
		return "index";
	}

}