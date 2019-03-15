package com.main.sts.dto;

import java.io.Serializable;
import java.util.List;

public class RouteStopsDTO implements Serializable {

    int route_id;
    String route_name;
    List<StopDTO> stops;
    String timings;

    // these two variables are for internal purpose (for applying sortign based
    // on startHour and Min)
    int routeStartHour;
    int routeStartMin;

    public String getRoute_name() {
        return route_name;
    }

    public void setRoute_name(String route_name) {
        this.route_name = route_name;
    }

    public List<StopDTO> getStops() {
        return stops;
    }

    public void setStops(List<StopDTO> stops) {
        this.stops = stops;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public int getRoute_id() {
        return route_id;
    }

    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }

    public int getRouteStartHour() {
        return routeStartHour;
    }

    public void setRouteStartHour(int routeStartHour) {
        this.routeStartHour = routeStartHour;
    }

    public int getRouteStartMin() {
        return routeStartMin;
    }

    public void setRouteStartMin(int routeStartMin) {
        this.routeStartMin = routeStartMin;
    }

    @Override
    public String toString() {
        return "RouteStopsDTO [route_id=" + route_id + ", route_name=" + route_name + ", timings=" + timings + "]";
    }

}
