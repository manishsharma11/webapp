package com.main.sts.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "daily_driver_speed")
public class DriverSpeedEntity {

    @Id
    @GeneratedValue
    private int id;

    private int driver_id;
    private String start_time;
    private String end_time;
    private String trip_name;
    private String bus_licence_number;
    private Date trip_running_date;
    private String driver_name;

    private Double start_longitude;
    private Double end_longitude;

    private Double start_latitude;
    private Double end_latitude;

    int highest_speed;

    public int getId() {
        return id;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getTrip_name() {
        return trip_name;
    }

    public String getBus_licence_number() {
        return bus_licence_number;
    }

    public Date getTrip_Running_Date() {
        return trip_running_date;
    }

    public String getDriver_name() {
        return driver_name;
    }

    public Double getStart_longitude() {
        return start_longitude;
    }

    public Double getEnd_longitude() {
        return end_longitude;
    }

    public Double getStart_latitude() {
        return start_latitude;
    }

    public Double getEnd_latitude() {
        return end_latitude;
    }

    public int getHighest_speed() {
        return highest_speed;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public void setTrip_name(String trip_name) {
        this.trip_name = trip_name;
    }

    public void setBus_licence_number(String bus_licence_number) {
        this.bus_licence_number = bus_licence_number;
    }

    public void setTrip_running_Date(Date date) {
        this.trip_running_date = date;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

    public void setStart_longitude(Double start_longitude) {
        this.start_longitude = start_longitude;
    }

    public void setEnd_longitude(Double end_longitude) {
        this.end_longitude = end_longitude;
    }

    public void setStart_latitude(Double start_latitude) {
        this.start_latitude = start_latitude;
    }

    public void setEnd_latitude(Double end_latitude) {
        this.end_latitude = end_latitude;
    }

    public void setHighest_speed(int highest_speed) {
        this.highest_speed = highest_speed;
    }

    @Override
    public String toString() {
        return "DriverSpeedEntity [id=" + id + ", driver_id=" + driver_id + ", start_time=" + start_time
                + ", end_time=" + end_time + ", trip_name=" + trip_name + ", bus_licence_number=" + bus_licence_number
                + ", date=" + trip_running_date + ", driver_name=" + driver_name + ", start_longitude=" + start_longitude
                + ", end_longitude=" + end_longitude + ", stat_latitude=" + start_latitude + ", end_latitude="
                + end_latitude + ", highest_speed=" + highest_speed + "]";
    }

}
