package com.main.sts.controllers.rest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.main.sts.dto.PushMessageDTO;
import com.main.sts.dto.Response;
import com.main.sts.service.PushNotificationService;

@Controller
@RequestMapping("/push")
public class PushNotificationController {

    @Autowired
    private PushNotificationService pushNotificationService;

    static final Logger logger = Logger.getLogger(PushNotificationController.class);

    @RequestMapping(value = "/send", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<Response> sendNotification(@RequestBody PushMessageDTO dto) {
        boolean status = false;
        try {
            status = pushNotificationService.sendPushNotification(dto.getCommuter_id(), dto.getTitle(), dto.getMessage());
            Response resp = new Response();
            resp.response = status;
            // resp.returnCode = status.name();
            // TODO: handle the response properly.
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            Response resp = new Response();
            resp.response = status;
            return new ResponseEntity<Response>(resp, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

}
