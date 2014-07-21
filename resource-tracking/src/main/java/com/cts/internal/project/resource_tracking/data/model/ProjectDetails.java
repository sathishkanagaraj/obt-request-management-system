package com.cts.internal.project.resource_tracking.data.model;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Component
@Table(name = "Project_Details")

public class ProjectDetails {
    @Id
    @Column(name = "obt_code")
    private String obtid;
    @Column(name = "project_name")
    private String pname;
    @Column(name = "practice")
    private String practice;
    @Column(name = "project_description")
    private String pdescription;
    @Column(name = "job_description")
    private String jdescription;
    @Column(name = "portfolio")
    private String portfolio;
    @Column(name = "location")
    private String location;
    @Column(name = "start_date")
    private Date StartDate;
    @Column(name = "end_date")
    private Date EndDate;
    @Column(name = "duration")
    private String duration;
    @Column(name = "dutch")
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