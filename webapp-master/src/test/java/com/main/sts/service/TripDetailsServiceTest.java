package com.main.sts.service;

import javax.annotation.Resource;

import org.junit.Test;

import com.main.sts.BaseTest;
import com.main.sts.entities.TripDetail;

public class TripDetailsServiceTest extends BaseTest {

    @Resource
    private TripDetailService tripDetailService;

    @Test
    public void testAddTripDetail() {
        TripDetail td = new TripDetail();
        td.setTrip_name("abcd");
        td.setBusid(740);
        td.setRouteid(32);
        tripDetailService.insertTripDetail(td);
    }
}
