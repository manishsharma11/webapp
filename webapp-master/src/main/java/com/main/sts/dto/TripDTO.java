package com.main.sts.dto;

import java.io.Serializable;

public class TripDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private int from_stop_id;
    private int to_stop_id;

    private String from_stop_code;
    private String to_stop_code;

    public int getFrom_stop_id() {
        return from_stop_id;
    }
    public void setFrom_stop_id(int from_stop_id) {
        this.from_stop_id = from_stop_id;
    }
    public int getTo_stop_id() {
        return to_stop_id;
    }
    public void setTo_stop_id(int to_stop_id) {
        this.to_stop_id = to_stop_id;
    }
    public String getFrom_stop_code() {
        return from_stop_code;
    }
    public void setFrom_stop_code(String from_stop_code) {
        this.from_stop_code = from_stop_code;
    }
    public String getTo_stop_code() {
        return to_stop_code;
    }
    public void setTo_stop_code(String to_stop_code) {
        this.to_stop_code = to_stop_code;
    }

}
