package com.main.sts.dto;

import java.io.Serializable;

public class FareDTO implements Serializable {

    int from_stop_id;
    int to_stop_id;
    String from_stop;
    String to_stop;
    int charged_fare;
    int actual_fare;
    int charged_free_rides;

    public int getFrom_stop_id() {
        return from_stop_id;
    }
    public void setFrom_stop_id(int from_stop_id) {
        this.from_stop_id = from_stop_id;
    }
    public int getTo_stop_id() {
        return to_stop_id;
    }
    public void setTo_stop_id(int to_stop_id) {
        this.to_stop_id = to_stop_id;
    }
    public String getFrom_stop() {
        return from_stop;
    }
    public void setFrom_stop(String from_stop) {
        this.from_stop = from_stop;
    }
    public String getTo_stop() {
        return to_stop;
    }
    public void setTo_stop(String to_stop) {
        this.to_stop = to_stop;
    }
    public int getCharged_fare() {
        return charged_fare;
    }
    public void setCharged_fare(int charged_fare) {
        this.charged_fare = charged_fare;
    }
    public int getActual_fare() {
        return actual_fare;
    }
    public void setActual_fare(int actual_fare) {
        this.actual_fare = actual_fare;
    }

    public int getCharged_free_rides() {
        return charged_free_rides;
    }
    public void setCharged_free_rides(int charged_free_rides) {
        this.charged_free_rides = charged_free_rides;
    }

    @Override
    public String toString() {
        return "FareDTO [from_stop_id=" + from_stop_id + ", to_stop_id=" + to_stop_id + ", from_stop=" + from_stop
                + ", to_stop=" + to_stop + ", charged_fare=" + charged_fare + ", actual_fare=" + actual_fare
                + ", charged_free_rides=" + charged_free_rides + "]";
    }

}
