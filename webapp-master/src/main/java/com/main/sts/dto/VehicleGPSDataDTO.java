package com.main.sts.dto;

public class VehicleGPSDataDTO extends GPSDataDTO {

    String ec_device_id;
    // String vehicle_number;
    Integer trip_id;

    // public String getVehicle_number() {
    // return vehicle_number;
    // }
    //
    // public void setVehicle_number(String vehicle_number) {
    // this.vehicle_number = vehicle_number;
    // }

    public Integer getTrip_id() {
        return trip_id;
    }

    public String getEc_device_id() {
        return ec_device_id;
    }

    public void setEc_device_id(String ec_device_id) {
        this.ec_device_id = ec_device_id;
    }

    public void setTrip_id(Integer trip_id) {
        this.trip_id = trip_id;
    }

    @Override
    public int compareTo(GPSDataDTO o) {
        VehicleGPSDataDTO o1 = (VehicleGPSDataDTO) o;
        int i = this.ec_device_id.compareTo(o1.ec_device_id);
        if (i != 0) {
            return i;
        }

        return super.compareTo(o1);
    }

    @Override
    public String toString() {
        return "VehicleGPSDataDTO [ec_device_id=" + ec_device_id + ", trip_id=" + trip_id + ", getGps_lat()="
                + getGps_lat() + ", getGps_long()=" + getGps_long() + "]";
    }



}
