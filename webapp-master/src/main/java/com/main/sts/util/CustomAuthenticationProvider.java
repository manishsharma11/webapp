package com.main.sts.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.switchuser.SwitchUserGrantedAuthority;
import org.springframework.stereotype.Component;

import com.main.sts.entities.Roles;
import com.main.sts.entities.Users;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = Logger.getLogger(CustomAuthenticationProvider.class);

    @Autowired
    private MD5PassEncryptionClass encryptionClass;

    @Autowired
    private LoginService loginService;
	

	@Override
	public Authentication authenticate(Authentication authentication) {

		// System.out.println(usersAndRolesDAOImpl);

		String name = authentication.getName().toLowerCase();
		String password = authentication.getCredentials().toString();
		password = encryptionClass.EncryptText(password);
		// System.out.println("User:"+name+" pass: "+password);
		try {
			Users login = loginService.getUser(name);
			System.out.println("inside AuthenticationProvider class:"+login);
			Roles role = null;
			if (login == null) {
				logger.info("Login failed: wrong username or password");
				return null;
			} else {
				// System.out.println(login);
				if (password.equals(login.getPassword())) {
					//if (password.equals(login.getPassword())) {

					/*
					 * if (name.equals(login.getLogin_id()) &&
					 * password.equals(login.getPassword())) {
					 */
					// System.out.println("ok....");
					// logger.info("inside Auth Provider condition checking");
					role = login.getRole();
					List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
					grantedAuths.add(new SwitchUserGrantedAuthority(role.getRole_name(), authentication));
					Authentication auth = new UsernamePasswordAuthenticationToken(name, password, grantedAuths);

					if (login.getAccess_status().equals("Enabled"))
						return auth;
					else
						return null;
				} else {
					logger.info("Login failed: wrong password");
					return null;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return null;
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
}