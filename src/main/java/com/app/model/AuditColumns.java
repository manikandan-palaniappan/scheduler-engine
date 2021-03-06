package com.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.xml.bind.annotation.XmlTransient;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Audit columns, for report.
 *
 * @author Abdul
 */
public class AuditColumns {


     /** Created by user. */
    @CreatedBy
    @JoinColumn(name = "created_user_id", updatable = false)
    @JsonIgnore
    @OneToOne
    private Long createdBy;

    /** Last updated by user. */
    @LastModifiedBy
    @JoinColumn(name = "updated_user_id")
    @JsonIgnore
    @OneToOne
    private Long updatedBy;

    /** Deleted by user. */
    @LastModifiedBy
    @JoinColumn(name = "deleted_user_id")
    @JsonIgnore
    @OneToOne
    private Long deletedBy;

    /** Created date and time. */
    @CreatedDate
    @DateTimeFormat(style = "M-")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date_time", updatable = false)
    private Date createdDateTime;

    /** Last modified date and time. */
    @LastModifiedDate
    @DateTimeFormat(style = "M-")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDateTime;

    /** Deleted date and time. */
    @LastModifiedDate
    @DateTimeFormat(style = "M-")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedDateTime;

    @Transient
    @JsonIgnore
    @XmlTransient
    private SimpleDateFormat format = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy", Locale.getDefault());


    /**
     * Get the createdBy.
     * @return createdBy
     */
    public Long getCreatedBy() {
        return createdBy;
    }

    /**
     * Set the createdBy.
     * @param createdBy - the User to set
     */
    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Get the updatedBy.
     * @return updatedBy
     */
    public Long getUpdatedBy() {
        return updatedBy;
    }

    /**
     * Set the updatedBy.
     * @param updatedBy - the User to set
     */
    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * Get the createdDateTime.
     * @return createdDateTime
     */
    public Date getCreatedDateTime() {

        if (this.createdDateTime == null) {
            return createdDateTime;
        } else {
            try {
                return format.parse(createdDateTime.toString());
            } catch (Exception ex) {
                return createdDateTime;
            }
        }
    }

    /**
     * Set the createdDateTime.
     * @param createdDateTime - the DateTime to set
     */
    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    /**
     * Get the lastModifiedDateTime.
     * @return lastModifiedDateTime
     */
    public Date getLastModifiedDateTime() {

        if (this.lastModifiedDateTime == null) {
            return lastModifiedDateTime;
        } else {
            try {
                return format.parse(lastModifiedDateTime.toString());
            } catch (Exception ex) {
                return lastModifiedDateTime;
            }
        }
    }

    /**
     * Set the lastModifiedDateTime.
     * @param lastModifiedDateTime - the DateTime to set
     */
    public void setLastModifiedDateTime(Date lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    /**
     * Get the deletedBy.
     * @return deletedBy
     */
    public Long getDeletedBy() {
        return deletedBy;
    }

    /**
     * Set the deletedBy.
     * @param deletedBy - the User to set
     */
    public void setDeletedBy(Long deletedBy) {
        this.deletedBy = deletedBy;
    }

    /**
     * Get the deletedDateTime.
     * @return deletedDateTime
     */
    public Date getDeletedDateTime() {

        if (this.deletedDateTime == null) {
            return deletedDateTime;
        } else {
            try {
                return format.parse(deletedDateTime.toString());
            } catch (Exception ex) {
                return deletedDateTime;
            }
        }
    }

    /**
     * Set the deletedDateTime.
     * @param deletedDateTime - the DateTime to set
     */
    public void setDeletedDateTime(Date deletedDateTime) {
        this.deletedDateTime = deletedDateTime;
    }

}
