package com.areeb.event_booking_system_backend.Service;


import com.areeb.event_booking_system_backend.Model.RefreshToken;
import com.areeb.event_booking_system_backend.Model.User;
import com.areeb.event_booking_system_backend.Repository.RefreshTokenRepository;
import com.areeb.event_booking_system_backend.Security.Jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Value("${jwt.refreshExpirationMs}")
    private  long refreshTokenDurationMs; // 1 Month

    public RefreshToken createRefreshToken(User user) {
        String token = jwtUtils.generateRefreshToken(user.getEmail());
        RefreshToken refreshToken = new RefreshToken(
                user,
                token,
                new Date((new Date()).getTime() + refreshTokenDurationMs)
        );
        return refreshTokenRepository.save(refreshToken);
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }
    public Optional<RefreshToken> findByUser(User user) {
        return refreshTokenRepository.findByUser(user);
    }

    public void deleteByUser(User user) {
        refreshTokenRepository.deleteByUser(user.getId());
    }

    public boolean isExpired(RefreshToken refreshToken) {
        return refreshToken.getExpiryDate().before(new Date());
    }
}
