package com.main.sts.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ec.eventserver.service.DeviceService;
import com.main.sts.service.BusesService;
import com.main.sts.service.TripService;

@Entity
@Table(name = "bus_gps_data")
public class VehicleGpsData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "device_id")
    String ec_device_id;

    @Column(name = "trip_id")
    Integer trip_id;

    @Column(name = "latitude")
    String latitude;

    @Column(name = "longitude")
    String longitude;

    @Column(name = "created_at")
    private Date event_time;

    public String getEc_device_id() {
        return ec_device_id;
    }

    public void setEc_device_id(String ec_device_id) {
        this.ec_device_id = ec_device_id;
    }

    public Integer getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(Integer trip_id) {
        this.trip_id = trip_id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getEventTime() {
        return event_time;
    }

    public void setEventTime(Date eventTime) {
        this.event_time = eventTime;
    }

    @Override
    public String toString() {
        return "VehicleGpsData [id=" + id + ", ec_device_id=" + ec_device_id + ", trip_id=" + trip_id + ", gps_lat="
                + latitude + ", gps_long=" + longitude + ", created_at=" + event_time + "]";
    }
    

}
