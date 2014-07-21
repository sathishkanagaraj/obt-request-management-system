package com.cts.internal.project.resource_tracking.web.controller;

import com.cts.internal.project.resource_tracking.business.DetailsNotFoundBOException;
import com.cts.internal.project.resource_tracking.business.IResourceTrackingBO;
import com.cts.internal.project.resource_tracking.data.model.ProjectDetails;
import com.cts.internal.project.resource_tracking.web.viewobject.ProjectDetailsView;
import com.cts.internal.project.resource_tracking.web.viewobject.ResourceDetailsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SearchController {
    @Autowired
    private IResourceTrackingBO iResourceTrackingBO;
    private ResourceDetailsView resourceDetailsView = new ResourceDetailsView();
    private String obtcode;

    @RequestMapping("/search")
    public ModelAndView search() {
        List<ProjectDetails> obtidList;
        ModelAndView modelAndView = new ModelAndView("search-request");
        modelAndView.addObject("resourceDetailsView", resourceDetailsView);
        obtidList = iResourceTrackingBO.searchAllProjectDetails();
        modelAndView.addObject("ProjectDetails", obtidList);
        return modelAndView;
    }

    @RequestMapping(value = "/search_resource_details")
    public ModelAndView searchResourceDetails(@ModelAttribute("ResourceDetailsView") ResourceDetailsView resourceDetailsView) {
        ModelAndView modelAndView = new ModelAndView("search-resource-details-success");
        obtcode = resourceDetailsView.getObtid();
        ResourceDetailsView resourceDetailsView1;
        try {
            resourceDetailsView1 = iResourceTrackingBO.searchResourceDetails(obtcode);
        } catch (DetailsNotFoundBOException e) {
            throw new DetailsNotFoundControllerException(e.getMessage());
        }
        modelAndView.addObject("resource_details_view", resourceDetailsView1);
        return modelAndView;
    }

    @ExceptionHandler(DetailsNotFoundControllerException.class)
    public ModelAndView handleCustomException(DetailsNotFoundControllerException e) {
        ModelAndView model = new ModelAndView("search-request");
        model.addObject("resourceDetailsView", resourceDetailsView);
        model.addObject("obtid_error", e.getMessage());
        return model;
    }

    @RequestMapping("/search_project_details")
    public ModelAndView searchProjectDetails() {
        ModelAndView modelAndView = new ModelAndView("search-project-details-success");
        ProjectDetailsView projectDetailsView;
        projectDetailsView = iResourceTrackingBO.searchDetails(obtcode);
        modelAndView.addObject("project_details_view", projectDetailsView);
        return modelAndView;
    }
}
