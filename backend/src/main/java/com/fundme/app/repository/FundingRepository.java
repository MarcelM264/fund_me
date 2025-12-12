package com.fundme.app.repository;

import com.fundme.app.model.Funding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FundingRepository extends JpaRepository<Funding, Long> {
    List<Funding> findByProjectId(Long projectId);
    List<Funding> findBySupporterId(Long supporterId);
}
