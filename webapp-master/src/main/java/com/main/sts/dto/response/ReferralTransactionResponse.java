package com.main.sts.dto.response;

import java.io.Serializable;
import java.sql.Timestamp;

import com.main.sts.service.ReturnCodes;

public class ReferralTransactionResponse implements Serializable {

    private Integer transaction_id;
    private ReturnCodes returnCode;
    private Integer commuter_id;
    private Integer free_rides_available;
    private Timestamp created_at;
    private String message;

    public Integer getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(Integer transaction_id) {
        this.transaction_id = transaction_id;
    }

    public ReturnCodes getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(ReturnCodes returnCode) {
        this.returnCode = returnCode;
    }

    public Integer getCommuter_id() {
        return commuter_id;
    }

    public void setCommuter_id(Integer commuter_id) {
        this.commuter_id = commuter_id;
    }

    public Integer getFree_rides_available() {
        return free_rides_available;
    }

    public void setFree_rides_available(Integer free_rides_available) {
        this.free_rides_available = free_rides_available;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "TransactionResponse [transaction_id=" + transaction_id + ", returnCode=" + returnCode
                + ", commuter_id=" + commuter_id + ", credits_available=" + free_rides_available + ", created_at="
                + created_at + ", message=" + message + "]";
    }

}
