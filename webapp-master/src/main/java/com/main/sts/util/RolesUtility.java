package com.main.sts.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class RolesUtility {

	public String getRole(HttpServletRequest request) {

		if (request.isUserInRole("ROLE_ADMIN")) {
			return "ROLE_ADMIN";
		} else if (request.isUserInRole("ROLE_GUEST")) {
			return "ROLE_GUEST";
		} else
			return "ROLE_GUEST";
	}
}
