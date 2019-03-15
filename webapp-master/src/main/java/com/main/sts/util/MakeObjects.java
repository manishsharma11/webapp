package com.main.sts.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.main.sts.entities.Address;
import com.main.sts.entities.Alerts;
import com.main.sts.entities.Guardians;
import com.main.sts.entities.Parents;
import com.main.sts.entities.Staff;
import com.main.sts.entities.Students;
import com.main.sts.entities.Transport;
import com.main.sts.service.StaffService;

@Component
public class MakeObjects {

	@Autowired
	private StaffService staffservice;
	public Transport getTransport(HttpServletRequest request, int id, String subscriber_type, String transport_type) {

		Transport transport = new Transport();
		transport.setTransport_type(transport_type);
		transport.setSubscriber_id(id);
		transport.setSubscriber_type(subscriber_type);
		// System.out.println("-->" +
		// request.getParameter("bus_id_number_fromhome").length());
		if (transport_type.equals(SystemConstants.PICKUP)) {
			if (request.getParameter("bus_id_number_fromhome").length() != 0) {
				transport.setBus_id(Integer.parseInt(request.getParameter("bus_id_number_fromhome")));
				transport.setRoute_id(Integer.parseInt(request.getParameter("route_id_fromhome")));
				transport.setStop_id(Integer.parseInt(request.getParameter("stop_id_fromhome")));
				transport.setTrip_id(Integer.parseInt(request.getParameter("trip_id_fromhome")));

			} else {
				transport.setBus_id(0);
				transport.setRoute_id(0);
				transport.setStop_id(0);
				transport.setTrip_id(0);
			}

		}
		if (transport_type.equals(SystemConstants.DROPOFF)) {
			if (request.getParameter("bus_id_number_fromschool").length() != 0) {
				transport.setBus_id(Integer.parseInt(request.getParameter("bus_id_number_fromschool")));
				transport.setRoute_id(Integer.parseInt(request.getParameter("route_id_fromschool")));
				transport.setStop_id(Integer.parseInt(request.getParameter("stop_id_fromschool")));
				transport.setTrip_id(Integer.parseInt(request.getParameter("trip_id_fromschool")));
			} else {
				transport.setBus_id(0);
				transport.setRoute_id(0);
				transport.setStop_id(0);
				transport.setTrip_id(0);
			}

		}
		return transport;
	}

	public Students getStudent(HttpServletRequest request) {

		Students student = new Students();
		student.setFirst_name(request.getParameter("first_name"));
		student.setLast_name(request.getParameter("last_name"));
		student.setGr_number(request.getParameter("student_id"));
		student.setRfid_number(request.getParameter("rfid_number"));
		student.setGender(request.getParameter("gender"));
		student.setStudent_grade(request.getParameter("student_grade"));
		return student;

	}

	
	public Staff getStaff(HttpServletRequest request)
	{
		Staff staff= new Staff();
		staff.setEmail(request.getParameter("email"));
		staff.setFull_name(request.getParameter("name"));
		staff.setGender(request.getParameter("gender"));
		staff.setMobile_number(request.getParameter("mobile"));
		staff.setRfid_number(request.getParameter("rfid_number"));
		staff.setStaff_id(request.getParameter("staff_id"));
		return staff;
	}
	
	
	public Parents getParent(HttpServletRequest request, int student_id) {

		// System.out.println(request.getParameter("parent_mobile"));
		Parents parent = new Parents();
		parent.setFirst_name(request.getParameter("parent_firstname"));
		parent.setLast_name(request.getParameter("parent_lastname"));
		parent.setMobile(request.getParameter("parent_mobile").trim());
		parent.setEmail(request.getParameter("parent_email"));
		parent.setStudent_id(student_id);
		return parent;

	}
	public Guardians getGuardian(HttpServletRequest request, int student_id) {

		// System.out.println(request.getParameter("parent_mobile"));
		Guardians parent = new Guardians();
		parent.setFirst_name(request.getParameter("first_name"));
		parent.setLast_name(request.getParameter("last_name"));
		parent.setMobile_number(request.getParameter("mobile").trim());
		parent.setEmail(request.getParameter("email"));
		parent.setRelation(request.getParameter("relation"));
		parent.setStudent_id(student_id);
		return parent;

	}

	public Address getAddress(HttpServletRequest request, int id, String subscriber_type) {

		Address address = new Address();
		address.setCity(request.getParameter("city"));
		address.setCountry(request.getParameter("country"));
		address.setPostal(request.getParameter("postal"));
		address.setState(request.getParameter("state"));
		address.setStreet(request.getParameter("street"));
		address.setSubscriber_id(id);
		address.setSubscriber_type(subscriber_type);
		return address;
	}

	public Alerts getAlerts(HttpServletRequest request, int id, String subscriber_type, String alerts_type) {

		Alerts alerts = new Alerts();
		alerts.setAlert_type(alerts_type);
		alerts.setSubscriber_id(id);
		alerts.setSubscriber_type(subscriber_type);
		if (alerts_type.equals("email")) {
 
			
			alerts.setAll_alerts(request.getParameter("email_all"));
			alerts.setIrregularities(request.getParameter("email_irregularities"));
			alerts.setLate(request.getParameter("email_late"));
			alerts.setNo_show(request.getParameter("email_noshow"));
			alerts.setRegularities(request.getParameter("email_regularities"));
 
			if (request.getParameter("email_all") == null)
				alerts.setAll_alerts("off");
			else
				alerts.setAll_alerts(request.getParameter("email_all"));
			if (request.getParameter("email_irregularities") == null)
				alerts.setIrregularities("off");
			else
				alerts.setIrregularities(request.getParameter("email_irregularities"));
			if(request.getParameter("email_late")==null)
				alerts.setLate("off");
			else 
				alerts.setLate(request.getParameter("email_late"));
			if(request.getParameter("email_noshow")==null)
				alerts.setNo_show("off");
			else
				alerts.setNo_show(request.getParameter("email_noshow"));
			if(request.getParameter("email_regularities") == null)
				alerts.setRegularities("off");
			else 
				alerts.setRegularities(request.getParameter("email_regularities"));
 
			  
		}
		if (alerts_type.equals("sms")) {
			if (request.getParameter("sms_all") == null)
				alerts.setAll_alerts("off");
			else
				alerts.setAll_alerts(request.getParameter("sms_all"));
			if (request.getParameter("sms_irregularities") == null)
				alerts.setIrregularities("off");
			else
				alerts.setIrregularities(request.getParameter("sms_irregularities"));
			if(request.getParameter("sms_late")==null)
				alerts.setLate("off");
			else 
				alerts.setLate(request.getParameter("sms_late"));
			if(request.getParameter("sms_noshow")==null)
				alerts.setNo_show("off");
			else
				alerts.setNo_show(request.getParameter("sms_noshow"));
			if(request.getParameter("sms_regularities") == null)
				alerts.setRegularities("off");
			else 
				alerts.setRegularities(request.getParameter("sms_regularities"));
			 
		}

		return alerts;
	}

	/*public Address updateAddress(int id, HttpServletRequest request,String subscriber_type) {
		Address address=staffservice.getAddress(id);
		address.setCity(request.getParameter("city"));
		address.setCountry(request.getParameter("country"));
		address.setPostal(request.getParameter("postal"));
		address.setState(request.getParameter("state"));
		address.setStreet(request.getParameter("street"));
		address.setSubscriber_id(id);
		address.setSubscriber_type(subscriber_type);
		return address;
	}*/
}

/*
 * Student[ first_name, last_name, student_id, rfid_number, gender]
 * 
 * Parent[ parent_firstname, parent_lastname, parent_mobile, parent_email]
 * 
 * 
 * Transport[ bus_id_number_fromhome, route_id_fromhome, trip_id_fromhome,
 * stop_id_fromhome, bus_id_number_fromschool, route_id_fromschool,
 * trip_id_fromschool, stop_id_fromschool]
 * 
 * Address [ street, city, state, postal, country ]
 * 
 * Alerts [email_all, email_noshow, email_late, email_irregularities,
 * email_regularities, sms_all, sms_noshow, sms_late, sms_irregularities,
 * sms_regularities ]
 */
