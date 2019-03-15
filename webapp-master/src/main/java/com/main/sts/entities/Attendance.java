package com.main.sts.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="attendance")
public class Attendance {

	
	@Id
	@GeneratedValue
	private int id;
	private int student_id;
	private String date,in_time,out_time;
	private int session_id;
	private String reason;
	public int getId() {
		return id;
	}
	public int getStudent_id() {
		return student_id;
	}
	public String getDate() {
		return date;
	}
	public String getIn_time() {
		return in_time;
	}
	public String getOut_time() {
		return out_time;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setIn_time(String in_time) {
		this.in_time = in_time;
	}
	public void setOut_time(String out_time) {
		this.out_time = out_time;
	}
	@Override
	public String toString() {
		return "Attendance [id=" + id + ", student_id=" + student_id + ", date=" + date + ", in_time=" + in_time
				+ ", out_time=" + out_time + ", session_id=" + session_id + ", reason=" + reason + "]";
	}
	public int getSession_id() {
		return session_id;
	}
	public void setSession_id(int session_id) {
		this.session_id = session_id;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
}
