package com.main.sts.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.main.sts.common.CommonConstants.VehicleStatus;
import com.main.sts.entities.Buses;
import com.main.sts.entities.DailyDriverData;
import com.main.sts.entities.DailyRunningBuses;
import com.main.sts.entities.DailySubscriberData;
import com.main.sts.entities.DriverSpeedEntity;
import com.main.sts.entities.PlexusMediatorRecord;
import com.main.sts.entities.Students;
import com.main.sts.entities.TripDetail;
import com.main.sts.entities.Trips;
import com.main.sts.service.BusesService;
import com.main.sts.service.DailyDriverDataService;
import com.main.sts.service.DailyRunningBusesService;
import com.main.sts.service.DailySubscriberDataService;
import com.main.sts.service.DriverSpeedService;
import com.main.sts.service.DriversService;
import com.main.sts.service.PlexusMediatorService;
import com.main.sts.service.StudentsService;
import com.main.sts.service.TripService;
import com.main.sts.util.RolesUtility;

@Controller
@RequestMapping(value = "/school_admin/reports")
@PreAuthorize("isAuthenticated()")
@Secured("ROLE_ADMIN")
public class ReportGeneratorController {

	int a = 0;

	@Autowired
	private DriverSpeedService speeddao;

	@Autowired
	private RolesUtility rolesUtility;

	@Autowired
	private DailyRunningBusesService dailyrunningbusesservice;

	@Autowired
	private TripService tripservice;
	@Autowired
	private BusesService busesservice;
	@Autowired
	private DailySubscriberDataService DailySubscriberDataService;
	@Autowired
	private StudentsService studentservice;
	@Autowired
	private DailyDriverDataService DailyDriverDataService;
	@Autowired
	private DriversService DriversService;
	@Autowired
	private PlexusMediatorService plexus;

	@RequestMapping(value = "/generate", method = RequestMethod.GET)
	public ModelAndView reportGenerationPage(ModelAndView modelAndView, HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			modelAndView.setViewName("redirect:/j_spring_security_logout");
			return modelAndView;
		}

		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		modelAndView.addObject("date", formatter.format(new Date()));
		modelAndView.setViewName("/school_admin/report/generate");

		modelAndView.addObject("login_role", rolesUtility.getRole(request));
		modelAndView.addObject("login_name", auth.getName());

		return modelAndView;
	}

	//bus report section
	@RequestMapping(value="/busreport",method=RequestMethod.GET)
	public ModelAndView busReport(ModelAndView view,HttpServletRequest request)
	{
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		if(auth!=null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()==false)
		{
			view.setViewName("rdeirect:/j_spring_security_logout");
			return view;
		}
		DateFormat format=new SimpleDateFormat("dd-MM-yyyy");
		view.addObject("date", format.format(new Date()));
		view.addObject("login_role", rolesUtility.getRole(request));
		view.setViewName("/school_admin/report/busreport");
		view.addObject("login_name", auth.getName());
		return view;
	}
	
	
    @RequestMapping(value = "/getbusreport", method = RequestMethod.POST)
    public @ResponseBody String getBusByDates(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated() == false) {
            return "rdeirect:/j_spring_security_logout";

        }
        String date1 = request.getParameter("from");
        // String from[]=date1.split("-");
        // date1=from[2]+"-"+from[1]+"-"+from[0];
        String date2 = request.getParameter("to");
        // String to[]=date2.split("-");
        // date2=to[2]+"-"+to[1]+"-"+to[0];
        List<DailyRunningBuses> buses = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = sdf.parse(date1);
            d2 = sdf.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (date1.equals(date2)) {
            buses = dailyrunningbusesservice.getCurrentRunningBuses(d1);
        } else {
            buses = dailyrunningbusesservice.getBusesByDates(d1, d2);
        }
        String ret = "";
        if (buses == null)
            buses = new ArrayList<DailyRunningBuses>();
        double o = 0, l = 0, v = 0.00;
        if (buses.size() > 0) {
            for (DailyRunningBuses db : buses) {
                if (db.getVehicle_status() == VehicleStatus.ONTIME.getStatusValue()) {
                    o++;
                } else if (db.getVehicle_status() == VehicleStatus.LATE.getStatusValue()) {
                    l++;
                } else {
                    v++;
                }
                Trips t = tripservice.getTrip(db.getTrip_id());
                Buses vehicle = t.getTripDetail().getBus();
                ret = ret + vehicle.getBus_licence_number() + ":" + t.getTripDetail().getTrip_name() + ":" + db.getRunning_date() + ":"
                        + db.getVehicle_status() + ":" + vehicle.getDriver().getDriver_name() + "/";
            }

            ret = ret + "+" + String.valueOf(o / buses.size() * 100) + "*" + String.valueOf(l / buses.size() * 100) + "*"
                    + String.valueOf((v / buses.size() * 100));
        }
        return ret;
    }
	
	
	@RequestMapping(value = "/getbuses", method = RequestMethod.POST)
	public @ResponseBody String searchBusByDate(HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			// System.out.println("inside if");
			return "redirect:/j_spring_security_logout";
		}

		String date = request.getParameter("date");
		String arr[] = date.split("-");
		
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = null;
        try {
            d = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }


		//List<DailyRunningBuses> buses = dailyrunningbusesservice.getCurrentRunningBuses(d2 + "-" + ad1 + "-"+ arr[0]);
        List<DailyRunningBuses> buses = dailyrunningbusesservice.getCurrentRunningBuses(d);
	      
		String ret = "";
		for (DailyRunningBuses bus : buses) {
			Trips trip = tripservice.getTrip(bus.getTrip_id());
			TripDetail td = trip.getTripDetail();
	         Buses vehicle = trip.getTripDetail().getBus();
			if (ret.contains(vehicle.getBus_licence_number())) {
				ret = ret + "";
			} else {
				ret = ret + td.getBusid() + ":" + td.getBus().getBus_licence_number() + "+";
			}

		}
		// System.out.println(ret);
		return ret;
	}

	@RequestMapping(value = "/gettrips", method = RequestMethod.POST)
	public @ResponseBody String searchTripsByBusID(HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			// System.out.println("inside if");
			return "redirect:/j_spring_security_logout";
		}

		String busno = request.getParameter("busno");
		// System.out.println("bus id "+busno);
		Buses b = busesservice.getBusByLicence(busno);
		List<Trips> trips = tripservice.getTripsByBusId(b.getBus_id());
		// System.out.println(trips);
		String res = "";
        for (Trips trip : trips) {
            TripDetail td = trip.getTripDetail();
            res = res + td.getTrip_name() + ":" + td.getId() + "+";
        }
		// System.out.println("trips are " +res);
		return res;
	}

	@RequestMapping(value = "/getstud", method = RequestMethod.POST)
	public @ResponseBody String getStudents(HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			// System.out.println("inside if");
			return "redirect:/j_spring_security_logout";
		}

		int trip = Integer.parseInt(request.getParameter("trip"));
		String date = request.getParameter("date");
		String arr[] = date.split("-");
		// System.out.println("trip name "+trip+" date is "+arr[2]+"-"+arr[1]+"-"+arr[0]);
		List<DailySubscriberData> stud = DailySubscriberDataService.getDailyStudentSubscribers(trip, arr[2] + "-"
				+ arr[1] + "-" + arr[0]);
		// System.out.println("students are "+stud);
		Gson gson = new Gson();
		String s = gson.toJson(stud);
		String res = "";
		for (DailySubscriberData student : stud) {

			res = res + student.getSubscriber_name() + "/" + student.getIn_stop() + "/" + student.getIn_time() + "/"
					+ student.getOut_stop() + "/" + student.getOut_time() + "/"
					+ studentservice.getStudent(student.getSubscriber_id()).getGr_number() + "+";
		}
		// System.out.println(res);

		return res;

	}

	// Student report section

	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public ModelAndView studentReportGenerationPage(ModelAndView modelAndView, HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			modelAndView.setViewName("redirect:/j_spring_security_logout");
			return modelAndView;
		}

		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		modelAndView.addObject("date", formatter.format(new Date()));
		modelAndView.setViewName("/school_admin/report/studentreport");

		modelAndView.addObject("login_role", rolesUtility.getRole(request));
		modelAndView.addObject("login_name", auth.getName());

		return modelAndView;
	}

	@RequestMapping(value = "/getstudents", method = RequestMethod.POST)
	public @ResponseBody String getAllStudents(HttpServletRequest request)

	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			// System.out.println("inside if");
			return "redirect:/j_spring_security_logout";
		}

		String s = "";

		List<Students> stud = studentservice.getStudentsList();
		// //System.out.println("students are "+stud);
		Collections.sort(stud, new Comparator<Students>() {

			@Override
			public int compare(Students o1, Students o2) {

				int i = o1.getFirst_name().compareToIgnoreCase(o2.getFirst_name());

				return i;
			}
		});
		for (Students student : stud) {
			if (!(s.contains(student.getFirst_name() + " " + student.getLast_name())))
				s = s + student.getFirst_name() + " " + student.getLast_name() + "+";
		}
		// System.out.println("names are "+s);

		return s;
	}

	@RequestMapping(value = "/getstudreport", method = RequestMethod.POST)
	public @ResponseBody String getStudentReport(HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			// System.out.println("inside if");
			return "redirect:/j_spring_security_logout";
		}

		// String s = "";
		String name = request.getParameter("name");
		String date = request.getParameter("date");
		String arr[] = date.split("-");
		// System.out.println(name + "date "+date);
		Students st = studentservice.getStudentByName(name.split(" ")[0], name.split(" ")[1]);
		// System.out.println(st);
		List<PlexusMediatorRecord> record = plexus.getStudentByStudId(st.getId(), arr[2] + "-" + arr[1] + "-" + arr[0]);
		// System.out.println(record);
		String med = "";
		for (PlexusMediatorRecord r : record) {
			med = med + r.getPerson_name() + "/" + r.getMobile_number() + "/" + r.getDate() + "/" + r.getStatus() + "/"
					+ r.getMessage() + "+";
		}

		List<DailySubscriberData> result = DailySubscriberDataService.getStudentByNameAndDate(name, arr[2] + "-"
				+ arr[1] + "-" + arr[0]);

		String res = "";
		for (DailySubscriberData student : result) {
			Trips trip = tripservice.getTrip(student.getTrip_id());
			res = res + student.getSubscriber_name() + "/" + student.getIn_stop() + "/" + student.getIn_time() + "/"
					+ student.getOut_time() + "/" + student.getOut_stop() + "/" + trip.getTripDetail().getTrip_type() + "/";

			// System.out.println("trip id "+trip_id);

			res = res + trip.getTripDetail().getTrip_name() + "/";

			res = res + trip.getTripDetail().getBus().getBus_licence_number() + "/";
			res = res + studentservice.getStudent(student.getSubscriber_id()).getGr_number() + "+";

		}
		// System.out.println("res "+res);
		// System.out.println("med "+med);
		return res + "*" + med;

	}

	// driver report

	@RequestMapping(value = "/driver", method = RequestMethod.GET)
	public ModelAndView driverReportGenerationPage(ModelAndView modelAndView, HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			modelAndView.setViewName("redirect:/j_spring_security_logout");
			return modelAndView;
		}
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		modelAndView.addObject("date", formatter.format(new Date()));
		modelAndView.setViewName("/school_admin/report/driverreport");

		modelAndView.addObject("login_role", rolesUtility.getRole(request));
		modelAndView.addObject("login_name", auth.getName());

		return modelAndView;
	}

	@RequestMapping(value = "/getdrivername", method = RequestMethod.POST)
	public @ResponseBody String getDriverNames(HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			System.out.println("inside if");
			return "redirect:/j_spring_security_logout";
		}

		String s = "";
		String date = request.getParameter("date");
		String arr[] = date.split("-");
		// System.out.println("student date is "+arr[2]+"-"+arr[1]+"-"+arr[0]);
		List<DailyDriverData> name = DailyDriverDataService.getDriverByDate(arr[2] + "-" + arr[1] + "-" + arr[0]);
		for (DailyDriverData dv : name) {
			if (!(s.contains(dv.getOut_driver_name())))
				s = s + dv.getOut_driver_name() + ":" + dv.getOut_driver_id() + "+";
		}
		// System.out.println("names are "+s);
		return s;

	}

	@RequestMapping(value = "/getdriverreport", method = RequestMethod.POST)
	public @ResponseBody String getBusesByDriver(HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			System.out.println("inside if");
			return "redirect:/j_spring_security_logout";
		}

		String driverid = "";
		String name = DriversService.getDriver(Integer.parseInt(request.getParameter("name"))).getDriver_name();
		;
		// String trip_id=request.getParameter("trip");
		String date = request.getParameter("date");
		// System.out.println("name " + name + " date " + date);
		String arr[] = date.split("-");

		List<DailyDriverData> data = DailyDriverDataService.getDriverDataByDriverIdAndDate(name, arr[2] + "-" + arr[1]
				+ "-" + arr[0]);
		// System.out.println("driver rep " + data);
		String s1 = "";
		for (DailyDriverData dv : data) {
			driverid = DriversService.getDriver(dv.getOut_driver_id()).getDriver_id();
			String busid = busesservice.getBusById(tripservice.getTrip(dv.getTrip_id()).getTripDetail().getBusid())
					.getBus_licence_number();
			s1 = s1 + driverid + "/" + dv.getOut_driver_name() + "/" + dv.getDate() + "/" + dv.getIn_time() + "/"
					+ dv.getOut_time() + "/" + dv.getTrip_id() + "/" + busid + "/";

			Trips trip = tripservice.getTrip(dv.getTrip_id());
			s1 = s1 + trip.getTripDetail().getTrip_name() + "+";

		}
		List<DriverSpeedEntity> dspeed = speeddao.getDriverSpeedByIDandDate(
				Integer.parseInt(request.getParameter("name")), arr[2] + "-" + arr[1] + "-" + arr[0]);
		String s2 = "";
		for (DriverSpeedEntity sp : dspeed) {

			s2 = s2 + sp.getBus_licence_number() + "/" + sp.getDriver_name() + "/" + sp.getTrip_name() + "/"
					+ sp.getHighest_speed() + "/" + sp.getStart_time() + "/" + sp.getEnd_time() + "+";
		}
		// System.out.println("driver detail " + s2);
		String res = s1 + "*" + s2;
		return res;
	}

	// getpdf

}
