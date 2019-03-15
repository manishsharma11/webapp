package com.main.sts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.sts.dao.sql.AlertsDao;
import com.main.sts.entities.Alerts;

@Service
public class AlertService {

	@Autowired
	private AlertsDao alertdao;
	
	public void insertAlert(Alerts alerts)
	{
		alertdao.insertAlerts(alerts);
	}
	public void updateAlert(Alerts alerts)
	{
		alertdao.updateAlerts(alerts);
	}
	public Alerts getAlerts(int subscriber_id, String subscriber_type, String alert_type) {
		return alertdao.getAlerts(subscriber_id, subscriber_type, alert_type);
	}
}
