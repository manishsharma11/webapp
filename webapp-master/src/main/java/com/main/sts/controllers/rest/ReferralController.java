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
import com.main.sts.dto.response.ReferralDTO;
import com.main.sts.entities.FAQ;
import com.main.sts.entities.ReferralCode;
import com.main.sts.service.ReferralCodeService;
import com.main.sts.service.ReferralTransactionService;
import com.main.sts.service.ReturnCodes;

@Controller
@RequestMapping("/referral")
public class ReferralController {

    @Autowired
    private ReferralCodeService referralCodeService;
    
    @Autowired
    private ReferralTransactionService referralTxService;

    static final Logger logger = Logger.getLogger(ReferralController.class);

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<ReferralCode>> getFAQs() {
        List<ReferralCode> referralCodes = null;
        Response resp = new Response();
        try {
            referralCodes = referralCodeService.findAll();
            resp.response = referralCodes;
            resp.setReturnCode(ReturnCodes.SUCCESS.name());
            return new ResponseEntity<List<ReferralCode>>(referralCodes, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp.response = null;
            resp.setReturnCode(ReturnCodes.UNKNOWN_ERROR.name());
            return new ResponseEntity<List<ReferralCode>>(referralCodes, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/generate/{commuter_id}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Response> createReferralCodeForACommuter(@PathVariable int commuter_id) {
        ReferralDTO referralCode = null;
        Response resp = new Response();
        try {
            referralCode = referralCodeService.getReferralCode(commuter_id);
            resp.response = referralCode;
            resp.setReturnCode(ReturnCodes.SUCCESS.name());
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp.response = null;
            resp.setReturnCode(ReturnCodes.UNKNOWN_ERROR.name());
            return new ResponseEntity<Response>(resp, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

}
