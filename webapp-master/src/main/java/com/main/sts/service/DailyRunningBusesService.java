package com.main.sts.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.eventserver.dto.request.VehicleGpsDataRequest;
import com.main.sts.common.CommonConstants.TripStatus;
import com.main.sts.common.CommonConstants.VehicleStatus;
import com.main.sts.dao.sql.DailyRunningBusesDao;
import com.main.sts.entities.Buses;
import com.main.sts.entities.DailyBusCoordinates;
import com.main.sts.entities.DailyRunningBuses;
import com.main.sts.entities.Drivers;
import com.main.sts.entities.Trips;

@Service
public class DailyRunningBusesService {

    public static Logger logger = Logger.getLogger(DailyRunningBusesService.class);

    @Autowired
    private DailyRunningBusesDao dailyRunningBusesDao;

    @Autowired
    private DriversService driversService;

    @Autowired
    private TransportService transportService;

    @Autowired
    private BusesService busesService;

    public DailyRunningBuses getDailyRunningBus(int trip_id, Date date, TripStatus trip_status) {
        return dailyRunningBusesDao.getDailyRunningBus(trip_id, date, trip_status);
    }
    
    public DailyRunningBuses getDailyRunningBus(int trip_id, Date date) {
        return dailyRunningBusesDao.getDailyRunningBus(trip_id, date);
    }

    public void updateDailyRunningBuses(DailyRunningBuses dailyRunningBuses) {
        dailyRunningBusesDao.updateEntity(dailyRunningBuses);
    }

    public void startDailyRunningBus(Trips trip, VehicleGpsDataRequest data) {
        System.out.println("data.getVehicle_id():"+data.getVehicle_id());
        Buses bus = busesService.getBusById(data.getVehicle_id());
        Drivers driver = driversService.getDriver(bus.getDriverId());

        DailyRunningBuses dr = new DailyRunningBuses();
        dr.setArrived_time("none");
        dr.setVehicle_status(VehicleStatus.ONTIME.getStatusValue());
        dr.setCurrent_stop("none");
        dr.setRunning_date(data.getCreated_at());
//        dr.setIs_bus_arrived_to_school(false);
//        dr.setIs_bus_out_of_school(false);
        dr.setLatitude(Double.toString(data.getGps_lat()));
        dr.setLongitude(Double.toString(data.getGps_long()));
        dr.setUsers_in_bus(0);
        dr.setTrip_id(trip.getId());
        dr.setTrip_status(TripStatus.RUNNING.getStatusValue());
        dr.setDriver_id(driver.getId());
        dr.setTrip_start_time(trip.getTripDetail().getTrip_start_time_hours() + ":"
                + trip.getTripDetail().getTrip_start_time_minutes());
        dr.setTrip_end_time(trip.getTripDetail().getTrip_end_time_hours() + ":"
                + trip.getTripDetail().getTrip_end_time_minutes());

        dailyRunningBusesDao.insertDailyRunningBuses(dr);

        logger.info("Bus [ " + data.getVehicle_id() + " ] Trip [ " + trip.getTripDetail().getTrip_name()
                + " ] started by " + " Name : [ " + driver.getDriver_name() + "]]");
        System.out.println("Bus [ " + data.getVehicle_id() + " ] Trip [ " + trip.getTripDetail().getTrip_name()
                + " ] started by " + " Name : [ " + driver.getDriver_name() + "]]");
        // Handle the case where driver does not click on start trip.
    }

    public void setBusArrivedToLastStop(DailyRunningBuses dailyRunningBuses) {
        dailyRunningBusesDao.updateEntity(dailyRunningBuses);
    }

    public void updateTripEndTime(int dailyRunningBusId, String format) {
        DailyRunningBuses db = dailyRunningBusesDao.getDailyBusbyId(dailyRunningBusId);
        db.setTrip_end_time(format);
        dailyRunningBusesDao.updateEntity(db);
    }
    
//    public void updateTripEndTime(int id, String format) {
//        DailyRunningBuses db = dailyRunningBusesDao.getDailyBusbyId(id);
//        db.setTrip_end_time(format);
//        dailyRunningBusesDao.updateEntity(db);
//    }

    // public DailyRunningBuses getDailyRunningBus(int trip_id, Date date,
    // String trip_status) {
    // return dailyRunningBusesDao.getDailyRunningBus(trip_id,
    // date.toLocaleString(), trip_status);
    // }

    public List<DailyRunningBuses> getDailyRunningBus(Date date, TripStatus trip_status) {
        return dailyRunningBusesDao.getDailyRunningBus(date, trip_status);
    }

    public List<DailyRunningBuses> getDailyRunningBusList(Date date, int trip_id) {
        return dailyRunningBusesDao.getDailyRunningBusList(date, trip_id);
    }

    public DailyRunningBuses getDailyRunningBusWithRunningStatus(Date date, int trip_id) {
        return dailyRunningBusesDao.getDailyRunningBusWithRunningStatus(date, trip_id);
    }

    public List<DailyRunningBuses> getCurrentRunningBuses(Date date) {
        return dailyRunningBusesDao.getDailyRunningBus(date);
    }

    public DailyBusCoordinates getLatestBusCoordinates(Date date, int trip_id) {
        return dailyRunningBusesDao.getLatestBusCoordinates(date, trip_id);
    }

    public List<DailyRunningBuses> getBusesByDates(Date date1, Date date2) {
        return dailyRunningBusesDao.getBusesByDates(date1, date2);
    }

}
