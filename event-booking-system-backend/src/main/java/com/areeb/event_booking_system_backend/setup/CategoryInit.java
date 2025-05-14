package com.areeb.event_booking_system_backend.setup;

import com.areeb.event_booking_system_backend.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CategoryInit implements CommandLineRunner {
    @Autowired
    private CategoryService categoryService;


    @Override
    public void run(String... args) {
        categoryService.initializeCategories();
    }
}
