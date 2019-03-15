package com.main.sts.service;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;

import com.main.sts.BaseTest;
import com.main.sts.common.CommonConstants.TripStatus;
import com.main.sts.entities.DailyBusCoordinates;
import com.main.sts.entities.DailyRunningBuses;
import com.main.sts.util.DateUtil;

public class DailyRunningBusServiceTest extends BaseTest {

    @Resource
    private DailyRunningBusesService service;

    @Test
    public void testgetDailyRunningBus() {
        for (DailyRunningBuses e : service.getDailyRunningBus(new Date(), TripStatus.RUNNING)) {
            System.out.println(e.getVehicle_id() + "--" + e);
        }
    }

    @Test
    public void test1() {
        int trip_id = 40;
        Date current_date = DateUtil.getTodayDateWithOnlyDate();
        DailyRunningBuses b = service.getDailyRunningBusWithRunningStatus(current_date, trip_id);
        System.out.println(b);

        DailyBusCoordinates coordinates = service.getLatestBusCoordinates(current_date, trip_id);
        System.out.println(coordinates);
    }
}
