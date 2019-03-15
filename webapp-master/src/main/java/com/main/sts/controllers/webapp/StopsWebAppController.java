package com.main.sts.controllers.webapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.main.sts.dto.RouteStopsDTO;
import com.main.sts.dto.StopsDTO;
import com.main.sts.entities.RouteStops;
import com.main.sts.service.StopsService;

@Controller
@RequestMapping("/webapp/stops")
public class StopsWebAppController {

    @Autowired
    private StopsService stopsService;

    static final Logger logger = Logger.getLogger(StopsWebAppController.class);

    @RequestMapping(value = "/findRoutes", method = RequestMethod.GET)
    public ModelAndView getRoutes(ModelAndView model) {
        List<RouteStopsDTO> routeStops = null;
        try {
            routeStops = stopsService.getAllAvailableRoutesWithStops();
            model.addObject("routeStops", routeStops);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.setViewName("/webapp/routes");
        return model;
    }
    
    @RequestMapping(value = "/from_stops")
    public List<StopsDTO> getFromStops() {
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
                StopsDTO stop = new StopsDTO();
                stop.route_stop_id = rs.getId();
                stop.stop_id = rs.stop.getId();
                stop.stop_name = rs.stop.getDisplay_name();//rs.stop.getStop_name();
                stop.latitude = rs.stop.getLatitude();
                stop.longitude = rs.stop.getLongitude();
                stop.shortcode = rs.stop.getShortcode();
                stop.help_text = rs.stop.getHelp_text();
//                System.out.println(stop.toString());
//                stopsDTOs.add(stop.toString());
               
                stopsDTOs.add(stop);
            }
        } else {
            System.out.println("stops list is null");
        }
//        String from_list = stopsDTOs.toString();
//        System.out.println(from_list);
//        model.addObject("from_stops_list", stopsDTOs);
//        return model;
        System.out.println("List Stops"+stopsDTOs);
        return stopsDTOs;
    }
    
}

