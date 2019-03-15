package com.main.sts.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import com.main.sts.entities.Stops;
import com.main.sts.service.StopsService;
import com.main.sts.util.RolesUtility;

@Controller
@RequestMapping(value = "/school_admin/stop")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_GUEST','ROLE_CUSTOMER_SUPPORT','ROLE_OPERATOR')")
public class BusStopsController {

	
	@Autowired
	private RolesUtility rolesUtility;

	public static final Logger logger = Logger.getLogger(BusStopsController.class);
	@Autowired
	private StopsService stopservice;

	@RequestMapping(value = "/stops", method = RequestMethod.GET)
	public ModelAndView stopsHomePage(ModelAndView model, HttpServletRequest request) {
		// System.out.println("inside stops controller");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}

		// Authentication auth =
		// SecurityContextHolder.getContext().getAuthentication();
		model.setViewName("/school_admin/stops/stops_list");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		List<Stops> stops = stopservice.getAllStops();
		// //System.out.println("stops are "+stops.size());
		Collections.sort(stops);
		/*
		 * List<RouteStopsEntity> rt = rs.getAll(); model.addObject("rt", rt);
		 */
		model.addObject("stops", stops);
		model.addObject("current_page", "stops");

		model.addObject("login_role", rolesUtility.getRole(request));
		return model;
	}

	@RequestMapping(value = "/addstop", method = RequestMethod.GET)
	public ModelAndView addStop(ModelAndView model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}

		model.setViewName("/school_admin/stops/addstop");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "stops");

		model.addObject("login_role", rolesUtility.getRole(request));
		return model;
	}

	@RequestMapping(value = "/addstopaction", method = RequestMethod.POST)
	public String addStopAction(final Model model, HttpServletRequest request, @ModelAttribute("stop") Stops stop) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			System.out.println("inside if");
			return "redirect:/j_spring_security_logout";
		}

		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addAttribute("date", formatter.format(new Date()));
		model.addAttribute("login_name", auth.getName());
		model.addAttribute("current_page", "stops");

		// validation for stop name duplicate starts
		Stops stopEntity = stopservice.getStopName(stop.getStop_name());
		if (stopEntity != null) {
			model.addAttribute("stop", stop);
			model.addAttribute("error_message", "stopDup");
			return "/school_admin/stops/addstop";
		}
		// validation for stop name duplicate starts

		// validation for stop latitude and longitude duplicate starts
		Stops se = stopservice.getStop(stop.getLatitude(), stop.getLongitude());
		if (se != null) {
			model.addAttribute("stop", stop);
			model.addAttribute("error_message", "latAndLongDup");
			return "/school_admin/stops/addstop";
		}
		// validation for stop latitude and longitude duplicate ends
		else {
			// stop.setIsAssigned("no");
			stopservice.insertStop(stop);
			logger.info("Stop [ " + stop.getStop_name() + " ] got added");
			return "redirect:/school_admin/stop/stops";
		}
	}

	@RequestMapping(value = "/updatestop", method = RequestMethod.GET)
	public ModelAndView updateStop(ModelAndView model, HttpServletRequest request, HttpSession session) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}

		// Authentication auth =
		// SecurityContextHolder.getContext().getAuthentication();
		model.setViewName("/school_admin/stops/updatestop");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "stops");
		int stopId = Integer.parseInt(request.getParameter("id"));
		Stops stopEntity = stopservice.getStop(stopId);
		model.addObject("stop", stopEntity);
		session.setAttribute("stopId", stopId);
		
		model.addObject("login_role", rolesUtility.getRole(request));

		return model;

	}

	@RequestMapping(value = "/updatestopaction", method = RequestMethod.POST)
	public ModelAndView updateStopAction(ModelAndView model, HttpServletRequest request, HttpSession session,
			@ModelAttribute Stops stop) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}

		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "stops");
		int stopId = (Integer) session.getAttribute("stopId");
		List<Stops> stopList = stopservice.getAllStops();
		String old_isassigned=stopservice.getStop(stopId).getIsAssigned();
		if(old_isassigned.equalsIgnoreCase("no"))
		{
			stop.setIsAssigned("0");
		}
		else
		{
			stop.setIsAssigned(old_isassigned);
		}
		for (Stops stopEntity : stopList) {
			if (!(stopEntity.getId() == (stopId))) {
				String stopName = stopEntity.getStop_name();
				String longitude = stopEntity.getLongitude();
				String latitude = stopEntity.getLatitude();
				if (stop.getStop_name().equals(stopName)) {
					model.addObject("error_message", "stopDup");
					model.addObject("stop", stop);
					model.setViewName("/school_admin/stops/updatestop");
					return model;
				}

				if (stop.getLatitude() == latitude && stop.getLongitude() == longitude) {
					model.addObject("error_message", "latAndLongDup");
					model.addObject("stop", stop);
					model.setViewName("/school_admin/stops/updatestop");
					return model;
				}
			}
		}
		// System.out.println(stop);
		stopservice.updateStop(stopId, stop);
		logger.info("Stop [ " + stop.getStop_name() + " ] got Updated");
		return new ModelAndView("redirect:/school_admin/stop/stops");
	}

	@RequestMapping(value = "/deletestop", method = RequestMethod.GET)
	public ModelAndView removeStop(ModelAndView model, HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}

		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "stops");
		model.setViewName("/school_admin/stops/removestop");
		int stopId = Integer.parseInt(request.getParameter("id"));
		Stops stopEntity = stopservice.getStop(stopId);
		HttpSession session = request.getSession();
		session.setAttribute("id", stopId);
		// //System.out.println("stop to delete "+stopEntity);
		model.addObject("stop", stopEntity);
		model.addObject("login_role", rolesUtility.getRole(request));
		return model;
	}

	@RequestMapping(value = "/deletestopaction", method = RequestMethod.POST)
	public String removeStopAction(HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			System.out.println("inside if");
			return "redirect:/j_spring_security_logout";
		}
		HttpSession session = request.getSession();

		int stopId = (Integer) session.getAttribute("id");
		String stopname = stopservice.getStop(stopId).getStop_name();
		stopservice.deleteStop(stopId);
		logger.info("Stop [ " + stopname + " ] got Deleted");
		return "redirect:/school_admin/stop/stops";
	}

	@RequestMapping(value = "/removeAllStopsByStopIds", method = RequestMethod.POST)
	public @ResponseBody String removeAllStopsByStopIds(ModelAndView model, HttpServletRequest request) {
		// //System.out.println("inside delete stops method");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			System.out.println("inside if");
			return "redirect:/j_spring_security_logout";
		}

		String stopIds = request.getParameter("stopIds");
		//System.out.println(stopIds);
		String stopsIdsArray[] = stopIds.split(",");
		int totalItems = stopsIdsArray.length;
		//System.out.println(totalItems);
		Stops stop = null;
		for (int i = 0; i < totalItems; i++) {
			// System.out.println("staff to be deleted: "+stopsIdsArray[i-1]);
			//busStopsDaoImpl.deleteStopByStopId(stopsIdsArray[i]);
			stop = stopservice.getStop(Integer.parseInt(stopsIdsArray[i]));
			stopservice.deleteStop(stop);
			logger.info("Stop [ "+stop.getStop_name()+" ] deleted");
		}
		// return null;
		return "stops";
	}

	// search function

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public @ResponseBody String searchStop(HttpServletRequest request, Model model, HttpSession session) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			System.out.println("inside if");
			return "redirect:/j_spring_security_logout";
		}

		String rfid_number = request.getParameter("rfid_number");
		String searchOption = request.getParameter("searchOption");
		System.out.println("inside controller" + rfid_number +" "+searchOption);
		List<Stops> searchStops = null;

		searchStops = stopservice.searchStops(searchOption, rfid_number);
		// System.out.println("stops search.. "+searchStops);
		session.setAttribute("searchStops", searchStops);
		return "/sts/school_admin/stop/stopSearch";
	}

	@RequestMapping(value = "/stopSearch", method = RequestMethod.GET)
	public ModelAndView stopsSearchResponse(ModelAndView model, HttpServletRequest request, HttpSession session) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}

		model.setViewName("/school_admin/stops/stops_list");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "stops");

		@SuppressWarnings("unchecked")
		List<Stops> searchstops = (List<Stops>) session.getAttribute("searchStops");
		// System.out.println(searchstops);

		model.addObject("stops", searchstops);
		if (searchstops==null) {
			model.addObject("stops", stopservice.getAllStops());
			model.addObject("error_message", "noMatching");
		}

		return model;
	}

}// class

