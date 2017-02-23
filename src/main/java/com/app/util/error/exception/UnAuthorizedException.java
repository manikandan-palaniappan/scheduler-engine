/**
 *
 */
package com.app.util.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author gemini01
 *
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnAuthorizedException extends Exception {
    /**
     * UnAuthorizedException.
     * @param message  error message,
     */
    public UnAuthorizedException(String message) {
        super(message);
    }

}
