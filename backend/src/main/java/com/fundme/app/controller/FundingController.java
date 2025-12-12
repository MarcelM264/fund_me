package com.fundme.app.controller;

import com.fundme.app.dto.FundingRequest;
import com.fundme.app.model.Funding;
import com.fundme.app.service.FundingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fundings")
@CrossOrigin(origins = "http://localhost:4200")
public class FundingController {
    
    @Autowired
    private FundingService fundingService;
    
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Funding>> getFundingsByProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(fundingService.getFundingsByProject(projectId));
    }
    
    @GetMapping("/supporter/{supporterId}")
    public ResponseEntity<List<Funding>> getFundingsBySupporter(@PathVariable Long supporterId) {
        return ResponseEntity.ok(fundingService.getFundingsBySupporter(supporterId));
    }
    
    @PostMapping("/project/{projectId}")
    public ResponseEntity<Funding> createFunding(@PathVariable Long projectId,
                                                  @Valid @RequestBody FundingRequest fundingRequest,
                                                  Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
        Funding funding = new Funding();
        funding.setAmount(fundingRequest.getAmount());
        funding.setMessage(fundingRequest.getMessage());
        
        Funding createdFunding = fundingService.createFunding(projectId, funding, authentication.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFunding);
    }
}
