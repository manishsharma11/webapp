package com.main.sts.dto.response;

import java.io.Serializable;

import com.main.sts.service.ReturnCodes;

public class BookingCancellationResponse implements Serializable {

    Integer booking_id;
    Integer commuter_id;
    Integer refunded_points;
    Integer refunded_free_rides;
    Integer account_balance;
    Integer free_rides_balance;
    ReturnCodes returnCode;

    public Integer getBooking_id() {
        return booking_id;
    }
    public void setBooking_id(Integer booking_id) {
        this.booking_id = booking_id;
    }
    public Integer getCommuter_id() {
        return commuter_id;
    }
    public void setCommuter_id(Integer commuter_id) {
        this.commuter_id = commuter_id;
    }
    public Integer getRefunded_points() {
        return refunded_points;
    }
    public void setRefunded_points(Integer refunded_points) {
        this.refunded_points = refunded_points;
    }
    public Integer getAccount_balance() {
        return account_balance;
    }
    public void setAccount_balance(Integer account_balance) {
        this.account_balance = account_balance;
    }
    public ReturnCodes getReturnCode() {
        return returnCode;
    }
    public void setReturnCode(ReturnCodes returnCode) {
        this.returnCode = returnCode;
    }
    public Integer getRefunded_free_rides() {
        return refunded_free_rides;
    }
    public void setRefunded_free_rides(Integer refunded_free_rides) {
        this.refunded_free_rides = refunded_free_rides;
    }
    public Integer getFree_rides_balance() {
        return free_rides_balance;
    }
    public void setFree_rides_balance(Integer free_rides_balance) {
        this.free_rides_balance = free_rides_balance;
    }

}
