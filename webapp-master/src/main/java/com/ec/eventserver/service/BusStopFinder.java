package com.ec.eventserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.main.sts.entities.DashboardSettings;
import com.main.sts.entities.RouteStops;
import com.main.sts.entities.Stops;
import com.main.sts.entities.VehicleGpsData;
import com.main.sts.service.DashBoardSettingsService;
import com.main.sts.service.RouteStopsService;

@Component
public class BusStopFinder {

    @Autowired
    private DashBoardSettingsService boardSettingsService;

    @Autowired
    private RouteStopsService routeStopsService;

    @Autowired
    private FindDistance findDistance;

    @Autowired
    private SystemProperties systemProperties;

    public RouteStops find(int route_id, double latitude, double longitude) {
        double distance_to_find_stop = systemProperties.getDistance_to_find_stop();
        List<RouteStops> routeStops = routeStopsService.getAllRouteStops(route_id);
        double distance = 0.0;
        RouteStops found_stop = null;

        for (RouteStops routeStop : routeStops) {

            distance = findDistance.calculateDistance(latitude, longitude,
                    Double.parseDouble(routeStop.getStop().getLatitude()),
                    Double.parseDouble(routeStop.getStop().getLongitude()));
            // System.out.println("Busstop  " +
            // routeStop.getStop().getStop_name() + " --> " + distance + "<=" +
            // distance_to_find_stop);
            if (distance <= distance_to_find_stop) {
                found_stop = routeStop;
                break;
            }

        }
        // System.out.println("Stop found --> " + found_stop);
        return found_stop;
    }

    public boolean isBusAtSchool(Double latitude, Double longitude) {
        double distance_to_find_stop = systemProperties.getDistance_to_find_stop();

        DashboardSettings dashboardSettings = boardSettingsService.getDashBoardSettings();
        double distance = findDistance.calculateDistance(latitude, longitude, dashboardSettings.getSchool_latitude(),
                dashboardSettings.getSchool_longitude());
        // System.out.println("BusStopFinder --> dis" + distance);

        if (distance <= distance_to_find_stop) {
            return true;
        } else
            return false;
    }

    public boolean isBusOutOfFirstStop(double latitude, double longitude, String first_stop_latitude,
            String first_stop_longitude) {
        return isBusOutOfFirstStop(latitude, longitude,
                Double.parseDouble(first_stop_latitude), Double.parseDouble(first_stop_longitude));
    }
    
    public boolean isBusOutOfFirstStop(String latitude, String longitude, String first_stop_latitude,
            String first_stop_longitude) {
        return isBusOutOfFirstStop(Double.parseDouble(latitude), Double.parseDouble(longitude),
                Double.parseDouble(first_stop_latitude), Double.parseDouble(first_stop_longitude));
    }

    public boolean isBusOutOfFirstStop(Double latitude, Double longitude, Double first_stop_latitude, Double first_stop_longitude) {
        double distance_to_find_stop = systemProperties.getDistance_to_find_stop();
        double distance_to_find_bus_outof_stop = systemProperties.getDistance_to_find_bus_outof_stop();
        double distance = findDistance.calculateDistance(latitude, longitude, first_stop_latitude,
                first_stop_longitude);
        // System.out.println("BusStopFinder --> dis" + distance);
        if (distance > distance_to_find_stop && distance <= distance_to_find_bus_outof_stop) {
            return true;
        } else
            return false;
    }

    public boolean isBusOutOfStop(VehicleGpsData data, Stops stop) {
        double distance_to_find_stop = systemProperties.getDistance_to_find_stop();
        double distance_to_find_bus_outof_stop = systemProperties.getDistance_to_find_bus_outof_stop();
        double distance = findDistance.calculateDistance(data.getLatitude(), data.getLongitude(),
                stop.getLatitude(), stop.getLongitude());
        System.out.println(distance);
        if (distance > distance_to_find_stop && distance <= distance_to_find_bus_outof_stop) {
            return true;
        } else
            return false;
    }

    public RouteStops isBusOutOfStop(int route_id, String latitude, String longitude) {
        return isBusOutOfStop(route_id, Double.parseDouble(latitude), Double.parseDouble(longitude));
    }

    public RouteStops isBusOutOfStop(int route_id, Double latitude, Double longitude) {
        double distance_to_find_stop = systemProperties.getDistance_to_find_stop();
        double distance_to_find_bus_outof_stop = systemProperties.getDistance_to_find_bus_outof_stop();
        List<RouteStops> routeStops = routeStopsService.getAllRouteStops(route_id);
        double distance = 0.0;
        RouteStops found_stop = null;

        for (RouteStops routeStop : routeStops) {

            distance = findDistance.calculateDistance(latitude, longitude,
                    Double.parseDouble(routeStop.getStop().getLatitude()),
                    Double.parseDouble(routeStop.getStop().getLongitude()));
            // System.out.println("Busstop out --> " + distance);
            if (distance > distance_to_find_stop && distance <= distance_to_find_bus_outof_stop) {
                found_stop = routeStop;
                break;
            }

        }

        return found_stop;
    }
}
