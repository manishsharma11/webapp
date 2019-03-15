package com.main.sts.dto;

import java.io.Serializable;

public class StopDTO implements Serializable {

    private String stop_name;

    private int stop_position;
    private String stop_time;
    private String time_duration;
    private Integer route_id;
    private Integer stop_id;

    private String latitude;
    private String longitude;
    private String geofence;
    private Boolean enabled;

    boolean start_stop;
    boolean end_stop;

    public String getStop_name() {
        return stop_name;
    }
    public void setStop_name(String stop_name) {
        this.stop_name = stop_name;
    }
    public boolean isStart_stop() {
        return start_stop;
    }
    public void setStart_stop(boolean start_stop) {
        this.start_stop = start_stop;
    }
    public boolean isEnd_stop() {
        return end_stop;
    }
    public void setEnd_stop(boolean end_stop) {
        this.end_stop = end_stop;
    }
    public int getStop_position() {
        return stop_position;
    }
    public void setStop_position(int stop_position) {
        this.stop_position = stop_position;
    }
    public String getStop_time() {
        return stop_time;
    }
    public void setStop_time(String stop_time) {
        this.stop_time = stop_time;
    }

    public String getTime_duration() {
        return time_duration;
    }
    public void setTime_duration(String time_duration) {
        this.time_duration = time_duration;
    }
    public Integer getRoute_id() {
        return route_id;
    }
    public void setRoute_id(Integer route_id) {
        this.route_id = route_id;
    }
    public Integer getStop_id() {
        return stop_id;
    }
    public void setStop_id(Integer stop_id) {
        this.stop_id = stop_id;
    }
    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    public String getGeofence() {
        return geofence;
    }
    public void setGeofence(String geofence) {
        this.geofence = geofence;
    }
    public Boolean getEnabled() {
        return enabled;
    }
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    @Override
    public String toString() {
        return "StopDTO [stop_name=" + stop_name + ", stop_position=" + stop_position + ", stop_time=" + stop_time
                + ", time_duration=" + time_duration + ", route_id=" + route_id + ", stop_id=" + stop_id
                + ", latitude=" + latitude + ", longitude=" + longitude + ", geofence=" + geofence + ", enabled="
                + enabled + ", start_stop=" + start_stop + ", end_stop=" + end_stop + "]";
    }


}