package com.areeb.event_booking_system_backend.Payload;

import lombok.*;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequestDTO {
    private String name;
    private String description;
    private String venue;
    private double price;
    private Date eventDate;
    private Set<Long> categoryIds;
    private String imageUrl;
}
