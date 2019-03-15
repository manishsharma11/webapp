package com.main.sts.controllers.webapp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.main.sts.dto.Response;
import com.main.sts.entities.Commuter;
import com.main.sts.entities.Notification;
import com.main.sts.service.NotificationService;
import com.main.sts.service.ReturnCodes;

@Controller
@RequestMapping("/webapp")
public class NotificationWebAppController  extends CommonController{

    @Autowired
    private NotificationService notificationService;

    static final Logger logger = Logger.getLogger(NotificationWebAppController.class);

    @RequestMapping(value = "/notifications", method = RequestMethod.GET)
    public ModelAndView getAllNotifications(ModelAndView model, HttpServletRequest request, HttpSession session) {
       
        List<Notification> notifications = null;
        try {
            session = super.getSession(request);
            Commuter commuter = super.getCommuter(request);
            int commuter_id = commuter.getCommuter_id();
            notifications = notificationService.getNoticationByCommuterId(commuter_id);
            model.addObject("notifications", notifications);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.setViewName("/webapp/notifications");
        model.addObject("current_page", "notifications");
        return model;
    }

}