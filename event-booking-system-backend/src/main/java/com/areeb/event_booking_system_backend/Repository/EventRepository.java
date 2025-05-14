package com.areeb.event_booking_system_backend.Repository;

import com.areeb.event_booking_system_backend.Model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
