package com.cts.internal.project.resource_tracking.web.controller;

import com.cts.internal.project.resource_tracking.business.IProfileManagementBO;
import com.cts.internal.project.resource_tracking.business.NoIdExistException;
import com.cts.internal.project.resource_tracking.business.ProfileExistsBOException;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;

/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/


@Controller
public class AddProfileController {
    @Autowired
    private AddProfileAdapter addProfileAdapter;

    @Autowired
    private IProfileManagementBO iProfileManagementBO;

    @Autowired
    private ProfileDetails profileDetails;

    private ProfileDetailsView profileDetailsView = new ProfileDetailsView();
    //private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    /**
     * Upload single file using Spring Controller
     */

    @RequestMapping("/AddResourceProfile")
    public ModelAndView GetOBTID() {
        ModelAndView modelAndView = new ModelAndView("add-resource-profile-start");
        modelAndView.addObject("profileDetailsView", profileDetailsView);
        return modelAndView;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ModelAndView upload(@ModelAttribute("profileDetailsView") ProfileDetailsView profileDetailsView) {
        ModelAndView modelAndView = new ModelAndView("add-profile-details");
        this.profileDetailsView.setObtid(profileDetailsView.getObtid());
        try {
            iProfileManagementBO.obtidExistBO(profileDetailsView.getObtid());
        } catch (NoIdExistException e) {
            throw new NoIdExistControllerException(e.getMessage());
        }
        modelAndView.addObject("profileDetailsView", this.profileDetailsView);
        return modelAndView;
    }

    @ExceptionHandler(NoIdExistControllerException.class)
    public ModelAndView handleCustomException(NoIdExistControllerException ex) {

        ModelAndView modelAndView = new ModelAndView("add-resource-profile-start");
        modelAndView.addObject("profileDetailsView", profileDetailsView);
        modelAndView.addObject("obtid_error", ex.getMessage());
        return modelAndView;
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ModelAndView uploadFileHandler(@ModelAttribute("profileDetailsView") @Valid ProfileDetailsView profileDetailsView1, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("add-profile-details");
            if (bindingResult.getFieldError("profile_shared_date") != null) {
                modelAndView.addObject("date_error", "Date field cannot be left blank");
            }
            modelAndView.addObject("profileDetailsView", profileDetailsView1);
            return modelAndView;
        } else {
            MultipartFile file = profileDetailsView1.getFile();
            if (!file.isEmpty()) {
                ModelAndView modelAndView = new ModelAndView("add-resource-profile-start");
                try {
                    profileDetailsView1.setBytes(profileDetailsView1.getFile().getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String obtid = profileDetailsView1.getObtid();
                String asid = profileDetailsView1.getAssociate_id();
                try {
                    iProfileManagementBO.searchId(obtid, asid);
                } catch (ProfileExistsBOException e) {
                    throw new ProfileExistsControllerException(e.getMessage());
                }
                profileDetails = addProfileAdapter.buildFileUploadEntity(profileDetailsView1);
                iProfileManagementBO.addProfileDetails(profileDetails);
                modelAndView.addObject("successMessage", "Resource Profile inserted successfully... Would you like to add another profile?");
                modelAndView.addObject("profileDetailsView", new ProfileDetailsView());
                return modelAndView;
            } else {
                ModelAndView modelAndView = new ModelAndView("add-profile-details");
                modelAndView.addObject("err", "resume cannot be left blank");
                modelAndView.addObject("profileDetailsView", profileDetailsView1);
                return modelAndView;
            }

        }
    }

    @ExceptionHandler(ProfileExistsControllerException.class)
    public ModelAndView handleCustomException(ProfileExistsControllerException ex) {
        ProfileDetailsView profileDetailsView = new ProfileDetailsView();
        ModelAndView modelAndView = new ModelAndView("add-profile-details");
        modelAndView.addObject("profileDetailsView", profileDetailsView);
        modelAndView.addObject("id_error", ex.getMessage());
        return modelAndView;
    }
}

