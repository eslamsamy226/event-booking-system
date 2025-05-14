package com.areeb.event_booking_system_backend.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final String uploadDir = "uploads";

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveImage(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path imagePath = Paths.get(uploadDir, fileName);
        Files.createDirectories(imagePath.getParent());
        Files.copy(file.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }


}

