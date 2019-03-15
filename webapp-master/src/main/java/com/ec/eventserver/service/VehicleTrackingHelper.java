package com.ec.eventserver.service;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.eventserver.dto.request.VehicleGpsDataRequest;
import com.ec.eventserver.models.DeviceInfo;
import com.main.sts.common.CommonConstants.TripStatus;
import com.main.sts.entities.DailyRunningBuses;
import com.main.sts.entities.RouteStops;
import com.main.sts.entities.Stops;
import com.main.sts.entities.Trips;
import com.main.sts.service.BookingService;
import com.main.sts.service.BusesService;
import com.main.sts.service.DailyBusCoordinatesService;
import com.main.sts.service.DailyRunningBusesService;
import com.main.sts.service.RouteStopsService;
import com.main.sts.service.TripService;

@Service("vehicleTrackingHelperService")
public class VehicleTrackingHelper {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private BusesService vehicleService;

    @Autowired
    private TripService tripService;

    @Autowired
    private DailyRunningBusesService dailyRunningVehicleService;

    @Autowired
    private DailyBusCoordinatesService dailyBusCoordinatesService;
    
    @Autowired
    private EtaProcessor etaProcessor;
    
    @Autowired
    private BookingService bookingService;
    
    @Autowired
    private RouteStopsService routeStopsService;
    
    @Autowired 
    private GpsDataProcess gpsDataProcess;
    

//    public void handleIncomingMessage(VehicleGpsDataRequest gpsData, boolean startingTrip) {
//        String device_id = gpsData.getDevice_id();
//        Trips trip = findCurrentVehicleTrip(device_id);
//        if (trip == null) {
//            System.out.println("No trip found in system for device_id:" + device_id);
//        } else {
//            if (gpsData.getVehicle_id() == null) {
//                gpsData.setVehicle_id(trip.getTripDetail().getBusid());
//            }
//            DailyRunningBuses dailyRunningBus = dailyRunningVehicleService.getDailyRunningBus(trip.getId(),
//                    gpsData.getCreated_at(), TripStatus.RUNNING);
//            if (dailyRunningBus == null) {
//                // trip not started
//                System.out.println("daily bus is null");
//                dailyRunningVehicleService.startDailyRunningBus(trip, gpsData);
//            } else {
//                // for running trip
//                processData(dailyRunningBus, trip, gpsData);
//            }
//        }
//    }
    
//    public void handleIncomingMessage(VehicleGpsDataRequest gpsData, TripStatus tripStatus) {
//        String device_id = gpsData.getDevice_id();
//        Integer trip_id = gpsData.getTrip_id();
//
//        Trips trip = null;
//        if (trip_id != null) {
//            trip = tripService.getTrip(trip_id);;
//        }
//        if (tripStatus == TripStatus.STARTING_TRIP) {
//            startTrip(trip);
//        }
//        
////        if (trip == null) {
////            System.out.println("No trip found in system for device_id:" + device_id);
////            System.out.println("Trying to find the trip through mapping for " + device_id);
////            trip = findCurrentVehicleTrip(device_id);
////        }
//        
//        if (trip == null) {
//            System.out.println("No trip found in system for device_id:" + device_id);
//            handleNonRunningTripTracking();
//        } else {
//            
//        }
//
//        if (trip == null) {
//            System.out.println("No trip found in system for device_id:" + device_id);
//        } else {
//            if (gpsData.getVehicle_id() == null) {
//                gpsData.setVehicle_id(trip.getTripDetail().getBusid());
//            }
//            DailyRunningBuses dailyRunningBus = dailyRunningVehicleService.getDailyRunningBus(trip.getId(),
//                    gpsData.getCreated_at(), TripStatus.RUNNING);
//            if (dailyRunningBus == null) {
//                // trip not started
//                System.out.println("daily bus is null");
//                dailyRunningVehicleService.startDailyRunningBus(trip, gpsData);
//            } else {
//                // for running trip
//                processData(dailyRunningBus, trip, gpsData);
//            }
//
//            if (tripStatus == TripStatus.ENDING_TRIP) {
//                endTrip(dailyRunningBus);
//            }
//        }
//    }
    
    public void handleIncomingMessage(VehicleGpsDataRequest gpsData, TripStatus tripStatus) {
        String device_id = gpsData.getDevice_id();
        Integer trip_id = gpsData.getTrip_id();

        Trips trip = null;
        if (trip_id != null) {
            trip = tripService.getTrip(trip_id);;
        }

        // if (trip == null) {
        // System.out.println("No trip found in system for device_id:" +
        // device_id);
        // System.out.println("Trying to find the trip through mapping for " +
        // device_id);
        // trip = findCurrentVehicleTrip(device_id);
        // }

        if (trip == null) {
            System.out.println("No trip found in system for device_id:" + device_id);
            handleNonRunningTripTracking(gpsData);
        } else {

            // Adding vehicle information.
            addVehicle(trip, gpsData);

            switch (tripStatus) {
                case STARTING_TRIP :
                    startTrip(trip, gpsData);
                    break;
                case RUNNING :
                    handleRunningTrip(trip, gpsData);
                    break;
                case ENDING_TRIP :
                    endTrip(trip, gpsData);
                    break;
                default :
                    break;
            }
        }
    }
    
    private void addVehicle(Trips trip, VehicleGpsDataRequest gpsData) {
        if (gpsData.getVehicle_id() == null) {
            gpsData.setVehicle_id(trip.getTripDetail().getBusid());
        }
    }
    
    public void startTrip(Trips trip, VehicleGpsDataRequest gpsData) {
        System.out.println("startTrip:Got a message for starting trip id:" + trip.getId());
        DailyRunningBuses dailyRunningBus = dailyRunningVehicleService.getDailyRunningBus(trip.getId(),
                gpsData.getCreated_at(), TripStatus.RUNNING);
        if (dailyRunningBus == null) {
            // trip not started
            System.out
                    .println("daily bus is null so creating an entry for: trip:" + trip.getId() + "and starting trip");
            dailyRunningVehicleService.startDailyRunningBus(trip, gpsData);
            
            // adding coordinates for trip
            processData(dailyRunningBus, trip, gpsData);
        } else {
            // for running trip
            handleRunningTrip(trip, gpsData);
        }
    }
    
    public void handleRunningTrip(Trips trip, VehicleGpsDataRequest gpsData) {
        System.out.println("handleRunningTrip...");
        
        String device_id = gpsData.getDevice_id();
        if (trip == null) {
            System.out.println("No trip found in system for device_id:" + device_id);
            //throw new IllegalArgumentException("No trip found in system for device_id:" + device_id+" It can't be null");
            
            System.out.println("=================== STARTING TRIP===============device_id:" + device_id);
            startTrip(trip, gpsData);
        }
        
        System.out.println("handleRunningTrip: for trip id:" + trip.getId());

        DailyRunningBuses dailyRunningBus = dailyRunningVehicleService.getDailyRunningBus(trip.getId(),
                gpsData.getCreated_at(), TripStatus.RUNNING);
        if (dailyRunningBus == null) {
            // trip not started
            System.out.println("daily bus is null");
            throw new IllegalArgumentException("DailyRunningBuses shouldnt be null for tripid:"+trip.getId()+"---device_id:"+device_id);
        }

        // for running trip
        processData(dailyRunningBus, trip, gpsData);
    }
    
    public void endTrip(Trips trip, VehicleGpsDataRequest gpsData){
        System.out.println("endTrip:Got a message for ending a trip id:" + trip.getId());

        DailyRunningBuses dailyRunningBus = dailyRunningVehicleService.getDailyRunningBus(trip.getId(),
                gpsData.getCreated_at());
        
        if (dailyRunningBus == null) {
            // trip not started
            System.out.println("daily bus is null");
            throw new IllegalArgumentException("DailyRunningBuses shouldnt be null");
        }
        
        TripStatus tripStatus = TripStatus.getTripStatus(dailyRunningBus.getTrip_status());

        // adding coordinates for trip
        processData(dailyRunningBus, trip, gpsData);
        
        // a trip should running before it can be ended
        if (tripStatus == TripStatus.STARTING_TRIP || tripStatus == TripStatus.RUNNING) {
            int dailyRunningBusId = dailyRunningBus.getId();
            String endTime = null;

            Date d = new Date();
            endTime = d.getHours() + ":" + d.getMinutes();
            System.out.println("endTime:" + endTime);
            dailyRunningVehicleService.updateTripEndTime(dailyRunningBusId, endTime);
            
            // marking expirary of all bookings
            bookingService.markBookingExpired(trip.getId());
        } else {
            System.out.println("tripStatus: in endTrip:" + tripStatus);
            if (tripStatus == TripStatus.ENDING_TRIP) {
                System.out.println("trip already ended. it seems we got 2 end trip call");
                alreadyEndedTrip(trip, gpsData);
            }
        }
    }
    
    /**
     * Handling Already ended trip
     * @param trip
     * @param gpsData
     */
    public void alreadyEndedTrip(Trips trip, VehicleGpsDataRequest gpsData) {
        handleNonRunningTripTracking(gpsData);
    }
    
    public void handleNonRunningTripTracking(VehicleGpsDataRequest gpsData) {
        String device_id = gpsData.getDevice_id();
        System.out.println("No trip found in system for device_id:" + device_id);
    }

    public Trips findCurrentVehicleTrip(String device_id) {
        DeviceInfo deviceInfo = deviceService.getDeviceInfoByECDeviceId(device_id);
        int vehicle_id = deviceInfo.getVehicle_id();

        // Date
        Date today = new Date();
//        today.setHours(0);
//        today.setMinutes(0);
//        today.setSeconds(0);
        Trips trip = tripService.getTripByVehicleIdAndRunningDate(vehicle_id, today);
        return trip;
    }

    public void processData(DailyRunningBuses dailyRunningBus, Trips trip, VehicleGpsDataRequest gpsData) {
        dailyRunningBus = dailyRunningVehicleService.getDailyRunningBus(trip.getId(), gpsData.getCreated_at(),
                TripStatus.RUNNING);
         //if (properties.getIs_eta_required().equals("true")) {
         etaProcessor.procesEta(gpsData, trip, dailyRunningBus);
         //}

        // // Gps Data and process it
        gpsDataProcess.process(gpsData, trip, dailyRunningBus);
         
        dailyBusCoordinatesService.insertDailyBusCoordinates(gpsData, trip.getId());
        // gpsDataProcess.checkDriverSpeed(context, deviceData, trip);
        
        // checking for the current location if it is a stop
        checkForStopAndTakeAction(trip, gpsData);
    }
    
    public void checkForStopAndTakeAction(Trips trip, VehicleGpsDataRequest gpsData) {
        Stops reachedStop = checkForBusStopLocation(trip, gpsData);
       // sending message to all user those are goign to dropoff 
        if (reachedStop != null) {
            // Only if the current stop is dropoff stop for some commuetrs,
            // they will receive this message. for thank you for riding with us.
            bookingService.markBookingExpired(trip.getId(), reachedStop.getId(), true);
        }
        
        if(reachedStop!=null){
            // TODO: Pending
            //bookingService.notifyUserAboutPickupPoint(trip.getId(), reachedStop.getId(), true);
        }
    }
    
    public Stops checkForBusStopLocation(Trips trip, VehicleGpsDataRequest gpsData) {
        int route_id = trip.getTripDetail().getRouteid();
        Set<RouteStops> routeStops = routeStopsService.getAllAvailableFromStops(route_id);
        Stops reachedStop = null;
        for (RouteStops rs : routeStops) {
            Stops stop = rs.getStop();
            double lat1 = Double.parseDouble(stop.getLatitude());
            double long1 = Double.parseDouble(stop.getLongitude());

            double lat2 = gpsData.getGps_lat();
            double long2 = gpsData.getGps_long();

            double distanceInMeters = distance(lat1, lat2, long1, long2, 0, 0);
            distanceInMeters = (distanceInMeters < 0 ? -distanceInMeters : distanceInMeters);
            
            System.out.println("distanceInMeters:"+distanceInMeters+" name:"+stop.getStop_name()+"--");
            if (distanceInMeters < 50) {
                reachedStop = stop;
                break;
            }
        }
        return reachedStop;
    }

    // public void processData(DailyRunningBuses dailyRunningBus, Trips trip,
    // DeviceData deviceData,
    // SystemProperties properties) {
    // boolean is_rfid_valid = !(deviceData.getRfid_number().equals("0"));
    // dailyRunningBus =
    // dailyRunningBusesService.getDailyRunningBus(trip.getId(),
    // deviceData.getDate(), "running");
    // logger.info("Trip Running --> " + dailyRunningBus);
    // // trip started already
    // if (is_rfid_valid) {
    // // RFID data and process it
    // rfidDataProcess.process(deviceData, trip, dailyRunningBus);
    // }
    // // Gps Data and process it
    // gpsDataProcess.process(deviceData, trip, dailyRunningBus);
    //
    // //System.out.println(properties + ",,,,,,,,,,,,,,");
    // if (properties.getIs_eta_required().equals("true")) {
    // etaProcees.procesEta(deviceData, trip, dailyRunningBus);
    // }
    //
    // /*
    // * Insert daily bus coordinates
    // *
    // * Start here
    // */
    //
    // dailyBusCoordinatesService.insertDailyBusCoordinates(deviceData,
    // trip.getId());
    //
    // /*
    // * Insert daily bus coordinates
    // *
    // * Start here
    // */
    //
    // /*
    // * if (properties.getIs_eta_required().equals("true")) {
    // * etaProcees.procesEta(deviceData, trip, dailyRunningBus);
    // *
    // *
    // * }
    // */
    // gpsDataProcess.checkDriverSpeed(context, deviceData, trip);
    //
    // }

    // public Trips findCurrentBusTrip(DeviceData data, ApplicationContext
    // context) throws ParseException {
    //
    // List<Trips> trips = tripService.getTripsByBusId(data.getBus_id());
    // Date start = null, end = null;
    // Date device_time = getTime.getDate(data.getTime());
    // Trips current_trip = null;
    // DailyRunningBuses daily = null;
    // //logger.info("trips "+trips.size());
    // for (Trips trip : trips) {
    // daily = dailyRunningBusesService.getDailyRunningBus(trip.getId(),
    // data.getDate(), "running");
    // start = getTime.getDate(trip.getTrip_start_time_hours() + ":" +
    // (trip.getTrip_start_time_minutes() - 1));
    // if(daily!=null)
    // end = getTime.getDate(daily.getTrip_end_time());
    // else
    // end=getTime.getDate(trip.getTrip_end_time_hours() + ":" +
    // (trip.getTrip_end_time_minutes() +1));
    // //System.out.println(start + " -- " + end + " -- " + device_time);
    // if(daily!=null){
    // if (device_time.equals(end)||device_time.after(end)) {
    //
    // /*EtaProcess eta = etaProcessService.getlastEta(trip.getId(),
    // data.getDate());
    // if (eta != null)
    // tripExtension.etaNotNull(eta, data, trip, daily);
    // else*/
    // trip=tripExtension.etaNull(data, trip, daily);
    // end = getTime.getDate(daily.getTrip_end_time());
    // }
    // }
    // if (device_time.after(start) && device_time.before(end)) {
    // current_trip = trip;
    // break;
    // }
    //
    //
    // }
    // return current_trip;
    //
    // }

    // public void handleDeviceMessage(DeviceData deviceData, SystemProperties
    // properties) throws ParseException {
    // Buses bus =
    // busesService.getBusByLicence(deviceData.getBus_licence_number());
    //
    // if (bus == null) {
    // logger.info("Bus Licence received [ " +
    // deviceData.getBus_licence_number()
    // + " ] is not found in database { Action: Ignoring data }");
    // } else {
    // deviceData.setBus_id(bus.getBus_id());
    // // logger.info(deviceData);
    // BusTripController busTripFinder =
    // context.getBean(BusTripController.class);
    // Trips trip = busTripFinder.findCurrentBusTrip(deviceData, context);
    // if (trip == null) {
    // logger.info("No trip found for bus [ " +
    // deviceData.getBus_licence_number() + " ]");
    // } else {
    // boolean is_rfid_valid = !(deviceData.getRfid_number().equals("0"));
    //
    // DailyRunningBuses dailyRunningBus =
    // dailyRunningBusesService.getDailyRunningBus(trip.getId(),
    // deviceData.getDate(), "running");
    // if (dailyRunningBus == null) {
    // // trip not started
    // logger.info("daily bus is null");
    // // deviceData.getRfid_number().equals("????????????????")
    // if (is_rfid_valid) {
    // logger.info("rfid is ok");
    // // RFID data, and start trip
    // dailyRunningBusesService.startDailyRunningBus(trip, deviceData);
    // } else {
    //
    // logger.info("Trip Error:  Trip not started yet..");
    // }
    // } else {
    // processData(dailyRunningBus, trip, deviceData, properties);
    // }
    //
    // }
    // }
    //
    // }
    
    /*
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     * 
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in meters
     * @returns Distance in Meters
     */
    public static double distance(double lat1, double lat2, double lon1,
            double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        Double latDistance = Math.toRadians(lat2 - lat1);
        Double lonDistance = Math.toRadians(lon2 - lon1);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }
}
