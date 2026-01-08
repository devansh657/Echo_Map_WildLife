package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Event;
import com.example.demo.model.Rewards;
import com.example.demo.model.User;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.RewardsRepository;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final RewardsRepository rewardsRepository;

    @Autowired
    private UserService userService;

    public EventService(EventRepository eventRepository, UserRepository userRepository, RewardsRepository rewardsRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.rewardsRepository = rewardsRepository;
    }


    public String deleteEvent(Integer eventId, String username) {
        Optional<User> optionalOrganizer = userRepository.findByUsername(username);
        if (optionalOrganizer.isEmpty()) {
            return "Organizer account doesn't exist";
        }

        User user = optionalOrganizer.get();

        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isEmpty()) {
            return "Event doesn't exist";
        }
        Event event = optionalEvent.get();

        user.getEvents().remove(event);
        eventRepository.delete(event);
        return "Event deleted";
    }

    public String createEvent(String title, String description, String startDate, String endDate, int requiredVolunteers, String username) {
        int create_events_points = 10; //Assigning fixed value 10 to the points of creating events
        Optional<User> optionalOrganizer = userRepository.findByUsername(username);
        if (!optionalOrganizer.isPresent()) {
            return "Organizer user not found";
        }


        User organizer = optionalOrganizer.get();

        Optional<Event> titleCheck = eventRepository.findByTitle(title);

        if (titleCheck.isPresent()) {
            return "Title already exists";
        }

        // Create new event with organizer and set create events points
        Event newEvent = new Event(title, description, startDate, endDate, requiredVolunteers, organizer);

        eventRepository.save(newEvent);

        // Update organizer's points
        organizer.setPointsEarned(organizer.getPointsEarned() + create_events_points); // Add points to organizer's total points
        userRepository.save(organizer);

        // Check if the organizer is eligible for any rewards
        checkAndAddRewards(organizer);

        return "Event created";
    }

    public List<Event> findAllEvents() {
        List<Event> events = eventRepository.findAll();

        return events;
    }

    public List<Event> findOwnEvents(String username) {
        List<Event> events = eventRepository.findByOrganizerUsername(username);
        return events;
    }

    @Transactional
    public String unenrolToEvent(Integer eventId, String username) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);

        if (!optionalEvent.isPresent()) {
            return "Event not found";
        }


        Event event = optionalEvent.get();
        Set<User> volunteers = event.getVolunteers();

        for (User user : volunteers) {
            if (user.getUsername().equals(username)) {
                return "User enrolled already";
            }
        }
        if (event.getRequiredVolunteers() == event.getVolunteerCount()) {
            return "No space available";
        }

        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (!optionalUser.isPresent()) {
            return "User trying to enroll not found";
        }

        User user = optionalUser.get();

        event.getVolunteers().add(user);
        user.getEvents().add(event);
        event.setVolunteerCount(event.getVolunteerCount() + 1);
        eventRepository.save(event);

        // Update points earned by user
        userService.updatePoints(username, -5);

        // Check if the user is eligible for any rewards
        checkAndAddRewards(user);

        return "User Enrolled";
    }

    @Transactional
    public String enrolToEvent(Integer eventId, String username) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        int volunteer_points = 5;
        if (!optionalEvent.isPresent()) {
            return "Event not found";
        }

        Event event = optionalEvent.get();
        Set<User> volunteers = event.getVolunteers();
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (!optionalUser.isPresent()) {
            return "User trying to enroll not found";
        }

        User user = optionalUser.get();

        for (User volunteer : volunteers) {
            if (volunteer.getUsername().equals(username)) {
                event.getVolunteers().remove(volunteer);
                user.getEvents().remove(event);
                event.setVolunteerCount(event.getVolunteerCount() - 1);
                return "User Unenrolled";
            }
        }
        if (event.getRequiredVolunteers() == event.getVolunteerCount()) {
            return "No space available";
        }

        event.getVolunteers().add(user);
        user.getEvents().add(event);
        event.setVolunteerCount(event.getVolunteerCount() + 1);


        user.setPointsEarned(user.getPointsEarned() + volunteer_points);

        eventRepository.save(event);
        userRepository.save(user);

        // Update points earned by user
        userService.updatePoints(username, 5);

        // Check if the user is eligible for any rewards
        checkAndAddRewards(user);

        return "User Enrolled";
    }

    private void checkAndAddRewards(User user) {
        List<Rewards> availableRewards = rewardsRepository.findAll();
        for (Rewards reward : availableRewards) {
            if (user.getPointsEarned() >= reward.getPointsRequired() && !user.getRewardsReceived().contains(reward)) {
                user.getRewardsReceived().add(reward);
                userRepository.save(user);
            }
        }
    }
}
