package com.cts.internal.project.resource_tracking.data.repository;

import com.cts.internal.project.resource_tracking.data.model.ProjectDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectDetailsRepo extends JpaRepository<ProjectDetails, String> {
}
