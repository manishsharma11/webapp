package com.main.sts.controllers.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import com.main.sts.dto.RouteStopsDTO;
import com.main.sts.dto.StopsDTO;
import com.main.sts.entities.RouteStops;
import com.main.sts.entities.Stops;
import com.main.sts.service.ReturnCodes;
import com.main.sts.service.StopsService;

@Controller
@RequestMapping("/stops")
public class StopsController {

    @Autowired
    private StopsService stopsService;

    static final Logger logger = Logger.getLogger(StopsController.class);

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<Stops> getAllStops() {
        List<Stops> stops = null;
        try {
            stops = stopsService.getAllStops();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stops;
    }

    @RequestMapping(value = "/from_stops", method = RequestMethod.GET)
    public @ResponseBody List<StopsDTO> getFromStops() {
        Set<RouteStops> stops = null;
        boolean onlyPickupStops = true;
        try {
            stops = stopsService.getAllAvailableFromStops();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // TODO: Add a check for empty
        List<StopsDTO> stopsDTOs = new ArrayList<StopsDTO>();
        if (stops != null) {
            for (RouteStops rs : stops) {
                StopsDTO stop = stopsService.toStopsDTO(rs);
                stopsDTOs.add(stop);
            }
        } else {
            System.out.println("stops list is null");
        }
        return stopsDTOs;
    }
    
    @RequestMapping(value = "/from_stops/{route_id}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Response> getRoutes(@PathVariable int route_id) {
        Response resp = new Response();
        Set<RouteStops> routeStops = null;
        try {
            routeStops = stopsService.getAllAvailableFromStops();
            // TODO: Add a check for empty
            List<StopsDTO> stopsDTOs = new ArrayList<StopsDTO>();
            if (routeStops != null) {
                for (RouteStops rs : routeStops) {
                    StopsDTO stop = stopsService.toStopsDTO(rs);
                    stopsDTOs.add(stop);
                }
                resp.response =  stopsDTOs;
                resp.returnCode = ReturnCodes.SUCCESS.name();
                return new ResponseEntity<Response>(resp, HttpStatus.OK);
            } else {
                System.out.println("stops list is null");
                resp.response = null;
                resp.returnCode = ReturnCodes.UNKNOWN_ERROR.name();
                return new ResponseEntity<Response>(resp, HttpStatus.SERVICE_UNAVAILABLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.response = null;
            resp.returnCode = ReturnCodes.UNKNOWN_ERROR.name();
            return new ResponseEntity<Response>(resp, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/to_stops/{from_stop_id}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<StopsDTO>> getToStops(@PathVariable int from_stop_id) {
        List<RouteStops> routeStopsList = null;
        try {
            routeStopsList = stopsService.getAllAvailableToStops(from_stop_id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<StopsDTO> stops = new ArrayList<StopsDTO>();
        if (routeStopsList != null) {
            for (RouteStops rs : routeStopsList) {
                StopsDTO stop = stopsService.toStopsDTO(rs);
                stops.add(stop);
            }
        }
        return new ResponseEntity<List<StopsDTO>>(stops, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/findRoutes", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Response> getRoutes() {
        Response resp = new Response();
        List<RouteStopsDTO> routeStops = null;
        try {
            routeStops = stopsService.getAllAvailableRoutesWithStops();
            resp.response = routeStops;
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
