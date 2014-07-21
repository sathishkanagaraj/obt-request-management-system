package com.cts.internal.project.resource_tracking.business;

import com.cts.internal.project.resource_tracking.data.model.ProfileDetails;

public interface IProfileManagementBO {
    public void addProfileDetails(ProfileDetails fileUpload);

    public ProfileDetails searchId(String obtid, String associateid) throws ProfileExistsBOException;

    public void updateResourceDetails(ProfileDetails fileUpload);

    public void obtidExistBO(String obtid) throws NoIdExistException;

    public ProfileDetails searchIdUpdate(String obtid, String associateid) throws NoProfileExistsException;
}
