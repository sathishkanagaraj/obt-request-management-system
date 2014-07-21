package com.cts.internal.project.resource_tracking.web.controller;

import com.cts.internal.project.resource_tracking.business.IProfileManagementBO;
import com.cts.internal.project.resource_tracking.business.NoProfileExistsException;
import com.cts.internal.project.resource_tracking.data.model.ProfileDetails;
import com.cts.internal.project.resource_tracking.web.adapter.AddProfileAdapter;
import com.cts.internal.project.resource_tracking.web.viewobject.ProfileDetailsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Controller
public class UpdateResourceProfileController {
    @Autowired
    private AddProfileAdapter addProfileAdapter;

    @Autowired
    private IProfileManagementBO iResourceTrackingBO;

    @Autowired
    private ProfileDetails profileDetails;
    private ProfileDetailsView profileDetailsView = new ProfileDetailsView();
    private byte[] bytes;

    @RequestMapping("/UpdateResourceProfile")
    public ModelAndView getOBTid_AssociateId() {
        ProfileDetailsView profileDetailsView = new ProfileDetailsView();
        ModelAndView modelAndView = new ModelAndView("update-resource-profile");
        modelAndView.addObject("addResourceProfileDetailsView", profileDetailsView);
        return modelAndView;
    }

    @RequestMapping(value = "/CheckId", method = RequestMethod.POST)
    public ModelAndView checkId(@ModelAttribute("addResourceProfileDetailsView") ProfileDetailsView profileDetailsView) {
        ModelAndView modelAndView = new ModelAndView("update-resource-profile-details");
        String obtid = profileDetailsView.getObtid();
        String associateid = profileDetailsView.getAssociate_id();
        try {
            profileDetailsView = addProfileAdapter.buildFileUploadView(iResourceTrackingBO.searchIdUpdate(obtid, associateid));
        } catch (NoProfileExistsException e) {
            throw new NoProfileExistsControllerException(e.getMessage());
        }
        bytes = profileDetailsView.getBytes();
        modelAndView.addObject("addResourceProfileDetailsView", profileDetailsView);
        return modelAndView;
    }

    @ExceptionHandler(NoProfileExistsControllerException.class)
    public ModelAndView handleCustomException(NoProfileExistsControllerException ex) {
        ModelAndView modelAndView = new ModelAndView("update-resource-profile");
        modelAndView.addObject("addResourceProfileDetailsView", profileDetailsView);
        modelAndView.addObject("obtid_error", ex.getMessage());
        return modelAndView;
    }

    @RequestMapping("/DownloadResume")
    public void DownloadProfileResume(HttpServletResponse response) {
        response.setHeader("Content-Disposition", "attachment; filename=\"" + "resume" + "\"");
        try {
            ServletOutputStream outs = response.getOutputStream();
            outs.write(bytes);
            outs.flush();
            outs.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @RequestMapping(value = "/UpdateResourceProfileDetails", method = RequestMethod.POST)
    public ModelAndView updateResourceProfileDetails(@ModelAttribute("addResourceProfileDetailsView") @Valid ProfileDetailsView profileDetailsView, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("update-resource-profile-details");
            if (bindingResult.getFieldError("profile_shared_date") != null) {
                modelAndView.addObject("pdate_error", "Date field cannot be left blank");
            }
            if (bindingResult.getFieldError("result_date") != null) {
                modelAndView.addObject("rdate_error", "Date field cannot be left blank");
            }
            if (bindingResult.getFieldError("intake_date") != null) {
                modelAndView.addObject("idate_error", "Date field cannot be left blank");
            }
            modelAndView.addObject("addResourceProfileDetailsView", profileDetailsView);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("update-resource-profile");
            if (profileDetailsView.getFile().isEmpty()) {
                profileDetailsView.setBytes(bytes);
            }
            profileDetails = addProfileAdapter.buildFileUploadEntity(profileDetailsView);
            iResourceTrackingBO.updateResourceDetails(profileDetails);
            modelAndView.addObject("addResourceProfileDetailsView", this.profileDetailsView);
            modelAndView.addObject("successMessage", "Profile Updated Successfully");
            return modelAndView;
        }
    }
}
