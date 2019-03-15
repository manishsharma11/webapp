package com.main.sts.controllers.webapp;

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
@RequestMapping("/webapp/trips")
public class TripWebAppController {

    @Autowired
    private TripService tripService;

    static final Logger logger = Logger.getLogger(TripWebAppController.class);

    @RequestMapping(value = "/get_stops_trips", method = RequestMethod.POST)
    public List<ShuttleTimingsDTO> getTodayAndTomorrowTrips(TripDTO tripDTO) {
        List<ShuttleTimingsDTO> trips = null;
        try {
            int from_stop_id = tripDTO.getFrom_stop_id();
            int to_stop_id = tripDTO.getTo_stop_id();
            trips = tripService.getTodayAndTomorrowTripsResponses(from_stop_id, to_stop_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trips;
    }
   
}
