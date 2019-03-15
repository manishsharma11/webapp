package com.main.sts.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name="address")
public class Address {

	@Id
	@GeneratedValue
	private int id;
	private int subscriber_id;
	private String subscriber_type,street,city,state,postal,country;
	public int getId() {
		return id;
	}
	public int getSubscriber_id() {
		return subscriber_id;
	}
	public String getSubscriber_type() {
		return subscriber_type;
	}
	public String getStreet() {
		return street;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getPostal() {
		return postal;
	}
	public String getCountry() {
		return country;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setSubscriber_id(int subscriber_id) {
		this.subscriber_id = subscriber_id;
	}
	public void setSubscriber_type(String subscriber_type) {
		this.subscriber_type = subscriber_type;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setPostal(String postal) {
		this.postal = postal;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", subscriber_id=" + subscriber_id + ", subscriber_type=" + subscriber_type
				+ ", street=" + street + ", city=" + city + ", state=" + state + ", postal=" + postal + ", country="
				+ country + "]";
	}
}
