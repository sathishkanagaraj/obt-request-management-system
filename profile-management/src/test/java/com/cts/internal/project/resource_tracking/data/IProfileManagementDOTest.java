package com.cts.internal.project.resource_tracking.data;

import com.cts.internal.project.resource_tracking.data.model.ProfileDetails;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/database-conf.xml")
public class IProfileManagementDOTest {
    @Autowired
    private IProfileManagementDO iProfileManagementDO;

    @Autowired
    private ProfileDetails profileDetails;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testAddProfileDetailsDO() throws Exception {
        byte[] bytes = new byte[10];
        DateFormat df = new SimpleDateFormat("yyyy/dd/MM");
        Date date = df.parse("2014/12/11");
        profileDetails.setProfile_id(1);
        profileDetails.setAssociate_id("12");
        profileDetails.setFile(bytes);
        profileDetails.setObtid("12345");
        profileDetails.setProfile_shared_date(date);
        profileDetails.setPoc_details("oqoo");
        iProfileManagementDO.addProfileDetailsDO(profileDetails);
    }

    @Test
    public void testCheckIds() throws Exception {
        iProfileManagementDO.checkIds("12345", "12");
    }

    @Test
    @Transactional
    public void testUpdateResourceProfileDetailsDO() throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy/dd/MM");
        Date date = df.parse("2014/06/06");
        profileDetails.setIntake_date(date);
        iProfileManagementDO.updateResourceProfileDetailsDO(profileDetails);
    }
}
