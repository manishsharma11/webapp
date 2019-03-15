package com.main.sts.util;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StudentsJspEntity {

	@Id
	private Integer id;
	private String first_name;
	private String last_name;
	private String gr_number;
	private String rfid_number;
	private String bus_licence_number_home;
	private String stop_name_home;
	private String bus_licence_number_school;
	private String stop_name_school;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getGr_number() {
		return gr_number;
	}
	public void setGr_number(String gr_number) {
		this.gr_number = gr_number;
	}
	public String getRfid_number() {
		return rfid_number;
	}
	public void setRfid_number(String rfid_number) {
		this.rfid_number = rfid_number;
	}
	public String getBus_licence_number_home() {
		return bus_licence_number_home;
	}
	public void setBus_licence_number_home(String bus_licence_number_home) {
		this.bus_licence_number_home = bus_licence_number_home;
	}
	public String getStop_name_home() {
		return stop_name_home;
	}
	public void setStop_name_home(String stop_name_home) {
		this.stop_name_home = stop_name_home;
	}
	public String getBus_licence_number_school() {
		return bus_licence_number_school;
	}
	public void setBus_licence_number_school(String bus_licence_number_school) {
		this.bus_licence_number_school = bus_licence_number_school;
	}
	public String getStop_name_school() {
		return stop_name_school;
	}
	public void setStop_name_school(String stop_name_school) {
		this.stop_name_school = stop_name_school;
	}
	@Override
	public String toString() {
		return "StudentsJspEntity [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", gr_number=" + gr_number + ", rfid_number=" + rfid_number + ", bus_licence_number_home="
				+ bus_licence_number_home + ", stop_name_home=" + stop_name_home + ", bus_licence_number_school="
				+ bus_licence_number_school + ", stop_name_school=" + stop_name_school + "]";
	}
	
	
}
