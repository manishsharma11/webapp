package com.main.sts.dto;

import java.io.Serializable;
import java.util.Arrays;

public class StopsDTO implements Serializable {

    public Integer route_stop_id;
    public Integer stop_id;// 134
    public String stop_name;// "Waverock"
    public String latitude;// "17.416916"
    public String longitude;// "78.346227"
    public Integer stop_number;// 1
    // private String stop_time;// "18:00"
    // private Boolean enabled;// null
    // private String shortcode;// "WRCKP"
    // private String help_text;// "Near 200 m"
    // private String image_path;// "/images/WRCKP.png"
    // private String display_name;// "Waverock"

    // public Integer route_stop_id;
    // public Integer stop_id;
    // public String stop_name;
    // public String latitude;
    // public String longitude;

    public String shortcode;// "WRCKP"
    public String help_text;// "Near 200 m"
    
    // Not in use, jus for backward compatability
    public String image_icon_path;

    public String stop_icon_path;
    public String stop_type_name;
    public String image_path;
    
    public String[] tags;
    
    public String stop_reaching_time;
    
    public Boolean stop_icon_enabled;
    
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

    public Integer getStop_number() {
        return stop_number;
    }

    public void setStop_number(Integer stop_number) {
        this.stop_number = stop_number;
    }

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

    public String getShortcode() {
        return shortcode;
    }
    
    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }
    
    public String getHelp_text() {
        return help_text;
    }
    public void setHelp_text(String help_text) {
        this.help_text = help_text;
    }

    public String getStop_reaching_time() {
        return stop_reaching_time;
    }

    public void setStop_reaching_time(String stop_reaching_time) {
        this.stop_reaching_time = stop_reaching_time;
    }

    public String getStop_icon_path() {
        return stop_icon_path;
    }

    public void setStop_icon_path(String stop_icon_path) {
        this.stop_icon_path = stop_icon_path;
    }

    public String getStop_type_name() {
        return stop_type_name;
    }

    public void setStop_type_name(String stop_type_name) {
        this.stop_type_name = stop_type_name;
    }

    public Boolean getStop_icon_enabled() {
        return stop_icon_enabled;
    }

    public void setStop_icon_enabled(Boolean stop_icon_enabled) {
        this.stop_icon_enabled = stop_icon_enabled;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getImage_icon_path() {
        if (image_icon_path == null) {
            return stop_icon_path;
        }
        return image_icon_path;
    }

    public void setImage_icon_path(String image_icon_path) {
        this.image_icon_path = image_icon_path;
        this.stop_icon_path = image_icon_path;
    }

    @Override
    public String toString() {
        return "StopsDTO [route_stop_id=" + route_stop_id + ", stop_id=" + stop_id + ", stop_name=" + stop_name
                + ", latitude=" + latitude + ", longitude=" + longitude + ", stop_number=" + stop_number
                + ", shortcode=" + shortcode + ", help_text=" + help_text + ", stop_icon_path=" + stop_icon_path
                + ", stop_type_name=" + stop_type_name + ", image_path=" + image_path + ", tags="
                + Arrays.toString(tags) + ", stop_reaching_time=" + stop_reaching_time + ", stop_icon_enabled="
                + stop_icon_enabled + "]";
    }
    
    
}
