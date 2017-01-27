/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.service;

import com.app.model.Task;
import com.app.util.domain.CRUDService;
import org.springframework.stereotype.Service;

/**
 *
 * @author Manikandan
 */
@Service
public interface TaskService extends CRUDService<Task>{
    
}
