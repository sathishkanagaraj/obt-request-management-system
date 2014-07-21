package com.cts.internal.project.resource_tracking.web.adapter;

import com.cts.internal.project.resource_tracking.data.model.ResourceDetails;
import com.cts.internal.project.resource_tracking.web.viewobject.ResourceDetailsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResourceDetailsAdapter {
    @Autowired
    private ResourceDetails resourceDetails;

    public ResourceDetails buildResourceDetails(ResourceDetailsView resourceDetailsView) {
        resourceDetails.setObtid(resourceDetailsView.getObtid());
        resourceDetails.setLevel(resourceDetailsView.getLevel());
        resourceDetails.setRdate(resourceDetailsView.getRdate());
        resourceDetails.setResponsibility(resourceDetailsView.getResponsibility());
        resourceDetails.setRole(resourceDetailsView.getRole());
        resourceDetails.setTechnology(resourceDetailsView.getTechnology());
        resourceDetails.setType(resourceDetailsView.getType());
        return resourceDetails;
    }

    public ResourceDetailsView buildResourceDetailsView(ResourceDetails resourceDetails) {
        ResourceDetailsView resourceDetailsView = new ResourceDetailsView();
        resourceDetailsView.setObtid(resourceDetails.getObtid());
        resourceDetailsView.setLevel(resourceDetails.getLevel());
        resourceDetailsView.setRdate(resourceDetails.getRdate());
        resourceDetailsView.setResponsibility(resourceDetails.getResponsibility());
        resourceDetailsView.setRole(resourceDetails.getRole());
        resourceDetailsView.setTechnology(resourceDetails.getTechnology());
        resourceDetailsView.setType(resourceDetails.getType());
        return resourceDetailsView;
    }
}
