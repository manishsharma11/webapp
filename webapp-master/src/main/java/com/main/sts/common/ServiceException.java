package com.main.sts.common;

import com.main.sts.service.ReturnCodes;

/**
 * Common Service exception.
 * 
 * @author rahul
 *
 */
public class ServiceException extends Exception {

    public static enum ErrorType {
        ILLEGAL_ARGUMENT;
    }

    private ErrorType errorType;
    private ReturnCodes returnCode;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, ReturnCodes returnCode) {
        super(message);
        this.returnCode = returnCode;
    }

    public ServiceException(String message, ErrorType errorType, ReturnCodes returnCode) {
        super(message);
        this.errorType = errorType;
        this.returnCode = returnCode;
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message, Throwable cause, ReturnCodes returnCode) {
        super(message, cause);
        this.returnCode = returnCode;
    }

    public ReturnCodes getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(ReturnCodes returnCode) {
        this.returnCode = returnCode;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

}
