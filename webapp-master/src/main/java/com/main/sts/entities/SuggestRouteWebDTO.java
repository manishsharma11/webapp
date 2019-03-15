package com.main.sts.entities;

import java.sql.Timestamp;
import java.util.Comparator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

public class SuggestRouteWebDTO implements Comparator {

    public Integer id;
    public String from_stop;
    public String to_stop;
    public Timestamp created_at;
    
    // can be null for backward compatibility
    public Integer commuter_id;
    public Integer start_time_hour;
    public Integer start_time_minutes;
    public Integer end_time_hour;
    public Integer end_time_minutes;

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }
    
    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
    
    public Integer getCommuter_id() {
        return commuter_id;
    }

    public void setCommuter_id(Integer commuter_id) {
        this.commuter_id = commuter_id;
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

    @Override
    public int compare(Object o1, Object o2) {
        SuggestRouteWebDTO r1 = (SuggestRouteWebDTO) o1;
        SuggestRouteWebDTO r2 = (SuggestRouteWebDTO) o2;
        if (r1.id > r2.id)
            return 1;
        else
            return 0;
    }

}
