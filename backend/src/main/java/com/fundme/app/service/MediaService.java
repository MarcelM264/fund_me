package com.fundme.app.service;

import com.fundme.app.model.Media;
import com.fundme.app.model.Project;
import com.fundme.app.repository.MediaRepository;
import com.fundme.app.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class MediaService {
    
    @Autowired
    private MediaRepository mediaRepository;
    
    @Autowired
    private ProjectRepository projectRepository;
    
    private final String uploadDir = "uploads/";
    
    public List<Media> getMediaByProject(Long projectId) {
        return mediaRepository.findByProjectId(projectId);
    }
    
    @Transactional
    public Media uploadMedia(Long projectId, MultipartFile file, Media.MediaType mediaType) throws IOException {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));
        
        // Create upload directory if it doesn't exist
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        
        // Generate unique filename
        String originalFileName = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + "_" + originalFileName;
        Path filePath = uploadPath.resolve(fileName);
        
        // Save file
        Files.write(filePath, file.getBytes());
        
        // Create media entity
        Media media = new Media();
        media.setFileName(fileName);
        media.setFileType(file.getContentType());
        media.setMediaType(mediaType);
        media.setUrl("/uploads/" + fileName);
        media.setProject(project);
        
        return mediaRepository.save(media);
    }
    
    @Transactional
    public void deleteMedia(Long id) {
        Media media = mediaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Media not found with id: " + id));
        
        try {
            // Delete file from filesystem
            Path filePath = Paths.get(uploadDir + media.getFileName());
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete file: " + e.getMessage());
        }
        
        mediaRepository.delete(media);
    }
}
