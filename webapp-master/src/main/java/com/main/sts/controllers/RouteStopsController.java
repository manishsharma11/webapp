package com.main.sts.controllers;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.main.sts.entities.RouteStops;
import com.main.sts.service.RouteStopsService;
import com.main.sts.util.RolesUtility;

@Controller
@RequestMapping(value = "/school_admin/route/routestop")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_GUEST','ROLE_CUSTOMER_SUPPORT','ROLE_OPERATOR')")
public class RouteStopsController {

	@Autowired
	private RolesUtility rolesUtility;
	@Autowired
	private RouteStopsService routeStopsService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody String addStop(HttpServletRequest request, @ModelAttribute RouteStops routeStop) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			System.out.println("inside if");
			return "redirect:/j_spring_security_logout";
		}
		String ret = null;
		try {
			ret = routeStopsService.addRouteStop(request);
		} catch (Exception e) {
			e.printStackTrace();
			ret = "notok";
		}
		return ret;
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public @ResponseBody String removeStop(HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			System.out.println("inside if");
			return "redirect:/j_spring_security_logout";
		}

		try {
			routeStopsService.removeRouteStop(request);
			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
			return "notok";
		}

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody String updateStop(HttpServletRequest request) throws ParseException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			System.out.println("inside if");
			return "redirect:/j_spring_security_logout";
		}

		String ret = null;
		String data = request.getParameter("data");
		ret = routeStopsService.updateRouteStop(data);
		/*
		 * String arr[] = data.split("-");// 0-old num ,1-new num, 2-old time //
		 * ,3-new time ,4-stop, id 5-route_id
		 * 
		 * if (!arr[0].equals(arr[1])) { String time = null; if
		 * (arr[2].equals(arr[3])) { time = arr[2]; } else { time = arr[3]; }
		 * 
		 * int old_num = Integer.parseInt(arr[0]); int new_num =
		 * Integer.parseInt(arr[1]); //
		 * //System.out.println("Old num: "+old_num); //
		 * //System.out.println("new num: "+new_num); if (old_num != new_num) {
		 * long count = routeStopsDaoImpl.allStopsCount(arr[5]);
		 * RouteStopsEntity routeStopsEntity = new RouteStopsEntity();
		 * routeStopsEntity.setStop_number(new_num);
		 * routeStopsEntity.setRoute_id(arr[5]);
		 * routeStopsEntity.setStop_time(time); String ope = null; if (old_num <
		 * new_num) { ope = "inc"; } else { ope = "dec"; } String ret1 =
		 * compareTimesWhileStopEdit(routeStopsEntity, ope); //
		 * //System.out.println(ret1); if (ret1.equals("ok")) { RouteStopsEntity
		 * entity =
		 * routeStopsDaoImpl.getRouteStopByStopNumberAndRouteId(old_num,
		 * arr[5]); if (new_num == 1) {
		 * 
		 * routeStopsDaoImpl.incrementStopNumbersBelow(old_num, arr[5]);
		 * routeStopsDaoImpl.updateRouteStopNumberAndTime(entity.getId(),
		 * new_num, time, entity.getRoute_id()); } else if (count == new_num) {
		 * routeStopsDaoImpl.decrementStopNumbersabove(old_num, arr[5]);
		 * routeStopsDaoImpl.updateRouteStopNumberAndTime(entity.getId(),
		 * new_num, time, entity.getRoute_id()); } else { //
		 * //System.out.println("else"); if (old_num < new_num) { // inc
		 * routeStopsDaoImpl.updateRouteStopNumberAndTime(entity.getId(), (int)
		 * count + 1, time, entity.getRoute_id());
		 * routeStopsDaoImpl.decrementStopNumbersBelow(new_num,
		 * entity.getRoute_id());
		 * routeStopsDaoImpl.updateRouteStopNumberAndTime(entity.getId(),
		 * new_num, time, entity.getRoute_id());
		 * routeStopsDaoImpl.incrementStopNumbersBelow(old_num,
		 * entity.getRoute_id()); } else { // System.out.println("dec"); // dec
		 * routeStopsDaoImpl.updateRouteStopNumberAndTime(entity.getId(), 0,
		 * time, entity.getRoute_id());
		 * routeStopsDaoImpl.incrementStopNumbers(new_num,
		 * entity.getRoute_id());
		 * routeStopsDaoImpl.updateRouteStopNumberAndTime(entity.getId(),
		 * new_num, time, entity.getRoute_id());
		 * routeStopsDaoImpl.decrementStopNumbers(old_num,
		 * entity.getRoute_id()); //
		 * routeStopsDaoImpl.updateRouteStopNumberAndTime(entity.getId(), //
		 * new_num, time, entity.getRoute_id()); } } ret = "ok"; } else { ret =
		 * ret1; } }
		 * 
		 * } else { if (!arr[2].equals(arr[3])) { // Change just time
		 * RouteStopsEntity routeStopsEntity = new RouteStopsEntity();
		 * routeStopsEntity.setStop_number(Integer.parseInt(arr[0]));
		 * routeStopsEntity.setRoute_id(arr[5]);
		 * routeStopsEntity.setStop_time(arr[3]); String ret1 =
		 * compareTimesWhileStopTimeUpdate(routeStopsEntity);
		 * 
		 * if (ret1.equals("ok")) { ret = "ok"; RouteStopsEntity entity =
		 * routeStopsDaoImpl.getRouteStopByStopNumberAndRouteId(
		 * Integer.parseInt(arr[0]), arr[5]);
		 * 
		 * routeStopsDaoImpl.updateRouteStopNumberAndTime(entity.getId(),
		 * Integer.parseInt(arr[0]), arr[3], entity.getRoute_id()); } else { ret
		 * = "code_1"; } }
		 * 
		 * } if (ret != null && ret.equals("code_1")) { ret = "code_1_1"; } if
		 * (ret == null) { ret = "ok"; }
		 */
		// System.out.println("Return: "+ret);
		return ret;
	}

	/*
	 * public String compareTimesWhileStopEdit(RouteStops routeStopsEntity,
	 * String ope) throws ParseException { Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication(); if ((auth != null
	 * && !(auth instanceof AnonymousAuthenticationToken) &&
	 * auth.isAuthenticated()) == false) { System.out.println("inside if");
	 * return "redirect:/j_spring_security_logout"; }
	 * 
	 * String ret = null; long count =
	 * routeStopsDaoImpl.allStopsCount(routeStopsEntity.getRoute_id());
	 * SimpleDateFormat sdf = new SimpleDateFormat("HH:ss"); Date current_time =
	 * sdf.parse(routeStopsEntity.getStop_time()); if
	 * (routeStopsEntity.getStop_number() == 1) { RouteStopsEntity nextStop =
	 * routeStopsDaoImpl.getRouteStopByStopNumberAndRouteId(1,
	 * routeStopsEntity.getRoute_id()); Date nextStop_time =
	 * sdf.parse(nextStop.getStop_time()); if
	 * (nextStop_time.equals(current_time) || current_time.after(nextStop_time))
	 * { ret = "code_1"; } else { ret = "ok"; } } else if (count ==
	 * routeStopsEntity.getStop_number()) { RouteStopsEntity preStop =
	 * routeStopsDaoImpl.getRouteStopByStopNumberAndRouteId((int) count,
	 * routeStopsEntity.getRoute_id()); Date preStop_time =
	 * sdf.parse(preStop.getStop_time()); if (preStop_time.equals(current_time)
	 * || current_time.before(preStop_time)) { ret = "code_1"; } else { ret =
	 * "ok"; }
	 * 
	 * } else { if (ope.equals("dec")) { RouteStopsEntity preStop =
	 * routeStopsDaoImpl.getRouteStopByStopNumberAndRouteId(
	 * routeStopsEntity.getStop_number() - 1, routeStopsEntity.getRoute_id());
	 * 
	 * RouteStopsEntity nextStop =
	 * routeStopsDaoImpl.getRouteStopByStopNumberAndRouteId(
	 * routeStopsEntity.getStop_number(), routeStopsEntity.getRoute_id()); //
	 * //System.out.println(preStop); // //System.out.println(nextStop); Date
	 * nextStop_time = sdf.parse(nextStop.getStop_time()); Date preStop_time =
	 * sdf.parse(preStop.getStop_time()); if (preStop_time.equals(current_time)
	 * || current_time.before(preStop_time) ||
	 * current_time.equals(nextStop_time) || current_time.after(nextStop_time))
	 * { ret = "code_1"; } else { ret = "ok"; } } else { RouteStopsEntity
	 * preStop = routeStopsDaoImpl.getRouteStopByStopNumberAndRouteId(
	 * routeStopsEntity.getStop_number(), routeStopsEntity.getRoute_id());
	 * 
	 * RouteStopsEntity nextStop =
	 * routeStopsDaoImpl.getRouteStopByStopNumberAndRouteId(
	 * routeStopsEntity.getStop_number() + 1, routeStopsEntity.getRoute_id());
	 * // //System.out.println(preStop); // //System.out.println(nextStop); Date
	 * nextStop_time = sdf.parse(nextStop.getStop_time()); Date preStop_time =
	 * sdf.parse(preStop.getStop_time()); if (preStop_time.equals(current_time)
	 * || current_time.before(preStop_time) ||
	 * current_time.equals(nextStop_time) || current_time.after(nextStop_time))
	 * { ret = "code_1"; } else { ret = "ok"; } } } return ret; }
	 */

	/*
	 * public String compareTimesWhileStopTimeUpdate(RouteStopsEntity
	 * routeStopsEntity) throws ParseException { Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication(); if ((auth != null
	 * && !(auth instanceof AnonymousAuthenticationToken) &&
	 * auth.isAuthenticated()) == false) { System.out.println("inside if");
	 * return "redirect:/j_spring_security_logout"; }
	 * 
	 * String ret = null;
	 * 
	 * RouteStopsEntity
	 * previousStop=routeStopsDaoImpl.getRouteStopByStopNumberAndRouteId
	 * (routeStopsEntity.getStop_number()-1, routeStopsEntity.getRoute_id());
	 * RouteStopsEntity nextStop=routeStopsDaoImpl
	 * .getRouteStopByStopNumberAndRouteId(routeStopsEntity .getStop_number(),
	 * routeStopsEntity.getRoute_id());
	 * 
	 * SimpleDateFormat sdf = new SimpleDateFormat("HH:ss"); long count =
	 * routeStopsDaoImpl.allStopsCount(routeStopsEntity.getRoute_id()); if
	 * (count == 0) { ret = "ok"; } else if ((count + 1) ==
	 * (routeStopsEntity.getStop_number())) {
	 * 
	 * RouteStopsEntity previousStop =
	 * routeStopsDaoImpl.getRouteStopByStopNumberAndRouteId((int) count,
	 * routeStopsEntity.getRoute_id()); Date current_time =
	 * sdf.parse(routeStopsEntity.getStop_time()); Date preStop_time =
	 * sdf.parse(previousStop.getStop_time()); if
	 * (current_time.equals(preStop_time) || current_time.before(preStop_time))
	 * { ret = "code_1"; } else { ret = "ok"; } } else { //
	 * System.out.println(" stops not null");
	 * 
	 * Date current_time = sdf.parse(routeStopsEntity.getStop_time());
	 * RouteStopsEntity previousStop =
	 * routeStopsDaoImpl.getRouteStopByStopNumberAndRouteId(
	 * routeStopsEntity.getStop_number() - 1, routeStopsEntity.getRoute_id());
	 * RouteStopsEntity nextStop =
	 * routeStopsDaoImpl.getRouteStopByStopNumberAndRouteId(
	 * routeStopsEntity.getStop_number() + 1, routeStopsEntity.getRoute_id());
	 * if (previousStop == null && nextStop != null) { Date nextStop_time =
	 * sdf.parse(nextStop.getStop_time()); if
	 * (current_time.equals(nextStop_time) || current_time.after(nextStop_time))
	 * { ret = "code_1"; } else ret = "ok"; } else if (nextStop == null &&
	 * previousStop != null) { Date previousStop_time =
	 * sdf.parse(previousStop.getStop_time()); if
	 * (current_time.equals(previousStop_time) ||
	 * current_time.before(previousStop_time)) { ret = "code_1"; } else ret =
	 * "ok"; } else if (nextStop == null && previousStop == null) { ret = "ok";
	 * } else { Date previousStop_time = sdf.parse(previousStop.getStop_time());
	 * Date nextStop_time = sdf.parse(nextStop.getStop_time()); //
	 * System.out.println(previousStop); // System.out.println(nextStop); if
	 * (current_time.equals(previousStop_time) ||
	 * current_time.before(previousStop_time) ||
	 * current_time.equals(nextStop_time) || current_time.after(nextStop_time))
	 * { ret = "code_1"; }
	 * 
	 * else { ret = "ok"; } }
	 * 
	 * } return ret; }
	 */

}
