package com.main.sts.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="transport")
public class Transport {

	@Id
	@GeneratedValue
	private Integer id;
	private String subscriber_type;
	private int subscriber_id;
	private String transport_type;
	private Integer bus_id;
	private Integer route_id;
	private Integer trip_id;
	private Integer stop_id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSubscriber_type() {
		return subscriber_type;
	}
	public void setSubscriber_type(String subscriber_type) {
		this.subscriber_type = subscriber_type;
	}
	public int getSubscriber_id() {
		return subscriber_id;
	}
	public void setSubscriber_id(int subscriber_id) {
		this.subscriber_id = subscriber_id;
	}
	public Integer getBus_id() {
		return bus_id;
	}
	public void setBus_id(Integer bus_id) {
		this.bus_id = bus_id;
	}
	public Integer getRoute_id() {
		return route_id;
	}
	public void setRoute_id(Integer route_id) {
		this.route_id = route_id;
	}
	public Integer getTrip_id() {
		return trip_id;
	}
	public void setTrip_id(Integer trip_id) {
		this.trip_id = trip_id;
	}
	public Integer getStop_id() {
		return stop_id;
	}
	public void setStop_id(Integer stop_id) {
		this.stop_id = stop_id;
	}
	public String getTransport_type() {
		return transport_type;
	}
	public void setTransport_type(String transport_type) {
		this.transport_type = transport_type;
	}
	@Override
	public String toString() {
		return "Transport [id=" + id + ", subscriber_type=" + subscriber_type + ", subscriber_id=" + subscriber_id
				+ ", transport_type=" + transport_type + ", bus_id=" + bus_id + ", route_id=" + route_id + ", trip_id="
				+ trip_id + ", stop_id=" + stop_id + "]";
	}

	
}
