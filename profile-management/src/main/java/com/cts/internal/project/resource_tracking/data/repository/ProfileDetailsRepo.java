package com.cts.internal.project.resource_tracking.data.repository;

import com.cts.internal.project.resource_tracking.data.model.ProfileDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileDetailsRepo extends JpaRepository<ProfileDetails, Integer> {
}
