package com.main.sts.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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

import com.main.sts.entities.Buses;
import com.main.sts.entities.Drivers;
import com.main.sts.service.BusesService;
import com.main.sts.service.DriversService;
import com.main.sts.service.StudentsService;
import com.main.sts.util.RolesUtility;

@Controller
@RequestMapping(value = "/school_admin/bus")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_GUEST','ROLE_CUSTOMER_SUPPORT','ROLE_OPERATOR')")
public class BusesController {

	@Autowired
	private StudentsService studentservice;
	@Autowired
	private RolesUtility usersUtility;

	// new dependencies

	private static final Logger logger = Logger.getLogger(BusesController.class);
	@Autowired
	private BusesService busesservice;
	@Autowired
	private DriversService driverservie;

	@RequestMapping(value = "/buses", method = RequestMethod.GET)
	public ModelAndView driversHomePage(ModelAndView model, HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}

		// Authentication auth =
		// SecurityContextHolder.getContext().getAuthentication();
		model.setViewName("/school_admin/bus/buses_list");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		/*
		 * List<TripEntity> trips=tripsDaoImpl.getAllTrips(); for (TripEntity
		 * tripEntity : trips) { int seats=tripEntity.getTotal_seats();
		 * if(seats>0) { String busLicence=tripEntity.getBus_id();
		 * busesDaoImpl.updateBusAllottedSeas(busLicence); } }
		 */

		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "buses_list");

		List<Buses> buses = busesservice.getBuses();
		Collections.sort(buses);
		model.addObject("buses", buses);
		// System.out.println(buses);
		// getting count of student in buslist

		model.addObject("login_role", usersUtility.getRole(request));

		return model;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView seniorHomePage(ModelAndView model, HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}

		model.setViewName("/school_admin/bus/addnewbus");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "buses_list");

		model.addObject("drivers", driverservie.listDrivers());
		// System.out.println(driverservie.listDrivers());
		model.addObject("login_role", usersUtility.getRole(request));

		return model;
	}

	@RequestMapping(value = "/addbusaction", method = RequestMethod.POST)
	public ModelAndView addBusAction(ModelAndView model, HttpServletRequest request, @ModelAttribute Buses busEntity) {
		if (request.isUserInRole("ROLE_ADMIN")) {
			model.addObject("login_role", "ROLE_ADMIN");
		}

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		// System.out.println(busEntity);
		Buses bus = busesservice.getBusByLicence(busEntity.getBus_licence_number());
		if (bus != null) {
			model.setViewName("/school_admin/bus/addnewbus");
			model.addObject("login_role", usersUtility.getRole(request));
			model.addObject("drivers", driverservie.listDrivers());
			model.addObject("message", "bus_exists");
			model.addObject("bus", busEntity);
			model.addObject("bus_licence_number", busEntity.getBus_licence_number());

			return model;
		}
		int status = 1;
		int did = Integer.parseInt(request.getParameter("driver_name"));
		busEntity.setDriver(driverservie.getDriver(did));
		busEntity.setDriverId(driverservie.getDriver(did).getId());
		busesservice.insertBus(busEntity);
		driverservie.updateDriverStatus(did, status);
		// System.out.println(busEntity.getDriver().getDriver_name());
		logger.info("New Bus [ " + busEntity.getBus_licence_number() + " ] got added");
		return new ModelAndView("redirect:/school_admin/bus/buses");

	}

	@RequestMapping(value = "/updatebus", method = RequestMethod.GET)
	public ModelAndView updateBus(ModelAndView model, HttpServletRequest request, HttpSession session,
			@ModelAttribute Buses busEntity) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}

		int bus_id = Integer.parseInt(request.getParameter("id"));
		// Authentication auth =
		// SecurityContextHolder.getContext().getAuthentication();
		model.setViewName("/school_admin/bus/updatebus");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("drivers", driverservie.listDrivers());
		// model.addObject("routes", routeDaoImpl.getAllRoutes());
		model.addObject("date", formatter.format(new Date()));
		model.addObject("bus", busesservice.getBusById(bus_id));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "buses_list");

		// to store old bus stop.
		session.setAttribute("oldBusId", bus_id);

		// to store old driver id
		// Syste.mout.println("bus for update "+busEntity);

		model.addObject("login_role", usersUtility.getRole(request));

		return model;
	}

	@RequestMapping(value = "/updatebusaction", method = RequestMethod.POST)
	public ModelAndView updateBusAction(ModelAndView model, HttpServletRequest request, HttpSession session,
			@ModelAttribute("busEntity") Buses busEntity) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}

		// getting old bus details starts
		int oldBusId = (Integer) session.getAttribute("oldBusId");
		Buses busOldEntity = busesservice.getBusById(oldBusId);
		// System.out.println(busOldEntity);
		String busOldLicence = busOldEntity.getBus_licence_number();
		Buses check = busesservice.getBusByLicence(busEntity.getBus_licence_number());

		if (check != null)

			if (check.getBus_id() != busOldEntity.getBus_id()) {
				// //System.out.println("busId equal");
				// Authentication auth =
				// SecurityContextHolder.getContext().getAuthentication();
				model.setViewName("/school_admin/bus/updatebus");
				DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				model.addObject("date", formatter.format(new Date()));
				// model.addObject("buses", busesDaoImpl.getAllBuses());
				model.addObject("login_name", auth.getName());
				model.addObject("current_page", "buses_list");
				model.addObject("bus", busesservice.getBusById(oldBusId));
				model.addObject("drivers", driverservie.listDrivers());
				model.addObject("login_role", usersUtility.getRole(request));
				model.addObject("message", "bus_exists");
				return model;
			}
		try {

			int did = 0;
			// System.out.println(request.getParameter("driver_name"));
			if (request.getParameter("driver_name").equals("") || request.getParameter("driver_name") == null) {
				// System.out.println("if");
				busEntity.setDriver(driverservie.getDriver(busOldEntity.getDriver().getId()));
				busEntity.setDriverId(busOldEntity.getDriver().getId());
			} else {
				// System.out.println("else");
				did = Integer.parseInt(request.getParameter("driver_name"));
				busEntity.setDriver(driverservie.getDriver(did));
				busEntity.setDriverId(did);
				driverservie.updateDriverStatus(busOldEntity.getDriver().getId(), 0);
				driverservie.updateDriverStatus(busEntity.getDriver().getId(), 1);
			}
			// System.out.println(busEntity);
			//shouldnt be uncommented as that will make it not to update the value of bus allotted.
			//busEntity.setBus_allotted(busOldEntity.getBus_allotted());
			busesservice.updateBus(busEntity);

			logger.info("bus [ " + busEntity.getBus_licence_number() + " ] got updated by user [" + auth.getName()
					+ " ]");
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * ////System.out.println("updatebusaction");
		 * busesDaoImpl.updateSingleBus(busEntity, oldBusId); //after update bus
		 * licence number should be changed in trip
		 * tripsDaoImpl.updateTripWhenBusUpdates(busOldLicence,
		 * busEntity.getBus_licence_number(),busEntity.getBus_capacity());
		 * 
		 * //after updating the bus dirver availability has to change String
		 * oldDriverId=busOldEntity.getDriver_id(); String
		 * newDriverId=busEntity.getDriver_id();
		 * 
		 * if(!(oldDriverId.equals(newDriverId))) {
		 * ////System.out.println("driver is changed");
		 * driverDaoImpl.updateDriverStatusWhenAddedToBus
		 * (newDriverId,busEntity);
		 * driverDaoImpl.updateDriverWhenBusDeleted(oldDriverId); }
		 * 
		 * //if bus updates then it should update on student also
		 * ////System.out.println(oldBusId);
		 * studentDaoImpl.updateStudentBusLicenceFromHome(oldBusId,
		 * busEntity.getBus_licence_number());
		 * studentDaoImpl.updateStudentBusLicenceFromSchool(oldBusId,
		 * busEntity.getBus_licence_number());
		 * 
		 * //if bus updates then it should updateon staff also
		 * staffDaoImpl.updateStaffBusLicenceFromHome(oldBusId,
		 * busEntity.getBus_licence_number());
		 * staffDaoImpl.updateStaffBusLicenceFromSchool(oldBusId,
		 * busEntity.getBus_licence_number());
		 */
		model.addObject("login_role", usersUtility.getRole(request));
		return new ModelAndView("redirect:/school_admin/bus/buses");
	}

	@RequestMapping(value = "/removebus", method = RequestMethod.GET)
	public ModelAndView removeBus(ModelAndView model, HttpServletRequest request, HttpSession session) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}

		// Authentication auth =
		// SecurityContextHolder.getContext().getAuthentication();
		model.setViewName("/school_admin/bus/removebus");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "buses_list");

		int busId = Integer.parseInt(request.getParameter("id"));
		Buses busEntity = busesservice.getBusById(busId);
		model.addObject("bus", busEntity);
		session.setAttribute("deletebusid", busId);
		model.addObject("login_role", usersUtility.getRole(request));

		return model;
	}

	@RequestMapping(value = "/removebusaction", method = RequestMethod.POST)
	public ModelAndView RemoveBusAction(ModelAndView model, HttpServletRequest request, HttpSession session) {

		// Authentication auth =
		// SecurityContextHolder.getContext().getAuthentication();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}

		/* String route_id=request.getParameter("route_id"); */
		int busId = (Integer) session.getAttribute("deletebusid");
		Buses bus = busesservice.getBusById(busId);
		int driver_id = bus.getDriver().getId();
		// driverDaoImpl.updateDriverWhenBusDeleted(driver_id);

		if (bus.getBus_allotted() != 0) {
			model.setViewName("/school_admin/bus/removebus");
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			model.addObject("date", formatter.format(new Date()));
			model.addObject("bus", bus);
			model.addObject("login_name", auth.getName());
			model.addObject("current_page", "buses_list");
			model.addObject("error", "studentsExists");
			model.addObject("login_role", usersUtility.getRole(request));
			return model;
		}
		driverservie.updateDriverStatus(driver_id, 0);
		busesservice.deleteBus(busId);
		// routeDaoImpl.updateRouteWhenBusCreated(route_id, "none", "none");
		// Authentication auth =
		// SecurityContextHolder.getContext().getAuthentication();
		// //System.out.println("driverIDDDDD"+driver_id);
		model.addObject("login_role", usersUtility.getRole(request));

		logger.info("bus [ " + bus.getBus_licence_number() + " ] got deleted");

		return new ModelAndView("redirect:/school_admin/bus/buses");
	}

	// search students starts
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public @ResponseBody String searchBusByBusId(HttpServletRequest request, Model model, HttpSession session) {
		String search_bus = request.getParameter("search_bus");
		String searchOption = request.getParameter("searchOption");
		System.out.println(search_bus + "  " + searchOption);
		List<Buses> searchBuses = new ArrayList<Buses>();
		if (searchOption.equals("bus_licence_number")) {
			searchBuses = busesservice.searchBuses(searchOption, search_bus);
		} else if (searchOption.equals("bus_capacity")) {
			searchBuses = busesservice.searchBuses(searchOption, search_bus);
		} else if (searchOption.equals("driverId")) {
			List<Drivers> drivers = driverservie.searchDrivers(search_bus, "driver_name");
			// System.out.println(drivers);
			for (Drivers drivers2 : drivers) {
				Buses bus = busesservice.getBusByDriverId(drivers2.getId());
				searchBuses.add(bus);
			}
			// System.out.println(searchBuses);
		}
		session.setAttribute("searchBuses", searchBuses);
		return "/sts/school_admin/bus/studentBus";
	}

	@RequestMapping(value = "/studentBus", method = RequestMethod.GET)
	public ModelAndView studentSearchResponse(ModelAndView model, HttpServletRequest request, HttpSession session) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}

		// Authentication auth =
		// SecurityContextHolder.getContext().getAuthentication();
		model.setViewName("/school_admin/bus/buses_list");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "buses_list");
		List<Buses> searchBusesList = (List<Buses>) session.getAttribute("searchBuses");
		model.addObject("buses", searchBusesList);
		if (searchBusesList == null || searchBusesList.isEmpty()) {
			model.addObject("buses", busesservice.getBuses());
			model.addObject("error_message", "noMatching");
		}
		model.addObject("login_role", usersUtility.getRole(request));
		return model;
	}

	// search students ends

	@RequestMapping(value = "/removeAllBusesByBusIds", method = RequestMethod.POST)
	public @ResponseBody String removeAllBusesByBusIds(ModelAndView model, HttpServletRequest request) {
		// //System.out.println("inside delete stops method");

		String busIds = request.getParameter("busIds");
		// //System.out.println(driverIds);
		String busIdsArray[] = busIds.split(",");
		int totalItems = busIdsArray.length;
		// //System.out.println(totalItems);

		for (int i = 0; i <= totalItems - 1; i++) {

			Buses busEntity = busesservice.getBusById(Integer.parseInt(busIdsArray[i]));
			// if this contain any student it should not be deleted.
			/*
			 * Students student1 = studentservice.
			 * .validateStudentByBusIdFromHome(busIdsArray[i]); StudentEntity
			 * student2 = studentDaoImpl
			 * .validateStudentByBusIdFromSchool(busIdsArray[i]);
			 * 
			 * StaffEntity staff1 = staffDaoImpl
			 * .validateStaffByBusIdFromHome(busIdsArray[i]); StaffEntity staff2
			 * = staffDaoImpl .validateStaffByBusIdFromSchool(busIdsArray[i]);
			 * 
			 * TripEntity tripEntity =
			 * tripsDaoImpl.validateTripByBusId(busEntity
			 * .getBus_licence_number());
			 * 
			 * if (student1 == null && student2 == null && staff1 == null &&
			 * staff2 == null && tripEntity == null) {
			 */
			busesservice.deleteBus(Integer.parseInt(busIdsArray[i]));
			int status = busEntity.getDriver().getAvailable();
			driverservie.updateDriverStatus(busEntity.getDriver().getId(), --status);
			// driverDaoImpl.updateDriverWhenBusDeleted(busEntity.getDriver_id());

		}
		// return null;
		return "buses";
	}
	
	@RequestMapping(value = "/unassign_driver", method = RequestMethod.GET)
    public ModelAndView assignDriver(ModelAndView model, HttpServletRequest request, HttpSession session) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
            model.setViewName("redirect:/j_spring_security_logout");
            return model;
        }

        // Authentication auth =
        // SecurityContextHolder.getContext().getAuthentication();
        model.setViewName("/school_admin/bus//unassigndriver");
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        model.addObject("date", formatter.format(new Date()));
        model.addObject("login_name", auth.getName());
        model.addObject("current_page", "buses_list");

        int busId = Integer.parseInt(request.getParameter("id"));
        Buses busEntity = busesservice.getBusById(busId);
        model.addObject("bus", busEntity);
        session.setAttribute("deletebusid", busId);
        model.addObject("login_role", usersUtility.getRole(request));

        return model;
    }
	@RequestMapping(value = "/unassigndriveraction", method = RequestMethod.POST)
    public ModelAndView assignDriverAction(ModelAndView model, HttpServletRequest request, HttpSession session) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
            model.setViewName("redirect:/j_spring_security_logout");
            return model;
        }

        /*int busId = Integer.parseInt(request.getParameter("bus_id"));
        Buses busEntity = busesservice.getBusById(busId);
        driverservie.updateDriverStatus(busEntity.getDriverId(), 0);
        busEntity.setDriver(null);
        busEntity.setDriverId(0);
        
        busesservice.updateBus(busEntity);*/

        model.addObject("login_role", usersUtility.getRole(request));

        model.setViewName("redirect:/school_admin/bus/buses");
        return model;
    }
}// class

