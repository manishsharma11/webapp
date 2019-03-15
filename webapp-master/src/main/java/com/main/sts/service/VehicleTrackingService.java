package com.main.sts.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.eventserver.dto.request.VehicleGpsDataRequest;
import com.ec.eventserver.service.VehicleTrackingHelper;
import com.main.sts.common.CommonConstants.TripStatus;
import com.main.sts.common.ServiceException;
import com.main.sts.common.ServiceException.ErrorType;
import com.main.sts.dao.sql.UserPositionDao;
import com.main.sts.dao.sql.VehiclePositionDao;
import com.main.sts.dto.GoogleDistancePojo;
import com.main.sts.dto.StopsDTO;
import com.main.sts.dto.UserGPSDataDTO;
import com.main.sts.dto.VehicleGPSDataDTO;
import com.main.sts.entities.Booking;
import com.main.sts.entities.DailyBusCoordinates;
import com.main.sts.entities.Location;
import com.main.sts.entities.RouteStops;
import com.main.sts.entities.Routes;
import com.main.sts.entities.StopPosition;
import com.main.sts.entities.Stops;
import com.main.sts.entities.TrackingDetails;
import com.main.sts.entities.Trips;
import com.main.sts.entities.UserGpsData;
import com.main.sts.entities.VehicleGpsData;

@Service("vehicleTrackingService")
public class VehicleTrackingService {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private StopsService stopsService;

    @Autowired
    private TripService tripService;

    @Autowired
    private RouteStopsService routeStopService;

    @Autowired
    private VehiclePositionDao vehiclePosDao;
    
    @Autowired
    private UserPositionDao userPosDao;

    @Autowired
    private GoogleMapService googleMapService;
    
    @Autowired
    private VehicleTrackingHelper vehicleTrackingHelper;
    
    @Autowired
    private DailyBusCoordinatesService busCoordinateService;
    
    @Autowired
    private DailyRunningBusesService dailyRunningBusService;

    public DistanceOutput calculateDistance(int booking_id, int trip_id) {
        DistanceMatrix distanceMatrix = new DistanceMatrix();
        // int booking_id = 0;
        // int trip_id = 0;

        findUserBookingStopPosition(booking_id, distanceMatrix);
        findVehicleCurrentPostion(trip_id, distanceMatrix);

        GoogleDistancePojo distance = googleMapService.findDistance(distanceMatrix);
        String distanceVal = distance.getRows()[0].getElements()[0].getDistance().getText();
        String durationVal = distance.getRows()[0].getElements()[0].getDuration().getText();

        System.out.println(distance.getRows()[0].getElements()[0].getDistance().getText());
        // duration
        System.out.println(distance.getRows()[0].getElements()[0].getDuration().getText());

        DistanceOutput distOutput = new DistanceOutput();
        distOutput.durationVal = durationVal;
        distOutput.eta = calcualteETA(distanceVal, durationVal) + "m";
        return distOutput;
    }

    public double calcualteETA(String distance, String duration) {
        String[] arr = distance.split(" ");
        double val = Double.parseDouble(arr[0]);
        String unit = arr[1].trim();
        if (unit.equals("km")) {
            double val_meters = val * 1000;
            double vehicle_speed_per_min = (50 * 1000) / 60; // 50km/hr
            double timetaken = val_meters / vehicle_speed_per_min;

            return timetaken;
        } else if (unit.equals("m")) {
            double val_meters = val;
            double vehicle_speed_per_min = (50 * 1000) / 60; // 50km/hr
            double timetaken = val_meters / vehicle_speed_per_min;

            return timetaken;
        }
        return -1;
    }

    public void findVehicleCurrentPostion(int trip_id, DistanceMatrix distanceMatrix) {
        VehicleGpsData vehiclePos = vehiclePosDao.findPosition(trip_id);
        String vehicle_lat = vehiclePos.getLatitude();
        String vehicle_long = vehiclePos.getLongitude();

        distanceMatrix.vehicle_lat = vehicle_lat;
        distanceMatrix.vehicle_long = vehicle_long;
    }

    public void findUserBookingStopPosition(int booking_id, DistanceMatrix distanceMatrix) {
        Booking booking = bookingService.getBookingById(booking_id);
        int to_stop_id = booking.getTo_stop();
        Stops stops = stopsService.getStop(to_stop_id);
        String booking_stop_lat = stops.getLatitude();
        String booking_stop_long = stops.getLongitude();

        distanceMatrix.booking_stop_lat = booking_stop_lat;
        distanceMatrix.booking_stop_long = booking_stop_long;
    }

    // old functionality
    public TrackingDetails trackVehcileByBooking1(int booking_id) throws ServiceException {
        Booking booking = bookingService.getBookingById(booking_id);
        if (booking == null) {
            throw new ServiceException("Booking Id " + booking_id + " does not exist", ErrorType.ILLEGAL_ARGUMENT,
                    ReturnCodes.BOOKING_ID_NOT_EXIST);
        }
        int trip_id = booking.getTrip_id();
        Trips trip = tripService.getTrip(trip_id);
        Routes routes = trip.getTripDetail().getRoutes();
        int route_id = routes.getId();
        
        List<RouteStops> route_stops = routeStopService.getAllRouteStops(route_id);
        List<StopPosition> stopWithPositions = new ArrayList<StopPosition>();
        
        TrackingDetails trackingDetails = new TrackingDetails();

        for (RouteStops rs : route_stops) {
            // String latitude = rs.getStop().getLatitude();
            // String longitude = rs.getStop().getLongitude();
            Stops stop = rs.getStop();
            StopPosition stopPos = new StopPosition();
            if (booking.getFrom_stop() == stop.getId()) {
                stopPos.setUser_boarding_point(true);
            }
            if (booking.getTo_stop() == stop.getId()) {
                stopPos.setUser_drop_off_point(true);
            }
            StopsDTO stopDTO = new StopsDTO();
            stopDTO.route_stop_id = null;
            stopDTO.stop_id = stop.getId();
            stopDTO.stop_name = stop.getStop_name();
            stopDTO.latitude = stop.getLatitude();
            stopDTO.longitude = stop.getLongitude();
            stopPos.stop = stopDTO;
            stopWithPositions.add(stopPos);
        }
        
        trackingDetails.stops = stopWithPositions;

        VehicleGpsData vehicleGPS = vehiclePosDao.getVehicleGPS(trip_id);
        System.out.println("vehicleGPS:"+vehicleGPS);

        // Vehicle Position
        if (vehicleGPS != null) {
            Location vehicleCurrentPos = new Location();
            vehicleCurrentPos.setLatitude(vehicleGPS.getLatitude());
            vehicleCurrentPos.setLongitude(vehicleGPS.getLongitude());
            trackingDetails.vehicleLocation = vehicleCurrentPos;
        }
        
//        UserGpsData userGPS = userPosDao.getUserGPS(booking.getCommuter_id());
//        System.out.println("userGPS:"+userGPS);
//        
//        // User(commuter) Position
//        if (userGPS != null) {
//            Location userCurrentPos = new Location();
//            userCurrentPos.setLatitude(userGPS.getGps_lat());
//            userCurrentPos.setLongitude(userGPS.getGps_long());
//            trackingDetails.userLocation = userCurrentPos;
//        }
        
        return trackingDetails;
    }
    
    // new functionality
    public TrackingDetails trackVehcileByBooking(int booking_id) throws ServiceException {
        Booking booking = bookingService.getBookingById(booking_id);
        if (booking == null) {
            throw new ServiceException("Booking Id " + booking_id + " does not exist", ErrorType.ILLEGAL_ARGUMENT,
                    ReturnCodes.BOOKING_ID_NOT_EXIST);
        }
        int trip_id = booking.getTrip_id();
        Trips trip = tripService.getTrip(trip_id);
        Routes routes = trip.getTripDetail().getRoutes();
        int route_id = routes.getId();
        
        List<RouteStops> route_stops = routeStopService.getAllRouteStops(route_id);
        List<StopPosition> stopWithPositions = new ArrayList<StopPosition>();
        
        TrackingDetails trackingDetails = new TrackingDetails();

        Collections.sort(route_stops, new RouteStopsService.RouteStopsTimingsComparator());
        
        // Adding the information about the stops those are going to come.
        for (RouteStops rs : route_stops) {
            // String latitude = rs.getStop().getLatitude();
            // String longitude = rs.getStop().getLongitude();
            Stops stop = rs.getStop();
            StopPosition stopPos = new StopPosition();
            if (booking.getFrom_stop() == stop.getId()) {
                stopPos.setUser_boarding_point(true);
            }
            if (booking.getTo_stop() == stop.getId()) {
                stopPos.setUser_drop_off_point(true);
            }
            Date d = new Date();
            String[] arr = rs.getStop_time().split(":");
            d.setHours(Integer.parseInt(arr[0]));
            d.setMinutes(Integer.parseInt(arr[1]));

            StopsDTO stopDTO = new StopsDTO();
            stopDTO.route_stop_id = null;
            stopDTO.stop_id = stop.getId();
            stopDTO.stop_name = stop.getStop_name();
            stopDTO.stop_number = rs.getStop_number();
            stopDTO.latitude = stop.getLatitude();
            stopDTO.longitude = stop.getLongitude();
            stopDTO.stop_reaching_time = d.toString();//rs.getStop_time();
            stopPos.stop = stopDTO;
            stopWithPositions.add(stopPos);
        }
        
        trackingDetails.stops = stopWithPositions;
        
        List<StopPosition> tracking_points = new ArrayList<StopPosition>();
        // Adding the information about the coordinate where the bus already
        // went through.
        Date trip_date = new Date();
        System.out.println(trip_id + "---" + trip_date);
        List<DailyBusCoordinates> ls = busCoordinateService.getTripsCoordinates(trip_id, trip_date, 30);
        if (ls != null && ls.size() > 0) {
            for (DailyBusCoordinates c : ls) {
                StopPosition stopPos = new StopPosition();
                StopsDTO stopDTO = new StopsDTO();
                stopDTO.route_stop_id = null;
                stopDTO.stop_name = null;
                stopDTO.latitude = Double.toString(c.getLatitude());
                stopDTO.longitude = Double.toString(c.getLongitude());
                stopDTO.stop_reaching_time = c.getRunning_date().toString();
                stopPos.stop = stopDTO;
                tracking_points.add(stopPos);
            }
        } else {
            System.out.println("Coordinates not available in bus cordinates service");
        }

        trackingDetails.tracking_points = tracking_points;

        VehicleGpsData vehicleGPS = vehiclePosDao.getVehicleGPS(trip_id);
        System.out.println("vehicleGPS:"+vehicleGPS);

        // Vehicle Position
        if (vehicleGPS != null) {
            Location vehicleCurrentPos = new Location();
            vehicleCurrentPos.setLatitude(vehicleGPS.getLatitude());
            vehicleCurrentPos.setLongitude(vehicleGPS.getLongitude());
            trackingDetails.vehicleLocation = vehicleCurrentPos;
        }
        
//        UserGpsData userGPS = userPosDao.getUserGPS(booking.getCommuter_id());
//        System.out.println("userGPS:"+userGPS);
//        
//        // User(commuter) Position
//        if (userGPS != null) {
//            Location userCurrentPos = new Location();
//            userCurrentPos.setLatitude(userGPS.getGps_lat());
//            userCurrentPos.setLongitude(userGPS.getGps_long());
//            trackingDetails.userLocation = userCurrentPos;
//        }
        
        return trackingDetails;
    }
    
    public ReturnCodes storeVehiclePos(VehicleGPSDataDTO dto) {
        return storeVehiclePos(dto, TripStatus.RUNNING);
    }
    
    public ReturnCodes startTrip(VehicleGPSDataDTO dto) {
        return storeVehiclePos(dto, TripStatus.STARTING_TRIP);
    }
    
    public ReturnCodes endTrip(VehicleGPSDataDTO dto) {
        return storeVehiclePos(dto, TripStatus.ENDING_TRIP);
    }

    private ReturnCodes storeVehiclePos(VehicleGPSDataDTO dto, TripStatus tripStatus) {

        VehicleGpsData gpsData = new VehicleGpsData();

        // gpsData.setVehicle_number(dto.getVehicle_number());
        gpsData.setEc_device_id(dto.getEc_device_id());
        gpsData.setTrip_id(dto.getTrip_id());
        gpsData.setLatitude(dto.getGps_lat());
        gpsData.setLongitude(dto.getGps_long());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss.SSS", Locale.ENGLISH);
        
        System.out.println("gpsData:"+gpsData +"----------"+sdf.format(new Date()));
        
        boolean status = vehiclePosDao.insert(gpsData);
        if (status) {
            VehicleGpsDataRequest gpsDataReq = toVehicleGpsDataRequest(gpsData);
            try {
                // TODO: make it async
                vehicleTrackingHelper.handleIncomingMessage(gpsDataReq, tripStatus);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ReturnCodes.VEHICLE_POS_INSERT_SUCCESS;
        } else {
            return ReturnCodes.VEHICLE_POS_INSERT_FAILED;
        }
    }

    public VehicleGpsDataRequest toVehicleGpsDataRequest(VehicleGpsData gpsData) {
        VehicleGpsDataRequest gpsDataReq = new VehicleGpsDataRequest();
        gpsDataReq.setDevice_id(gpsData.getEc_device_id());
        gpsDataReq.setGps_lat(Double.parseDouble(gpsData.getLatitude()));
        gpsDataReq.setGps_long(Double.parseDouble(gpsData.getLongitude()));
        gpsDataReq.setTrip_id(gpsData.getTrip_id());
        gpsDataReq.setCreated_at(new Date(gpsData.getEventTime().getTime()));

        Calendar c = Calendar.getInstance();
        c.setTime(gpsData.getEventTime());

        String time = c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
        gpsDataReq.setTime(time);
        return gpsDataReq;
    }

    public static class DistanceOutput {
        String durationVal;
        String eta;
    }

    public static class DistanceMatrix {

        public int commuter_id;
        public int booking_id;
        public String vehicle_lat;
        public String vehicle_long;
        public String booking_stop_lat;
        public String booking_stop_long;
    }

    // Not tested yet. as needs to have a stops and trip defined.
    public static void main(String[] args) {
        VehicleTrackingService vehicleTracking = new VehicleTrackingService();
        int booking_id = 0;
        int trip_id = 0;
        DistanceOutput distOutput = vehicleTracking.calculateDistance(booking_id, trip_id);
    }

}
