package com.fundme.app.repository;

import com.fundme.app.model.Project;
import com.fundme.app.model.Project.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByStatus(ProjectStatus status);
    List<Project> findByCreatorId(Long creatorId);
    List<Project> findByStatusOrderByCreatedAtDesc(ProjectStatus status);
    List<Project> findAllByOrderByCreatedAtDesc();
}
