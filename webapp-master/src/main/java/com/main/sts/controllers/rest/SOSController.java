package com.main.sts.controllers.rest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.main.sts.dto.Response;
import com.main.sts.dto.SOSDTO;
import com.main.sts.service.ReturnCodes;
import com.main.sts.service.SOSService;

@Controller
@RequestMapping("/sos")
public class SOSController {

    static final Logger logger = Logger.getLogger(SOSController.class);

    @Autowired
    private SOSService sosService;

    @RequestMapping(value = "/raise_alert", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Response> raiseAlert(@RequestBody SOSDTO sos) {
        Response resp = new Response();
        try {
            boolean sendEnabled = true;
            boolean notified = sosService.raiseAlertForSOSHelp(sos.getCommuter_id(), sendEnabled);
            if (notified) {
                resp.setReturnCode(ReturnCodes.SUCCESS.name());
            } else {
                resp.setReturnCode(ReturnCodes.UNKNOWN_ERROR.name());
            }
            resp.response = null;
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp.response = null;
            resp.setReturnCode(ReturnCodes.UNKNOWN_ERROR.name());
            return new ResponseEntity<Response>(resp, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
