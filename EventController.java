package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AllEventsDTO;
import com.example.demo.dto.EventDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.model.Event;
import com.example.demo.service.EventService;
import com.example.demo.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService, UserService userService) {
        this.eventService = eventService;
    }

    @PostMapping(value = "/createEvent", consumes="application/json")
    public ResponseEntity<ResponseDTO> createEvent(@RequestBody EventDTO eventDTO){
        ResponseDTO response;
        String status = eventService.createEvent(
            eventDTO.getTitle(),
            eventDTO.getDescription(),
            eventDTO.getStartDate(),
            eventDTO.getEndDate(),
            eventDTO.getRequiredVolunteers(),
            eventDTO.getUsername()
        );

        switch(status){
            case "Title already exists":
                response = new ResponseDTO("Title", status);
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
            case "Event created":
                response = new ResponseDTO("Added", status);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            case "Organizer user not found":
                response = new ResponseDTO("Added", status);
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
            default:
                response = new ResponseDTO("Error", "An unknown error occurred");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{eventId}/{user}")
    public ResponseEntity<ResponseDTO> deleteEvent(@PathVariable Integer eventId, @PathVariable String user){
        String status = eventService.deleteEvent(eventId, user);
        ResponseDTO response = status.equals("Event deleted") 
            ? new ResponseDTO("Deleted", status) 
            : new ResponseDTO("Error", status);

        HttpStatus httpStatus = status.equals("Event deleted") ? HttpStatus.OK : HttpStatus.CONFLICT;
        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping("/allEvents/{username}/{filter}")
    public ResponseEntity<List<AllEventsDTO>> getAllEvents(@PathVariable String username, @PathVariable String filter) {
        List<Event> events = "false".equals(filter) ? eventService.findAllEvents() : eventService.findOwnEvents(username);
        
        if(events.isEmpty()){
            return ResponseEntity.ok(new ArrayList<>());
        }

        List<AllEventsDTO> allEvents = new ArrayList<>();
        for (Event target : events) {
            boolean enrolmentCheck = target.getVolunteers().stream()
                                           .anyMatch(user -> user.getUsername().equals(username));
            AllEventsDTO event = new AllEventsDTO(
                target.getTitle(),
                target.getDescription(),
                target.getStartDate(),
                target.getEndDate(),
                target.getVolunteerCount(),
                target.getRequiredVolunteers(),
                target.getEvent_id(),
                target.getOrganizer().getUsername(),
                enrolmentCheck
            );
            
            allEvents.add(event);
        }

        return ResponseEntity.ok(allEvents);
    }

    @PutMapping("/enroll/{eventId}/{username}")
    public ResponseEntity<ResponseDTO> enrolToEvent(@PathVariable Integer eventId, @PathVariable String username){
        String status = eventService.enrolToEvent(eventId, username);
        ResponseDTO response = new ResponseDTO(status.equals("User Enrolled") ? "Added" : "Error", status);

        HttpStatus httpStatus = status.equals("User Enrolled") || status.equals("User Unenrolled") 
                                ? HttpStatus.OK : HttpStatus.CONFLICT;
        return new ResponseEntity<>(response, httpStatus);
    }
}
