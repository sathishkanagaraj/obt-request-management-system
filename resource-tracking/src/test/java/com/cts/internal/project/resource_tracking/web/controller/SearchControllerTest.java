package com.cts.internal.project.resource_tracking.web.controller;

import com.cts.internal.project.resource_tracking.business.IResourceTrackingBO;
import com.cts.internal.project.resource_tracking.data.model.ProjectDetails;
import com.cts.internal.project.resource_tracking.web.viewobject.ProjectDetailsView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class SearchControllerTest {

    private MockMvc mockMvc;
    @Mock
    private IResourceTrackingBO iResourceTrackingBO;
    @InjectMocks
    private SearchController searchController = new SearchController();

    @Before
    public void setUp() throws Exception {
        this.mockMvc = standaloneSetup(searchController).build();
    }

    @Test
    public void testSearch() throws Exception {
        List<ProjectDetails> projectDetailses = mockProjectDetails();
        when(iResourceTrackingBO.searchAllProjectDetails()).thenReturn(projectDetailses);
        mockMvc.perform(get("/search"))
                .andExpect(status().isOk())
                .andExpect(view().name("search-request")).andExpect(model().attributeExists("resourceDetailsView"));
    }

    private List<ProjectDetails> mockProjectDetails() {
        ProjectDetails projectDetails = mock(ProjectDetails.class);
        when(projectDetails.getObtid()).thenReturn("123");
        return Arrays.asList(projectDetails);
    }

    @Test
    public void testSearchSuccess() throws Exception {
        ProjectDetailsView projectDetailsView = new ProjectDetailsView();
        // when(iResourceTrackingBO.searchDetails(anyString())).thenReturn(projectDetailsView);
        mockMvc.perform(get("/searchsucess"))
                .andExpect(status().isOk())
                .andExpect(view().name("search-success")).andExpect(model().attributeExists("details"));
        // verify(iResourceTrackingBO, times(1)).searchDetails(anyString());

    }
}
