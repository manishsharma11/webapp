package com.main.sts;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;

import com.main.sts.entities.RouteStops;
import com.main.sts.service.StopsService;

public class StopServiceTest extends BaseTest {

    @Resource
    private StopsService stopService;

    @Test
    public void findAllStops() {
        Assert.assertFalse(stopService.getAllStops().isEmpty());
        Assert.assertFalse(stopService.getAllAvailableFromStops().isEmpty());
    }
    
    @Test
    public void findToStops(){
        List<RouteStops> routeStopsList = null;
        int from_stop_id = 1;//129;
        try {
            routeStopsList = stopService.getAllAvailableToStops(from_stop_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (RouteStops routeStops : routeStopsList) {
            System.out.println(routeStops.getRoute_id() +"--"+routeStops.getStop().getStop_name());
        }
    }
    
    @Test
    public void findFromStops() {
        List<RouteStops> routeStopsList = null;
        int from_stop_id = 1;// 129;
        try {
            routeStopsList = stopService.getAllAvailableToStops(from_stop_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (RouteStops routeStops : routeStopsList) {
            System.out.println(stopService.toStopsDTO(routeStops));
        }
    }
}
