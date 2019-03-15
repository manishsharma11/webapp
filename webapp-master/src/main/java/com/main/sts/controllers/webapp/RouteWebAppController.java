package com.main.sts.controllers.webapp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.main.sts.dto.RouteDTO;
import com.main.sts.entities.Commuter;
import com.main.sts.entities.SuggestRoute;
import com.main.sts.service.RouteService;

@Controller
@RequestMapping("/webapp/route")
public class RouteWebAppController extends CommonController{

    private static final Logger logger = Logger.getLogger(RouteWebAppController.class);

    @Autowired
    private RouteService routeService;

    @RequestMapping(value = "/show_suggest_route")
    public ModelAndView suggestARoute(ModelAndView model, HttpServletRequest request) {
        try {
//            boolean loggedin = super.isLoggedIn(request);
//            if (!loggedin) {
//                model.setViewName("/login");
//                return model;
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       model.setViewName("/webapp/suggest_route");
       return model;
    }
    
    @RequestMapping(value = "/suggest_route", method = RequestMethod.POST)
    public ModelAndView getSuggestedRoute(ModelAndView model, HttpSession session, HttpServletRequest request) {
        boolean status = false;
        try {
//            boolean loggedin = super.isLoggedIn(request);
//            if (!loggedin) {
//                model.setViewName("/login");
//                return model;
//            }
            String from_stop = request.getParameter("from");
            String to_stop = request.getParameter("to");
            String start_time = request.getParameter("morning");
            String[] sthour = start_time.split(":"); //sthour[0] is hour, sthour[1] is min AM or PM,
            String[] stmin = sthour[1].split(" "); // stmin[0] is min
            
            Integer start_time_hour = Integer.parseInt(sthour[0]);
            Integer start_time_minutes = Integer.parseInt(stmin[0]);
            
            String end_time = request.getParameter("evening");
            String[] ehour = end_time.split(":"); //ehour[0] is hour, ehour[1] is min AM or PM,
            String[] emin = ehour[1].split(" "); // emin[0] is min
            
            Integer end_time_hour = Integer.parseInt(ehour[0]);
            Integer end_time_minutes = Integer.parseInt(emin[0]);
             
            session = super.getSession(request);
            Commuter commuter = super.getCommuter(request);
            int commuter_id = commuter.getCommuter_id();
            SuggestRoute sr = new SuggestRoute();
            sr.setFrom_stop(from_stop);
            sr.setTo_stop(to_stop);
            sr.setStart_time_hour(start_time_hour);
            sr.setStart_time_minutes(start_time_minutes);
            sr.setEnd_time_hour(end_time_hour);
            sr.setEnd_time_minutes(end_time_minutes);
            sr.setCommuter_id(commuter_id);
            status = routeService.suggestARoute(sr);
        } catch (Exception e) {
            e.printStackTrace();
        }
       model.addObject("status", status);
       model.setViewName("/webapp/suggest_route");
       return model;
    }

}

