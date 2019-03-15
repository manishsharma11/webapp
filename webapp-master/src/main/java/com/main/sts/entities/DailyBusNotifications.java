package com.main.sts.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.main.sts.common.CommonConstants;

@Entity
@Table(name = "daily_bus_notification")
public class DailyBusNotifications {

    @Id
    @GeneratedValue
    private int id;
    private int vehicle_id;
    private Date running_date;
    private int trip_id;
    private String vehicle_license_number;
    private String notification;
    private String message_type;

    /**
     * {@link CommonConstants.VehicleStatus}
     */
    private int vehicle_status;
    private String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getVehicle_license_number() {
        return vehicle_license_number;
    }

    public void setVehicle_license_number(String vehicle_license_number) {
        this.vehicle_license_number = vehicle_license_number;
    }

    public Date getRunning_date() {
        return running_date;
    }

    public void setRunning_date(Date running_date) {
        this.running_date = running_date;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getMessage_type() {
        return message_type;
    }

    public void setMessage_type(String message_type) {
        this.message_type = message_type;
    }

    public int getVehicle_status() {
        return vehicle_status;
    }

    public void setVehicle_status(int vehicle_status) {
        this.vehicle_status = vehicle_status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    @Override
    public String toString() {
        return "DailyBusNotifications [id=" + id + ", bus_id=" + vehicle_id + ", running_date=" + running_date
                + ", trip_id=" + trip_id + ", bus_licence_number=" + vehicle_license_number + ", notification="
                + notification + ", message_type=" + message_type + ", status=" + vehicle_status + ", time=" + time
                + "]";
    }

}
