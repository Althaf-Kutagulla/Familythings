package com.pojo;

public class CalendarEvent {
	
	private String eventId;
	private String title;
	private String description;
	private String location;
	private String startDateTime;
	private String endDateTime;
	//empty constructor
	public CalendarEvent(){
		
	}
	//Parameterized constructor
	public CalendarEvent(String eventId,String title, String description, String location, String startDateTime, String endDateTime) {
		super();
		this.eventId= eventId;
		this.title = title;
		this.description = description;
		this.location = location;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}
	public String getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}
	
	

}
