package com.main.sts.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.main.sts.common.CommonConstants;
import com.main.sts.common.CommonConstants.VehicleStatus;

@Entity
@Table(name = "daily_running_buses")
public class DailyRunningBuses {

    @Id
    @GeneratedValue
    private int id;
    private Date running_date;
    private int trip_id;
    /**
     * {@link CommonConstants.TripStatus}
     */
    private int trip_status;// running,over
    /**
     * {@link CommonConstants.VehicleStatus}
     */
    private int vehicle_status;// late,verylate,delay,ontime
    private String current_stop;
    private String arrived_time;
    private int users_in_bus;
    private Boolean is_message_sent_to_first_stop = false;
    private Boolean is_bus_out_of_first_stop = false;
    private Boolean is_bus_arrived_to_trip_last_stop = false;
    private String latitude;
    private String longitude;
    private String trip_start_time;
    private String trip_end_time;
    
    // some extra added after taking from DailyRunningBusesJspEntity
    private int driver_id;
//    private String driver_name;
    private int vehicle_id;
    private String vehicle_licence_number;
    
    @Transient
    private String vehicle_status_str;
    
    @Transient
    private String trip_status_str;
    

    public String getTrip_start_time() {
        return trip_start_time;
    }

    public void setTrip_start_time(String trip_start_time) {
        this.trip_start_time = trip_start_time;
    }

    public String getTrip_end_time() {
        return trip_end_time;
    }

    public void setTrip_end_time(String trip_end_time) {
        this.trip_end_time = trip_end_time;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getRunning_date() {
        return running_date;
    }

    public void setRunning_date(Date running_date) {
        this.running_date = running_date;
    }

    public Integer getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(Integer trip_id) {
        this.trip_id = trip_id;
    }

    public int getTrip_status() {
        return trip_status;
    }

    public void setTrip_status(int trip_status) {
        this.trip_status = trip_status;
    }

    public int getVehicle_status() {
        return vehicle_status;
    }

    public void setVehicle_status(int vehicle_status) {
        this.vehicle_status = vehicle_status;
    }

    public String getCurrent_stop() {
        return current_stop;
    }

    public void setCurrent_stop(String current_stop) {
        this.current_stop = current_stop;
    }

    public String getArrived_time() {
        return arrived_time;
    }

    public void setArrived_time(String arrived_time) {
        this.arrived_time = arrived_time;
    }

    public int getUsers_in_bus() {
        return users_in_bus;
    }

    public void setUsers_in_bus(int users_in_bus) {
        this.users_in_bus = users_in_bus;
    }

//    public boolean isIs_message_sent_to_first_stop() {
//        return is_message_sent_to_first_stop;
//    }
//
//    public void setIs_message_sent_to_first_stop(boolean is_message_sent_to_first_stop) {
//        this.is_message_sent_to_first_stop = is_message_sent_to_first_stop;
//    }
//
//    public boolean isIs_bus_out_of_school() {
//        return is_bus_out_of_school;
//    }
//
//    public void setIs_bus_out_of_school(boolean is_bus_out_of_school) {
//        this.is_bus_out_of_school = is_bus_out_of_school;
//    }
//
//    public boolean isIs_bus_arrived_to_school() {
//        return is_bus_arrived_to_school;
//    }
//
//    public void setIs_bus_arrived_to_school(boolean is_bus_arrived_to_school) {
//        this.is_bus_arrived_to_school = is_bus_arrived_to_school;
//    }
    
    
    public String getLatitude() {
        return latitude;
    }

    public boolean isIs_message_sent_to_first_stop() {
        return is_message_sent_to_first_stop;
    }

    public void setIs_message_sent_to_first_stop(Boolean is_message_sent_to_first_stop) {
        this.is_message_sent_to_first_stop = is_message_sent_to_first_stop;
    }

    public Boolean isIs_bus_out_of_first_stop() {
        return is_bus_out_of_first_stop;
    }

    public void setIs_bus_out_of_first_stop(Boolean is_bus_out_of_first_stop) {
        this.is_bus_out_of_first_stop = is_bus_out_of_first_stop;
    }

    public Boolean isIs_bus_arrived_to_trip_last_stop() {
        return is_bus_arrived_to_trip_last_stop;
    }

    public void setIs_bus_arrived_to_trip_last_stop(Boolean is_bus_arrived_to_trip_last_stop) {
        this.is_bus_arrived_to_trip_last_stop = is_bus_arrived_to_trip_last_stop;
    }

    public void setVehicle_status_str(String vehicle_status_str) {
        this.vehicle_status_str = vehicle_status_str;
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
    

//    public String getDriver_name() {
//        return driver_name;
//    }
//
//    public void setDriver_name(String driver_name) {
//        this.driver_name = driver_name;
//    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getVehicle_licence_number() {
        return vehicle_licence_number;
    }

    public void setVehicle_licence_number(String vehicle_licence_number) {
        this.vehicle_licence_number = vehicle_licence_number;
    }

    public String getVehicle_status_str() {
        return VehicleStatus.getVehicleStatus(vehicle_status).getStatusText();
    }

//    public void setVehicle_status_str(String vehicle_status_str) {
//        this.vehicle_status_str = vehicle_status_str;
//    }

    public String getTrip_status_str() {
        return trip_status_str;
    }

    public void setTrip_status_str(String trip_status_str) {
        this.trip_status_str = trip_status_str;
    }

    @Override
    public String toString() {
        return "DailyRunningBuses [id=" + id + ", running_date=" + running_date + ", trip_id=" + trip_id
                + ", trip_status=" + trip_status + ", vehicle_status=" + vehicle_status + ", current_stop="
                + current_stop + ", arrived_time=" + arrived_time + ", users_in_bus=" + users_in_bus + ", latitude="
                + latitude + ", longitude=" + longitude + ", trip_start_time=" + trip_start_time + ", trip_end_time="
                + trip_end_time + ", driver_id=" + driver_id + ", vehicle_id="
                + vehicle_id + ", vehicle_licence_number=" + vehicle_licence_number + "]";
    }

}
