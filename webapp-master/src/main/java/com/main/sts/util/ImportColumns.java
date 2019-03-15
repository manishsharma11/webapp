package com.main.sts.util;

import org.springframework.stereotype.Component;

@Component
public class ImportColumns {

	private int first_name,last_name,gender,gr_number,parent_first_name,parent_last_name,parent_email,parent_mobile,street,city;
	
	private int postal,country,state,bus_from_home,route_from_home,pickup_time,stop_from_home,bus_from_school,route_from_school;
	
	private int dropoff_time,stop_from_school,student_grade;

	public int getFirst_name() {
		return first_name;
	}

	public int getLast_name() {
		return last_name;
	}

	public int getGender() {
		return gender;
	}

	public int getGr_number() {
		return gr_number;
	}

	public int getParent_first_name() {
		return parent_first_name;
	}

	public int getParent_last_name() {
		return parent_last_name;
	}

	public int getParent_email() {
		return parent_email;
	}

	public int getParent_mobile() {
		return parent_mobile;
	}

	public int getStreet() {
		return street;
	}

	public int getCity() {
		return city;
	}

	public int getPostal() {
		return postal;
	}

	public int getCountry() {
		return country;
	}

	public int getState() {
		return state;
	}

	public int getBus_from_home() {
		return bus_from_home;
	}

	public int getRoute_from_home() {
		return route_from_home;
	}

	public int getPickup_time() {
		return pickup_time;
	}

	public int getStop_from_home() {
		return stop_from_home;
	}

	public int getBus_from_school() {
		return bus_from_school;
	}

	public int getRoute_from_school() {
		return route_from_school;
	}

	public int getDropoff_time() {
		return dropoff_time;
	}

	public int getStop_from_school() {
		return stop_from_school;
	}

	public void setFirst_name(int first_name) {
		this.first_name = first_name;
	}

	public void setLast_name(int last_name) {
		this.last_name = last_name;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public void setGr_number(int gr_number) {
		this.gr_number = gr_number;
	}

	public void setParent_first_name(int parent_first_name) {
		this.parent_first_name = parent_first_name;
	}

	public void setParent_last_name(int parent_last_name) {
		this.parent_last_name = parent_last_name;
	}

	public void setParent_email(int parent_email) {
		this.parent_email = parent_email;
	}

	public void setParent_mobile(int parent_mobile) {
		this.parent_mobile = parent_mobile;
	}

	public void setStreet(int street) {
		this.street = street;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public void setPostal(int postal) {
		this.postal = postal;
	}

	public void setCountry(int country) {
		this.country = country;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void setBus_from_home(int bus_from_home) {
		this.bus_from_home = bus_from_home;
	}

	public void setRoute_from_home(int route_from_home) {
		this.route_from_home = route_from_home;
	}

	public void setPickup_time(int pickup_time) {
		this.pickup_time = pickup_time;
	}

	public void setStop_from_home(int stop_from_home) {
		this.stop_from_home = stop_from_home;
	}

	public void setBus_from_school(int bus_from_school) {
		this.bus_from_school = bus_from_school;
	}

	public void setRoute_from_school(int route_from_school) {
		this.route_from_school = route_from_school;
	}

	public void setDropoff_time(int dropoff_time) {
		this.dropoff_time = dropoff_time;
	}

	public void setStop_from_school(int stop_from_school) {
		this.stop_from_school = stop_from_school;
	}

	@Override
	public String toString() {
		return "ImportColumns [first_name=" + first_name + ", last_name=" + last_name + ", gender=" + gender
				+ ", gr_number=" + gr_number + ", parent_first_name=" + parent_first_name + ", parent_last_name="
				+ parent_last_name + ", parent_email=" + parent_email + ", parent_mobile=" + parent_mobile
				+ ", street=" + street + ", city=" + city + ", postal=" + postal + ", country=" + country + ", state="
				+ state + ", bus_from_home=" + bus_from_home + ", route_from_home=" + route_from_home
				+ ", pickup_time=" + pickup_time + ", stop_from_home=" + stop_from_home + ", bus_from_school="
				+ bus_from_school + ", route_from_school=" + route_from_school + ", dropoff_time=" + dropoff_time
				+ ", stop_from_school=" + stop_from_school + ", student_grade=" + student_grade + "]";
	}

	public int getStudent_grade() {
		return student_grade;
	}

	public void setStudent_grade(int student_grade) {
		this.student_grade = student_grade;
	}
	
	
	 
	
}
