package com.main.sts.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "vehicles")
public class Vehicles implements Comparable<Vehicles> {

	@Id
	@GeneratedValue
	private int vehicle_id;

	private String vehicle_licence_number;
	private String vehicle_capacity;
	private String vehicle_make_model;
	private String vehicle_type;

	private int vehicle_allotted, driverId;

	@OneToOne(targetEntity = Drivers.class)
	@JoinColumn(name = "driverId", referencedColumnName = "id", insertable = false, updatable = false)
	public Drivers driver;

	public int getVehicle_id() {
		return vehicle_id;
	}

	public String getVehicle_licence_number() {
		return vehicle_licence_number;
	}

	public String getVehicle_capacity() {
		return vehicle_capacity;
	}

	public String getVehicle_make_model() {
		return vehicle_make_model;
	}

	public void setVehicle_id(int vehicle_id) {
		this.vehicle_id = vehicle_id;
	}

	public void setVehicle_licence_number(String vehicle_licence_number) {
		this.vehicle_licence_number = vehicle_licence_number;
	}

	public void setVehicle_capacity(String vehicle_capacity) {
		this.vehicle_capacity = vehicle_capacity;
	}

	public void setVehicle_make_model(String vehicle_make_model) {
		this.vehicle_make_model = vehicle_make_model;
	}

	public int getVehicle_allotted() {
		return vehicle_allotted;
	}

	public void setVehicle_allotted(int vehicle_allotted) {
		this.vehicle_allotted = vehicle_allotted;
	}

	public String getVehicle_type() {
		return vehicle_type;
	}

	public void setVehicle_type(String vehicle_type) {
		this.vehicle_type = vehicle_type;
	}

	@Override
	public int compareTo(Vehicles o) {
		return vehicle_licence_number.compareTo(o.getVehicle_licence_number());
	}

	public Drivers getDriver() {
		return driver;
	}

	public void setDriver(Drivers driver) {
		this.driver = driver;
	}

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	@Override
	public String toString() {
		return "Buses [vehicle_id=" + vehicle_id + ", vehicle_licence_number="
				+ vehicle_licence_number + ", vehicle_capacity="
				+ vehicle_capacity + ", vehicle_make_model="
				+ vehicle_make_model + ", vehicle_allotted=" + vehicle_allotted
				+ ", driverId=" + driverId + ", driver=" + driver + "]";
	}

}
