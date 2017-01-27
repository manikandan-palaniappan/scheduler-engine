/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.repository;

import com.app.model.Task;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Manikandan
 */
public interface TaskRepository extends PagingAndSortingRepository<Task, Long>{
    
}
