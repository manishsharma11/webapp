package com.main.sts.entities;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "user_roles")
public class Users_Roles {

	@Id
	private int user_id;

	private int role_id;

	public int getUser_id() {
		return user_id;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	@Override
	public String toString() {
		return "Users_Roles [user_id=" + user_id + ", role_id=" + role_id + "]";
	}

}
