package com.cts.internal.project.resource_tracking.web.adapter;

import com.cts.internal.project.resource_tracking.data.model.ProfileDetails;
import com.cts.internal.project.resource_tracking.web.viewobject.ProfileDetailsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AddProfileAdapter {
    @Autowired
    private ProfileDetails fileUpload;

    public ProfileDetails buildFileUploadEntity(ProfileDetailsView fileUploadView) {
        fileUpload.setProfile_id(fileUploadView.getProfile_id());
        fileUpload.setObtid(fileUploadView.getObtid());
        fileUpload.setAssociate_id(fileUploadView.getAssociate_id());
        try {
            fileUpload.setFile(fileUploadView.getFile().getBytes());
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
        fileUpload.setIntake_date(fileUploadView.getIntake_date());
        fileUpload.setPoc_details(fileUploadView.getOnsite_poc_details());
        fileUpload.setResult(fileUploadView.getResult());
        fileUpload.setResult_date(fileUploadView.getResult_date());
        fileUpload.setRemarks(fileUploadView.getRemarks());
        fileUpload.setProfile_shared_date(fileUploadView.getProfile_shared_date());
        return fileUpload;
    }

    public ProfileDetailsView buildFileUploadView(ProfileDetails fileUpload) {
        ProfileDetailsView fileUploadView = new ProfileDetailsView();
/*        try {
            OutputStream outputStream=new FileOutputStream(String.valueOf(file));
            outputStream.write(fileUpload.getFile());
        } catch (FileNotFoundException e) {
            System.out.println("file not found exception"+e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException"+e.getMessage());
        }
        fileUploadView.setFile(file);*/
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
