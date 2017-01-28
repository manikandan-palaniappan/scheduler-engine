/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.service;

import com.app.model.Job;
import com.app.util.domain.CRUDService;
import org.springframework.stereotype.Service;

/**
 *
 * @author Manikandan
 */
@Service
public interface JobService extends CRUDService<Job>{

    /**
     * This method get all jobs with specified by deleted value.
     *
     * @param deleted is the User is soft deleted.
     * @return the user list.
     */
    public Iterable<Job> findAllByDeleted(Boolean deleted);

}
