package com.cts.internal.project.resource_tracking.web.controller;

/*import com.cts.internal.project.resource_tracking.business.IResourceTrackingBO;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@RunWith(MockitoJUnitRunner.class)
public class RequestStatusControllerTest {
    private MockMvc mockMvc;
    @Mock
    private IResourceTrackingBO iResourceTrackingBO;
    @InjectMocks
    private RequestStatusController requestStatusController=new RequestStatusController();
    @Mock
    private RequestStatusView requestStatusView;
    @Mock
    private RequestStatusAdapter requestStatusAdapter;
    @Mock
    private RequestStatus requestStatus;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(requestStatusController).build();
       }
    @Test
    public void testWelcomePage() throws Exception {
        mockMvc.perform(get("/ResourceTracking"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }

    @Test
    public void testUpdateRequestStatus() throws Exception {
        mockMvc.perform(get("/updaterequest"))
                .andExpect(status().isOk())
                .andExpect(view().name("request-status")).andExpect(model().attributeExists("requestStatusView"));
    }

    @Test
    public void testRequestStatusSuccess() throws Exception {

        mockMvc.perform(post("/requeststatus"))
                .andExpect(status().isOk())
               .andExpect(view().name("update-request-success"));
        when(requestStatusAdapter.buildRequestStatus(requestStatusView)).thenReturn(requestStatus);
       // verify(iResourceTrackingBO, times(1)).updateRequestStatus((RequestStatus)anyObject());
    }
}*/
