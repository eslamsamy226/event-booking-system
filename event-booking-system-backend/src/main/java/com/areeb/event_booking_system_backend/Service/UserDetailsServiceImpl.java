package com.areeb.event_booking_system_backend.Service;

import com.areeb.event_booking_system_backend.Model.Enum.ERole;
import com.areeb.event_booking_system_backend.Model.Role;
import com.areeb.event_booking_system_backend.Model.User;
import com.areeb.event_booking_system_backend.Repository.RoleRepository;
import com.areeb.event_booking_system_backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User profile = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + email));

        return UserDetailsImpl.build(profile);
    }

    public void initAdmin() {
        Optional<User> admin = userRepository.findByEmail("admin@areeb.com");
        if (admin.isEmpty()) {
            User user = new User(
                    "Admin", "Admin", "admin@areeb.com", passwordEncoder.encode("admin"),
                    true
            );
            Set<Role> roles = new HashSet<>();
            Role studentRepRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(studentRepRole);
            user.setRoles(roles);
            saveUser(user);
        }
    }
}
