package com.cts.internal.project.resource_tracking.data;

import com.cts.internal.project.resource_tracking.data.model.ProfileDetails;

import java.util.List;

public interface IProfileManagementDO {
    public List<ProfileDetails> getAssociateIDs(String obtid) throws NoProfilesFoundDO;

    public byte[] getResumesDO(String obtid, String associateid);
}
