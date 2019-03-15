package com.main.sts.controllers.rest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.main.sts.dto.Response;
import com.main.sts.dto.UserGPSDataDTO;
import com.main.sts.service.ReturnCodes;
import com.main.sts.service.UserTrackingService;

@Controller
@RequestMapping("/user_tracking")
public class UserTrackingController {

    @Autowired
    private UserTrackingService userTrackingService;

    static final Logger logger = Logger.getLogger(UserTrackingController.class);

    @RequestMapping(value = "/store_user_pos", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Response> storeUserPos(@RequestBody UserGPSDataDTO dto) {
        ReturnCodes returnCode = ReturnCodes.UNKNOWN_ERROR;
        Response resp = new Response();
        try {
            returnCode = userTrackingService.storeUserPos(dto);
            if (returnCode == ReturnCodes.USER_POS_INSERT_SUCCESS) {
                resp.message = "Insert success";
            } else if (returnCode == ReturnCodes.USER_POS_INSERT_FAILED) {
                resp.message = "Insert failed";
            }
            // resp.response = dto;
            resp.returnCode = returnCode.name();
            System.out.println(resp);
            System.out.println("--->>" + new ResponseEntity<Response>(resp, HttpStatus.OK));
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp.returnCode = returnCode.name();
            System.out.println(resp);
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        }
    }

}
