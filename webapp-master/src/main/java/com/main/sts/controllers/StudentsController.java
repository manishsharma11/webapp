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

import org.apache.log4j.Logger;
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

import com.google.gson.Gson;
import com.main.sts.entities.Address;
import com.main.sts.entities.Alerts;
import com.main.sts.entities.Buses;
import com.main.sts.entities.Guardians;
import com.main.sts.entities.Parents;
import com.main.sts.entities.Roles;
import com.main.sts.entities.Stops;
import com.main.sts.entities.Students;
import com.main.sts.entities.Transport;
import com.main.sts.entities.Trips;
import com.main.sts.entities.Users;
import com.main.sts.service.BusesService;
import com.main.sts.service.GuardianService;
import com.main.sts.service.RfidCardsService;
import com.main.sts.service.RouteService;
import com.main.sts.service.RouteStopsService;
import com.main.sts.service.StopsService;
import com.main.sts.service.StudentsService;
import com.main.sts.service.TransportService;
import com.main.sts.service.TripService;
import com.main.sts.util.LoginService;
import com.main.sts.util.RolesUtility;
import com.main.sts.util.StudentsJspEntity;
import com.main.sts.util.SystemConstants;

////school_admin/personsstudents/getAllRoutesByBusId
@Controller
@RequestMapping(value = "/school_admin/persons")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_GUEST','ROLE_CUSTOMER_SUPPORT','ROLE_OPERATOR')")
public class StudentsController {

	private static final Logger logger = Logger.getLogger(StudentsController.class);

	private String type = "student";

	@Autowired
	private RolesUtility rolesUtility;
	@Autowired
	private LoginService loginrole;
	@Autowired
	private StudentsService studentsService;
	@Autowired
	private BusesService busesService;
	@Autowired
	private RfidCardsService rfidCardsService;

	@Autowired
	private TripService tripService;
	@Autowired
	private RouteStopsService routeStopsService;

	@Autowired
	private TransportService transportService;
	@Autowired
	private RouteService routeService;
	@Autowired
	private StopsService stopsService;
	@Autowired
	private GuardianService guardianService;

	@RequestMapping(value = "/students", method = RequestMethod.GET)
	public ModelAndView driversHomePage(ModelAndView model, HttpServletRequest request) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name=auth.getName();
		Users user=loginrole.getUser(name);
		Roles role=user.getRole();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}

		model.setViewName("/school_admin/persons/students");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		List<StudentsJspEntity> jspEntities=null;
		model.addObject("date", formatter.format(new Date()));
		if(role.getRole_name().equalsIgnoreCase("ROLE_OPERATOR"))
			jspEntities = studentsService.getAllParentStudentsForJsp(user.getFull_name());
		else 
   		  jspEntities = studentsService.getAllStudentsForJsp();
		 //System.out.println(jspEntities);
		 
		 
		model.addObject("students", jspEntities);
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "students_list");

		if (request.isUserInRole("ROLE_ADMIN")) {
			model.addObject("login_role", "ROLE_ADMIN");
		}
		return model;
	}

	@RequestMapping(value = "/students/add", method = RequestMethod.GET)
	public ModelAndView addStudent(ModelAndView model, HttpServletRequest request,
			@ModelAttribute("error_message") String error_message) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		model.setViewName("/school_admin/persons/addstudent");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		// //System.out.println(busesDaoImpl.getAllBuses());
		model.addObject("buses", busesService.getBuses());
		model.addObject("date", formatter.format(new Date()));
		model.addObject("rfids", rfidCardsService.getAvailableRfids(type, 0));
		// //System.out.println(rfidDaoImpl.getRfidListByTypeAndStatus("student",
		// "yes"));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "students_list");
		model.addObject("message", "non-error");

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

		/*
		 * // for teachers dropdown box start: List<StaffEntity> staffList =
		 * staffDaoImpl.getAllStaff(); model.addObject("staffList", staffList);
		 * // for teachers dropdown box end
		 */
		// setting gender default value as male
		model.addObject("error_message", error_message);
		// setting gender default value as male end

		model.addObject("login_role", rolesUtility.getRole(request));

		return model;
	}

	// guardian concept added
	@RequestMapping(value = "/students/addguardian", method = RequestMethod.GET)
	public ModelAndView addGuardain(ModelAndView model, HttpServletRequest request, HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		if (request.getParameter("error") != null) {
			if (request.getParameter("error").equals("nameexist"))
				model.addObject("error", "nameexist");
			else if (request.getParameter("error").equals("mobileexist"))
				model.addObject("error", "mobileexist");
			else if (request.getParameter("error").equals("emailexist"))
				model.addObject("error", "emailexist");
		}
		model.setViewName("/school_admin/persons/addguardian");
		int id = Integer.parseInt(request.getParameter("id"));
		String fn = request.getParameter("fn");
		session.setAttribute("s_id", id);
		Parents father = studentsService.getParent(id);
		String fathername = father.getFirst_name() + " " + father.getLast_name();
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "students_list");
		model.addObject("message", "non-error");
		String[] locales = Locale.getISOCountries();
		List<String> countries = new LinkedList<String>();
		for (String countryCode : locales) {
			Locale obj = new Locale("", countryCode);
			countries.add(obj.getDisplayCountry());
		}
		Collections.sort(countries);
		model.addObject("countries", countries);
		model.addObject("login_role", rolesUtility.getRole(request));
		model.addObject("fn", fn);
		model.addObject("father", fathername);
		session.setAttribute("sname", fn);
		return model;

	}

	@RequestMapping(value = "/students/addguardianaction", method = RequestMethod.POST)
	public ModelAndView addGuardianAction(ModelAndView model, HttpServletRequest request, HttpSession session,
			@ModelAttribute Guardians guardians1, @ModelAttribute Address address) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		// System.out.println("adding guardian ");
		// check that guardian name,mobile & email allready exist
		int s_id = (Integer) session.getAttribute("s_id");
		String fn = (String) session.getAttribute("sname");
		System.out.println(request.getParameter("email_all"));
		List<Guardians> guardians = guardianService.getAllGuardainByStudentId(s_id);
		for (Guardians g : guardians) {

			if (g.getFirst_name().equalsIgnoreCase(request.getParameter("first_name"))) {
				model.addObject("error", "nameexist");
				model.addObject("studentEntity", guardians1);
				model.addObject("address", address);
				// model.setViewName("redirect:/school_admin/persons/students/addguardian?id="+s_id+"&fn="+fn);
				model.setViewName("school_admin/persons/addguardian");
				model.addObject("fn", fn);
				return model;
			}
			if (g.getMobile_number().equalsIgnoreCase(request.getParameter("mobile"))) {
				model.addObject("error", "mobileexist");
				model.addObject("studentEntity", guardians1);
				model.addObject("address", address);
				model.setViewName("redirect:/school_admin/persons/students/addguardian?id=" + s_id + "&fn=" + fn);
				return model;
			}
			if (g.getMobile_number().equalsIgnoreCase(request.getParameter("email"))) {
				model.addObject("error", "emailexist");
				model.addObject("studentEntity", guardians1);
				model.addObject("address", address);
				model.setViewName("redirect:/school_admin/persons/students/addguardian?id=" + s_id + "&fn=" + fn);
				return model;
			}

		}
		try {
			guardianService.add(request, s_id);
			logger.info("Guardian [ " + request.getParameter("first_name") + " ] got added for student [ " + fn + " ]");
			model.setViewName("redirect:/school_admin/persons/students");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/students/viewguardian", method = RequestMethod.GET)
	public ModelAndView viewGuardian(ModelAndView model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		model.setViewName("/school_admin/persons/guardians");
		int sid = Integer.parseInt(request.getParameter("id"));
		List<Guardians> guardians = guardianService.getAllGuardainByStudentId(sid);
		String fn = request.getParameter("fn");
		Collections.sort(guardians);

		model.addObject("login_role", rolesUtility.getRole(request));
		Parents father = studentsService.getParent(sid);
		String fathername = father.getFirst_name() + " " + father.getLast_name();
		model.addObject("sname", fn);
		model.addObject("fname", fathername);
		model.addObject("students", guardians);
		model.addObject("studentid", sid);
		model.addObject("login_name", auth.getName());
		return model;
	}

	@RequestMapping(value = "/students/updateguardian", method = RequestMethod.GET)
	public ModelAndView updateGuardian(ModelAndView model, HttpServletRequest request)

	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		// System.out.println("update Guarian");
		model.addObject("login_role", rolesUtility.getRole(request));
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		int id = Integer.parseInt(request.getParameter("id"));
		model.setViewName("/school_admin/persons/updateguardian");
		Guardians guardians = guardianService.getGuardianById(id);
		model.addObject("students", guardians);
		Alerts sms_alerts = studentsService.getAlerts(id, "guardian", "sms");
		Alerts email_alert = studentsService.getAlerts(id, "guardian", "email");
		Address address = studentsService.getAddress(id, "guardian");
		Students sname = studentsService.getStudent(guardians.getStudent_id());
		Parents fname = studentsService.getParent(guardians.getStudent_id());
		if (request.getParameter("error") != null) {
			if (request.getParameter("error").equals("nameexist"))
				model.addObject("error", "nameexist");
			else if (request.getParameter("error").equals("mobileexist"))
				model.addObject("error", "mobileexist");
			else if (request.getParameter("error").equals("emailexist"))
				model.addObject("error", "emailexist");
		}
		model.addObject("sname", sname);
		model.addObject("fname", fname.getFirst_name() + " " + fname.getLast_name());
		model.addObject("sms", sms_alerts);
		model.addObject("email", email_alert);
		model.addObject("address", address);
		// logger.info("sms "+sms_alerts);
		// logger.info("address "+address);
		String[] locales = Locale.getISOCountries();
		List<String> countries = new LinkedList<String>();
		for (String countryCode : locales) {
			Locale obj = new Locale("", countryCode);
			countries.add(obj.getDisplayCountry());
		}
		Collections.sort(countries);
		model.addObject("countries", countries);
		return model;
	}

	@RequestMapping(value = "/students/viewdetailguardian", method = RequestMethod.GET)
	public ModelAndView viewDetailGuardian(ModelAndView model, HttpServletRequest request)

	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		// System.out.println("update Guarian");
		model.addObject("login_role", rolesUtility.getRole(request));
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		int id = Integer.parseInt(request.getParameter("id"));
		model.setViewName("/school_admin/persons/viewguardian");
		Guardians guardians = guardianService.getGuardianById(id);
		model.addObject("studentEntity", guardians);
		Alerts sms_alerts = studentsService.getAlerts(id, "guardian", "sms");
		Alerts email_alert = studentsService.getAlerts(id, "guardian", "email");
		Address address = studentsService.getAddress(id, "guardian");
		Students sname = studentsService.getStudent(guardians.getStudent_id());
		Parents fname = studentsService.getParent(guardians.getStudent_id());
		model.addObject("sname", sname.getFirst_name() + " " + sname.getLast_name());
		model.addObject("student", sname);
		model.addObject("father", fname.getFirst_name() + " " + fname.getLast_name());
		model.addObject("smsAlert", sms_alerts);
		model.addObject("emailAlert", email_alert);
		model.addObject("address", address);
		// logger.info("sms "+sms_alerts);
		// logger.info("address "+address);

		return model;
	}

	@RequestMapping(value = "/students/updateguardianaction", method = RequestMethod.POST)
	public String updateGuardianAction(Model model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {

			return "redirect:/j_spring_security_logout";
		}
		String type = "";
		int s_id = Integer.parseInt(request.getParameter("sid"));
		int gid = Integer.parseInt(request.getParameter("id"));
		List<Guardians> guardians = guardianService.getAllGuardainByStudentId(s_id);
		for (Guardians g : guardians) {
			if (g.getId() != gid) {
				if (g.getFirst_name().equalsIgnoreCase(request.getParameter("first_name"))) {

					model.addAttribute("error", "nameexist");

					return "redirect:/school_admin/persons/students/updateguardian?id=" + gid;
				} else if (g.getMobile_number().equalsIgnoreCase(request.getParameter("mobile"))) {
					model.addAttribute("error", "mobileexist");
					return "redirect:/school_admin/persons/students/updateguardian?id=" + gid;
				} else if (g.getMobile_number().equalsIgnoreCase(request.getParameter("email"))) {
					model.addAttribute("error", "emailexist");
					return "redirect:/school_admin/persons/students/updateguardian?id=" + gid;
				}
			}
		}
		try {
			guardianService.updateGuardianValue(request);
			logger.info("Guardian [ " + request.getParameter("first_name") + " ] got updated");
			type = "redirect:/school_admin/persons/students/viewguardian?id=" + request.getParameter("sid");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return type;
	}

	// end
	@RequestMapping(value = "/students/addstudentaction", method = RequestMethod.POST)
	public ModelAndView addStudentAction(ModelAndView model, HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}

		// Check Student GR_number exists or not

		// model.setViewName("/school_admin/persons/addstudent");
		model.addObject("login_role", rolesUtility.getRole(request));
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		// model.addObject("error", "studentidExistError");
		try {
			studentsService.addStudent(request);
			logger.info("Student [ " + request.getParameter("first_name") + request.getParameter("first_name")
					+ " ] has been added");
			model.setViewName("redirect:/school_admin/persons/students");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/students/getAllTripsByBusId", method = RequestMethod.POST)
	public @ResponseBody String getAllTripsByBusId(HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			System.out.println("inside if");
			return "redirect:/j_spring_security_logout";
		}

		String type = request.getParameter("type");
		String route = request.getParameter("route_id");
		// System.out.println(type + " " + route);
		List<Trips> tripEntity = tripService.getTripsByRouteIdAndTripType(Integer.parseInt(route), type);
		Gson gson = new Gson();
		String json = gson.toJson(tripEntity);

		// System.out.println(tripEntity);

		// System.out.println(tripEntity);

		return json;
	}

	@RequestMapping(value = "/students/getAllStopsByTripId", method = RequestMethod.POST)
	public @ResponseBody String getAllStopsByRouteId(HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			System.out.println("inside if");
			return "redirect:/j_spring_security_logout";
		}

		String trip_id = request.getParameter("trip_id");
		Trips trips = tripService.getTrip(Integer.parseInt(trip_id));

		List<Stops> stops = routeStopsService.getStopDetails(trips.getTripDetail().getRouteid());
		// logger.info(stops);
		Gson gson = new Gson();
		String json = gson.toJson(stops);

		return json;
	}

	@RequestMapping(value = "/students/getAllRoutesByBusId", method = RequestMethod.POST)
	public @ResponseBody String getAllRoutesByBusId(HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			System.out.println("inside if");
			return "redirect:/j_spring_security_logout";
		}

		String bus_licence_number = request.getParameter("bus_licence_number");
		String type = request.getParameter("type");
		// System.out.println(bus_licence_number + " " + type);
		Buses buses = busesService.getBusByLicence(bus_licence_number);
		List<Trips> tripEntity = tripService.getTripsByBusIdAndTripType(buses.getBus_id(), type);

		// System.out.println("tripEntity: " + tripEntity);

		for (int i = 1; i < tripEntity.size(); i++) {
			int a1 = tripEntity.get(i).getTripDetail().getRoutes().getId();
			int a2 = tripEntity.get(i - 1).getTripDetail().getRoutes().getId();
			// System.out.println(a1);
			if (a1 == a2) {
				// System.out.println("equals");
				tripEntity.remove(i);
			}
		}
		// System.out.println(tripEntity);
		Gson gson = new Gson();
		String json = gson.toJson(tripEntity);

		return json;
	}

	// search students starts
	@RequestMapping(value = "/student/search", method = RequestMethod.POST)
	public @ResponseBody String searchStudent(HttpServletRequest request, Model model, HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			System.out.println("inside if");
			return "redirect:/j_spring_security_logout";
		}

		String str = request.getParameter("rfid_number");
		String searchOption = request.getParameter("searchOption");
		// System.out.println("inside controller" + rfid_number);
		List<Students> searchStudents = null;
		if (searchOption.equals("rfid_number")) {
			// //System.out.println("search by rfid");
			searchStudents = studentsService.searchStudents(str, searchOption);
			// //System.out.println(searchStudents);
		}

		else if (searchOption.equalsIgnoreCase("gr")) {
			searchStudents = studentsService.searchStudents(str, searchOption);
		} else {
			// //System.out.println("search by name:"+rfid_number);
			searchStudents = studentsService.searchStudents(str, searchOption);
			// //System.out.println(searchStudents);
		}
		session.setAttribute("searchStudents", searchStudents);
		return "/sts/school_admin/persons/studentSearch";
	}

	@RequestMapping(value = "/studentSearch", method = RequestMethod.GET)
	public ModelAndView studentSearchResponse(ModelAndView model, HttpServletRequest request, HttpSession session) {
		// Authentication auth = SecurityContextHolder.getContext()
		// .getAuthentication();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		model.setViewName("/school_admin/persons/students");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "students");

		@SuppressWarnings("unchecked")
		List<Students> searchStudentsList = (List<Students>) session.getAttribute("searchStudents");
		model.addObject("students", searchStudentsList);
		// //System.out.println(searchStudentsList);

		if (searchStudentsList.isEmpty()) {
			model.addObject("students", studentsService.getAllStudents());
			model.addObject("error_message", "noMatching");
		}

		model.addObject("login_role", rolesUtility.getRole(request));
		return model;
	}

	// search students ends
	@RequestMapping(value = "/viewstudent", method = RequestMethod.GET)
	public ModelAndView viewStudent(ModelAndView model, HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		model.setViewName("/school_admin/persons/viewstudent");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String id = request.getParameter("id");
		Students student = studentsService.getStudent(Integer.parseInt(id));
		Transport homeTransport = transportService.getTransport(student.getId(), "student", SystemConstants.PICKUP);
		Transport schoolTransport = transportService.getTransport(student.getId(), "student", SystemConstants.DROPOFF);
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "students_list");
		model.addObject("student", student);
		model.addObject("parent", studentsService.getParent(student.getId()));
		model.addObject("smsAlert", studentsService.getAlerts(student.getId(), "student", "sms"));
		model.addObject("emailAlert", studentsService.getAlerts(student.getId(), "student", "email"));
		model.addObject("address", studentsService.getAddress(student.getId(), "student"));
		model.addObject("homeBus", busesService.getBusById(homeTransport.getBus_id()));
		model.addObject("schoolBus", busesService.getBusById(schoolTransport.getBus_id()));
		model.addObject("homeRoute", routeService.getRoute(homeTransport.getRoute_id()));
		model.addObject("schoolRoute", routeService.getRoute(schoolTransport.getRoute_id()));
		model.addObject("homeTrip", tripService.getTrip(homeTransport.getTrip_id()));
		model.addObject("schoolTrip", tripService.getTrip(schoolTransport.getTrip_id()));
		model.addObject("homeStop", stopsService.getStop(homeTransport.getStop_id()));
		model.addObject("schoolStop", stopsService.getStop(schoolTransport.getStop_id()));

		return model;
	}

	// search students ends
	@RequestMapping(value = "/students/removestudent", method = RequestMethod.GET)
	public ModelAndView removestudent(ModelAndView model, HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		model.setViewName("/school_admin/persons/removestudent");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String id = request.getParameter("id");
		Students student = studentsService.getStudent(Integer.parseInt(id));
		System.out.println("student " + student);
		Transport homeTransport = transportService.getTransport(student.getId(), "student", SystemConstants.PICKUP);
		System.out.println("home tr " + homeTransport);
		Transport schoolTransport = transportService.getTransport(student.getId(), "student", SystemConstants.DROPOFF);
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "students_list");
		model.addObject("student", student);
		model.addObject("parent", studentsService.getParent(student.getId()));
		model.addObject("smsAlert", studentsService.getAlerts(student.getId(), "student", "sms"));
		model.addObject("emailAlert", studentsService.getAlerts(student.getId(), "student", "email"));
		model.addObject("address", studentsService.getAddress(student.getId(), "student"));
		model.addObject("homeBus", busesService.getBusById(homeTransport.getBus_id()));
		model.addObject("schoolBus", busesService.getBusById(schoolTransport.getBus_id()));
		model.addObject("homeRoute", routeService.getRoute(homeTransport.getRoute_id()));
		model.addObject("schoolRoute", routeService.getRoute(schoolTransport.getRoute_id()));
		model.addObject("homeTrip", tripService.getTrip(homeTransport.getTrip_id()));
		model.addObject("schoolTrip", tripService.getTrip(schoolTransport.getTrip_id()));
		model.addObject("homeStop", stopsService.getStop(homeTransport.getStop_id()));
		model.addObject("schoolStop", stopsService.getStop(schoolTransport.getStop_id()));
		return model;
	}

	@RequestMapping(value = "/students/removestudentaction", method = RequestMethod.GET)
	public ModelAndView removestudentAction(ModelAndView model, HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		model.setViewName("redirect:../students");

		String id = request.getParameter("id");
		// System.out.println(id);
		try {
			studentsService.deleteStudent(Integer.parseInt(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// StudentEntity entity = studentDaoImpl.getStudentById(id);
		/*
		 * studentDaoImpl.removeStudent(id);
		 * rfidDaoImpl.updateRfidWhenPersonDeleted(entity.getRfid_number());
		 * 
		 * // decresing count in trip if staff removed // TripEntity //
		 * tripEntity1
		 * =tripsDaoImpl.validateTripByTripName(entity.getTrip_id_fromhome());
		 * tripsDaoImpl.decreaseSeats(entity.getTrip_id_fromhome());
		 * 
		 * // TripEntity //
		 * tripEntity2=tripsDaoImpl.validateTripByTripName(entity
		 * .getTrip_id_fromschool());
		 * tripsDaoImpl.decreaseSeats(entity.getTrip_id_fromschool());
		 * 
		 * // decresing code end
		 */
		return model;
	}

	@RequestMapping(value = "/students/updatestudent", method = RequestMethod.GET)
	public ModelAndView updateStudent(ModelAndView model, HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}

		// //System.out.println("add controller");
		String id = request.getParameter("id");

		model.setViewName("/school_admin/persons/updatestudent");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		// //System.out.println(busesDaoImpl.getAllBuses());
		model.addObject("buses", busesService.getBuses());
		model.addObject("date", formatter.format(new Date()));
		model.addObject("rfids", rfidCardsService.getAvailableRfids("student", 0));
		// //System.out.println(rfidDaoImpl.getRfidListByTypeAndStatus("student",
		// "yes"));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "students_list");
		model.addObject("message", "non-error");
		Students student = studentsService.getStudent(Integer.parseInt(id));
		Transport homeTransport = transportService.getTransport(student.getId(), "student", SystemConstants.PICKUP);
		Transport schoolTransport = transportService.getTransport(student.getId(), "student", SystemConstants.DROPOFF);
		model.addObject("student", student);
		model.addObject("parent", studentsService.getParent(student.getId()));
		model.addObject("smsAlert", studentsService.getAlerts(student.getId(), "student", "sms"));
		model.addObject("emailAlert", studentsService.getAlerts(student.getId(), "student", "email"));
		model.addObject("address", studentsService.getAddress(student.getId(), "student"));
		model.addObject("homeBus", busesService.getBusById(homeTransport.getBus_id()));
		model.addObject("schoolBus", busesService.getBusById(schoolTransport.getBus_id()));
		model.addObject("homeRoute", routeService.getRoute(homeTransport.getRoute_id()));
		model.addObject("schoolRoute", routeService.getRoute(schoolTransport.getRoute_id()));
		model.addObject("homeTrip", tripService.getTrip(homeTransport.getTrip_id()));
		model.addObject("schoolTrip", tripService.getTrip(schoolTransport.getTrip_id()));
		model.addObject("homeStop", stopsService.getStop(homeTransport.getStop_id()));
		model.addObject("schoolStop", stopsService.getStop(schoolTransport.getStop_id()));
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
		// System.out.println(busesService.getBusById(schoolTransport.getBus_id()));
		return model;
	}

	@RequestMapping(value = "/students/updatestudentaction", method = RequestMethod.POST)
	public ModelAndView updateStudentAction(ModelAndView model, HttpServletRequest request) {

		// added by sami for unique GR no

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}

		try {
			studentsService.updateStudent(request);
			/*
			 * logger.info("Student [ " + request.getParameter("first_name") +
			 * request.getParameter("first_name") + " ] has been added");
			 */
			model.setViewName("redirect:/school_admin/persons/students");

		} catch (Exception e) {
			e.printStackTrace();
		}

		model.setViewName("redirect:/school_admin/persons/students");

		return model;
	}// update student action

	/*
	 * public String isStopDetailsChangedFromHome(StudentEntity studentEntity,
	 * OldBusDetails oldBusDetails) { Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication(); if ((auth != null
	 * && !(auth instanceof AnonymousAuthenticationToken) &&
	 * auth.isAuthenticated()) == false) { System.out.println("inside if");
	 * return "redirect:/j_spring_security_logout"; }
	 * 
	 * String ret = null; if
	 * (studentEntity.getBus_id_number_fromhome().equals("")) { ret = "no"; }
	 * else { // changed so decrement seats in old and increment in new //
	 * TripEntity //
	 * tripEntity1=tripsDaoImpl.validateTripByTripName(oldBusDetails
	 * .getOld_trip_id_fromhome());
	 * tripsDaoImpl.decreaseSeats(oldBusDetails.getOld_trip_id_fromhome());
	 * 
	 * // TripEntity //
	 * tripEntity2=tripsDaoImpl.validateTripByTripName(studentEntity
	 * .getTrip_id_fromhome());
	 * tripsDaoImpl.increaseSeats(studentEntity.getTrip_id_fromhome()); ret =
	 * "yes"; } return ret; }
	 * 
	 * public String isStopDetailsChangedFromSchool(StudentEntity studentEntity,
	 * OldBusDetails oldBusDetails) { Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication(); if ((auth != null
	 * && !(auth instanceof AnonymousAuthenticationToken) &&
	 * auth.isAuthenticated()) == false) { System.out.println("inside if");
	 * return "redirect:/j_spring_security_logout"; }
	 * 
	 * String ret = null; if
	 * (studentEntity.getBus_id_number_fromschool().equals("")) { ret = "no"; }
	 * else { // changed so decrement seats in old and increment in new //
	 * TripEntity //
	 * tripEntity1=tripsDaoImpl.validateTripByTripName(oldBusDetails
	 * .getOld_trip_id_fromschool());
	 * tripsDaoImpl.decreaseSeats(oldBusDetails.getOld_trip_id_fromschool());
	 * 
	 * // TripEntity //
	 * tripEntity2=tripsDaoImpl.validateTripByTripName(studentEntity
	 * .getTrip_id_fromschool());
	 * tripsDaoImpl.increaseSeats(studentEntity.getTrip_id_fromschool()); ret =
	 * "yes"; } return ret; }
	 */

	@RequestMapping(value = "/removeGuardian", method = RequestMethod.POST)
	public @ResponseBody String removeGuardians(ModelAndView model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			System.out.println("inside if");
			return "redirect:/j_spring_security_logout";
		}
		//System.out.println("deleting Guardian");
		 
		//System.out.println(request.getParameter("stuIds"));
		String sidv=request.getParameter("sid");
		String v=sidv.split("=")[1];
		//System.out.println(v);
		int sid = Integer.parseInt(v);
		String sname = request.getParameter("sname");
		String name =  sname.split("=")[1];
		//System.out.println(name);
		String stuIdsArray[] = request.getParameter("stuIds").split(",");
		for (int i = 1; i <= stuIdsArray.length; i++) {

			try {
				guardianService.deleteStudent(Integer.parseInt(stuIdsArray[i - 1]));
				logger.info("Guardian Deleted");
			} catch (Exception e) {
				e.printStackTrace();

			}

		}
		// Gson gson=new Gson();
		String t = "viewguardian?id=" + sid + "&fn=" + name;
		return t;

	}

	@RequestMapping(value = "/removeAllStudentsById", method = RequestMethod.POST)
	public @ResponseBody String removeAllStudentsById(ModelAndView model, HttpServletRequest request) {
		// System.out.println("inside delete students method");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			System.out.println("inside if");
			return "redirect:/j_spring_security_logout";
		}

		String stuIdsArray[] = request.getParameter("stuIds").split(",");
		for (int i = 1; i <= stuIdsArray.length; i++) {

			try {
				studentsService.deleteStudent(Integer.parseInt(stuIdsArray[i - 1]));
			} catch (Exception e) {
				e.printStackTrace();

			}

		}
		return "students";
	}

}// class
