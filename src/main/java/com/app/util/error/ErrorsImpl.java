package com.app.util.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Stores and exposes information about data-binding and validation errors of a specific object.
 *
 */
@Component
public class ErrorsImpl implements Errors {

    /** Message source attribute for internationalization support. */
    @Autowired
    private MessageSource messageSource;

    /** Global error list attribute. */
    private List<String> globalError = new ArrayList<String>();

    /** Field errors attribute. */
    private HashMap<String, String> fieldErrors = new HashMap<String, String>();

    @Override
    public List<String> getGlobalError() {

        System.out.println("Get global Error object : " + this.globalError);
        return globalError;
    }

    @Override
    public void addGlobalError(String errorMessage) {
        try {
            errorMessage = messageSource.getMessage(errorMessage, new Object[] {}, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException ex) {
            ex.printStackTrace();
            //Do nothing, the i18n key will be returned
        } catch (NullPointerException ex) {
            //Do nothing, the i18n key will be returned
            ex.printStackTrace();
        }
        this.globalError.add(errorMessage);
    }

    @Override
    public HashMap<String, String> getFieldErrors() {
        System.out.println("Get field Error object : " + this.fieldErrors);
        return fieldErrors;
    }

    @Override
    public void addFieldError(String field, String errorMessage) {
        try {            
            errorMessage = messageSource.getMessage(errorMessage, new Object[] {}, LocaleContextHolder.getLocale());            
        } catch (NoSuchMessageException ex) {
            //Do nothing, the i18n key will be returned
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            //Do nothing, the i18n key will be returned
            ex.printStackTrace();
        }
        this.fieldErrors.put(field, errorMessage);
    }

    @Override
    public void setFieldErrors(Map<String, String> errorsMap) {
        this.fieldErrors.putAll(errorsMap);
    }

    @Override
    public Boolean hasErrors() {
        return !(fieldErrors.isEmpty() && globalError.isEmpty());
    }
}
