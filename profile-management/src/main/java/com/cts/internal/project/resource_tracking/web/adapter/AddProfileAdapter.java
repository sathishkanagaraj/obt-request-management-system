package com.cts.internal.project.resource_tracking.web.adapter;

import com.cts.internal.project.resource_tracking.data.model.ProfileDetails;
import com.cts.internal.project.resource_tracking.web.viewobject.ProfileDetailsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddProfileAdapter {
    @Autowired
    private ProfileDetails profileDetails;

    public ProfileDetails buildFileUploadEntity(ProfileDetailsView profileDetailsView) {
        profileDetails.setProfile_id(profileDetailsView.getProfile_id());
        profileDetails.setObtid(profileDetailsView.getObtid());
        profileDetails.setAssociate_id(profileDetailsView.getAssociate_id());
        profileDetails.setFile(profileDetailsView.getBytes());
        profileDetails.setIntake_date(profileDetailsView.getIntake_date());
        profileDetails.setPoc_details(profileDetailsView.getOnsite_poc_details());
        profileDetails.setResult(profileDetailsView.getResult());
        profileDetails.setResult_date(profileDetailsView.getResult_date());
        profileDetails.setRemarks(profileDetailsView.getRemarks());
        profileDetails.setProfile_shared_date(profileDetailsView.getProfile_shared_date());
        return profileDetails;
    }

    public ProfileDetailsView buildFileUploadView(ProfileDetails fileUpload) {
        ProfileDetailsView fileUploadView = new ProfileDetailsView();
        fileUploadView.setBytes(fileUpload.getFile());
        fileUploadView.setProfile_id(fileUpload.getProfile_id());
        fileUploadView.setIntake_date(fileUpload.getIntake_date());
        fileUploadView.setAssociate_id(fileUpload.getAssociate_id());
        fileUploadView.setObtid(fileUpload.getObtid());
        fileUploadView.setOnsite_poc_details(fileUpload.getPoc_details());
        fileUploadView.setProfile_shared_date(fileUpload.getProfile_shared_date());
        fileUploadView.setRemarks(fileUpload.getRemarks());
        fileUploadView.setResult(fileUpload.getResult());
        fileUploadView.setResult_date(fileUpload.getResult_date());
        return fileUploadView;
    }
}
