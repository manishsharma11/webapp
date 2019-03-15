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

import com.main.sts.dto.Response;
import com.main.sts.dto.response.AboutUsDTO;
import com.main.sts.dto.response.OfferCheck;
import com.main.sts.dto.response.VersionCheckDTO;
import com.main.sts.dto.response.VersionCheckResponseDTO;
import com.main.sts.service.DashBoardSettingsService;
import com.main.sts.service.ReturnCodes;

@Controller
@RequestMapping("/general")
public class SettingsController {

    @Autowired
    private DashBoardSettingsService settingsService;

    static final Logger logger = Logger.getLogger(SettingsController.class);

    @RequestMapping(value = "/about_us", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Response> getAboutUs() {
        AboutUsDTO aboutus = null;
        Response resp = new Response();
        try {
            aboutus = settingsService.getAboutUs();
            resp.response = aboutus;
            resp.setReturnCode(ReturnCodes.SUCCESS.name());
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp.response = null;
            resp.setReturnCode(ReturnCodes.UNKNOWN_ERROR.name());
            return new ResponseEntity<Response>(resp, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
    
    @RequestMapping(value = "/version_check", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Response> checkAppVersion(@RequestBody VersionCheckDTO versionCheckReq) {
        VersionCheckResponseDTO versionCheckResp = null;
        Response resp = new Response();
        try {
            versionCheckResp = settingsService.checkAppVersion(versionCheckReq);
            resp.response = versionCheckResp;
            resp.setReturnCode(ReturnCodes.SUCCESS.name());
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp.response = null;
            resp.setReturnCode(ReturnCodes.UNKNOWN_ERROR.name());
            return new ResponseEntity<Response>(resp, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
    
    @RequestMapping(value = "/offer_announcement_check", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Response> offerCheck() {
        OfferCheck offerCheck = null;
        Response resp = new Response();
        try {
            offerCheck = settingsService.getOfferAnnouncementCheck();
            resp.response = offerCheck;
            resp.setReturnCode(ReturnCodes.SUCCESS.name());
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp.response = null;
            resp.setReturnCode(ReturnCodes.UNKNOWN_ERROR.name());
            return new ResponseEntity<Response>(resp, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

//    @RequestMapping(value = "/referral_scheme", method = RequestMethod.GET)
//    public @ResponseBody ResponseEntity<Response> getReferralScheme() {
//        ReferralDTO referral_scheme = null;
//        Response resp = new Response();
//        try {
//            referral_scheme = settingsService.getReferralScheme();;
//            resp.response = referral_scheme;
//            resp.setReturnCode(ReturnCodes.SUCCESS.name());
//            return new ResponseEntity<Response>(resp, HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            resp.response = null;
//            resp.setReturnCode(ReturnCodes.UNKNOWN_ERROR.name());
//            return new ResponseEntity<Response>(resp, HttpStatus.SERVICE_UNAVAILABLE);
//        }
//    }

}
