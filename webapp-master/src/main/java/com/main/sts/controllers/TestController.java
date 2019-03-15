package com.main.sts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.main.sts.util.RolesUtility;

@Controller
@RequestMapping(value = "/school_admin")
public class TestController {

	@Autowired
	private RolesUtility rolesUtility;

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public ModelAndView errorPage(ModelAndView model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		model.setViewName("/school_admin/error");
		return model;
	}

}
