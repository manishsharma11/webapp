package com.main.sts.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.main.sts.entities.Buses;
import com.main.sts.entities.RouteStops;
import com.main.sts.entities.Routes;
import com.main.sts.entities.TripDetail;
import com.main.sts.service.BusesService;
import com.main.sts.service.RouteService;
import com.main.sts.service.RouteStopsService;
import com.main.sts.service.TripDetailService;
import com.main.sts.util.ErrorCodes;
import com.main.sts.util.RolesUtility;

@Controller
@RequestMapping(value = "/school_admin/trip")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_GUEST','ROLE_CUSTOMER_SUPPORT','ROLE_OPERATOR')")
/*
 * @PreAuthorize("isAuthenticated()")
 * 
 * @Secured("ROLE_ADMIN")
 */
public class TripsDetailsControllers {

    private static final Logger logger = Logger.getLogger(TripsDetailsControllers.class);

    @Autowired
    private RolesUtility rolesUtility;

    // PLEASE NOTE: DONT DEFINE ANY TRIPSERVICE In THIS CLASS. AS THAT WILL
    // BREAK THINGS.
    @Autowired
    private TripDetailService tripDetailService;

    @Autowired
    private BusesService busservice;

    @Autowired
    private RouteService routeservice;

    @Autowired
    private RouteStopsService routestopservice;

    @RequestMapping(value = "/trips", method = RequestMethod.GET)
    public ModelAndView tripssHomePage(ModelAndView model, HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
            model.setViewName("redirect:/j_spring_security_logout");
            return model;
        }

        // System.out.println("inside trips controller:");
        // Authentication auth =
        // SecurityContextHolder.getContext().getAuthentication();
        model.setViewName("/school_admin/trip/trips_list");
        DateFormat formatter = new SimpleDateFormat("dd-MMy-yyy");
        model.addObject("date", formatter.format(new Date()));
        model.addObject("login_name", auth.getName());
        List<TripDetail> tripDetails = tripDetailService.getAllTripDetails();
        Collections.sort(tripDetails);
        model.addObject("trips", tripDetails);
        model.addObject("current_page", "trips");
        // //System.out.println("inside tripcontroller");

        model.addObject("login_role", rolesUtility.getRole(request));
        return model;
    }

    // search trips
    @RequestMapping(value = "/trip/search", method = RequestMethod.POST)
    public @ResponseBody String searchStudent(HttpServletRequest request, Model model, HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
            System.out.println("inside if");
            return "redirect:/j_spring_security_logout";
        }

        String rfid_number = request.getParameter("rfid_number");
        String searchOption = request.getParameter("searchOption");
        System.out.println("inside controller" + rfid_number + "  " + searchOption);
        List<TripDetail> searchtrips = new ArrayList<TripDetail>();
        if (searchOption.equals("trip_name")) {
            searchtrips = tripDetailService.searchTripDetails(searchOption, rfid_number);
        } else if (searchOption.equals("trip_type")) {
            searchtrips = tripDetailService.searchTripDetails(searchOption, rfid_number);
        } else if (searchOption.equals("routeid")) {
            List<Routes> routes = routeservice.searchRoutes("route_name", rfid_number);
            for (Routes routes2 : routes) {
                TripDetail tripDetail = tripDetailService.getTripRouteIdOrBusId(routes2.getId(), 0);
                searchtrips.add(tripDetail);
            }

        } else if (searchOption.equals("busid")) {

            List<Buses> routes = busservice.searchBuses("bus_licence_number", rfid_number);
            for (Buses routes2 : routes) {
                TripDetail tripDetail = tripDetailService.getTripRouteIdOrBusId(routes2.getBus_id(), 1);
                searchtrips.add(tripDetail);
            }

        }

        session.setAttribute("searchtrips", searchtrips);
        return "/sts/school_admin/trip/tripSearch";
    }

    @RequestMapping(value = "/tripSearch", method = RequestMethod.GET)
    public ModelAndView studentSearchResponse(ModelAndView model, HttpServletRequest request, HttpSession session) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
            model.setViewName("redirect:/j_spring_security_logout");
            return model;
        }

        // Authentication auth =
        // SecurityContextHolder.getContext().getAuthentication();
        model.setViewName("/school_admin/trip/trips_list");
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        model.addObject("date", formatter.format(new Date()));
        model.addObject("login_name", auth.getName());
        model.addObject("current_page", "students");

        @SuppressWarnings("unchecked")
        List<TripDetail> searchStudentsList = (List<TripDetail>) session.getAttribute("searchtrips");
        model.addObject("trips", searchStudentsList);

        // System.out.println(searchStudentsList);
        if (searchStudentsList == null || searchStudentsList.isEmpty())

        {
            // System.out.println("trips empty");
            model.addObject("trips", tripDetailService.getAllTrips());
            model.addObject("error_message", "noMatching");
        }

        model.addObject("login_role", rolesUtility.getRole(request));

        return model;
    }

    @RequestMapping(value = "/addTrip", method = RequestMethod.GET)
    public ModelAndView addStop(ModelAndView model, HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
            model.setViewName("redirect:/j_spring_security_logout");
            return model;
        }

        // Authentication auth =
        // SecurityContextHolder.getContext().getAuthentication();
        model.setViewName("/school_admin/trip/addtrip");
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        model.addObject("date", formatter.format(new Date()));
        model.addObject("login_name", auth.getName());
        model.addObject("current_page", "trips");
        // get all buses
        model.addObject("buses", busservice.getBuses());
        model.addObject("login_role", rolesUtility.getRole(request));

        return model;
    }

    @RequestMapping(value = "/addtripaction", method = RequestMethod.GET)
    public String addRouteAction(final Model model, HttpServletRequest request,
            @ModelAttribute("trip") TripDetail tripDetails, HttpSession session) throws ParseException {

        // System.out.println("inside addtripaction controller");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
            // System.out.println("inside if");
            return "redirect:/j_spring_security_logout";
        }

        System.out.println(request.getParameter("route_name") + " " + request.getParameter("bus_id"));
        String bus_id = request.getParameter("bus_id");

        Buses busEntity = busservice.getBusByLicence(bus_id);
        String fs = (String) session.getAttribute("fs");
        String ls = (String) session.getAttribute("ls");
        model.addAttribute("login_role", rolesUtility.getRole(request));

        // validation for stop name duplicate starts
        TripDetail tripEntity = tripDetailService.getTripDetailsByName(tripDetails.getTrip_name());
        String arr = request.getParameter("route_name");
        String a[] = arr.split(";;");
        int routeId = Integer.parseInt(a[0]);
        // //System.out.println(busEntity);
        // trip.setTotal_seats(busEntity.getBus_capacity());
        // trip.setSeats_filled(0);
        tripDetails.setBusid(busEntity.getBus_id());
        tripDetails.setRouteid(routeId);
        tripDetails.setBus(busEntity);
        tripDetails.setRoutes(routeservice.getRoute(routeId));
        if (tripEntity != null) {
            // System.out.println("tripEntity"+tripEntity);
            model.addAttribute("trip", tripDetails);
            model.addAttribute("error_message", "tripDup");
            model.addAttribute("errorOccured", "yes");
            model.addAttribute("fs", fs);
            model.addAttribute("ls", ls);
            model.addAttribute("buses", busservice.getBuses());
            return "/school_admin/trip/addtrip";

        }
        // validation for stop timings
        int tripStartTime = tripDetails.getTrip_start_time_hours() * 60 + tripDetails.getTrip_start_time_minutes();
        int tripEndTime = tripDetails.getTrip_end_time_hours() * 60 + tripDetails.getTrip_end_time_minutes();

        List<TripDetail> tripsDetail = tripDetailService.getTripDetailByBusId(busEntity.getBus_id());
        logger.info("trips " + tripsDetail);

        for (TripDetail tripSingle : tripsDetail) {
            int triptripSingleStartTime = tripSingle.getTrip_start_time_hours() * 60
                    + tripSingle.getTrip_start_time_minutes();
            int triptripSingleEndTime = tripSingle.getTrip_end_time_hours() * 60
                    + tripSingle.getTrip_end_time_minutes();

            // TODO: for now disabling this

//            if (tripStartTime >= triptripSingleStartTime && tripStartTime <= triptripSingleEndTime) {
//                System.out.println("tripEntity" + tripDetails);
//                model.addAttribute("trip", tripDetails);
//                model.addAttribute("error_message", "tripStartTime");
//                model.addAttribute("errorOccured", "yes");
//                model.addAttribute("fs", fs);
//                model.addAttribute("ls", ls);
//                model.addAttribute("buses", busservice.getBuses());
//                return "/school_admin/trip/addtrip";
//            }
            // TODO: for now disabling this
//            if (tripEndTime >= triptripSingleStartTime && tripEndTime <= triptripSingleEndTime) {
//                // System.out.println("tripEntity"+tripEntity);
//                model.addAttribute("trip", tripDetails);
//                model.addAttribute("error_message", "tripEndTime");
//                model.addAttribute("errorOccured", "yes");
//                model.addAttribute("fs", fs);
//                model.addAttribute("ls", ls);
//                model.addAttribute("buses", busservice.getBuses());
//                return "/school_admin/trip/addtrip";
//
//            }
            
            // TODO: for now disabling this

//            if (tripStartTime < triptripSingleStartTime && tripEndTime > triptripSingleEndTime) {
//                // System.out.println("tripEntity"+tripEntity);
//                model.addAttribute("trip", tripDetails);
//                model.addAttribute("error_message", "prevTrip");
//                model.addAttribute("errorOccured", "yes");
//                model.addAttribute("fs", fs);
//                model.addAttribute("ls", ls);
//                model.addAttribute("buses", busservice.getBuses());
//                return "/school_admin/trip/addtrip";
//
//            }
        }
        /*
         * String strtTime[] = firstStopTimings.split(":"); int startStopHours =
         * Integer.parseInt(strtTime[0]); int startStopMinutes =
         * Integer.parseInt(strtTime[1]); int totalStartStopTime =
         * startStopHours * 60 + startStopMinutes;
         * 
         * String secndTime[] = secondStopTimings.split(":"); int endStopHours =
         * Integer.parseInt(secndTime[0]); int endStopMinutes =
         * Integer.parseInt(secndTime[1]); int totalEndStopTime = endStopHours *
         * 60 + endStopMinutes;
         */

        /*
         * if (tripStartTime >= totalStartStopTime) {
         * 
         * model.addAttribute("trip", tripDetails);
         * model.addAttribute("error_message", "StartTripError");
         * model.addAttribute("errorOccured", "yes"); model.addAttribute("fs",
         * fs); model.addAttribute("buses", busservice.getBuses());
         * model.addAttribute("ls", ls); return "/school_admin/trip/addtrip"; }
         * 
         * if (tripEndTime <= totalEndStopTime) {
         * System.out.println("tripEntity" + tripDetails);
         * model.addAttribute("trip", tripDetails);
         * model.addAttribute("error_message", "EndTripError");
         * model.addAttribute("errorOccured", "yes"); model.addAttribute("fs",
         * fs); model.addAttribute("ls", ls); model.addAttribute("buses",
         * busservice.getBuses()); return "/school_admin/trip/addtrip"; }
         */

        /*
         * school start time should be in between trip start time and stop1
         * timings
         */
        /*
         * int schoolHours = tripDetails.getSchool_time_hours(); int
         * schoolMinutes = tripDetails.getSchool_time_minutes(); int
         * schoolTotalMinutes = schoolHours * 60 + schoolMinutes;
         * 
         * if (tripDetails.getTrip_type().equals("Pick Up")) { //
         * System.out.println("inside pick up");
         * 
         * if (schoolTotalMinutes <= totalEndStopTime || schoolTotalMinutes >=
         * tripEndTime) { // System.out.println("tripEntity"+trip);
         * model.addAttribute("trip", tripDetails);
         * model.addAttribute("error_message", "schoolArrivalTimeError");
         * model.addAttribute("errorOccured", "yes"); //
         * System.out.println("saterror:"+trip.getRoutes().getRoute_name());
         * model.addAttribute("fs", fs); model.addAttribute("ls", ls);
         * model.addAttribute("routes", routeservice.getAllRoutes());
         * model.addAttribute("buses", busservice.getBuses()); return
         * "/school_admin/trip/addtrip"; } }
         * 
         * if (tripDetails.getTrip_type().equals("Drop Off")) { //
         * System.out.println("inside Drop Off"); if (schoolTotalMinutes <=
         * tripStartTime || schoolTotalMinutes >= totalStartStopTime) { //
         * System.out.println("tripEntity"+trip); model.addAttribute("trip",
         * tripDetails); model.addAttribute("error_message",
         * "schoolStartTimeError"); model.addAttribute("errorOccured", "yes");
         * model.addAttribute("fs", fs); model.addAttribute("ls", ls);
         * model.addAttribute("buses", busservice.getBuses()); return
         * "/school_admin/trip/addtrip"; }
         * 
         * }
         */

        try {
            tripDetailService.insertTripDetail(tripDetails);
            logger.info("New trip [" + tripDetails.getTrip_name() + " ] got added");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // if route is assigned to a trip then it should not be deleted.
        String status = routeservice.getRoute(routeId).getIsAssigned();
        int i = Integer.parseInt(status);

        routeservice.updateStatus(routeId, String.valueOf(++i));
        int bs = busEntity.getBus_allotted();
        busservice.updateBusStatus(busEntity, ++bs);

        return "redirect:/school_admin/trip/trips";
    }

    @RequestMapping(value = "/updateTrip", method = RequestMethod.GET)
    public ModelAndView updateTrip(ModelAndView model, HttpServletRequest request, HttpSession session) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
            model.setViewName("redirect:/j_spring_security_logout");
            return model;
        }

        // Authentication auth =
        // SecurityContextHolder.getContext().getAuthentication();
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        model.addObject("date", formatter.format(new Date()));
        model.addObject("login_name", auth.getName());
        model.addObject("current_page", "trips");

        int trip_detail_id = Integer.parseInt(request.getParameter("id"));
        TripDetail tripDetail = tripDetailService.getTripDetail(trip_detail_id);
        int routeIdOld = tripDetail.getRoutes().getId();
        // String routeNameOld=trip.getRoute_name();
        model.addObject("trip", tripDetail);
        // System.out.println("%%%%%%%%%%%%%%"+trip);
        model.setViewName("/school_admin/trip/updatetrip");
        // get all buses
        model.addObject("buses", busservice.getBuses());
        // model.addObject("routes", routeservice.getAllRoutes());
        // System.out.println("update: routeId:"+routeIdOld);
        session.setAttribute("tripId", trip_detail_id);
        session.setAttribute("trip", tripDetail);
        session.setAttribute("routeIdOld", routeIdOld);

        model.addObject("OroutId", routeIdOld);

        model.addObject("login_role", rolesUtility.getRole(request));

        return model;
    }

    @RequestMapping(value = "/updateTripAction", method = RequestMethod.GET)
    public String updateTripAction(Model model, HttpServletRequest request,
            @ModelAttribute("trip") TripDetail tripDetails, HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
            return "redirect:/j_spring_security_logout";
        }

        // System.out.println("##########"+auth.getName()+"********authenticated:
        // "+b);
        TripDetail tripOld1 = tripDetailService.getTripDetail(Integer.parseInt(request.getParameter("trip_id")));
        // session.removeAttribute("trip");
        int tripId = (Integer) session.getAttribute("tripId");

        // System.out.println();
        // Authentication auth =
        // SecurityContextHolder.getContext().getAuthentication();
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        model.addAttribute("date", formatter.format(new Date()));
        model.addAttribute("login_name", auth.getName());
        model.addAttribute("current_page", "trips");

        // System.out.println("*********"+request.getParameter("route_name"));
        if (request.getParameter("route_name").contains(";;")) {
            // System.out.println("dropdown selected");
            String route[] = request.getParameter("route_name").split(";;");
            tripDetails.setRouteid((Integer.parseInt(route[0])));
            // trip.setRoute_name(route[1]);
        }

        else {
            // if route name checkbox not selected'
            tripDetails.setTrip_type(tripOld1.getTrip_type());
            tripDetails.setRouteid((tripOld1.getRouteid()));
            // logger.info("trip route d "+trip.getRouteid());
            tripDetails.setRoutes(routeservice.getRoute(tripOld1.getRouteid()));
            tripDetails.setBus(busservice.getBusById(tripOld1.getBusid()));
            // trip.setRoute_name(tripOld1.getRoute_name());
        }

        // validation for stop timings
        int tripStartTime = tripDetails.getTrip_start_time_hours() * 60 + tripDetails.getTrip_start_time_minutes();
        int tripEndTime = tripDetails.getTrip_end_time_hours() * 60 + tripDetails.getTrip_end_time_minutes();
        String bus_id = request.getParameter("bus_id");

        // System.out.println(busEntity);
        if (tripOld1.getBus().getBus_licence_number() != bus_id) {
            Buses busEntity = busservice.getBusByLicence(bus_id);
            int status = busEntity.getBus_allotted();
            busservice.updateBusStatus(busEntity, ++status);
            tripDetails.setBusid(busEntity.getBus_id());
            Buses old = busservice.getBusByLicence(tripOld1.getBus().getBus_licence_number());
            int ol = old.getBus_allotted();
            busservice.updateBusStatus(old, --ol);
        } else {
            Buses busEntity = busservice.getBusByLicence(bus_id);

            tripDetails.setBusid(busEntity.getBus_id());
        }

        List<TripDetail> tripsList = tripDetailService.getAllTrips();

        for (TripDetail td : tripsList) {
            if (!(td.getId() == (tripId))) {
                String tripName = td.getTrip_name();
                String tripOldName = tripDetails.getTrip_name();
                if (tripName.equals(tripOldName)) {
                    model.addAttribute("error_message", "tripDup");
                    model.addAttribute("errorOccured", "yes");
                    model.addAttribute("trip", tripDetails);
                    // model.addAttribute("fs", fs);
                    // model.addAttribute("ls", ls);
                    model.addAttribute("buses", busservice.getBuses());
                    // model.addAttribute("/school_admin/trip/updatetrip");
                    return "/school_admin/trip/updatetrip";
                }
            }
        }
        // List<Trips> trips =
        // tripservice.getTripsByBusId(busEntity.getBus_id());
        List<TripDetail> trips = tripDetailService.getTripDetailByBusId(tripDetails.getBusid());
        // System.out.println("trip by bus: " + trips);

        for (TripDetail tripSingle : trips) {
            if (!(tripSingle.getId() == (tripId))) {
                int triptripSingleStartTime = tripSingle.getTrip_start_time_hours() * 60
                        + tripSingle.getTrip_start_time_minutes();
                int triptripSingleEndTime = tripSingle.getTrip_end_time_hours() * 60
                        + tripSingle.getTrip_end_time_minutes();

                if (tripStartTime >= triptripSingleStartTime && tripStartTime <= triptripSingleEndTime) {
                    // System.out.println("tripEntity"+trip);
                    model.addAttribute("trip", tripDetails);
                    model.addAttribute("error_message", "tripStartTime");
                    model.addAttribute("errorOccured", "yes");
                    model.addAttribute("buses", busservice.getBuses());
                    return "/school_admin/trip/updatetrip";
                }
                if (tripEndTime >= triptripSingleStartTime && tripEndTime <= triptripSingleEndTime) {
                    // System.out.println("tripEntity"+trip);
                    model.addAttribute("trip", tripDetails);
                    model.addAttribute("error_message", "tripEndTime");
                    model.addAttribute("errorOccured", "yes");
                    model.addAttribute("buses", busservice.getBuses());
                    return "/school_admin/trip/updatetrip";
                }

            }
        } // for

        /*
         * String strtTime[] = firstStopTimings.split(":"); int startStopHours =
         * Integer.parseInt(strtTime[0]); int startStopMinutes =
         * Integer.parseInt(strtTime[1]); int totalStartStopTime =
         * startStopHours * 60 + startStopMinutes;
         * 
         * String secndTime[] = secondStopTimings.split(":"); int endStopHours =
         * Integer.parseInt(secndTime[0]); int endStopMinutes =
         * Integer.parseInt(secndTime[1]); int totalEndStopTime = endStopHours *
         * 60 + endStopMinutes;
         * 
         * if (tripStartTime >= totalStartStopTime) { //
         * System.out.println("tripEntity"+trip); model.addAttribute("trip",
         * tripDetails); model.addAttribute("error_message", "StartTripError");
         * model.addAttribute("errorOccured", "yes");
         * model.addAttribute("buses", busservice.getBuses()); return
         * "/school_admin/trip/updatetrip"; }
         * 
         * if (tripEndTime <= totalEndStopTime) {
         * System.out.println("tripEntity" + tripOld1);
         * model.addAttribute("trip", tripOld1);
         * model.addAttribute("error_message", "EndTripError");
         * model.addAttribute("errorOccured", "yes");
         * model.addAttribute("buses", busservice.getBuses()); return
         * "/school_admin/trip/updatetrip"; }
         * 
         * int schoolHours = tripDetails.getSchool_time_hours(); int
         * schoolMinutes = tripDetails.getSchool_time_minutes(); int
         * schoolTotalMinutes = schoolHours * 60 + schoolMinutes;
         * 
         * if (tripDetails.getTrip_type().equals("Pick Up")) {
         * 
         * if (schoolTotalMinutes <= totalEndStopTime || schoolTotalMinutes >=
         * tripEndTime) { // System.out.println("tripEntity"+trip);
         * 
         * model.addAttribute("error_message", "schoolArrivalTimeError");
         * model.addAttribute("errorOccured", "yes"); model.addAttribute("trip",
         * tripDetails); System.out.println(tripDetails);
         * model.addAttribute("buses", busservice.getBuses()); return
         * "/school_admin/trip/updatetrip"; } }
         * 
         * if (tripDetails.getTrip_type().equals("Drop Off")) { //
         * System.out.println("inside Drop Off"); if (schoolTotalMinutes <=
         * tripStartTime || schoolTotalMinutes >= totalStartStopTime) { //
         * System.out.println("tripEntity"+trip); model.addAttribute("trip",
         * tripDetails); model.addAttribute("error_message",
         * "schoolStartTimeError"); model.addAttribute("errorOccured", "yes");
         * model.addAttribute("buses", busservice.getBuses()); return
         * "/school_admin/trip/updatetrip"; }
         * 
         * }
         */

        int routeIdOld = (Integer) session.getAttribute("routeIdOld");
        int currentRouteId = 0;
        String arr = request.getParameter("route_name");
        // System.out.println("route_name " + arr);
        if (arr.equals("") || arr.equals(null)) {
            currentRouteId = Integer.parseInt(request.getParameter("current_route"));
        } else {
            String a[] = arr.split(";;");
            currentRouteId = Integer.parseInt(a[0]);
            // System.out.println("old routeId: " + routeIdOld +
            // "current routeID: " + currentRouteId);
        }
        if (!(routeIdOld == (currentRouteId))) {
            tripDetails.setRouteid((currentRouteId));
            // System.out.println("route has been changed");
            String status = routeservice.getRoute(currentRouteId).getIsAssigned();
            int i = Integer.parseInt(status);
            routeservice.updateStatus(currentRouteId, String.valueOf(++i));
            String status1 = routeservice.getRoute(routeIdOld).getIsAssigned();
            int i1 = Integer.parseInt(status1);
            routeservice.updateStatus(routeIdOld, String.valueOf(--i1));

        }
        // System.out.println(trip);

        tripDetailService.updatetrip(tripId, tripDetails);
        logger.info("Trip [" + tripDetails.getTrip_name() + " ] got updated.....");

        // tripsDaoImpl.updateTripByTripId(tripId, trip);
        // change in student and staff
        // studentDaoImpl.updateStudentTripNameFromHome(trip.getTrip_name(),
        // tripId);
        // studentDaoImpl.updateStudentTripNameFromSchool(trip.getTrip_name(),
        // tripId);

        // staffDaoImpl.updateStaffTripNameFromHome(trip.getTrip_name(),
        // tripId);
        // staffDaoImpl.updateStaffTripNameFromSchool(trip.getTrip_name(),
        // tripId);

        model.addAttribute("trip", tripDetails);
        return "redirect:/school_admin/trip/trips";
    }

    @RequestMapping(value = "/viewTrip", method = RequestMethod.GET)
    public ModelAndView viewTrip(ModelAndView model, HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
            System.out.println("inside if");
            model.setViewName("/j_spring_security_logout");
            return model;
        }

        String trip_detail_id = request.getParameter("id");
        TripDetail trip = tripDetailService.getTripDetail(Integer.parseInt((trip_detail_id)));
        model.addObject("trip", trip);
        model.setViewName("/school_admin/trip/viewtrip");

        model.addObject("login_role", rolesUtility.getRole(request));
        return model;
    }

    @RequestMapping(value = "/deleteTrip", method = RequestMethod.GET)
    public ModelAndView removeStop(ModelAndView model, HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
            System.out.println("inside if");
            model.setViewName("/j_spring_security_logout");
            return model;
        }

        // Authentication auth =
        // SecurityContextHolder.getContext().getAuthentication();
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        model.addObject("date", formatter.format(new Date()));
        model.addObject("login_name", auth.getName());
        model.addObject("current_page", "trips");
        model.setViewName("/school_admin/trip/removetrip");
        Integer trip_detail_id = null;
        if (request.getParameter("id") != null) {

            trip_detail_id = Integer.parseInt(request.getParameter("id"));
        } else {
            trip_detail_id = Integer.parseInt(request.getSession().getAttribute("id").toString());
        }
        HttpSession session = request.getSession();
        session.setAttribute("id", trip_detail_id);
        TripDetail trip = tripDetailService.getTripDetail(trip_detail_id);
        model.addObject("trip", trip);
        model.addObject("login_role", rolesUtility.getRole(request));
        request.getSession().removeAttribute("ERROR_MESSAGE");
        request.getSession().removeAttribute("id");
        return model;
    }

    @RequestMapping(value = "/deleteTripAction", method = RequestMethod.POST)
    public ModelAndView deleteTrip(HttpServletRequest request, ModelAndView view) {

        Integer trip_detail_id = null;
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
                System.out.println("inside if");
                view.setViewName("redirect:/j_spring_security_logout");
                return view;
            }

            // System.out.println("delete action");
            trip_detail_id = Integer.parseInt(request.getParameter("id"));

            // changes in routes
            TripDetail tripDetail = tripDetailService.getTripDetail(trip_detail_id);
            String tn = tripDetail.getTrip_name();
            if (!(tripDetail.equals(null))) {
                int routeId = tripDetail.getRoutes().getId();
                String status = routeservice.getRoute(routeId).getIsAssigned();
                int i = Integer.parseInt(status);
                routeservice.updateStatus(routeId, String.valueOf(--i));
            }
            // System.out.println(trip.getRoute_id());
            Buses busEntity = busservice.getBusById(tripDetail.getBus().getBus_id());
            int bs = busEntity.getBus_allotted();
            busservice.updateBusStatus(busEntity, --bs);
            // if (tripDetail.getSeats_filled() == 0)

            System.out.println("Deleting trip_detail_id:" + trip_detail_id);
            tripDetailService.deleteTripDetail(trip_detail_id);

            logger.info("Trip [" + tn + " ] got Deleted by User [" + auth.getName() + " ]");
        } catch (DataIntegrityViolationException e) {
            view.addObject("ERROR_MESSAGE", ErrorCodes.CODE_1);
            logger.error("Exception: " + e.getMessage());
            request.getSession().setAttribute("ERROR_MESSAGE", ErrorCodes.CODE_1);
            request.getSession().setAttribute("id", trip_detail_id);
            view.setViewName("redirect:/school_admin/trip/deleteTrip");
            return view;
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage());
        }
        view.setViewName("redirect:/school_admin/trip/trips");
        return view;
    }
    // testing purpose
    @RequestMapping(value = "/getAllRoutesByTripType", method = RequestMethod.POST)
    public @ResponseBody String getAllRoutesByTripType(ModelAndView model, HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
            System.out.println("inside if");
            return "redirect:/j_spring_security_logout";
        }

        // System.out.println("inside getallroute method");
        String route_type = request.getParameter("trip_type");
        // System.out.println(route_type);
        List<Routes> routes = routeservice.getRouteByType(route_type);
        // System.out.println(routes);
        Gson gson = new Gson();
        String json = gson.toJson(routes);
        return json;

    }

    public static String firstStopTimings;
    public static String secondStopTimings;

    // for first and last stop
    @RequestMapping(value = "/getAllStopssByRouteId", method = RequestMethod.POST)
    public @ResponseBody String getAllStopssByRouteId(ModelAndView model, HttpServletRequest request,
            HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
            System.out.println("inside if");
            return "redirect:/j_spring_security_logout";
        }

        // System.out.println("inside getallroute method ");
        int route_id = Integer.parseInt(request.getParameter("route_id"));
        // System.out.println("route id is:"+route_id);
        // List<RoutesEntity>
        // routes=tripsDaoImpl.getAllRoutesByTripType(route_type);
        List<RouteStops> routeStops = routestopservice.getStopsByRouteId(route_id);
        Collections.sort(routeStops);
        // System.out.println("size: " + routeStops.size());
        if (routeStops.size() > 0) {

            RouteStops re1 = routeStops.get(0);
            // System.out.println(re1);
            String firstStopTime = re1.getStop_time();
            int lastStopNumber = routeStops.size();
            RouteStops re = routeStops.get(lastStopNumber - 1);
            // System.out.println(re);
            String lastStopTime = re.getStop_time();

            // System.out.println("firstStopName&lastStopName:"+firstStopTime+";"+lastStopTime);
            firstStopTimings = firstStopTime;
            secondStopTimings = lastStopTime;
            session.setAttribute("fs", firstStopTime);
            session.setAttribute("ls", lastStopTime);
            Gson gson = new Gson();
            String json = gson.toJson(firstStopTime + ";" + lastStopTime);
            return json;
        } else {
            Gson gson = new Gson();
            String json = gson.toJson("none;none");
            return json;
        }

    }

    @RequestMapping(value = "/removeAllTripsByTripIds", method = RequestMethod.POST)
    public @ResponseBody String removeAllTripsByTripIds(ModelAndView model, HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
            System.out.println("inside if");
            return "redirect:/j_spring_security_logout";
        }

        // System.out.println("inside delete trips method");

        String tripIds = request.getParameter("tripIds");
        // System.out.println(tripIds);
        String trips[] = tripIds.split(",");
        int totalItems = trips.length;
        // System.out.println(totalItems);
        for (int i = 1; i <= totalItems; i++) {
            // StudentEntity se1 =
            // studentDaoImpl.getStudentByRouteIdFromHome(trips[i - 1]);
            // StudentEntity se2 =
            // studentDaoImpl.getStudentByRouteIdFromSchool(trips[i - 1]);
            // System.out.println("se1"+se1);
            // System.out.println("se2"+se2);
            // if ((se1 == null) && (se2 == null)) {
            // System.out.println("con be deleted");
            // before delete, change route status
            TripDetail trip = tripDetailService.getTripDetail((Integer.parseInt(trips[i - 1])));

            if (!(trip.equals(""))) {
                int routeId = trip.getRoutes().getId();
                String status = routeservice.getRoute(routeId).getIsAssigned();
                int k = Integer.parseInt(status);
                routeservice.updateStatus(routeId, String.valueOf(--k));
            }
            Buses busEntity = busservice.getBusById(trip.getBus().getBus_id());
            int bs = busEntity.getBus_allotted();
            busservice.updateBusStatus(busEntity, --bs);
            tripDetailService.deleteTripDetail(Integer.parseInt((trips[i - 1])));
            logger.info("Trip Deleted");
            // }
            /*
             * //System.out.println("can be deleted");
             * tripsDaoImpl.deleteTrip(trips[i-1]);
             */
        }
        /*
         * Gson gson = new Gson(); String json = gson.toJson(routes); return
         * json;
         */
        // return null;
        return "trips";
    }

}
