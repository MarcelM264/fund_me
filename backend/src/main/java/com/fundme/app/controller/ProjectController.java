package com.fundme.app.controller;

import com.fundme.app.dto.ProjectRequest;
import com.fundme.app.model.Project;
import com.fundme.app.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectController {
    
    @Autowired
    private ProjectService projectService;
    
    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }
    
    @GetMapping("/active")
    public ResponseEntity<List<Project>> getActiveProjects() {
        return ResponseEntity.ok(projectService.getActiveProjects());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }
    
    @GetMapping("/creator/{creatorId}")
    public ResponseEntity<List<Project>> getProjectsByCreator(@PathVariable Long creatorId) {
        return ResponseEntity.ok(projectService.getProjectsByCreator(creatorId));
    }
    
    @PostMapping
    public ResponseEntity<Project> createProject(@Valid @RequestBody ProjectRequest projectRequest,
                                                  Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
        Project project = new Project();
        project.setTitle(projectRequest.getTitle());
        project.setDescription(projectRequest.getDescription());
        project.setFundingGoal(projectRequest.getFundingGoal());
        project.setDeadline(projectRequest.getDeadline());
        
        Project createdProject = projectService.createProject(project, authentication.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id,
                                                  @Valid @RequestBody ProjectRequest projectRequest,
                                                  Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
        Project projectDetails = new Project();
        projectDetails.setTitle(projectRequest.getTitle());
        projectDetails.setDescription(projectRequest.getDescription());
        projectDetails.setFundingGoal(projectRequest.getFundingGoal());
        projectDetails.setDeadline(projectRequest.getDeadline());
        
        Project updatedProject = projectService.updateProject(id, projectDetails, authentication.getName());
        return ResponseEntity.ok(updatedProject);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
        projectService.deleteProject(id, authentication.getName());
        return ResponseEntity.noContent().build();
    }
}
