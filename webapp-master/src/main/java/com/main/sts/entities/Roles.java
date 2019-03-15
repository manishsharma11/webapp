package com.main.sts.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Roles {

	@Id
	@GeneratedValue
	private Integer role_id;

	private String role_name;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_roles", joinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "role_id") },
	inverseJoinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "user_id") })
	private Set<Users> userRoles;

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public Set<Users> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<Users> userRoles) {
		this.userRoles = userRoles;
	}

	
}
