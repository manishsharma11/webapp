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

import com.main.sts.dto.FeedbackDTO;
import com.main.sts.entities.Feedback;
import com.main.sts.service.FeedbackService;

@Controller
@RequestMapping("/feedback")
// @Api(value = "feedbackservice", description =
// "Operations pertaining to FeedbackService")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    static final Logger logger = Logger.getLogger(FeedbackController.class);

    // @ApiOperation(value = "Sumit the feedback about a trip")
    @RequestMapping(value = "/submit_feedback", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Boolean> submitFeedback(@RequestBody FeedbackDTO feedback) {
        boolean status = false;
        try {
            Feedback feedbackObj = new Feedback();
            feedbackObj.setCommuter_id(feedback.getCommuter_id());
            feedbackObj.setTrip_id(feedback.getTrip_id());
            feedbackObj.setBooking_id(feedback.getBooking_id());;
            feedbackObj.setRating_points(feedback.getRating_points());
            feedbackObj.setReason(feedback.getReason());
            feedbackObj.setConcern(feedback.getConcern());
            status = feedbackService.addFeedback(feedbackObj);
            return new ResponseEntity<Boolean>(status, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Boolean>(status, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

}
