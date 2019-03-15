package com.main.sts.controllers;

import java.math.BigInteger;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.main.sts.entities.Feedback;
import com.main.sts.service.FeedbackService;
import com.main.sts.util.RolesUtility;
import com.main.sts.util.SystemConstants;

@Controller
@RequestMapping(value = "/school_admin/feedback")
public class FeedbackWebController {
    
    @Autowired
    FeedbackService feedbackService;
    
    @Autowired
    private RolesUtility rolesUtility;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView feedbackPage(ModelAndView model, HttpServletRequest request) {
        List<Feedback> feedbackList = null;
       
        BigInteger count = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
            model.setViewName("redirect:/j_spring_security_logout");
            return model;
        }
        try {
            Integer offset = (request.getParameter("offset") != null
                    && !request.getParameter("offset").trim().isEmpty())
                            ? Integer.parseInt(request.getParameter("offset"))
                            : 1;
            Integer limit = (request.getParameter("limit") != null && !request.getParameter("limit").trim().isEmpty())
                    ? Integer.parseInt(request.getParameter("limit"))
                    : SystemConstants.recordsPerPage;
            count = feedbackService.getFeedbackCount();
        
            feedbackList = feedbackService.getRecordsByPagination(offset, limit);
            model.addObject("feedbackList", feedbackList);
            model.addObject("recordsCount", count);
            model.addObject("limit", limit);
            model.addObject("offset", offset);
            model.addObject("offset", offset);
            model.addObject("recordsPerPage", SystemConstants.recordsPerPage);
            model.addObject("login_name", auth.getName());
            model.addObject("login_role", rolesUtility.getRole(request));
         
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.setViewName("/school_admin/feedback/list");
        
        return model;
     }
    }
