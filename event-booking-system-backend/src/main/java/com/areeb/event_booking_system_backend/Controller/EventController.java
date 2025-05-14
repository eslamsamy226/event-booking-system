package com.areeb.event_booking_system_backend.Controller;

import com.areeb.event_booking_system_backend.Payload.EventRequestDTO;
import com.areeb.event_booking_system_backend.Payload.EventResponseDTO;
import com.areeb.event_booking_system_backend.Service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<EventResponseDTO> createEvent(
            @RequestBody EventRequestDTO dto
    ) {
        EventResponseDTO createdEvent = eventService.createEvent(dto);
        return ResponseEntity.ok(createdEvent);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(eventService.getAllEvents(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<EventResponseDTO> updateEvent(
            @PathVariable Long id,
            @RequestBody EventRequestDTO dto
    ) {

        return ResponseEntity.ok(eventService.updateEvent(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{eventId}/book/{userId}")
    public ResponseEntity<EventResponseDTO> bookEvent(@PathVariable Long eventId, @PathVariable Long userId) {
        return ResponseEntity.ok(eventService.bookEvent(eventId, userId));
    }
}
