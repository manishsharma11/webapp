package com.main.sts.dto.response;

import java.io.Serializable;

public class VersionCheckResponseDTO implements Serializable {

    public String current_app_version;
    public String version_check_update_message;
    public boolean is_updated;
    public boolean force_to_update;

    public String getCurrent_app_version() {
        return current_app_version;
    }
    public void setCurrent_app_version(String current_app_version) {
        this.current_app_version = current_app_version;
    }
    public String getVersion_check_update_message() {
        return version_check_update_message;
    }
    public void setVersion_check_update_message(String version_check_update_message) {
        this.version_check_update_message = version_check_update_message;
    }
    public boolean isIs_updated() {
        return is_updated;
    }
    public void setIs_updated(boolean is_updated) {
        this.is_updated = is_updated;
    }
    public boolean isForce_to_update() {
        return force_to_update;
    }
    public void setForce_to_update(boolean force_to_update) {
        this.force_to_update = force_to_update;
    }

}
