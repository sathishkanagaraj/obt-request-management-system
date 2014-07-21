package com.cts.internal.project.resource_tracking.web.resteasy.resource;

import com.cts.internal.project.resource_tracking.business.IProfileManagementBO;
import com.cts.internal.project.resource_tracking.business.NoProfilesFoundBO;
import com.cts.internal.project.resource_tracking.data.model.ProfileDetails;
import com.cts.internal.project.resource_tracking.web.viewobject.ProfileDetailsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Controller
@Path("/SearchProfile")
public class SearchProfilesResource {

    @Autowired
    private IProfileManagementBO iProfileManagementBO;

    @GET
    @Path("/getProfileResume")
    @Produces("application/msword")
    public Response getResume(@QueryParam("obtid") String obtid, @QueryParam("associateid") String associate_id) {
        byte[] bytes = iProfileManagementBO.getResumesBO(obtid, associate_id);
        Response.ResponseBuilder response = Response.ok(bytes);
        response.header("Content-Disposition", "attachment; filename=" + associate_id + ".doc");

        return response.build();
    }

    @GET
    @Path("/EnterObtId")
    public ModelAndView goToObtId() {
        ProfileDetailsView profileDetailsView = new ProfileDetailsView();
        ModelAndView modelAndView = new ModelAndView("search-profile-start");
        modelAndView.addObject("addResourceProfileDetailsView", profileDetailsView);
        return modelAndView;
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public ModelAndView getAssociateId(@FormParam("obtid") String obtid) {
        ProfileDetailsView profileDetailsView = new ProfileDetailsView();
        List<ProfileDetails> profileDetailsList;
        try {
            ModelAndView modelAndView = new ModelAndView("search-profile");
            profileDetailsList = iProfileManagementBO.getAssociateIDBO(obtid);
            modelAndView.addObject("profileDetailsList", profileDetailsList);
            modelAndView.addObject("addResourceProfileDetailsView", profileDetailsView);
            return modelAndView;
        } catch (NoProfilesFoundBO noProfilesFoundBO) {
            ModelAndView modelAndView = new ModelAndView("search-profile-start");
            modelAndView.addObject("addResourceProfileDetailsView", profileDetailsView);
            modelAndView.addObject("noprofile_error", noProfilesFoundBO.getMessage());
            return modelAndView;
        }
    }
}
