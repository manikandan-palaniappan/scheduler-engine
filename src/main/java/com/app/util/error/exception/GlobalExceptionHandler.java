/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.util.error.exception;

import com.app.util.error.Errors;
import org.springframework.http.HttpStatus;  
import org.springframework.web.bind.annotation.ControllerAdvice;  
import org.springframework.web.bind.annotation.ExceptionHandler;  
import org.springframework.web.bind.annotation.ResponseStatus;  
import org.springframework.web.bind.annotation.RestController;  
  
/** 
 * @author ekansh 
 * @since 19/2/16 
 */  
@ControllerAdvice  
@RestController  
public class GlobalExceptionHandler {  
  
    @ResponseStatus(HttpStatus.BAD_REQUEST)  
    @ExceptionHandler(value = ApplicationException.class)  
    public Errors handleBaseException(ApplicationException e){        
        return e.getErrors();  
    }  
  
    @ExceptionHandler(value = Exception.class)
    public String handleException(Exception e){return e.getMessage();}  
  
  
}  

