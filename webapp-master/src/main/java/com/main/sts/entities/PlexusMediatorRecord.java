package com.main.sts.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="plexus_mediator_record")
public class PlexusMediatorRecord
{
	
	@Id
	@GeneratedValue
	private int id;
	private int student_id;
	private String person_name,message,date,time,mobile_number,notification_id,status,device_type;
	public int getId() {
		return id;
	}
	public int getStudent_id() {
		return student_id;
	}
	public String getPerson_name() {
		return person_name;
	}
	public String getMessage() {
		return message;
	}
	public String getDate() {
		return date;
	}
	public String getTime() {
		return time;
	}
	public String getMobile_number() {
		return mobile_number;
	}
	public String getNotification_id() {
		return notification_id;
	}
	public String getStatus() {
		return status;
	}
	public String getDevice_type() {
		return device_type;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}
	public void setNotification_id(String notification_id) {
		this.notification_id = notification_id;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}
	@Override
	public String toString() {
		return "PlexusMediatorRecord [id=" + id + ", student_id=" + student_id + ", person_name=" + person_name
				+ ", message=" + message + ", date=" + date + ", time=" + time + ", mobile_number=" + mobile_number
				+ ", notification_id=" + notification_id + ", status=" + status + ", device_type=" + device_type + "]";
	}
	

}
