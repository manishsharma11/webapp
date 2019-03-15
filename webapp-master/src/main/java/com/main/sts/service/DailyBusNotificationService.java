package com.main.sts.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.sts.common.CommonConstants.VehicleStatus;
import com.main.sts.dao.sql.DailyBusNotificationsDao;
import com.main.sts.entities.DailyBusNotifications;
import com.main.sts.entities.DailyRunningBuses;

@Service
public class DailyBusNotificationService {

    @Autowired
    private DailyBusNotificationsDao dbnDao;

    public List<DailyBusNotifications> getTodysNotifications(Date running_date, VehicleStatus status) {
        return dbnDao.getDailyNotification(running_date, status);
    }

    public DailyBusNotifications getTodysNotifications(Date running_date, VehicleStatus status, int trip_id) {
        return dbnDao.getDailyNotification(running_date, status, trip_id);
    }

    public void insert(DailyRunningBuses dailyRunningBus, String time, VehicleStatus status) {
        DailyBusNotifications db = new DailyBusNotifications();
        db.setVehicle_id(dailyRunningBus.getVehicle_id());
        db.setVehicle_license_number(dailyRunningBus.getVehicle_licence_number());
        db.setRunning_date(dailyRunningBus.getRunning_date());
        db.setMessage_type("bus_" + status);
        db.setNotification("Ontime: Bus [ " + dailyRunningBus.getVehicle_licence_number() + " ] is " + status + " at Stop [ "
                + dailyRunningBus.getCurrent_stop() + " ]");
        db.setVehicle_status(status.getStatusValue());
        db.setTime(time);
        db.setTrip_id(dailyRunningBus.getTrip_id());
        dbnDao.insertEntity(db);
    }

    public void update(DailyBusNotifications busNotifications) {
        dbnDao.updateEntity(busNotifications);
    }

}
