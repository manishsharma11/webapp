package com.main.sts.controllers;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.main.sts.entities.Buses;
import com.main.sts.entities.Drivers;
import com.main.sts.entities.SuggestRoute;
import com.main.sts.entities.SuggestRouteWebDTO;
import com.main.sts.service.RouteService;
import com.main.sts.service.SuggestRoutesService;
import com.main.sts.util.RolesUtility;
import com.main.sts.util.SystemConstants;

@Controller
@RequestMapping(value = "/school_admin/suggested_routes")
//@PreAuthorize("hasAnyRole('ROLE _ADMIN', 'ROLE_GUEST', 'ROLE_CUSTOMER_SUPPORT', 'ROLE_OPERATOR')")
public class SuggestedRoutesWebController {
    
    @Autowired
    private SuggestRoutesService suggestRouteService;
    
    @Autowired
    private RolesUtility rolesUtility;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
     public ModelAndView suggestedRoutesPage(ModelAndView model, HttpServletRequest request)
     {
        List<SuggestRoute> suggestedRoutes = null;
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
            count = suggestRouteService.getSuggestRouteCount();
        
            suggestedRoutes = suggestRouteService.getRecordsByPagination(offset, limit);
            model.addObject("suggestedRoutes", suggestedRoutes);
            model.addObject("recordsCount", count);
            model.addObject("limit", limit);
            model.addObject("offset", offset);
            model.addObject("offset", offset);
            model.addObject("recordsPerPage", SystemConstants.recordsPerPage);
            model.addObject("login_name", auth.getName());
            model.addObject("login_role", rolesUtility.getRole(request));
         for (SuggestRoute suggestedRoute : suggestedRoutes)
         {
             System.out.println(suggestedRoute);
         }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.setViewName("/school_admin/suggested_routes/list");
        
        return model;
        
     }
    
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public @ResponseBody String searchSuggestedRoutes(HttpServletRequest request, Model model, HttpSession session) {
      String search_sr = request.getParameter("search_sr");
      String searchOption = request.getParameter("searchOption");
      System.out.println(search_sr + "  " + searchOption);
      
      List<SuggestRoute> searchSuggestedRoutes = new ArrayList<SuggestRoute>();
      if (searchOption.equals("from_stop")) {
        searchSuggestedRoutes = suggestRouteService.searchSuggestedRoutes(searchOption, search_sr);
      } else if (searchOption.equals("to_stop")) {
        searchSuggestedRoutes = suggestRouteService.searchSuggestedRoutes(searchOption, search_sr);
      } else if (searchOption.equals("start_time_hour")) {
        searchSuggestedRoutes = suggestRouteService.searchSuggestedRoutes(searchOption, String.valueOf(search_sr));
        
        }
      session.setAttribute("searchSuggestedRoutes", searchSuggestedRoutes);
      return "/sts/school_admin/suggested_routes/searchedSuggestions";
    }
    
    @RequestMapping(value = "/searchedSuggestions", method = RequestMethod.GET)
    public ModelAndView suggestRouteSearchResponse(ModelAndView model, HttpServletRequest request, HttpSession session) {

      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
        model.setViewName("redirect:/j_spring_security_logout");
        return model;
      }

      model.setViewName("/school_admin/suggested_routes/list");
      DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
      model.addObject("date", formatter.format(new Date()));
      model.addObject("login_name", auth.getName());
      model.addObject("current_page", "list");
      List<SuggestRoute> searchSuggestedRoutesList = (List<SuggestRoute>) session.getAttribute("searchSuggestedRoutes");
      model.addObject("suggestedRoutes", searchSuggestedRoutesList);
      if (searchSuggestedRoutesList == null || searchSuggestedRoutesList.isEmpty()) {
        model.addObject("suggestedRoutes", suggestRouteService.getAllSuggestedRoutes());
        model.addObject("error_message", "noMatching");
      }
      model.addObject("login_role", rolesUtility.getRole(request));
      return model;
    }
}