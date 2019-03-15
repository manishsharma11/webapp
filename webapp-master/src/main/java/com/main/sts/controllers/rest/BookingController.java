package com.main.sts.controllers.rest;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.main.sts.dto.BookingDTO;
import com.main.sts.dto.RegisterNotifyAvailabilityDTO;
import com.main.sts.dto.PreBookingDTO;
import com.main.sts.dto.Response;
import com.main.sts.dto.response.BookingCancellationResponse;
import com.main.sts.dto.response.BookingHistoryResponse;
import com.main.sts.dto.response.BookingResponse;
import com.main.sts.dto.response.BookingStopDetailsResponse;
import com.main.sts.dto.response.RegisterNotifyAvailabilityResponse;
import com.main.sts.dto.response.PreBookingCreditDetails;
import com.main.sts.dto.response.PreBookingDetails;
import com.main.sts.entities.Booking;
import com.main.sts.service.BookingService;
import com.main.sts.service.ReturnCodes;

@Controller
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    static final Logger logger = Logger.getLogger(BookingController.class);

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<Booking>> getBookingsList() {
        List<Booking> bookings = null;
        try {
            bookings = bookingService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<List<Booking>>(bookings, HttpStatus.OK);
    }

    @RequestMapping(value = "/findById/{booking_id}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<BookingHistoryResponse> getBookingById(@PathVariable Integer booking_id) {
        BookingHistoryResponse booking = null;
        try {
            // booking =
            // bookingService.toBookingHisotry(bookingService.getBookingById(booking_id));
            booking = bookingService.getBookingDetailsById(booking_id);
            booking.setReturnCode(ReturnCodes.SUCCESS);
            return new ResponseEntity<BookingHistoryResponse>(booking, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<BookingHistoryResponse>(booking, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/findByCommuterId/{commuter_id}", method = RequestMethod.GET)
    public @ResponseBody List<BookingHistoryResponse> getBookingByCommuterId(@PathVariable int commuter_id) {
        List<BookingHistoryResponse> bookings = null;
        try {
            bookings = bookingService.toBookingHisotry(bookingService.getBookingByCommuterId(commuter_id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookings;
    }

    @RequestMapping(value = "/bookNow", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<BookingResponse> bookNow(@RequestBody BookingDTO booking) {
        System.out.println("booking:" + booking);
        BookingResponse bookingResp = new BookingResponse();
        boolean bookingMsgToBeSent = booking.isSms_send_enabled();
        try {
            bookingResp = bookingService.bookNow(booking, bookingMsgToBeSent);
            // Dont add the Return code, as that is already set in bookNow
            // method with various return codes.
            // bookingResp.setReturnCode(ReturnCodes.BOOKING_SUCCESSFUL);
            return new ResponseEntity<BookingResponse>(bookingResp, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            bookingResp.setReturnCode(ReturnCodes.BOOKING_FAILED);
            return new ResponseEntity<BookingResponse>(bookingResp, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/cancelBooking/{booking_id}", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<BookingCancellationResponse> cancelRide(@PathVariable Integer booking_id) {
        BookingCancellationResponse bookingCancellationResp = new BookingCancellationResponse();
        try {
            bookingCancellationResp = bookingService.cancelRide(booking_id);
            if (bookingCancellationResp.getReturnCode() == null) {
                bookingCancellationResp.setReturnCode(ReturnCodes.BOOKING_CANCELLATION_SUCCESSFUL);
            }
            return new ResponseEntity<BookingCancellationResponse>(bookingCancellationResp, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            bookingCancellationResp.setReturnCode(ReturnCodes.BOOKING_CANCELLATION_FAILED);
            return new ResponseEntity<BookingCancellationResponse>(bookingCancellationResp,
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/pre_booking_details", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Response> getPreBookingDetails(@RequestBody PreBookingDTO preBooking) {
        Response resp = new Response();
        PreBookingDetails prebooking_details = null;
        try {
            prebooking_details = bookingService.getPreBookingDetails(preBooking.getCommuter_id(), preBooking.getNum_seats_choosen(), preBooking.getTrip_id(),
                    preBooking.getFrom_stop_id(), preBooking.getTo_stop_id());
            resp.response = prebooking_details;
            resp.returnCode = ReturnCodes.SUCCESS.name();
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp.response = null;
            resp.returnCode = ReturnCodes.UNKNOWN_ERROR.name();
            return new ResponseEntity<Response>(resp, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
    
    @RequestMapping(value = "/pre_booking_credit_details", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Response> getPreCreditDetails(@RequestBody PreBookingDTO preBooking) {
        Response resp = new Response();
        PreBookingCreditDetails prebooking_details = null;
        try {
            prebooking_details = bookingService.getPreBookingCreditDetails(preBooking.getCommuter_id(), preBooking.getNum_seats_choosen(), preBooking.getTrip_id(),
                    preBooking.getFrom_stop_id(), preBooking.getTo_stop_id());
            resp.response = prebooking_details;
            resp.returnCode = ReturnCodes.SUCCESS.name();
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp.response = null;
            resp.returnCode = ReturnCodes.UNKNOWN_ERROR.name();
            return new ResponseEntity<Response>(resp, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
    
    @RequestMapping(value = "/get_booking_stop_details/{booking_id}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Response> getBookingStopDetails(@PathVariable Integer booking_id) {
        Response resp = new Response();
        BookingStopDetailsResponse bookingStopsDetails = null;
        try {
            bookingStopsDetails = bookingService.getBookingStopDetails(booking_id);
            resp.response = bookingStopsDetails;
            resp.returnCode = ReturnCodes.SUCCESS.name();
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp.response = null;
            resp.returnCode = ReturnCodes.UNKNOWN_ERROR.name();
            return new ResponseEntity<Response>(resp, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
    
    @RequestMapping(value = "/register_notify_availability", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Response> registerNotifyAvailability(@RequestBody RegisterNotifyAvailabilityDTO dto) {
        Response resp = new Response();
        RegisterNotifyAvailabilityResponse response = new RegisterNotifyAvailabilityResponse();
        String message = "Thank you! We will notify you if seats become available for this shuttle.";
        try {
            boolean requestStatus = bookingService.registerNotifyAvailability(dto.getCommuter_id(), dto.getTrip_id(),
                    dto.getFrom_stop_id(), dto.getTo_stop_id());

            response.setTrip_id(dto.getTrip_id());
            response.setCommuter_id(dto.getCommuter_id());
            response.setRequest_status(requestStatus);
            response.setMessage(message);

            resp.response = response;
            resp.returnCode = ReturnCodes.SUCCESS.name();
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp.response = null;
            resp.returnCode = ReturnCodes.UNKNOWN_ERROR.name();
            return new ResponseEntity<Response>(resp, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

}
