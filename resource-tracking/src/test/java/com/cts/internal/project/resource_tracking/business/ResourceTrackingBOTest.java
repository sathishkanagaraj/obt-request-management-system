package com.cts.internal.project.resource_tracking.business;

import com.cts.internal.project.resource_tracking.data.DetailsNotFoundException;
import com.cts.internal.project.resource_tracking.data.IResourceTrackingDO;
import com.cts.internal.project.resource_tracking.data.model.ProjectDetails;
import com.cts.internal.project.resource_tracking.data.model.ResourceDetails;
import com.cts.internal.project.resource_tracking.exception.ExistingObtidException;
import com.cts.internal.project.resource_tracking.web.adapter.ProjectDetailsAdapter;
import com.cts.internal.project.resource_tracking.web.adapter.ResourceDetailsAdapter;
import com.cts.internal.project.resource_tracking.web.viewobject.ProjectDetailsView;
import com.cts.internal.project.resource_tracking.web.viewobject.ResourceDetailsView;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class ResourceTrackingBOTest {
    @InjectMocks
    private ResourceTrackingBO resourceTrackingBO = new ResourceTrackingBO();
    @Rule
    public org.junit.rules.ExpectedException expectedException = ExpectedException.none();
    @Mock
    private IResourceTrackingDO iResourceTrackingDO;
    @Mock
    private ProjectDetails projectDetails;
    @Mock
    private ResourceDetails resourceDetails;
    @Mock
    private ProjectDetailsAdapter projectDetailsAdapter;
    @Mock
    private ResourceDetailsAdapter resourceDetailsAdapter;


    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testAddProjectDetails() throws Exception {
        resourceTrackingBO.addProjectDetails(projectDetails, resourceDetails);
        verify(iResourceTrackingDO, times(1)).insertProjectDetails(projectDetails);
        verify(iResourceTrackingDO, times(1)).insertResourceDetails(resourceDetails);
    }

    @Test
    public void testSearchDetails() throws Exception {
        resourceTrackingBO.searchDetails(anyString());
        ProjectDetailsView projectDetailsView = new ProjectDetailsView();
        when(projectDetailsAdapter.buildProjectDetailsView(projectDetails)).thenReturn(projectDetailsView);
        when(iResourceTrackingDO.searchProjectDetails(anyString())).thenReturn(projectDetails);
    }

    @Test
    public void testNoDetailsFound() throws Exception {
        expectedException.expect(DetailsNotFoundBOException.class);
        expectedException.expectMessage("Invalid OBT ID");
        when(iResourceTrackingDO.searchResourceDetails(anyString())).thenThrow(new DetailsNotFoundException("Invalid OBT ID"));
        resourceTrackingBO.searchResourceDetails(anyString());

    }

    @Test
    public void testSearchAllProjectDetails() throws Exception {
        resourceTrackingBO.searchAllProjectDetails();
        List<ProjectDetails> projectDetailses = mockProjectDetails();
        when(iResourceTrackingDO.searchAllDetails()).thenReturn(projectDetailses);
    }

    private List<ProjectDetails> mockProjectDetails() {
        List<ProjectDetails> mocklist = new ArrayList<ProjectDetails>();
        mocklist.add(projectDetails);
        return mocklist;  //To change body of created methods use File | Settings | File Templates.
    }

    @Test
    public void testDeleteRequestBO() throws Exception {
        resourceTrackingBO.deleteRequestBO(anyString());
        verify(iResourceTrackingDO, times(1)).deleteRequest(anyString());
    }

    @Test//(expected = DetailsNotFoundBOException.class)
    public void testInvalidOBTId() throws Exception {
        expectedException.expect(DetailsNotFoundBOException.class);
        expectedException.expectMessage("Invalid OBT ID");
        doThrow(new DetailsNotFoundException("Invalid OBT ID")).when(iResourceTrackingDO).deleteRequest(anyString());
        resourceTrackingBO.deleteRequestBO(anyString());
    }

    @Test//(expected = ExistingObtidException.class)
    public void testObtIdAlreadyExistsException() throws Exception {
        expectedException.expect(ExistingObtidException.class);
        expectedException.expectMessage("ObtId already Exists");
        when(iResourceTrackingDO.checkObtid(anyString())).thenReturn(resourceDetails);
        resourceTrackingBO.existingResourceDetails(anyString());
    }

    @Test
    public void testdeleteRequestListBO() throws Exception {
        String[] obtids = mockObtids();
        resourceTrackingBO.deleteRequestListBO(obtids);
        verify(iResourceTrackingDO, times(1)).deleteRequestList(obtids);
    }

    private String[] mockObtids() {
        String[] mockstring = new String[5];
        mockstring[0] = anyString();
        return mockstring;
    }

    @Test
    public void testsearchResourceDetails() throws Exception {
        when(iResourceTrackingDO.searchResourceDetails(anyString())).thenReturn(resourceDetails);
        when(resourceDetailsAdapter.buildResourceDetailsView(resourceDetails)).thenReturn(new ResourceDetailsView());
        resourceTrackingBO.searchResourceDetails(anyString());
    }
}
