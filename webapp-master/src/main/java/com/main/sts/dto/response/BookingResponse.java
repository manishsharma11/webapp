package com.main.sts.dto.response;

import java.io.Serializable;

import com.main.sts.service.ReturnCodes;

public class BookingResponse implements Serializable{

    Integer booking_id;
    Integer max_active_bookings_number = -1;
    String eta;
    ReturnCodes returnCode;

    public Integer getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(Integer booking_id) {
        this.booking_id = booking_id;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public ReturnCodes getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(ReturnCodes returnCode) {
        this.returnCode = returnCode;
    }
    
    public Integer getMax_active_bookings_number() {
        return max_active_bookings_number;
    }

    public void setMax_active_bookings_number(Integer max_active_bookings_number) {
        this.max_active_bookings_number = max_active_bookings_number;
    }

    @Override
    public String toString() {
        return "BookingResponse [booking_id=" + booking_id + ", eta=" + eta + ", returnCode=" + returnCode + "]";
    }

}
