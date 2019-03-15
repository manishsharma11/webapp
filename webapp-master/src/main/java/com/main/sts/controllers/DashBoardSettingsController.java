package com.main.sts.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.encoding.Md4PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.main.sts.entities.AdminEmails;
import com.main.sts.entities.DashboardSettings;
import com.main.sts.service.AdminEmailService;
import com.main.sts.service.DashBoardSettingsService;
import com.main.sts.util.MD5PassEncryptionClass;

@Controller
@RequestMapping(value = "/school_admin/settings")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_GUEST','ROLE_CUSTOMER_SUPPORT','ROLE_OPERATOR')")
public class DashBoardSettingsController {

	private static final Logger logger = Logger.getLogger(DashBoardSettingsController.class);

	@Autowired
	MD5PassEncryptionClass password;
	 
	@Autowired
	private AdminEmailService adminEmailService;
	@Autowired
	private DashBoardSettingsService boardSettingsService;

	@RequestMapping(value = "/view")
	public ModelAndView preferences(ModelAndView model, HttpServletRequest request) {
		// //System.out.println("insid preferences");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.setViewName("/school_admin/settings");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		model.addObject("date", formatter.format(new Date()));

		model.addObject("login_name", auth.getName());
		model.addObject("dashBoardSettings", boardSettingsService.getDashBoardSettings());

		model.addObject("emails", adminEmailService.getAllMails());
		DashboardSettings preferences = boardSettingsService.getDashBoardSettings();
		String plain = password.DecryptText(preferences.getSmtp_password());

		model.addObject("smtp_password", plain);
		/*
		 * To check which role use is logged in
		 */
		if (request.isUserInRole("ROLE_ADMIN")) {
			model.addObject("login_role", "ROLE_ADMIN");
		}
		return model;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updatepreferenceAction(ModelAndView model, HttpServletRequest request,
			@ModelAttribute DashboardSettings dashboardSettings) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		// //System.out.println("insdie updatepreferences");
		model.setViewName("redirect:/school_admin/settings/view");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		boardSettingsService.updateSettings(dashboardSettings);
		logger.info("Dashboard settings have been updated");
		return model;
	}

	@RequestMapping(value = "/adminemail/add")
	public @ResponseBody String addEmailsToPreferences(ModelAndView model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("adminPreferences", boardSettingsService.getDashBoardSettings());

		String email = request.getParameter("email");
		adminEmailService.addEmail(email);
		logger.info("Admin Email [ " + email + " ] has been added");
		return "/sts/school_admin/settings/view";
	}

	/*
	 * @RequestMapping(value = "/adminemail/update") public ModelAndView
	 * updateEmail(ModelAndView model, HttpServletRequest request) {
	 * Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication(); //
	 * //System.out.println("insdie updatepreferences");
	 * model.setViewName("redirect:/school_admin/settings/view"); DateFormat
	 * formatter = new SimpleDateFormat("dd-MM-yyyy"); model.addObject("date",
	 * formatter.format(new Date())); model.addObject("login_name",
	 * auth.getName()); // //System.out.println("Admin "+admin);
	 * AdminPreferences admin_profile =
	 * adminPreferencesDaoImpl.getAdminPreferences();
	 * model.addObject("adminPreferences",
	 * adminPreferencesDaoImpl.getAdminPreferences()); return model; }
	 */

	@RequestMapping(value = "/adminemail/delete")
	public ModelAndView deleteEmail(ModelAndView model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		// System.out.println("insdie updatepreferences");
		model.setViewName("redirect:/school_admin/settings/view");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());

		Integer id = Integer.parseInt(request.getParameter("id"));
		AdminEmails adminEmails = adminEmailService.getMail(id);
		logger.info("Admin Email [ " + adminEmails.getEmail() + " ] has been deleted");
		adminEmailService.deleteEmail(id);
		return model;
	}

}
