package com.main.sts.controllers;

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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.main.sts.entities.Session;
import com.main.sts.service.SessionService;
import com.main.sts.util.RolesUtility;

@Controller
@RequestMapping(value="/school_admin/session")
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CUSTOMER_SUPPORT','ROLE_OPERATOR','ROLE_GUEST')")
public class SessionController 
{

	private static final Logger logger = Logger.getLogger(Session.class);
	@Autowired
	private SessionService sessionservice;
	@Autowired
	private RolesUtility rolesutility;
	 
	@RequestMapping(value="/sessionlist", method=RequestMethod.GET)
	public ModelAndView getSessionList(HttpServletRequest request,ModelAndView modelAndView)
	{
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		if(auth!=null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()==false)
		{
			modelAndView.setViewName("redirect:/j_spring_security_logout");
			return modelAndView;
		}
		DateFormat formater=new SimpleDateFormat("dd-MM-yyyy");
		modelAndView.setViewName("school_admin/session/session_list");
		modelAndView.addObject("login_role", rolesutility.getRole(request));
		modelAndView.addObject("login_name", auth.getName());
		modelAndView.addObject("date", formater.format(new Date()));
		modelAndView.addObject("current_page", "SessionList");
		
		List<Session> session=sessionservice.getSessions();
		modelAndView.addObject("session", session);
		return modelAndView;
	}
	
	@RequestMapping(value="/addsession",method=RequestMethod.GET)
	public ModelAndView addSession(HttpServletRequest request,ModelAndView model)
	{
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		if(auth!=null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()==false)
		{
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		
		model.setViewName("/school_admin/session/addsession");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "add_session");
		model.addObject("login_role", rolesutility.getRole(request));

		return model;
		
		
	}
	
	@RequestMapping(value="/addsessionaction",method=RequestMethod.GET)
	public String sessionAdded(HttpServletRequest request,final Model model,@ModelAttribute Session session)
	{
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		if(auth!=null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()==false)
		{
			 
			return "redirect:/j_spring_security_logout";
		}
		List<Session> all=sessionservice.getSessions();
		int starting_time=(session.getStarting_time_hours() * 60)+session.getStarting_time_minutes();
		int end_time=session.getEnd_time_hours()*60 +session.getEnd_time_minutes();
		
		if(session.getStarting_time_hours()>=session.getEnd_time_hours())
		{
			model.addAttribute("session", session);
		 	model.addAttribute("error_message", "higherstart");
			model.addAttribute("errorOccured", "yes");
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			model.addAttribute("date", formatter.format(new Date()));
			model.addAttribute("login_name", auth.getName());
			model.addAttribute("current_page", "add_session");
			model.addAttribute("login_role", rolesutility.getRole(request));
			 return "/school_admin/session/addsession";
		}
		
		for(Session sessions:all)
		{
			int old_starting_time=(sessions.getStarting_time_hours() * 60)+sessions.getStarting_time_minutes();
			int old_end_time=sessions.getEnd_time_hours()*60 +sessions.getEnd_time_minutes();
			if((starting_time>=old_starting_time && starting_time<=old_end_time) ||(end_time>=old_starting_time && end_time<=old_end_time) )
			{
				model.addAttribute("session", session);
			 	model.addAttribute("error_message", "duplicate session");
				model.addAttribute("errorOccured", "yes");
				DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				model.addAttribute("date", formatter.format(new Date()));
				model.addAttribute("login_name", auth.getName());
				model.addAttribute("current_page", "add_session");
				model.addAttribute("login_role", rolesutility.getRole(request));
				 return "/school_admin/session/addsession";
			}
			
		}
			sessionservice.insertSession(session);
			logger.info("Session Added by Name "+session.getSession_name());
			return "redirect:/school_admin/session/sessionlist";
	}
}
