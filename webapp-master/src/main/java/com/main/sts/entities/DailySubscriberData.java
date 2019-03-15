package com.main.sts.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "daily_subscriber_data")
public class DailySubscriberData {

	@Id
	@GeneratedValue
	private int id;
	private int subscriber_id;
	private String subscriber_type;
	private String date;
	private int trip_id;
	private String in_stop;
	private String subscriber_boarded_wrong_stop_inbound;
	private String out_stop;
	private String subscriber_boarded_wrong_stop_outbound;
	private String in_time;
	private String out_time;
	private Double in_latitude;
	private Double in_longitude;
	private Double out_latitude;
	private Double out_longitude;
	private String subscriber_name;
	private String subscriber_boarded_wrong_bus;
	private String subscriber_boarded_wrong_trip;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSubscriber_id() {
		return subscriber_id;
	}

	public void setSubscriber_id(int subscriber_id) {
		this.subscriber_id = subscriber_id;
	}

	public String getSubscriber_type() {
		return subscriber_type;
	}

	public void setSubscriber_type(String subscriber_type) {
		this.subscriber_type = subscriber_type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getTrip_id() {
		return trip_id;
	}

	public void setTrip_id(int trip_id) {
		this.trip_id = trip_id;
	}

	public String getIn_stop() {
		return in_stop;
	}

	public void setIn_stop(String in_stop) {
		this.in_stop = in_stop;
	}

	public String getSubscriber_boarded_wrong_stop_inbound() {
		return subscriber_boarded_wrong_stop_inbound;
	}

	public void setSubscriber_boarded_wrong_stop_inbound(String subscriber_boarded_wrong_stop_inbound) {
		this.subscriber_boarded_wrong_stop_inbound = subscriber_boarded_wrong_stop_inbound;
	}

	public String getOut_stop() {
		return out_stop;
	}

	public void setOut_stop(String out_stop) {
		this.out_stop = out_stop;
	}

	public String getSubscriber_boarded_wrong_stop_outbound() {
		return subscriber_boarded_wrong_stop_outbound;
	}

	public void setSubscriber_boarded_wrong_stop_outbound(String subscriber_boarded_wrong_stop_outbound) {
		this.subscriber_boarded_wrong_stop_outbound = subscriber_boarded_wrong_stop_outbound;
	}

	public String getIn_time() {
		return in_time;
	}

	public void setIn_time(String in_time) {
		this.in_time = in_time;
	}

	public String getOut_time() {
		return out_time;
	}

	public void setOut_time(String out_time) {
		this.out_time = out_time;
	}

	public Double getIn_latitude() {
		return in_latitude;
	}

	public void setIn_latitude(Double in_latitude) {
		this.in_latitude = in_latitude;
	}

	public Double getIn_longitude() {
		return in_longitude;
	}

	public void setIn_longitude(Double in_longitude) {
		this.in_longitude = in_longitude;
	}

	public Double getOut_latitude() {
		return out_latitude;
	}

	public void setOut_latitude(Double out_latitude) {
		this.out_latitude = out_latitude;
	}

	public Double getOut_longitude() {
		return out_longitude;
	}

	public void setOut_longitude(Double out_longitude) {
		this.out_longitude = out_longitude;
	}

	public String getSubscriber_name() {
		return subscriber_name;
	}

	public void setSubscriber_name(String subscriber_name) {
		this.subscriber_name = subscriber_name;
	}

	public String getSubscriber_boarded_wrong_bus() {
		return subscriber_boarded_wrong_bus;
	}

	public void setSubscriber_boarded_wrong_bus(String subscriber_boarded_wrong_bus) {
		this.subscriber_boarded_wrong_bus = subscriber_boarded_wrong_bus;
	}

	public String getSubscriber_boarded_wrong_trip() {
		return subscriber_boarded_wrong_trip;
	}

	public void setSubscriber_boarded_wrong_trip(String subscriber_boarded_wrong_trip) {
		this.subscriber_boarded_wrong_trip = subscriber_boarded_wrong_trip;
	}

}
