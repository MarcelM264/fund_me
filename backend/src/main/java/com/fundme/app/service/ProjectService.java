package com.fundme.app.service;

import com.fundme.app.model.Project;
import com.fundme.app.model.User;
import com.fundme.app.repository.ProjectRepository;
import com.fundme.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProjectService {
    
    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public List<Project> getAllProjects() {
        return projectRepository.findAllByOrderByCreatedAtDesc();
    }
    
    public List<Project> getActiveProjects() {
        return projectRepository.findByStatusOrderByCreatedAtDesc(Project.ProjectStatus.ACTIVE);
    }
    
    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
    }
    
    public List<Project> getProjectsByCreator(Long creatorId) {
        return projectRepository.findByCreatorId(creatorId);
    }
    
    @Transactional
    public Project createProject(Project project, String username) {
        User creator = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
        
        project.setCreator(creator);
        project.setCurrentFunding(BigDecimal.ZERO);
        project.setStatus(Project.ProjectStatus.ACTIVE);
        
        return projectRepository.save(project);
    }
    
    @Transactional
    public Project updateProject(Long id, Project projectDetails, String username) {
        Project project = getProjectById(id);
        
        // Check if the user is the creator
        if (!project.getCreator().getUsername().equals(username)) {
            throw new RuntimeException("Not authorized to update this project");
        }
        
        project.setTitle(projectDetails.getTitle());
        project.setDescription(projectDetails.getDescription());
        project.setFundingGoal(projectDetails.getFundingGoal());
        project.setDeadline(projectDetails.getDeadline());
        
        return projectRepository.save(project);
    }
    
    @Transactional
    public void deleteProject(Long id, String username) {
        Project project = getProjectById(id);
        
        // Check if the user is the creator
        if (!project.getCreator().getUsername().equals(username)) {
            throw new RuntimeException("Not authorized to delete this project");
        }
        
        projectRepository.delete(project);
    }
    
    @Transactional
    public void updateProjectFunding(Long projectId, BigDecimal amount) {
        Project project = getProjectById(projectId);
        project.setCurrentFunding(project.getCurrentFunding().add(amount));
        
        // Check if funding goal is reached
        if (project.getCurrentFunding().compareTo(project.getFundingGoal()) >= 0) {
            project.setStatus(Project.ProjectStatus.FUNDED);
        }
        
        projectRepository.save(project);
    }
}
