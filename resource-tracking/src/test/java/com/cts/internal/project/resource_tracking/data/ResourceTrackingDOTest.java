package com.cts.internal.project.resource_tracking.data;

import com.cts.internal.project.resource_tracking.data.model.ProjectDetails;
import com.cts.internal.project.resource_tracking.data.model.ResourceDetails;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:resource-tracking/src/main/webapp/WEB-INF/database-conf.xml")
public class ResourceTrackingDOTest {

    @Autowired
    private IResourceTrackingDO iResourceTrackingDO;

    @Autowired
    private ResourceDetails resourceDetails;

    @Autowired
    private ProjectDetails projectDetails;

    @Rule
    public org.junit.rules.ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testInsertResourceDetails() throws Exception {
        resourceDetails.setObtid("obt115");
        iResourceTrackingDO.insertResourceDetails(resourceDetails);
    }

    @Test
    public void testInsertProjectDetails() throws Exception {
        projectDetails.setObtid("obt115");
        iResourceTrackingDO.insertProjectDetails(projectDetails);
    }

    @Test
    public void testNoDetailsFoundException() throws Exception {
        expectedException.expect(DetailsNotFoundException.class);
        expectedException.expectMessage("Invalid OBT ID");
        iResourceTrackingDO.searchResourceDetails("obt");
    }

    @Test
    public void testSearchResourceDetails() throws Exception {
        iResourceTrackingDO.searchResourceDetails("obt116");
    }

    @Test
    public void testSearchProjectDetails() throws Exception {
        iResourceTrackingDO.searchProjectDetails("obt116");
    }

    @Test
    public void testSearchAllDetails() throws Exception {
        iResourceTrackingDO.searchAllDetails();
    }

    @Test
    public void testInvalidOBTidException() throws Exception {
        expectedException.expect(DetailsNotFoundException.class);
        expectedException.expectMessage("Invalid OBT ID");
        iResourceTrackingDO.deleteRequest("obt");
    }

    @Test
    @Transactional
    public void testDeleteRequest() throws Exception {
        iResourceTrackingDO.deleteRequest("obt116");


    }

    @Test
    @Transactional
    public void testDeleteRequestList() throws Exception {
        String[] obtid = new String[3];
        obtid[0] = "obt114";
        obtid[1] = "obt115";
        obtid[2] = "obt116";
        iResourceTrackingDO.deleteRequestList(obtid);
    }

    @Test
    public void testCheckObtid() throws Exception {
        iResourceTrackingDO.checkObtid("obt115");
    }
}
