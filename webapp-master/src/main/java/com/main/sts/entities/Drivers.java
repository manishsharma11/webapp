package com.main.sts.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "drivers")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "drivers")
public class Drivers  {

	@Id
	@GeneratedValue
	private Integer id;
	private String driver_id;
	private String driver_name;
	private String contact_number;
	private String rfid_number;
	private Integer available; // 0 - available , 1 - not available
	private Integer active;	   // 0 - available , 1 - not available
	private String street;
	private String city;
	private String state;
	private String zip;
	private String country;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getContact_number() {
		return contact_number;
	}
	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}
	public String getRfid_number() {
		return rfid_number;
	}
	public void setRfid_number(String rfid_number) {
		this.rfid_number = rfid_number;
	}
	public Integer getAvailable() {
		return available;
	}
	public void setAvailable(Integer available) {
		this.available = available;
	}
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public String toString() {
		return "Drivers [id=" + id + ", driver_id=" + driver_id + ", driver_name=" + driver_name + ", contact_number="
				+ contact_number + ", rfid_number=" + rfid_number + ", available=" + available + ", active=" + active
				+ ", street=" + street + ", city=" + city + ", state=" + state + ", zip=" + zip + ", country="
				+ country + "]";
	}
	
	
}
