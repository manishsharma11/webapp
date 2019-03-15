package com.main.sts.dto;

import java.io.Serializable;

public class SavedRouteDTO implements Serializable {
    Integer commuter_id;
    Integer from_stop_id;
    Integer to_stop_id;
    Integer pickup_time_hours;
    Integer pickup_time_min;
    Integer dropoff_time_hours;
    Integer dropoff_time_min;

    public Integer getCommuter_id() {
        return commuter_id;
    }
    public void setCommuter_id(Integer commuter_id) {
        this.commuter_id = commuter_id;
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
    public Integer getPickup_time_hours() {
        return pickup_time_hours;
    }
    public void setPickup_time_hours(Integer pickup_time_hours) {
        this.pickup_time_hours = pickup_time_hours;
    }
    public Integer getPickup_time_min() {
        return pickup_time_min;
    }
    public void setPickup_time_min(Integer pickup_time_min) {
        this.pickup_time_min = pickup_time_min;
    }
    public Integer getDropoff_time_hours() {
        return dropoff_time_hours;
    }
    public void setDropoff_time_hours(Integer dropoff_time_hours) {
        this.dropoff_time_hours = dropoff_time_hours;
    }
    public Integer getDropoff_time_min() {
        return dropoff_time_min;
    }
    public void setDropoff_time_min(Integer dropoff_time_min) {
        this.dropoff_time_min = dropoff_time_min;
    }

}
