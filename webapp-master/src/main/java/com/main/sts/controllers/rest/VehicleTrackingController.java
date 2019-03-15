package com.main.sts.controllers.rest;

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

import com.main.sts.common.ServiceException;
import com.main.sts.common.ServiceException.ErrorType;
import com.main.sts.dto.Response;
import com.main.sts.dto.UserGPSDataDTO;
import com.main.sts.dto.VehicleGPSDataDTO;
import com.main.sts.entities.TrackingDetails;
import com.main.sts.service.ReturnCodes;
import com.main.sts.service.VehicleTrackingService;

@Controller
@RequestMapping("/vehicle_tracking")
public class VehicleTrackingController {

    @Autowired
    private VehicleTrackingService vehicleTrackingService;

    static final Logger logger = Logger.getLogger(VehicleTrackingController.class);

    @RequestMapping(value = "/store_vehicle_pos", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Response> storeVehiclePos(@RequestBody VehicleGPSDataDTO dto) {
        ReturnCodes returnCode = ReturnCodes.UNKNOWN_ERROR;
        Response resp = new Response();
        try {
            returnCode = vehicleTrackingService.storeVehiclePos(dto);
            if (returnCode == ReturnCodes.VEHICLE_POS_INSERT_SUCCESS) {
                resp.message = "Insert success";
            } else if (returnCode == ReturnCodes.VEHICLE_POS_INSERT_FAILED) {
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
    
    @RequestMapping(value = "/start_trip", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Response> startTrip(@RequestBody VehicleGPSDataDTO dto) {
        ReturnCodes returnCode = ReturnCodes.UNKNOWN_ERROR;
        Response resp = new Response();
        try {
            returnCode = vehicleTrackingService.startTrip(dto);
            if (returnCode == ReturnCodes.VEHICLE_POS_INSERT_SUCCESS) {
                resp.message = "Insert success";
            } else if (returnCode == ReturnCodes.VEHICLE_POS_INSERT_FAILED) {
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
    
    @RequestMapping(value = "/end_trip", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Response> endTrip(@RequestBody VehicleGPSDataDTO dto) {
        ReturnCodes returnCode = ReturnCodes.UNKNOWN_ERROR;
        Response resp = new Response();
        try {
            returnCode = vehicleTrackingService.endTrip(dto);
            if (returnCode == ReturnCodes.VEHICLE_POS_INSERT_SUCCESS) {
                resp.message = "Insert success";
            } else if (returnCode == ReturnCodes.VEHICLE_POS_INSERT_FAILED) {
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

    @RequestMapping(value = "/trackByBookingId/{booking_id}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Response> trackByBookingId(@PathVariable Integer booking_id) {
        TrackingDetails trackingDetails = null;
        Response resp = new Response();
        try {
            trackingDetails = vehicleTrackingService.trackVehcileByBooking(booking_id);
            if (trackingDetails != null) {
                resp.response = trackingDetails;
                resp.returnCode = ReturnCodes.SUCCESS.name();
                return new ResponseEntity<Response>(resp, HttpStatus.OK);
            } else {
                resp.response = null;
                resp.message = "Stops list are null";
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
    
    @RequestMapping(value = "/trackByBookingId1/{booking_id}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Response> trackByBookingId1(@PathVariable Integer booking_id) {
        TrackingDetails trackingDetails = null;
        Response resp = new Response();
        try {
            trackingDetails = vehicleTrackingService.trackVehcileByBooking1(booking_id);
            if (trackingDetails != null) {
                resp.response = trackingDetails;
                resp.returnCode = ReturnCodes.SUCCESS.name();
                return new ResponseEntity<Response>(resp, HttpStatus.OK);
            } else {
                resp.response = null;
                resp.message = "Stops list are null";
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
