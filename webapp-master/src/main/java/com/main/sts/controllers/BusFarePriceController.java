package com.main.sts.controllers;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.main.sts.dao.sql.FareDao;
import com.main.sts.entities.BusFarePriceEntity;
import com.main.sts.entities.Stops;
import com.main.sts.service.StopsService;

@Controller
@RequestMapping(value = "/school_admin/busfareprices")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_GUEST','ROLE_CUSTOMER_SUPPORT','ROLE_OPERATOR')")
public class BusFarePriceController {

    @Autowired
    private StopsService stopservice;

    @Autowired
    private FareDao busFarePriceDao;

    @RequestMapping(value = "/busfareprices_list", method = RequestMethod.GET)
    public ModelAndView busFarePriceHome(ModelAndView model) {
        List<BusFarePriceEntity> busFarePriceEntities = busFarePriceDao.findAll();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
            model.setViewName("redirect:/j_spring_security_logout");
            return model;
        }
        model.setViewName("/school_admin/busfareprices/busfareprices_list");
        model.addObject("login_name", auth.getName());
        model.addObject("current_page", "buses_list");
        model.addObject("busFarePriceEntities", busFarePriceEntities);
        return model;
    }

    @RequestMapping(value = "/busfareprices_list/add", method = RequestMethod.GET)
    public ModelAndView busFarePriceHomeAdd(ModelAndView model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
            model.setViewName("redirect:/j_spring_security_logout");
            return model;
        }
        List<Stops> stops = stopservice.getAllStops();
        model.setViewName("/school_admin/busfareprices/add");
        model.addObject("login_name", auth.getName());
        model.addObject("current_page", "buses_list");
        model.addObject("stops", stops);
        return model;
    }

    @RequestMapping(value = "/busfareprices_list/addaction", method = RequestMethod.POST)
    public ModelAndView busFarePriceHomeAddAction(ModelAndView model, @ModelAttribute BusFarePriceEntity entity) {

        busFarePriceDao.insertEntity(entity);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
            model.setViewName("redirect:/j_spring_security_logout");
            return model;
        }
        List<Stops> stops = stopservice.getAllStops();
        model.setViewName("redirect:/school_admin/busfareprices/busfareprices_list");
        model.addObject("login_name", auth.getName());
        model.addObject("current_page", "buses_list");
        model.addObject("stops", stops);
        return model;
    }

    @RequestMapping(value = "/removbusFarePriceEntitiesById", method = RequestMethod.POST)
    public @ResponseBody String removeAllStudentsById(ModelAndView model, HttpServletRequest request) {
        // System.out.println("inside delete students method");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
            System.out.println("inside if");
            return "redirect:/j_spring_security_logout";
        }

        String stuIdsArray[] = request.getParameter("ids").split(",");
        for (int i = 0; i <= stuIdsArray.length - 1; i++) {

            try {
                int fare_id = Integer.parseInt(stuIdsArray[i].trim());
                busFarePriceDao.deleteFare(fare_id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "busfareprices_list";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ModelAndView busFarePriceHomeUpdate(ModelAndView model, HttpServletRequest requ) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
            model.setViewName("redirect:/j_spring_security_logout");
            return model;
        }
        List<Stops> stops = stopservice.getAllStops();
        int fare_id = Integer.parseInt(requ.getParameter("id"));
        //String queryStr = "from BusFarePriceEntity r where r.fare_id = ?";
        //Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        //parameters.put(0, Integer.parseInt(requ.getParameter("id")));
        BusFarePriceEntity busFarePriceEntity = busFarePriceDao.getFareById(fare_id);
        model.setViewName("/school_admin/busfareprices/update");
        model.addObject("login_name", auth.getName());
        model.addObject("current_page", "buses_list");
        model.addObject("busFarePriceEntity", busFarePriceEntity);
        model.addObject("stops", stops);
        return model;
    }
    
    @RequestMapping(value = "/updateaction", method = RequestMethod.POST)
    public ModelAndView busFarePriceHomUpdateAction(ModelAndView model, @ModelAttribute BusFarePriceEntity entity) {

        busFarePriceDao.updateEntity(entity);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
            model.setViewName("redirect:/j_spring_security_logout");
            return model;
        }
        List<Stops> stops = stopservice.getAllStops();
        model.setViewName("redirect:/school_admin/busfareprices/busfareprices_list");
        model.addObject("login_name", auth.getName());
        model.addObject("current_page", "buses_list");
        model.addObject("stops", stops);
        return model;
    }
}
