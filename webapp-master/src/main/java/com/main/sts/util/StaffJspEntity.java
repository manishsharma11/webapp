package com.main.sts.util;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StaffJspEntity implements Comparable<StaffJspEntity>
{
	
	@Id
	private int id;
	
	private String full_name,rfid_number,staff_id,mobile_number;
	private String bus_licence_number_home;
	private String stop_name_home;
	private String bus_licence_number_school;
	private String stop_name_school;
	public int getId() {
		return id;
	}
	public String getFull_name() {
		return full_name;
	}
	public String getRfid_number() {
		return rfid_number;
	}
	public String getStaff_id() {
		return staff_id;
	}
	public String getMobile_number() {
		return mobile_number;
	}
	public String getBus_licence_number_home() {
		return bus_licence_number_home;
	}
	public String getStop_name_home() {
		return stop_name_home;
	}
	public String getBus_licence_number_school() {
		return bus_licence_number_school;
	}
	public String getStop_name_school() {
		return stop_name_school;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public void setRfid_number(String rfid_number) {
		this.rfid_number = rfid_number;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}
	public void setBus_licence_number_home(String bus_licence_number_home) {
		this.bus_licence_number_home = bus_licence_number_home;
	}
	public void setStop_name_home(String stop_name_home) {
		this.stop_name_home = stop_name_home;
	}
	public void setBus_licence_number_school(String bus_licence_number_school) {
		this.bus_licence_number_school = bus_licence_number_school;
	}
	public void setStop_name_school(String stop_name_school) {
		this.stop_name_school = stop_name_school;
	}
	@Override
	public String toString() {
		return "StaffJspEntity [id=" + id + ", full_name=" + full_name + ", rfid_number=" + rfid_number + ", staff_id="
				+ staff_id + ", mobile_number=" + mobile_number + ", bus_licence_number_home="
				+ bus_licence_number_home + ", stop_name_home=" + stop_name_home + ", bus_licence_number_school="
				+ bus_licence_number_school + ", stop_name_school=" + stop_name_school + "]";
	}
	@Override
	public int compareTo(StaffJspEntity o) {
		// TODO Auto-generated method stub
		return this.full_name.compareTo(o.getFull_name());
	}

}
