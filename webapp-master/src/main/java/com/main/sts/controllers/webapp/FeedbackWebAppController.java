package com.main.sts.controllers.webapp;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.main.sts.dto.FeedbackDTO;
import com.main.sts.entities.Commuter;
import com.main.sts.entities.Feedback;
import com.main.sts.service.FeedbackService;

@Controller
@RequestMapping("/webapp/feedback")
public class FeedbackWebAppController {

    @Autowired
    private FeedbackService feedbackService;

    static final Logger logger = Logger.getLogger(FeedbackWebAppController.class);
    
    @RequestMapping(value = "/show")
    public  ModelAndView showFeedback(ModelAndView model) {
        model.setViewName("/webapp/feedback");
        return model;
    }

   // @ApiOperation(value = "Submit the feedback about a trip")
    @RequestMapping(value = "/submit_feedback", method = RequestMethod.POST)
    public ModelAndView submitFeedback(ModelAndView model, HttpServletRequest request, HttpSession session) {
        boolean status = false;
        session = request.getSession(false);
        int commuter_id = ((Commuter)session.getAttribute("commuter")).getCommuter_id();
        Integer trip_id = (Integer)session.getAttribute("trip_id");
        int rating_points = Integer.parseInt(request.getParameter("rating"));
        String concern = request.getParameter("concern");
        String[] reasons = (String[])request.getParameterValues("reason");
        String reason = Arrays.toString(reasons);
        reason = reason.substring(1, reason.length()-1);
        try {
            Feedback feedbackObj = new Feedback();
            feedbackObj.setCommuter_id(commuter_id);
            feedbackObj.setTrip_id(trip_id);
            feedbackObj.setRating_points(rating_points);
            feedbackObj.setReason(reason);
            feedbackObj.setConcern(concern);
            status = feedbackService.addFeedback(feedbackObj);
            model.addObject("status", status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.setViewName("/webapp/feedback");
        return model;
    }

}

