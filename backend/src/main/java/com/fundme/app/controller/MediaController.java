package com.fundme.app.controller;

import com.fundme.app.model.Media;
import com.fundme.app.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/media")
@CrossOrigin(origins = "http://localhost:4200")
public class MediaController {
    
    @Autowired
    private MediaService mediaService;
    
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Media>> getMediaByProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(mediaService.getMediaByProject(projectId));
    }
    
    @PostMapping("/project/{projectId}/upload")
    public ResponseEntity<Media> uploadMedia(@PathVariable Long projectId,
                                              @RequestParam("file") MultipartFile file,
                                              @RequestParam("mediaType") Media.MediaType mediaType) {
        try {
            Media media = mediaService.uploadMedia(projectId, file, mediaType);
            return ResponseEntity.status(HttpStatus.CREATED).body(media);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedia(@PathVariable Long id) {
        mediaService.deleteMedia(id);
        return ResponseEntity.noContent().build();
    }
}
