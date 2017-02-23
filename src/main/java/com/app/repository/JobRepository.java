/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.repository;

import com.app.model.Job;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Manikandan
 */
public interface JobRepository extends PagingAndSortingRepository<Job, Long>{
    
    /**
     * This method get all job with specified by deleted value.
     *
     * @param deleted is job is soft deleted.
     * @return the group list.
     */
    Iterable<Job> findAllByDeleted(boolean deleted);
    
    /**
     * This method get job with specified id and by deleted value.
     *
     * @param id
     * @param deleted is job is soft deleted.
     * @return the job.
     */
    Job findByIdAndDeleted(Long id, boolean deleted);
    
    /**
     * This method get job with specified name and by deleted value.
     * 
     * @param name
     * @param deleted
     * @return 
     */
    Job findByNameAndDeleted(String name, boolean deleted);
    
}
