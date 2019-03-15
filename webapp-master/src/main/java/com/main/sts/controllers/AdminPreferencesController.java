/*package com.main.sts.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.main.sts.dao.PasswordEncrypt;
import com.main.sts.model.AdminPreferences;
import com.main.sts.model.EmailsPreference;

@Controller
@RequestMapping(value = "/school_admin/preferences")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_GUEST')")
public class AdminPreferencesController {

	//private static final Logger logger = Logger.getLogger(AdminPreferencesController.class);

	@Autowired
	PasswordEncrypt password;
	@Autowired
	private AdminPreferencesDaoImpl adminPreferencesDaoImpl;


	@RequestMapping(value = "/settings")
	public ModelAndView preferences(ModelAndView model, HttpServletRequest request) {
		// //System.out.println("insid preferences");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.setViewName("/school_admin/preferences");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		model.addObject("date", formatter.format(new Date()));

		model.addObject("login_name", auth.getName());
		model.addObject("adminPreferences", adminPreferencesDaoImpl.getAdminPreferences());

		model.addObject("emails", adminPreferencesDaoImpl.getEmails());
		AdminPreferences preferences = adminPreferencesDaoImpl.getAdminPreferences();
		String plain = password.DecryptText(preferences.getPassword());

		model.addObject("password", plain);
		
		 * To check which role use is logged in
		 
		if (request.isUserInRole("ROLE_ADMIN")) {
			model.addObject("login_role", "ROLE_ADMIN");
		}
		return model;
	}

	@RequestMapping(value = "/updatepreferences", method = RequestMethod.POST)
	public ModelAndView updatepreferenceAction(ModelAndView model, HttpServletRequest request,
			@ModelAttribute AdminPreferences preferences) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		// //System.out.println("insdie updatepreferences");
		model.setViewName("redirect:/school_admin/preferences/settings");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		// //System.out.println("Admin "+admin);

		AdminPreferences admin_profile = adminPreferencesDaoImpl.getAdminPreferences();

		String encrypt = password.EncryptText(preferences.getPassword());
		System.out.println(preferences.getPassword());
		System.out.println("enc " + encrypt);
		preferences.setPassword(encrypt);
		if (admin_profile == null) {
			// System.out.println("null");
			adminPreferencesDaoImpl.savePreferences(preferences);
		} else {
			adminPreferencesDaoImpl.updatePreferences(preferences);
		}

		model.addObject("adminPreferences", adminPreferencesDaoImpl.getAdminPreferences());

		return model;
	}

	@RequestMapping(value = "/addEmails")
	public @ResponseBody String addEmailsToPreferences(ModelAndView model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = request.getParameter("email");
		// System.out.println(email);
		// emails.setEmail(email);
		EmailsPreference emails = new EmailsPreference();
		emails.setEmail(email);

		// model.setViewName("/school_admin/preferences");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("adminPreferences", adminPreferencesDaoImpl.getAdminPreferences());

		adminPreferencesDaoImpl.saveEmailPreferences(emails);
		if (request.isUserInRole("ROLE_ADMIN")) {
			// //System.out.println("School admin has logged in .....");
		}

		return "/sts/school_admin/preferences";
	}

	@RequestMapping(value = "/updateEmail")
	public ModelAndView updateEmail(ModelAndView model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		// //System.out.println("insdie updatepreferences");
		model.setViewName("redirect:/school_admin/preferences");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		// //System.out.println("Admin "+admin);
		//AdminPreferences admin_profile = adminPreferencesDaoImpl.getAdminPreferences();
		model.addObject("adminPreferences", adminPreferencesDaoImpl.getAdminPreferences());
		return model;
	}

	@RequestMapping(value = "/deleteEmail")
	public ModelAndView deleteEmail(ModelAndView model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		// System.out.println("insdie updatepreferences");
		model.setViewName("redirect:/school_admin/preferences");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		// //System.out.println("Admin "+admin);
		// AdminPreferences
		// admin_profile=adminPreferencesDaoImpl.getAdminPreferences();
		// model.addObject("adminPreferences",
		// adminPreferencesDaoImpl.getAdminPreferences());
		String id = request.getParameter("id");
		// System.out.println(id);
		adminPreferencesDaoImpl.removeEmails(id);
		return model;
	}
}
*/