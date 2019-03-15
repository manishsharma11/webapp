package com.main.sts.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.main.sts.entities.SavedRoutes;
import com.main.sts.service.SavedRoutesService;

@Controller
@RequestMapping(value = "/school_admin/saved_routes")
public class SavedRoutesWebController {
    
    @Autowired
    private SavedRoutesService savedRoutesService;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
     public ModelAndView savedRoutesHomePage(ModelAndView model, HttpServletRequest request){
        
        List<SavedRoutes> savedRoutes = savedRoutesService.getAllSavedRoutes();
        for(SavedRoutes savedRoute : savedRoutes)
        {
            System.out.println(savedRoute);
        }
        model.setViewName("/school_admin/saved_routes/list");
        model.addObject("saved_routes", savedRoutes);
        return model;
    }

}
