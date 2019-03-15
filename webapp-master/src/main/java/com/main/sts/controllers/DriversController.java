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

import com.main.sts.entities.Drivers;
import com.main.sts.service.DriversService;
import com.main.sts.service.RfidCardsService;
import com.main.sts.util.RolesUtility;

@Controller
@RequestMapping(value = "/school_admin/persons")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_GUEST','ROLE_CUSTOMER_SUPPORT','ROLE_OPERATOR')")
public class DriversController {

	private static final Logger logger = Logger.getLogger(DriversController.class);

	@Autowired
	private RolesUtility rolesUtility;
	@Autowired
	private RfidCardsService rfidCardsService;
	@Autowired
	private DriversService driversService;

	@RequestMapping(value = "/drivers", method = RequestMethod.GET)
	public ModelAndView driversHomePage(ModelAndView model, HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}

		model.setViewName("/school_admin/persons/drivers");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		model.addObject("date", formatter.format(new Date()));
		List<Drivers> drivers = driversService.listDrivers();
		model.addObject("drivers", drivers);
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "drivers_list");

		model.addObject("login_role", rolesUtility.getRole(request));

		return model;
	}

	@RequestMapping(value = "/drivers/add", method = RequestMethod.GET)
	public ModelAndView seniorHomePage(ModelAndView model, HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}

		// Authentication auth =
		// SecurityContextHolder.getContext().getAuthentication();
		model.setViewName("/school_admin/persons/adddriverform");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		model.addObject("date", formatter.format(new Date()));
		model.addObject("drivers_rfid_list", rfidCardsService.getAvailableRfids("driver", 0));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "drivers_list");

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

	@RequestMapping(value = "/drivers/adddriveraction", method = RequestMethod.POST)
	public ModelAndView addDriverAction(ModelAndView model, HttpServletRequest request,
			@ModelAttribute Drivers driverentity) {
		// Authentication auth =
		// SecurityContextHolder.getContext().getAuthentication();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}

		try {
			Drivers driver = (Drivers) driversService.addDriver(request);
			// logger.info(driversService.listDrivers());
			logger.info("Driver [ " + request.getParameter("driver_name") + " ] has been added with Rfid [ "
					+ request.getParameter("driver_rfid_number") + " ]");
			rfidCardsService.updateRfidWhenAllocated(driver.getId(), driver.getDriver_name(), driver.getRfid_number());
			logger.info("Rfid [ " + request.getParameter("driver_rfid_number") + " ] allocated to driver [ "
					+ request.getParameter("driver_name") + " ] with Driver Id [ "
					+ request.getParameter("driver_name") + " ] ");

			// System.out.println(" driver based on rfid "+driverEntity);
			model.setViewName("redirect:/school_admin/persons/drivers");
			model.addObject("message", "driver_add_success");
			model.addObject("driver_name", request.getParameter("driver_name"));
		} catch (Exception e) {
			model.addObject("driver_name", request.getParameter("driver_name"));
			model.setViewName("redirect:/school_admin/persons/adddriverform");
			model.addObject("message", "driver_add_success");
			e.printStackTrace();
		}

		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "drivers_list");
		model.addObject("date", formatter.format(new Date()));

		model.addObject("login_role", rolesUtility.getRole(request));

		return model;

	}

	@RequestMapping(value = "/drivers/updatedriver", method = RequestMethod.GET)
	public ModelAndView UpdateDriver(ModelAndView model, HttpServletRequest request, HttpSession session) {
		// Authentication auth =
		// SecurityContextHolder.getContext().getAuthentication();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}

		model.setViewName("/school_admin/persons/updatedriver");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("drivers_rfid_list", rfidCardsService.getAvailableRfids("driver", 0));
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "drivers_list");
		int driver_id = Integer.parseInt(request.getParameter("id"));
		Drivers driver = driversService.getDriver(driver_id);
		model.addObject("driver", driver);

		session.setAttribute("oldDriver", driver);

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

	@RequestMapping(value = "/drivers/updatedriveraction", method = RequestMethod.POST)
	public ModelAndView updateDriverAction(ModelAndView model, HttpServletRequest request,
			@ModelAttribute Drivers driver) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}

		String new_rfid_number = request.getParameter("driver_rfid_number");
		// System.out.println(new_rfid_number);

		try {
			driversService.updateDriver(driver, new_rfid_number); // update
																	// driver
																	// here

			model.setViewName("redirect:/school_admin/persons/drivers");

		} catch (Exception e) {

			e.printStackTrace();
		}

		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "drivers_list");
		model.addObject("date", formatter.format(new Date()));
		return model;
	}

	@RequestMapping(value = "/drivers/removedriver", method = RequestMethod.GET)
	public ModelAndView removeDriver(ModelAndView model, HttpServletRequest request) {
		// Authentication auth =
		// SecurityContextHolder.getContext().getAuthentication();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}

		model.setViewName("/school_admin/persons/removedriver");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		model.addObject("date", formatter.format(new Date()));
		// model.addObject("drivers_rfid_list",rfidCardsService.getAvailableRfids("driver",
		// 0));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "drivers_list");
		int id = Integer.parseInt(request.getParameter("id"));
		Drivers driver = driversService.getDriver(id);
		// //System.out.println(driver);
		model.addObject("driver", driver);

		model.addObject("login_role", rolesUtility.getRole(request));

		return model;
	}

	@RequestMapping(value = "/drivers/removedriveraction", method = RequestMethod.POST)
	public ModelAndView removeDriverAction(ModelAndView model, HttpServletRequest request) {
		// Authentication auth =
		// SecurityContextHolder.getContext().getAuthentication();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}

		int id = Integer.parseInt(request.getParameter("driver_id"));
		String driver_rfid_number = request.getParameter("rfid_number");
		Drivers driver = driversService.getDriver(id);
		try {
			rfidCardsService.updateRfidWhenDeallocated(driver_rfid_number);
			logger.info("Rfid [ " + driver_rfid_number + " ] de-allocated from driver [ " + driver.getDriver_name()
					+ " ] with Driver Id [ " + driver.getDriver_id() + " ] ");
			driversService.deleteDriver(id);
			logger.info("Driver [ " + driver.getDriver_name() + " ] with Driver ID [ " + driver.getDriver_id()
					+ " ] has been deleted");
			model.setViewName("redirect:/school_admin/persons/drivers");

		} catch (Exception e) {
			model.setViewName("redirect:/school_admin/persons/removedriverform");
			e.printStackTrace();
		}

		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "drivers_list");
		model.addObject("date", formatter.format(new Date()));
		return model;
	}

	@RequestMapping(value = "/driver/viewdriver", method = RequestMethod.GET)
	public ModelAndView viewDriver(ModelAndView model, HttpServletRequest request) {

		// Authentication auth =
		// SecurityContextHolder.getContext().getAuthentication();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		int id = Integer.parseInt(request.getParameter("id"));
		model.addObject("driverInfo", driversService.getDriver(id));
		model.setViewName("/school_admin/persons/viewDriver");
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "staff_list");
		model.addObject("login_role", rolesUtility.getRole(request));

		return model;
	}

	// search students starts
	@RequestMapping(value = "/driver/search", method = RequestMethod.POST)
	public @ResponseBody String searchStudent(HttpServletRequest request, Model model, HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			System.out.println("inside if");
			return "redirect:/j_spring_security_logout";
		}

		String value = request.getParameter("searchkey");
		String searchOption = request.getParameter("searchOption");
		// System.out.println("value "+value+" option "+searchOption);
		List<Drivers> searchDrivers = null;
		if (searchOption.equalsIgnoreCase("rfid_number")) {

			searchDrivers = driversService.searchDrivers(value, searchOption);

		} else if (searchOption.equalsIgnoreCase("driver_name")) {
			searchDrivers = driversService.searchDrivers(value, searchOption);
		}

		else if (searchOption.equalsIgnoreCase("phone")) {
			// System.out.println("search by phone:"+rfid_number);
			// searchDrivers = driverDaoImpl.searchDriversByPhone(rfid_number);
			// System.out.println(searchDrivers);
		}
		session.setAttribute("searchDrivers", searchDrivers);
		return "/sts/school_admin/persons/driverSearch";
	}

	@RequestMapping(value = "/driverSearch", method = RequestMethod.GET)
	public ModelAndView studentSearchResponse(ModelAndView model, HttpServletRequest request, HttpSession session) {
		// Authentication auth =
		// SecurityContextHolder.getContext().getAuthentication();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}

		model.setViewName("/school_admin/persons/drivers");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "drivers_list");

		List<Drivers> searchDriversList = (List<Drivers>) session.getAttribute("searchDrivers");
		model.addObject("drivers", searchDriversList);

		if (searchDriversList == null) {
			model.addObject("drivers", driversService.listDrivers());
			model.addObject("error_message", "noMatching");
		}

		return model;
	}

	// search students ends

	@RequestMapping(value = "/removeAllDriversById", method = RequestMethod.POST)
	public @ResponseBody String removeAllDriversById(ModelAndView model, HttpServletRequest request) {
		// System.out.println("inside delete drivers method");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			System.out.println("inside if");
			return "redirect:/j_spring_security_logout";
		}

		String driverIds = request.getParameter("driverIds");
		// //System.out.println(driverIds);
		String driverIdsArray[] = driverIds.split(",");
		int totalItems = driverIdsArray.length;
		// //System.out.println(totalItems);

		for (int i = 1; i <= totalItems; i++) {
			// System.out.println("driver deleted ids: "+driverIdsArray[i-1]);
			Drivers entity = driversService.getDriver(Integer.parseInt(driverIdsArray[i - 1]));
			driversService.deleteDriver(entity);
			logger.info("Driver [ " + entity.getDriver_name() + " ] deleted from drivers list");
			rfidCardsService.updateRfidWhenDeallocated(entity.getRfid_number());
		}
		// return null;
		return "drivers";
	}

}// class

