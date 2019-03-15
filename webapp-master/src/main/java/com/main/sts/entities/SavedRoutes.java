package com.main.sts.entities;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "saved_routes")
public class SavedRoutes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "saved_routes_id_seq_gen")
    @SequenceGenerator(name = "saved_routes_id_seq_gen", sequenceName = "saved_routes_id_seq")
    private int id;

    private Integer commuter_id;
    
    private Integer from_stop_id;
    private Integer to_stop_id;

    private Integer pickup_time_hours;
    private Integer pickup_time_min;
    private Integer dropoff_time_hours;
    private Integer dropoff_time_min;

    private Date created_at;
    private Date updated_at;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
    
    @Override
    public String toString() {
        return "SavedRoutes [id=" + id + ", commuter_id=" + commuter_id
                + ", from_stop_id=" + from_stop_id + ", to_stop_id=" + to_stop_id + ", pickup_time_hours="
                + pickup_time_hours + ", pickup_time_min=" + pickup_time_min + ", dropoff_time_hours="
                + dropoff_time_hours + ", dropoff_time_min=" + dropoff_time_min + ", created_at=" + created_at
                + ", updated_at=" + updated_at + "]";
    }

}
