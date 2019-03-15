package com.main.sts.entities;



import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users {

	@Id
	@GeneratedValue
	private Integer user_id;
	private String full_name;
	private String user_name;

	private String password;

	private String access_status;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "user_roles", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "role_id") })
	private Roles role;

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public String getAccess_status() {
		return access_status;
	}

	public void setAccess_status(String access_status) {
		this.access_status = access_status;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	@Override
	public String toString() {
		return "Users [user_id=" + user_id + ", full_name=" + full_name
				+ ", user_name=" + user_name + ", password=" + password
				+ ", access_status=" + access_status + ", role=" + role + "]";
	}
	
	 
	

}
