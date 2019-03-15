package com.main.sts.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.main.sts.common.CommonConstants.TripStatus;
import com.main.sts.common.CommonConstants.VehicleStatus;
import com.main.sts.entities.Buses;
import com.main.sts.entities.DailyBusNotifications;
import com.main.sts.entities.DailyDriverData;
import com.main.sts.entities.DailyRunningBuses;
import com.main.sts.service.BusesService;
import com.main.sts.service.DailyBusNotificationService;
import com.main.sts.service.DailyDriverDataService;
import com.main.sts.service.DailyRunningBusesService;
import com.main.sts.service.TripService;
import com.main.sts.util.DateUtil;
import com.main.sts.util.RolesUtility;

@Controller
@RequestMapping(value = "/school_admin/homepage")
@PreAuthorize("isAuthenticated()")
@Secured({ "ROLE_ADMIN", "ROLE_GUEST" })
public class HomePageDataController {

    private static Logger logger = Logger.getLogger(HomePageDataController.class);

    @Autowired
    private DailyRunningBusesService dailyRunningBusesService;

    @Autowired
    private BusesService busesService;

    @Autowired
    private RolesUtility rolesUtility;

    @Autowired
    private DailyDriverDataService dailyDriverDataService;

    @Autowired
    private DailyBusNotificationService dailyBusNotificationsService;

    @Autowired
    private DailyRunningBusesService dailyrunningbuses;

    @Autowired
    private TripService tripservice;

    int d, e, f = 0;

    @RequestMapping(value = "/getHomePageData", method = RequestMethod.POST)
    public @ResponseBody String studentRfidUpdate(HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
            System.out.println("inside if");
            return "redirect:/j_spring_security_logout";
        }

        Date date = new Date();

        //String current_date_str = new SimpleDateFormat("yyyy-MM-dd").format(date);
        Date current_date = DateUtil.getTodayDateWithOnlyDate();
        
        String time = new SimpleDateFormat("HH:mm").format(date);
        // System.out.println("Today's date: " + current_date);
        List<DailyRunningBuses> dailyRunningBuses = dailyRunningBusesService.getDailyRunningBus(current_date, TripStatus.RUNNING);

        int tota_buses = busesService.getBuses().size();
        // System.out.println(buses.size());

        if (dailyRunningBuses == null || dailyRunningBuses.size() == 0) {
            return "none---" + tota_buses;
        } else {
            int late = 0, very_late = 0, ontime = 0, total_buses = 0;

            for (DailyRunningBuses drb : dailyRunningBuses) {
                VehicleStatus vehicleStatus  = VehicleStatus.getVehicleStatus(drb.getVehicle_status());
                
                DailyBusNotifications notification = dailyBusNotificationsService.getTodysNotifications(current_date, vehicleStatus,
                        drb.getTrip_id());
                
                if (notification == null) {
                    dailyBusNotificationsService.insert(drb, time, vehicleStatus);
                    continue;
                }
                String message = null;
                if (notification != null) {
                    switch (vehicleStatus) {
                        case ONTIME :
                            ontime++;
                            message = "Ontime:Bus [ " + drb.getVehicle_licence_number() + " ] is Ontime at Stop [ " + drb.getCurrent_stop()
                                    + " ]";
                            break;
                        case LATE :
                            late++;
                            message = "Late:Bus [ " + drb.getVehicle_licence_number() + " ] is Late at Stop [ " + drb.getCurrent_stop()
                                    + " ]";
                            break;
                        case VERY_LATE :
                            message = "VeryLate:Bus [ " + drb.getVehicle_licence_number() + " ] is VeryLate at Stop [ "
                                    + drb.getCurrent_stop() + " ]";
                            break;

                        default :
                            break;
                    }
                    notification.setNotification(message);
                    dailyBusNotificationsService.update(notification);
                }
            }
            total_buses = tota_buses - late - very_late - ontime;

            Gson gos = new Gson();
            String send = gos.toJson(dailyRunningBuses);
            // logger.info(send);
            send = send + "---" + ontime + ":" + late + ":" + very_late + ":" + total_buses;
            return send;

        }
    }

    @RequestMapping(value = "/getNotifications", method = RequestMethod.POST)
    public @ResponseBody String getNotifications(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
            System.out.println("inside if");
            return "redirect:/j_spring_security_logout";
        }

        //Date date = new Date();
        String status = request.getParameter("status");
        //String current_date = new SimpleDateFormat("yyyy-MM-dd").format(date);
        Date current_date = DateUtil.getTodayDateWithOnlyDate();

        VehicleStatus vehicleStatus = VehicleStatus.getVehicleStatus(status);
        List<DailyBusNotifications> dailyNotifications = dailyBusNotificationsService.getTodysNotifications(
                current_date, vehicleStatus);
        // System.out.println(dailyNotifications);
        Gson gos = new Gson();
        String send = gos.toJson(dailyNotifications);
        return send;
    }

    @RequestMapping(value = "/getdriverinformation", method = RequestMethod.POST)
    public @ResponseBody String driver_data(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
            System.out.println("inside if");
            return "redirect:/j_spring_security_logout";
        }

        String send = null;
        int driver_id = Integer.parseInt(request.getParameter("driver_id"));
        int trip_id = Integer.parseInt(request.getParameter("trip_id"));
        // System.out.println("Trip ID: " + trip_id);
        // System.out.println("Driver ID: " + driver_id);
        Date date = new Date();

        String current_date = new SimpleDateFormat("yyyy-MM-dd").format(date);
        DailyDriverData dailyDriverData = dailyDriverDataService.getDailyDriverData(trip_id, driver_id, current_date);

        if (dailyDriverData == null) {
            send = "none";
        } else {

            send = dailyDriverData.getIn_time() + "+" + dailyDriverData.getOut_time();
        }

        return send;

    }

    // notificationspage
    @RequestMapping(value = "/notificationspage", method = RequestMethod.GET)
    public ModelAndView notificationsPage(ModelAndView view, HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
            view.setViewName("redirect:/j_spring_security_logout");
            return view;
        }

        Date date = new Date();
        String status = request.getParameter("status");
        // System.out.println(status);
       // String current_date_str = new SimpleDateFormat("yyyy-MM-dd").format(date);

        Date current_date = DateUtil.getTodayDateWithOnlyDate();

        VehicleStatus vehicleStatus = VehicleStatus.getVehicleStatus(status);
        // System.out.println("date " + current_date);
        List<DailyBusNotifications> dailyNotifications = dailyBusNotificationsService.getTodysNotifications(
                current_date, vehicleStatus);
        // System.out.println("list " + dailyNotifications);
        // added by sami
        List<Buses> remainingbuses = new ArrayList<Buses>();

        List<DailyRunningBuses> buses = dailyrunningbuses.getCurrentRunningBuses(current_date);
        Buses busEntity = null;
        if (status.equalsIgnoreCase("outofservice")) {
            System.out.println("if loop");
            List<Buses> allbuses = busesService.getBuses();
            for (Buses bus : allbuses) {

                boolean found = false;
                for (DailyRunningBuses currentRunningBuses : buses) {
                    if (bus.getBus_licence_number().equals(
                            tripservice.getTrip(currentRunningBuses.getTrip_id()).getTripDetail().getBus()
                                    .getBus_licence_number())) {
                        found = true;
                    }
                }
                if (found == false) {
                    busEntity = busesService.getBusById(bus.getBus_id());
                    remainingbuses.add(busEntity);
                }
            }
            // System.out.println("remain " + remainingbuses);
            view.addObject("dailyNotifications", remainingbuses);
        }

        else {
            // System.out.println("else");
            view.addObject("Notifications", dailyNotifications);
        }
        view.setViewName("/school_admin/notificationspage");
        view.addObject("status", request.getParameter("status"));

        return view;
    }

}
