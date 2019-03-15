package com.main.sts.dto;

import java.io.Serializable;

import com.main.sts.service.ReturnCodes;

public class AccountDTO implements Serializable {

    Integer commuter_id;
    Integer points_balance=0;
    Integer free_rides_balance=0;
    String free_rides_account_message;
    ReturnCodes returnCode;

    public Integer getCommuter_id() {
        return commuter_id;
    }
    public void setCommuter_id(Integer commuter_id) {
        this.commuter_id = commuter_id;
    }
    public Integer getPoints_balance() {
        return points_balance;
    }
    public void setPoints_balance(Integer points_balance) {
        this.points_balance = points_balance;
    }

    public ReturnCodes getReturnCode() {
        return returnCode;
    }
    public void setReturnCode(ReturnCodes returnCode) {
        this.returnCode = returnCode;
    }

    public Integer getFree_rides_balance() {
        if (free_rides_balance == null) {
            free_rides_balance = 0;
        }
        return free_rides_balance;
    }
    public void setFree_rides_balance(Integer free_rides_balance) {
        this.free_rides_balance = free_rides_balance;
    }

    public String getFree_rides_account_message() {
        return free_rides_account_message;
    }
    public void setFree_rides_account_message(String free_rides_account_message) {
        this.free_rides_account_message = free_rides_account_message;
    }
    @Override
    public String toString() {
        return "AccountDTO [commuter_id=" + commuter_id + ", points_balance=" + points_balance
                + ", free_rides_balance=" + free_rides_balance + ", returnCode=" + returnCode + "]";
    }

}
