package com.main.sts.dto;

import java.io.Serializable;

public class FeedbackDTO implements Serializable{
	Integer commuter_id;
	Integer trip_id;
	Integer booking_id;
	Integer rating_points;
	String reason;
	String concern;

	public Integer getCommuter_id() {
		return commuter_id;
	}

	public void setCommuter_id(Integer commuter_id) {
		this.commuter_id = commuter_id;
	}

	public Integer getTrip_id() {
		return trip_id;
	}

	public void setTrip_id(Integer trip_id) {
		this.trip_id = trip_id;
	}

	
    public Integer getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(Integer booking_id) {
        this.booking_id = booking_id;
    }

    public Integer getRating_points() {
        return rating_points;
    }

	public void setRating_points(Integer rating_points) {
		this.rating_points = rating_points;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getConcern() {
		return concern;
	}

	public void setConcern(String concern) {
		this.concern = concern;
	}

}