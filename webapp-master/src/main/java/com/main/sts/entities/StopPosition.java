package com.main.sts.entities;

import com.main.sts.dto.StopsDTO;

public class StopPosition {

    public StopsDTO stop;

    boolean user_boarding_point = false;
    boolean user_drop_off_point = false;

    boolean vehicle_started_point = false;
    // boolean vehicle_current_location = false;
    //
    // boolean user_current_location = false;

    public boolean isUser_boarding_point() {
        return user_boarding_point;
    }

    public void setUser_boarding_point(boolean user_boarding_point) {
        this.user_boarding_point = user_boarding_point;
    }

    public boolean isUser_drop_off_point() {
        return user_drop_off_point;
    }

    public void setUser_drop_off_point(boolean user_drop_off_point) {
        this.user_drop_off_point = user_drop_off_point;
    }

    public boolean isVehicle_started_point() {
        return vehicle_started_point;
    }

    public void setVehicle_started_point(boolean vehicle_started_point) {
        this.vehicle_started_point = vehicle_started_point;
    }

    public StopsDTO getStop() {
        return stop;
    }

    public void setStop(StopsDTO stop) {
        this.stop = stop;
    }

}
