package com.main.sts.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.main.sts.common.CommonConstants;

public class BookingWebDTO implements Comparable<BookingWebDTO>, Serializable {

    public int id;
    public int commuter_id;
    public int from_stop;
    public int to_stop;
    public int actual_fare;
    public int charged_fare;
    public Date booking_travel_date;
    public Date booking_time;
    public int num_seats_booked = 1;
    public int status;
    public int bus_id;
    public int trip_id;
    //public String booking_travel_date_time;
    public Date booking_expirary_date;

    public boolean message_sent_for_pickup;
    public boolean message_sent_for_dropoff;

    public Date booking_cancellation_date;

    // if a user try to book 4 person in one ticket, we will deduct 4 free
    // rides.
    public Integer charged_free_rides;

    public String commuter_name;
    public String mobile;
    public String gender;
    
    public String from_stop_name;
    public String to_stop_name;
    
    public String trip_name;
    public String trip_type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommuter_id() {
        return commuter_id;
    }

    public void setCommuter_id(int commuter_id) {
        this.commuter_id = commuter_id;
    }

    public int getFrom_stop() {
        return from_stop;
    }

    public void setFrom_stop(int from_stop) {
        this.from_stop = from_stop;
    }

    public int getTo_stop() {
        return to_stop;
    }

    public void setTo_stop(int to_stop) {
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

    public Date getBooking_time() {
        return booking_time;
    }

    public void setBooking_time(Date booking_time) {
        this.booking_time = booking_time;
    }

    public int getNum_seats_booked() {
        return num_seats_booked;
    }

    public void setNum_seats_booked(int num_seats_booked) {
        this.num_seats_booked = num_seats_booked;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getBus_id() {
        return bus_id;
    }

    public void setBus_id(int bus_id) {
        this.bus_id = bus_id;
    }

    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

//    public String getBooking_travel_date_time() {
//        return booking_travel_date_time;
//    }
//
//    public void setBooking_travel_date_time(String booking_travel_date_time) {
//        this.booking_travel_date_time = booking_travel_date_time;
//    }

    public Date getBooking_expirary_date() {
        return booking_expirary_date;
    }

    public void setBooking_expirary_date(Date booking_expirary_date) {
        this.booking_expirary_date = booking_expirary_date;
    }

    public boolean isMessage_sent_for_pickup() {
        return message_sent_for_pickup;
    }

    public void setMessage_sent_for_pickup(boolean message_sent_for_pickup) {
        this.message_sent_for_pickup = message_sent_for_pickup;
    }

    public boolean isMessage_sent_for_dropoff() {
        return message_sent_for_dropoff;
    }

    public void setMessage_sent_for_dropoff(boolean message_sent_for_dropoff) {
        this.message_sent_for_dropoff = message_sent_for_dropoff;
    }

    public Date getBooking_cancellation_date() {
        return booking_cancellation_date;
    }

    public void setBooking_cancellation_date(Date booking_cancellation_date) {
        this.booking_cancellation_date = booking_cancellation_date;
    }

    public Integer getCharged_free_rides() {
        return charged_free_rides;
    }

    public void setCharged_free_rides(Integer charged_free_rides) {
        this.charged_free_rides = charged_free_rides;
    }

    public String getCommuter_name() {
        return commuter_name;
    }

    public void setCommuter_name(String commuter_name) {
        this.commuter_name = commuter_name;
    }

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
    
    

//    public BookingWebDTO toBookingWebDTO(Booking b) {
//        this.id = b.getId();
//        this.commuter_id = b.getCommuter_id();
//        this.from_stop = b.getFrom_stop();
//        this.to_stop = b.getTo_stop();
//        this.actual_fare = b.getActual_fare();
//        this.charged_fare = b.getCharged_fare();
//        this.booking_travel_date = b.getBooking_travel_date();
//        this.booking_time = b.getBooking_time();
//        this.num_seats_booked = b.getNum_seats_booked();
//        this.status = b.getStatus();
//        this.bus_id = b.getBus_id();
//        this.trip_id = b.getTrip_id();
//        this.booking_travel_date_time = b.getBooking_travel_date_time();
//        this.booking_expirary_date = b.getBooking_expirary_date();
//        this.message_sent_for_pickup = b.isMessage_sent_for_pickup();
//        this.message_sent_for_dropoff = b.isMessage_sent_for_dropoff();
//        this.booking_cancellation_date = b.getBooking_cancellation_date();
//        this.charged_free_rides = b.getCharged_free_rides();
//        this.commuter_name = "test";
//        this.from_stop_name = "pointa";
//        this.to_stop_name = "pointb";
//        return this;
//    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public int compareTo(BookingWebDTO o) {
        return ((Integer)id).compareTo((Integer)o.id);
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTrip_name() {
        return trip_name;
    }

    public void setTrip_name(String trip_name) {
        this.trip_name = trip_name;
    }

    public String getTrip_type() {
        return trip_type;
    }

    public void setTrip_type(String trip_type) {
        this.trip_type = trip_type;
    }

    @Override
    public String toString() {
        return "BookingWebDTO [id=" + id + ", commuter_id=" + commuter_id + ", from_stop=" + from_stop + ", to_stop="
                + to_stop + ", actual_fare=" + actual_fare + ", charged_fare=" + charged_fare
                + ", booking_travel_date=" + booking_travel_date + ", booking_time=" + booking_time
                + ", num_seats_booked=" + num_seats_booked + ", status=" + status + ", bus_id=" + bus_id + ", trip_id="
                + trip_id + ", booking_expirary_date=" + booking_expirary_date + ", message_sent_for_pickup="
                + message_sent_for_pickup + ", message_sent_for_dropoff=" + message_sent_for_dropoff
                + ", booking_cancellation_date=" + booking_cancellation_date + ", charged_free_rides="
                + charged_free_rides + ", commuter_name=" + commuter_name + ", mobile=" + mobile + ", gender=" + gender
                + ", from_stop_name=" + from_stop_name + ", to_stop_name=" + to_stop_name + ", trip_name=" + trip_name
                + ", trip_type=" + trip_type + "]";
    }
    
    
}
