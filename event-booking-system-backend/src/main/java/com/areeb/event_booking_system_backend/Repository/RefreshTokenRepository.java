package com.areeb.event_booking_system_backend.Repository;


import com.areeb.event_booking_system_backend.Model.RefreshToken;
import com.areeb.event_booking_system_backend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);



    Optional<RefreshToken> findByUser(User user);

    @Transactional
    @Modifying
    @Query("DELETE FROM RefreshToken r WHERE r.user.id = :userId")
    void deleteByUser(@Param("userId") Long userId);
}
