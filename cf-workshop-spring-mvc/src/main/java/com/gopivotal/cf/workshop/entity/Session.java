package com.gopivotal.cf.workshop.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * An attendee's session.
 */
@Entity
public class Session {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
		
	@ManyToOne
	private Attendee attendee;

	private String name;
	private Date date;
	private boolean completed;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the attendee
	 */
	public Attendee getAttendee() {
		return attendee;
	}
	/**
	 * @param attendee the attendee to set
	 */
	public void setAttendee(Attendee attendee) {
		this.attendee = attendee;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the completed
	 */
	public boolean isCompleted() {
		return completed;
	}
	/**
	 * @param completed the completed to set
	 */
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
}
