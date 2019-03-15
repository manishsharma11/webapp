package com.main.sts.entities;

import java.util.List;

public class TrackingDetails {

    // stops for a particular user, based on booking_id
    public List<StopPosition> stops;
    public List<StopPosition> tracking_points;
    public Location userLocation;
    public Location vehicleLocation;
    public boolean bus_arrived_to_trip_first_stop = false;
    public boolean bus_left_user_pickup_point = false;

    public Location getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(Location userLocation) {
        this.userLocation = userLocation;
    }

    public Location getVehicleLocation() {
        return vehicleLocation;
    }

    public void setVehicleLocation(Location vehicleLocation) {
        this.vehicleLocation = vehicleLocation;
    }

    public List<StopPosition> getStops() {
        return stops;
    }

    public void setStops(List<StopPosition> stops) {
        this.stops = stops;
    }

    public List<StopPosition> getTracking_points() {
        return tracking_points;
    }

    public void setTracking_points(List<StopPosition> tracking_points) {
        this.tracking_points = tracking_points;
    }

    public boolean isBus_left_user_pickup_point() {
        return bus_left_user_pickup_point;
    }

    public void setBus_left_user_pickup_point(boolean bus_left_user_pickup_point) {
        this.bus_left_user_pickup_point = bus_left_user_pickup_point;
    }

    public boolean isBus_arrived_to_trip_first_stop() {
        return bus_arrived_to_trip_first_stop;
    }

    public void setBus_arrived_to_trip_first_stop(boolean bus_arrived_to_trip_first_stop) {
        this.bus_arrived_to_trip_first_stop = bus_arrived_to_trip_first_stop;
    }

}
