package com.ec.eventserver.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ec.eventserver.dto.request.DeviceRequest;
import com.ec.eventserver.dto.request.DeviceResponse;
import com.ec.eventserver.models.DeviceInfo;
import com.ec.eventserver.service.DeviceService;
import com.main.sts.common.ServiceException;
import com.main.sts.common.ServiceException.ErrorType;
import com.main.sts.dto.Response;
import com.main.sts.service.ReturnCodes;

@Controller
@RequestMapping("/event/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    static final Logger logger = Logger.getLogger(DeviceController.class);

    @RequestMapping(value = "/generate_ec_device_id", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Response> generateDeviceId(@RequestBody DeviceRequest request) {
        DeviceResponse deviceInfoResp = null;
        Response resp = new Response();
        try {
            deviceInfoResp = deviceService.insertDeviceInfo(request);
            if (deviceInfoResp != null) {
                resp.message = "Device Id generated";
                resp.returnCode = ReturnCodes.EC_DEVICE_ID_GENERATED.name();
            } else {
                resp.message = "Insert failed";
                resp.returnCode = ReturnCodes.EC_DEVICE_ID_GENERATION_FAILED.name();
            }
            resp.response = deviceInfoResp;
            System.out.println(resp);
            System.out.println("--->>" + new ResponseEntity<Response>(resp, HttpStatus.OK));
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp.returnCode = ReturnCodes.EC_DEVICE_ID_GENERATION_FAILED.name();
            resp.message = e.getMessage();
            System.out.println(resp);
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/getByECDeviceId/{ec_device_id}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Response> getByECDeviceId(@PathVariable String ec_device_id) {
        DeviceInfo deviceInfo = null;
        Response resp = new Response();
        try {
            deviceInfo = deviceService.getDeviceInfoByECDeviceId(ec_device_id);
            if (deviceInfo != null) {
                resp.response = deviceInfo;
                resp.returnCode = ReturnCodes.SUCCESS.name();
                return new ResponseEntity<Response>(resp, HttpStatus.OK);
            } else {
                resp.response = null;
                resp.message = "Device info not available for: " + ec_device_id;
                resp.returnCode = ReturnCodes.BAD_REQUEST.name();
                return new ResponseEntity<Response>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof ServiceException) {
                ErrorType errorType = ((ServiceException) e).getErrorType();
                if (errorType == ErrorType.ILLEGAL_ARGUMENT) {
                    resp.response = null;
                    resp.message = e.getMessage();
                    resp.returnCode = ReturnCodes.BAD_REQUEST.name();
                    return new ResponseEntity<Response>(resp, HttpStatus.BAD_REQUEST);
                } else {
                    resp.response = null;
                    resp.message = e.getMessage();
                    resp.returnCode = ReturnCodes.UNKNOWN_ERROR.name();
                }
            }
            return new ResponseEntity<Response>(resp, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/getByHWDeviceId/{hw_device_id}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Response> getByHWDeviceId(@PathVariable String hw_device_id) {
        DeviceInfo deviceInfo = null;
        Response resp = new Response();
        try {
            deviceInfo = deviceService.getDeviceInfoByHWDeviceId(hw_device_id);
            if (deviceInfo != null) {
                resp.response = deviceInfo;
                resp.returnCode = ReturnCodes.SUCCESS.name();
                return new ResponseEntity<Response>(resp, HttpStatus.OK);
            } else {
                resp.response = null;
                resp.message = "Device info not available for: " + hw_device_id;
                resp.returnCode = ReturnCodes.BAD_REQUEST.name();
                return new ResponseEntity<Response>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof ServiceException) {
                ErrorType errorType = ((ServiceException) e).getErrorType();
                if (errorType == ErrorType.ILLEGAL_ARGUMENT) {
                    resp.response = null;
                    resp.message = e.getMessage();
                    resp.returnCode = ReturnCodes.BAD_REQUEST.name();
                    return new ResponseEntity<Response>(resp, HttpStatus.BAD_REQUEST);
                } else {
                    resp.response = null;
                    resp.message = e.getMessage();
                    resp.returnCode = ReturnCodes.UNKNOWN_ERROR.name();
                }
            }
            return new ResponseEntity<Response>(resp, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

}
