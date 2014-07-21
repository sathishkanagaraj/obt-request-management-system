package com.cts.internal.project.resource_tracking.web.adapter;

import com.cts.internal.project.resource_tracking.data.model.ProjectDetails;
import com.cts.internal.project.resource_tracking.web.viewobject.ProjectDetailsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectDetailsAdapter {
    @Autowired
    private ProjectDetails projectDetails;


    public ProjectDetails buildProjectDetails(ProjectDetailsView projectDetailsView) {
        projectDetails.setObtid(projectDetailsView.getObtid());
        projectDetails.setDuration(projectDetailsView.getDuration());
        projectDetails.setDutch(projectDetailsView.getDutch());
        projectDetails.setEndDate(projectDetailsView.getEndDate());
        projectDetails.setJdescription(projectDetailsView.getJdescription());
        projectDetails.setLocation(projectDetailsView.getLocation());
        projectDetails.setPdescription(projectDetailsView.getPdescription());
        projectDetails.setPname(projectDetailsView.getPname());
        projectDetails.setPortfolio(projectDetailsView.getPortfolio());
        projectDetails.setPractice(projectDetailsView.getPractice());
        projectDetails.setStartDate(projectDetailsView.getStartDate());
        return projectDetails;
    }

    public ProjectDetailsView buildProjectDetailsView(ProjectDetails projectDetails) {
        ProjectDetailsView projectDetailsView = new ProjectDetailsView();
        projectDetailsView.setObtid(projectDetails.getObtid());
        projectDetailsView.setDuration(projectDetails.getDuration());
        projectDetailsView.setDutch(projectDetails.getDutch());
        projectDetailsView.setEndDate(projectDetails.getEndDate());
        projectDetailsView.setJdescription(projectDetails.getJdescription());
        projectDetailsView.setLocation(projectDetails.getLocation());
        projectDetailsView.setPdescription(projectDetails.getPdescription());
        projectDetailsView.setPname(projectDetails.getPname());
        projectDetailsView.setPortfolio(projectDetails.getPortfolio());
        projectDetailsView.setStartDate(projectDetails.getStartDate());
        projectDetailsView.setPractice(projectDetails.getPractice());
        return projectDetailsView;
    }
}
