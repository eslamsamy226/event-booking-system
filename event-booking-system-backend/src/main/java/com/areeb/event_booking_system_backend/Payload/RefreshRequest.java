package com.areeb.event_booking_system_backend.Payload;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RefreshRequest {


    @NotBlank
    private String refreshToken;

}
