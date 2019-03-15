package com.main.sts.dto.response;

import java.io.Serializable;
import java.util.Date;

import com.main.sts.common.CommonConstants.BookingStatus;
import com.main.sts.service.ReturnCodes;

public class BookingHistoryResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer booking_id;;
    private int commuter_id;
    private int from_stop_id;
    private int to_stop_id;
    private String from_stop;
    private String to_stop;
    private int actual_fare;
    private int charged_fare;
    private Date booking_travel_date;
    private String booking_travel_date_time;
    private BookingStatus booking_status;
    private int num_seats_booked;

    private String distance_km;
    private String general_travel_time;
    
    // if booking_tracking_enabled is false, then it will show "SHOW ROUTE MAP" and 
    // if it is true then it will show "TRACK MY BUS"
    private boolean booking_tracking_enabled;

    private int charged_free_rides;

    ReturnCodes returnCode;

    public Integer getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(Integer booking_id) {
        this.booking_id = booking_id;
    }

    public int getCommuter_id() {
        return commuter_id;
    }

    public void setCommuter_id(int commuter_id) {
        this.commuter_id = commuter_id;
    }

    public int getFrom_stop_id() {
        return from_stop_id;
    }

    public void setFrom_stop_id(int from_stop_id) {
        this.from_stop_id = from_stop_id;
    }

    public int getTo_stop_id() {
        return to_stop_id;
    }

    public void setTo_stop_id(int to_stop_id) {
        this.to_stop_id = to_stop_id;
    }

    public String getFrom_stop() {
        return from_stop;
    }

    public void setFrom_stop(String from_stop) {
        this.from_stop = from_stop;
    }

    public String getTo_stop() {
        return to_stop;
    }

    public void setTo_stop(String to_stop) {
        this.to_stop = to_stop;
    }

    public int getActual_fare() {
        return actual_fare;
    }

    public void setActual_fare(int actual_fare) {
        this.actual_fare = actual_fare;
    }

    public int getCharged_fare() {
        return charged_fare;
    }

    public void setCharged_fare(int charged_fare) {
        this.charged_fare = charged_fare;
    }

    public Date getBooking_travel_date() {
        return booking_travel_date;
    }

    public void setBooking_travel_date(Date booking_travel_date) {
        this.booking_travel_date = booking_travel_date;
    }

    public String getBooking_travel_date_time() {
        return booking_travel_date_time;
    }

    public void setBooking_travel_date_time(String booking_travel_date_time) {
        this.booking_travel_date_time = booking_travel_date_time;
    }

    public ReturnCodes getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(ReturnCodes returnCode) {
        this.returnCode = returnCode;
    }

    public int getNum_seats_booked() {
        return num_seats_booked;
    }

    public void setNum_seats_booked(int num_seats_booked) {
        this.num_seats_booked = num_seats_booked;
    }

    public BookingStatus getBooking_status() {
        return booking_status;
    }

    public void setBooking_status(BookingStatus booking_status) {
        this.booking_status = booking_status;
    }

    public String getDistance_km() {
        return distance_km;
    }

    public void setDistance_km(String distance_km) {
        this.distance_km = distance_km;
    }

    public String getGeneral_travel_time() {
        return general_travel_time;
    }

    public void setGeneral_travel_time(String general_travel_time) {
        this.general_travel_time = general_travel_time;
    }

    public boolean isBooking_tracking_enabled() {
        return booking_tracking_enabled;
    }

    public void setBooking_tracking_enabled(boolean booking_tracking_enabled) {
        this.booking_tracking_enabled = booking_tracking_enabled;
    }

    public int getCharged_free_rides() {
        return charged_free_rides;
    }

    public void setCharged_free_rides(Integer charged_free_rides) {
        if (charged_free_rides == null) {
            charged_free_rides = 0;
        }
        this.charged_free_rides = charged_free_rides;
    }

    @Override
    public String toString() {
        return "BookingHistoryResponse [booking_id=" + booking_id + ", commuter_id=" + commuter_id + ", from_stop="
                + from_stop + ", to_stop=" + to_stop + ", charged_fare=" + charged_fare + ", booking_travel_date="
                + booking_travel_date + ", booking_travel_date_time=" + booking_travel_date_time + ", returnCode=" + returnCode
                + "]";
    }

}
