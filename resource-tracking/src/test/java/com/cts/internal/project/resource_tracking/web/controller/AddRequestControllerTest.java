package com.cts.internal.project.resource_tracking.web.controller;

import com.cts.internal.project.resource_tracking.business.IResourceTrackingBO;
import com.cts.internal.project.resource_tracking.data.model.ProjectDetails;
import com.cts.internal.project.resource_tracking.web.adapter.ProjectDetailsAdapter;
import com.cts.internal.project.resource_tracking.web.adapter.ResourceDetailsAdapter;
import com.cts.internal.project.resource_tracking.web.viewobject.ProjectDetailsView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class AddRequestControllerTest {

    private MockMvc mockMvc;
    @Mock
    private IResourceTrackingBO iResourceTrackingBO;
    @Mock
    private ResourceDetailsAdapter resourceDetailsAdapter;
    @Mock
    private ProjectDetailsAdapter projectDetailsAdapter;
    @Mock
    private BindingResult result;
    @InjectMocks
    private AddRequestController addRequestController = new AddRequestController();

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(addRequestController).build();
    }

    @Test
    public void testResourceDetails() throws Exception {
        mockMvc.perform(get("/ResourceDetails"))
                .andExpect(status().isOk())
                .andExpect(view().name("resource-details")).andExpect(model().attributeExists("resourceDetailsView"));
    }

    @Test
    public void testProjectDetails() throws Exception {
        when(result.hasErrors()).thenReturn(true);
        mockMvc.perform(get("/ProjectDetails"))
                .andExpect(status().isOk())
                .andExpect(view().name("project-details")).andExpect(model().attributeExists("projectDetailsView"));

        //  verify(iResourceTrackingBO, times(1)).existingResourceDetails(anyString());
    }

    @Test
    public void testResourceDetailsBack() throws Exception {
        mockMvc.perform(get("/ResourceDetailsBack"))
                .andExpect(status().isOk())
                .andExpect(view().name("resource-details")).andExpect(model().attributeExists("resourceDetailsView"));
    }

    @Test
    public void testAddRequestSuccess() throws Exception {
        mockMvc.perform(post("/Success"))
                .andExpect(status().isOk());
        when(projectDetailsAdapter.buildProjectDetails(mock(ProjectDetailsView.class))).thenReturn(mock(ProjectDetails.class));
        //verify(iResourceTrackingBO, times(1)).addProjectDetails((ProjectDetails) anyObject());

    }
}
