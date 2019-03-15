package com.main.sts.dto;

public class RegisterNotifyAvailabilityDTO {

    int trip_id;
    int commuter_id;
    int from_stop_id;
    int to_stop_id;

    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    public int getCommuter_id() {
        return commuter_id;
    }

    public void setCommuter_id(int commuter_id) {
        this.commuter_id = commuter_id;
    }

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

}
