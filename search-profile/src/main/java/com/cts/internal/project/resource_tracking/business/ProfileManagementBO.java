package com.cts.internal.project.resource_tracking.business;

import com.cts.internal.project.resource_tracking.data.IProfileManagementDO;
import com.cts.internal.project.resource_tracking.data.NoProfilesFoundDO;
import com.cts.internal.project.resource_tracking.data.model.ProfileDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProfileManagementBO implements IProfileManagementBO {
    @Autowired
    private IProfileManagementDO iProfileManagementDO;

    @Override
    public List<ProfileDetails> getAssociateIDBO(String obtid) throws NoProfilesFoundBO {
        List<ProfileDetails> profile = new ArrayList<ProfileDetails>();
        try {
            profile = iProfileManagementDO.getAssociateIDs(obtid);
        } catch (NoProfilesFoundDO noProfilesFoundDO) {
            throw new NoProfilesFoundBO(noProfilesFoundDO.getMessage());
        }
        return profile;
    }

    @Override
    public byte[] getResumesBO(String obtid, String assocaiteid) {
        return iProfileManagementDO.getResumesDO(obtid, assocaiteid);
    }
}
