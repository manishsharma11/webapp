package com.main.sts.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Students {

	@Id
	@GeneratedValue
	private Integer id;
	private String first_name;
	private String last_name;
	private String gr_number;
	private String rfid_number;
	private String gender;
	private String student_grade;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getGr_number() {
		return gr_number;
	}

	public void setGr_number(String gr_number) {
		this.gr_number = gr_number;
	}

	public String getRfid_number() {
		return rfid_number;
	}

	public void setRfid_number(String rfid_number) {
		this.rfid_number = rfid_number;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStudent_grade() {
		return student_grade;
	}

	public void setStudent_grade(String student_grade) {
		this.student_grade = student_grade;
	}

	@Override
	public String toString() {
		return "Students [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", gr_number="
				+ gr_number + ", rfid_number=" + rfid_number + ", gender=" + gender + ", student_grade="
				+ student_grade + "]";
	}

	 

	 

}
