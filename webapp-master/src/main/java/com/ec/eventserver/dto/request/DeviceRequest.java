package com.ec.eventserver.dto.request;

import java.io.Serializable;

public class DeviceRequest implements Serializable {

    String hw_device_id;

    public String getHw_device_id() {
        return hw_device_id;
    }

    public void setHw_device_id(String hw_device_id) {
        this.hw_device_id = hw_device_id;
    }



}
