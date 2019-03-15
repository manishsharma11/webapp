package com.main.sts.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.main.sts.entities.Roles;
import com.main.sts.entities.Users;

@Component
public class MyAuthenticationSuccessHandler extends
		SimpleUrlAuthenticationSuccessHandler {
	
	@Autowired
	private LoginService loginService;
	static Logger log = Logger.getLogger(MyAuthenticationSuccessHandler.class
			.getName());

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String name = auth.getName(); // get logged in username
		
		Roles role = null;
		try {
			Users admin = loginService.getUser(name);

			role = admin.getRole();
			if (role.getRole_name().equals("ROLE_ADMIN")) {
				setDefaultTargetUrl("/school_admin/homepage");
				logger.info("Admin: [ "+admin.getFull_name()+" ] has been logged in ");
			}
			
			/*if (role.equals("ROLE_GUEST")) {
				setDefaultTargetUrl("/school_guest/homepage");
				logger.info("Admin: [ "+name+" ] has been logged in ");
			}*/
			
			else if (role.getRole_name().equals("ROLE_GUEST")) {
				setDefaultTargetUrl("/school_admin/homepage");
				logger.info("Guest: [ "+name+" ] has been logged in ");
			}
			else if (role.getRole_name().equals("ROLE_CUSTOMER_SUPPORT")) {
				setDefaultTargetUrl("/school_admin/homepage");
				logger.info("TEACHER: [ "+admin.getFull_name()+" ] has been logged in ");
			}
			else if(role.getRole_name().equalsIgnoreCase("ROLE_OPERATOR"))
			{
				setDefaultTargetUrl("/school_admin/homepage");
				logger.info("PARENT: [ "+admin.getFull_name()+" ] has been logged in ");
			}
			super.onAuthenticationSuccess(request, response, authentication);
		} catch (NullPointerException e) {

			log.info("User Name [ " + name + " ] doesnot exists....");
		}
		// changeLastLoginTime(username)

	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

}