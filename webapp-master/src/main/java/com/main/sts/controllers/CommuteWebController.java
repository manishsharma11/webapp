package com.main.sts.controllers;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.main.sts.controllers.rest.CommuterController;
import com.main.sts.dto.RefundDTO;
import com.main.sts.dto.response.TransactionResponse;
import com.main.sts.entities.Booking;
import com.main.sts.entities.Commuter;
import com.main.sts.entities.Drivers;
import com.main.sts.entities.TransactionInfo;
import com.main.sts.entities.Trips;
import com.main.sts.service.BookingService;
import com.main.sts.service.CommuterService;
import com.main.sts.service.ReferralCodeService;
import com.main.sts.service.ReturnCodes;
import com.main.sts.service.TransactionService;
import com.main.sts.util.MD5PassEncryptionClass;
import com.main.sts.util.PasswordEncrypter;
import com.main.sts.util.RolesUtility;
import com.main.sts.util.SystemConstants;

@Controller
@RequestMapping("/school_admin/commuter")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_GUEST','ROLE_CUSTOMER_SUPPORT','ROLE_OPERATOR')")
public class CommuteWebController {

    @Autowired
    private CommuterService commuterService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private RolesUtility rolesUtility;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private ReferralCodeService referralCodeService;
    
    static final Logger logger = Logger.getLogger(CommuterController.class);

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView getCommutersList(ModelAndView view, HttpServletRequest request) {
        List<Commuter> commuterList = null;
        BigInteger count = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        try {
            Integer offset = (request.getParameter("offset") != null
                    && !request.getParameter("offset").trim().isEmpty())
                            ? Integer.parseInt(request.getParameter("offset"))
                            : 1;
            Integer limit = (request.getParameter("limit") != null && !request.getParameter("limit").trim().isEmpty())
                    ? Integer.parseInt(request.getParameter("limit"))
                    : SystemConstants.recordsPerPage;
            count = commuterService.getCommutersCount();
            commuterList = commuterService.getCommutersList(offset, limit);
            view.addObject("commuterList", commuterList);
            view.addObject("recordsCount", count);
            view.addObject("limit", limit);
            view.addObject("offset", offset);
            view.addObject("recordsPerPage", SystemConstants.recordsPerPage);
            view.addObject("login_name", auth.getName());
            view.addObject("login_role", rolesUtility.getRole(request));
        } catch (Exception e) {
            e.printStackTrace();
        }
        view.setViewName("/school_admin/commuter/list");
        view.addObject("current_page", "commuter");
        return view;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView getCommuter(HttpServletRequest request, ModelAndView view) {
        Commuter commuter = null;

        try {
            commuter = commuterService.getCommuterById(Integer.parseInt(request.getParameter("id")));
            view.addObject("commuter", commuter);
            view.setViewName("/school_admin/commuter/edit");
            // System.out.println(commuter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        view.addObject("current_page", "commuter");
        return view;
    }

    @RequestMapping(value = "/editaction", method = RequestMethod.POST)
    public ModelAndView editAtion(HttpServletRequest request, ModelAndView view) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        
//        Timestamp created_at = null;
//        if (request.getParameter("created_at") != null && !request.getParameter("created_at").isEmpty()) {
//            Timestamp timestamp = Timestamp.valueOf(sdf.format(sdf.parse(request.getParameter("created_at"))));
//            created_at = timestamp;
//        }
//
//        Timestamp verified_at = null;
//        if (request.getParameter("verified_at") != null && !request.getParameter("verified_at").isEmpty()) {
//            Timestamp timestamp = Timestamp.valueOf(sdf.format(sdf.parse(request.getParameter("verified_at"))));
//            verified_at = timestamp;
//        }

        int commuter_id = Integer.parseInt(request.getParameter("id"));
        int active = Integer.parseInt(request.getParameter("active"));
        //String apikey = request.getParameter("apikey");
        // System.out.println(request.getParameter("created_at"));

        String email = request.getParameter("email");
        //String gcm_reg_id = request.getParameter("gcm_reg_id");
        String geneder = request.getParameter("gender");
        //String mobile = request.getParameter("mobile");
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        String hashPassword = PasswordEncrypter.encryptText(password);

        int status = Integer.parseInt(request.getParameter("status"));

        // System.out.println(commuter);
        try {
            boolean updated = commuterService.updateCommuterDetails(commuter_id, name, email, geneder, hashPassword, status, active);
            Commuter commuter = commuterService.getCommuterById(commuter_id);
            view.addObject("commuter", commuter);
            view.addObject("updated", true);
            view.setViewName("/school_admin/commuter/edit");
            // System.out.println("updated.......................");
        } catch (Exception e) {
            e.printStackTrace();
        }
        view.addObject("current_page", "commuter");
        return view;
    }

    @RequestMapping(value = "/transactionhistory", method = RequestMethod.GET)
    public ModelAndView transactionHistory(ModelAndView view, HttpServletRequest request) {
        List<TransactionInfo> transactionInfo = null;
        Commuter commuter = null;
        try {
            transactionInfo = transactionService
                    .getTransactionsByCommuterId(Integer.parseInt(request.getParameter("id")));
            commuter = commuterService.getCommuterById(Integer.parseInt(request.getParameter("id")));
            System.out.println(transactionInfo);
            view.addObject("comm", commuter);
            view.addObject("transactionInfo", transactionInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        view.setViewName("/school_admin/commuter/transactionhistory");
        view.addObject("current_page", "commuter");
        return view;
    }

    @RequestMapping(value = "/bookinghistory", method = RequestMethod.GET)
    public ModelAndView bookinghistory(ModelAndView view, HttpServletRequest request) {
        Commuter commuter = null;
        List<Booking> bookings = null;
        try {
            bookings = bookingService.getBookingByCommuterId(Integer.parseInt(request.getParameter("id")));
            commuter = commuterService.getCommuterById(Integer.parseInt(request.getParameter("id")));
            System.out.println(bookings);
            view.addObject("bookings", bookings);
            view.addObject("comm", commuter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        view.setViewName("/school_admin/commuter/bookings");
        view.addObject("current_page", "commuter");
        return view;
    }

    @RequestMapping(value = "/refund", method = RequestMethod.GET)
    public ModelAndView refund(HttpServletRequest request, ModelAndView view) {

        Commuter commuter = null;
        try {
            commuter = commuterService.getCommuterById(Integer.parseInt(request.getParameter("id")));
            view.setViewName("/school_admin/commuter/refund");
            view.addObject("comm", commuter);
            // System.out.println(commuter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        view.addObject("current_page", "commuter");
        return view;
    }

    @RequestMapping(value = "/refundaction", method = RequestMethod.POST)
    public ModelAndView refundAction(HttpServletRequest request, ModelAndView view) {

        RefundDTO refundDTO = new RefundDTO();
        List<Commuter> commuterList = null;
        try {
            Integer refundPoints = Integer.parseInt(request.getParameter("refund_points"));
            Integer refundFreeRides = Integer.parseInt(request.getParameter("refund_free_rides"));
            String refundReason = request.getParameter("refund_reason");
            System.out.println("refundReason:"+refundReason);
            // TODO: why we are doing like this?
            commuterList = commuterService.findAll();
            view.addObject("commuterList", commuterList);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            refundDTO.setCommuter_id(Integer.parseInt(request.getParameter("id")));
            refundDTO.setAdmin_id_or_name(auth.getName());
            refundDTO.setRefund_points(refundPoints);
            refundDTO.setRefund_free_rides(refundFreeRides);
            refundDTO.setRefund_reason(refundReason);
            TransactionResponse txResp = transactionService.refund(refundDTO);
            
            System.out.println("txResp:"+txResp);
            if (txResp.getReturnCode() == ReturnCodes.TRANSACION_COMPLETED_SUCCESSFULLY) {
                view.setViewName("/school_admin/commuter/list");
                view.addObject("message", "refund_success");
            } else {
                view.setViewName("/school_admin/commuter/list");
                view.addObject("message", "refund_failure");
                view.addObject("failure_message", txResp.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        view.addObject("current_page", "commuter");
        return view;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView deleteCommuter(HttpServletRequest request, ModelAndView view) {
        Commuter commuter = null;
        /*
         * To check which role use is logged in
         */
        if (request.isUserInRole("ROLE_ADMIN")) {
            view.addObject("login_role", "ROLE_ADMIN");
        }
        //logger.info(model);
        try {
            commuter = commuterService.getCommuterById(Integer.parseInt(request.getParameter("id")));
            view.addObject("commuter", commuter);
            view.setViewName("/school_admin/commuter/delete");
            // System.out.println(commuter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        view.addObject("current_page", "commuter");
        return view;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(value = "/deleteaction", method = RequestMethod.POST)
    public ModelAndView deleteCommuterAction(HttpServletRequest request, ModelAndView view) {
        Commuter commuter = null;

        try {
            commuter = commuterService.getCommuterById(Integer.parseInt(request.getParameter("id")));
            commuterService.deleteCommuter(commuter);
            view.setViewName("redirect:list");
            // System.out.println(commuter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        view.addObject("current_page", "commuter");
        return view;
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.POST)
      public @ResponseBody String searchCommuters(ModelAndView model, HttpServletRequest request, HttpSession session) {
        
       String searchedValue = request.getParameter("search_commuter");
       String searchOption = request.getParameter("searchOption");
       System.out.println(searchedValue + "  " + searchOption);
       
       List<Commuter> searchCommuters = new ArrayList<Commuter>();
       if (searchOption.equals("commuter_id") || searchOption.equals("status")) {
         searchCommuters = commuterService.searchCommuters(searchOption, Integer.valueOf(searchedValue));
       }
       else if (searchOption.equals("name")|| searchOption.equals("email")|| searchOption.equals("mobile")|| searchOption.equals("referral_code_used")|| searchOption.equals("gender")) {
           searchCommuters = commuterService.searchCommuters(searchOption, searchedValue);
       }
       else if (searchOption.equals("referral_code_belongs_to")) {
           searchOption = "commuter_id";
           Integer referralCodeOf = referralCodeService.getCommuterByReferralCode(searchedValue);
           searchCommuters = commuterService.searchCommuters(searchOption, referralCodeOf);
       }
       session.setAttribute("searchCommuters", searchCommuters);
       
       return "/sts/school_admin/commuter/searchedCommuters"; 
      }
    
    @RequestMapping(value = "/searchedCommuters", method = RequestMethod.GET)
    public ModelAndView tripsSearchResponse(ModelAndView model, HttpServletRequest request, HttpSession session) {

      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
        model.setViewName("redirect:/j_spring_security_logout");
        return model;
      }
      DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
      model.addObject("date", formatter.format(new Date()));
      model.addObject("login_name", auth.getName());
      model.addObject("current_page", "list");
      List<Commuter> searchCommuters = (List<Commuter>) session.getAttribute("searchCommuters");
      System.out.println(searchCommuters);
      
      model.addObject("commuterList", searchCommuters);
      
     if (searchCommuters == null || searchCommuters.isEmpty()) {
        model.addObject("error_message", "noMatching");
      }
     
      model.setViewName("/school_admin/commuter/list");
      model.addObject("current_page", "commuter");
      model.addObject("recordsPerPage", SystemConstants.recordsPerPage);
      model.addObject("login_role", rolesUtility.getRole(request));
      return model;
    }
}
