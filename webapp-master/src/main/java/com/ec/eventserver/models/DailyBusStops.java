package com.ec.eventserver.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "daily_bus_stops")
public class DailyBusStops {

    @Id
    @GeneratedValue
    private int id;
    private int trip_id;
    private int routestop_id;
    private Date running_date;
    private String expected_time;
    private String arrived_time;
    private Double latitude;
    private Double longitude;
    private boolean is_stop_out_of_range;
    private boolean is_eta_sent = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    public int getRoutestop_id() {
        return routestop_id;
    }

    public void setRoutestop_id(int routestop_id) {
        this.routestop_id = routestop_id;
    }

    public Date getRunning_date() {
        return running_date;
    }

    public void setRunning_date(Date running_date) {
        this.running_date = running_date;
    }

    public String getExpected_time() {
        return expected_time;
    }

    public void setExpected_time(String expected_time) {
        this.expected_time = expected_time;
    }

    public String getArrived_time() {
        return arrived_time;
    }

    public void setArrived_time(String arrived_time) {
        this.arrived_time = arrived_time;
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

    public boolean isIs_stop_out_of_range() {
        return is_stop_out_of_range;
    }

    public void setIs_stop_out_of_range(boolean is_stop_out_of_range) {
        this.is_stop_out_of_range = is_stop_out_of_range;
    }

    public boolean isIs_eta_sent() {
        return is_eta_sent;
    }

    public void setIs_eta_sent(boolean is_eta_sent) {
        this.is_eta_sent = is_eta_sent;
    }

    @Override
    public String toString() {
        return "DailyBusStops [id=" + id + ", trip_id=" + trip_id + ", routestop_id=" + routestop_id + ", date="
                + running_date + ", expected_time=" + expected_time + ", arrived_time=" + arrived_time + ", latitude="
                + latitude + ", longitude=" + longitude + ", is_stop_out_of_range=" + is_stop_out_of_range
                + ", is_eta_sent=" + is_eta_sent + "]";
    }

}
