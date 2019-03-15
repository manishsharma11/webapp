package com.main.sts.controllers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.main.sts.common.CommonConstants.TripStatus;
import com.main.sts.common.CommonConstants.VehicleStatus;
import com.main.sts.entities.Buses;
import com.main.sts.entities.DailyBusCoordinates;
import com.main.sts.entities.DailyRunningBuses;
import com.main.sts.entities.DailySubscriberData;
import com.main.sts.service.BusesService;
import com.main.sts.service.DailyRunningBusesService;
import com.main.sts.service.DailySubscriberDataService;
import com.main.sts.util.DateUtil;
import com.main.sts.util.RolesUtility;

@Controller
@RequestMapping(value = "/school_admin/map")
@PreAuthorize("isAuthenticated()")
@Secured("ROLE_ADMIN")
public class MapDataController {

    @Autowired
    private RolesUtility rolesUtility;

    @Autowired
    private DailyRunningBusesService dailyRunningBusesService;

    @Autowired
    private DailySubscriberDataService dailySubscriberDataService;

    @Autowired
    private BusesService busesService;

    @RequestMapping(value = "/getMapData", method = RequestMethod.POST)
    public @ResponseBody String mapData(HttpServletRequest request, ModelAndView model) {

        try {
            Date current_date = DateUtil.getTodayDateWithOnlyDate();

            List<DailyRunningBuses> runningVehicles = dailyRunningBusesService.getDailyRunningBus(current_date,
                    TripStatus.RUNNING);

            // //System.out.println(buses);
            if (runningVehicles.size() == 0) {
                return "none";
            } else {
                String send = "";
                for (DailyRunningBuses b : runningVehicles) {
                    send = send + b.getUsers_in_bus() + ":" + b.getLatitude() + ":" + b.getLongitude() + ":"
                            + b.getVehicle_status() + ":" + b.getVehicle_licence_number() + "+";

                }
                // //System.out.println(send);
                return send;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "none";
        }
    }

    @RequestMapping(value = "/getSingleBusMapData", method = RequestMethod.POST)
    public @ResponseBody String getSingleBusMapData(HttpServletRequest request, ModelAndView model) {

        try {
            int bus_id = Integer.parseInt(request.getParameter("bus_id"));
            int trip_id = Integer.parseInt(request.getParameter("trip_id"));
            Date date = new Date();
            Buses bus = busesService.getBusById(bus_id);
            String current_date_str = new SimpleDateFormat("yyyy-MM-dd").format(date);
            Date current_date = DateUtil.getTodayDateWithOnlyDate();

            DailyRunningBuses buses = dailyRunningBusesService.getDailyRunningBusWithRunningStatus(current_date, trip_id);
            List<DailySubscriberData> dailySubscriberDatas = dailySubscriberDataService.getDailySubscribers(trip_id, current_date_str);
            DailyBusCoordinates busCoordinates = dailyRunningBusesService.getLatestBusCoordinates(current_date, trip_id);
            if (buses == null) {
                return "none";
            } else {
                String send = "";
                // System.out.println(buses);
                send = send + buses.getUsers_in_bus() + ":" + busCoordinates.getLatitude() + ":" + busCoordinates.getLongitude() + ":"
                        + VehicleStatus.getVehicleStatus(buses.getVehicle_status()).getStatusText() + ":" + bus.getBus_licence_number()
                        + "+";

                return send;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "none";
        }
    }
}
