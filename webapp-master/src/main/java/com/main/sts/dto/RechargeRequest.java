package com.main.sts.dto;

import java.io.Serializable;

import com.main.sts.common.CommonConstants.TransactionStatus;

public class RechargeRequest implements Serializable {

    Integer commuter_id;
    Integer points;
    // credit, debits
    Integer tx_type;
    // success, failed
    /**
     * {@link TransactionStatus}
     */
    // SUccess: 1 , failed:2
    Integer tx_status;
    String admin_id_or_name;

    // the transaction id for the payment made on payumoney or any other system.
    private Integer payment_tx_id;

    public Integer getCommuter_id() {
        return commuter_id;
    }
    public void setCommuter_id(Integer commuter_id) {
        this.commuter_id = commuter_id;
    }
    public Integer getPoints() {
        return points;
    }
    public void setPoints(Integer points) {
        this.points = points;
    }
    public Integer getTx_type() {
        return tx_type;
    }
    public void setTx_type(Integer tx_type) {
        this.tx_type = tx_type;
    }
    public Integer getTx_status() {
        return tx_status;
    }
    public void setTx_status(Integer tx_status) {
        this.tx_status = tx_status;
    }
    public String getAdmin_id_or_name() {
        return admin_id_or_name;
    }
    public void setAdmin_id_or_name(String admin_id_or_name) {
        this.admin_id_or_name = admin_id_or_name;
    }
    public Integer getPayment_tx_id() {
        return payment_tx_id;
    }
    public void setPayment_tx_id(Integer payment_tx_id) {
        this.payment_tx_id = payment_tx_id;
    }

}
