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

import com.main.sts.dto.RouteDTO;
import com.main.sts.entities.SuggestRoute;
import com.main.sts.service.RouteService;

@Controller
@RequestMapping("/route")
public class RouteController {

    private static final Logger logger = Logger.getLogger(RouteController.class);

    @Autowired
    private RouteService routeService;

    @RequestMapping(value = "/suggest_route", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Boolean> suggestARoute(@RequestBody RouteDTO route) {
        boolean status = false;
        try {
            SuggestRoute sr = new SuggestRoute();
            sr.setFrom_stop(route.getFrom_stop());
            sr.setTo_stop(route.getTo_stop());
            sr.setCommuter_id(route.getCommuter_id());
            sr.setStart_time_hour(route.getStart_time_hour());
            sr.setStart_time_minutes(route.getStart_time_minutes());
            sr.setEnd_time_hour(route.getEnd_time_hour());
            sr.setEnd_time_minutes(route.getEnd_time_minutes());
            status = routeService.suggestARoute(sr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (status) {
            return new ResponseEntity<Boolean>(status, HttpStatus.OK);
        } else {
            return new ResponseEntity<Boolean>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

}
