package com.ec.eventserver.service;

import javax.annotation.Resource;

import org.junit.Test;

import com.ec.eventserver.models.DeviceInfo;
import com.main.sts.BaseTest;

public class DeviceServiceTest extends BaseTest {

    @Resource
    private DeviceService deviceService;

    @Test
    public void insertDeviceInfo() {
        DeviceInfo device_info = new DeviceInfo();
        device_info.setHw_device_id("abcd");
        DeviceInfo newDeviceInfo = deviceService.insertDeviceInfo(device_info);
        System.out.println(newDeviceInfo);
    }
}
