package com.main.sts.dao;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//preparing test data dao, so I can query the already existing test_bus_gps_data table to simulate the event tracking

@Entity
@Table(name = "test_bus_gps_data")
public class VehicleGpsTestData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "device_id")
    String ec_device_id;

    @Column(name = "trip_id")
    Integer trip_id;

    @Column(name = "lat")
    String gps_lat;

    @Column(name = "long")
    String gps_long;

    private Timestamp created_at;

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

    public String getGps_lat() {
        return gps_lat;
    }

    public void setGps_lat(String gps_lat) {
        this.gps_lat = gps_lat;
    }

    public String getGps_long() {
        return gps_long;
    }

    public void setGps_long(String gps_long) {
        this.gps_long = gps_long;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "VehicleGpsData [id=" + id + ", vehicle_number=" + ec_device_id + ", trip_id=" + trip_id + ", gps_lat="
                + gps_lat + ", gps_long=" + gps_long + ", created_at=" + created_at + "]";
    }

}
