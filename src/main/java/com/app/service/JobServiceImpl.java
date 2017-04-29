/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.service;

import com.api.dkron.exceptions.APIException;
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
        errors = validator.validateEntity(job, errors);
        
        Job dbJob = jobRepository.findByNameAndDeleted(job.getName(), false);
        if(dbJob != null) {
            errors.addFieldError("name", "common.job.name.unique");
        }  
        
        if(job.getSchedule() == null || job.getSchedule().equals("")) {
            errors.addFieldError("schedule", "common.job.schedule.mandatory");
        }
        
        if(job.getCommand() == null || job.getCommand().equals("")) {
            errors.addFieldError("command", "common.job.command.mandatory");
        }
        
        if (errors.hasErrors()) {            
            throw new ApplicationException(errors);
            
        } else {
            
            try {
                            
                dkronJob = new com.api.dkron.models.Job();

                BeanUtils.copyProperties(job, dkronJob);               

                dkronJob = dkronJobResource.createOrUpdateJob(dkronJob, dkronBaseUri);

                if(dkronJob != null) {
                   
                   job.setCreatedDateTime(new Date());
                   job.setName(dkronJob.getName());
                   job = jobRepository.save(job);
                   LOGGER.info(" Job created Successfully");               
                }                 

            } catch(APIException ex) {                                
                
                errors.addGlobalError(ex.getLocalizedMessage());
                throw new ApplicationException(errors);                                                
                
            } catch (Throwable ex) {
                java.util.logging.Logger.getLogger(JobServiceImpl.class.getName()).log(Level.SEVERE, null, ex);                          

            }
            
            return job;
        }                                        
    }

    @Override
    public Job update(Job job) throws Exception {                        
        
        Errors errors = validator.rejectIfNullEntity("Job", job);

        //Validate Job entity
        errors = validator.validateEntity(job, errors);
        
        if(job.getSchedule() == null || job.getSchedule().equals("")) {
            errors.addFieldError("schedule", "common.job.schedule.mandatory");
        }
        
        if(job.getCommand() == null || job.getCommand().equals("")) {
            errors.addFieldError("command", "common.job.command.mandatory");
        }
        
        Job dbJob = jobRepository.findByIdAndDeleted(job.getId(), false);        
        if(!dbJob.getName().equals(job.getName())) {
            errors.addFieldError("name", "common.job.name.cannot.be.changed");
        }
        
        if (errors.hasErrors()) {            
            throw new ApplicationException(errors);

        } else {                    
        
            try {

                BeanUtils.copyProperties(job, dbJob, "id");
                
                dkronJob = new com.api.dkron.models.Job();

                BeanUtils.copyProperties(dbJob, dkronJob);               

                dkronJob = dkronJobResource.createOrUpdateJob(dkronJob, dkronBaseUri);

                if(dkronJob != null) {

                   dbJob.setLastModifiedDateTime(new Date());
                   job.setName(dkronJob.getName());
                   dbJob = jobRepository.save(dbJob);
                   LOGGER.info(" Job updated Successfully");               
                }  

            } catch(APIException ex) {                                
                
                errors.addGlobalError(ex.getLocalizedMessage());
                throw new ApplicationException(errors);                                                
                
            } catch (Throwable ex) {
                java.util.logging.Logger.getLogger(JobServiceImpl.class.getName()).log(Level.SEVERE, null, ex);                          

            }
            
            return dbJob;
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
        
        Errors errors = validator.rejectIfNullEntity("Job", job);
        
        try {
                
                dkronJobResource.deleteJob(job.getName(), dkronBaseUri);                
                
                //soft delete job
                job.setDeleted(true);
                job.setDeletedDateTime(new Date());
                jobRepository.save(job);                
                LOGGER.info(job.getName() + " Job soft deleted Successfully");
                
            } catch(APIException ex) {                                
                
                errors.addGlobalError(ex.getLocalizedMessage());
                throw new ApplicationException(errors);                                                
                
            } catch (Throwable ex) {
                
                java.util.logging.Logger.getLogger(JobServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
                
    }

    @Override
    public void delete(Long id) throws Exception {
        
        if (id == null) {
            throw new Exception("Job cannot be deleted");
        }
        
        Job dbJob = jobRepository.findByIdAndDeleted(id, false);
        
        Errors errors = validator.rejectIfNullEntity("Job", dbJob);
            
        try {

            dkronJobResource.deleteJob(dbJob.getName(), dkronBaseUri);            

            //soft delete job
            dbJob.setDeleted(true);
            dbJob.setDeletedDateTime(new Date());
            jobRepository.save(dbJob);                
            LOGGER.info(dbJob.getName() + " Job soft deleted Successfully");

        } catch(APIException ex) {                                
                
            errors.addGlobalError(ex.getLocalizedMessage());
            throw new ApplicationException(errors);                                                

        } catch (Throwable ex) {

            java.util.logging.Logger.getLogger(JobServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }

}
