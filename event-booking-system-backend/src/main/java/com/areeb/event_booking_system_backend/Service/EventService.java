package com.areeb.event_booking_system_backend.Service;

import com.areeb.event_booking_system_backend.Model.Category;
import com.areeb.event_booking_system_backend.Model.Event;
import com.areeb.event_booking_system_backend.Model.User;
import com.areeb.event_booking_system_backend.Payload.EventRequestDTO;
import com.areeb.event_booking_system_backend.Payload.EventResponseDTO;
import com.areeb.event_booking_system_backend.Repository.CategoryRepository;
import com.areeb.event_booking_system_backend.Repository.EventRepository;
import com.areeb.event_booking_system_backend.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final ImageService imageService;

    public EventResponseDTO createEvent(EventRequestDTO dto) {
        Set<Category> categories = fetchCategoriesByIds(dto.getCategoryIds());


        Event event = new Event();
        event.setName(dto.getName());
        event.setDescription(dto.getDescription());
        event.setVenue(dto.getVenue());
        event.setPrice(dto.getPrice());
        event.setEventDate(dto.getEventDate());
        event.setCategories(categories);
        event.setImageUrl(dto.getImageUrl());

        Event savedEvent = eventRepository.save(event);
        return mapToDTO(savedEvent);
    }

    public Map<String, Object> getAllEvents(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by("eventDate").descending());
        Page<Event> eventPage = eventRepository.findAll(pageable);

        List<EventResponseDTO> events = eventPage.getContent()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("eventNo", eventPage.getTotalElements());
        response.put("totalPages", eventPage.getTotalPages());
        response.put("currentPage", eventPage.getNumber());
        response.put("events", events);

        return response;
    }

    public EventResponseDTO getEventById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Event not found"));
        return mapToDTO(event);
    }

    @Transactional
    public EventResponseDTO updateEvent(Long id, EventRequestDTO dto) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Event not found"));

        Set<Category> categories = fetchCategoriesByIds(dto.getCategoryIds());

        event.setName(dto.getName());
        event.setDescription(dto.getDescription());
        event.setVenue(dto.getVenue());
        event.setPrice(dto.getPrice());
        event.setEventDate(dto.getEventDate());
        event.setCategories(categories);
        event.setImageUrl(dto.getImageUrl());

        Event updatedEvent = eventRepository.save(event);
        return mapToDTO(updatedEvent);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    @Transactional
    public EventResponseDTO bookEvent(Long eventId, Long userId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new NoSuchElementException("Event not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        if (!event.getBookedUsers().contains(user)) {
            event.getBookedUsers().add(user);
            eventRepository.save(event);
        }

        return mapToDTO(event);
    }

    // === Helper Methods ===

    private Set<Category> fetchCategoriesByIds(Set<Long> ids) {
        return ids.stream()
                .map(id -> categoryRepository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("Category not found: " + id)))
                .collect(Collectors.toSet());
    }

    private EventResponseDTO mapToDTO(Event event) {
        return EventResponseDTO.builder()
                .id(event.getId())
                .name(event.getName())
                .attendees(event.getBookedUsers().size())
                .description(event.getDescription())
                .venue(event.getVenue())
                .price(event.getPrice())
                .imageUrl(event.getImageUrl())
                .eventDate(event.getEventDate())
                .categories(event.getCategories()
                        .stream()
                        .map(cat -> cat.getName().name())
                        .collect(Collectors.toSet()))
                .build();
    }
}
