package com.cts.internal.project.resource_tracking.data.model;


import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Component
@Table(name = "Resource_Details")
public class ResourceDetails {
    @Id
    @Column(name = "obt_code")
    private String obtid;

    @Column(name = "resource_type")
    private String type;

    @Column(name = "resource_role")
    private String role;

    @Column(name = "resource_responsibility")
    private String responsibility;

    @Column(name = "resource_technology")
    private String technology;

    @Column(name = "resource_level")
    private String level;

    @Column(name = "request_received_date")
    private Date rdate;

    public String getObtid() {
        return obtid;
    }

    public void setObtid(String obtid) {
        this.obtid = obtid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Date getRdate() {
        return rdate;
    }

    public void setRdate(Date rdate) {
        this.rdate = rdate;
    }
}
