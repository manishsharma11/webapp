package com.main.sts.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="session")
public class Session 
{

	@Id
	@GeneratedValue
	private int id;
	private String session_name,session_type;
	private int starting_time_hours,end_time_hours,starting_time_minutes,end_time_minutes;
	public int getId() {
		return id;
	}
	public String getSession_name() {
		return session_name;
	}
	public String getSession_type() {
		return session_type;
	}
	 
	public void setId(int id) {
		this.id = id;
	}
	public void setSession_name(String session_name) {
		this.session_name = session_name;
	}
	public void setSession_type(String session_type) {
		this.session_type = session_type;
	}
	public int getStarting_time_hours() {
		return starting_time_hours;
	}
	public int getEnd_time_hours() {
		return end_time_hours;
	}
	public int getStarting_time_minutes() {
		return starting_time_minutes;
	}
	public int getEnd_time_minutes() {
		return end_time_minutes;
	}
	public void setStarting_time_hours(int starting_time_hours) {
		this.starting_time_hours = starting_time_hours;
	}
	public void setEnd_time_hours(int end_time_hours) {
		this.end_time_hours = end_time_hours;
	}
	public void setStarting_time_minutes(int starting_time_minutes) {
		this.starting_time_minutes = starting_time_minutes;
	}
	public void setEnd_time_minutes(int end_time_minutes) {
		this.end_time_minutes = end_time_minutes;
	}
	@Override
	public String toString() {
		return "Session [id=" + id + ", session_name=" + session_name + ", session_type=" + session_type
				+ ", starting_time_hours=" + starting_time_hours + ", end_time_hours=" + end_time_hours
				+ ", starting_time_minutes=" + starting_time_minutes + ", end_time_minutes=" + end_time_minutes + "]";
	}
	 
}
