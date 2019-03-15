package com.main.sts.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name="guardians")
public class Guardians implements Comparable<Guardians> {
	
	
	@Id
	@GeneratedValue
	int id;
	String first_name,last_name,relation,mobile_number,email;
	int student_id;
	int sms_alert_id,email_alert_id;
	
	
	@OneToOne(targetEntity=Alerts.class)
	@JoinColumn(name="sms_alert_id",referencedColumnName="id",insertable=false,updatable=false)
	public Alerts sms_alert;
	@OneToOne(targetEntity=Alerts.class)
	@JoinColumn(name="email_alert_id",referencedColumnName="id",insertable=false,updatable=false)
	public Alerts email_alert;
	public int getId() {
		return id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public String getRelation() {
		return relation;
	}
	public String getMobile_number() {
		return mobile_number;
	}
	public String getEmail() {
		return email;
	}
	public int getStudent_id() {
		return student_id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	@Override
	public String toString() {
		return "Guardians [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", relation="
				+ relation + ", mobile_number=" + mobile_number + ", email=" + email + ", student_id=" + student_id
				+ "]";
	}
	@Override
	public int compareTo(Guardians o) {
		 
		return (this.first_name.compareTo(o.first_name));
	}
	public int getSms_alert_id() {
		return sms_alert_id;
	}
	public int getEmail_alert_id() {
		return email_alert_id;
	}
	public Alerts getSms_alert() {
		return sms_alert;
	}
	public Alerts getEmail_alert() {
		return email_alert;
	}
	public void setSms_alert_id(int sms_alert_id) {
		this.sms_alert_id = sms_alert_id;
	}
	public void setEmail_alert_id(int email_alert_id) {
		this.email_alert_id = email_alert_id;
	}
	public void setSms_alert(Alerts sms_alert) {
		this.sms_alert = sms_alert;
	}
	public void setEmail_alert(Alerts email_alert) {
		this.email_alert = email_alert;
	}

}
