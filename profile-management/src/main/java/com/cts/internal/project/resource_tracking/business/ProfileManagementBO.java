package com.cts.internal.project.resource_tracking.business;

import com.cts.internal.project.resource_tracking.data.IProfileManagementDO;
import com.cts.internal.project.resource_tracking.data.NoProfileExistsDOException;
import com.cts.internal.project.resource_tracking.data.ProfileExistsDOException;
import com.cts.internal.project.resource_tracking.data.model.ProfileDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfileManagementBO implements IProfileManagementBO {
    @Autowired
    private IProfileManagementDO iProfileManagementDO;

    @Autowired
    private ProfileDetails profileDetails;

    @Override
    public void addProfileDetails(ProfileDetails profileDetails) {
        iProfileManagementDO.addProfileDetailsDO(profileDetails);
    }

    @Override
    public ProfileDetails searchId(String obtid, String associateid) throws ProfileExistsBOException {
        try {
            iProfileManagementDO.checkIdsAdd(obtid, associateid);
        } catch (ProfileExistsDOException e) {
            throw new ProfileExistsBOException(e.getMessage());
        }
        return profileDetails;
    }

    @Override
    public void updateResourceDetails(ProfileDetails profileDetails) {
        iProfileManagementDO.updateResourceProfileDetailsDO(profileDetails);
    }

    @Override
    public void obtidExistBO(String obtid) throws NoIdExistException {
        if (iProfileManagementDO.obtidExists(obtid)) {
            throw new NoIdExistException("Invalid Obt ID");
        }
    }

    @Override
    public ProfileDetails searchIdUpdate(String obtid, String associateid) throws NoProfileExistsException {
        try {
            profileDetails = iProfileManagementDO.checkIds(obtid, associateid);
        } catch (NoProfileExistsDOException e) {
            throw new NoProfileExistsException(e.getMessage());
        }
        return profileDetails;
    }

}
