package com.cts.internal.project.resource_tracking.data.repository;

import com.cts.internal.project.resource_tracking.data.model.ResourceDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceDetailsRepo extends JpaRepository<ResourceDetails, String> {
}
