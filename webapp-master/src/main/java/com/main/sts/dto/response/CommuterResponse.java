package com.main.sts.dto.response;

import java.io.Serializable;

public class CommuterResponse implements Serializable {

    public int commuter_id;
    public int pickup_stop_id;
    public int dropoff_stop_id;

    public String pickup_stop_name;
    public String dropoff_stop_name;

    public int charged_fare;
    public String commuter_name;
    public String mobile;

    public int booking_id;
    public int booking_status;
    
    public Boolean is_pickup = null;
    public String type = null;
    
    public int num_seats_booked;
    

    public int getCommuter_id() {
        return commuter_id;
    }

    public void setCommuter_id(int commuter_id) {
        this.commuter_id = commuter_id;
    }

    public int getPickup_stop_id() {
        return pickup_stop_id;
    }

    public void setPickup_stop_id(int pickup_stop_id) {
        this.pickup_stop_id = pickup_stop_id;
    }

    public int getDropoff_stop_id() {
        return dropoff_stop_id;
    }

    public void setDropoff_stop_id(int dropoff_stop_id) {
        this.dropoff_stop_id = dropoff_stop_id;
    }

    public String getPickup_stop_name() {
        return pickup_stop_name;
    }

    public void setPickup_stop_name(String pickup_stop_name) {
        this.pickup_stop_name = pickup_stop_name;
    }

    public String getDropoff_stop_name() {
        return dropoff_stop_name;
    }

    public void setDropoff_stop_name(String dropoff_stop_name) {
        this.dropoff_stop_name = dropoff_stop_name;
    }

    public int getCharged_fare() {
        return charged_fare;
    }

    public void setCharged_fare(int charged_fare) {
        this.charged_fare = charged_fare;
    }

    public String getCommuter_name() {
        return commuter_name;
    }

    public void setCommuter_name(String commuter_name) {
        this.commuter_name = commuter_name;
    }

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public Boolean getIs_pickup() {
        return is_pickup;
    }

    public void setIs_pickup(Boolean is_pickup) {
        this.is_pickup = is_pickup;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getBooking_status() {
        return booking_status;
    }

    public void setBooking_status(int booking_status) {
        this.booking_status = booking_status;
    }

    public int getNum_seats_booked() {
        return num_seats_booked;
    }

    public void setNum_seats_booked(int num_seats_booked) {
        this.num_seats_booked = num_seats_booked;
    }

    @Override
    public String toString() {
        return "CommuterResponse [commuter_id=" + commuter_id + ", pickup_stop_id=" + pickup_stop_id
                + ", dropoff_stop_id=" + dropoff_stop_id + ", pickup_stop_name=" + pickup_stop_name
                + ", dropoff_stop_name=" + dropoff_stop_name + ", charged_fare=" + charged_fare + ", commuter_name="
                + commuter_name + ", booking_id=" + booking_id + ", is_pickup=" + is_pickup + ", type=" + type + "]";
    }

    
}
