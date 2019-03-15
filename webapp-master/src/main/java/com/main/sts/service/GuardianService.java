package com.main.sts.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.sts.dao.sql.GuardianDao;
import com.main.sts.entities.Address;
import com.main.sts.entities.Alerts;
import com.main.sts.entities.Guardians;
import com.main.sts.util.MakeObjects;

@Service
public class GuardianService
{

	@Autowired
	private GuardianDao guardianDao;
	@Autowired
	private MakeObjects makeObjects;
	@Autowired
	private AddressService addressService;
	@Autowired
	private AlertService alertService;
	
	private final static String subscriber_type="guardian";
	private static final String alert_sms="sms";
	private static final String alert_email="email";
	
	public List<Guardians> getAllGuardainByStudentId(int s_id) {
		 
		return guardianDao.getGuardians(s_id);
	}

	public void add(HttpServletRequest request,int s_id) 
	{
	 try{
		Guardians guardians=makeObjects.getGuardian(request, s_id);
		
		Address address=makeObjects.getAddress(request, s_id,subscriber_type);
		//System.out.println("add "+address);
		addressService.insertAddress(address);
		//System.out.println("address "+address);
		Alerts sms_alerts=makeObjects.getAlerts(request, s_id, subscriber_type,alert_sms);
		Alerts email_alerts=makeObjects.getAlerts(request, s_id, subscriber_type,alert_email);
		alertService.insertAlert(sms_alerts);
		alertService.insertAlert(email_alerts);
		guardians.setEmail_alert(email_alerts);
		guardians.setEmail_alert_id(email_alerts.getId());
		guardians.setSms_alert(sms_alerts);
		guardians.setSms_alert_id(sms_alerts.getId());
		guardianDao.addGuardian(guardians);
		//System.out.println("guardian "+guardians);
		//updateGuardian(guardians.getId());
		address.setSubscriber_id(guardians.getId());
		sms_alerts.setSubscriber_id(guardians.getId());
		email_alerts.setSubscriber_id(guardians.getId());
		addressService.updateAddress(address);
		alertService.updateAlert(email_alerts);
		alertService.updateAlert(sms_alerts);
	 }
	 catch(Exception e)
	 {
		 e.printStackTrace();
	 }
		
	}

	public Guardians getGuardianById(int id) {
		 
		return guardianDao.getGuardianById(id);
	}
	
	public void updateGuardian(int gid)
	{
		Guardians guardians=getGuardianById(gid);
		 guardianDao.updateGuardian(guardians);
	}

	public void updateGuardianValue(HttpServletRequest request) {
		 
		Guardians guardians=getGuardianById(Integer.parseInt(request.getParameter("id")));
		Guardians new_guardian=makeObjects.getGuardian(request, guardians.getStudent_id());
		new_guardian.setId(guardians.getId());
	
		
		Address old=addressService.getAddress(guardians.getId(), subscriber_type);
		Address address=makeObjects.getAddress(request, guardians.getId(), subscriber_type);
		address.setId(old.getId());
		
		Alerts sms=alertService.getAlerts(guardians.getId(), subscriber_type, alert_sms);
		Alerts n_sms=makeObjects.getAlerts(request, guardians.getId(), subscriber_type, alert_sms);
		n_sms.setId(sms.getId());
		
		Alerts email=alertService.getAlerts(guardians.getId(), subscriber_type, alert_email);
		Alerts n_email=makeObjects.getAlerts(request, guardians.getId(), subscriber_type, alert_email);
		n_email.setId(email.getId());
		
		addressService.updateAddress(address);
		alertService.updateAlert(n_email);
		alertService.updateAlert(n_sms);
		new_guardian.setEmail_alert(n_email);
		new_guardian.setEmail_alert_id(n_email.getId());
		new_guardian.setSms_alert(n_sms);
		new_guardian.setSms_alert_id(n_sms.getId());
		guardianDao.updateGuardian(new_guardian);
		
		
	}

	public void deleteStudent(int parseInt) {
		 Guardians guardians=getGuardianById(parseInt);
		 Address address=addressService.getAddress(parseInt, subscriber_type);
		 Alerts sms=alertService.getAlerts(parseInt, subscriber_type, alert_sms);
		 Alerts email=alertService.getAlerts(parseInt, subscriber_type, alert_email);
		 guardianDao.deleteEntity(guardians);
		 guardianDao.deleteEntity(email);
		 guardianDao.deleteEntity(sms);
		 guardianDao.deleteEntity(address);
		 
		
	}

	 
	
}
