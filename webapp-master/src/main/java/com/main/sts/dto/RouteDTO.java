package com.main.sts.dto;

import java.io.Serializable;

public class RouteDTO implements Serializable {

    // can be null for backward compatibility
    Integer commuter_id;
    String from_stop;
    String to_stop;
    Integer start_time_hour;
    Integer start_time_minutes;
    Integer end_time_hour;
    Integer end_time_minutes;

    public Integer getCommuter_id() {
        return commuter_id;
    }

    public void setCommuter_id(Integer commuter_id) {
        this.commuter_id = commuter_id;
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

    public Integer getStart_time_hour() {
        return start_time_hour;
    }

    public void setStart_time_hour(Integer start_time_hour) {
        this.start_time_hour = start_time_hour;
    }

    public Integer getStart_time_minutes() {
        return start_time_minutes;
    }

    public void setStart_time_minutes(Integer start_time_minutes) {
        this.start_time_minutes = start_time_minutes;
    }

    public Integer getEnd_time_hour() {
        return end_time_hour;
    }

    public void setEnd_time_hour(Integer end_time_hour) {
        this.end_time_hour = end_time_hour;
    }

    public Integer getEnd_time_minutes() {
        return end_time_minutes;
    }

    public void setEnd_time_minutes(Integer end_time_minutes) {
        this.end_time_minutes = end_time_minutes;
    }

}
