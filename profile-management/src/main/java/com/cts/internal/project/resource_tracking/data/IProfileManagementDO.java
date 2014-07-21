package com.cts.internal.project.resource_tracking.data;

import com.cts.internal.project.resource_tracking.data.model.ProfileDetails;

public interface IProfileManagementDO {
    public void addProfileDetailsDO(ProfileDetails fileUpload);

    public ProfileDetails checkIds(String obtid, String associateid) throws NoProfileExistsDOException;

    public void updateResourceProfileDetailsDO(ProfileDetails fileUpload);

    public boolean obtidExists(String obtid);

    public void checkIdsAdd(String obtid, String associateid) throws ProfileExistsDOException;
}
