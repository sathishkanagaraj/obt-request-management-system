package com.cts.internal.project.resource_tracking.business;

import com.cts.internal.project.resource_tracking.data.DetailsNotFoundException;
import com.cts.internal.project.resource_tracking.data.IResourceTrackingDO;
import com.cts.internal.project.resource_tracking.data.model.ProjectDetails;
import com.cts.internal.project.resource_tracking.data.model.ResourceDetails;
import com.cts.internal.project.resource_tracking.exception.ExistingObtidException;
import com.cts.internal.project.resource_tracking.web.adapter.ProjectDetailsAdapter;
import com.cts.internal.project.resource_tracking.web.adapter.ResourceDetailsAdapter;
import com.cts.internal.project.resource_tracking.web.viewobject.ProjectDetailsView;
import com.cts.internal.project.resource_tracking.web.viewobject.ResourceDetailsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResourceTrackingBO implements IResourceTrackingBO {
    @Autowired
    private IResourceTrackingDO iResourceTrackingDO;

    @Autowired
    private ProjectDetails projectDetails;

    @Autowired
    private ProjectDetailsAdapter projectDetailsAdapter;

    @Autowired
    private ResourceDetails resourceDetails;

    @Autowired
    private ResourceDetailsAdapter resourceDetailsAdapter;

    @Override
    public void existingResourceDetails(String obtid) throws ExistingObtidException {
        ResourceDetails resourceDetails = iResourceTrackingDO.checkObtid(obtid);
        if (resourceDetails != null) {
            throw new ExistingObtidException("ObtId already Exists");
        }
    }

    @Override
    public void addProjectDetails(ProjectDetails projectDetails, ResourceDetails resourceDetails) {
        iResourceTrackingDO.insertResourceDetails(resourceDetails);
        iResourceTrackingDO.insertProjectDetails(projectDetails);
    }

    @Override
    public ProjectDetailsView searchDetails(String obtcode) {
        ProjectDetailsView projectDetailsView;
        projectDetails = iResourceTrackingDO.searchProjectDetails(obtcode);
        projectDetailsView = projectDetailsAdapter.buildProjectDetailsView(projectDetails);
        return projectDetailsView;
    }

    @Override
    public ResourceDetailsView searchResourceDetails(String obtcode) throws DetailsNotFoundBOException {
        ResourceDetailsView resourceDetailsView;
        try {
            resourceDetails = iResourceTrackingDO.searchResourceDetails(obtcode);
        } catch (DetailsNotFoundException e) {
            throw new DetailsNotFoundBOException(e.getMessage());
        }
        resourceDetailsView = resourceDetailsAdapter.buildResourceDetailsView(resourceDetails);
        return resourceDetailsView;
    }

    @Override
    public List<ProjectDetails> searchAllProjectDetails() {
        List<ProjectDetails> obtidList;
        obtidList = iResourceTrackingDO.searchAllDetails();
        return obtidList;
    }

    @Override
    public void deleteRequestBO(String obtid) throws DetailsNotFoundBOException {
        try {
            iResourceTrackingDO.deleteRequest(obtid);
        } catch (DetailsNotFoundException e) {
            throw new DetailsNotFoundBOException(e.getMessage());
        }
    }

    @Override
    public void deleteRequestListBO(String[] obtid) {
        iResourceTrackingDO.deleteRequestList(obtid);
    }
}
