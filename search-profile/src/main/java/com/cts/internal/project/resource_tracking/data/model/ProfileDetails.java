package com.cts.internal.project.resource_tracking.data.model;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Entity
@Table(name = "resource_profile")
@XmlRootElement
@NamedQueries(

        value = {
                @NamedQuery(name = "SearchByid", query = "select profile from ProfileDetails profile where  profile.obtid=:obtid and profile.associate_id=:associate_id"),
                @NamedQuery(name = "UpdateProfile", query = "update ProfileDetails profile " +
                        "set profile.associate_id=:associate_id," +
                        "profile.intake_date=:intake_date,profile.obtid=:obtid,profile.poc_details=:poc_details," +
                        " profile.profile_shared_date=:profile_shared_date,profile.remarks=:remarks," +
                        "profile.result=:result,profile.result_date=:result_date,profile.file=:file where profile.profile_id=:profile_id"),
                @NamedQuery(name = "getResume", query = "select profile.file from ProfileDetails profile where profile.obtid=:obtid and profile.associate_id=:associate_id"),
                @NamedQuery(name = "SearchByObtid", query = "select profile from ProfileDetails profile where profile.obtid=:obtid")

        }
)
@Component
public class ProfileDetails {
    @Id
    @Column(name = "profile_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int profile_id;

    @Column(name = "obt_code")
    private String obtid;

    @Column(name = "profile_resume")
    @Lob
    @Basic(fetch = FetchType.EAGER)
    private byte[] file;

    @Column(name = "associate_id")
    private String associate_id;

    @Column(name = "profile_shared_date")
    private Date profile_shared_date;

    @Column(name = "intake_date")
    private Date intake_date;

    @Column(name = "onsite_poc_details")
    private String poc_details;

    @Column(name = "result_date")
    private Date result_date;

    @Column(name = "result")
    private String result;

    @Column(name = "remarks")
    private String remarks;

    public int getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(int profile_id) {
        this.profile_id = profile_id;
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

    public Date getIntake_date() {
        return intake_date;
    }

    public void setIntake_date(Date intake_date) {
        this.intake_date = intake_date;
    }

    public String getPoc_details() {
        return poc_details;
    }

    public void setPoc_details(String poc_details) {
        this.poc_details = poc_details;
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

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
