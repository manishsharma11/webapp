package com.main.sts.dto;

import java.io.Serializable;
import java.util.Date;

import com.main.sts.entities.Buses;
import com.main.sts.entities.RouteStops;
import com.main.sts.entities.TripDetail;
import com.main.sts.entities.Trips;

public class ShuttleTimingsDTO implements Serializable {

    public static final long serialVersionUID = 1L;

    // id and trip_id both are same. they are only for backward comaptability.
    private int id;
    public int trip_id;
    public String trip_name;
    public String trip_display_name;
    public String trip_type;

    public int seats_filled;
    public int busid;
    public int routeid;
    public Date trip_running_date;
    private String vehicle_model;

    // If trip is enabled or not. like trip for a holiday wouldnt be active.
    private Boolean enabled;

    // if trip is active or not, like trip got completed before it can be
    // booked.
    private boolean active = false;

    /**
     * Number of seats available.
     */
    private int seats_available;

    // 9.30
    public String pickup_stop_time;

    // AM or PM
    public String pickup_stop_time_ampm;

    private String eta;

    // AM or PM
    private String eta_ampm;
    
    // will be shown to user, how much we are goign to charge
    private Integer charged_fare;

    public int getId() {
        return id;// getTrip_id();
    }

    public void setId(int id) {
        this.id = id;
        setTrip_id(id);
    }

    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
        this.id = trip_id;
    }

    public String getTrip_name() {
        return trip_name;
    }

    public void setTrip_name(String trip_name) {
        this.trip_name = trip_name;
    }

    public String getTrip_display_name() {
        return trip_display_name;
    }

    public void setTrip_display_name(String trip_display_name) {
        this.trip_display_name = trip_display_name;
    }

    public String getTrip_type() {
        return trip_type;
    }

    public void setTrip_type(String trip_type) {
        this.trip_type = trip_type;
    }

    public int getSeats_filled() {
        return seats_filled;
    }

    public void setSeats_filled(int seats_filled) {
        this.seats_filled = seats_filled;
    }

    public int getBusid() {
        return busid;
    }

    public void setBusid(int busid) {
        this.busid = busid;
    }

    public int getRouteid() {
        return routeid;
    }

    public void setRouteid(int routeid) {
        this.routeid = routeid;
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

    public int getSeats_available() {
        return seats_available;
    }

    public void setSeats_available(int seats_available) {
        this.seats_available = seats_available;
    }

//    public static ShuttleTimingsDTO toTripResponse(Trips t) {
//        return toTripResponse(t, false);
//    }

    public String getVehicle_model() {
        return vehicle_model;
    }

    public void setVehicle_model(String vehicle_model) {
        this.vehicle_model = vehicle_model;
    }

    public String getPickup_stop_time() {
        return pickup_stop_time;
    }

    public void setPickup_stop_time(String pickup_stop_time) {
        this.pickup_stop_time = pickup_stop_time;
    }

    public String getPickup_stop_time_ampm() {
        return pickup_stop_time_ampm;
    }

    public void setPickup_stop_time_ampm(String pickup_stop_time_ampm) {
        this.pickup_stop_time_ampm = pickup_stop_time_ampm;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public String getEta_ampm() {
        return eta_ampm;
    }

    public void setEta_ampm(String eta_ampm) {
        this.eta_ampm = eta_ampm;
    }

    public Integer getCharged_fare() {
        return charged_fare;
    }

    public void setCharged_fare(Integer charged_fare) {
        this.charged_fare = charged_fare;
    }

    public static ShuttleTimingsDTO toTripResponse(Trips t, RouteStops rs, boolean seatsAvailableCheck) {
        ShuttleTimingsDTO tr = new ShuttleTimingsDTO();
        tr.id = t.getId();
        tr.trip_id = t.getId();
        tr.seats_filled = t.getSeats_filled();
        tr.trip_running_date = t.getTrip_running_date();
        tr.enabled = t.getEnabled();
        tr.active = t.isActive();

        if (seatsAvailableCheck) {
            Buses bus = t.getTripDetail().getBus();
            int allocated_seats = bus.getBus_allotted();
            if (allocated_seats >= tr.seats_filled) {
                tr.seats_available = allocated_seats - tr.seats_filled;
            } else {
                tr.seats_available = 0;
            }
        }

        TripDetail td = t.getTripDetail();

        tr.busid = td.getBusid();
        tr.routeid = td.getRouteid();
        tr.trip_name = td.getTrip_name();
        tr.trip_display_name = td.getTrip_display_name();

        // for some use-case it needs like where we show the trip timings, for
        // showing the vehicle type.
        tr.vehicle_model = t.getTripDetail().getBus().getBus_make_model();
        
        tr.pickup_stop_time = rs.getStop_time();
        
        Integer hours = Integer.parseInt(rs.getStop_time().split(":")[0]);
        tr.pickup_stop_time_ampm = hours < 12 ? "AM" : "PM";
        
        // TODO: implements ETA
        tr.eta = null;
        tr.eta_ampm = null;

        return tr;
    }

    @Override
    public String toString() {
        return "ShuttleTimingsDTO [id=" + id + ", trip_id=" + trip_id + ", trip_name=" + trip_name
                + ", trip_display_name=" + trip_display_name + ", trip_type=" + trip_type + ", seats_filled="
                + seats_filled + ", busid=" + busid + ", routeid=" + routeid + ", trip_running_date="
                + trip_running_date + ", vehicle_model=" + vehicle_model + ", enabled=" + enabled + ", active="
                + active + ", seats_available=" + seats_available + ", pickup_stop_time=" + pickup_stop_time
                + ", pickup_stop_time_ampm=" + pickup_stop_time_ampm + ", eta=" + eta + ", eta_ampm=" + eta_ampm + "]";
    }
    
}
