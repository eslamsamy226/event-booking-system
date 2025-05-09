package com.areeb.event_booking_system_backend.setup;

import com.areeb.event_booking_system_backend.Model.Enum.ERole;
import com.areeb.event_booking_system_backend.Model.Role;
import com.areeb.event_booking_system_backend.Model.User;
import com.areeb.event_booking_system_backend.Repository.RoleRepository;
import com.areeb.event_booking_system_backend.Repository.UserRepository;
import com.areeb.event_booking_system_backend.Service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class AdminInit implements CommandLineRunner {



    @Override
    public void run(String... args) throws Exception {

    }
}
