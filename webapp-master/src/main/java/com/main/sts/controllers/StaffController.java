package com.main.sts.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.main.sts.entities.Staff;
import com.main.sts.entities.Stops;
import com.main.sts.entities.Transport;
import com.main.sts.service.BusesService;
import com.main.sts.service.RfidCardsService;
import com.main.sts.service.RouteService;
import com.main.sts.service.RouteStopsService;
import com.main.sts.service.StaffService;
import com.main.sts.service.StopsService;
import com.main.sts.service.TransportService;
import com.main.sts.service.TripService;
import com.main.sts.util.RolesUtility;
import com.main.sts.util.StaffJspEntity;
import com.main.sts.util.SystemConstants;

@Controller
@RequestMapping(value = "/school_admin/persons")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_GUEST','ROLE_CUSTOMER_SUPPORT','ROLE_OPERATOR')")
/*
 * @PreAuthorize("isAuthenticated()")
 * 
 * @Secured("ROLE_ADMIN")
 */
public class StaffController {

	
	
	@Autowired
	private RolesUtility rolesUtility;
	@Autowired
	private RouteStopsService routestopservice;
	@Autowired
	private RfidCardsService rfidservice;
	@Autowired
	private BusesService busservice;
	@Autowired
	private StaffService staffservice;
	@Autowired
	private TransportService transportservice;
	@Autowired
	private TripService tripservice;
	@Autowired
	private RouteService routeservice;
	@Autowired
	private StopsService stopservice;

	@RequestMapping(value = "/staff", method = RequestMethod.GET)
	public ModelAndView driversHomePage(ModelAndView model, HttpServletRequest request) {
		// Authentication auth =
		// SecurityContextHolder.getContext().getAuthentication();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		model.setViewName("/school_admin/persons/staff");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		List<StaffJspEntity> staffs = staffservice.getStaffDetails();
		Collections.sort(staffs);

		model.addObject("staffs", staffs);
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "staff_list");

		model.addObject("login_role", rolesUtility.getRole(request));
		//System.out.println(staffservice.getStaffDetails());
		return model;
	}

	@RequestMapping(value = "/staff/add", method = RequestMethod.GET)
	public ModelAndView addStaff(ModelAndView model, HttpServletRequest request,
			@ModelAttribute("staff") Staff staff) {
		// Authentication auth =
		// SecurityContextHolder.getContext().getAuthentication();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		model.setViewName("/school_admin/persons/addstaff");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("rfids", rfidservice.getAvailableRfids("staff", 0));
		model.addObject("date", formatter.format(new Date()));
		model.addObject("buses", busservice.getBuses());
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "staff_list");
		// model.addObject("staff", staff);

		// generating country names
		String[] locales = Locale.getISOCountries();
		List<String> countries = new LinkedList<String>();
		for (String countryCode : locales) {
			Locale obj = new Locale("", countryCode);
			countries.add(obj.getDisplayCountry());
		}
		Collections.sort(countries);
		model.addObject("countries", countries);
		// country name code end

		// setting gender default value as male
		staff.setGender("male");
		model.addObject("staff", staff);

		model.addObject("login_role", rolesUtility.getRole(request));

		return model;
	}

	@RequestMapping(value = "/staff/getAllStopsByBusId", method = RequestMethod.POST)
	public @ResponseBody String studentRfidUpdate(HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			System.out.println("inside if");
			return "redirect:/j_spring_security_logout";
		}

		String route_id = request.getParameter("route_id");
		List<Stops> stops = routestopservice.getStopDetails(Integer.parseInt(route_id));
		// //System.out.println(stops);
		Gson gson = new Gson();
		String json = gson.toJson(stops);
		return json;
	}

	@RequestMapping(value = "/staff/addstaffaction", method = RequestMethod.POST)
	public ModelAndView addStudentAction(ModelAndView model, HttpServletRequest request,
			@ModelAttribute("staff") Staff staff) {
		// //System.out.println(staff);

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}

		// added by sami for staff id

		// Authentication
		// auth=SecurityContextHolder.getContext().getAuthentication();
		String sid = request.getParameter("staff_id");
		String oldrfid = request.getParameter("rfid_number");
		// System.out.println("rfid "+oldrfid);
		/*
		 * StudentEntity olddata=studentDaoImpl.getStudentByRfidNumber(oldrfid);
		 * //System.out.println("old data "+olddata);
		 */
		Staff check = staffservice.getStaffByStaffId(sid);
		if (check != null) {
			// System.out.println("Id allready exist");

			model.setViewName("/school_admin/persons/addstaff");
			model.addObject("login_role", rolesUtility.getRole(request));
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			model.addObject("date", formatter.format(new Date()));
			model.addObject("login_name", auth.getName());
			model.addObject("rfids", rfidservice.getAvailableRfids("staff", 0));
			model.addObject("staff", staff);
			model.addObject("buses", busservice.getBuses());

			String[] locales = Locale.getISOCountries();
			List<String> countries = new LinkedList<String>();
			for (String countryCode : locales) {
				Locale obj = new Locale("", countryCode);
				countries.add(obj.getDisplayCountry());
			}
			Collections.sort(countries);
			model.addObject("countries", countries);

			model.addObject("error", "staffidExistError");
			return model;
		}
		// ended

		staffservice.addStaff(request);

		/*
		 * if(staff.getTrip_id_fromhome().isEmpty()) {
		 * staff.setBus_licence_number_fromhome("none");
		 * staff.setBus_id_number_fromhome("none");
		 * staff.setRoute_id_fromhome("none");
		 * staff.setRoute_name_fromhome("none");
		 * staff.setTrip_name_fromhome("none");
		 * staff.setTrip_id_fromhome("none");
		 * staff.setStop_name_fromhome("none");
		 * staff.setStop_id_fromhome("none"); }
		 * if(staff.getTrip_id_fromschool().isEmpty()) {
		 * staff.setBus_licence_number_fromschool("none");
		 * staff.setBus_id_number_fromschool("none");
		 * staff.setRoute_id_fromschool("none");
		 * staff.setRoute_name_fromschool("none");
		 * staff.setTrip_name_fromschool("none");
		 * staff.setTrip_id_fromschool("none");
		 * staff.setStop_name_fromschool("none");
		 * staff.setStop_id_fromschool("none"); }
		 * 
		 * TripEntity
		 * tripHome=tripsDaoImpl.getTripByTripId(staff.getTrip_id_fromhome());
		 * TripEntity
		 * tripSchool=tripsDaoImpl.getTripByTripId(staff.getTrip_id_fromschool
		 * ());
		 * 
		 * 
		 * if(!(staff.getTrip_id_fromhome().equals("none"))) {
		 * //System.out.println("from is there>>>>>>");
		 * if(tripHome.getTotal_seats()==tripHome.getSeats_filled()) {
		 * //System.out
		 * .println("bus home to school seats filled cap: "+tripHome.
		 * getTotal_seats()+"filled "+tripHome.getSeats_filled());
		 * model.setViewName("/school_admin/persons/addstaff");
		 * model.addObject("message", "fromHome-error");
		 * model.addObject("routeFromHome", tripHome.getRoute_name()); return
		 * model; } }
		 * 
		 * if(!(staff.getTrip_id_fromschool().equals("none"))) {
		 * if(tripSchool.getTotal_seats()==tripSchool.getSeats_filled()) {
		 * //System
		 * .out.println("bus school to home seats filled cap: "+tripSchool
		 * .getTotal_seats()+"filled "+tripSchool.getSeats_filled());
		 * model.setViewName("/school_admin/persons/addstaff");
		 * model.addObject("message", "fromSchool-error");
		 * model.addObject("routeFromSchool", tripSchool.getRoute_name());
		 * return model; } }
		 * 
		 * if(!(staff.getTrip_id_fromhome().equals("none") &&
		 * staff.getTrip_id_fromschool().equals("none"))) {
		 * //System.out.println("both are not empty"); //both routes same and
		 * having only one seat then it should not be added.
		 * if(staff.getTrip_id_fromhome() == staff.getTrip_id_fromschool()) {
		 * int
		 * remainingSeats=tripHome.getTotal_seats()-tripHome.getSeats_filled();
		 * if(remainingSeats==1) {
		 * //System.out.println("bus school to home seats filled cap: "
		 * +tripSchool.getTotal_seats()+"filled "+tripSchool.getSeats_filled());
		 * model.setViewName("/school_admin/persons/addstaff");
		 * model.addObject("message", "oneSeat-error");
		 * model.addObject("routeSame", tripSchool.getRoute_name()); return
		 * model; } } }
		 * 
		 * staffDaoImpl.saveStaff(staff); StaffEntity
		 * entity=staffDaoImpl.getStaffByRfidNumber(staff.getRfid_number());
		 * //System.out.println(entity);
		 * rfidDaoImpl.updateRfidWhenAlocated(entity
		 * .getRfid_number(),entity.getId(), entity.getName());
		 * 
		 * //after adding staff incresing seats_filled in Trip collection
		 * //TripEntity
		 * tripEntity1=tripsDaoImpl.validateTripByTripName(staff.getTrip_id_fromhome
		 * ()); tripsDaoImpl.increaseSeats(staff.getTrip_id_fromhome());
		 * 
		 * //TripEntity tripEntity2=tripsDaoImpl.validateTripByTripName(staff.
		 * getTrip_id_fromschool());
		 * tripsDaoImpl.increaseSeats(staff.getTrip_id_fromschool());
		 */
		// increasing end

		model.setViewName("redirect:/school_admin/persons/staff");

		model.addObject("login_role", rolesUtility.getRole(request));

		return model;
	}

	@RequestMapping(value = "/staff/updatestaff", method = RequestMethod.GET)
	public ModelAndView updateStaff(ModelAndView model, HttpServletRequest request, HttpSession session) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}

		int id = Integer.parseInt(request.getParameter("id"));// staffId
		Transport homeTransport = transportservice.getTransport(id, "staff", SystemConstants.PICKUP);
		session.setAttribute("staffid", id);
		Transport schoolTransport = transportservice.getTransport(id, "staff", SystemConstants.DROPOFF);
		// Authentication auth =
		// SecurityContextHolder.getContext().getAuthentication();
		model.setViewName("/school_admin/persons/updatestaff");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Staff staffEntity = staffservice.getStaffById(id);
		/* model.addObject("staff", staffDaoImpl.getStaffById(id)); */
		model.addObject("staff", staffEntity);
		// System.out.println("staffEntity:"+staffEntity);
		model.addObject("rfids", rfidservice.getAvailableRfids("staff", 0));
		model.addObject("date", formatter.format(new Date()));
		model.addObject("buses", busservice.getBuses());
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "staff_list");
		model.addObject("address", staffservice.getAddress(id, "staff"));
		model.addObject("homeBus", busservice.getBusById(homeTransport.getBus_id()));
		model.addObject("schoolBus", busservice.getBusById(schoolTransport.getBus_id()));
		model.addObject("homeRoute", routeservice.getRoute(homeTransport.getRoute_id()));
		model.addObject("schoolRoute", routeservice.getRoute(schoolTransport.getRoute_id()));
		model.addObject("homeTrip", tripservice.getTrip(homeTransport.getTrip_id()));
		model.addObject("schoolTrip", tripservice.getTrip(schoolTransport.getTrip_id()));
		model.addObject("homeStop", stopservice.getStop(homeTransport.getStop_id()));
		model.addObject("schoolStop", stopservice.getStop(schoolTransport.getStop_id()));
		model.addObject("sms", staffservice.getAlerts(id, "staff", "sms"));
		model.addObject("email", staffservice.getAlerts(id, "staff", "email"));
		// generating country names
		String[] locales = Locale.getISOCountries();
		List<String> countries = new LinkedList<String>();
		for (String countryCode : locales) {
			Locale obj = new Locale("", countryCode);
			countries.add(obj.getDisplayCountry());
		}
		Collections.sort(countries);
		model.addObject("countries", countries);
		// country name code end

		model.addObject("login_role", rolesUtility.getRole(request));

		return model;
	}

	/*
	 * @RequestMapping(value = "/staff/updatestaffaction", method =
	 * RequestMethod.POST) public ModelAndView updateStudentAction(ModelAndView
	 * model, HttpServletRequest request, @ModelAttribute StaffEntity
	 * staff,HttpSession session) { //System.out.println(staff); String id =
	 * request.getParameter("id");
	 * 
	 * String bus_id = request.getParameter("bus_id");//this is routeId not
	 * busId //if bus does not change then bus_id should take from bus_id1
	 * observe in jsp if(request.getParameter("bus_id").equals("")) {
	 * bus_id=request.getParameter("bus_id1");// this is routeId not busId
	 * //System.out.println("bus_id.equals empty");
	 * bus_id=(String)session.getAttribute("current_routeId");
	 * //System.out.println("session route:"+bus_id); } String stop_id =
	 * request.getParameter("stop_id"); String old_bus_id =
	 * request.getParameter("old_bus_id"); String old_bus_id =
	 * (String)session.getAttribute("old_bus_id"); String old_stop_id =
	 * request.getParameter("old_stop_id"); String staff_rfid =
	 * request.getParameter("staff_rfid"); String old_staff_rfid =
	 * request.getParameter("old_staff_rfid");
	 * 
	 * 
	 * //for count increse in busentity start
	 * //System.out.println("old_bus_id"+old_bus_id); BusEntity beOld =
	 * busesDaoImpl.getBusById(old_bus_id); BusEntity beNew =
	 * busesDaoImpl.getBusRouteId(bus_id);
	 * //System.out.println("busId:"+bus_id+"new busId:"+beNew); String busIdOld
	 * = old_bus_id; String busIdNew = beNew.getId();
	 * 
	 * //System.out.println("oldBusId:"+busIdOld+"newBusId:"+busIdNew); //for
	 * count increse in busentity end if(!busIdOld.equals(busIdNew)) {
	 * //System.out.println("buses are different");
	 * //System.out.println("oldBusId:"+busIdOld+"newBusId:"+busIdNew);
	 * if(beNew.getBus_capacity() <= beNew.getNoOfStudents())
	 * if(beNew.getBus_capacity() <= busesDaoImpl.getBusStudentCount(busIdNew))
	 * { Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication();
	 * model.setViewName("/school_admin/persons/updatestaff");
	 * model.setViewName("/school_admin/persons/updatestaff"); DateFormat
	 * formatter = new SimpleDateFormat("dd-MM-yyyy"); model.addObject("staffs",
	 * staffDaoImpl.getAllStaff()); model.addObject("date", formatter.format(new
	 * Date())); model.addObject("login_name", auth.getName());
	 * model.addObject("current_page", "staff_list");
	 * model.addObject("busLicence", beNew.getBus_licence_number());
	 * model.addObject("error", "new_bus_capacity");
	 * 
	 * 
	 * //staff.setRoute_id((String)session.getAttribute("current_routeId"));
	 * staff.setStaff_rfid((String)session.getAttribute("current_rfid"));
	 * staff.setStop_name((String)session.getAttribute("current_stop"));
	 * staff.setBus_licence_number
	 * ((String)session.getAttribute("bus_licence_number"));
	 * model.addObject("staff", staff); model.addObject("buses",
	 * busesDaoImpl.getAllBuses());
	 * model.addObject("rfids",rfidDaoImpl.getRfidListByTypeAndStatus("staff",
	 * "yes")); //System.out.println("nt updating error");
	 * 
	 * // generating country names String[] locales = Locale.getISOCountries();
	 * List<String> countries = new LinkedList<String>(); for (String
	 * countryCode : locales) { Locale obj = new Locale("", countryCode);
	 * countries.add(obj.getDisplayCountry()); } Collections.sort(countries);
	 * model.addObject("countries", countries); // country name code end return
	 * model; } }
	 * 
	 * 
	 * if (!stop_id.equals("")) { StopsEntity stop =
	 * busStopsDaoImpl.getStopsByRouteIdAndStopId( staff.getBus_id(),
	 * staff.getStop_id()); //System.out.println("stopppp:"+stop); BusEntity bus
	 * = busesDaoImpl.getBusRouteId(stop.getRoute_id());
	 * //staffDaoImpl.updateBusAndStop(id, bus_id, stop_id, stop.getStop_name(),
	 * bus.getBus_licence_number(),bus.getRoute_id(),bus.getRoute_name());
	 * //staffDaoImpl.updateBusAndStop(id, bus.getId(), stop_id,
	 * stop.getStop_name(),
	 * bus.getBus_licence_number(),bus.getRoute_id(),bus.getRoute_name()); //if
	 * user selects another bus then need to change route_id
	 * //System.out.println(bus_id); //BusEntity
	 * busEntity=busesDaoImpl.getBusById(bus_id); //String
	 * routeIdUpdate=bus.getRoute_id(); //String
	 * routeNameUpdate=bus.getRoute_name(); } if (!staff_rfid.equals("")) {
	 * //System.out.println("old= "+old_staff_rfid+" new="+staff_rfid);
	 * rfidDaoImpl.updateRfidWhenPersonDeleted(old_staff_rfid);
	 * staffDaoImpl.updateStaffRfid(staff_rfid, id); StaffEntity staffEntity =
	 * staffDaoImpl.getStaffByRfidNumber(staff.getStaff_rfid());
	 * rfidDaoImpl.updateRfidWhenAlocated(staff_rfid, staffEntity.getId(),
	 * staffEntity.getStaff_name());
	 * 
	 * }
	 * 
	 * 
	 * //System.out.println("updating...."); staffDaoImpl.updateStaff(staff);
	 * //while updating if changes then count should change.
	 * 
	 * //for count increse in busentity start
	 * //System.out.println("after updating decreasing oldbusId:"
	 * +busIdOld+"increasing newBusId:"+busIdNew);
	 * if(!busIdOld.equals(busIdNew)) {
	 * //busesDaoImpl.increaseCountByBus(beNew);
	 * //busesDaoImpl.decreaseCountByBus(beOld); } //for count increse in
	 * busentity end
	 * 
	 * 
	 * model.setViewName("redirect:/school_admin/persons/staff"); return model;
	 * }
	 */

	@RequestMapping(value = "/staff/viewstaff", method = RequestMethod.GET)
	public ModelAndView viewStaff(ModelAndView model, HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}

		int id = Integer.parseInt(request.getParameter("id"));

		Transport homeTransport = transportservice.getTransport(id, "staff", SystemConstants.PICKUP);
		// System.out.println("home "+homeTransport);
		Transport schoolTransport = transportservice.getTransport(id, "staff", SystemConstants.DROPOFF);
		// System.out.println("school "+schoolTransport);
		// Authentication auth =
		// SecurityContextHolder.getContext().getAuthentication();
		model.setViewName("/school_admin/persons/viewstaff");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("staff", staffservice.getStaffById(id));
		model.addObject("address", staffservice.getAddress(id, "staff"));
		model.addObject("homeBus", busservice.getBusById(homeTransport.getBus_id()));
		model.addObject("schoolBus", busservice.getBusById(schoolTransport.getBus_id()));
		model.addObject("homeRoute", routeservice.getRoute(homeTransport.getRoute_id()));
		model.addObject("schoolRoute", routeservice.getRoute(schoolTransport.getRoute_id()));
		model.addObject("homeTrip", tripservice.getTrip(homeTransport.getTrip_id()));
		model.addObject("schoolTrip", tripservice.getTrip(schoolTransport.getTrip_id()));
		model.addObject("homeStop", stopservice.getStop(homeTransport.getStop_id()));
		model.addObject("schoolStop", stopservice.getStop(schoolTransport.getStop_id()));
		model.addObject("sms", staffservice.getAlerts(id, "staff", "sms"));
		model.addObject("email", staffservice.getAlerts(id, "staff", "email"));
		model.addObject("date", formatter.format(new Date()));

		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "staff_list");

		model.addObject("login_role", rolesUtility.getRole(request));

		return model;
	}

	@RequestMapping(value = "/staff/deletestaff", method = RequestMethod.GET)
	public ModelAndView deleteStaff(ModelAndView model, HttpServletRequest request,
			final RedirectAttributes redirectAttributes) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}

		int id = Integer.parseInt(request.getParameter("id"));
		Staff staffEntity = staffservice.getStaffById(id);
		String staff_rfid = staffEntity.getRfid_number();

		// Authentication auth =
		// SecurityContextHolder.getContext().getAuthentication();
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Transport homeTransport = transportservice.getTransport(id, "staff", SystemConstants.PICKUP);
		// System.out.println("home "+homeTransport);
		Transport schoolTransport = transportservice.getTransport(id, "staff", SystemConstants.DROPOFF);
		// model.addObject("staff", staffDaoImpl.getStaffById(id));
		model.addObject("staff", staffservice.getStaffById(id));
		model.addObject("address", staffservice.getAddress(id, "staff"));
		model.addObject("homeBus", busservice.getBusById(homeTransport.getBus_id()));
		model.addObject("schoolBus", busservice.getBusById(schoolTransport.getBus_id()));
		model.addObject("homeRoute", routeservice.getRoute(homeTransport.getRoute_id()));
		model.addObject("schoolRoute", routeservice.getRoute(schoolTransport.getRoute_id()));
		model.addObject("homeTrip", tripservice.getTrip(homeTransport.getTrip_id()));
		model.addObject("schoolTrip", tripservice.getTrip(schoolTransport.getTrip_id()));
		model.addObject("homeStop", stopservice.getStop(homeTransport.getStop_id()));
		model.addObject("schoolStop", stopservice.getStop(schoolTransport.getStop_id()));
		model.addObject("sms", staffservice.getAlerts(id, "staff", "sms"));
		model.addObject("email", staffservice.getAlerts(id, "staff", "email"));
		model.addObject("date", formatter.format(new Date()));

		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "staff_list");

		model.addObject("login_role", rolesUtility.getRole(request));

		// getting students based on teacher rfid
		/*
		 * StudentEntity
		 * se=studentDaoImpl.getStudentByTeacherRfidNumber(staff_rfid);
		 * if(se!=null) {
		 * 
		 * model.setViewName("/school_admin/persons/removestaffError");
		 * model.addObject("error", "allocated"); model.addObject("staffs",
		 * staffDaoImpl.getAllStaff()); //model.addObject("name",
		 * se.getStudent_name()); model.addObject("staff", staffEntity);
		 * //System.out.println("inside se!=null"); }
		 * 
		 * //getting student list over
		 * 
		 * else {
		 */
		model.setViewName("/school_admin/persons/removestaff");
		// model.addObject("staff", staffEntity);
		// }

		model.addObject("login_role", rolesUtility.getRole(request));
		return model;
	}

	@RequestMapping(value = "/staff/removestaffaction", method = RequestMethod.GET)
	public ModelAndView removestudentAction(ModelAndView model, HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}

		String id = request.getParameter("id");
		try {
			staffservice.deleteStaff(Integer.parseInt(id));
			model.setViewName("redirect:../staff");
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addObject("login_role", rolesUtility.getRole(request));
		return model;
	}

	// search students starts
	@RequestMapping(value = "/staff/search", method = RequestMethod.POST)
	public @ResponseBody String searchStudent(HttpServletRequest request, Model model, HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			System.out.println("inside if");
			return "redirect:/j_spring_security_logout";
		}

		String rfid_number = request.getParameter("rfid_number");
		String searchOption = request.getParameter("searchOption");
		 System.out.println("inside controller "+searchOption+"  " + rfid_number);

		List<StaffJspEntity> searchStaffs = null;
		if (searchOption.equals("rfid_number")) {
			 
			searchStaffs = staffservice.searchStaff(searchOption, rfid_number);
 
		} else if (searchOption.equalsIgnoreCase("name")) {
			 
			searchStaffs = staffservice.searchStaff(searchOption, rfid_number);
			 
		} else {
			searchStaffs = staffservice.searchStaff(searchOption, rfid_number);
		}
		session.setAttribute("searchStaffs", searchStaffs);
		return "/sts/school_admin/persons/staffSearch";
	}

	@RequestMapping(value = "/staffSearch", method = RequestMethod.GET)
	public ModelAndView studentSearchResponse(ModelAndView model, HttpServletRequest request, HttpSession session) {
		// Authentication auth = SecurityContextHolder.getContext()
		// .getAuthentication();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		model.setViewName("/school_admin/persons/staff");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "staff_list");

		List<StaffJspEntity> searchStudentsList = (List<StaffJspEntity>) session.getAttribute("searchStaffs");
		model.addObject("staff", searchStudentsList);
		System.out.println("result "+searchStudentsList);
		if (searchStudentsList.isEmpty()) {
			model.addObject("staff", staffservice.getStaff());
			model.addObject("error_message", "noMatching");
		}

		return model;
	}

	// search students ends

	@RequestMapping(value = "/staff/updatestaffaction", method = RequestMethod.POST)
	public ModelAndView updateStudentAction(@ModelAttribute Staff staffEntity, ModelAndView model,
			HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}

		// System.out.println("inside update");
		HttpSession session = request.getSession();
		int id = (Integer) session.getAttribute("staffid");
		String staff_id = staffEntity.getStaff_id();

		Staff st = staffservice.getStaffByStaffId(staff_id);
		if (st != null)
			if (st.getId() != id) {
				System.out.println("same staff id allready exist");
				Transport homeTransport = transportservice.getTransport(id, "staff", SystemConstants.PICKUP);
				session.setAttribute("staffid", id);
				Transport schoolTransport = transportservice.getTransport(id, "staff", SystemConstants.DROPOFF);
				// Authentication auth =
				// SecurityContextHolder.getContext().getAuthentication();
				model.setViewName("/school_admin/persons/updatestaff");
				DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

				/* model.addObject("staff", staffDaoImpl.getStaffById(id)); */
				model.addObject("staff", staffEntity);
				// System.out.println("staffEntity:"+staffEntity);
				model.addObject("rfids", rfidservice.getAvailableRfids("staff", 0));
				model.addObject("date", formatter.format(new Date()));
				model.addObject("buses", busservice.getBuses());
				model.addObject("login_name", auth.getName());
				model.addObject("current_page", "staff_list");
				model.addObject("address", staffservice.getAddress(id, "staff"));
				model.addObject("homeBus", busservice.getBusById(homeTransport.getBus_id()));
				model.addObject("schoolBus", busservice.getBusById(schoolTransport.getBus_id()));
				model.addObject("homeRoute", routeservice.getRoute(homeTransport.getRoute_id()));
				model.addObject("schoolRoute", routeservice.getRoute(schoolTransport.getRoute_id()));
				model.addObject("homeTrip", tripservice.getTrip(homeTransport.getTrip_id()));
				model.addObject("schoolTrip", tripservice.getTrip(schoolTransport.getTrip_id()));
				model.addObject("homeStop", stopservice.getStop(homeTransport.getStop_id()));
				model.addObject("schoolStop", stopservice.getStop(schoolTransport.getStop_id()));
				model.addObject("sms", staffservice.getAlerts(id, "staff", "sms"));
				model.addObject("email", staffservice.getAlerts(id, "staff", "email"));
				// generating country names
				String[] locales = Locale.getISOCountries();
				List<String> countries = new LinkedList<String>();
				for (String countryCode : locales) {
					Locale obj = new Locale("", countryCode);
					countries.add(obj.getDisplayCountry());
				}
				Collections.sort(countries);
				model.addObject("countries", countries);
				// country name code end

				model.addObject("login_role", rolesUtility.getRole(request));
				model.addObject("error", "staffidExistError");
				return model;
			}

		try {
			staffservice.updateStaff(request);
			/*
			 * logger.info("Student [ " + request.getParameter("first_name") +
			 * request.getParameter("first_name") + " ] has been added");
			 */

			model.setViewName("redirect:/school_admin/persons/staff");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// staffservice.updateAddress(id, request, "staff");

		/*
		 * //System.out.println(staffEntity); String
		 * ret=isStopDetailsChangedFromHome( staffEntity,oldBusDetails);
		 * 
		 * 
		 * //added by sami for unique staffid // Authentication
		 * auth=SecurityContextHolder.getContext().getAuthentication(); String
		 * sid=staffEntity.getStaff_id();
		 * //System.out.println("present id on page "+sid); String
		 * oldrfid=request.getParameter("old_rfid_number");
		 * //System.out.println("old rfid "+oldrfid); StaffEntity
		 * result=staffDaoImpl.getStaffByRfidNumber(oldrfid);
		 * //System.out.println(result);
		 * 
		 * if(sid.equals(result.getStaff_id())) {
		 * //System.out.println("same value in update "); }
		 * 
		 * else { StaffEntity check=staffDaoImpl.getStaffByStaffidNumber(sid);
		 * if(check!=null) {
		 * 
		 * //System.out.println(" update allready exist");
		 * model.setViewName("/school_admin/persons/updatestaff");
		 * model.addObject("login_role",rolesUtility.getRole(request));
		 * DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		 * model.addObject("date", formatter.format(new Date()));
		 * model.addObject("login_name", auth.getName());
		 * 
		 * model.addObject("staff", result);
		 * 
		 * model.addObject("error", "staffidExistError"); return model;
		 * 
		 * } } //ended
		 * 
		 * if(ret.equals("no")){ // From Home
		 * staffEntity.setBus_id_number_fromhome
		 * (oldBusDetails.getOld_bus_id_number_fromhome());
		 * staffEntity.setBus_licence_number_fromhome
		 * (oldBusDetails.getOld_bus_licence_number_fromhome());
		 * staffEntity.setRoute_id_fromhome
		 * (oldBusDetails.getOld_route_id_fromhome());
		 * staffEntity.setRoute_name_fromhome
		 * (oldBusDetails.getOld_route_name_fromhome());
		 * staffEntity.setTrip_id_fromhome
		 * (oldBusDetails.getOld_trip_id_fromhome());
		 * staffEntity.setTrip_name_fromhome
		 * (oldBusDetails.getOld_trip_name_fromhome());
		 * staffEntity.setStop_id_fromhome
		 * (oldBusDetails.getOld_stop_id_fromhome());
		 * staffEntity.setStop_name_fromhome
		 * (oldBusDetails.getOld_stop_name_fromhome());
		 * 
		 * 
		 * 
		 * } ret=isStopDetailsChangedFromSchool( staffEntity,oldBusDetails);
		 * if(ret.equals("no")){ // From Home
		 * staffEntity.setBus_id_number_fromschool
		 * (oldBusDetails.getOld_bus_id_number_fromschool());
		 * staffEntity.setBus_licence_number_fromschool
		 * (oldBusDetails.getOld_bus_licence_number_fromschool());
		 * staffEntity.setRoute_id_fromschool
		 * (oldBusDetails.getOld_route_id_fromschool());
		 * staffEntity.setRoute_name_fromschool
		 * (oldBusDetails.getOld_route_name_fromschool());
		 * staffEntity.setTrip_id_fromschool
		 * (oldBusDetails.getOld_trip_id_fromschool());
		 * staffEntity.setTrip_name_fromschool
		 * (oldBusDetails.getOld_trip_name_fromschool());
		 * staffEntity.setStop_id_fromschool
		 * (oldBusDetails.getOld_stop_id_fromschool());
		 * staffEntity.setStop_name_fromschool
		 * (oldBusDetails.getOld_stop_name_fromschool());
		 * 
		 * 
		 * }
		 * 
		 * 
		 * String flagHome=request.getParameter("checkBoxHome"); String
		 * flagSchool=request.getParameter("checkBoxSchool");
		 * 
		 * if(flagHome==null)// check box is off {
		 * 
		 * String tripId=staffEntity.getTrip_id_fromhome();
		 * 
		 * if(!(tripId.equals("none"))) {
		 * //System.out.println("tripId from home is not none");
		 * tripsDaoImpl.decreaseSeats(tripId); }
		 * 
		 * //System.out.println("home check box is off");
		 * staffEntity.setBus_licence_number_fromhome("none");
		 * staffEntity.setBus_id_number_fromhome("none");
		 * staffEntity.setRoute_id_fromhome("none");
		 * staffEntity.setRoute_name_fromhome("none");
		 * staffEntity.setTrip_name_fromhome("none");
		 * staffEntity.setTrip_id_fromhome("none");
		 * staffEntity.setStop_name_fromhome("none");
		 * staffEntity.setStop_id_fromhome("none");
		 * 
		 * 
		 * 
		 * }
		 * 
		 * if(flagSchool==null)//check box is off { String
		 * tripIdSchool=staffEntity.getTrip_id_fromschool();
		 * 
		 * 
		 * if(!(tripIdSchool.equals("none"))) {
		 * //System.out.println("tripId school not none");
		 * tripsDaoImpl.decreaseSeats(tripIdSchool); }
		 * 
		 * 
		 * //System.out.println("scholl check box is off");
		 * staffEntity.setBus_licence_number_fromschool("none");
		 * staffEntity.setBus_id_number_fromschool("none");
		 * staffEntity.setRoute_id_fromschool("none");
		 * staffEntity.setRoute_name_fromschool("none");
		 * staffEntity.setTrip_name_fromschool("none");
		 * staffEntity.setTrip_id_fromschool("none");
		 * staffEntity.setStop_name_fromschool("none");
		 * staffEntity.setStop_id_fromschool("none");
		 * 
		 * 
		 * }
		 * 
		 * 
		 * 
		 * 
		 * 
		 * if(!staffEntity.getRfid_number().equals("")){
		 * //System.out.println("Change rfid");
		 * 
		 * rfidDaoImpl.updateRfidWhenPersonDeleted(oldBusDetails.getOld_rfid_number
		 * ()); rfidDaoImpl.updateRfidWhenAlocated(staffEntity.getRfid_number(),
		 * staffEntity.getId(),staffEntity.getName());
		 * 
		 * } else{
		 * rfidDaoImpl.updateRfidWhenPersonDeleted(oldBusDetails.getOld_rfid_number
		 * ()); staffEntity.setRfid_number(oldBusDetails.getOld_rfid_number());
		 * rfidDaoImpl
		 * .updateRfidWhenAlocated(oldBusDetails.getOld_rfid_number(),
		 * staffEntity.getId(),staffEntity.getName()); }
		 * 
		 * staffDaoImpl.removeStaff(staffEntity.getId());
		 * 
		 * staffDaoImpl.saveStaff(staffEntity);
		 * rfidDaoImpl.updateStaffName(staffEntity.getRfid_number(),
		 * staffEntity.getName());
		 */

		if (request.isUserInRole("ROLE_ADMIN")) {
			model.addObject("login_role", "ROLE_ADMIN");
		}

		return model;
	}

	/*public String isStopDetailsChangedFromHome(StaffEntity studentEntity, OldBusDetails oldBusDetails) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			System.out.println("inside if");
			return "redirect:/j_spring_security_logout";
		}

		String ret = null;
		if (studentEntity.getBus_id_number_fromhome().equals("")) {

			ret = "no";
		} else {
			// changed so decrement seats in old and increment in new
			// TripEntity
			// tripEntity1=tripsDaoImpl.validateTripByTripName(oldBusDetails.getOld_trip_name_fromhome());
			tripsDaoImpl.decreaseSeats(oldBusDetails.getOld_trip_id_fromhome());

			// TripEntity
			// tripEntity2=tripsDaoImpl.validateTripByTripName(studentEntity.getTrip_name_fromhome());
			tripsDaoImpl.increaseSeats(studentEntity.getTrip_id_fromhome());

			ret = "yes";
		}
		return ret;
	}

	public String isStopDetailsChangedFromSchool(StaffEntity studentEntity, OldBusDetails oldBusDetails) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			System.out.println("inside if");
			return "redirect:/j_spring_security_logout";
		}

		String ret = null;
		if (studentEntity.getBus_id_number_fromschool().equals("")) {
			ret = "no";
		} else {
			// changed so decrement seats in old and increment in new
			// TripEntity
			// tripEntity1=tripsDaoImpl.validateTripByTripName(oldBusDetails.getOld_trip_name_fromschool());
			tripsDaoImpl.decreaseSeats(oldBusDetails.getOld_trip_id_fromschool());

			// TripEntity
			// tripEntity2=tripsDaoImpl.validateTripByTripName(studentEntity.getTrip_name_fromschool());
			tripsDaoImpl.increaseSeats(studentEntity.getTrip_id_fromschool());
			ret = "yes";
		}
		return ret;
	}*/

	@RequestMapping(value = "/removeAllStaffsById", method = RequestMethod.POST)
	public @ResponseBody String removeAllDriversById(ModelAndView model, HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			System.out.println("inside if");
			return "redirect:/j_spring_security_logout";
		}

		// System.out.println("inside delete staff method");

		String staffsIds = request.getParameter("staffsIds");
		// //System.out.println(driverIds);
		String staffIdsArray[] = staffsIds.split(",");
		int totalItems = staffIdsArray.length;
		// //System.out.println(totalItems);

		for (int i = 1; i <= totalItems; i++) {
			try {
				staffservice.deleteStaff(Integer.parseInt(staffIdsArray[i - 1]));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// return null;
		return "staff";
	}
}
