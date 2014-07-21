package com.cts.internal.project.resource_tracking.web.viewobject;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class ProfileDetailsView {

    @NotEmpty
    private String onsite_poc_details;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date intake_date;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date result_date;

    private String result;

    private String remarks;

    private String obtid;

    @NotEmpty
    private String associate_id;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date profile_shared_date;


    private MultipartFile file;

    private int profile_id;

    private byte[] bytes;

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public int getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(int profile_id) {
        this.profile_id = profile_id;
    }

    public String getOnsite_poc_details() {
        return onsite_poc_details;
    }

    public void setOnsite_poc_details(String onsite_poc_details) {
        this.onsite_poc_details = onsite_poc_details;
    }

    public Date getIntake_date() {
        return intake_date;
    }

    public void setIntake_date(Date intake_date) {
        this.intake_date = intake_date;
    }

    public Date getResult_date() {
        return result_date;
    }

    public void setResult_date(Date result_date) {
        this.result_date = result_date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getObtid() {
        return obtid;
    }

    public void setObtid(String obtid) {
        this.obtid = obtid;
    }

    public String getAssociate_id() {
        return associate_id;
    }

    public void setAssociate_id(String associate_id) {
        this.associate_id = associate_id;
    }

    public Date getProfile_shared_date() {
        return profile_shared_date;
    }

    public void setProfile_shared_date(Date profile_shared_date) {
        this.profile_shared_date = profile_shared_date;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
