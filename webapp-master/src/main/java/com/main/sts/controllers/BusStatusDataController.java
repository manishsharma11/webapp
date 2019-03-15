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

import com.google.gson.Gson;
import com.main.sts.entities.Buses;
import com.main.sts.entities.DailyRunningBuses;
import com.main.sts.entities.DailySubscriberData;
import com.main.sts.entities.Trips;
import com.main.sts.service.BusesService;
import com.main.sts.service.DailyRunningBusesService;
import com.main.sts.service.DailySubscriberDataService;
import com.main.sts.service.TripService;

@Controller
@RequestMapping(value = "/school_admin/bus")
@PreAuthorize("isAuthenticated()")
@Secured({ "ROLE_ADMIN", "ROLE_GUEST","ROLE_CUSTOMER_SUPPORT" ,"ROLE_OPERATOR"})
public class BusStatusDataController {

	@Autowired
	private DailyRunningBusesService dailyRunningBusesService;

	@Autowired
	private DailySubscriberDataService dailySubscriberDataService;
	@Autowired
	private BusesService busesService;
	@Autowired
	private TripService tripService;

	@RequestMapping(value = "/getBusPageData", method = RequestMethod.POST)
	public @ResponseBody String studentRfidUpdate(HttpServletRequest request) {
		Date date = new Date();
		String current_date_str = new SimpleDateFormat("yyyy-MM-dd").format(date);
		
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date current_date = cal.getTime();
        
		// String trip_id = request.getParameter("trip_id");
		// //System.out.println(trip_id);
		// String bus_id = request.getParameter("bus_id").trim();

		int trip_id = Integer.parseInt(request.getParameter("trip_id"));
		int bus_id = Integer.parseInt(request.getParameter("bus_id").trim());
		Gson gson = new Gson();
		Trips trip = tripService.getTrip(trip_id);
		DailyRunningBuses currentRunningBuses = dailyRunningBusesService.getDailyRunningBusWithRunningStatus(current_date, trip_id);
		Buses bus = busesService.getBusById(bus_id);
		List<DailySubscriberData> dailySubscriberDatas = dailySubscriberDataService.getDailySubscribers(trip_id,
				current_date_str);
		//System.out.println(dailySubscriberDatas.size());
		String json = gson.toJson(dailySubscriberDatas);
		String send = bus.getBus_licence_number() + "+" +

		currentRunningBuses.getVehicle_status() + "+" + currentRunningBuses.getCurrent_stop() + "+"
		+ currentRunningBuses.getArrived_time() + "+" + dailySubscriberDatas.size() + "+" + json + "+"
		+ trip.getTripDetail().getTrip_type() + "+" + gson.toJson(null);
		// System.out.println("curr " + currentRunningBuses);
		return send;
	}
}
