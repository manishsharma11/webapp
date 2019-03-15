package com.main.sts.controllers;

import java.io.File;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.main.sts.entities.Commuter;
import com.main.sts.service.CommuterService;
import com.main.sts.service.NotificationService;
import com.main.sts.service.SMSService;

@Controller
@RequestMapping(value = "/school_admin/sms_note")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_GUEST','ROLE_CUSTOMER_SUPPORT','ROLE_OPERATOR')")
public class SMSNotificationsController {

    @Autowired
    private CommuterService commuterService;

    @Autowired
    private SMSService smsService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView getAllStops(ModelAndView model, HttpServletRequest request) {
        String ApplicationName = ((HttpServletRequest) request).getContextPath().replace("/", "");

        System.out.println("<br/>File system context path (in TestServlet): " + ApplicationName);
        List<Commuter> commuterList = null;
        try {
            commuterList = commuterService.findAllActiveAndVerified();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
//        Commuter dummyCommuter = new Commuter();
//        dummyCommuter.setCommuter_id(Integer.MIN_VALUE);
//        dummyCommuter.setName("all");
//        dummyCommuter.setMobile("-1");
//        commuterList.add(dummyCommuter);
        
        model.setViewName("/school_admin/sms_notifications/sms_notes");
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        model.addObject("date", formatter.format(new Date()));
        model.addObject("current_page", "sms_note");
        model.addObject("commuters", commuterList);
        return model;
    }

    @RequestMapping(value = "/send_sms", method = RequestMethod.GET)
    public ModelAndView sendSMS(ModelAndView model, HttpServletRequest request) {
        System.out.println("Sending SMS send------------------->>>");
        try {
            String data = request.getParameter("data");

            String dataSplit[] = data.split(":");
            String ids[] = dataSplit[0].split(",");
            String message = dataSplit[1].trim();
            if (message == null) {
                throw new IllegalArgumentException("Message cant be null");
            }

            smsService.sendSMS(ids, message);

        } catch (Exception e) {
            e.printStackTrace();
        }
        model.setViewName("/school_admin/sms_notifications/sms_notes");
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        model.addObject("date", formatter.format(new Date()));
        model.addObject("current_page", "sms_note");
        return model;
    }
}
