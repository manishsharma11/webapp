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
public class StudentRfidController {

	private static final String type = "student";
	private static final Logger logger = Logger.getLogger(StudentRfidController.class);

	@Autowired
	private RfidCardsService rfidCardsService;

	@Autowired
	private RolesUtility rolesUtility;

	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public ModelAndView seniorHomePage(ModelAndView model, HttpServletRequest request) {
		// Authentication auth =
		// SecurityContextHolder.getContext().getAuthentication();
		// System.out.println("inside student rifd controller");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}

		model.setViewName("/school_admin/rfid/student_rfid_list");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());

		model.addObject("login_role", rolesUtility.getRole(request));

		model.addObject("current_page", "student_rfid_list");
		/*
		 * List<RfidEntity> stuRfids = rfidDaoImpl.getRfidListByType(type);
		 * Collections.sort(stuRfids);
		 */

		// //System.out.println(rfidDaoImpl.getRfidListByType("student"));

		/*
		 * Sql database -- start
		 */

		List<RfidCards> cards = rfidCardsService.getRfidsByType(type);

		// System.out.println(cards);
		/*
		 * Sql database -- end
		 */
		model.addObject("student_rfid_list", cards);
		return model;
	}

	@RequestMapping(value = "/student/add", method = RequestMethod.POST)
	public @ResponseBody String studentRfidAdd(HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			// System.out.println("inside if");
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
			logger.info("New Rfid number [ " + rfid_number + " ]  has been registered with type Student");
			return "rfid_inserted";
		}

		/*
		 * Sql database -- end
		 */

		/*
		 * RfidEntity rfid = rfidDaoImpl.getRFIDByNumber(rfid_number); if (rfid
		 * == null) { rfid = new RfidEntity(); //
		 * //System.out.println("RFID "+rfid); rfid.setAllocated_time("none");
		 * rfid.setAllocated_to("none"); rfid.setAvailable("yes");
		 * rfid.setRfid_number(rfid_number); rfid.setType("student");
		 * rfid.setAllocated_person_name("none"); boolean reply =
		 * rfidDaoImpl.saveRfid(rfid);
		 * 
		 * if (reply == true) { logger.info("Student RFID " + rfid_number +
		 * " added"); return "rfid_inserted"; } else { return
		 * "rfid_notinserted"; } } else { return "rfid_exists"; }
		 */

	}

	@RequestMapping(value = "/student/update", method = RequestMethod.POST)
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
			logger.info("Rfid number [ " + current_rfid + " ] changed to [ " + new_rfid + " ]  type Student");
			return "rfid_inserted";
		} else if (reply == false) {
			return "rfid_exists";
		} else {
			return "rfid_notinserted";
		}

	}

	@RequestMapping(value = "/student/delete", method = RequestMethod.POST)
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
	@RequestMapping(value = "/student/search", method = RequestMethod.POST)
	public @ResponseBody String searchRfid(HttpServletRequest request, Model model, HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			return "redirect:/j_spring_security_logout";
		}

		String rfid_number = request.getParameter("rfid_number");
		List<RfidCards> rfids = rfidCardsService.searchRfids(rfid_number, "student");
		session.setAttribute("rfids", rfids);
		// logger.info(rfids);
		return "/sts/school_admin/rfid/studentSearch";
	}

	@RequestMapping(value = "/studentSearch", method = RequestMethod.GET)
	public ModelAndView studentSearchResponse(ModelAndView model, HttpServletRequest request, HttpSession session) {
		// Authentication auth =
		// SecurityContextHolder.getContext().getAuthentication();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		model.setViewName("/school_admin/rfid/student_rfid_list");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "student_rfid_list");

		List<RfidCards> rfids = (List<RfidCards>) session.getAttribute("rfids");
		model.addObject("student_rfid_list", rfids);
		// //System.out.println(rfids);
		if (rfids.isEmpty()) {
			// //System.out.println("null");
			model.addObject("student_rfid_list", rfidCardsService.getRfidsByType(type));
			model.addObject("error_message", "noMatching");
		}

		return model;
	}

	// search RFID ends

	@RequestMapping(value = "/removeAllRFIDSByTripIds", method = RequestMethod.POST)
	public @ResponseBody String removeAllTripsByTripIds(ModelAndView model, HttpServletRequest request) {
		// System.out.println("inside delete rfids method");
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
			rfidCardsService.deleteRfid(rfidsArray[i - 1]);
			RfidCards rfidCard = rfidCardsService.getRfidCard(rfidsArray[i - 1]);
			rfidCardsService.deleteRfid(rfidCard);
			logger.info("Rfid [ " + rfidCard.getRfid_number() + " ] deleted from [ " + rfidCard.getType()
					+ " ] cards list");
		}
		// return null;
		return "student";
	}
}
