package com.main.sts.controllers;

import java.util.ArrayList;
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

import com.main.sts.entities.BookingWebDTO;
import com.main.sts.entities.DashboardSettings;
import com.main.sts.service.BookingService;
import com.main.sts.service.DashBoardSettingsService;

@Controller
@RequestMapping(value = "/school_admin")
public class SettingsWebController {
    @Autowired
    private DashBoardSettingsService settingsService;
    
    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public ModelAndView settingssHomePage(ModelAndView model, HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
            model.setViewName("redirect:/j_spring_security_logout");
            return model;
        }
      
        DashboardSettings setting = settingsService.getDashBoardSettings();    
        //List<DashboardSettings> settings = new ArrayList<DashboardSettings>();
        //settings.add(setting);
       // System.out.println(settings);
        
      //  model.setViewName("/school_admin/settings");
        model.setViewName("/mobile_web/booking_confirmation");
        model.addObject("dashBoardSettings", setting);
        return model;
    }
}
