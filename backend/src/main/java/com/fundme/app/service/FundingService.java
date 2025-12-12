package com.fundme.app.service;

import com.fundme.app.model.Funding;
import com.fundme.app.model.Project;
import com.fundme.app.model.User;
import com.fundme.app.repository.FundingRepository;
import com.fundme.app.repository.ProjectRepository;
import com.fundme.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FundingService {
    
    @Autowired
    private FundingRepository fundingRepository;
    
    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ProjectService projectService;
    
    public List<Funding> getFundingsByProject(Long projectId) {
        return fundingRepository.findByProjectId(projectId);
    }
    
    public List<Funding> getFundingsBySupporter(Long supporterId) {
        return fundingRepository.findBySupporterId(supporterId);
    }
    
    @Transactional
    public Funding createFunding(Long projectId, Funding funding, String username) {
        User supporter = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
        
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));
        
        if (project.getStatus() != Project.ProjectStatus.ACTIVE) {
            throw new RuntimeException("Project is not active for funding");
        }
        
        funding.setSupporter(supporter);
        funding.setProject(project);
        
        Funding savedFunding = fundingRepository.save(funding);
        
        // Update project funding
        projectService.updateProjectFunding(projectId, funding.getAmount());
        
        return savedFunding;
    }
}
