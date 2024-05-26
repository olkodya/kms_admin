package com.example.kms.controller;

import com.example.kms.entity.Image;
import com.example.kms.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:8081", "https://kmsadmin-production.up.railway.app"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ImageController {
    private final ImageService service;
    @Operation(summary = "Image upload", description = "Returns image data after successful upload")
    @PostMapping("/images")
    public ResponseEntity<Image> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        if (file.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(service.uploadImage(file), HttpStatus.CREATED);
    }
    @Operation(summary = "Get image by id", description = "Returns image")
    @GetMapping("/images/{id}")
    public ResponseEntity<?> getImageById(@PathVariable Integer id) {
        return new ResponseEntity<>(service.getImageById(id), HttpStatus.OK);
    }

    @Operation(summary = "Get all images", description = "Returns images data")
    @GetMapping("/images")
    public ResponseEntity<List<Image>> getAllImages(){
        var images = service.getAllImages();
        if (images.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @Operation(summary = "Delete image by id")
    @DeleteMapping("/images/{id}")
    public ResponseEntity<HttpStatus> deleteImageById(@PathVariable Integer id) {
        service.deleteImageById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
