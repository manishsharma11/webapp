package com.main.sts.dto;

public class EmailResponse {
    private int code;
    private boolean success;
    private String message;

    public EmailResponse(int code, String msg) {
        this.code = code;
        this.success = code == 200;
        this.message = msg;
    }

    public int getCode() {
        return this.code;
    }

    public boolean getStatus() {
        return this.success;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return "EmailResponse [code=" + code + ", success=" + success + ", message=" + message + "]";
    }

}