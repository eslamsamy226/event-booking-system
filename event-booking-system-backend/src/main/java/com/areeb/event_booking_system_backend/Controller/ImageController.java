package com.areeb.event_booking_system_backend.Controller;

import com.areeb.event_booking_system_backend.Service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PreAuthorize("hasRole('ROLE_ADMIN') and isAuthenticated()")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        String imageUrl = imageService.saveImage(file);
        return ResponseEntity.ok(Collections.singletonMap("imageUrl", imageUrl));
    }


    @GetMapping("/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) throws IOException {
        Path imagePath = Paths.get("uploads").resolve(imageName).normalize();

        UrlResource resource = new UrlResource(imagePath.toUri());

        // Check if the image exists and is readable
        if (!resource.exists() || !resource.isReadable()) {
            return ResponseEntity.notFound().build();
        }

        String contentType = Files.probeContentType(imagePath);
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body((Resource) resource);
    }
}
