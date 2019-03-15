package com.ec.eventserver.controllers;

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

import com.main.sts.common.ServiceException;
import com.main.sts.common.ServiceException.ErrorType;
import com.main.sts.dto.Response;
import com.main.sts.dto.TripResponseDTO;
import com.main.sts.dto.response.CommuterBookingResponse;
import com.main.sts.service.BookingService;
import com.main.sts.service.ReturnCodes;
import com.main.sts.service.TripService;

import javax.annotation.Resource;

import org.junit.Test;

import com.ec.eventserver.models.DeviceInfo;
import com.main.sts.BaseTest;

public class VehicleControllerTest extends BaseTest {
    
    @Resource
    private VehicleController vehicleController;
    
    @Autowired 
    BookingService bookingService;
    @Test
    @RequestMapping(value = "/getBookings/{trip_id}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Response> getBookingCommuters(@PathVariable Integer trip_id) {
        List<CommuterBookingResponse> bookings = null;
        Response resp = new Response();
        try {
            bookings = bookingService.getAllCommuters(trip_id);
            if (bookings != null) {
                resp.response = bookings;
                resp.returnCode = ReturnCodes.SUCCESS.name();
                return new ResponseEntity<Response>(resp, HttpStatus.OK);
            } else {
                resp.response = null;
                resp.message = "No active trips available for the trip_id: " + trip_id;
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
