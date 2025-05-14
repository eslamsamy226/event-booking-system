package com.areeb.event_booking_system_backend.Payload;


import lombok.*;

import lombok.*;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventResponseDTO {
    private Long id;
    private Integer attendees;
    private String name;
    private String description;
    private String venue;
    private double price;
    private String imageUrl;
    private Date eventDate;
    private Set<String> categories;
}
