package com.main.sts.controllers.rest;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.main.sts.dto.Response;
import com.main.sts.dto.ShuttleTimingsDTO;
import com.main.sts.dto.TripDTO;
import com.main.sts.dto.TripResponseDTO;
import com.main.sts.entities.RouteStops;
import com.main.sts.service.ReturnCodes;
import com.main.sts.service.TripService;

@Controller
@RequestMapping("/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    static final Logger logger = Logger.getLogger(TripController.class);

//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public @ResponseBody List<Trips> getAllTrips() {
//        List<Trips> trips = null;
//        try {
//            trips = tripService.getAllTrips();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return trips;
//    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<TripResponseDTO> getAllTrips() {
        List<TripResponseDTO> trips = null;
        try {
            trips = tripService.getAllTripsResponse();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trips;
    }
    
//    @RequestMapping(value = "/get_trips", method = RequestMethod.POST)
//    public @ResponseBody List<Trips> getAllTrips(@RequestBody TripDTO trip) {
//        List<Trips> trips = null;
//        try {
//            int from_stop_id = trip.getFrom_stop_id();
//            int to_stop_id = trip.getTo_stop_id();
//            trips = tripService.getTripsBetweenStops(from_stop_id, to_stop_id);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return trips;
//    }
    
    // I think this is not more in use now. but I am not deleting it.
//    @RequestMapping(value = "/get_trips", method = RequestMethod.POST)
//    public @ResponseBody List<TripResponseDTO> getTodayAndTomorrowTrips1(@RequestBody TripDTO trip) {
//        List<TripResponseDTO> trips = null;
//        try {
//            int from_stop_id = trip.getFrom_stop_id();
//            int to_stop_id = trip.getTo_stop_id();
//            trips = tripService.getTodayAndTomorrowTripsResponses(from_stop_id, to_stop_id);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return trips;
//    }
    
    @RequestMapping(value = "/get_stops_trips", method = RequestMethod.POST)
    public @ResponseBody List<ShuttleTimingsDTO> getTodayAndTomorrowTrips(@RequestBody TripDTO trip) {
        List<ShuttleTimingsDTO> trips = null;
        try {
            int from_stop_id = trip.getFrom_stop_id();
            int to_stop_id = trip.getTo_stop_id();
            trips = tripService.getTodayAndTomorrowTripsResponses(from_stop_id, to_stop_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trips;
    }
    
//    @RequestMapping(value = "/get_all_trips", method = RequestMethod.POST)
//    public @ResponseBody List<TripResponseDTO> getAllTrips(@RequestBody TripDTO trip) {
//        List<TripResponseDTO> trips = null;
//        try {
//            int from_stop_id = trip.getFrom_stop_id();
//            int to_stop_id = trip.getTo_stop_id();
//            trips = tripService.getTripsResponseBetweenStops1(from_stop_id, to_stop_id);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return trips;
//    }

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
