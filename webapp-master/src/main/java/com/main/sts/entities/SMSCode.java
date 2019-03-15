package com.main.sts.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sms_codes")
public class SMSCode implements Comparable<SMSCode>, Serializable {

	@Id
	@GeneratedValue
	private int id;
	@Column(name = "user_id")
	private int commuter_id;
	private int code;
	private int status;
	private Timestamp created_at;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCommuter_id() {
		return commuter_id;
	}

	public void setCommuter_id(int commuter_id) {
		this.commuter_id = commuter_id;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	@Override
	public String toString() {
		return "SmsCodes [id=" + id + ", commuter_id=" + commuter_id + ", code=" + code
				+ ", status=" + status + ", created_at=" + created_at + "]";
	}

	@Override
	public int compareTo(SMSCode o) {
		return ((Integer) id).compareTo(o.getId());
	}

}
