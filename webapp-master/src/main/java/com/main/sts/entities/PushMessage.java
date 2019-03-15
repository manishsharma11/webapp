package com.main.sts.entities;

import java.io.Serializable;

public class PushMessage implements Serializable {

    String gcm_regid;
    String title;
    String message;

    public String getGcm_regid() {
        return gcm_regid;
    }
    public void setGcm_regid(String gcm_regid) {
        this.gcm_regid = gcm_regid;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
