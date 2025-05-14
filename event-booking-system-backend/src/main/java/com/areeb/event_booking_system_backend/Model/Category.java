package com.areeb.event_booking_system_backend.Model;

import com.areeb.event_booking_system_backend.Model.Enum.ECategory;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private ECategory name;
}
