package com.cts.internal.project.resource_tracking.data;

import com.cts.internal.project.resource_tracking.data.model.ProjectDetails;
import com.cts.internal.project.resource_tracking.data.model.ResourceDetails;
import com.cts.internal.project.resource_tracking.data.repository.ProjectDetailsRepo;
import com.cts.internal.project.resource_tracking.data.repository.ResourceDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Repository
@Transactional
public class ResourceTrackingDO implements IResourceTrackingDO {
    @Autowired
    private ResourceDetails resourceDetails;

    @Autowired
    private ResourceDetailsRepo resourceDetailsRepo;

    @Autowired
    private ProjectDetails projectDetails;

    @Autowired
    private ProjectDetailsRepo projectDetailsRepo;

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void insertResourceDetails(ResourceDetails resourceDetails) {
        resourceDetailsRepo.save(resourceDetails);
        // em.merge(addRequestVO);
    }

    @Override
    @Transactional
    public void insertProjectDetails(ProjectDetails projectDetails) {
        projectDetailsRepo.save(projectDetails);
        // em.merge(projectDetails);
    }

    @Override
    @Transactional
    public ResourceDetails searchResourceDetails(String obtcode) throws DetailsNotFoundException {
        resourceDetails = resourceDetailsRepo.findOne(obtcode);
        if (resourceDetails == null) {
            throw new DetailsNotFoundException("Invalid OBT ID");
        }
        return resourceDetails;
    }

    @Override
    @Transactional
    public ProjectDetails searchProjectDetails(String obtcode) {
        projectDetails = projectDetailsRepo.findOne(obtcode);
        return projectDetails;
    }

    @Override
    public List<ProjectDetails> searchAllDetails() {
        List<ProjectDetails> obtidList;
        obtidList = projectDetailsRepo.findAll();
        return obtidList;
    }

    @Transactional
    @Override
    public void deleteRequest(String obtid) throws DetailsNotFoundException {
        projectDetails = projectDetailsRepo.findOne(obtid);
        if (projectDetails == null) {
            throw new DetailsNotFoundException("Invalid OBT ID");
        } else {
            Query query = em.createNamedQuery("DeleteRow");
            query.setParameter("obtid", obtid);
            query.executeUpdate();
            projectDetailsRepo.delete(obtid);
            resourceDetailsRepo.delete(obtid);
        }
    }

    @Override
    @Transactional
    public void deleteRequestList(String[] obtid) {
        for (String obtid1 : obtid) {
            Query query = em.createNamedQuery("DeleteRow");
            query.setParameter("obtid", obtid1);
            query.executeUpdate();
            projectDetailsRepo.delete(obtid1);
            resourceDetailsRepo.delete(obtid1);
        }
    }

    @Override
    @Transactional
    public ResourceDetails checkObtid(String obtid) {
        return resourceDetailsRepo.findOne(obtid);
    }

}
