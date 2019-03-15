package com.main.sts;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;

import com.main.sts.entities.RouteStops;
import com.main.sts.entities.Stops;
import com.main.sts.service.RouteStopsService;

public class RouteStopServiceTest extends BaseTest {

    @Resource
    private RouteStopsService routeStopService;

    @Test
    public void testFindAll() {
        Assert.assertFalse(routeStopService.findAll().isEmpty());
        for (Stops c : routeStopService.findAll()) {
            System.out.println(c.getStop_name());
        }
    }

    @Test
    public void testFindAllAvailableFromStops() {
        Set<RouteStops> routeStops = routeStopService.getAllAvailableFromStops();
        for (RouteStops rs : routeStops) {
            System.out.println(rs.getId() + "--" +rs.getStop_id() + "--" + rs.getStop_number() + "--" + rs.getStop().getStop_name());
        }
    }

    @Test
    public void testFindAllAvailableToStops() {
        int stop_id = 741;
        stop_id=33;
        List<RouteStops> routeStops = routeStopService.getAllAvailableToStops(stop_id);
        for (RouteStops rs : routeStops) {
            System.out.println(rs.getId() + "--" + rs.getStop_number() + "--" + rs.getStop().getStop_name());
        }
    }

    @Test
    public void testFindAllAvailableFromAndToStops() {
        Set<RouteStops> routeFromStops = routeStopService.getAllAvailableFromStops();
        for (RouteStops rs : routeFromStops) {
            System.out.println(rs.getId() + "--" + rs.getStop_number() + "--" + rs.getStop().getStop_name());
            System.out.print("-------------");
            List<RouteStops> routeToStops = routeStopService.getAllAvailableToStops(rs.getStop_id());
            for (RouteStops rs1 : routeToStops) {
                System.out.println("\t\t"+rs1.getId() +" "+rs1.getStop_id() + "--" + rs1.getStop_number() + "--" + rs1.getStop().getStop_name());
            }
        }
    }
    
    @Test
    public void testFindAllAvailableFromAndToStops1() {
        Set<RouteStops> routeFromStops = routeStopService.getAllAvailableFromStops();
        for (RouteStops rs : routeFromStops) {
            System.out.println(rs.getId() + "--" + rs.getStop_number() + "--" + rs.getStop().getStop_name());
            System.out.print("-------------");
        }
        
        List<RouteStops> routeToStops = routeStopService.getAllAvailableToStops(34);
        for (RouteStops rs1 : routeToStops) {
            System.out.println("\t\t"+rs1.getId() +" "+rs1.getStop_id() + "--" + rs1.getStop_number() + "--" + rs1.getStop().getStop_name());
        }
    }


}
