package com.main.sts.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "daily_driver_data")
public class DailyDriverData {
	@Id
	@GeneratedValue
	private int id;
	private int in_driver_id;
	private String in_driver_name;
	private String out_driver_name;
	private int out_driver_id;
	private String in_time;
	private String out_time;
	private String date;
	private int trip_id;
	private Double in_latitude;
	private Double in_longitude;
	private Double out_latitude;
	private Double out_longitude;
	private boolean is_session_closed_by_system = false;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIn_driver_id() {
		return in_driver_id;
	}

	public void setIn_driver_id(int in_driver_id) {
		this.in_driver_id = in_driver_id;
	}

	public int getOut_driver_id() {
		return out_driver_id;
	}

	public void setOut_driver_id(int out_driver_id) {
		this.out_driver_id = out_driver_id;
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

	public boolean isIs_session_closed_by_system() {
		return is_session_closed_by_system;
	}

	public void setIs_session_closed_by_system(boolean is_session_closed_by_system) {
		this.is_session_closed_by_system = is_session_closed_by_system;
	}

	public String getIn_driver_name() {
		return in_driver_name;
	}

	public void setIn_driver_name(String in_driver_name) {
		this.in_driver_name = in_driver_name;
	}

	public String getOut_driver_name() {
		return out_driver_name;
	}

	public void setOut_driver_name(String out_driver_name) {
		this.out_driver_name = out_driver_name;
	}

	@Override
	public String toString() {
		return "DailyDriverData [id=" + id + ", in_driver_id=" + in_driver_id + ", in_driver_name=" + in_driver_name + ", out_driver_name=" + out_driver_name
				+ ", out_driver_id=" + out_driver_id + ", in_time=" + in_time + ", out_time=" + out_time + ", date=" + date + ", trip_id=" + trip_id
				+ ", in_latitude=" + in_latitude + ", in_longitude=" + in_longitude + ", out_latitude=" + out_latitude + ", out_longitude=" + out_longitude
				+ ", is_session_closed_by_system=" + is_session_closed_by_system + "]";
	}

	

}
