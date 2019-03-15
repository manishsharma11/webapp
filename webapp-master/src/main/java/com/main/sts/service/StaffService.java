package com.main.sts.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.sts.dao.sql.StaffDao;
import com.main.sts.entities.Address;
import com.main.sts.entities.Alerts;
import com.main.sts.entities.Buses;
import com.main.sts.entities.Staff;
import com.main.sts.entities.Transport;
import com.main.sts.entities.Trips;
import com.main.sts.util.MakeObjects;
import com.main.sts.util.StaffJspEntity;
import com.main.sts.util.SystemConstants;

@Service
public class StaffService {

	private static final Logger logger = Logger.getLogger(Staff.class);
	@Autowired
	private StaffDao staffdao;

	@Autowired
	private MakeObjects makeObjects;
	@Autowired
	private RfidCardsService rfidCardsService;

	@Autowired
	private TripService tripService;
	@Autowired
	private BusesService busesService;
	@Autowired
	private TransportService transportService;

	private final static String subscriber_type = "staff";
	private final static String alerts_type_sms = "sms";
	private final static String alerts_type_email = "email";

	public void addStaff(HttpServletRequest request) {
		Staff staff = makeObjects.getStaff(request);
		staffdao.insert(staff);

		rfidCardsService.updateRfidWhenAllocated(staff.getId(), staff.getFull_name(), staff.getRfid_number());
		Address address = makeObjects.getAddress(request, staff.getId(), subscriber_type);
		staffdao.insertAddress(address);

		Alerts smsalert = makeObjects.getAlerts(request, staff.getId(), subscriber_type, alerts_type_sms);
		staffdao.insertAlert(smsalert);

		Alerts emailalert = makeObjects.getAlerts(request, staff.getId(), subscriber_type, alerts_type_email);
		staffdao.insertAlert(emailalert);

		Transport from_home = makeObjects.getTransport(request, staff.getId(), subscriber_type, SystemConstants.PICKUP);
		staffdao.insertTransport(from_home);

		Transport from_school = makeObjects.getTransport(request, staff.getId(), subscriber_type,
				SystemConstants.DROPOFF);
		staffdao.insertTransport(from_school);

		if (request.getParameter("trip_id_fromhome").length() != 0)
			if (busSeatsAvailable(Integer.parseInt(request.getParameter("trip_id_fromhome"))))
				tripService.incrementTripSeat(from_home.getTrip_id());

		if (request.getParameter("trip_id_fromschool").length() != 0)
			if (busSeatsAvailable(Integer.parseInt(request.getParameter("trip_id_fromschool")))){
				System.out.println("from school "+from_school.getTrip_id());
				tripService.incrementTripSeat(from_school.getTrip_id());
			}
		logger.info("Staff [ " + staff.getFull_name() + " ] has been Added");
	}

	public void updateStaff(HttpServletRequest request) {
		String new_rfid = request.getParameter("rfid_number");
		Staff st = staffdao.getStaff(Integer.parseInt(request.getParameter("id")));
		//System.out.println("staff "+st);
		String rfid_num = null;
		if (new_rfid.length() > 6) {

			// System.out.println("new==>" + new_rfid);
			rfidCardsService.updateRfidWhenDeallocated(st.getRfid_number());
			rfidCardsService.updateRfidWhenAllocated(st.getId(), st.getFull_name(), new_rfid);
			rfid_num = new_rfid;
		} else {
			rfid_num = st.getRfid_number();
		}

		Staff staff = makeObjects.getStaff(request);
		// System.out.println(staff);
		staff.setId(st.getId());
		staff.setRfid_number(rfid_num);
		// System.out.println(staff);
		staffdao.update(staff);

		Transport transport = transportService.getTransport(st.getId(), "staff", SystemConstants.PICKUP);
		Transport homeTransport = makeObjects.getTransport(request, st.getId(), "staff", SystemConstants.PICKUP);
		homeTransport.setId(transport.getId());
		// System.out.println(homeTransport);
		if (request.getParameter("bus_id_number_fromhome").length() != 0) {
			staffdao.updateEntity(homeTransport);
		}
		Transport transport1 = transportService.getTransport(st.getId(), "staff", SystemConstants.DROPOFF);
		Transport schoolTransport = makeObjects.getTransport(request, st.getId(), "staff", SystemConstants.DROPOFF);
		schoolTransport.setId(transport1.getId());
		if (request.getParameter("bus_id_number_fromschool").length() != 0) {
			staffdao.updateEntity(schoolTransport);
		}
		Address add = getAddress(st.getId(), "staff");
		// System.out.println(add);
		Address address = makeObjects.getAddress(request, st.getId(), "staff");
		address.setId(add.getId());
		staffdao.updateEntity(address);

		Alerts email_al = getAlerts(st.getId(), "staff", "email");
		Alerts sms_al = getAlerts(st.getId(), "staff", "sms");

		Alerts emailAlerts = makeObjects.getAlerts(request, st.getId(), "staff", "email");
		Alerts smsAlerts = makeObjects.getAlerts(request, st.getId(), "staff", "sms");

		emailAlerts.setId(email_al.getId());
		smsAlerts.setId(sms_al.getId());
		staffdao.updateEntity(emailAlerts);
		staffdao.updateEntity(smsAlerts);
		if (request.getParameter("trip_id_fromhome").length() != 0) {
			int trip_id_from_home = Integer.parseInt(request.getParameter("trip_id_fromhome"));
			if (busSeatsAvailable(trip_id_from_home)) {
				tripService.incrementTripSeat(homeTransport.getTrip_id());
				if (transport.getTrip_id() != 0)
					if(transport.getTrip_id()!=homeTransport.getTrip_id())
					tripService.decrementTripSeat(transport.getTrip_id());
			}

		}

		if (request.getParameter("trip_id_fromschool").length() != 0) {
			int trip_id_from_school = Integer.parseInt(request.getParameter("trip_id_fromschool"));

			if (busSeatsAvailable(trip_id_from_school)) {

				tripService.incrementTripSeat(schoolTransport.getTrip_id());
				if (transport1.getTrip_id() != 0)
					if(transport1.getTrip_id()!=schoolTransport.getTrip_id())
					{
						System.out.println("trip from school");
					tripService.decrementTripSeat(transport1.getTrip_id());
					}

			}

		}

		/*if (transport.getTrip_id() != 0)
			if(transport.getTrip_id()!=homeTransport.getTrip_id()){
				System.out.println("trip from school 1");
				tripService.decrementTripSeat(transport.getTrip_id());
			}
			
		if (transport1.getTrip_id() != 0)
			if(transport1.getTrip_id()!=schoolTransport.getTrip_id()){
				System.out.println("trip from school 2");
			tripService.decrementTripSeat(transport1.getTrip_id());
			}*/

		logger.info("Staff [ " + staff.getFull_name() + " ] has been updated");

	}

	public void deleteStaff(int staff_id) {

		Staff staff = staffdao.getStaff(staff_id);
		rfidCardsService.updateRfidWhenDeallocated(staff.getRfid_number());
		staffdao.deleteEntity(staff);

		Address address = getAddress(staff_id, subscriber_type);
		staffdao.deleteEntity(address);
		Transport homeTransport = transportService.getTransport(staff.getId(), "staff", SystemConstants.PICKUP);
		if (homeTransport.getTrip_id() != 0) {
			tripService.decrementTripSeat(homeTransport.getTrip_id());
			staffdao.deleteEntity(homeTransport);

		}
		Transport schoolTransport = transportService.getTransport(staff.getId(), "staff", SystemConstants.DROPOFF);
		if (schoolTransport.getTrip_id() != 0) {
			System.out.println("delete staff");
			tripService.decrementTripSeat(schoolTransport.getTrip_id());
			// System.out.println(schoolTransport.getTrip_id());
			staffdao.deleteEntity(schoolTransport);

		}
		Alerts email_al = getAlerts(staff.getId(), "staff", "email");
		Alerts sms_al = getAlerts(staff.getId(), "staff", "sms");
		staffdao.deleteEntity(sms_al);
		staffdao.deleteEntity(email_al);
		logger.info("staff [ " + staff.getFull_name() + " ] has been deleted");
	}

	public Staff getStaffByStaffId(String staff_id) {
		return staffdao.getstaff(staff_id);
	}

	public List<Staff> getStaff() {
		return staffdao.getStaff();
	}

	public Staff getStaffById(int id) {
		return staffdao.getStaff(id);
	}

	public Address getAddress(int subscriber_id, String type) {
		return staffdao.getAddress(subscriber_id, type);
	}

	public Transport getTransport(int subscriber_id, String trans_type) {
		return staffdao.getTransport(subscriber_id, trans_type);
	}

	public Alerts getAlerts(int subscriber_id, String type, String alert_type) {
		return staffdao.getAlerts(subscriber_id, type, alert_type);
	}

	public boolean busSeatsAvailable(int trip_id) {

		Trips trip = tripService.getTrip(trip_id);
		int seats_filled = trip.getSeats_filled();
		Buses buses = busesService.getBusById(trip.getTripDetail().getBusid());
		int total_seats = Integer.parseInt(buses.getBus_capacity());
		if (seats_filled == total_seats) {
			return false;
		} else {
			return true;
		}
	}

    public List<StaffJspEntity> getStaffDetails() {
        return staffdao.getStaffDetails();
    }

    public List<StaffJspEntity> searchStaff(String column, String value) {
        return staffdao.search(column, value);
    }
}
