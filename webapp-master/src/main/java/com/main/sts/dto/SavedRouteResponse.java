package com.main.sts.dto;

public class SavedRouteResponse extends SavedRouteDTO {

    private String from_stop_name;
    private String to_stop_name;

    private String from_stop_shortcode;
    private String to_stop_shortcode;

    public String getFrom_stop_name() {
        return from_stop_name;
    }

    public void setFrom_stop_name(String from_stop_name) {
        this.from_stop_name = from_stop_name;
    }

    public String getTo_stop_name() {
        return to_stop_name;
    }

    public void setTo_stop_name(String to_stop_name) {
        this.to_stop_name = to_stop_name;
    }

    public String getFrom_stop_shortcode() {
        return from_stop_shortcode;
    }

    public void setFrom_stop_shortcode(String from_stop_shortcode) {
        this.from_stop_shortcode = from_stop_shortcode;
    }

    public String getTo_stop_shortcode() {
        return to_stop_shortcode;
    }

    public void setTo_stop_shortcode(String to_stop_shortcode) {
        this.to_stop_shortcode = to_stop_shortcode;
    }

    @Override
    public String toString() {
        return "SavedRouteResponse [from_stop_name=" + from_stop_name + ", to_stop_name=" + to_stop_name
                + ", from_stop_shortcode=" + from_stop_shortcode + ", to_stop_shortcode=" + to_stop_shortcode + "]";
    }

}
