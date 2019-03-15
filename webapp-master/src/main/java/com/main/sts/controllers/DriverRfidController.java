package com.main.sts.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.main.sts.entities.RfidCards;
import com.main.sts.service.RfidCardsService;
import com.main.sts.util.RolesUtility;

@Controller
@RequestMapping(value = "/school_admin/rfid")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_GUEST','ROLE_CUSTOMER_SUPPORT','ROLE_OPERATOR')")
public class DriverRfidController {

	private static final Logger logger = Logger.getLogger(DriverRfidController.class);
	private static final String type = "driver";

	@Autowired
	RolesUtility rolesUtility;
	@Autowired
	private RfidCardsService rfidCardsService;

	@RequestMapping(value = "/driver", method = RequestMethod.GET)
	public ModelAndView seniorHomePage(ModelAndView model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}

		model.setViewName("/school_admin/rfid/driver_rfid_list");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "student_rfid_list");
		List<RfidCards> cards = rfidCardsService.getRfidsByType(type);

		model.addObject("driver_rfid_list", cards);
		// //System.out.println(rfidDaoImpl.getRfidListByType("student"));
		// logger.info(cards);

		model.addObject("login_role", rolesUtility.getRole(request));
		return model;
	}

	@RequestMapping(value = "/driver/add", method = RequestMethod.POST)
	public @ResponseBody String studentRfidAdd(HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			System.out.println("inside if");
			return "redirect:/j_spring_security_logout";
		}

		String rfid_number = request.getParameter("rfid_number");
		/*
		 * Sql database -- start
		 */

		boolean cardfound = rfidCardsService.rfidExists(rfid_number);
		if (cardfound == true) {
			return "rfid_exists";
		} else {
			rfidCardsService.addRfid(rfid_number, type);
			logger.info("New Rfid number [ " + rfid_number + " ]  has been registered with type driver");
			return "rfid_inserted";
		}

		/*
		 * RfidEntity rfid = rfidDaoImpl.getRFIDByNumber(rfid_number); if (rfid
		 * == null) { rfid = new RfidEntity(); //
		 * //System.out.println("RFID "+rfid); rfid.setAllocated_time("none");
		 * rfid.setAllocated_to("none"); rfid.setAvailable("yes");
		 * rfid.setRfid_number(rfid_number); rfid.setType("driver");
		 * rfid.setAllocated_person_name("none"); boolean reply =
		 * rfidDaoImpl.saveRfid(rfid);
		 * 
		 * if (reply == true) { logger.info("Driver RFID " + rfid_number +
		 * " added"); return "rfid_inserted"; } else { return
		 * "rfid_notinserted"; } } else { return "rfid_exists"; }
		 */

	}

	@RequestMapping(value = "/driver/update", method = RequestMethod.POST)
	public @ResponseBody String studentRfidUpdate(HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			System.out.println("inside if");
			return "redirect:/j_spring_security_logout";
		}

		String current_rfid = request.getParameter("current_rfid");
		String new_rfid = request.getParameter("new_rfid");
		// //System.out.println("Current rfid="+current_rfid+"  new rfid="+new_rfid);
		boolean reply = rfidCardsService.updateRfid(current_rfid, new_rfid);
		if (reply == true) {
			logger.info("Rfid number [ " + current_rfid + " ] changed to [ " + new_rfid + " ]  type Driver");
			return "rfid_inserted";
		} else if (reply == false) {
			return "rfid_exists";
		} else {
			return "rfid_notinserted";
		}
	}

	@RequestMapping(value = "/driver/delete", method = RequestMethod.POST)
	public @ResponseBody String studentRfidDelete(HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			System.out.println("inside if");
			return "redirect:/j_spring_security_logout";
		}

		String rfid_number = request.getParameter("rfid_number");

		boolean reply = rfidCardsService.deleteRfid(rfid_number);
		if (reply == true) {
			logger.info("Rfid number [ " + rfid_number + " ]   type Driver has been deleted");
			return "rfid_deleted";
		} else {
			return "rfid_notdeleted";
		}
	}

	// search RFID starts
	@RequestMapping(value = "/driver/search", method = RequestMethod.POST)
	public @ResponseBody String searchRfid(HttpServletRequest request, Model model, HttpSession session) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			System.out.println("inside if");
			return "redirect:/j_spring_security_logout";
		}

		String rfid_number = request.getParameter("rfid_number");
		List<RfidCards> rfids = rfidCardsService.searchRfids(rfid_number, type);
		session.setAttribute("rfids", rfids);
		return "/sts/school_admin/rfid/driverSearch";
	}

	@RequestMapping(value = "/driverSearch", method = RequestMethod.GET)
	public ModelAndView driverSearchResponse(ModelAndView model, HttpServletRequest request, HttpSession session) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}

		// Authentication auth =
		// SecurityContextHolder.getContext().getAuthentication();
		model.setViewName("/school_admin/rfid/driver_rfid_list");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());

		model.addObject("current_page", "driver_rfid_list");
		List<RfidCards> rfids = (List<RfidCards>) session.getAttribute("rfids");
		model.addObject("driver_rfid_list", rfids);

		if (rfids.isEmpty()) {
			// //System.out.println("null");
			model.addObject("driver_rfid_list", rfidCardsService.getRfidsByType(type));
			model.addObject("error_message", "noMatching");
		}

		return model;
	}

	// search RFID ends
	@RequestMapping(value = "/removeAllRFIDSByRFIds", method = RequestMethod.POST)
	public @ResponseBody String removeAllTripsByTripIds(ModelAndView model, HttpServletRequest request) {
		// //System.out.println("inside delete rfids method");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			System.out.println("inside if");
			return "redirect:/j_spring_security_logout";
		}

		String rfids = request.getParameter("rfids");
		String rfidsArray[] = rfids.split(",");
		int totalItems = rfidsArray.length;

		for (int i = 1; i <= totalItems; i++) {
			// //System.out.println("delete "+rfidsArray[i-1]);
			rfidCardsService.deleteRfid(rfidsArray[i - 1]);
			RfidCards rfidCard = rfidCardsService.getRfidCard(rfidsArray[i - 1]);
			rfidCardsService.deleteRfid(rfidCard);

			logger.info("Rfid [ " + rfidCard.getRfid_number() + " ] deleted from [ " + rfidCard.getType()
					+ " ] cards list");
		}

		/*
		 * Gson gson = new Gson(); String json = gson.toJson(routes); return
		 * json;
		 */
		// return null;
		return "driver";

	}
}
