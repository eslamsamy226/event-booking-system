package com.areeb.event_booking_system_backend.Controller;

import com.areeb.event_booking_system_backend.Payload.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping
    public ResponseEntity<?> check() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        UserDetails principal = (UserDetails) authentication.getPrincipal();
//
//        return ResponseEntity.ok().body(new MessageResponse("Your are logged in, Here is your info : "+principal.getUsername()));
        return ResponseEntity.ok().body(new MessageResponse("WORKING ............"));
    }
}
