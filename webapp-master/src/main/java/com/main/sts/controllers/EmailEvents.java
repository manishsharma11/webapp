package com.main.sts.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
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

import com.main.sts.dto.EmailDTO;
import com.main.sts.dto.EmailResponse;
import com.main.sts.entities.Buses;
import com.main.sts.entities.Commuter;
import com.main.sts.entities.DashboardSettings;
import com.main.sts.entities.Parents;
import com.main.sts.entities.Transport;
import com.main.sts.entities.Trips;
import com.main.sts.service.BusesService;
import com.main.sts.service.CommuterService;
import com.main.sts.service.DashBoardSettingsService;
import com.main.sts.service.ParentService;
import com.main.sts.service.SendGridMailProvider;
import com.main.sts.service.TransportService;
import com.main.sts.service.TripService;
import com.main.sts.util.MD5PassEncryptionClass;
import com.main.sts.util.MyThreadPoolExecutor;
import com.main.sts.util.RolesUtility;

@Controller
@RequestMapping(value = "/school_admin/emails")
@Secured("ROLE_ADMIN")
@PreAuthorize("isAuthenticated()")
public class EmailEvents {

    @Autowired
    //private ParentService studentservice;
    private CommuterService commuterService;

    Properties props = null;
    Session session = null;

    @Autowired
    private MD5PassEncryptionClass passwordEncrypt;
    
    @Autowired
    private RolesUtility rolesUtility;
    
    @Autowired
    private DashBoardSettingsService dashBoardSettingsService;
    
    @Autowired
    private TripService tripService;
    
    @Autowired
    private TransportService transportService;
    
    @Autowired
    private BusesService busesService;
    
    @Autowired
    private SendGridMailProvider emailProvider;

    // private DashboardSettings adminPreferences;

    private static final Logger logger = Logger.getLogger(EmailEvents.class);

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public ModelAndView getEvents(ModelAndView modelAndView, HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
            modelAndView.setViewName("redirect:/j_spring_security_logout");
            return modelAndView;
        }

        System.out.println("getting events");
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        modelAndView.addObject("date", formatter.format(new Date()));
        List<Buses> bus = busesService.getBuses();

        modelAndView.addObject("allbuses", bus);

        modelAndView.setViewName("/school_admin/events/eventpage");

        modelAndView.addObject("login_role", rolesUtility.getRole(request));
        modelAndView.addObject("login_name", auth.getName());
        return modelAndView;
    }

    @RequestMapping(value = "/getnames", method = RequestMethod.POST)
    public @ResponseBody String getNames(ModelAndView modelAndView, HttpServletRequest request) {
        String res = "";
        String triptype = request.getParameter("busno");
        int id = busesService.getBusByLicence(request.getParameter("busid")).getBus_id();
        System.out.println("type " + triptype + " id " + id);
        List<Trips> list = null;
        if (triptype.contains(",")) {
            System.out.println("if");
            String no[] = triptype.split(",");
            for (int i = 0; i < no.length; i++) {
                System.out.println(no[i]);
                list = tripService.getTripsByBusIdAndTripType(id, no[i]);
                System.out.println(list);
                for (Trips t : list) {
                    res = res + t.getTripDetail().getTrip_name() + "+";
                }

            }
            return res;
        } else {
            System.out.println("else");
            list = tripService.getTripsByBusIdAndTripType(id, triptype);
            for (Trips t : list) {
                res = res + t.getTripDetail().getTrip_name() + "+";
            }
            System.out.println(res);
            return res;

        }
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public ModelAndView sendMail(HttpServletRequest request, ModelAndView model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
            model.setViewName("redirect:/j_spring_security_logout");
            return model;
        }

        DashboardSettings adminPreferences = dashBoardSettingsService.getDashBoardSettings();
        String event = request.getParameter("events");
        String to = "";
        String sub = request.getParameter("sub");
        String body = request.getParameter("msg");
        List<Commuter> commuters = null;
        if (event.equalsIgnoreCase("all")) {
            commuters = commuterService.findAll();
            for (Commuter c : commuters) {
                to = to + c.getEmail() + ",";
            }
        } else if (event.equalsIgnoreCase("bus")) {
            String busid = request.getParameter("buses");
            String trips[] = request.getParameterValues("tnames");
            System.out.println("trip " + trips[0]);
            
            // basically it means it will send email to all the persons who were part of the selected trips on today.
            Date today = new Date();
            today.setHours(0);
            today.setMinutes(0);
            today.setSeconds(0);
            for (int i = 0; i < trips.length; i++) {
                Trips trip = tripService.getTripByName(trips[i], today);
                List<Transport> sid = transportService.getCommuterIdByTrip(trip.getId());
                for (Transport t : sid) {
                    Commuter c = commuterService.getCommuterById(t.getSubscriber_id());
                    if (!to.contains(c.getEmail())) {
                        to = to + c.getEmail() + ",";
                    }
                }
            }
        } else if (event.equalsIgnoreCase("one")) {
            to = request.getParameter("email_id") + ",";
        }
        
        System.out.println("to:"+to);
        System.out.println("body:"+body);

        EmailDTO emailObj = new EmailDTO();
        String[] tos = to.split(",");
        if (tos.length > 1) {
            tos = new String[]{"dynamicrahul2020@gmail.com", "smartguyhere@gmail.com", "rahul@easycommute.co"};
        }
        
        boolean res = false;
        if (tos.length > 1) {
            emailObj.setTo(tos);
            emailObj.setSubject(sub);
            emailObj.setHtml(body);

            EmailResponse resp = emailProvider.sendEmail(emailObj);
            res = resp.getStatus();
        } else {
            System.out.println("there are no participant for sending emails." + tos);
            res = false;
        }

        // System.out.println("result is "+res);
        model.addObject("login_role", rolesUtility.getRole(request));
        model.setViewName("/school_admin/events/eventpage");
        if (res) {
            model.addObject("result", "success");
        } else {
            model.addObject("result", "failure");
        }
        return model;
    }

}
