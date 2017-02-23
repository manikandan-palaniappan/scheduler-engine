/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.service;

import com.app.model.Job;
import com.app.repository.JobRepository;
import com.app.util.domain.PagingAndSorting;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.api.dkron.resources.JobResource;
import com.app.util.domain.AppValidator;
import com.app.util.error.Errors;
import com.app.util.error.exception.ApplicationException;
import java.util.logging.Level;
import org.springframework.beans.BeanUtils;

/**
 *
 * @author Manikandan
 */
@Service
public class JobServiceImpl implements JobService {
    
    /** Logger constant. */
    private static final Logger LOGGER = LoggerFactory.getLogger(JobServiceImpl.class);
    
    private com.api.dkron.models.Job dkronJob;
    
    private JobResource dkronJobResource = new JobResource();
    
    /** Dkron server baseUri. */
    @Value("${dkron.server.baseUri}")
    private String dkronBaseUri;
    
    @Autowired
    private JobRepository jobRepository;
    
    @Autowired
    AppValidator validator;

    @Override
    public Job create(Job job) throws Exception {
        
        Errors errors = validator.rejectIfNullEntity("Job", job);

        //Validate Job entity
        //Todo : Need to throw errors properly
        errors = validator.validateEntity(job, errors);
        
        Job dbJob = jobRepository.findByNameAndDeleted(job.getName(), false);
        if(dbJob != null) {
            errors.addFieldError("name", "common.job.name.unique");
        }
        
        if (errors.hasErrors()) {
            LOGGER.info("Errors ========>" +  errors.getFieldErrors());
            throw new ApplicationException(errors);
            
        } else {
            
            try {
                            
                dkronJob = new com.api.dkron.models.Job();

                BeanUtils.copyProperties(job, dkronJob);

                LOGGER.info("DkronBaseUri "+dkronBaseUri);
                LOGGER.info("Name "+dkronJob.getName());
                LOGGER.info("Command "+dkronJob.getCommand());
                LOGGER.info("Schedule "+dkronJob.getSchedule());
                LOGGER.info("Disabled "+dkronJob.getDisabled());

                dkronJob = dkronJobResource.createOrUpdateJob(dkronJob, dkronBaseUri);

                if(dkronJob != null) {

                   LOGGER.info(" Job created in dkron server successfully");
                   job.setCreatedDateTime(new Date());
                   job = jobRepository.save(job);
                   LOGGER.info(" Job created in scheduler engine successfully");               
                }  

               return job;

            } catch (Throwable ex) {

                java.util.logging.Logger.getLogger(JobServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                return null;            

            }
        }                                        
    }

    @Override
    public Job update(Job job) throws Exception {
        
        Errors errors = validator.rejectIfNullEntity("Job", job);

        //Validate Job entity
        errors = validator.validateEntity(job, errors);
        
        Job dbJob = jobRepository.findByIdAndDeleted(job.getId(), false);
        if(!dbJob.getName().equals(job.getName())) {
            errors.addFieldError("name", "Job name cannot be changed");
        }
        
        if (errors.hasErrors()) {
            LOGGER.info("Errors ========>" +  errors.getFieldErrors());
            throw new ApplicationException(errors);

        } else {                    
        
            try {

                dkronJob = new com.api.dkron.models.Job();

                BeanUtils.copyProperties(job, dkronJob);

                LOGGER.info("DkronBaseUri "+dkronBaseUri);
                LOGGER.info("Name "+dkronJob.getName());
                LOGGER.info("Command "+dkronJob.getCommand());
                LOGGER.info("Schedule "+dkronJob.getSchedule());
                LOGGER.info("Disabled "+dkronJob.getDisabled());

                dkronJob = dkronJobResource.createOrUpdateJob(dkronJob, dkronBaseUri);

                if(dkronJob != null) {

                   LOGGER.info(" Job updated in dkron server successfully");
                   job.setLastModifiedDateTime(new Date());
                   job = jobRepository.save(job);
                   LOGGER.info(" Job updated in scheduler engine successfully");               
                }  

               return job;

            } catch (Throwable ex) {

                java.util.logging.Logger.getLogger(JobServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                return null;            

            }  
        }    
    }

    @Override
    public Job find(Long id) throws Exception {
        return jobRepository.findByIdAndDeleted(id, false);
    }

    @Override
    public Iterable<Job> findAll() throws Exception {
        return jobRepository.findAll();
    }

    @Override
    public Page<Job> findAll(PagingAndSorting pagingAndSorting) throws Exception {
        return jobRepository.findAll(pagingAndSorting.toPageRequest());
    }

    @Override
    public Iterable<Job> findAllByDeleted(Boolean deleted) {
        return jobRepository.findAllByDeleted(deleted);
    }

    @Override
    public void delete(Job job) throws Exception {
        
        if (job == null) {
            throw new Exception("Job cannot be deleted");
        }
        
        try {
                
                dkronJobResource.deleteJob(job.getName(), dkronBaseUri);
                LOGGER.info(job.getName() + " Job deleted in dkron server successfully");
                
                //soft delete job
                job.setDeleted(true);
                job.setDeletedDateTime(new Date());
                jobRepository.save(job);                
                LOGGER.info(job.getName() + " Job soft deleted in scheduler engine successfully");
                
            } catch (Throwable ex) {
                
                java.util.logging.Logger.getLogger(JobServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
                
    }

    @Override
    public void delete(Long id) throws Exception {
        
        if (id == null) {
            throw new Exception("Job cannot be deleted");
        }
        
        Job dbJob = jobRepository.findOne(id);
            
        try {

            dkronJobResource.deleteJob(dbJob.getName(), dkronBaseUri);
            LOGGER.info(dbJob.getName() + " Job deleted in dkron server successfully");

            //soft delete job
            dbJob.setDeleted(true);
            dbJob.setDeletedDateTime(new Date());
            jobRepository.save(dbJob);                
            LOGGER.info(dbJob.getName() + " Job soft deleted in scheduler engine successfully");

        } catch (Throwable ex) {

            java.util.logging.Logger.getLogger(JobServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }

}
