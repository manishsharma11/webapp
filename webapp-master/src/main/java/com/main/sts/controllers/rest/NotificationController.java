package com.main.sts.controllers.rest;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.main.sts.dto.Response;
import com.main.sts.entities.Notification;
import com.main.sts.service.NotificationService;
import com.main.sts.service.ReturnCodes;

@Controller
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    static final Logger logger = Logger.getLogger(NotificationController.class);

    @RequestMapping(value = "/findByCommuterId/{commuter_id}", method = RequestMethod.GET)
    public ResponseEntity<Response> sendNotification(@PathVariable int commuter_id) {
        List<Notification> notifications = null;
        try {
            notifications = notificationService.getNoticationByCommuterId(commuter_id);
            Response resp = new Response();
            resp.response = notifications;
            resp.returnCode = ReturnCodes.SUCCESS.name();
            // TODO: handle the response properly.
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            Response resp = new Response();
            resp.response = notifications;
            resp.returnCode = ReturnCodes.UNKNOWN_ERROR.name();
            return new ResponseEntity<Response>(resp, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

}
