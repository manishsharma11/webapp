package com.main.sts.dto;

import java.io.Serializable;

public class PreBookingDTO implements Serializable {

    Integer commuter_id;
    Integer num_seats_choosen;
    int trip_id;
    Integer from_stop_id;
    Integer to_stop_id;

    public Integer getCommuter_id() {
        return commuter_id;
    }

    public void setCommuter_id(Integer commuter_id) {
        this.commuter_id = commuter_id;
    }

    public Integer getNum_seats_choosen() {
        return num_seats_choosen;
    }

    public void setNum_seats_choosen(Integer num_seats_choosen) {
        this.num_seats_choosen = num_seats_choosen;
    }

    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    public Integer getFrom_stop_id() {
        return from_stop_id;
    }

    public void setFrom_stop_id(Integer from_stop_id) {
        this.from_stop_id = from_stop_id;
    }

    public Integer getTo_stop_id() {
        return to_stop_id;
    }

    public void setTo_stop_id(Integer to_stop_id) {
        this.to_stop_id = to_stop_id;
    }

    @Override
    public String toString() {
        return "PreBookingDTO [trip_id=" + trip_id + ", from_stop_id=" + from_stop_id + ", to_stop_id=" + to_stop_id
                + "]";
    }

}
