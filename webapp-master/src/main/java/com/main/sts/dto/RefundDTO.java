package com.main.sts.dto;

import java.io.Serializable;

public class RefundDTO implements Serializable {

    Integer commuter_id;
    Integer refund_points;
    Integer refund_free_rides;
    String admin_id_or_name;
    String refund_reason;

    public Integer getCommuter_id() {
        return commuter_id;
    }
    public void setCommuter_id(Integer commuter_id) {
        this.commuter_id = commuter_id;
    }

    public Integer getRefund_points() {
        return refund_points;
    }
    public void setRefund_points(Integer refund_points) {
        this.refund_points = refund_points;
    }

    public Integer getRefund_free_rides() {
        return refund_free_rides;
    }
    public void setRefund_free_rides(Integer refund_free_rides) {
        this.refund_free_rides = refund_free_rides;
    }
    public String getAdmin_id_or_name() {
        return admin_id_or_name;
    }
    public void setAdmin_id_or_name(String admin_id_or_name) {
        this.admin_id_or_name = admin_id_or_name;
    }
    public String getRefund_reason() {
        return refund_reason;
    }
    public void setRefund_reason(String refund_reason) {
        this.refund_reason = refund_reason;
    }

}
