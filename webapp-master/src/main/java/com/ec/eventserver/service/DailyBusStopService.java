package com.ec.eventserver.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.eventserver.dao.DailyBusStopsDao;
import com.ec.eventserver.dto.request.VehicleGpsDataRequest;
import com.ec.eventserver.models.DailyBusStops;
import com.main.sts.entities.RouteStops;
import com.main.sts.entities.VehicleGpsData;

@Service
public class DailyBusStopService {

    @Autowired
    private DailyBusStopsDao busStopDao;

    public void insertDailyBusStop(VehicleGpsDataRequest data, RouteStops routeStop, int trip_id) {
        String arrived_time = data.getEventTimeInHoursMins();
        DailyBusStops dailyBusStop = new DailyBusStops();
        dailyBusStop.setArrived_time(arrived_time);
        dailyBusStop.setRunning_date(data.getCreated_at());
        dailyBusStop.setExpected_time(routeStop.getStop_time());
        dailyBusStop.setIs_eta_sent(false);
        dailyBusStop.setIs_stop_out_of_range(false);
        dailyBusStop.setLatitude(data.getGps_lat());
        dailyBusStop.setLongitude(data.getGps_long());
        dailyBusStop.setRoutestop_id(routeStop.getId());
        dailyBusStop.setTrip_id(trip_id);
        busStopDao.insertEntity(dailyBusStop);
    }

    public DailyBusStops getDailyBusStop(int id) {
        return busStopDao.getDailyBusStop(id);
    }

    public DailyBusStops getDailyBusStop(int trip_id, Date date, int routestop_id) {
        return busStopDao.getDailyBusStop(trip_id, date, routestop_id);
    }

    public void setBusOutOfStop(int id) {
        DailyBusStops busStops = busStopDao.getDailyBusStop(id);
        busStops.setIs_stop_out_of_range(true);
        busStopDao.updateEntity(busStops);
    }

    public DailyBusStops getLastStopsByTripAndDate(Date date, int id) {
        return busStopDao.getDailyLastBusStop(date, id);
    }

    public List<DailyBusStops> getAllDailyBusStops(int trip_id, Date date) {
        return busStopDao.getAllDailyBusStops(trip_id, date);
    }

    public int getCount(int i) {
        return busStopDao.getCount(i);
    }

}
