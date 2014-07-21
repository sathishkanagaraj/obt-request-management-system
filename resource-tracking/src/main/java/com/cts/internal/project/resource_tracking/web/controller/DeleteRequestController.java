package com.cts.internal.project.resource_tracking.web.controller;

import com.cts.internal.project.resource_tracking.business.DetailsNotFoundBOException;
import com.cts.internal.project.resource_tracking.business.IResourceTrackingBO;
import com.cts.internal.project.resource_tracking.data.model.ProjectDetails;
import com.cts.internal.project.resource_tracking.web.viewobject.ResourceDetailsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class DeleteRequestController {
    @Autowired
    private IResourceTrackingBO iResourceTrackingBO;

    private ResourceDetailsView resourceDetailsView = new ResourceDetailsView();

    @RequestMapping("/delete")
    public ModelAndView deleteRequest() {
        ModelAndView modelAndView = new ModelAndView("delete-request");
        modelAndView.addObject("resourceDetailsView", resourceDetailsView);
        return modelAndView;
    }

    @RequestMapping("/deletedetails")
    public ModelAndView deleteRequestSuccess(@ModelAttribute("resourceDetailsView") ResourceDetailsView detailsView, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("delete-request");
        if (request.getParameter("delete") != null) {
            if (request.getParameter("delete").equals("single")) {
                modelAndView.addObject("delete", "single");
            } else {
                modelAndView.addObject("delete", "multi");
                List<ProjectDetails> obtidlist = iResourceTrackingBO.searchAllProjectDetails();
                modelAndView.addObject("obtidlist", obtidlist);
            }
        } else {
            if (request.getParameterValues("checkobtid") == null) {
                String obtid = detailsView.getObtid();
                try {
                    iResourceTrackingBO.deleteRequestBO(obtid);
                } catch (DetailsNotFoundBOException e) {
                    throw new DetailsNotFoundControllerException(e.getMessage());
                }
            } else {
                String[] obtidlist = request.getParameterValues("checkobtid");
                iResourceTrackingBO.deleteRequestListBO(obtidlist);
            }
            modelAndView.addObject("resourceDetailsView", resourceDetailsView);
            modelAndView.addObject("success_msg", "Resource Request deleted successfully");

        }
        return modelAndView;
    }

    @ExceptionHandler(DetailsNotFoundControllerException.class)
    public ModelAndView handleCustomException(DetailsNotFoundControllerException e) {
        ModelAndView modelAndView = new ModelAndView("delete-request");
        modelAndView.addObject("resourceDetailsView", resourceDetailsView);
        modelAndView.addObject("delete_error", e.getMessage());
        return modelAndView;
    }
}
