package com.cts.internal.project.resource_tracking.business;

import com.cts.internal.project.resource_tracking.data.model.ProfileDetails;

import java.util.List;

public interface IProfileManagementBO {
    public List<ProfileDetails> getAssociateIDBO(String obtid) throws NoProfilesFoundBO;

    public byte[] getResumesBO(String obtid, String assocaiteid);

}
