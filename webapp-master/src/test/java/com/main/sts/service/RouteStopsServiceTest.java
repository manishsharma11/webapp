package com.main.sts.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.main.sts.BaseTest;
import com.main.sts.dto.RouteStopsDTO;
import com.main.sts.dto.StopDTO;

public class RouteStopsServiceTest extends BaseTest {

    @Resource
    private RouteStopsService routeStopsService;
    
    @Resource
    private FareService fareService;

    @Test
    public void testGetAllAvailableRoutesWithStops() {
        List<RouteStopsDTO> routeStops = routeStopsService.getAllAvailableRoutesWithStops(true);
        for (RouteStopsDTO rs : routeStops) {
            System.out.println("rs:" + rs.getRoute_name() + "--" + rs.getTimings() +"---"+rs);
            for (StopDTO s : rs.getStops()) {
                System.out.println("\t\t s:" + s.getStop_name() + "--" + s.isStart_stop() + "--" + s.isEnd_stop()
                        + "---" + s);
            }
        }
    }
    
    @Test
    public void testGetAllAvailableRoutesWithStops_IdentifyStopsWithoutFareDefined() {
        List<RouteStopsDTO> routeStops = routeStopsService.getAllAvailableRoutesWithStops(true);
        for (final RouteStopsDTO rs : routeStops) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("rs:" + rs.getRoute_name() + "--" + rs.getTimings() + "---" + rs);
                    for (StopDTO s1 : rs.getStops()) {
                        for (StopDTO s2 : rs.getStops()) {
                            if (s1.getStop_id() != s2.getStop_id()) {
                                System.out.println("======>>>"+s1.getStop_name() + "--" + s2.getStop_name() + "=="
                                        + fareService.fetchFare(s1.getStop_id(), s2.getStop_id()));
                            }
                        }
                    }
                }
            });
            t.start();
        }
        try {
            Thread.sleep(3000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
