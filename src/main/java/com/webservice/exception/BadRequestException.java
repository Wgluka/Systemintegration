package com.webservice.exception;

/**
 * Created by yukai on 2016/10/13.
 */
public class BadRequestException extends RuntimeException {
    private String error;
    private String description;

    public BadRequestException(String description, String error) {
        this.description = description;
        this.error = error;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getError() {

        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
