/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.service;

import com.app.controller.JobController;
import com.app.model.Job;
import com.app.repository.JobRepository;
import com.app.util.domain.PagingAndSorting;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 *
 * @author Manikandan
 */
@Service
public class JobServiceImpl implements JobService {
    
    /** Logger constant. */
    private static final Logger LOGGER = LoggerFactory.getLogger(JobController.class);
    
     @Autowired
    private JobRepository jobRepository;

    @Override
    public Job create(Job job) throws Exception {
        job.setCreatedDateTime(new Date());
        return jobRepository.save(job);
    }

    @Override
    public Job update(Job job) throws Exception {
        job.setLastModifiedDateTime(new Date());
        return jobRepository.save(job);
    }

    @Override
    public Job find(Long id) throws Exception {
        return jobRepository.findOne(id);
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
        jobRepository.delete(job);
    }

    @Override
    public void delete(Long id) throws Exception {
        jobRepository.delete(id);
    }

}
