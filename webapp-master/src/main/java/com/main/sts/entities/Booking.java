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

@Entity
@Table(name = "booking")
/**
 * @author rahul
 *
 */
public class Booking implements Comparable<Booking>, Serializable {

//  //@SequenceGenerator(name = "booking_id_seq_gen", sequenceName = "booking_id_seq")

    
//    @Id
//    @GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
//            @org.hibernate.annotations.Parameter(name = "sequenceName", value = "sequence"),
//            @org.hibernate.annotations.Parameter(name = "allocationSize", value = "1"),
//    })
//    @GeneratedValue(generator = "sequence", strategy=GenerationType.SEQUENCE)
    
//    @Id
//    @GenericGenerator(name = "booking_id_seq_gen", strategy = "sequence", parameters = {
//            @org.hibernate.annotations.Parameter(name = "sequenceName", value = "booking_id_seq"),
//            @org.hibernate.annotations.Parameter(name = "allocationSize", value = "1"),})
    //@GeneratedValue(generator = "booking_id_seq_gen", strategy = GenerationType.IDENTITY)
   // @GeneratedValue(strategy = GenerationType.IDENTITY)

//    @Id
//    @GenericGenerator(name = "booking_id_seq_gen", strategy = "enhanced-table", parameters = {
//            @org.hibernate.annotations.Parameter(name = "table_name", value = "sequence_table")
//    })
//    @GeneratedValue(generator = "table", strategy=GenerationType.TABLE)
    
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "booking_id_seq_gen")
    @SequenceGenerator(name = "booking_id_seq_gen", sequenceName = "booking_id_seq")
    private int id;
    private int commuter_id;
    private int from_stop;
    private int to_stop;
    private int actual_fare;
    private int charged_fare;
    private Date booking_travel_date;
    private Date booking_time;
    private int num_seats_booked = 1;

    /**
     * {@link CommonConstants.BookingStatus#getBookingStatus()}
     */
    // Use BookingStatus.getBookingStatus for dealing with this variable.
    // don't hardcode the value anywhere in code. use this enum.
    private int status;
    private int bus_id;
    private int trip_id;
    
    @Transient
    private String booking_travel_date_time;
    
    private Date booking_expirary_date;

    private boolean message_sent_for_pickup;
    private boolean message_sent_for_dropoff;
    
    private Date booking_cancellation_date;
    
    // if a user try to book 4 person in one ticket, we will deduct 4 free rides. 
    private Integer charged_free_rides;
  
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

    public int getNum_seats_booked() {
        return num_seats_booked;
    }

    public void setNum_seats_booked(int num_seats_booked) {
        this.num_seats_booked = num_seats_booked;
    }

    public String getBooking_travel_date_time() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm");
        System.out.println("booking_travel_date:"+booking_travel_date);
        Date d = booking_travel_date;
        if (d != null) {
            String text = sdf.format(d);
            booking_travel_date_time = text;
        }
        return booking_travel_date_time;
    }
    
    public String[] getBookingTravelDateAndTimeString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd-MM-yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm a");

        System.out.println("booking_travel_date:" + booking_travel_date);
        Date d = booking_travel_date;
        String[] dateTime = new String[2];
        if (d != null) {
            String date = dateFormat.format(d);
            String time = timeFormat.format(d);
            dateTime[0] = date;
            dateTime[1] = time;
            return dateTime;
        }
        return null;
    }
    
//    public void setBooking_travel_date_time(String booking_travel_date_time) {
//        this.booking_travel_date_time = booking_travel_date_time;
//    }

    @Override
    public int compareTo(Booking o) {
        return ((Integer) o.getId()).compareTo((Integer) o.getId());
    }

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

    public void setBooking_travel_date_time(String booking_travel_date_time) {
        this.booking_travel_date_time = booking_travel_date_time;
    }

    public Integer getCharged_free_rides() {
        if (charged_free_rides == null) {
            charged_free_rides = 0;
        }
        return charged_free_rides;
    }

    public void setCharged_free_rides(Integer charged_free_rides) {
        this.charged_free_rides = charged_free_rides;
    }

    @Override
    public String toString() {
        return "Booking [id=" + id + ", commuter_id=" + commuter_id + ", from_stop=" + from_stop + ", to_stop="
                + to_stop + ", actual_fare=" + actual_fare + ", charged_fare=" + charged_fare
                + ", booking_travel_date=" + booking_travel_date + ", booking_time=" + booking_time
                + ", num_seats_booked=" + num_seats_booked + ", status=" + status + ", bus_id=" + bus_id + ", trip_id="
                + trip_id + ", booking_travel_date_time=" + booking_travel_date_time + ", booking_expirary_date="
                + booking_expirary_date + ", message_sent_for_pickup=" + message_sent_for_pickup
                + ", message_sent_for_dropoff=" + message_sent_for_dropoff + ", booking_cancellation_date="
                + booking_cancellation_date + "]";
    }
    
}
