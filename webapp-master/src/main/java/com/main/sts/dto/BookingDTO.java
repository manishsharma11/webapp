package com.main.sts.dto;

import java.io.Serializable;
import java.util.Date;

public class BookingDTO implements Serializable {

    Integer commuter_id;
    // String mobile;
    // String from_stop_code;
    // String to_stop_code;
    Integer from_stop_id;
    Integer to_stop_id;
    Date booking_travel_date;// default is for today'
    Date booking_travel_time;
    Integer trip_id;
    Integer num_seats_choosen = 1;// default 1
    // Integer vehicle_id;

    boolean sms_send_enabled = true;
    
    String promo_code;

    public Integer getCommuter_id() {
        return commuter_id;
    }

    public void setCommuter_id(Integer commuter_id) {
        this.commuter_id = commuter_id;
    }

    // public String getFrom_stop_code() {
    // return from_stop_code;
    // }
    //
    // public void setFrom_stop_code(String from_stop_code) {
    // this.from_stop_code = from_stop_code;
    // }
    //
    // public String getTo_stop_code() {
    // return to_stop_code;
    // }
    //
    // public void setTo_stop_code(String to_stop_code) {
    // this.to_stop_code = to_stop_code;
    // }

    public Integer getFrom_stop_id() {
        return from_stop_id;
    }

    public void setFrom_stop_id(Integer from_stop_id) {
        this.from_stop_id = from_stop_id;
    }

    public Integer getTo_stop_id() {
        return to_stop_id;
    }

    public void setTo_stop_id(Integer to_stop_id) {
        this.to_stop_id = to_stop_id;
    }

    public Date getBooking_date() {
        return booking_travel_date;
    }

    public void setBooking_date(Date booking_date) {
        this.booking_travel_date = booking_date;
    }

    public Date getBooking_travel_date() {
        return booking_travel_date;
    }

    public void setBooking_travel_date(Date booking_travel_date) {
        this.booking_travel_date = booking_travel_date;
    }

    public Date getBooking_travel_time() {
        return booking_travel_time;
    }

    public void setBooking_travel_time(Date booking_travel_time) {
        this.booking_travel_time = booking_travel_time;
    }

    public Integer getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(Integer trip_id) {
        this.trip_id = trip_id;
    }

    public Integer getNum_seats_choosen() {
        return num_seats_choosen;
    }

    public void setNum_seats_choosen(Integer num_seats_choosen) {
        this.num_seats_choosen = num_seats_choosen;
    }

    public boolean isSms_send_enabled() {
        return sms_send_enabled;
    }

    public void setSms_send_enabled(boolean sms_send_enabled) {
        this.sms_send_enabled = sms_send_enabled;
    }
    
    public String getPromo_code() {
        return promo_code;
    }

    public void setPromo_code(String promo_code) {
        this.promo_code = promo_code;
    }

    @Override
    public String toString() {
        return "BookingDTO [commuter_id=" + commuter_id + ", from_stop_id=" + from_stop_id + ", to_stop_id="
                + to_stop_id + ", booking_travel_date=" + booking_travel_date + ", booking_travel_time="
                + booking_travel_time + ", trip_id=" + trip_id + ", num_seats_choosen=" + num_seats_choosen
                + ", sms_send_enabled=" + sms_send_enabled + ", promo_code=" + promo_code + "]";
    }


    // public Integer getVehicle_id() {
    // return vehicle_id;
    // }
    //
    // public void setVehicle_id(Integer vehicle_id) {
    // this.vehicle_id = vehicle_id;
    // }
    
    

}
