package com.main.sts.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.eventserver.dto.request.VehicleGpsDataRequest;
import com.main.sts.dao.sql.DailyBusCoordinatesDao;
import com.main.sts.entities.DailyBusCoordinates;

@Service
public class DailyBusCoordinatesService {

    @Autowired
    private DailyBusCoordinatesDao dailyBusCoordinatesDao;

    public void insertDailyBusCoordinates(VehicleGpsDataRequest data, int trip_id) {

        DailyBusCoordinates bc = new DailyBusCoordinates();
        bc.setBus_speed(data.getVehicle_speed());

        Date d = data.getCreated_at();

        bc.setRunning_date(d);
        bc.setLatitude(data.getGps_lat());
        bc.setLongitude(data.getGps_long());
        bc.setTrip_id(trip_id);
        dailyBusCoordinatesDao.insertDailyBusCoordinates(bc);
    }

    public DailyBusCoordinates getLastCordinates(int trip_id, Date trip_date) {
        return dailyBusCoordinatesDao.getLast(trip_id, trip_date);
    }

    public DailyBusCoordinates getFirstCordinates(int trip_id, Date trip_date) {
        return dailyBusCoordinatesDao.getFirst(trip_id, trip_date);
    }

    public List<DailyBusCoordinates> getTripsCoordinates(int trip_id, Date trip_date) {
        return dailyBusCoordinatesDao.getTripsCoordinates(trip_id, trip_date);
    }
    
    public List<DailyBusCoordinates> getTripsCoordinates(int trip_id, Date trip_date, int limit) {
        return dailyBusCoordinatesDao.getTripsCoordinates(trip_id, trip_date, limit);
    }

}
