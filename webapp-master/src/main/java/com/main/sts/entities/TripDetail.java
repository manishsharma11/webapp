package com.main.sts.entities;

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
@Table(name = "trip_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "trip_details")
public class TripDetail implements Comparable<TripDetail> {

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "trip_details_id_seq_gen")
    @SequenceGenerator(name = "trip_details_id_seq_gen", sequenceName = "trip_details_id_seq")
    private int id;
    private String trip_name;
    private String trip_display_name;
    private String trip_type;

    // private int seats_filled;

    private int trip_start_time_hours;
    private int trip_start_time_minutes;
    private int trip_end_time_hours;
    private int trip_end_time_minutes;
    private int busid;
    private int routeid;
    // private Date trip_running_date;

    public int getRouteid() {
        return routeid;
    }

    public void setRouteid(int routeid) {
        this.routeid = routeid;
    }

    public int getBusid() {
        return busid;
    }

    public void setBusid(int busid) {
        this.busid = busid;
    }

    @OneToOne(targetEntity = Buses.class)
    @JoinColumn(name = "busid", referencedColumnName = "bus_id", insertable = false, updatable = false)
    private Buses bus;

    @OneToOne(targetEntity = Routes.class)
    @JoinColumn(name = "routeid", referencedColumnName = "id", insertable = false, updatable = false)
    private Routes routes;

    @Override
    public int compareTo(TripDetail o) {
        return this.trip_name.compareTo(o.getTrip_name());
    }

    public int getId() {
        return id;
    }

    public String getTrip_name() {
        return trip_name;
    }

    public String getTrip_display_name() {
        if (trip_display_name == null) {
            return getTrip_name();
        }
        return trip_display_name;
    }

    public void setTrip_display_name(String trip_display_name) {
        this.trip_display_name = trip_display_name;
    }

    public String getTrip_type() {
        return trip_type;
    }

    // public int getSeats_filled() {
    // return seats_filled;
    // }

    public int getTrip_start_time_hours() {
        return trip_start_time_hours;
    }

    public int getTrip_start_time_minutes() {
        return trip_start_time_minutes;
    }

    public int getTrip_end_time_hours() {
        return trip_end_time_hours;
    }

    public int getTrip_end_time_minutes() {
        return trip_end_time_minutes;
    }

    public Buses getBus() {
        return bus;
    }

    public Routes getRoutes() {
        return routes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTrip_name(String trip_name) {
        this.trip_name = trip_name;
    }

    public void setTrip_type(String trip_type) {
        this.trip_type = trip_type;
    }

    // public void setSeats_filled(int seats_filled) {
    // this.seats_filled = seats_filled;
    // }

    public void setTrip_start_time_hours(int trip_start_time_hours) {
        this.trip_start_time_hours = trip_start_time_hours;
    }

    public void setTrip_start_time_minutes(int trip_start_time_minutes) {
        this.trip_start_time_minutes = trip_start_time_minutes;
    }

    public void setTrip_end_time_hours(int trip_end_time_hours) {
        this.trip_end_time_hours = trip_end_time_hours;
    }

    public void setTrip_end_time_minutes(int trip_end_time_minutes) {
        this.trip_end_time_minutes = trip_end_time_minutes;
    }

    public void setBus(Buses bus) {
        this.bus = bus;
    }

    public void setRoutes(Routes routes) {
        this.routes = routes;
    }

    // public Date getTrip_running_date() {
    // return trip_running_date;
    // }
    //
    // public void setTrip_running_date(Date trip_running_date) {
    // this.trip_running_date = trip_running_date;
    // }

    @Override
    public String toString() {
        return "Trips [id=" + id + ", trip_name=" + trip_name + ", trip_type=" + trip_type + ", school_time_hours="
                + ", trip_start_time_hours=" + trip_start_time_hours + ", trip_start_time_minutes="
                + trip_start_time_minutes + ", trip_end_time_hours=" + trip_end_time_hours + ", trip_end_time_minutes="
                + trip_end_time_minutes + ", busid=" + busid + ", bus=" + bus + ", routes=" + routes + "]";
    }

}
