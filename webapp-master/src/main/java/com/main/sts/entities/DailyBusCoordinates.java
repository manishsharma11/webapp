package com.main.sts.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "daily_bus_coordinates")
public class DailyBusCoordinates {

    @Id
    @GeneratedValue
    private int id;
    private Date running_date;
    private int trip_id;
    private double bus_speed;
    private Double latitude;
    private Double longitude;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Date getRunning_date() {
        return running_date;
    }
    public void setRunning_date(Date running_date) {
        this.running_date = running_date;
    }
    public int getTrip_id() {
        return trip_id;
    }
    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }
    public double getBus_speed() {
        return bus_speed;
    }
    public void setBus_speed(double bus_speed) {
        this.bus_speed = bus_speed;
    }
    public Double getLatitude() {
        return latitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public Double getLongitude() {
        return longitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    
    @Override
    public String toString() {
        return "DailyBusCoordinates [id=" + id + ", running_date=" + running_date + ", trip_id=" + trip_id + ", bus_speed=" + bus_speed
                + ", latitude=" + latitude + ", longitude=" + longitude + "]";
    }

}
