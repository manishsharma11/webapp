package com.main.sts.dto.response;

public class SaveRoutesResponseDTO {

    Integer commuter_id;
    Integer from_stop_id;
    Integer to_stop_id;
    Integer booking_id;
   
    
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
    public Integer getFrom_stop_id() {
        return from_stop_id;
    }
    public void setFrom_stop_id(Integer from_stop_id) {
        this.from_stop_id = from_stop_id;
    }
    public Integer getTo_stop_id() {
        return to_stop_id;
    }
    public void setTo_stop_id(Integer to_stop_id) {
        this.to_stop_id = to_stop_id;
    }
    @Override
    public String toString() {
        return "SavedRouteDTO [commuter_id=" + commuter_id + ", from_stop_id=" + from_stop_id + ", to_stop_id="
                + to_stop_id + "]";
    
}
}
