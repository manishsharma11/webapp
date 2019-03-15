package com.main.sts;

import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;

import com.main.sts.dto.BookingDTO;
import com.main.sts.dto.response.BookingCancellationResponse;
import com.main.sts.dto.response.BookingResponse;
import com.main.sts.dto.response.CommuterBookingResponse;
import com.main.sts.dto.response.CommuterResponse;
import com.main.sts.dto.response.PreBookingDetails;
import com.main.sts.dto.response.StopsResponseDTO;
import com.main.sts.entities.Booking;
import com.main.sts.entities.BookingWebDTO;
import com.main.sts.service.BookingService;

public class BookingServiceTest extends BaseTest {

    @Resource
    private BookingService bookingService;

    @Test
    public void testFindAll() {
        Assert.assertFalse(bookingService.findAll().isEmpty());
        for (Booking c : bookingService.findAll()) {
            System.out.println(c.getBus_id() + "--" + c.getBooking_travel_date() + "--"
                    + c.getBooking_travel_date_time());
        }
    }

    @Test
    public void testBookNow() {
        // Assert.assertFalse(bookingService.findAll().isEmpty());
        String email = "rahul@easycommute.co1";
        String mobile = "919908599937";

        int commuter_id = 766;
//        int trip_id = 6634;
        int trip_id = 234;

        // route_id= 12656
        // trip_id = 13496
        Integer from_stop_code = 33;//127;
        Integer to_stop_code = 34;//130;// 34;

        BookingDTO booking = new BookingDTO();
        booking.setCommuter_id(commuter_id);
        booking.setTrip_id(trip_id);
        booking.setFrom_stop_id(from_stop_code);
        booking.setTo_stop_id(to_stop_code);
        booking.setNum_seats_choosen(1);

        boolean bookingMsgToBeSent = false;
        BookingResponse bookingResp = null;
        try {
            bookingResp = bookingService.bookNow(booking, bookingMsgToBeSent);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(bookingResp);
        System.out.println("bookingResp:" + bookingResp);
    }

    @Test
    public void testCancelRideNow() {
        // Assert.assertFalse(bookingService.findAll().isEmpty());
        int booking_id = 1387;//1410;//53;

        BookingCancellationResponse bookingCancellationResp = null;
        try {
            bookingCancellationResp = bookingService.cancelRide(booking_id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(bookingCancellationResp);
        System.out.println("bookingCancellationResp:" + bookingCancellationResp);
    }

    @Test
    public void testPreBookingDetails() {
        int commuter_id = 101;//742;//1;
//        int trip_id = 6634;
        int trip_id = 1;//2;
        int num_seats_choosen =1 ;

        // trip_id = 7610;
        // route_id= 12656
        // trip_id = 13496
        Integer source_stop_id = 33;//14;//33;// 130;//127;
        Integer dest_stop_id = 34;//4;//34;// 127;//130;// 34;
        try {
            PreBookingDetails preBookingDetails = bookingService.getPreBookingDetails(commuter_id, num_seats_choosen, trip_id, source_stop_id,
                    dest_stop_id);
            System.out.println(preBookingDetails);
            for (StopsResponseDTO rs : preBookingDetails.getStops()) {
                System.out.println(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testAllCommutersInATrip() {
        int trip_id = 2;
        List<CommuterBookingResponse> list = bookingService.getAllCommuters(trip_id);
        Assert.assertNotNull(list);
        Assert.assertNotSame(0, list.size());
        for (CommuterBookingResponse cbr : list) {
            System.out.println(cbr.getStop_name());
            for (CommuterResponse c : cbr.getCommuters()) {
                System.out.println("\t" + c.getBooking_id()+"--"+c.getCommuter_name() + "--" + c.getPickup_stop_id() +"--"+c.getType());
            }
        }
    }
    
    @Test
    public void testSendASMSBookingConfirmation(){
        int booking_id = 62752;
        // Note: its important.
        boolean bookingMsgToBeSent = false;
        Booking booking =  bookingService.getBookingById(booking_id);
        bookingService.sendBookingConfirmationSMS(booking, bookingMsgToBeSent);
    }
    
    @Test
    public void testBookingCancellationSMS(){
        int booking_id = 62752;
        // Note: its important.
        boolean bookingMsgToBeSent = false;
        Booking booking =  bookingService.getBookingById(booking_id);
        bookingService.sendBookingCancellationSMS(booking, bookingMsgToBeSent);
    }
    
    @Test
    public void testMarkBookingExpired(){
        int trip_id = 226;
        // Note: its important.
        boolean sendEnabled = true;
        bookingService.markBookingExpired(trip_id, sendEnabled);
    }
    
    @Test
    public void testGetAllBookingDetailsWithCommuters(){
        List<BookingWebDTO> bookings = bookingService.getBookingWithCommuterDetails();
        for (BookingWebDTO b : bookings) {
            System.out.println(b);
        }
    }
}
