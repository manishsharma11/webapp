package com.main.sts.controllers.webapp;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.main.sts.dto.AccountDTO;
import com.main.sts.dto.BookingDTO;
import com.main.sts.dto.FareDTO;
import com.main.sts.dto.PreBookingDTO;
import com.main.sts.dto.Response;
import com.main.sts.dto.ShuttleTimingsDTO;
import com.main.sts.dto.StopsDTO;
import com.main.sts.dto.TripDTO;
import com.main.sts.dto.response.BookingCancellationResponse;
import com.main.sts.dto.response.BookingHistoryResponse;
import com.main.sts.dto.response.BookingResponse;
import com.main.sts.dto.response.PreBookingCreditDetails;
import com.main.sts.dto.response.PreBookingDetails;
import com.main.sts.dto.response.StopsResponseDTO;
import com.main.sts.entities.Booking;
import com.main.sts.entities.Commuter;
import com.main.sts.entities.Session;
import com.main.sts.service.BookingService;
import com.main.sts.service.ReturnCodes;
import com.main.sts.service.StopsService;
import com.main.sts.service.TripService;

@Controller
@RequestMapping("/webapp/booking")
public class BookingWebAppController extends CommonController {
    
    @Autowired
    private TripService tripService;
    
    @Autowired
    private StopsWebAppController swc;
    
    @Autowired
    private BookingService bookingService;
    
    @Autowired
    private StopsService stopsService;
    
    @Autowired
    private TransactionWebAppController twc;

    static final Logger logger = Logger.getLogger(BookingWebAppController.class);
    
    @RequestMapping(value = "/my_bookings")
    public ModelAndView getBookingByCommuterId(ModelAndView model, HttpServletRequest request, HttpSession session) {
        List<BookingHistoryResponse> bookings = null;       
        
        try {
//            boolean loggedin = super.isLoggedIn(request);
//            if (!loggedin) {
//                model.setViewName("/login");
//                return model;
//            }
            Commuter commuter = getCommuter(request);
            int commuter_id = commuter.getCommuter_id();
            bookings = bookingService.toBookingHisotry(bookingService.getBookingByCommuterId(commuter_id));
       
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addObject("bookings", bookings);
        model.setViewName("/webapp/my_bookings");
        return model;
    }
    
    @RequestMapping(value = "/bookingPage")
    public ModelAndView showBookingPage(ModelAndView model, HttpServletRequest request) {
        
        try {
//            boolean loggedin = super.isLoggedIn(request);
//            if (!loggedin) {
//                model.setViewName("/login");
//                return model;
//            }
        
        HttpSession session = super.getSession(request);
        session.removeAttribute("booking_id");

       // StopsWebAppController swc = new StopsWebAppController();
        List<StopsDTO> list  = swc.getFromStops();
//        String from_list = list.toString();
//        System.out.println("List Stops Booking"+from_list);
//        model.addObject("from_list", from_list);
        model.addObject("from_list", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.setViewName("/webapp/book_now");
        return model;
    }
    
    private ModelAndView redirectToBookNow(String error_message, RedirectAttributes redir){
        redir.addFlashAttribute("error",error_message);
        return new ModelAndView("redirect:/webapp/booking/bookingPage");
    }
    
    @RequestMapping(value = "/available_shuttles", method = RequestMethod.POST)
    public ModelAndView showAvailableShuttles(ModelAndView model, HttpServletRequest request, PreBookingDTO preBookingDTO, RedirectAttributes redir) {
        
        try {
//            boolean loggedin = super.isLoggedIn(request);
//            if (!loggedin) {
//                model.setViewName("/login");
//                return model;
//            }
        
            Commuter commuter1= getCommuter(request);
            
        HttpSession session = super.getSession(request);
        System.out.println("session11:"+session);

        session.removeAttribute("booking_id");
        
        System.out.println("session11:"+session);

        String from_stop = request.getParameter("from_stop_id");
        String to_stop = request.getParameter("to_stop_id");
        
        if (from_stop != null && (from_stop.equals("null")||from_stop.equals(""))) {
            return redirectToBookNow("Please Choose Pick Up Point!", redir);
        }
        
        if (to_stop != null && (to_stop.equals("null")||to_stop.equals(""))) {
            return redirectToBookNow("Please Choose Drop Off Point!", redir);
        }
        
        if (from_stop == null) {
            return redirectToBookNow("Please Choose Pick Up Point!", redir);
        }
        
        if (to_stop == null) {
            return redirectToBookNow("Please Choose Drop Off Point!", redir);
        }
        
        int num_seats_choosen = 1;
        String seats = request.getParameter("num_seats");
        if (seats != null) {
            num_seats_choosen = Integer.parseInt(seats);
        }
        
        Commuter commuter = getCommuter(request);
        int commuter_id = commuter.getCommuter_id();
        
        int from_stop_id = Integer.parseInt(from_stop);
        int to_stop_id = Integer.parseInt(to_stop);
        String from = stopsService.getStop(from_stop_id).getStop_name();
        String to = stopsService.getStop(to_stop_id).getStop_name();
        preBookingDTO.setCommuter_id(commuter_id);
        preBookingDTO.setFrom_stop_id(from_stop_id);
        preBookingDTO.setTo_stop_id(to_stop_id);
        preBookingDTO.setNum_seats_choosen(num_seats_choosen);
        session.setAttribute("preBookingDTO", preBookingDTO);
        List<ShuttleTimingsDTO> trips = null;
        
        trips = tripService.getTodayAndTomorrowTrips(from_stop_id, to_stop_id);
       
        model.addObject("from_stop", from);
        model.addObject("to_stop", to);
        model.addObject("num_seats_choosen", num_seats_choosen);
        model.addObject("trips", trips);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.setViewName("/webapp/available_shuttles");
        return model;
    }
    
    @RequestMapping(value = "/pre_booking_details", method = RequestMethod.POST)
    public ModelAndView getPreBookingDetails(ModelAndView model, HttpServletRequest request, PreBookingDTO preBookingDTO) {
        
        try {
//            boolean loggedin = super.isLoggedIn(request);
//            if (!loggedin) {
//                model.setViewName("/login");
//                return model;
//            }
     
            HttpSession session = super.getSession(request);
            session.removeAttribute("booking_id");
            
            PreBookingDetails preBookingDetails = null;
            preBookingDTO = (PreBookingDTO) session.getAttribute("preBookingDTO");
            int trip_id = Integer.parseInt(request.getParameter("trip_id"));
            preBookingDTO.setTrip_id(trip_id);
            session.setAttribute("preBooking", preBookingDTO);
            
            preBookingDetails = bookingService.getPreBookingDetails(preBookingDTO.getCommuter_id(),
                    preBookingDTO.getNum_seats_choosen(), preBookingDTO.getTrip_id(), preBookingDTO.getFrom_stop_id(),
                    preBookingDTO.getTo_stop_id());
            
            model.addObject("preBookingDTO", preBookingDTO);
            model.addObject("preBookingDetails", preBookingDetails);
            model.addObject("status", "success");
        } catch (Exception e) {
            e.printStackTrace();
            model.addObject("status", "failure");
        }
        model.setViewName("/webapp/prebooking_details");
        return model;
    }
    
    @RequestMapping(value = "/bookNow", method = RequestMethod.GET)
    public ModelAndView bookNow(ModelAndView model, HttpServletRequest request, HttpSession session) {
        BookingResponse bookingResp = new BookingResponse();
        try {
//            boolean loggedin = super.isLoggedIn(request);
//            if (!loggedin) {
//                model.setViewName("/login");
//                return model;
//            }
        
            HttpSession session1 = super.getSession(request);
            if (session1.getAttribute("booking_id") != null) {
                return showBookingPage(model, request);
            }
            
            Commuter commuter = getCommuter(request);
            int commuter_id = commuter.getCommuter_id();
            System.out.println("Commuter " + commuter_id);
            
            session = super.getSession(request);
            PreBookingDTO preBookingDTO = (PreBookingDTO) session.getAttribute("preBooking");
            
            BookingDTO bookingDTO = new BookingDTO();
            bookingDTO.setCommuter_id(preBookingDTO.getCommuter_id());
            bookingDTO.setFrom_stop_id(preBookingDTO.getFrom_stop_id());
            bookingDTO.setTo_stop_id(preBookingDTO.getTo_stop_id());
            bookingDTO.setTrip_id(preBookingDTO.getTrip_id());
            bookingDTO.setNum_seats_choosen(preBookingDTO.getNum_seats_choosen());
            
            System.out.println("bookingDTO:" + bookingDTO);

            boolean bookingMsgToBeSent = bookingDTO.isSms_send_enabled();
    
            bookingResp = bookingService.bookNow(bookingDTO, bookingMsgToBeSent);
            // Dont add the Return code, as that is already set in bookNow
            // method with various return codes.
            // bookingResp.setReturnCode(ReturnCodes.BOOKING_SUCCESSFUL);
            System.out.println("bookingResp" + bookingResp);
            if(bookingResp.getReturnCode() == ReturnCodes.BOOKING_NOT_ENOUGH_BALANCE) {
                model.addObject("status", "NOT_ENOUGH_BALANCE");
                return twc.getMyWallet(model, session, request);
            }
            BookingHistoryResponse booking = bookingService.getBookingDetailsById(bookingResp.getBooking_id());
  
            model.addObject("status", "success");
            model.setViewName("/webapp/boarding_pass");
            model.addObject("booking", booking);
            
            session.setAttribute("booking_id", booking.getBooking_id());
        } catch (Exception e) {
            e.printStackTrace();
            bookingResp.setReturnCode(ReturnCodes.BOOKING_FAILED);
            model.addObject("status", "failure");
            model.setViewName("/webapp/booking_information");
        }
        return model;
    }
    
    @RequestMapping(value = "/boarding_pass", method=RequestMethod.POST)
    public ModelAndView showBoardingPass(ModelAndView model, HttpServletRequest request) {
        try {
//            boolean loggedin = super.isLoggedIn(request);
//            if (!loggedin) {
//                model.setViewName("/login");
//                return model;
//            }
        
            BookingHistoryResponse booking = null;
            int booking_id = 0;
            String bookingId = request.getParameter("booking_id");
            if (bookingId != null && bookingId != "") {
                booking_id = Integer.parseInt(bookingId);  
            }
            
            booking = bookingService.getBookingDetailsById(booking_id);
            System.out.println("Boarding Pass "+booking);
            model.addObject("booking", booking);
            HttpSession session = super.getSession(request);
            session.setAttribute("booking_id", booking_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.setViewName("/webapp/boarding_pass");
        return model;     
    }
    
    @RequestMapping(value = "/cancelBooking", method = RequestMethod.POST)
    public ModelAndView cancelRide(ModelAndView model, HttpServletRequest request) {
        BookingCancellationResponse bookingCancellationResp = new BookingCancellationResponse();
        try {
            int booking_id = Integer.parseInt(request.getParameter("booking_id"));
            bookingCancellationResp = bookingService.cancelRide(booking_id);
            if (bookingCancellationResp.getReturnCode() == null) {
                bookingCancellationResp.setReturnCode(ReturnCodes.BOOKING_CANCELLATION_SUCCESSFUL);
            }
            model.addObject("status", "booking_cancelled");
            model.addObject("bookingCancellationResp", bookingCancellationResp);
        } catch (Exception e) {
            e.printStackTrace();
            model.addObject("status", "booking_cancellation_failed");
        }
        model.setViewName("/webapp/booking_information");
        return model;
    }
 
}