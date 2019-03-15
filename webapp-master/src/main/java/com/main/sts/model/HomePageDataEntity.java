package com.main.sts.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HomePageDataEntity {

	@Bean
	public HomePageDataEntity homePageDataEntity(){
		
		return new HomePageDataEntity();
	}
	private String bus_licence;
	private String bus_id;
	private String current_stop;
	private String arrived_time;
	private int no_of_students;
	private String driver_id;
	private String driver_name;
	private String status;
	public String getBus_licence() {
		return bus_licence;
	}
	public void setBus_licence(String bus_licence) {
		this.bus_licence = bus_licence;
	}
	public String getBus_id() {
		return bus_id;
	}
	public void setBus_id(String bus_id) {
		this.bus_id = bus_id;
	}
	public String getCurrent_stop() {
		return current_stop;
	}
	public void setCurrent_stop(String current_stop) {
		this.current_stop = current_stop;
	}
	public String getArrived_time() {
		return arrived_time;
	}
	public void setArrived_time(String arrived_time) {
		this.arrived_time = arrived_time;
	}
	public int getNo_of_students() {
		return no_of_students;
	}
	public void setNo_of_students(int no_of_students) {
		this.no_of_students = no_of_students;
	}
	public String getDriver_id() {
		return driver_id;
	}
	public void setDriver_id(String driver_id) {
		this.driver_id = driver_id;
	}
	public String getDriver_name() {
		return driver_name;
	}
	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "HomePageDataEntity [bus_licence=" + bus_licence + ", bus_id="
				+ bus_id + ", current_stop=" + current_stop + ", arrived_time="
				+ arrived_time + ", no_of_students=" + no_of_students
				+ ", driver_id=" + driver_id + ", driver_name=" + driver_name
				+ ", status=" + status + "]";
	}
	
}
