package com.cts.internal.project.resource_tracking.web.controller;

import com.cts.internal.project.resource_tracking.business.IResourceTrackingBO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class DeleteRequestControllerTest {
    private MockMvc mockMvc;
    @Mock
    private IResourceTrackingBO iResourceTrackingBO;
    @InjectMocks
    private DeleteRequestController deleteRequestController;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(deleteRequestController).build();

    }

    @Test
    public void testDeleteRequest() throws Exception {
        mockMvc.perform(get("/delete"))
                .andExpect(status().isOk())
                .andExpect(view().name("delete-request")).andExpect(model().attributeExists("resourceDetailsView"));

    }

    @Test
    public void testDeleteRequestSuccess() throws Exception {
        mockMvc.perform(get("/deletedetails"))
                .andExpect(status().isOk())
                .andExpect(view().name("delete-success"));
        //verify(iResourceTrackingBO,times(1)).deleteRequestBO(anyString());
    }
}
