/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller;

import com.app.model.Job;
import com.app.service.JobService;
import com.app.util.web.ApiController;
import com.app.util.web.CRUDController;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Manikandan
 */
@RestController
@RequestMapping("/api/job")
public class JobController extends CRUDController<Job> implements ApiController {
    
    /** Logger constant. */
    private static final Logger LOGGER = LoggerFactory.getLogger(JobController.class);
    
    @Autowired
    private JobService jobService;
        
    /**
     * This method is used to return the job list.
     *
     * @param deleted the boolean value - is job has soft deleted
     * @return the job list.
     * @throws Exception default exception.
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Job> list(@RequestParam(value = "deleted", required = false) Boolean deleted) throws Exception {
        if (deleted != null) {
            return jobService.findAllByDeleted(deleted);
        } else {
            return jobService.findAll();
        }

    }
    
    /**
     * This method is used to create new job.
     *
     * @param job
     * @return the success/ failure message.
     * @throws Exception default exception.
     */
    @RequestMapping (method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @Override
    public Job create(@RequestBody Job job) throws Exception {
        return jobService.create(job);
    }        
    
    /**
     * This method is used to update job.
     *
     * @param job
     * @param id the job ID.
     * @return success/failure of the update.
     * @throws Exception default exception.
     */
    @RequestMapping (value = "/{id}", method = RequestMethod.PUT,
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    @Override
     public Job update(@RequestBody Job job, @PathVariable(PATH_ID) Long id) throws  Exception {         
         return jobService.update(job);
     }   
    
    /**
     * This method is used to delete the job(Soft).
     *
     * @param id the job ID.
     * @throws Exception the default exception
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Override
    public void delete(@PathVariable(PATH_ID) Long id) throws Exception {

        //Get existing job
        Job job = jobService.find(id);                
        jobService.delete(job);
    }

    /**
     * This method is used to return the job by ID.
     *
     * @param id the job ID.
     * @return the job.
     * @throws Exception default exception.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @Override
    public Job read(@PathVariable(PATH_ID) Long id) throws Exception {
        return jobService.find(id);
    }        
    
}
