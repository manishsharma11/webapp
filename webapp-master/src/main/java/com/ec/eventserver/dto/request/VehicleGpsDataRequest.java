package com.ec.eventserver.dto.request;

import java.io.Serializable;
import java.util.Date;

import com.main.sts.entities.Buses;
import com.main.sts.entities.Trips;
import com.main.sts.service.BusesService;
import com.main.sts.service.TripService;

public class VehicleGpsDataRequest implements Serializable {

    String device_id;
    double gps_lat;
    double gps_long;
    Integer trip_id;
    Integer vehicle_id;
    double vehicle_speed;

    private Date created_at;
    private String time;

    public String getDevice_id() {
        return device_id;
    }
    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }
    public double getGps_lat() {
        return gps_lat;
    }
    public void setGps_lat(double gps_lat) {
        this.gps_lat = gps_lat;
    }
    public double getGps_long() {
        return gps_long;
    }
    public void setGps_long(double gps_long) {
        this.gps_long = gps_long;
    }
    public Integer getTrip_id() {
        return trip_id;
    }
    public void setTrip_id(Integer trip_id) {
        this.trip_id = trip_id;
    }
    public Integer getVehicle_id() {
        return vehicle_id;
    }
    public void setVehicle_id(Integer vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
    public double getVehicle_speed() {
        return vehicle_speed;
    }
    public void setVehicle_speed(double vehicle_speed) {
        this.vehicle_speed = vehicle_speed;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public Buses getVehicle(BusesService vehicleService, TripService tripService) {
        if (trip_id != null) {
            Trips trip = tripService.getTrip(trip_id);
            int vehicle_id = trip.getTripDetail().getBusid();
            Buses buses = vehicleService.getBusById(vehicle_id);
            return buses;
        }
        return null;
    }

    public String getEventTimeInHoursMins() {
        String current_time = created_at.getHours() + ":" + created_at.getMinutes();
        return current_time;
    }
}
