package com.main.sts.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.main.sts.entities.Roles;
import com.main.sts.entities.Users;
import com.main.sts.entities.Users_Roles;
import com.main.sts.service.StudentsService;
import com.main.sts.service.UserRoleService;
import com.main.sts.service.UserService;
import com.main.sts.service.Users_RolesService;
import com.main.sts.util.MD5PassEncryptionClass;
import com.main.sts.util.RolesUtility;

@Controller
@RequestMapping(value = "/school_admin/roles")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class UsersAndRolesController {

	private static final Logger logger = Logger.getLogger(UsersAndRolesController.class);

	@Autowired
	private UserRoleService roles;
	@Autowired
	private RolesUtility rolesUtility;

	@Autowired
	private UserService userservice;

	@Autowired
	private Users_RolesService user_roleservice;
	@Autowired
	private MD5PassEncryptionClass md;
	@Autowired
	private StudentsService studentservice;

	@RequestMapping(value = "/change", method = RequestMethod.GET)
	public ModelAndView driversHomePage(ModelAndView model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}

		model.setViewName("/school_admin/roles/roles_list");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "roles_list");
		model.addObject("Roles", userservice.getAllUsers());

		model.addObject("login_role", rolesUtility.getRole(request));
		return model;
	}

	// added by sami for rolespage

	@RequestMapping(value = "/getrole", method = RequestMethod.GET)
	public ModelAndView rolePage(ModelAndView model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}

		model.setViewName("/school_admin/user_role/userRoles_list");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "roles_list");
		model.addObject("Roles", roles.getRoles());
		model.addObject("fkey", roles.getRoles());
		// System.out.println(roles.getRoles());
		// System.out.println(roles.getList());
		model.addObject("login_role", rolesUtility.getRole(request));
		return model;
	}

	@RequestMapping(value = "/addnewRole", method = RequestMethod.GET)
	public ModelAndView addNewRole(ModelAndView model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		model.setViewName("/school_admin/user_role/addnewrole");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "addRole");

		model.addObject("login_role", rolesUtility.getRole(request));
		return model;
	}

	@RequestMapping(value = "/addnewRoleAction", method = RequestMethod.POST)
	public ModelAndView addNewRoleAction(ModelAndView model, HttpServletRequest request,
			@ModelAttribute Roles rolesEntity) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		model.setViewName("redirect:/school_admin/roles/getrole");

		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "addRoleAction");

		System.out.println(request.getParameter("login_id"));
		Roles user = roles.isroleExist(request.getParameter("login_id"));
		System.out.println("user is " + user);
		if (user != null) {
			model.setViewName("/school_admin/user_role/addnewrole");
			model.addObject("error", "userNameError");
			model.addObject("error_occured", "yes");
			model.addObject("rolesEntity", rolesEntity);
			return model;
		}
		rolesEntity.setRole_name(request.getParameter("login_id"));
		System.out.println("get name " + rolesEntity.getRole_name());
		roles.insertRole(rolesEntity);
		logger.info("New Role Added " + rolesEntity.getRole_name());
		model.addObject("login_role", rolesUtility.getRole(request));
		return model;
	}

	@RequestMapping(value = "/changeroleOfUsers", method = RequestMethod.GET)
	public ModelAndView changeRoleOfUsers(ModelAndView model, HttpServletRequest request, HttpSession session) {
		// System.out.println("inside changepasswordOfUsers controller");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		model.setViewName("/school_admin/user_role/changerole");

		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		model.addObject("date", formatter.format(new Date()));

		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "Update Role");
		int roleId = Integer.parseInt(request.getParameter("id"));
		// System.out.println("role id "+roleId);
		Roles rolename = roles.getRoleNameById(roleId);
		// System.out.println("role name is "+rolename);
		session.setAttribute("IdInSession", roleId);

		model.addObject("rolename", rolename);

		model.addObject("login_role", rolesUtility.getRole(request));
		return model;
	}

	@RequestMapping(value = "/updatenewRoleAction", method = RequestMethod.POST)
	public ModelAndView updateNewRoleAction(ModelAndView model, HttpServletRequest request, Roles rolesEntity,
			HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		// System.out.println("inside updateRoleAction controller");
		model.setViewName("redirect:/school_admin/roles/getrole");
		String new_name = request.getParameter("login_id");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "updateRoleAction");
		Integer roleId = (Integer) session.getAttribute("IdInSession");
		// System.out.println("role id in update "+roleId);
		Roles role = roles.getRoleNameById(roleId);
		String old_name = role.getRole_name();
		Roles user = roles.isroleExist(new_name);
		if (user != null) {
			model.setViewName("/school_admin/user_role/changerole");
			model.addObject("error", "RoleNameError");
			model.addObject("error_occured", "yes");
			model.addObject("login_name", auth.getName());
			model.addObject("rolename", user);
			model.addObject("login_role", rolesUtility.getRole(request));
			return model;
		}
		role.setRole_name(new_name);

		roles.updateRole(role);
		logger.info("RoleName [ " + old_name + "  ] Updated its role_name to [" + role.getRole_name() + " ]");
		model.addObject("login_role", rolesUtility.getRole(request));
		return model;
	}

	@RequestMapping(value = "/deletenewRole", method = RequestMethod.GET)
	public ModelAndView deleteNewRole(ModelAndView model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		model.setViewName("school_admin/user_role/deleteConfirm");

		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "deleteRole");
		int roleId = Integer.parseInt(request.getParameter("id"));
		Roles entity = roles.getRoleNameById(roleId);
		model.addObject("roleEntity", entity);

		model.addObject("login_role", rolesUtility.getRole(request));
		return model;
	}

	@RequestMapping(value = "/deletenewRoleAction", method = RequestMethod.POST)
	public ModelAndView deleteNewRoleAction(ModelAndView model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		model.setViewName("redirect:/school_admin/roles/getrole");

		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "deleteRoleAction");

		int roleId_hidden = Integer.parseInt(request.getParameter("roleId_hidden"));
		Roles result = roles.getRoleNameById(roleId_hidden);
		if (result == null) {
			model.setViewName("/school_admin/user_role/deleteConfirm");
			model.addObject("error", "RoleNameError");
			model.addObject("error_occured", "yes");
			model.addObject("login_name", auth.getName());
			// model.addObject("rolename", user);
			model.addObject("login_role", rolesUtility.getRole(request));
			return model;
		}
		roles.deleteRole(result.getRole_id());
		logger.info("role [ " + result.getRole_name() + " has been removed by user [ " + auth.getName() + " ]");

		model.addObject("login_role", rolesUtility.getRole(request));
		return model;
	}

	// end

	@RequestMapping(value = "/addRole", method = RequestMethod.GET)
	public ModelAndView addUserRole(ModelAndView model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		model.setViewName("/school_admin/roles/addRole");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "addRole");
		model.addObject("allroles", roles.getRoles());
		model.addObject("login_role", rolesUtility.getRole(request));
		return model;
	}

	@RequestMapping(value = "/addRoleAction1", method = RequestMethod.POST)
	public ModelAndView addUserRoleAction(ModelAndView model, HttpServletRequest request,
			@ModelAttribute Users rolesEntity, BindingResult result) {
		// System.out.println("inside add mthod");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		model.setViewName("redirect:/school_admin/roles/change");

		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "addRoleAction");

		// code to encrypt the password starts

		// code to encrypt the password ends
		String username = request.getParameter("user_name");
		Users user = userservice.isUserNameExist(username);
		// System.out.println(user);
		if (user != null) {
			model.setViewName("/school_admin/roles/addRole");
			model.addObject("login_name", auth.getName());
			model.addObject("login_role", rolesUtility.getRole(request));
			model.addObject("error", "userNameError");
			model.addObject("error_occured", "yes");
			model.addObject("rolesEntity", rolesEntity);
			model.addObject("allroles", roles.getRoles());
			return model;
		}
		// rolesEntity.setLogin_id(uName);
		// System.out.println(request.getParameter("role"));
		userservice.addUser(rolesEntity);
		logger.info("New user added " + rolesEntity);
		Users u = userservice.isUserNameExist(username);
		int u_id = u.getUser_id();
		// System.out.println("uid is " + u_id);
		Users_Roles ur = new Users_Roles();
		ur.setRole_id(Integer.parseInt(request.getParameter("role")));
		ur.setUser_id(u_id);
		user_roleservice.insertIntoUsers_roles(ur);
		logger.info("added into Users_roles " + ur);

		model.addObject("login_role", rolesUtility.getRole(request));
		return model;
	}

	@RequestMapping(value = "/deleteRole", method = RequestMethod.GET)
	public ModelAndView deleteRole(ModelAndView model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		model.setViewName("school_admin/roles/deleteConfirm");

		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "deleteRole");
		int roleId = Integer.parseInt(request.getParameter("id"));
		Users entity = userservice.isUserExist(roleId);
		model.addObject("roleEntity", entity);

		model.addObject("login_role", rolesUtility.getRole(request));
		return model;
	}

	@RequestMapping(value = "/deleteRoleAction", method = RequestMethod.POST)
	public ModelAndView deleteRoleAction(ModelAndView model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		model.setViewName("redirect:/school_admin/roles/change");

		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "deleteRoleAction");

		int userId_hidden = Integer.parseInt(request.getParameter("roleId_hidden"));
		user_roleservice.deleteUser_roles(userId_hidden);
		logger.info("user id [" + userId_hidden + " ] deleted from users_roles");
		userservice.deleteUser(userId_hidden);
		logger.info("user has been removed by user [ " + auth.getName() + " ]");

		model.addObject("login_role", rolesUtility.getRole(request));
		return model;
	}

	@RequestMapping(value = "/updateRole", method = RequestMethod.GET)
	public ModelAndView updateRole(ModelAndView model, HttpServletRequest request, HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		// System.out.println("inside updateRole controller");
		model.setViewName("school_admin/roles/updateForm");

		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "deleteRole");
		int userId = Integer.parseInt(request.getParameter("id"));
		Users entity = userservice.isUserExist(userId);
		// System.out.println(entity);
		model.addObject("roleEntity", entity);
		model.addObject("allroles", roles.getRoles());
		// System.out.println(entity);
		session.setAttribute("roleId", userId);

		model.addObject("login_role", rolesUtility.getRole(request));
		return model;
	}

	@RequestMapping(value = "/updateRoleAction1", method = RequestMethod.POST)
	public ModelAndView updateRoleAction(ModelAndView model, HttpServletRequest request,
			@ModelAttribute Users rolesEntity, BindingResult result) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		// System.out.println("inside updateRoleAction controller");
		model.setViewName("redirect:/school_admin/roles/change");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		String username = request.getParameter("user_name");
		String role = request.getParameter("role");
		// System.out.println(role);
		int roleid = Integer.parseInt(role.trim());
		int userId = (Integer) request.getSession().getAttribute("roleId");
		Users user = userservice.isUserNameExist(username);
		// System.out.println(user);
		if (user != null) {
			if (user.getUser_id() != userId) {
				model.setViewName("/school_admin/roles/updateForm");
				model.addObject("login_name", auth.getName());
				model.addObject("login_role", rolesUtility.getRole(request));
				model.addObject("error", "userNameError");
				model.addObject("error_occured", "yes");
				model.addObject("rolesEntity", rolesEntity);
				model.addObject("allroles", roles.getRoles());
				return model;
			}
		}

		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "updateRoleAction");

		userservice.updateUser(rolesEntity.getFull_name(), rolesEntity.getUser_name(), rolesEntity.getAccess_status(),
				userId);
		logger.info("updated user [" + userId + " ]");
		user_roleservice.updateUsersRoles(userId, roleid);
		logger.info("updated users_roles with new roleid [" + roleid + "]to user [" + userId + " ]");
		model.addObject("login_role", rolesUtility.getRole(request));
		return model;
	}

	@RequestMapping(value = "/changepasswordOfUsers", method = RequestMethod.GET)
	public ModelAndView changePasswordOfUsers(ModelAndView model, HttpServletRequest request, HttpSession session) {
		// System.out.println("inside changepasswordOfUsers controller");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		model.setViewName("/school_admin/roles/changepasswordOfUsers");

		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		// //System.out.println("inside changepassword controller");
		model.addObject("date", formatter.format(new Date()));

		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "changeUserPasswordOfUsers");
		String userId = request.getParameter("id");

		session.setAttribute("IdInSession", userId);
		model.addObject("userId", request.getParameter("id"));
		// //System.out.println("id show:"+request.getParameter("id"));

		model.addObject("login_role", rolesUtility.getRole(request));
		return model;
	}

	@RequestMapping(value = "verifypasswordOfUser", method = RequestMethod.GET)
	public @ResponseBody String verifyPassword(HttpServletRequest req, HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			System.out.println("inside if");
			return "redirect:/j_spring_security_logout";
		}

		System.out.println("inside verifypasswordOfUser in UsersAndRoles");
		try {
			System.out.println(req.getParameter("password"));
			// UsersAndRolesEntity user =
			// usersAndRolesDAOImpl.getPassword(req.getParameter("password"));
			boolean flag = false;
			String rawPass = req.getParameter("password");
			// //System.out.println("raqPassword:"+rawPass);
			String userId = (String) session.getAttribute("IdInSession");
			Users rolesEntity = userservice.isUserExist(Integer.parseInt(userId));

			String hashPass = rolesEntity.getPassword();
			flag = userservice.validatePassword(rawPass, hashPass);
			System.out.println("password matched: " + flag);
			// //System.out.println("HOoooooooo:"+rolesEntity.getPassword());
			if (flag == false) {
				return "notok";
			} else {
				return "ok";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "notok";
		}
	}

	@RequestMapping(value = "changepasswordrequestOfUsers", method = RequestMethod.GET)
	public @ResponseBody String changepasswordrequest(HttpServletRequest req, HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			System.out.println("inside if");
			return "redirect:/j_spring_security_logout";
		}

		// System.out.println("inside changepasswordOF users and roles");
		try {
			String id = (String) session.getAttribute("IdInSession");
			// //System.out.println(session.getAttribute("IdInSession"));
			Users u = userservice.isUserExist(Integer.parseInt(id));
			if (u != null) {
				String enc = md.EncryptText(req.getParameter("c_newpass"));
				u.setPassword(enc);
				userservice.changePassword(u);
				logger.info("password has been changed by user [ " + auth.getName() + " ]");
				return "ok";
			}

			else {
				return "notok";
			}

		} catch (Exception e) {

			return "notok";
		}

	}

	// moved from schooladmincontroller

	@RequestMapping(value = "verifypassword", method = RequestMethod.GET)
	public @ResponseBody String verifyPassword(HttpServletRequest req) {
		try {
			// //System.out.println(req.getParameter("password"));
			String password = req.getParameter("password");
			String rawHashPassword = md.EncryptText(password);

			/*
			 * Login login = new Login(); // //System.out.println(login); if
			 * (login == null) { return "notok"; } else { return "ok"; }
			 */
			return "ok";
		} catch (Exception e) {

			return "notok";
		}

	}

	@RequestMapping(value = "changepasswordrequest", method = RequestMethod.GET)
	public @ResponseBody String changepasswordrequest(HttpServletRequest req) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		try {
			// //System.out.println(req.getParameter("c_newpass"));
			// Login login =
			// loginService.changePassword(req.getParameter("c_pass"),
			/*
			 * // req.getParameter("c_newpass")); Login login = new Login(); //
			 * //System.out.println(login); if (login == null) { return "notok";
			 * } else {
			 */
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

	// end

}
