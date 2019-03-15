package com.main.sts.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "price_fare")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "price_fare")
public class BusFarePriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "price_fare_id_seq_gen")
    @SequenceGenerator(name = "price_fare_id_seq_gen", sequenceName = "price_fare_id_seq")
    private int fare_id;

    @OneToOne(targetEntity = Stops.class)
    @JoinColumn(name = "source_stop_id", referencedColumnName = "id", insertable = false, updatable = false)
    public Stops source_stop;

    @OneToOne(targetEntity = Stops.class)
    @JoinColumn(name = "dest_stop_id", referencedColumnName = "id", insertable = false, updatable = false)
    public Stops dest_stop;

    private int source_stop_id;
    private int dest_stop_id;

    @Column(name = "actual_fare")
    private int actual_fare;
    //    private int actual_fare = charged_fare;

    @Column(name = "charged_fare")
    private int charged_fare;
    
    private int auto_fare, bus_fare;

    private String distance;
    private String time_duration;
    
    private Boolean discounted_fare_enabled = false;
    private Integer discounted_fare = 0;

    private Date created_at;
    private Date updated_at;

    public int getFare_id() {
        return fare_id;
    }

    public void setFare_id(int fare_id) {
        this.fare_id = fare_id;
    }

    public int getSource_stop_id() {
        return source_stop_id;
    }

    public void setSource_stop_id(int source_stop_id) {
        this.source_stop_id = source_stop_id;
    }

    public int getDest_stop_id() {
        return dest_stop_id;
    }

    public void setDest_stop_id(int dest_stop_id) {
        this.dest_stop_id = dest_stop_id;
    }

    public int getAuto_fare() {
        return auto_fare;
    }

    public void setAuto_fare(int auto_fare) {
        this.auto_fare = auto_fare;
    }

    public int getBus_fare() {
        return bus_fare;
    }

    public void setBus_fare(int bus_fare) {
        this.bus_fare = bus_fare;
    }

    public Stops getSource_stop() {
        return source_stop;
    }

    public void setSource_stop(Stops source_stop) {
        this.source_stop = source_stop;
    }

    public Stops getDest_stop() {
        return dest_stop;
    }

    public void setDest_stop(Stops dest_stop) {
        this.dest_stop = dest_stop;
    }

    public int getActual_fare() {
        return actual_fare;
    }

    public void setActual_fare(int actual_fare) {
        this.actual_fare = actual_fare;
    }

    public int getCharged_fare() {
        return charged_fare;
    }

    public void setCharged_fare(int charged_fare) {
        this.charged_fare = charged_fare;
    }
    
    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getTime_duration() {
        return time_duration;
    }

    public void setTime_duration(String time_duration) {
        this.time_duration = time_duration;
    }

    public Boolean getDiscounted_fare_enabled() {
        return discounted_fare_enabled;
    }

    public void setDiscounted_fare_enabled(Boolean discounted_fare_enabled) {
        this.discounted_fare_enabled = discounted_fare_enabled;
    }

    public Integer getDiscounted_fare() {
        return discounted_fare;
    }

    public void setDiscounted_fare(Integer discounted_fare) {
        this.discounted_fare = discounted_fare;
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
        return "BusFarePriceEntity [fare_id=" + fare_id + ", source_stop_id=" + source_stop_id + ", dest_stop_id="
                + dest_stop_id + ", fare=" + charged_fare + ", auto_fare=" + auto_fare + ", bus_fare=" + bus_fare + "]";
    }

}
