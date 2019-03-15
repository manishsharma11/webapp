package com.main.sts.entities;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "trips")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "trips")
public class Trips implements Comparable<Trips> {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "trips_id_seq_gen")
    @SequenceGenerator(name = "trips_id_seq_gen", sequenceName = "trips_id_seq")
    private int id;
    private int trip_detail_id;
    private int seats_filled;
    private Date trip_running_date;
    private Boolean enabled = true;

    @OneToOne(targetEntity = TripDetail.class)
    @JoinColumn(name = "trip_detail_id", referencedColumnName = "id", insertable = false, updatable = false)
    @BatchSize(size = 20)
    private TripDetail tripDetail;
    
    @Transient
    private boolean active = true;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrip_detail_id() {
        return trip_detail_id;
    }

    public void setTrip_detail_id(int trip_detail_id) {
        this.trip_detail_id = trip_detail_id;
    }

    public TripDetail getTripDetail() {
        return tripDetail;
    }

    public void setTripDetail(TripDetail tripDetail) {
        this.tripDetail = tripDetail;
    }

    public int getSeats_filled() {
        return seats_filled;
    }

    public void setSeats_filled(int seats_filled) {
        this.seats_filled = seats_filled;
    }

    public Date getTrip_running_date() {
        return trip_running_date;
    }

    public void setTrip_running_date(Date trip_running_date) {
        this.trip_running_date = trip_running_date;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public int compareTo(Trips o) {
        return this.trip_running_date.compareTo(o.trip_running_date);
    }
    
    public Date getTripRunningTime() {
        return getTripRunningTimeCal().getTime();
    }

    public Calendar getTripRunningTimeCal() {
        Trips t = this;
        TripDetail td = t.getTripDetail();
        System.out.println("trip:" + td.getTrip_name() + "---" + t.getTrip_running_date());
        Calendar tripRunningTime = Calendar.getInstance();
        tripRunningTime.setTime(t.getTrip_running_date());
        tripRunningTime.set(Calendar.HOUR_OF_DAY, td.getTrip_start_time_hours());
        tripRunningTime.set(Calendar.MINUTE, td.getTrip_start_time_minutes());
        tripRunningTime.set(Calendar.SECOND, 0);
        tripRunningTime.set(Calendar.MILLISECOND, 0);
        return tripRunningTime;
    }

    @Override
    public String toString() {
        return "Trips [id=" + id + ", trip_detail_id=" + trip_detail_id + ", seats_filled=" + seats_filled
                + ", trip_running_date=" + trip_running_date + ", enabled=" + enabled + ", tripDetail=" + tripDetail
                + "]";
    }

}
