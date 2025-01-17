package com.med.MedConnect.Controller;

import com.med.MedConnect.Model.Event.Event;
import com.med.MedConnect.Model.Event.EventRepo;
import com.med.MedConnect.Model.Event.EventDetails;
import com.med.MedConnect.Model.Event.EventDetailsRepo;
import com.med.MedConnect.Model.Event.publicEventScheduler;
import com.med.MedConnect.Model.Event.PrivateEventScheduler;
import com.med.MedConnect.Model.Event.RecurringEventScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventRepo eventRepository;

    @Autowired
    private EventDetailsRepo eventDetailsRepository;

    // GET: Retrieve all events
    @GetMapping
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // GET: Retrieve a specific event by ID
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable int id) {
        Optional<Event> event = eventRepository.findById(id);
        return event.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST: Create a new event
    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event newEvent) {
        try {
            Event savedEvent = eventRepository.save(newEvent);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEvent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // PUT: Update an existing event
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable int id, @RequestBody Event updatedEvent) {
        return eventRepository.findById(id).map(event -> {
            event.setName(updatedEvent.getName());
            event.setDescription(updatedEvent.getDescription());
            event.setVolunteerRoles(updatedEvent.getVolunteerRoles());
            Event savedEvent = eventRepository.save(event);
            return ResponseEntity.ok(savedEvent);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST: Schedule an event by ID with event type
    @PostMapping("/{id}/schedule")
    public ResponseEntity<Map<String, Object>> scheduleEvent(
            @PathVariable int id,
            @RequestParam String eventType) { // Accepts event type as a query parameter
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();

            // Determine the scheduler based on eventType
            switch (eventType.toLowerCase()) {
                case "public":
                    new publicEventScheduler().scheduleEvent(event);
                    break;
                case "private":
                    new PrivateEventScheduler().scheduleEvent(event);
                    break;
                case "recurring":
                    new RecurringEventScheduler().scheduleEvent(event);
                    break;
                default:
                    return ResponseEntity.status(400).body(Map.of("error", "Invalid event type"));
            }

            // Update event description to reflect scheduling
            event.setDescription(event.getDescription() + " [Scheduled]");
            eventRepository.save(event);

            // Add event details to the EventDetails table
            EventDetails eventDetails = new EventDetails();
            eventDetails.setEvent(event);
            eventDetails.setAttendance("Pending"); // Default attendance status
            eventDetails.setTotalHours(0); // Default total hours
            eventDetailsRepository.save(eventDetails);

            return ResponseEntity.ok(Map.of(
                    "message", "Event scheduled successfully",
                    "event", event,
                    "eventDetails", eventDetails
            ));
        } else {
            return ResponseEntity.status(404).body(Map.of("error", "Event not found"));
        }
    }
}
