package com.main.sts.dto.response;

public class BookingStopDetailsResponse {

    String stop_image_path;
    String help_text;
    // e.g. EXPIRED
    String booking_status;

    public String getStop_image_path() {
        return stop_image_path;
    }
    public void setStop_image_path(String stop_image_path) {
        this.stop_image_path = stop_image_path;
    }
    public String getHelp_text() {
        return help_text;
    }
    public void setHelp_text(String help_text) {
        this.help_text = help_text;
    }
    public String getBooking_status() {
        return booking_status;
    }
    public void setBooking_status(String booking_status) {
        this.booking_status = booking_status;
    }

}
