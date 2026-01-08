package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "events")
// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "event_id")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private int event_id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "description", nullable = false, unique = false)
    private String description;

    @Column(name = "startDate", nullable = false, unique = false)
    private String startDate;

    @Column(name = "endDate", nullable = false, unique = false)
    private String endDate;

    @Column(name = "requiredVolunteers", nullable = false, unique = false)
    private int requiredVolunteers;

    @Column(name = "volunteerCount", nullable = true, unique = false)
    private int volunteerCount;

    @Column(name = "create_events_points", nullable = true, unique = false)
    private int createEventsPoints;

    @Column(name = "volunteer_points", nullable = true, unique = false)
    private int volunteerPoints;

    @ManyToOne
    @JoinColumn(name = "organizer", nullable = true)
    private User organizer;

    @ManyToMany
    @JoinTable(
        name = "event_volunteer", 
        joinColumns = @JoinColumn(name = "event_id"), 
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> volunteers = new HashSet<>();

    public Event(String title, String description, String startDate, String endDate, Integer requiredVolunteers, User organizer) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.requiredVolunteers = requiredVolunteers;
        this.organizer = organizer;
}
    

    public Event() {
    }
    
    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
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

    public int getRequiredVolunteers() {
        return requiredVolunteers;
    }

    public void setRequiredVolunteers(int requiredVolunteers) {
        this.requiredVolunteers = requiredVolunteers;
    }

    public int getVolunteerCount() {
        return volunteerCount;
    }

    public void setVolunteerCount(int volunteerCount) {
        this.volunteerCount=volunteerCount;
    }

    public int getCreateEventsPoints() {
        return createEventsPoints;
    }
    
    public void setCreateEventsPoints(int createEventsPoints) {
        this.createEventsPoints = createEventsPoints;
    }
    
    public int getVolunteerPoints() {
        return volunteerPoints;
    }
    
    public void setVolunteerPoints(int volunteerPoints) {
        this.volunteerPoints = volunteerPoints;
    }
    

    public User getOrganizer() {
        return organizer;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    public Set<User> getVolunteers() {
        return volunteers;
    }

    public void setVolunteers(Set<User> volunteers) {
        this.volunteers = volunteers;
    }


}