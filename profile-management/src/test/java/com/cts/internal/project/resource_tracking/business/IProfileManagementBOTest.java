package com.cts.internal.project.resource_tracking.business;

import com.cts.internal.project.resource_tracking.data.IProfileManagementDO;
import com.cts.internal.project.resource_tracking.data.model.ProfileDetails;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class IProfileManagementBOTest {
    @Mock
    private IProfileManagementDO iProfileManagementDO;

    @InjectMocks
    private IProfileManagementBO iProfileManagementBO = new ProfileManagementBO();

    @Mock
    private ProfileDetails profileDetails;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testAddProfileDetails() throws Exception {
        iProfileManagementBO.addProfileDetails(profileDetails);
        verify(iProfileManagementDO, times(1)).addProfileDetailsDO(profileDetails);
    }

    @Test
    public void testSearchId() throws Exception {
        iProfileManagementBO.searchId(anyString(), anyString());
        when(iProfileManagementDO.checkIds(anyString(), anyString())).thenReturn(profileDetails);
    }

    @Test
    public void testUpdateResourceDetails() throws Exception {
        iProfileManagementBO.updateResourceDetails(profileDetails);
        verify(iProfileManagementDO, times(1)).updateResourceProfileDetailsDO(profileDetails);
    }
}
