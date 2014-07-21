package com.cts.internal.project.resource_tracking.data;

import com.cts.internal.project.resource_tracking.data.model.ProfileDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


@Repository
@Transactional
public class ProfileManagementDO implements IProfileManagementDO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ProfileDetails> getAssociateIDs(String obtid) throws NoProfilesFoundDO {
        List<ProfileDetails> profileDetailsList = new ArrayList<ProfileDetails>();
        Query query = entityManager.createNamedQuery("SearchByObtid");
        query.setParameter("obtid", obtid);
        profileDetailsList = query.getResultList();
        if (profileDetailsList.isEmpty()) {
            throw new NoProfilesFoundDO("No Profiles found");
        }
        return profileDetailsList;
    }

    @Override
    public byte[] getResumesDO(String obtid, String associateid) {
        byte[] bytes;
        Query query = entityManager.createNamedQuery("getResume");
        query.setParameter("obtid", obtid);
        query.setParameter("associate_id", associateid);
        bytes = (byte[]) query.getSingleResult();
        return bytes;
    }
}
