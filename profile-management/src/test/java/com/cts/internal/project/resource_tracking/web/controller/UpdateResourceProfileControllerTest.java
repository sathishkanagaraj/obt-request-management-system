package com.cts.internal.project.resource_tracking.web.controller;

import com.cts.internal.project.resource_tracking.business.IProfileManagementBO;
import com.cts.internal.project.resource_tracking.data.model.ProfileDetails;
import com.cts.internal.project.resource_tracking.web.adapter.AddProfileAdapter;
import com.cts.internal.project.resource_tracking.web.viewobject.ProfileDetailsView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class UpdateResourceProfileControllerTest {
    private MockMvc mockMvc;
    @Mock
    private IProfileManagementBO iProfileManagementBO;
    @Mock
    private AddProfileAdapter addProfileAdapter;
    @Mock
    private ProfileDetails profileDetails;
    @Mock
    private ProfileDetailsView profileDetailsView;
    @InjectMocks
    private UpdateResourceProfileController updateResourceProfileController = new UpdateResourceProfileController();

    @Before
    public void setUp() throws Exception {
        this.mockMvc = standaloneSetup(updateResourceProfileController).build();
    }

    @Test
    public void testGetOBTid_AssociateId() throws Exception {
        mockMvc.perform(post("/UpdateResourceProfile")).andExpect(status().isOk()).andExpect(view().name("update-resource-profile")).andExpect(model().attributeExists("addResourceProfileDetailsView", "WelcomeMessage"));
    }

    @Test
    public void testCheckId() throws Exception {
        byte[] bytes = new byte[0];
        when(profileDetailsView.getBytes()).thenReturn(bytes);
        mockMvc.perform(post("/CheckId")).andExpect(status().isOk()).andExpect(view().name("update-resource-profile-details")).andExpect(model().attributeExists("addResourceProfileDetailsView"));
        when(iProfileManagementBO.searchId(anyString(), anyString())).thenReturn(profileDetails);
        when(addProfileAdapter.buildFileUploadView(profileDetails)).thenReturn(profileDetailsView);
    }

    @Test
    public void testDownloadProfileResume() throws Exception {

    }

    @Test
    public void testUpdateResourceProfileDetails() throws Exception {

    }
}
