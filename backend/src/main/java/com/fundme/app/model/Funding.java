package com.fundme.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "fundings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Funding {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private BigDecimal amount;
    
    private String message;
    
    @Column(name = "funded_at")
    private LocalDateTime fundedAt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supporter_id", nullable = false)
    private User supporter;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
    
    @PrePersist
    protected void onCreate() {
        fundedAt = LocalDateTime.now();
    }
}
