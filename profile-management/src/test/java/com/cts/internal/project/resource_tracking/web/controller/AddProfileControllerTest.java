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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class AddProfileControllerTest {
    private MockMvc mockMvc;
    @Mock
    private IProfileManagementBO iProfileManagementBO;
    @Mock
    private AddProfileAdapter addProfileAdapter;
    @Mock
    private ProfileDetailsView profileDetailsView;

    @InjectMocks
    private AddProfileController addProfileController = new AddProfileController();

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(addProfileController).build();
    }

    @Test
    public void testGetOBTID() throws Exception {
        mockMvc.perform(get("/AddResourceProfile")).andExpect(status().isOk()).andExpect(view().name("add-resource-profile-start")).andExpect(model().attributeExists("profileDetailsView", "sampledata"));
    }

    @Test
    public void testUpload() throws Exception {
        mockMvc.perform(post("/upload")).andExpect(status().isOk()).andExpect(view().name("add-profile-details")).andExpect(model().attributeExists("profileDetailsView"));
    }

    @Test
    public void testUploadFileHandler() throws Exception {
        //     when(profileDetailsView.getFile()).thenReturn(mock(MultipartFile.class));
        mockMvc.perform(post("/uploadFile")).andExpect(status().isOk()).andExpect(view().name("add-resource-profile-start"));
        //     when(profileDetailsView.getFile().isEmpty()).thenReturn(true);
        when(addProfileAdapter.buildFileUploadEntity(profileDetailsView)).thenReturn(mock(ProfileDetails.class));
    }
}
