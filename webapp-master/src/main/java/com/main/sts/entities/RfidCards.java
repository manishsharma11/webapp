package com.main.sts.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rfid_cards")
public class RfidCards {

	@Id
	@GeneratedValue
	private int id;
	private String rfid_number;
	private String type;
	private int active; // 0 - available 1- not available
	private int available; // 0 - available 1- not available
	private int allocated_to;
	private String allocated_time;
	private String allocated_person_name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRfid_number() {
		return rfid_number;
	}

	public void setRfid_number(String rfid_number) {
		this.rfid_number = rfid_number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public int getAllocated_to() {
		return allocated_to;
	}

	public void setAllocated_to(int allocated_to) {
		this.allocated_to = allocated_to;
	}

	public String getAllocated_time() {
		return allocated_time;
	}

	public void setAllocated_time(String allocated_time) {
		this.allocated_time = allocated_time;
	}

	public String getAllocated_person_name() {
		return allocated_person_name;
	}

	public void setAllocated_person_name(String allocated_person_name) {
		this.allocated_person_name = allocated_person_name;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "RfidCards [id=" + id + ", rfid_number=" + rfid_number + ", type=" + type + ", active=" + active
				+ ", available=" + available + ", allocated_to=" + allocated_to + ", allocated_time=" + allocated_time
				+ ", allocated_person_name=" + allocated_person_name + "]";
	}

}
