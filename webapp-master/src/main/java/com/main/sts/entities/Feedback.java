package com.main.sts.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "feedback")
public class Feedback implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer feedback_id;
    Integer commuter_id;
    Integer trip_id;
    Integer booking_id;
    Integer rating_points;
    String reason;// fixed set of reasons
    String concern; // message.
    Timestamp created_at;

    public Integer getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(Integer feedback_id) {
        this.feedback_id = feedback_id;
    }

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

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

}
