package com.main.sts.service;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;

import com.main.sts.BaseTest;
import com.main.sts.common.CommonConstants.VehicleStatus;
import com.main.sts.entities.DailyBusNotifications;
import com.main.sts.util.DateUtil;

public class DailyBusNotificationServiceTest extends BaseTest {

    @Resource
    private DailyBusNotificationService service;

    @Test
    public void testGetTodysNotifications() {
        Date today = DateUtil.getTodayDateWithOnlyDate();
        for (DailyBusNotifications e : service.getTodysNotifications(today, VehicleStatus.ONTIME)) {
            System.out.println(e.getVehicle_id() + "--" + e);
        }
    }
}
