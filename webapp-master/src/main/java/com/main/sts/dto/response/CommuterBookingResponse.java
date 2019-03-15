package com.main.sts.dto.response;

import java.io.Serializable;
import java.util.List;

public class CommuterBookingResponse implements Serializable {

    public int trip_id;
    public String stop_name;
    public String stop_time;
    public Integer stop_number;

    List<CommuterResponse> commuters;

    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    public String getStop_name() {
        return stop_name;
    }

    public void setStop_name(String stop_name) {
        this.stop_name = stop_name;
    }

    public String getStop_time() {
        return stop_time;
    }

    public void setStop_time(String stop_time) {
        this.stop_time = stop_time;
    }

    public Integer getStop_number() {
        return stop_number;
    }

    public void setStop_number(Integer stop_number) {
        this.stop_number = stop_number;
    }

    public List<CommuterResponse> getCommuters() {
        return commuters;
    }

    public void setCommuters(List<CommuterResponse> commuters) {
        this.commuters = commuters;
    }

}
