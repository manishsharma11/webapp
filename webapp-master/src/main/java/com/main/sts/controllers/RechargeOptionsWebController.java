package com.main.sts.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.main.sts.entities.Buses;
import com.main.sts.entities.RechargeOptions;
import com.main.sts.service.RechargeOptionsService;
import com.main.sts.util.RolesUtility;

@Controller
@RequestMapping("/school_admin/recharge_options")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_GUEST','ROLE_CUSTOMER_SUPPORT','ROLE_OPERATOR')")
public class RechargeOptionsWebController {
    
    @Autowired
    RechargeOptionsService rechargeOptionsService;
    
    @Autowired
    private RolesUtility usersUtility;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView getRechargeOptionsList(ModelAndView model, HttpServletRequest request)
    {
        List<RechargeOptions> rechargeOptionsList = null;
        try{
            rechargeOptionsList = rechargeOptionsService.findAll();
            model.addObject("rechargeOptionsList", rechargeOptionsList);
            for(RechargeOptions rechargeOptions : rechargeOptionsList){
                System.out.println(rechargeOptions);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.setViewName("/school_admin/recharge_options/list");
        
        return model;
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addARecharge(ModelAndView model, HttpServletRequest request) {

      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
        model.setViewName("redirect:/j_spring_security_logout");
        return model;
      }

      model.setViewName("/school_admin/recharge_options/add_new_recharge");
      DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
      model.addObject("date", formatter.format(new Date()));
      model.addObject("login_name", auth.getName());
      model.addObject("current_page", "list");

      //model.addObject("drivers", driverservie.listDrivers());
      // System.out.println(driverservie.listDrivers());
      model.addObject("login_role", usersUtility.getRole(request));

      return model;
    }
    
    @RequestMapping(value = "/add_recharge_action", method = RequestMethod.POST)
    public ModelAndView addRechargeAction(ModelAndView model, HttpServletRequest request, @ModelAttribute RechargeOptions rechargeEntity) {
      if (request.isUserInRole("ROLE_ADMIN")) {
        model.addObject("login_role", "ROLE_ADMIN");
      }

      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
        model.setViewName("redirect:/j_spring_security_logout");
        return model;
      }
      // System.out.println(busEntity);
      RechargeOptions recharge = rechargeOptionsService.getRechargeOptions(rechargeEntity.getId());
      if (recharge != null) {
        model.setViewName("/school_admin/recharge_options/add_new_recharge");
        model.addObject("login_role", usersUtility.getRole(request));
       // model.addObject("drivers", driverservie.listDrivers());
        model.addObject("message", "recharge_exists");
        model.addObject("recharge", rechargeEntity);
        model.addObject("id", rechargeEntity.getId());

        return model;
      }
      /*int status = 1;
      int did = Integer.parseInt(request.getParameter("driver_name"));
      busEntity.setDriver(driverservie.getDriver(did));
      busEntity.setDriverId(driverservie.getDriver(did).getId());*/
      rechargeOptionsService.insertARechargeOption(rechargeEntity);
      // System.out.println(busEntity.getDriver().getDriver_name());
     // logger.info("New Bus [ " + busEntity.getBus_licence_number() + " ] got added");
      return new ModelAndView("redirect:/school_admin/recharge_options/list");

    }  
    
}
