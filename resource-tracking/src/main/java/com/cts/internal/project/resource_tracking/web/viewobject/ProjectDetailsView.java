package com.cts.internal.project.resource_tracking.web.viewobject;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class ProjectDetailsView {
    private String obtid;
    @NotEmpty
    private String pname;
    @NotEmpty
    private String practice;
    @NotEmpty
    private String pdescription;
    @NotEmpty
    private String jdescription;
    @NotEmpty
    private String portfolio;
    @NotEmpty
    private String location;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date StartDate;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date EndDate;
    @NotEmpty
    private String duration;
    @NotEmpty
    private String dutch;

    public String getObtid() {
        return obtid;
    }

    public void setObtid(String obtid) {
        this.obtid = obtid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPractice() {
        return practice;
    }

    public void setPractice(String practice) {
        this.practice = practice;
    }

    public String getPdescription() {
        return pdescription;
    }

    public void setPdescription(String pdescription) {
        this.pdescription = pdescription;
    }

    public String getJdescription() {
        return jdescription;
    }

    public void setJdescription(String jdescription) {
        this.jdescription = jdescription;
    }

    public String getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(String portfolio) {
        this.portfolio = portfolio;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDutch() {
        return dutch;
    }

    public void setDutch(String dutch) {
        this.dutch = dutch;
    }
}
