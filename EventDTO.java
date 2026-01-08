package com.example.demo.dto;

public class EventDTO {
    private String title;
    private String description;
    private String startDate;
    private String endDate;
    private Integer requiredVolunteers;
    private String username;
    
    public EventDTO(String title, String description, String startDate, String endDate, Integer requiredVolunteers,
            String username) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.requiredVolunteers = requiredVolunteers;
        this.username = username;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public void setRequiredVolunteers(Integer requiredVolunteers) {
        this.requiredVolunteers = requiredVolunteers;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getStartDate() {
        return startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public Integer getRequiredVolunteers() {
        return requiredVolunteers;
    }
    public String getUsername() {
        return username;
    }


}
