package com.main.sts.controllers;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
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

import com.main.sts.entities.SuggestRoute;
import com.main.sts.entities.TripDetail;
import com.main.sts.entities.Trips;
import com.main.sts.service.TripDetailService;
import com.main.sts.service.TripService;
import com.main.sts.util.DateUtil;
import com.main.sts.util.RolesUtility;
import com.main.sts.util.SystemConstants;

@Controller
@RequestMapping(value = "/school_admin/trip")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_GUEST','ROLE_CUSTOMER_SUPPORT','ROLE_OPERATOR')")
public class TripsController {

    private static Logger logger = Logger.getLogger(TripsController.class);
    @Autowired
    private TripService tripService;
    @Autowired
    private RolesUtility rolesUtility;

    @Autowired
    private TripDetailService tripDetailService;

    @RequestMapping(value = "/trips1", method = RequestMethod.GET)
    public ModelAndView tripssHomePage(ModelAndView model, HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
            model.setViewName("redirect:/j_spring_security_logout");
            return model;
        }
        Integer offset = (request.getParameter("offset") != null && !request.getParameter("offset").trim().isEmpty())
                ? Integer.parseInt(request.getParameter("offset"))
                : 1;
        Integer limit = (request.getParameter("limit") != null && !request.getParameter("limit").trim().isEmpty())
                ? Integer.parseInt(request.getParameter("limit"))
                : SystemConstants.recordsPerPage;
        BigInteger count = tripService.getCountOFRecords(true);
        List<Trips> trips = tripService.getRecordsByPagination(offset, limit);
        System.out.println(trips);
        model.addObject("trips", trips);
        model.addObject("recordsCount", count);
        model.addObject("limit", limit);
        model.addObject("offset", offset);
        model.addObject("recordsPerPage", SystemConstants.recordsPerPage);
       
        model.setViewName("/school_admin/trip/trips_list1");
        DateFormat formatter = new SimpleDateFormat("dd-MMy-yyy");
        model.addObject("date", formatter.format(new Date()));
        model.addObject("login_name", auth.getName());
        model.addObject("current_page", "trips");
        model.addObject("login_role", rolesUtility.getRole(request));
        return model;
    }

    @RequestMapping(value = "/addTrip1", method = RequestMethod.GET)
    public ModelAndView addStop(ModelAndView model, HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
            model.setViewName("redirect:/j_spring_security_logout");
            return model;
        }
        List<TripDetail> tripDetails = tripDetailService.getAllTripDetails();
        model.setViewName("/school_admin/trip/addtrip1");
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        model.addObject("date", formatter.format(new Date()));
        model.addObject("login_name", auth.getName());
        model.addObject("current_page", "trips");
        model.addObject("tripDetails", tripDetails);
        // get all buses
        model.addObject("login_role", rolesUtility.getRole(request));

        return model;
    }

    @RequestMapping(value = "/updateTrip1", method = RequestMethod.GET)
    public ModelAndView updateTrip(ModelAndView model, HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
            model.setViewName("redirect:/j_spring_security_logout");
            return model;
        }
        Trips trip = tripService.getTrip(Integer.parseInt(request.getParameter("id")));
        model.setViewName("/school_admin/trip/updatetrip1");
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        model.addObject("date", formatter.format(new Date()));
        model.addObject("login_name", auth.getName());
        model.addObject("current_page", "trips");
        model.addObject("trip", trip);
        // get all buses
        model.addObject("login_role", rolesUtility.getRole(request));

        return model;
    }

    @RequestMapping(value = "/cancelTrip1", method = RequestMethod.GET)
    public ModelAndView cancelTrip(ModelAndView model, HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
            model.setViewName("redirect:/j_spring_security_logout");
            return model;
        }
        boolean cancelExpiredBookingsAlso = true;
        int trip_id = Integer.parseInt(request.getParameter("id"));
        boolean cancelled = tripService.cancelTrip(trip_id, cancelExpiredBookingsAlso);
        System.out.println("cancelled:" + cancelled);
        Trips trip = tripService.getTrip(trip_id);

        model.setViewName("/school_admin/trip/updatetrip1");
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        model.addObject("date", formatter.format(new Date()));
        model.addObject("login_name", auth.getName());
        model.addObject("current_page", "trips");
        model.addObject("trip", trip);
        model.addObject("trip_cancellation_status", cancelled);
        // get all buses
        model.addObject("login_role", rolesUtility.getRole(request));

        return model;
    }
    
    @RequestMapping(value = "/updatetripaction1", method = RequestMethod.POST)
    public String updateTripAction(ModelAndView model, HttpServletRequest request) {

        Trips trip = tripService.getTrip(Integer.parseInt(request.getParameter("id")));
        trip.setEnabled(Boolean.parseBoolean(request.getParameter("enabled")));
        trip.setTrip_running_date(DateUtil.converStringToDateObject(request.getParameter("date")));

        tripService.updatetrip(Integer.parseInt(request.getParameter("id")), trip);

        return "redirect:trips1";
    }

    @RequestMapping(value = "/deleteTrip1", method = RequestMethod.GET)
    public ModelAndView removeTrip(ModelAndView model, HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
            model.setViewName("redirect:/j_spring_security_logout");
            return model;
        }
        Trips trip = tripService.getTrip(Integer.parseInt(request.getParameter("id")));
        model.setViewName("/school_admin/trip/removetrip1");
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        model.addObject("date", formatter.format(new Date()));
        model.addObject("login_name", auth.getName());
        model.addObject("current_page", "trips");
        model.addObject("trip", trip);
        // get all buses
        model.addObject("login_role", rolesUtility.getRole(request));

        return model;
    }

    @RequestMapping(value = "/deleteaction1", method = RequestMethod.POST)
    public String removeTripAction(ModelAndView model, HttpServletRequest request) {

        tripService.deleteTrip(Integer.parseInt(request.getParameter("id")));

        return "redirect:trips1";
    }
    @RequestMapping(value = "/addtripaction1", method = RequestMethod.POST)
    public String addStopAction(ModelAndView model, HttpServletRequest request) {

        try {
            Integer trip_detail_id = request.getParameter("trip_detail_id") != null ? Integer.parseInt(request
                    .getParameter("trip_detail_id")) : null;
            Boolean enabled = Boolean.parseBoolean(request.getParameter("enabled"));
            if (null != request.getParameter("from") && null != request.getParameter("to")) {
                Set<Date> dates = DateUtil.dateInterval(
                        DateUtil.converStringToDateObject(request.getParameter("from")),
                        DateUtil.converStringToDateObject(request.getParameter("to")));
                Trips trip = null;
                for (Date date : dates) {

                    trip = new Trips();
                    trip.setEnabled(enabled);
                    trip.setSeats_filled(0);
                    trip.setTrip_detail_id(trip_detail_id);
                    trip.setTrip_running_date(date);
                    tripService.insert(trip);
                }

            }
            return "redirect:trips1";
        } catch (Exception e) {

            logger.error("Exception: " + e.getMessage());
            return "redirect:addTrip1";
        }

    }
    
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public @ResponseBody String searchTrips(HttpServletRequest request, Model model, HttpSession session) {
        
      String searchedValue = request.getParameter("search_trips");
      String searchOption = request.getParameter("searchOption");
      System.out.println(searchedValue + "  " + searchOption);
      
      List<Trips> searchTrips = new ArrayList<Trips>();
      if (searchOption.equals("trip_name")) {
        searchTrips = tripService.searchTrips(searchOption, searchedValue);
      } else if (searchOption.equals("trip_running_date")) {
          searchTrips = tripService.searchTrips(searchOption, searchedValue);
      } else if (searchOption.equals("seats_filled")) {
          searchTrips = tripService.searchTrips(searchOption, Integer.valueOf(searchedValue));
        }
      session.setAttribute("searchTrips", searchTrips);
      
      return "/sts/school_admin/trip/searchedTrips";
    }
    
    @RequestMapping(value = "/searchedTrips", method = RequestMethod.GET)
    public ModelAndView tripsSearchResponse(ModelAndView model, HttpServletRequest request, HttpSession session) {

      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
        model.setViewName("redirect:/j_spring_security_logout");
        return model;
      }
      
      model.setViewName("/school_admin/trip/trips_list1");
      DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
      model.addObject("date", formatter.format(new Date()));
      model.addObject("login_name", auth.getName());
      model.addObject("current_page", "list");
    List<Trips> tripsList = (List<Trips>) session.getAttribute("searchTrips");
    System.out.println(tripsList);
      model.addObject("trips", tripsList);
     if (tripsList == null || tripsList.isEmpty()) {
        //model.addObject("trips", tripService.getAllTrips());
        model.addObject("error_message", "noMatching");
      }
     
      model.addObject("current_page", "trips");
      model.addObject("login_role", rolesUtility.getRole(request));
      return model;
    }
}
