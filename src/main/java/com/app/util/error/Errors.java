package com.app.util.error;


import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 * Stores and exposes information about data-binding and validation errors for a specific object.
 *
 */
@Component
public interface Errors {

    /**
     * Add global error.
     * @param errorMessage message to set
     */
    void addGlobalError(String errorMessage);

    /**
     * Get the list of global errors.
     * @return global error message
     */
    List<String> getGlobalError();

    /**
     * Set field errors map.
     *
     * @param errorsMap errors in field value pair
     */
    void setFieldErrors(Map<String, String> errorsMap);

    /**
     * Get field errors.
     *
     * @return map of errors
     */
    Map<String, String> getFieldErrors();

    /**
     * Add a field error.
     *
     * @param field name of the field which is not valid
     * @param errorMessage ,error message
     */
    void addFieldError(String field, String errorMessage);

    /**
     * Checks this entity has any field or global error.
     *
     * @return true if error is present, false otherwise.
     */
    Boolean hasErrors();
}
