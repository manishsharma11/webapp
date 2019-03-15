package com.main.sts.messageworkers;

public class Sms {

    private String sendTo;
    private String message;

    public Sms(String sendTo, String message) {
        this.message = message;
        this.sendTo = sendTo;
    }

    public String getSendTo() {
        return sendTo;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Sms [sendTo=" + sendTo + ", message=" + message + "]";
    }

}
