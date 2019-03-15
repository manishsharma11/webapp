package com.main.sts.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "buses")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "buses")
public class Buses implements Comparable<Buses> {

//	@Id
//	@GeneratedValue
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "buses_id_seq_gen")
  @SequenceGenerator(name = "buses_id_seq_gen", sequenceName = "buses_id_seq")
	private int bus_id;

	private String bus_licence_number, bus_capacity, bus_make_model;

	private int bus_allotted,driverId;

	@OneToOne(targetEntity = Drivers.class)
	@JoinColumn(name = "driverId", referencedColumnName = "id", insertable = false, updatable = false)
	public Drivers driver;

	public int getBus_id() {
		return bus_id;
	}

	public String getBus_licence_number() {
		return bus_licence_number;
	}

	public String getBus_capacity() {
		return bus_capacity;
	}

	public String getBus_make_model() {
		return bus_make_model;
	}

	public void setBus_id(int bus_id) {
		this.bus_id = bus_id;
	}

	public void setBus_licence_number(String bus_licence_number) {
		this.bus_licence_number = bus_licence_number;
	}

	public void setBus_capacity(String bus_capacity) {
		this.bus_capacity = bus_capacity;
	}

	public void setBus_make_model(String bus_make_model) {
		this.bus_make_model = bus_make_model;
	}

	public int getBus_allotted() {
		return bus_allotted;
	}

	public void setBus_allotted(int bus_allotted) {
		this.bus_allotted = bus_allotted;
	}

	 
	@Override
	public int compareTo(Buses o) {
		// TODO Auto-generated method stub
		return bus_licence_number.compareTo(o.getBus_licence_number());
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
		return "Buses [bus_id=" + bus_id + ", bus_licence_number=" + bus_licence_number + ", bus_capacity="
				+ bus_capacity + ", bus_make_model=" + bus_make_model + ", bus_allotted=" + bus_allotted
				+ ", driverId=" + driverId + ", driver=" + driver + "]";
	}

}
