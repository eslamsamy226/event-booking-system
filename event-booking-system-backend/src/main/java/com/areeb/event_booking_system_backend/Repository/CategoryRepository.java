package com.areeb.event_booking_system_backend.Repository;



import com.areeb.event_booking_system_backend.Model.Category;
import com.areeb.event_booking_system_backend.Model.Enum.ECategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(ECategory name);
}
