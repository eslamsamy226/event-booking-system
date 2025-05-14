package com.areeb.event_booking_system_backend.Service;


import com.areeb.event_booking_system_backend.Model.Category;
import com.areeb.event_booking_system_backend.Model.Enum.ECategory;
import com.areeb.event_booking_system_backend.Model.Role;
import com.areeb.event_booking_system_backend.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void saveAll(List<Category> categories) {
        categoryRepository.saveAll(categories);
    }

    @Transactional
    public void initializeCategories() {
        long count = categoryRepository.count();
        if (count == 0) {
            List<Category> categories=new ArrayList<>();
            for (ECategory eCategory : ECategory.values()) {
                Category category = new Category();
                category.setName(eCategory);
               categories.add(category);
            }
            categoryRepository.saveAll(categories);
            System.out.println("Category table initialized with default categories.");
        }
    }
}

