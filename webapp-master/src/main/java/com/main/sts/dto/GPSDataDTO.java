package com.main.sts.dto;

import java.io.Serializable;
import java.util.Date;

public class GPSDataDTO implements Comparable<GPSDataDTO>, Serializable {

    // String vehicle_id;
    // vehicle number like AP284567
    String gps_lat;
    String gps_long;
    // server side;
    Date tracking_event_time;

    public String getGps_lat() {
        return gps_lat;
    }

    public void setGps_lat(String gps_lat) {
        this.gps_lat = gps_lat;
    }

    public String getGps_long() {
        return gps_long;
    }

    public void setGps_long(String gps_long) {
        this.gps_long = gps_long;
    }

    @Override
    public int compareTo(GPSDataDTO o) {
        return this.tracking_event_time.compareTo(o.tracking_event_time);
    }

    @Override
    public String toString() {
        return "GPSDataDTO [gps_lat=" + gps_lat + ", gps_long=" + gps_long + ", tracking_event_time="
                + tracking_event_time + "]";
    }
    
    

}
