package com.cts.internal.project.resource_tracking.web.controller;

import com.cts.internal.project.resource_tracking.business.DetailsNotFoundBOException;
import com.cts.internal.project.resource_tracking.business.IResourceTrackingBO;
import com.cts.internal.project.resource_tracking.data.model.ProjectDetails;
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
public class RequestStatusController {
    @Autowired
    private IResourceTrackingBO iResourceTrackingBO;

    private ResourceDetailsView resourceDetailsView = new ResourceDetailsView();

    @RequestMapping(value = "/ResourceTracking", method = RequestMethod.GET)
    public String welcomePage() {
        return "home-page";
    }

    @RequestMapping(value = "/updaterequest")
    public ModelAndView updateRequestStatus() {
        List<ProjectDetails> obtidList;
        ModelAndView modelAndView = new ModelAndView("request-status");
        modelAndView.addObject("resourceDetailsView", resourceDetailsView);
        obtidList = iResourceTrackingBO.searchAllProjectDetails();
        modelAndView.addObject("ProjectDetails", obtidList);
        return modelAndView;
    }

    @RequestMapping(value = "/requeststatus", method = RequestMethod.GET)
    public ModelAndView requestStatusSuccess(@ModelAttribute("resourceDetailsView") @Valid ResourceDetailsView resourceDetailsView, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("resource-details");
        String obtcode1;
        String obtcode = resourceDetailsView.getObtid();
        System.out.println(obtcode);
        StringBuffer s = new StringBuffer();
        if (obtcode.contains(",")) {
            s.append(obtcode);
            obtcode1 = s.substring(0, 5);
            obtcode = obtcode1;
        }
        ResourceDetailsView resourceDetailsView1;
        try {
            resourceDetailsView1 = iResourceTrackingBO.searchResourceDetails(obtcode);
        } catch (DetailsNotFoundBOException e) {
            throw new DetailsNotFoundControllerException(e.getMessage());
        }
        resourceDetailsView1.setUpdate("update");
        modelAndView.addObject("resourceDetailsView", resourceDetailsView1);
        return modelAndView;
    }

    @ExceptionHandler(DetailsNotFoundControllerException.class)
    public ModelAndView handleCustomException(DetailsNotFoundControllerException e) {
        ResourceDetailsView resourceDetailsView = new ResourceDetailsView();
        ModelAndView model = new ModelAndView("request-status");
        model.addObject("resourceDetailsView", resourceDetailsView);
        model.addObject("obtid_error", e.getMessage());
        return model;
    }
}