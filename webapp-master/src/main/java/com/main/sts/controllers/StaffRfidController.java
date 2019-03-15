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
/*
 * @PreAuthorize("isAuthenticated()")
 * 
 * @Secured("ROLE_ADMIN")
 */
public class StaffRfidController {
	private static final Logger logger = Logger.getLogger(StudentRfidController.class);
	private static final String type = "staff";

	@Autowired
	private RolesUtility rolesUtility;
	@Autowired
	private RfidCardsService rfidCardsService;

	@RequestMapping(value = "/staff", method = RequestMethod.GET)
	public ModelAndView seniorHomePage(ModelAndView model, HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}

		// Authentication auth =
		// SecurityContextHolder.getContext().getAuthentication();
		model.setViewName("/school_admin/rfid/staff_rfid_list");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));

		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "staff_rfid_list");

		List<RfidCards> cards = rfidCardsService.getRfidsByType(type);

		model.addObject("staff_rfid_list", cards);

		// //System.out.println(rfidDaoImpl.getRfidListByType("student"));

		model.addObject("login_role", rolesUtility.getRole(request));
		// System.out.println("inside staffRFID controller");
		return model;
	}

	@RequestMapping(value = "/staff/add", method = RequestMethod.POST)
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
			logger.info("New Rfid number [ " + rfid_number + " ]  has been registered with type Staff");
			return "rfid_inserted";
		}

	}

	@RequestMapping(value = "/staff/update", method = RequestMethod.POST)
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
			logger.info("Rfid number [ " + current_rfid + " ] changed to [ " + new_rfid + " ]  type Staff");
			return "rfid_inserted";
		} else if (reply == false) {
			return "rfid_exists";
		} else {
			return "rfid_notinserted";
		}
	}

	@RequestMapping(value = "/staff/delete", method = RequestMethod.POST)
	public @ResponseBody String studentRfidDelete(HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			System.out.println("inside if");
			return "redirect:/j_spring_security_logout";
		}
		String rfid_number = request.getParameter("rfid_number");

		boolean reply = rfidCardsService.deleteRfid(rfid_number);
		if (reply == true) {
			logger.info("Rfid number [ " + rfid_number + " ]   type Student has been deleted");
			return "rfid_deleted";
		} else {
			return "rfid_notdeleted";
		}
	}

	// search RFID starts
	@RequestMapping(value = "/staff/search", method = RequestMethod.POST)
	public @ResponseBody String searchRfid(HttpServletRequest request, Model model, HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			System.out.println("inside if");
			return "redirect:/j_spring_security_logout";
		}
		String rfid_number = request.getParameter("rfid_number");
		List<RfidCards> rfids = rfidCardsService.searchRfids(rfid_number, type);
		session.setAttribute("rfids", rfids);
		return "/sts/school_admin/rfid/staffSearch";
	}

	@RequestMapping(value = "/staffSearch", method = RequestMethod.GET)
	public ModelAndView staffSearchResponse(ModelAndView model, HttpServletRequest request, HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		// Authentication auth =
		// SecurityContextHolder.getContext().getAuthentication();
		model.setViewName("/school_admin/rfid/staff_rfid_list");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "staff_rfid_list");
		List<RfidCards> rfids = (List<RfidCards>) session.getAttribute("rfids");
		model.addObject("staff_rfid_list", rfids);
		// //System.out.println(rfids);
		if (rfids.isEmpty()) {
			// //System.out.println("null");
			model.addObject("staff_rfid_list", rfidCardsService.getRfidsByType(type));
			model.addObject("error_message", "noMatching");
		}

		return model;
	}

	// search RFID ends

	@RequestMapping(value = "/removeAllStaffRFIDSByTripIds", method = RequestMethod.POST)
	public @ResponseBody String removeAllTripsByTripIds(ModelAndView model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			System.out.println("inside if");
			return "redirect:/j_spring_security_logout";
		}

		String rfids = request.getParameter("rfids");
		// //System.out.println(rfids);
		String rfidsArray[] = rfids.split(",");
		int totalItems = rfidsArray.length;
		// //System.out.println(totalItems);

		for (int i = 1; i <= totalItems; i++) {
			// System.out.println("delete "+rfidsArray[i-1]);
			rfidCardsService.deleteRfid(rfidsArray[i - 1]);
			RfidCards rfidCard = rfidCardsService.getRfidCard(rfidsArray[i - 1]);
			rfidCardsService.deleteRfid(rfidCard);
			logger.info("Rfid [ " + rfidCard.getRfid_number() + " ] deleted from [ " + rfidCard.getType()
					+ " ] cards list");
		}
		return "staff";
	}
}
