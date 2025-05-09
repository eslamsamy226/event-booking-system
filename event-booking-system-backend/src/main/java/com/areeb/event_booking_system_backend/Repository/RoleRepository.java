package com.areeb.event_booking_system_backend.Repository;


import com.areeb.event_booking_system_backend.Model.Enum.ERole;
import com.areeb.event_booking_system_backend.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
