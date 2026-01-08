package com.example.demo.dto;

public class AllEventsDTO {
    String title;
    String description;
    String startDate;
    String endDate;
    int volunteerCount;
    int requiredVolunteers;
    int event_id;
    String username;
    boolean enrolled;
    
    public boolean isEnrolled() {
        return enrolled;
    }
    public void setEnrolled(boolean enrolled) {
        this.enrolled = enrolled;
    }
    public String getTitle() {
        return title;
    }
    public AllEventsDTO(String title, String description, String startDate, String endDate, int volunteerCount,
            int requiredVolunteers, int event_id, String username, boolean enrolled) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.volunteerCount = volunteerCount;
        this.requiredVolunteers = requiredVolunteers;
        this.event_id = event_id;
        this.username = username;
        this.enrolled = enrolled;
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
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public int getVolunteerCount() {
        return volunteerCount;
    }
    public void setVolunteerCount(int volunteerCount) {
        this.volunteerCount = volunteerCount;
    }
    public int getRequiredVolunteers() {
        return requiredVolunteers;
    }
    public void setRequiredVolunteers(int requiredVolunteers) {
        this.requiredVolunteers = requiredVolunteers;
    }
    public int getEvent_id() {
        return event_id;
    }
    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }


}
