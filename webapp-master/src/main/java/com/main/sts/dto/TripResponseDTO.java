package com.main.sts.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.main.sts.dto.response.StopsResponseDTO;
import com.main.sts.entities.Buses;
import com.main.sts.entities.RouteStops;
import com.main.sts.entities.TripDetail;
import com.main.sts.entities.Trips;
import com.main.sts.service.RouteStopsService;
import com.main.sts.service.TripService;

public class TripResponseDTO implements Serializable {

    public static final long serialVersionUID = 1L;

    // id and trip_id both are same. they are only for backward comaptability.
    private int id;
    public int trip_id;
    public String trip_name;
    public String trip_display_name;
    public String trip_type;

    public int seats_filled;
    public int school_time_hours;
    public int school_time_minutes;
    public int trip_start_time_hours;
    public int trip_start_time_minutes;
    public int trip_end_time_hours;
    public int trip_end_time_minutes;
    public int busid;
    public int routeid;
    public Date trip_running_date;
    
    // TODO: ask Android guys to remove it.
    private long booking_travel_time = -1;
    
    // If trip is enabled or not. like trip for a holiday wouldnt be active.
    private Boolean enabled;
    
    // if trip is active or not, like trip got completed before it can be booked.
    private boolean active = false;
    
    /**
     * Number of seats available.
     */
    private int seats_available;

    public List<StopsResponseDTO> stops;

    private Buses bus;

    public int getId() {
        return id;//getTrip_id();
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

    public int getSchool_time_hours() {
        return school_time_hours;
    }

    public void setSchool_time_hours(int school_time_hours) {
        this.school_time_hours = school_time_hours;
    }

    public int getSchool_time_minutes() {
        return school_time_minutes;
    }

    public void setSchool_time_minutes(int school_time_minutes) {
        this.school_time_minutes = school_time_minutes;
    }

    public int getTrip_start_time_hours() {
        return trip_start_time_hours;
    }

    public void setTrip_start_time_hours(int trip_start_time_hours) {
        this.trip_start_time_hours = trip_start_time_hours;
    }

    public int getTrip_start_time_minutes() {
        return trip_start_time_minutes;
    }

    public void setTrip_start_time_minutes(int trip_start_time_minutes) {
        this.trip_start_time_minutes = trip_start_time_minutes;
    }

    public int getTrip_end_time_hours() {
        return trip_end_time_hours;
    }

    public void setTrip_end_time_hours(int trip_end_time_hours) {
        this.trip_end_time_hours = trip_end_time_hours;
    }

    public int getTrip_end_time_minutes() {
        return trip_end_time_minutes;
    }

    public void setTrip_end_time_minutes(int trip_end_time_minutes) {
        this.trip_end_time_minutes = trip_end_time_minutes;
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
    
    public Buses getBus() {
        return bus;
    }

    public void setBus(Buses bus) {
        this.bus = bus;
    }

    public List<StopsResponseDTO> getStops() {
        return stops;
    }

    public void setStops(List<StopsResponseDTO> stops) {
        this.stops = stops;
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
    
    public long getBooking_travel_time() {
        return booking_travel_time;
    }

    public void setBooking_travel_time(long booking_travel_time) {
        this.booking_travel_time = booking_travel_time;
    }

    public int getSeats_available() {
        return seats_available;
    }

    public void setSeats_available(int seats_available) {
        this.seats_available = seats_available;
    }

    public static TripResponseDTO toTripResponse(Trips t) {
        return toTripResponse(t, false);
    }

    public static TripResponseDTO toTripResponse(Trips t, boolean seatsAvailableCheck) {
        TripResponseDTO tr = new TripResponseDTO();
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
        tr.trip_start_time_hours = td.getTrip_start_time_hours();
        tr.trip_start_time_minutes = td.getTrip_start_time_minutes();
        tr.trip_end_time_hours = td.getTrip_end_time_hours();
        tr.trip_end_time_minutes = td.getTrip_end_time_minutes();
        
        // for some use-case it needs like where we show the trip timings, for showing the vehicle type.
        tr.bus = t.getTripDetail().getBus();
        
        return tr;
    }
    
    public static TripResponseDTO toTripResponseWithStops(Trips t, RouteStopsService routeStopsService) {
        TripResponseDTO tr = toTripResponse(t);
        
        List<RouteStops> stops = routeStopsService.getStopsByRouteId(t.getTripDetail().getRouteid());

        Collections.sort(stops, new RouteStopsService.StopNumberComparator());
        
        List<StopsResponseDTO> stopRespList = new ArrayList<StopsResponseDTO>();
        for (RouteStops rs : stops) {
            StopsResponseDTO stop = StopsResponseDTO.toStopResponse(rs);
            stopRespList.add(stop);
        }
        tr.stops = stopRespList;
        return tr;
    }

}
