package com.cts.internal.project.resource_tracking.data;

import com.cts.internal.project.resource_tracking.data.model.ProjectDetails;
import com.cts.internal.project.resource_tracking.data.model.ResourceDetails;

import java.util.List;

public interface IResourceTrackingDO {
    public void insertResourceDetails(ResourceDetails resourceDetails);

    public void insertProjectDetails(ProjectDetails projectDetails);

    public List<ProjectDetails> searchAllDetails();

    public void deleteRequest(String obtid) throws DetailsNotFoundException;

    public void deleteRequestList(String[] obtid);

    public ResourceDetails checkObtid(String obtid);

    public ResourceDetails searchResourceDetails(String obtcode) throws DetailsNotFoundException;

    public ProjectDetails searchProjectDetails(String obtcode);
}
