/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller;

import com.app.model.Job;
import com.app.util.web.ApiController;
import com.app.util.web.CRUDController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author U43723
 */
@RestController
@RequestMapping("/api/job")
public class JobController extends CRUDController<Job> implements ApiController {
    
    /** Logger constant. */
    private static final Logger LOGGER = LoggerFactory.getLogger(JobController.class);
    
    @RequestMapping("/index")
    public String index() {
        return "Greetings from Spring Boot JOB Controller !";
    }
    
}
