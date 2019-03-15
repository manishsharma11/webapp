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
import org.springframework.web.bind.annotation.ResponseBody;

import com.main.sts.dto.Response;
import com.main.sts.entities.RouteStops;
import com.main.sts.entities.Trips;
import com.main.sts.service.ReturnCodes;
import com.main.sts.service.TripService;

@Controller
@RequestMapping("/fares")
public class FareController {

    @Autowired
    private TripService tripService;

    static final Logger logger = Logger.getLogger(FareController.class);

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<Trips> getAllTrips() {
        List<Trips> trips = null;
        try {
            trips = tripService.getAllTrips();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trips;
    }

    @RequestMapping(value = "/{trip_id}/stops", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Response> getAllTrips(@PathVariable int trip_id) {
        Response resp = new Response();
        List<RouteStops> stops = null;
        try {
            stops = tripService.getRouteStops(trip_id);
            resp.response = stops;
            resp.returnCode = ReturnCodes.SUCCESS.name();
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp.response = null;
            resp.returnCode = ReturnCodes.UNKNOWN_ERROR.name();
            return new ResponseEntity<Response>(resp, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
