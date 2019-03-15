package com.main.sts.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.main.sts.entities.Buses;
import com.main.sts.entities.DashboardSettings;
import com.main.sts.entities.RouteStops;
import com.main.sts.entities.Trips;
import com.main.sts.service.BusesService;
import com.main.sts.service.DashBoardSettingsService;
import com.main.sts.service.RouteStopsService;
import com.main.sts.service.TripService;
import com.main.sts.util.RolesUtility;

@Controller
@RequestMapping(value = "/school_admin/")
@PreAuthorize("isAuthenticated()")
@Secured("ROLE_ADMIN")
public class SingleMapController {

	@Autowired
	private RolesUtility rolesUtility;
	@Autowired
	private RouteStopsService routeStopsService;
	@Autowired
	private DashBoardSettingsService boardSettingsService;
	@Autowired
	private TripService tripService;
	@Autowired
	private BusesService busesService;

	private final Logger logger = Logger.getLogger(SingleMapController.class);

	@RequestMapping(value = "single_map", method = RequestMethod.GET)
	public ModelAndView singleMapPage(ModelAndView model, HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}

		int route_id = 0;
		int trip_id = Integer.parseInt(request.getParameter("trip_id"));
		Trips trip = tripService.getTrip(trip_id);
		if (request.getParameter("route_id").equals("none")) {
			// System.out.println("in if map");
			route_id = trip.getTripDetail().getRouteid();

			Buses busEntity = busesService.getBusById(trip.getTripDetail().getBusid());
			model.addObject("bus_id", busEntity.getBus_id());
			model.addObject("trip_id", trip_id);
		} else
			route_id = Integer.parseInt(request.getParameter("route_id"));
		model.setViewName("/school_admin/single_map");

		List<RouteStops> routeStopsEntities = routeStopsService.getAllRouteStops(route_id);
		Gson gson = new Gson();
		String s = gson.toJson(routeStopsEntities);
		DashboardSettings dashboardSettings = boardSettingsService.getDashBoardSettings();
		String s1 = gson.toJson(dashboardSettings);
		model.addObject("stops", s);
		model.addObject("admin", s1);

		model.addObject("school_longitude", dashboardSettings.getSchool_longitude());
		model.addObject("school_latitude", dashboardSettings.getSchool_latitude());
		// //System.out.println(adminPreferencesDaoImpl.getAdminPreferences());
		return model;

	}

	@RequestMapping(value = "single_map1", method = RequestMethod.GET)
	public ModelAndView singleMapPage1(ModelAndView model, HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}

		String route_id = request.getParameter("route_id");
		if (route_id.equals("none")) {

			int trip_id = Integer.parseInt(request.getParameter("trip_id"));
			Trips tripEntity = tripService.getTrip(trip_id);
			route_id = String.valueOf(tripEntity.getTripDetail().getRouteid());
		}

		model.setViewName("/school_admin/single_map1");
		List<RouteStops> routeStops = routeStopsService.getAllRouteStops(Integer.parseInt(route_id));
		// List<RouteStopsEntity> routeStopsEntities =
		// routeStopsDaoImpl.getRouteStopsByRouteId(route_id);
		DashboardSettings dashboardSettings = boardSettingsService.getDashBoardSettings();
		Gson gson = new Gson();
		String s = gson.toJson(routeStops);
		// String s1 = gson.toJson(dashboardSettings);
		model.addObject("stops", s);
		model.addObject("admin1", dashboardSettings);
		logger.info("stops are " + s);
		logger.info("admin1 " + dashboardSettings);
		// //System.out.println(adminPreferencesDaoImpl.getAdminPreferences());
		return model;

	}
}
