package com.main.sts.dto.response;

import com.main.sts.dto.StopsDTO;
import com.main.sts.entities.RouteStops;
import com.main.sts.entities.Stops;

public class StopsResponseDTO extends StopsDTO {

    // public Integer route_stop_id;
    // public Integer stop_id;// 134
    // public String stop_name;// "Waverock"
    // public String latitude;// "17.416916"
    // public String longitude;// "78.346227"
    //public int stop_number;// 1
    public String stop_time;// "18:00"
    public Boolean enabled;// null
    //public String shortcode;// "WRCKP"
    //public String help_text;// "Near 200 m"
    public String image_path;// "/images/WRCKP.png"
    public String display_name;// "Waverock"

    public Boolean vehicle_started_point = false;
    public Boolean user_boarding_point = false;
    public Boolean user_drop_off_point = false;

    public Integer getRoute_stop_id() {
        return route_stop_id;
    }
    public void setRoute_stop_id(Integer route_stop_id) {
        this.route_stop_id = route_stop_id;
    }
    public Integer getStop_id() {
        return stop_id;
    }
    public void setStop_id(Integer stop_id) {
        this.stop_id = stop_id;
    }
    public String getStop_name() {
        return stop_name;
    }
    public void setStop_name(String stop_name) {
        this.stop_name = stop_name;
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
//    public int getStop_number() {
//        return stop_number;
//    }
    public void setStop_number(int stop_number) {
        this.stop_number = stop_number;
    }
    public String getStop_time() {
        return stop_time;
    }
    public void setStop_time(String stop_time) {
        this.stop_time = stop_time;
    }
    public Boolean getEnabled() {
        return enabled;
    }
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
//    public String getShortcode() {
//        return shortcode;
//    }
//    public void setShortcode(String shortcode) {
//        this.shortcode = shortcode;
//    }
//    public String getHelp_text() {
//        return help_text;
//    }
//    public void setHelp_text(String help_text) {
//        this.help_text = help_text;
//    }
    public String getImage_path() {
        return image_path;
    }
    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }
    public String getDisplay_name() {
        return display_name;
    }
    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }
    public Boolean getVehicle_started_point() {
        return vehicle_started_point;
    }
    public void setVehicle_started_point(Boolean vehicle_started_point) {
        this.vehicle_started_point = vehicle_started_point;
    }
    public Boolean getUser_boarding_point() {
        return user_boarding_point;
    }
    public void setUser_boarding_point(Boolean user_boarding_point) {
        this.user_boarding_point = user_boarding_point;
    }
    public Boolean getUser_drop_off_point() {
        return user_drop_off_point;
    }
    public void setUser_drop_off_point(Boolean user_drop_off_point) {
        this.user_drop_off_point = user_drop_off_point;
    }
    
    
    public static StopsResponseDTO toStopResponse(RouteStops rs) {
        StopsResponseDTO sr = new StopsResponseDTO();

        sr.route_stop_id = rs.getId();
        Stops s = rs.getStop();
        sr.stop_id = s.getId();
        sr.stop_name = s.getStop_name();
        sr.latitude = s.getLatitude();
        sr.longitude = s.getLongitude();
        sr.stop_number = rs.getStop_number();
        sr.stop_time = rs.getStop_time();
        sr.enabled = s.getEnabled();
        sr.shortcode = s.getShortcode();
        sr.help_text = s.getHelp_text();
        sr.image_path = s.getImage_path();
        sr.display_name = s.getDisplay_name();
        return sr;
    }

    public static StopsResponseDTO toStopResponse(RouteStops rs, int source_stop_id, int dest_stop_id, int first_stop_id) {
        StopsResponseDTO sr = toStopResponse(rs);
        Stops s = rs.getStop();

        if (s.getId() == source_stop_id) {
            sr.user_boarding_point = true;
        }
        if (s.getId() == dest_stop_id) {
            sr.user_drop_off_point = true;
        }
        if (s.getId() == first_stop_id) {
            sr.vehicle_started_point = true;
        }
        return sr;
    }

    @Override
    public String toString() {
        return "StopsResponseDTO [stop_number=" + stop_number + ", stop_time=" + stop_time + ", enabled=" + enabled
                + ", shortcode=" + shortcode + ", help_text=" + help_text + ", image_path=" + image_path
                + ", display_name=" + display_name + ", vehicle_started_point=" + vehicle_started_point
                + ", user_boarding_point=" + user_boarding_point + ", user_drop_off_point=" + user_drop_off_point + "]";
    }
    
    

}
