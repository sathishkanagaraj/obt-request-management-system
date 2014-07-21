package com.cts.internal.project.resource_tracking.web.resteasy.resource;

import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.net.URI;

@Provider
@Component
public class NoProfilesFoundControllerExceptionHandler implements ExceptionMapper<NoProfilesFoundControllerException> {
    @Override
    public Response toResponse(NoProfilesFoundControllerException exception) {
        /*ProfileDetailsView profileDetailsView=new ProfileDetailsView();
        ModelAndView model = new ModelAndView("search-profile-start");
        model.addObject("addResourceProfileDetailsView",profileDetailsView);
        model.addObject("profile_error", ex.getMessage());*/
        //return Response.serverError().entity(exception.getMessage()).type(MediaType.TEXT_HTML).build();
        return Response.created(URI.create("hello")).entity(exception.getMessage()).build();
    }
}
