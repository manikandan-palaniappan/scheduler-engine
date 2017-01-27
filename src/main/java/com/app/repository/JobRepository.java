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
    
}
