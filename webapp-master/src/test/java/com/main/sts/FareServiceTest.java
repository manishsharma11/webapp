package com.main.sts;

import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;

import com.main.sts.entities.BusFarePriceEntity;
import com.main.sts.entities.Stops;
import com.main.sts.service.FareService;
import com.main.sts.service.GoogleMapService;
import com.main.sts.service.GoogleMapService.DistanceAndTimeDuration;
import com.main.sts.service.RouteStopsService;
import com.main.sts.service.StopsService;
import com.main.sts.service.VehicleTrackingService.DistanceMatrix;

public class FareServiceTest extends BaseTest {

    @Resource
    RouteStopsService routeStopService;

    @Resource
    private FareService fareService;

    @Resource
    private StopsService stopsService;
    
    @Resource
    private GoogleMapService googleMapService;
    

    @Test
    public void testFindAll() {
        Assert.assertFalse(fareService.findAll().isEmpty());
        for (BusFarePriceEntity c : fareService.findAll()) {
            System.out.println(c.getSource_stop_id() + "--" + c.getDest_stop_id());
            System.out.println(c.getSource_stop().getStop_name() + "--" + c.getDest_stop().getStop_name());
        }
    }

    @Test
    public void findAllStops() {
        List<Stops> stops = routeStopService.getAllStops();
        for (Stops stop : stops) {
            for (Stops stop1 : stops) {
                if (stop.getId() == stop1.getId()) {
                    continue;
                }
                BusFarePriceEntity entity = fareService.fetchFare(stop.getId(), stop1.getId());
                if (entity == null) {
                    System.out.println("can't find " + stop.getStop_name() + "---" + stop1.getStop_name());
                }
            }
        }
    }
    
    @Test
    public void findAllStopsThatFall() {
        List<Stops> stops = stopsService.getAllStops();
        for (Stops stop : stops) {
            for (Stops stop1 : stops) {
                if (stop.getId() == stop1.getId()) {
                    continue;
                }
                BusFarePriceEntity entity = fareService.fetchFare(stop.getId(), stop1.getId());
                if (entity == null) {
                    System.out.println("can't find " + stop.getStop_name() + "---" + stop1.getStop_name());
                }
            }
        }
    }

    @Test
    public void fetchFare() {
        int source_stop_id = 33;
        int dest_stop_id = 89;
        BusFarePriceEntity entity = fareService.fetchFare(source_stop_id, dest_stop_id);
        System.out.println(entity);
    }
    
    @Test
    public void uploadAllDistanceAndTimeDuration() {
        Assert.assertFalse(fareService.findAll().isEmpty());
        for (BusFarePriceEntity c : fareService.findAll()) {
            System.out.println(c.getSource_stop_id() + "--" + c.getDest_stop_id());
            System.out.println(c.getSource_stop().getStop_name() + "--" + c.getDest_stop().getStop_name());

            if (!c.getDistance().startsWith("km")) {
                continue;
            }
            Stops sourceStop = c.getSource_stop();
            Stops destStop = c.getDest_stop();
            DistanceMatrix dm = new DistanceMatrix();
            dm.vehicle_lat = sourceStop.getLatitude().trim();
            dm.vehicle_long = sourceStop.getLongitude().trim();
            dm.booking_stop_lat = destStop.getLatitude().trim();
            dm.booking_stop_long = destStop.getLongitude().trim();
            DistanceAndTimeDuration dtd = googleMapService.getDistanceAndTimeDuration(dm);
            c.setDistance(dtd.distance);
            c.setTime_duration(dtd.time_duration);
            System.out.println(c.getSource_stop().getStop_name()+"--"+c.getDest_stop().getStop_name()+"--"+dtd.distance+"---"+dtd.time_duration);
            fareService.updateFare(c);
        }
    }
}
