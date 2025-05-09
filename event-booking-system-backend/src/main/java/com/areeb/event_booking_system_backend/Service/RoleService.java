package com.areeb.event_booking_system_backend.Service;


import com.areeb.event_booking_system_backend.Model.Enum.ERole;
import com.areeb.event_booking_system_backend.Model.Role;
import com.areeb.event_booking_system_backend.Repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public void initializeRoles() {
        // Check if the Role table is empty
        long count = roleRepository.count();

        List<Role> roles=new ArrayList<>();
        if (count == 0) {
            for(ERole role : ERole.values()){
                Role newRole = new Role(role);
                roles.add(newRole);
            }
            roleRepository.saveAll(roles);
            System.out.println("Role table initialized with default roles");
        }
    }
}
