package com.cts.internal.project.resource_tracking.business;

import com.cts.internal.project.resource_tracking.data.model.ProjectDetails;
import com.cts.internal.project.resource_tracking.data.model.ResourceDetails;
import com.cts.internal.project.resource_tracking.exception.ExistingObtidException;
import com.cts.internal.project.resource_tracking.web.viewobject.ProjectDetailsView;
import com.cts.internal.project.resource_tracking.web.viewobject.ResourceDetailsView;

import java.util.List;

public interface IResourceTrackingBO {
    public void existingResourceDetails(String obtid) throws ExistingObtidException;

    public void addProjectDetails(ProjectDetails projectDetails, ResourceDetails resourceDetails);

    public ProjectDetailsView searchDetails(String obtcode);

    public List<ProjectDetails> searchAllProjectDetails();

    public void deleteRequestBO(String obtid) throws DetailsNotFoundBOException;

    public void deleteRequestListBO(String[] obtid);

    ResourceDetailsView searchResourceDetails(String obtcode) throws DetailsNotFoundBOException;

}
