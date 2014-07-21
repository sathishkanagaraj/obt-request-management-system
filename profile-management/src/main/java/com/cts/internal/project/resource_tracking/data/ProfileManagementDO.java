package com.cts.internal.project.resource_tracking.data;

import com.cts.internal.project.resource_tracking.data.model.ProfileDetails;
import com.cts.internal.project.resource_tracking.data.repository.ProfileDetailsRepo;
import com.cts.internal.project.resource_tracking.data.repository.ResourceDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Repository
@Transactional
public class ProfileManagementDO implements IProfileManagementDO {
    @Autowired
    private ProfileDetailsRepo profileDetailsRepo;

    @Autowired
    private ProfileDetails profileDetails;

    @Autowired
    private ResourceDetailsRepo resourceDetailsRepo;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addProfileDetailsDO(ProfileDetails profileDetails1) {
        profileDetailsRepo.save(profileDetails1);
    }

    @Override
    @Transactional
    public ProfileDetails checkIds(String obtid, String associateid) throws NoProfileExistsDOException {
        Query query = entityManager.createNamedQuery("SearchByid");
        query.setParameter("obtid", obtid);
        query.setParameter("associate_id", associateid);
        if (query.getResultList().isEmpty()) {
            throw new NoProfileExistsDOException("Profile not found");
        }
        profileDetails = (ProfileDetails) query.getSingleResult();
        return profileDetails;
    }

    @Override
    public void updateResourceProfileDetailsDO(ProfileDetails profileDetails1) {
        Query query = entityManager.createNamedQuery("UpdateProfile");
        query.setParameter("profile_id", profileDetails1.getProfile_id());
        query.setParameter("associate_id", profileDetails1.getAssociate_id());
        query.setParameter("intake_date", profileDetails1.getIntake_date());
        query.setParameter("obtid", profileDetails1.getObtid());
        query.setParameter("poc_details", profileDetails1.getPoc_details());
        query.setParameter("profile_shared_date", profileDetails1.getProfile_shared_date());
        query.setParameter("remarks", profileDetails1.getRemarks());
        query.setParameter("result", profileDetails1.getResult());
        query.setParameter("result_date", profileDetails1.getResult_date());
        query.setParameter("file", profileDetails1.getFile());
        query.executeUpdate();
    }

    @Override
    public boolean obtidExists(String obtid) {
        boolean bool = false;
        if (resourceDetailsRepo.findOne(obtid) == null) {
            bool = true;
        }
        return bool;
    }

    @Override
    public void checkIdsAdd(String obtid, String associateid) throws ProfileExistsDOException {
        Query query = entityManager.createNamedQuery("SearchByid");
        query.setParameter("obtid", obtid);
        query.setParameter("associate_id", associateid);
        if (!query.getResultList().isEmpty()) {
            throw new ProfileExistsDOException("Profile Already Exists");
        }
    }
}
