package com.main.sts.dto.response;

import java.io.Serializable;

public class VersionCheckDTO implements Serializable {

    public String app_version;

    public String getApp_version() {
        return app_version;
    }

    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }
}
