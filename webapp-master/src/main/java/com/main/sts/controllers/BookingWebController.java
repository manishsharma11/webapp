package com.main.sts.controllers;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ec.eventserver.models.DeviceInfo;
import com.ec.eventserver.service.DeviceService;
import com.main.sts.service.BookingService;
import com.main.sts.service.ReturnCodes;
import com.main.sts.util.DateUtil;
import com.main.sts.util.RolesUtility;
import com.main.sts.util.SystemConstants;
import com.main.sts.controllers.rest.BookingController;
import com.main.sts.dto.response.BookingCancellationResponse;
import com.main.sts.entities.Booking;
import com.main.sts.entities.BookingWebDTO;
import com.main.sts.entities.Trips;

@Controller
@RequestMapping(value = "/school_admin/bookings")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_GUEST','ROLE_CUSTOMER_SUPPORT','ROLE_OPERATOR')")
public class BookingWebController {

    @Autowired
    private BookingService bookingService;
    
   @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView bookingsHomePage(ModelAndView model, HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
            model.setViewName("redirect:/j_spring_security_logout");
            return model;
        }
        String tripIDStr = request.getParameter("trip_id");
        boolean isCancelled = false;
        boolean show_isCancelled = false;
        List<BookingWebDTO> bookings = null;
        if (tripIDStr == null) {
            bookings = bookingService.getBookingWithCommuterDetails();
        } else {
            Integer tripID = Integer.parseInt(tripIDStr);
            bookings = bookingService.getBookingWithCommuterDetails(tripID);
        }
        for (BookingWebDTO booking : bookings) {
            System.out.println(booking);
        }
        model.setViewName("/school_admin/bookings/list");
        model.addObject("show_isCancelled", show_isCancelled);
        model.addObject("isCancelled", isCancelled);
        model.addObject("bookings", bookings);
        return model;
    }
    
  
   @RequestMapping(value = "/cancel_booking", method = RequestMethod.GET)
   public ModelAndView cancelBooking(ModelAndView model, HttpServletRequest request) {

       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
           model.setViewName("redirect:/j_spring_security_logout");
           return model;
       }
       boolean isCancelled = false;
       boolean show_isCancelled = true;
       try {
       BookingCancellationResponse bookingCancellationResp = new BookingCancellationResponse();
       int id = Integer.parseInt(request.getParameter("id"));
      
           bookingCancellationResp = bookingService.cancelRide(id);
           if ((bookingCancellationResp.getReturnCode() == ReturnCodes.BOOKING_CANCELLATION_SUCCESSFUL)
                   ||(bookingCancellationResp.getReturnCode() == ReturnCodes.BOOKING_ALREADY_EXPIRED)) {
               isCancelled = true;
           }
           
           List<BookingWebDTO> bookings = bookingService.getBookingWithCommuterDetails();
           model.setViewName("/school_admin/bookings/list");
           model.addObject("bookings", bookings);
       
            model.addObject("id", id);
            model.addObject("isCancelled", isCancelled);
            model.addObject("show_isCancelled", show_isCancelled);
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            return model;
        }
   }
}
