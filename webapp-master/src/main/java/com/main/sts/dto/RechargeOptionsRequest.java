package com.main.sts.dto;

import java.io.Serializable;

public class RechargeOptionsRequest implements Serializable {

    private Integer commuter_id;

    public int getCommuter_id() {
        return commuter_id;
    }

    public void setCommuter_id(int commuter_id) {
        this.commuter_id = commuter_id;
    }

    @Override
    public String toString() {
        return "RechargeOptionsRequest [commuter_id=" + commuter_id + "]";
    }

}
