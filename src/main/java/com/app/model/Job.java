/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Manikandan
 */
@Entity
public class Job extends AuditColumns {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
        
    private String name;
    
    private String serviceOwner;
    
    private String referenceId;
    
    private String schedule;   
    
    private String command;
    
    private Boolean disabled;
    
    private Integer errorCount;
    
    private Date lastError;
    
    private Date lastSuccess;
    
    private Integer successCount;
    
    private Boolean shell;
    
    private JobStatus jobStatus;
    
    private String description;
    
    private SchedulerType schedulerType;
    
    @DateTimeFormat(style = "M-")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    
    @DateTimeFormat(style = "M-")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    
    private String dockerImage;
    
    private String location;   
    
    private Boolean isRetryEnabled;
    
    private Long retryCounts;
    
    private boolean deleted = false;
    
    @OneToMany(mappedBy = "job")
    private List<Task> tasks;
    
    @Version    
    @JsonIgnore
    private Integer version;
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }   

    public String getServiceOwner() {
        return serviceOwner;
    }

    public void setServiceOwner(String serviceOwner) {
        this.serviceOwner = serviceOwner;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SchedulerType getSchedulerType() {
        return schedulerType;
    }

    public void setSchedulerType(SchedulerType schedulerType) {
        this.schedulerType = schedulerType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDockerImage() {
        return dockerImage;
    }

    public void setDockerImage(String dockerImage) {
        this.dockerImage = dockerImage;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getIsRetryEnabled() {
        return isRetryEnabled;
    }

    public void setIsRetryEnabled(Boolean isRetryEnabled) {
        this.isRetryEnabled = isRetryEnabled;
    }

    public Long getRetryCounts() {
        return retryCounts;
    }

    public void setRetryCounts(Long retryCounts) {
        this.retryCounts = retryCounts;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }        

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Integer getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }

    public Date getLastError() {
        return lastError;
    }

    public void setLastError(Date lastError) {
        this.lastError = lastError;
    }

    public Date getLastSuccess() {
        return lastSuccess;
    }

    public void setLastSuccess(Date lastSuccess) {
        this.lastSuccess = lastSuccess;
    }

    public Integer getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(Integer successCount) {
        this.successCount = successCount;
    }

    public Boolean getShell() {
        return shell;
    }

    public void setShell(Boolean shell) {
        this.shell = shell;
    }
    
    
    
}
