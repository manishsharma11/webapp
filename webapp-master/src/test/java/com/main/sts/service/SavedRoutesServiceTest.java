package com.main.sts.service;

import javax.annotation.Resource;

import org.junit.Test;

import com.main.sts.BaseTest;
import com.main.sts.dto.SavedRouteDTO;
import com.main.sts.dto.SavedRouteResponse;
import com.main.sts.entities.SavedRoutes;

public class SavedRoutesServiceTest extends BaseTest {

    @Resource
    private SavedRoutesService saveRouteService;

    @Test
    public void testSaveARoute() {
        SavedRouteDTO sdr = new SavedRouteDTO();
        sdr.setCommuter_id(1);
        sdr.setFrom_stop_id(1);
        sdr.setTo_stop_id(2);
        sdr.setPickup_time_hours(07);
        sdr.setPickup_time_min(30);
        sdr.setDropoff_time_hours(20);
        sdr.setDropoff_time_min(30);
        
        saveRouteService.saveARoute(sdr);
    }
    
    @Test
    public void testGetAllSavedRoutes() {
        for (SavedRoutes sr : saveRouteService.getAllSavedRoutes()) {
            System.out.println(sr);
        }
    }

    @Test
    public void testGetAllSavedRoutesRes() {
        int commuter_id = 12;
        for (SavedRouteResponse sr : saveRouteService.getAllSavedRoutesResponse(commuter_id)) {
            System.out.println(sr);
        }
    }
}
