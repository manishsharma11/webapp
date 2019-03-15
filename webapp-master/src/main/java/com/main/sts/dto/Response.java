package com.main.sts.dto;

import java.io.Serializable;

public class Response implements Serializable {

    private static final long serialVersionUID = 1L;

    public Object response;
    public String returnCode;
    public String message;

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}