package com.main.sts.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="alerts")
public class Alerts {

	@Id
	@GeneratedValue
	private Integer id;
	private String subscriber_type;
	private String alert_type;
	private String all_alerts;
	private String no_show;
	private String late;
	private String irregularities;
	private String regularities;
	private Integer subscriber_id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSubscriber_type() {
		return subscriber_type;
	}
	public void setSubscriber_type(String subscriber_type) {
		this.subscriber_type = subscriber_type;
	}
	public String getAlert_type() {
		return alert_type;
	}
	public void setAlert_type(String alert_type) {
		this.alert_type = alert_type;
	}
	public String getAll_alerts() {
		return all_alerts;
	}
	public void setAll_alerts(String all_alerts) {
		this.all_alerts = all_alerts;
	}
	public String getNo_show() {
		return no_show;
	}
	public void setNo_show(String no_show) {
		this.no_show = no_show;
	}
	public String getLate() {
		return late;
	}
	public void setLate(String late) {
		this.late = late;
	}
	public String getIrregularities() {
		return irregularities;
	}
	public void setIrregularities(String irregularities) {
		this.irregularities = irregularities;
	}
	public String getRegularities() {
		return regularities;
	}
	public void setRegularities(String regularities) {
		this.regularities = regularities;
	}
	public Integer getSubscriber_id() {
		return subscriber_id;
	}
	public void setSubscriber_id(Integer subscriber_id) {
		this.subscriber_id = subscriber_id;
	}
	@Override
	public String toString() {
		return "Alerts [id=" + id + ", subscriber_type=" + subscriber_type + ", alert_type=" + alert_type
				+ ", all_alerts=" + all_alerts + ", no_show=" + no_show + ", late=" + late + ", irregularities="
				+ irregularities + ", regularities=" + regularities + ", subscriber_id=" + subscriber_id + "]";
	}

	

}
