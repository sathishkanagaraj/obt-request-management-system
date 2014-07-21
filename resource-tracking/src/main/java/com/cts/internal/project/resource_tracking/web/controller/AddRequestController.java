package com.cts.internal.project.resource_tracking.web.controller;

import com.cts.internal.project.resource_tracking.business.IResourceTrackingBO;
import com.cts.internal.project.resource_tracking.data.model.ProjectDetails;
import com.cts.internal.project.resource_tracking.data.model.ResourceDetails;
import com.cts.internal.project.resource_tracking.exception.ExistingObtidException;
import com.cts.internal.project.resource_tracking.web.adapter.ProjectDetailsAdapter;
import com.cts.internal.project.resource_tracking.web.adapter.ResourceDetailsAdapter;
import com.cts.internal.project.resource_tracking.web.viewobject.ProjectDetailsView;
import com.cts.internal.project.resource_tracking.web.viewobject.ResourceDetailsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AddRequestController {

    @Autowired
    private ResourceDetails resourceDetails;

    @Autowired
    private ProjectDetails projectDetails;

    @Autowired
    private ProjectDetailsAdapter projectDetailsAdapter;

    @Autowired
    private IResourceTrackingBO iResourceTrackingBO;

    @Autowired
    private ResourceDetailsAdapter resourceDetailsAdapter;

    private ResourceDetailsView detailsView = new ResourceDetailsView();

    private ProjectDetailsView projectDetailsView = new ProjectDetailsView();

    private ResourceDetailsView resourceDetailsView = new ResourceDetailsView();


    @RequestMapping("/ResourceDetails")
    public ModelAndView resourceDetails() {
        ModelAndView modelAndView = new ModelAndView("resource-details");
        modelAndView.addObject("resourceDetailsView", resourceDetailsView);
        return modelAndView;
    }


    @RequestMapping(value = "/ProjectDetails")
    public ModelAndView projectDetails(@ModelAttribute("resourceDetailsView") @Valid ResourceDetailsView detailsView1, BindingResult bindingResult) throws ExistingObtidException {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("resource-details");
            if (bindingResult.getFieldError("obtid") != null) {
                modelAndView.addObject("obt_error", "OBT-ID must have 13 characters");
            }
            if (bindingResult.getFieldError("type") != null) {
                modelAndView.addObject("type_error", "Resource Type cannot be left blank");
            }
            modelAndView.addObject("resourceDetailsView", detailsView1);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("project-details");
            detailsView = detailsView1;
            ProjectDetailsView projectDetailsView1;
            if (detailsView1.getUpdate().isEmpty()) {
                iResourceTrackingBO.existingResourceDetails(detailsView1.getObtid());
                projectDetailsView.setObtid(detailsView1.getObtid());
                modelAndView.addObject("projectDetailsView", projectDetailsView);
            } else {
                projectDetailsView1 = iResourceTrackingBO.searchDetails(detailsView1.getObtid());
                modelAndView.addObject("projectDetailsView", projectDetailsView1);
            }

            return modelAndView;
        }
    }

    @ExceptionHandler(ExistingObtidException.class)
    public ModelAndView handleCustomException(ExistingObtidException ex) {

        ModelAndView model = new ModelAndView("resource-details");
        model.addObject("resourceDetailsView", resourceDetailsView);
        model.addObject("obtid_error", ex.getMessage());
        return model;

    }

    @RequestMapping(value = "/ResourceDetailsBack")
    public ModelAndView resourceDetailsBack() {
        ModelAndView modelAndView = new ModelAndView("resource-details");
        modelAndView.addObject("resourceDetailsView", detailsView);
        return modelAndView;
    }

    @RequestMapping(value = "/Success", method = RequestMethod.POST)
    public ModelAndView addRequestSuccess(@ModelAttribute("projectDetailsView") @Valid ProjectDetailsView projectDetailsView, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("project-details");
            modelAndView.addObject("projectDetailsView", projectDetailsView);
            return modelAndView;
        } else {
            projectDetails = projectDetailsAdapter.buildProjectDetails(projectDetailsView);
            resourceDetails = resourceDetailsAdapter.buildResourceDetails(detailsView);
            iResourceTrackingBO.addProjectDetails(projectDetails, resourceDetails);
            if (detailsView.getUpdate().isEmpty()) {
                ModelAndView modelAndView = new ModelAndView("resource-details");
                modelAndView.addObject("success_msg", "Request added successfully.");
                modelAndView.addObject("resourceDetailsView", resourceDetailsView);
                return modelAndView;
            } else {
                ModelAndView modelAndView = new ModelAndView("request-status");
                List<ProjectDetails> obtidList;
                obtidList = iResourceTrackingBO.searchAllProjectDetails();
                modelAndView.addObject("ProjectDetails", obtidList);
                modelAndView.addObject("success_msg", "Request Updated successfully.");
                modelAndView.addObject("resourceDetailsView", resourceDetailsView);
                return modelAndView;
            }

        }
    }

}