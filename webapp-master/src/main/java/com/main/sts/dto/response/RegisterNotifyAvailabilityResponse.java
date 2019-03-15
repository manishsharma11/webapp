package com.main.sts.dto.response;

public class RegisterNotifyAvailabilityResponse {

    int trip_id;
    int commuter_id;
    boolean request_status = false;
    String message;

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

    public boolean isRequest_status() {
        return request_status;
    }
    public void setRequest_status(boolean request_status) {
        this.request_status = request_status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
