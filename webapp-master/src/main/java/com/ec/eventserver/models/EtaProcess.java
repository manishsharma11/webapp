package com.ec.eventserver.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "eta_process")
public class EtaProcess {

    @Id
    @GeneratedValue
    private int id;
    private String stop_name;
    private String trip_type;
    private Date running_date;
    private String bus_licence_number;
    private String expected_time;
    private String actual_time;
    private int trip_id;
    private int stop_id;
    private int bus_id;
    private int route_id;
    private int count;
    private int stop_number;

    private boolean is_eta_sent;
    public int getId() {
        return id;
    }
    public String getStop_name() {
        return stop_name;
    }
    public String getTrip_type() {
        return trip_type;
    }

    public Date getRunning_date() {
        return running_date;
    }
    public void setRunning_date(Date running_date) {
        this.running_date = running_date;
    }
    public String getBus_licence_number() {
        return bus_licence_number;
    }
    public String getExpected_time() {
        return expected_time;
    }
    public String getActual_time() {
        return actual_time;
    }
    public int getTrip_id() {
        return trip_id;
    }
    public int getStop_id() {
        return stop_id;
    }
    public int getBus_id() {
        return bus_id;
    }
    public int getRoute_id() {
        return route_id;
    }
    public int getCount() {
        return count;
    }
    public int getStop_number() {
        return stop_number;
    }
    public boolean isIs_eta_sent() {
        return is_eta_sent;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setStop_name(String stop_name) {
        this.stop_name = stop_name;
    }
    public void setTrip_type(String trip_type) {
        this.trip_type = trip_type;
    }

    public void setBus_licence_number(String bus_licence_number) {
        this.bus_licence_number = bus_licence_number;
    }
    public void setExpected_time(String expected_time) {
        this.expected_time = expected_time;
    }
    public void setActual_time(String actual_time) {
        this.actual_time = actual_time;
    }
    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }
    public void setStop_id(int stop_id) {
        this.stop_id = stop_id;
    }
    public void setBus_id(int bus_id) {
        this.bus_id = bus_id;
    }
    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public void setStop_number(int stop_number) {
        this.stop_number = stop_number;
    }
    public void setIs_eta_sent(boolean is_eta_sent) {
        this.is_eta_sent = is_eta_sent;
    }

    @Override
    public String toString() {
        return "EtaProcess [id=" + id + ", stop_name=" + stop_name + ", trip_type=" + trip_type + ", running_date="
                + running_date + ", bus_licence_number=" + bus_licence_number + ", expected_time=" + expected_time
                + ", actual_time=" + actual_time + ", trip_id=" + trip_id + ", stop_id=" + stop_id + ", bus_id="
                + bus_id + ", route_id=" + route_id + ", count=" + count + ", stop_number=" + stop_number
                + ", is_eta_sent=" + is_eta_sent + "]";
    }

}
