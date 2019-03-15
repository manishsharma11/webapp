package com.main.sts.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.main.sts.entities.AdminEmails;
import com.main.sts.entities.Users;
import com.main.sts.messageworkers.MessagesThreadPool;
import com.main.sts.service.AdminEmailService;
import com.main.sts.service.DashBoardSettingsService;
import com.main.sts.service.StudentsService;
import com.main.sts.service.UserRoleService;
import com.main.sts.service.UserService;
import com.main.sts.util.LoginService;
import com.main.sts.util.MD5PassEncryptionClass;
import com.main.sts.util.RolesUtility;

@Controller
@RequestMapping(value = "/school_admin")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_GUEST','ROLE_CUSTOMER_SUPPORT','ROLE_OPERATOR')")
/*
 * @PreAuthorize("isAuthenticated()")
 * 
 * @Secured("ROLE_ADMIN")
 */
public class SchoolAdminController {

	private static final Logger logger = Logger.getLogger(SchoolAdminController.class);
	@Autowired
	LoginService loginService;
	@Autowired
	MD5PassEncryptionClass md;

	@Autowired
	RolesUtility rolesUtility;
	@Autowired
	private UserService userService;
	@Autowired
	MD5PassEncryptionClass password;

	@Autowired
	private DashBoardSettingsService dashBoardSettingsService;
	@Autowired
	private UserRoleService roleimpl;
	@Autowired
	private StudentsService studentservice;
	@Autowired
	private MessagesThreadPool threadpool;
	@Autowired
	private AdminEmailService adminservice;

	// Senior Admin Home page --> By Default it shows Schools List
	@RequestMapping(value = "/homepage", method = RequestMethod.GET)
	public ModelAndView seniorHomePage(ModelAndView model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.setViewName("/school_admin/homepage");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		model.addObject("date", formatter.format(new Date()));
		//System.out.println("school name "+dashBoardSettingsService.getDashBoardSettings());
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "homepage");
		String schoolname=dashBoardSettingsService.getDashBoardSettings().getSchool_name();
		 
		request.getSession().setAttribute("schoolname", schoolname);
		/*
		 * To check which role use is logged in
		 */
		if (request.isUserInRole("ROLE_ADMIN")) {
			model.addObject("login_role", "ROLE_ADMIN");
		}
		//logger.info(model);
		return model;
	}

	// adding for import

	@RequestMapping(value = "/import", method = RequestMethod.GET)
	public ModelAndView addImport(ModelAndView model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		model.setViewName("/school_admin/roles/import");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "import");
		model.addObject("allroles", roleimpl.getRoles());
		model.addObject("login_role", rolesUtility.getRole(request));
		return model;
	}

	@RequestMapping(value = "/execute", method = RequestMethod.POST)
	public ModelAndView executeImport(ModelAndView model, HttpServletRequest request,
			@RequestParam("file") MultipartFile file) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		//System.out.println("import");
		//System.out.println("file upload " + file.getOriginalFilename());
		if (!file.getOriginalFilename().contains(".csv")) {
			model.setViewName("/school_admin/roles/import");
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			model.addObject("date", formatter.format(new Date()));
			model.addObject("login_name", auth.getName());
			model.addObject("current_page", "import");
			model.addObject("allroles", roleimpl.getRoles());
			model.addObject("login_role", rolesUtility.getRole(request));
			model.addObject("fileerror", "yes");
			return model;
		}

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				// String option=request.getParameter("op");
				String rootpath = System.getProperty("catalina.home");
				File dir = new File(rootpath + File.separator + "tmpfiles");
				if (!dir.exists())
					dir.mkdir();
				String path = dir.getAbsolutePath() + File.separator + file.getOriginalFilename();
				String line = "";
				File serverfile = new File(path);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverfile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location=" + serverfile.getAbsolutePath());
				String msg = studentservice.processFile(path,file.getOriginalFilename());
				 List<AdminEmails> emails=adminservice.getAllMails();
				if (msg.equals("no_rfids")) {
					model.setViewName("/school_admin/roles/import");
					DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
					model.addObject("date", formatter.format(new Date()));
					model.addObject("login_name", auth.getName());
					model.addObject("current_page", "import");
					model.addObject("allroles", roleimpl.getRoles());
					model.addObject("login_role", rolesUtility.getRole(request));
					model.addObject("rfiderror", "yes");
					model.addObject("rows", studentservice.cards.size());
					
					return model;
				}
				if (!msg.equals("no_rfids")) {
					model.setViewName("/school_admin/roles/import");
					DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
					model.addObject("date", formatter.format(new Date()));
					model.addObject("login_name", auth.getName());
					model.addObject("current_page", "import");
					model.addObject("allroles", roleimpl.getRoles());
					model.addObject("login_role", rolesUtility.getRole(request));
					model.addObject("importerror", "yes");
					model.addObject("rows", msg);
					
					return model;
				}
				 
				/*  for(AdminEmails a:emails) 
				  {
					  Mail mail=new Mail(a.getEmail(),file.getOriginalFilename()+" Import Result", msg);
					  threadpool.addMessageToQueue(mail); 
					  }*/
				 

			} catch (Exception x) {
				x.printStackTrace();
			}
		}

		model.setViewName("/school_admin/roles/import");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "import");
		model.addObject("allroles", roleimpl.getRoles());
		model.addObject("login_role", rolesUtility.getRole(request));
		return model;
	}

	// end

	// Senior Admin Home page --> By Default it shows Schools List

	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public ModelAndView MapPage(ModelAndView model, HttpServletRequest request) {
		// System.out.println("inside map controller of SchoolAdminController");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.setViewName("/school_admin/map");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		model.addObject("date", formatter.format(new Date()));
		// model.addObject("admin_profile",
		// dashBoardSettingsService.getDashBoardSettings());
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "map");
		model.addObject("admin", dashBoardSettingsService.getDashBoardSettings());
		/*
		 * To check which role use is logged in
		 */

		if (request.isUserInRole("ROLE_ADMIN")) {
			model.addObject("login_role", "ROLE_ADMIN");
		}
		return model;
	}

	// Senior Admin Home page --> By Default it shows Schools List
	@RequestMapping(value = "/bus", method = RequestMethod.GET)
	public ModelAndView busStatus(ModelAndView model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String trip_id = request.getParameter("trip_id");
		model.setViewName("/school_admin/bus_status");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("bus_id", request.getParameter("bus_id"));
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("session", trip_id);
		model.addObject("current_page", "homepage");
		/*
		 * To check which role use is logged in
		 */
		if (request.isUserInRole("ROLE_ADMIN")) {
			// //System.out.println("School admin has logged in .....");
		}
		return model;
	}

	// Senior Admin Home page --> By Default it shows Schools List
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView profile(ModelAndView model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.setViewName("/school_admin/profile");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		model.addObject("date", formatter.format(new Date()));

		model.addObject("login_name", auth.getName());
		// System.out.println("profile "+userService.isUserNameExist(auth.getName()));
		model.addObject("admin_profile", userService.isUserNameExist(auth.getName()));
		// System.out.println(adminProfileDaoImpl.getAdminProfile().getAdmin_name());
		// System.out.println(adminProfileDaoImpl.getAdminProfile().getLogin_id());

		if (request.isUserInRole("ROLE_ADMIN")) {
			model.addObject("login_role", "ROLE_ADMIN");
		}
		return model;
	}

	@RequestMapping(value = "/updateprofile", method = RequestMethod.POST)
	public ModelAndView updateprofileAction(ModelAndView model, HttpServletRequest request, @ModelAttribute Users user) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.setViewName("redirect:/school_admin/profile");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		// System.out.println("User-->" + user);
		if (user.getUser_id() == null) {
			userService.addUser(user);
		} else {
			userService
					.updateUser(user.getFull_name(), user.getUser_name(), user.getAccess_status(), user.getUser_id());
		}
		return model;
	}

	@RequestMapping(value = "verifypassword", method = RequestMethod.GET)
	public @ResponseBody String verifyPassword(HttpServletRequest req) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String password = req.getParameter("password");
			String rawHashPassword = md.EncryptText(password);
			Users user = userService.isUserNameExist(auth.getName());
			// System.out.println(user);

			if (user == null) {
				return "notok";
			} else {

				if (user.getPassword().equals(rawHashPassword))
					return "ok";
				else
					return "notok";
			}

		} catch (Exception e) {

			return "notok";
		}

	}

	@RequestMapping(value = "changepasswordrequest", method = RequestMethod.GET)
	public @ResponseBody String changepasswordrequest(HttpServletRequest req) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		try {

			Users user = userService.isUserNameExist(auth.getName());
			String rawHashPassword = md.EncryptText(req.getParameter("c_newpass"));
			user.setPassword(rawHashPassword);
			userService.changePassword(user);
			logger.info("password has been changed by user [ " + auth.getName() + " ]");
			return "ok";

		} catch (Exception e) {

			return "notok";
		}

	}

	@RequestMapping(value = "/changepassword", method = RequestMethod.GET)
	public ModelAndView changePassword(ModelAndView model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.setViewName("/school_admin/changepassword");

		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		// //System.out.println("inside changepassword controller");
		model.addObject("date", formatter.format(new Date()));

		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "changepassword");

		if (request.isUserInRole("ROLE_ADMIN")) {
			model.addObject("login_role", "ROLE_ADMIN");
		}
		return model;
	}
}