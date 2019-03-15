package com.main.sts.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "staff")
public class Staff implements Comparable<Staff> {

	@Id
	@GeneratedValue
	private int id;
 
	private String gender,email,staff_id,full_name,rfid_number,mobile_number;
	
 
	public int getId() {
		return id;
	}

	public String getGender() {
		return gender;
	}

	public String getEmail() {
		return email;
	}

	public String getStaff_id() {
		return staff_id;
	}

	public String getFull_name() {
		return full_name;
	}

	 

	public String getRfid_number() {
		return rfid_number;
	}

	public String getMobile_number() {
		return mobile_number;
	}

	 

	public void setId(int id) {
		this.id = id;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	 

	public void setRfid_number(String rfid_number) {
		this.rfid_number = rfid_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}
 

	@Override
	public int compareTo(Staff o) {
		 
		return this.full_name.compareTo(o.getFull_name());
	}

	@Override
	public String toString() {
		return "Staff [id=" + id + ", gender=" + gender + ", email=" + email + ", staff_id=" + staff_id
				+ ", full_name=" + full_name + ", rfid_number=" + rfid_number + ", mobile_number=" + mobile_number
				+ "]";
	}
 
}
