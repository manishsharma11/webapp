package com.ec.eventserver.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.ec.eventserver.dto.request.VehicleGpsDataRequest;
import com.ec.eventserver.models.DailyBusStops;
import com.main.sts.common.CommonConstants;
import com.main.sts.common.CommonConstants.TripStatus;
import com.main.sts.common.CommonConstants.VehicleStatus;
import com.main.sts.entities.Buses;
import com.main.sts.entities.DailyRunningBuses;
import com.main.sts.entities.DailySubscriberData;
import com.main.sts.entities.DriverSpeedEntity;
import com.main.sts.entities.RouteStops;
import com.main.sts.entities.Stops;
import com.main.sts.entities.TripDetail;
import com.main.sts.entities.Trips;
import com.main.sts.entities.VehicleGpsData;
import com.main.sts.service.BusesService;
import com.main.sts.service.DailyRunningBusesService;
import com.main.sts.service.DailySubscriberDataService;
import com.main.sts.service.DriverSpeedService;
import com.main.sts.service.DriversService;
import com.main.sts.service.RouteService;
import com.main.sts.service.RouteStopsService;
import com.main.sts.service.StudentsService;
import com.main.sts.service.TripService;
import com.main.sts.util.SystemConstants;

@Component
public class GpsDataProcess {

    private static final Logger logger = Logger.getLogger(GpsDataProcess.class);
   
    @Autowired
    private BusesService vehicleService;
    
    @Autowired
    private RouteService routeService;
    
    @Autowired
    private RouteStopsService routeStopsService;
    
    @Autowired
    private BusStopFinder busStopFinder;
    
    @Autowired
    private DailyBusStopService dailyBusStopService;
    
    @Autowired
    private SystemProperties systemProperties;
    
    @Autowired
    private FindDistance findDistance;
    
    @Autowired
    private DailyRunningBusesService dailyRunningBusesService;
    
    @Autowired
    private DailySubscriberDataService dailySubscriberDataService;
    
    @Autowired
    private StudentsService studentsService;
    
    @Autowired
    private TripService tripService;
    
    @Autowired
    private DriversService driversService;
    
    @Autowired
    private DriverSpeedService driverspeedservice;

    public void process(VehicleGpsDataRequest data, Trips trip, DailyRunningBuses dailyRunningBuses) {
        // logger.info("GpsDataProcess ----> in process");
        Buses vehicle = data.getVehicle(vehicleService, tripService);
        boolean is_bus_at_school = busStopFinder.isBusAtSchool(data.getGps_lat(),
                data.getGps_long());
        boolean is_stop_school = false;
        TripDetail tripDetail = trip.getTripDetail();
        
        FirstAndLastRouteStop firstLastRS  = getFirstAndLastStopOfATrip(trip);
        RouteStops lastRouteStop = firstLastRS.last;
        RouteStops firstRouteStop = firstLastRS.first;
        Stops firstStop = firstRouteStop.getStop();
        if (is_bus_at_school) {
            logger.info("Bus [ " + vehicle.getBus_licence_number() + " ] arrived to  [ school ]  at [ " + data.getCreated_at()
                    + " ]");
            // Bus is at school
            dailyRunningBuses.setArrived_time(data.getEventTimeInHoursMins());
            VehicleStatus bus_status = findBusStatus(lastRouteStop.getStop_time(), data, dailyRunningBuses);
            dailyRunningBuses.setVehicle_status(bus_status.intValue());
            dailyRunningBusesService.setBusArrivedToLastStop(dailyRunningBuses);
            if (tripDetail.getTrip_type().equals(SystemConstants.DROPOFF)) {
                // find students
            }
            if (tripDetail.getTrip_type().equals(SystemConstants.PICKUP)) {
                if (dailyRunningBuses.isIs_bus_arrived_to_trip_last_stop() == false) {
                    dailyRunningBuses.setIs_bus_arrived_to_trip_last_stop(true);
                    dailyRunningBusesService.setBusArrivedToLastStop(dailyRunningBuses);
                }
            }
            is_stop_school = true;
        } else {
            Boolean status = dailyRunningBuses.isIs_bus_out_of_first_stop();
            if (status != null && !status && dailyRunningBuses.isIs_bus_arrived_to_trip_last_stop() == true) {
                // Find Bus out of school
                boolean is_bus_out_of_first_stop = busStopFinder.isBusOutOfFirstStop(data.getGps_lat(), data.getGps_long(), firstStop.getLatitude(), firstStop.getLongitude() );
                if (is_bus_out_of_first_stop) {
                    dailyRunningBuses.setIs_bus_out_of_first_stop(true);
                    dailyRunningBusesService.updateDailyRunningBuses(dailyRunningBuses);
                }
                is_stop_school = true;
            }
        }
        
        if (is_stop_school == false) {
            RouteStops stop = busStopFinder.find(tripDetail.getRouteid(), data.getGps_lat(), data.getGps_long());

            if (stop != null) {
                // logger.info(stop);
                DailyBusStops dailyBusStops = dailyBusStopService.getDailyBusStop(trip.getId(), data.getCreated_at(),
                        stop.getId());
                if (dailyBusStops == null) {
                    dailyBusStopService.insertDailyBusStop(data, stop, trip.getId());
                    // find bus status at stop
                    VehicleStatus vehicle_status = findBusStatus(stop, data, dailyRunningBuses);
                    dailyRunningBuses.setVehicle_status(vehicle_status.intValue());
                    dailyRunningBusesService.setBusArrivedToLastStop(dailyRunningBuses);
                }
            } else {
                stop = busStopFinder.isBusOutOfStop(tripDetail.getRouteid(), data.getGps_lat(), data.getGps_long());
                if (stop != null) {
                    DailyBusStops dailyBusStops = dailyBusStopService.getDailyBusStop(trip.getId(), data.getCreated_at(),
                            stop.getId());
                    if (dailyBusStops != null && dailyBusStops.isIs_stop_out_of_range() == false) {
                        // System.out.println("Bus out of range..");
                        // Set bus out of stop to TRUE
                        dailyBusStopService.setBusOutOfStop(dailyBusStops.getId());
                    }
                }
            }
        }
    }
    
    public FirstAndLastRouteStop getFirstAndLastStopOfATrip(Trips trip) {
        int route_id = trip.getTripDetail().getRouteid();
        Set<RouteStops> route_stops = routeStopsService.getAllAvailableFromStops(route_id,
                new RouteStopsService.StopNumberComparator());
        RouteStops lastRouteStop = null;
        RouteStops firstRouteStop = null;
        // very crude way of finding a last stop of a route
        for (RouteStops rs : route_stops) {
            // setting first stop, only on first iteration. from 2nd iteration it wont be null
            if (firstRouteStop == null) {
                firstRouteStop = rs;
            }
            // setting last stop in each iteration
            lastRouteStop = rs;
        }
        FirstAndLastRouteStop routeStop = new FirstAndLastRouteStop();
        routeStop.first = firstRouteStop;
        routeStop.last = lastRouteStop;
        return routeStop;
    }

    public VehicleStatus findBusStatus(RouteStops routeStops, VehicleGpsDataRequest data, DailyRunningBuses dailyRunningBuses) {
        Buses vehicle = data.getVehicle(vehicleService, tripService);
        logger.info("Bus [ " + vehicle.getBus_licence_number() + " ] arrived to stop [ "
                + routeStops.getStop().getStop_name() + " ] at [ " + data.getEventTimeInHoursMins() + " ]");
        dailyRunningBuses.setCurrent_stop(routeStops.getStop().getStop_name());
        dailyRunningBuses.setArrived_time(data.getEventTimeInHoursMins());
        dailyRunningBusesService.updateDailyRunningBuses(dailyRunningBuses);
        return calculateTimeDiff(data.getEventTimeInHoursMins(), routeStops.getStop_time());
    }

    public VehicleStatus findBusStatus(String last_stop_time, VehicleGpsDataRequest data, DailyRunningBuses dailyRunningBuses) {
        dailyRunningBuses.setCurrent_stop("last_stop");
        dailyRunningBuses.setArrived_time(data.getEventTimeInHoursMins());
        dailyRunningBusesService.updateDailyRunningBuses(dailyRunningBuses);
        return calculateTimeDiff(data.getEventTimeInHoursMins(), last_stop_time);
    }

    public VehicleStatus calculateTimeDiff(Date event_time, String extected_time) {
        String current_time = event_time.getHours() + ":" + event_time.getMinutes();
        return calculateTimeDiff(current_time, extected_time);
    }

    public VehicleStatus calculateTimeDiff(String current_time, String extected_time) {
        VehicleStatus bus_status = null;
        int c_time = (Integer.parseInt(current_time.split(":")[0]) * 60) + Integer.parseInt(current_time.split(":")[1]);
        int e_time = (Integer.parseInt(extected_time.split(":")[0]) * 60)
                + Integer.parseInt(extected_time.split(":")[1]);
        int diff = c_time - e_time;
        if (diff <= CommonConstants.ontime) {
            bus_status = VehicleStatus.ONTIME;//SystemConstants.bus_ontime;
            logger.info(" Bus Status : [ On Time ]");
        } else if (diff > CommonConstants.ontime && diff <= CommonConstants.late) {
            bus_status = VehicleStatus.LATE;//SystemConstants.bus_late;
            logger.info(" Bus Status : [ Late ] by [ " + diff + " ] minuts");
        } else {
            bus_status = VehicleStatus.VERY_LATE;//SystemConstants.bus_verylate;
            logger.info(" Bus Status : [ very Late ] by [ " + diff + " ] minuts");
        }
        return bus_status;
    }

    // method to check over speed

    boolean status;
    DriverSpeedEntity des = null;
    int id = 0;
    
    public void checkDriverSpeed(ApplicationContext context, VehicleGpsDataRequest data, Trips tripEntity) {
        // System.out.println("speed checking starts");
        Buses vehicle = data.getVehicle(vehicleService, tripService);
        String time = data.getEventTimeInHoursMins();
        
        // TODO: fix it
        int speed = 0;//(int) data.getBus_speed();
        
//        auto dist = distance_on_geoid(p1.latitude, p1.longitude, p2.latitude, p2.longitude);
//        auto time_s = (p2.timestamp - p1.timestamp) / 1000.0;
//        double speed_mps = dist / time_s;
//        double speed_kph = (speed_mps * 3600.0) / 1000.0;
        
        // System.out.println("speed is " + speed);
        des = new DriverSpeedEntity();
        Date trip_running_date = data.getCreated_at();
        DailyRunningBuses currentRunningBuses = dailyRunningBusesService.getDailyRunningBus(tripEntity.getId(),
                data.getCreated_at(), TripStatus.RUNNING);
        // System.out.println(currentRunningBuses);
        if (currentRunningBuses != null) {
            String trip_name = tripService.getTrip(currentRunningBuses.getTrip_id()).getTripDetail().getTrip_name();

            if (speed >= 80) {
                if (status == false) {

                    int did = currentRunningBuses.getDriver_id();

                    String dn = driversService.getDriver(did).getDriver_name();
                    des.setDriver_name(dn);
                    des.setDriver_id(did);
                    des.setStart_time(time);
                    des.setStart_latitude(data.getGps_lat());
                    des.setStart_longitude(data.getGps_long());
                    des.setEnd_time(time);
                    des.setEnd_latitude(data.getGps_lat());
                    des.setEnd_longitude(data.getGps_long());
                    des.setHighest_speed(speed);
                    des.setTrip_name(trip_name);
                    des.setBus_licence_number(vehicle.getBus_licence_number());
                    des.setTrip_running_Date(trip_running_date);
                    driverspeedservice.insertSpeed(des);
                    id = des.getId();
                    logger.info("inserting over speed into database");
                    // currentRunningBuses.setDriver_speed_id(des.getId());

                    this.status = true;

                }

                else if (status == true) {
                    logger.info("des " + des);
                    DriverSpeedEntity des1 = driverspeedservice.getdriver(id);
                    if (speed >= des.getHighest_speed())

                    {
                        des1.setEnd_time(time);
                        des1.setEnd_latitude(data.getGps_lat());
                        des1.setEnd_longitude(data.getGps_long());
                        des1.setHighest_speed(speed);
                        System.out.println("new speed set " + des1.getHighest_speed());

                        driverspeedservice.updateDriverSpeed(des1);
                        System.out.println("updated with higher or equal speed " + speed);
                        logger.info("The bus is updating overspeeding  in report, speed of the bus is "
                                + des1.getHighest_speed());
                    } else {
                        des1.setEnd_time(time);
                        des1.setEnd_latitude(data.getGps_lat());
                        des1.setEnd_longitude(data.getGps_long());
                        // des.setHighest_speed(speed);

                        // speedDao.updateDriverSpeed(des.getId(),des.getEnd_time(),des.getEnd_latitude(),des.getEnd_longitude(),
                        // des.getHighest_speed());
                        driverspeedservice.updateDriverSpeed(des1);
                        System.out.println("updated with lower speed " + speed);
                        logger.info("The bus  is updating  now in report, speed of the bus is" + speed);
                    }
                }

            } else {
                if (status == true) {
                    this.status = false;
                    // currentRunningBuses.setDriver_speed_id(null);
                    logger.info("bus is running below high speed i.e " + speed);
                }
            }
        }

    }
    
    //
    double distance_on_geoid(double lat1, double lon1, double lat2, double lon2) {
     
      // Convert degrees to radians
      lat1 = lat1 * Math.PI / 180.0;
      lon1 = lon1 * Math.PI / 180.0;
     
      lat2 = lat2 * Math.PI / 180.0;
      lon2 = lon2 * Math.PI / 180.0;
     
      // radius of earth in metres
      double r = 6378100;
     
      // P
      double rho1 = r * Math.cos(lat1);
      double z1 = r * Math.sin(lat1);
      double x1 = rho1 * Math.cos(lon1);
      double y1 = rho1 * Math.sin(lon1);
     
      // Q
      double rho2 = r * Math.cos(lat2);
      double z2 = r * Math.sin(lat2);
      double x2 = rho2 * Math.cos(lon2);
      double y2 = rho2 * Math.sin(lon2);
     
      // Dot product
      double dot = (x1 * x2 + y1 * y2 + z1 * z2);
      double cos_theta = dot / (r * r);
     
      double theta = Math.acos(cos_theta);
     
      // Distance in Metres
      return r * theta;
    }
    
    public static class FirstAndLastRouteStop {
        RouteStops first;
        RouteStops last;
    }

}
