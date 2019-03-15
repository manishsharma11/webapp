package com.main.sts.controllers.webapp;

import java.util.Enumeration;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

//import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpSession;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.main.sts.common.CommonConstants.UserChannelType;
import com.main.sts.controllers.rest.CommuterController;
import com.main.sts.dto.CommuterDTO;
import com.main.sts.entities.Commuter;
import com.main.sts.service.CommuterService;
import com.main.sts.service.ReturnCodes;
import com.main.sts.util.MD5PassEncryptionClass;
import com.main.sts.util.PasswordEncrypter;

@Controller
@RequestMapping("/webapp/commuter")
public class CommuterWebAppController extends CommonController {

    @Autowired
    private CommuterService commuterService;
    
    @Autowired
    private BookingWebAppController bookingWebAppController;
    
    static final Logger logger = Logger.getLogger(CommuterController.class);
    
    @RequestMapping(value = "/login_directly", method = RequestMethod.GET)
    public ModelAndView loginDirectly(ModelAndView model, HttpServletRequest request, String mobile, HttpServletResponse response) {
        return loginProcess(model, request, mobile, response);
    }
    
    @RequestMapping(value = "/login_process", method=RequestMethod.POST)
    public ModelAndView loginProcess(ModelAndView model, HttpServletRequest request, String mobile, HttpServletResponse response) {
        //HttpSession session = request.getSession(true);

        String mobile_Str = request.getParameter("mobile");
        String otp_str = request.getParameter("otp");
        String password = request.getParameter("password");
        String flag = request.getParameter("signin_flag");
        String remember_me_str = request.getParameter("remember_me");
        
//        if (mobile_Str == null) {
//            session = request.getSession(false);
//            Commuter commuter = getCommuter(request);
//            mobile_Str = commuterDTO.getMobile();
//            otp_str = String.valueOf(commuterDTO.getOtp());
//            flag = "otp";
//        }
        System.out.println("mobile_Str:" + mobile_Str + "--password:" + password + "--otp:" + otp_str + "--flag:"
                + flag + "--remember_me_str:" + remember_me_str);
        
        if (isLoggedIn(request)) {
            return bookingWebAppController.showBookingPage(model, request);
        }
        
        boolean remember_me = false;
        if (remember_me_str != null) {
            if (remember_me_str.equals("on")) {
                remember_me = true;
            } else {
                remember_me = false;
            }
        }
        
        if (mobile_Str == null || mobile_Str.equals("null") || mobile_Str.equals("")) {
            System.out.println("mobile_Str:"+mobile_Str);
            System.out.println("returning from1:");
            return redirectToLogin("Invalid Mobile Number");
        } else {
            mobile_Str = mobile_Str.trim();
        }
        
        Integer otp = null;
        
        if (otp_str != null) {
            otp_str = otp_str.trim();
            if (otp_str.length() > 0) {
                otp = Integer.parseInt(otp_str);
                System.out.println("otp:"+otp);
                System.out.println("returning from2:");
            } else {
                //return new ModelAndView("login");
                otp = null;
            }
        } else {
            //return new ModelAndView("login");
            otp = null;
        }
        
        if (mobile_Str.length() != 10) {
            System.out.println("mobile_Str:"+mobile_Str);
            System.out.println("returning from3:");
            return redirectToLogin("Invalid Mobile Number");
        }
        
        // both can't be null at a time;
        if (otp_str == null && password == null) {
            System.out.println("otp_str:"+otp_str+"---password:"+password);
            System.out.println("returning from4:");
            return redirectToLogin(
                    "Login with OTP or Password");
        }
        if (password != null && password.equals("")) {
            System.out.println("password:"+password);
            System.out.println("returning from4:");
            return redirectToLogin("Enter Your Password"); 
        }
        
        if ((otp_str != null && password != null) && otp_str.equals("null") && password.equals("null")) {
            System.out.println("otp_str:"+otp_str+"---password:"+password);

            System.out.println("returning from5:");

            return redirectToLogin("Login with OTP or Password");
        }
        
        if (password != null && (password.length() < 6 || password.length() > 14 )) {
            System.out.println("password:"+password);
            System.out.println("returning from:");
            return redirectToLogin("Enter Your Password (6 - 14) Characters"); 
        }
        
        // if otp str is not null and the length is not greater than zero, then show this error.
        if (otp_str != null && !(otp_str.length() > 0)) {
            System.out.println("OTP:" + otp_str);
            System.out.println("returning from4:");
            return redirectToLogin("Invalid OTP");
        }
        
        boolean validated = false;
        
        if (flag != null) {
            System.out.println("flag:" + flag);
            System.out.println("returning from6:");

            if (flag.equals("otp")) {
                if (otp == null) {
                    return redirectToLogin("Invalid OTP");
                }
                ReturnCodes rc = commuterService.validateUserByOTP(mobile_Str, otp);
                if (rc == ReturnCodes.USER_VERIFIED) {
                    validated = true;
                } else {
                    return redirectToLogin("Invalid OTP");
                }
            } else if (flag.equals("password")) {
                validated = commuterService.validateUserByPassword(mobile_Str, password);
                if (validated) {
                    // user is autheniticated.
                } else {
                    return redirectToLogin("Invalid Credentials!");
                }
            }
        } else {
            System.out.println("flag:"+flag);
            System.out.println("returning from7:");
              // it means he is autologging.
            checkAutoLogging(request, response, mobile_Str, remember_me);
        }
        
        if (validated) {
            Commuter commuter1 = commuterService.getCommuterByMobile(mobile_Str);
            
            System.out.println("creating new session");
            this.createNewSession(request);
            
            // Setting these attributes for tracking a loggedin user.
            HttpSession session = super.getSession(request);
            session.setAttribute("authetication_done", true);
            session.setAttribute("autheticated", true);
            
            System.out.println("Added to existign session:"+session);

            // updating in session.
            this.addOrUpdateCommuterObject(request, commuter1);
            
            if(commuter1.getPassword() == null || commuter1.getPassword().equals("")) {
                model.setViewName("/set_password");
                return model;
            }
            
            return bookingWebAppController.showBookingPage(model, request);
        } else {
            return redirectToLogin("Invalid Credentials!");
            // show him error page
        }
    }
    
    
    private ModelAndView redirectToLogin(String error_message){
        return new ModelAndView("login").addObject("error",error_message);
    }

    public void checkAutoLogging(HttpServletRequest request, HttpServletResponse response, String mobile, boolean remember_me){
        Commuter commuter = null;
        HttpSession session = getSession(request);
        String sesid = session.getId();
        try {
            String ecCookie = (String)session.getAttribute("ecCookie");
            
            if(ecCookie != null) {
                String[] data=ecCookie.split("@");
                System.out.println("cookied received:"+data[0]);
                int commuter_id = Integer.parseInt(commuterService.base64decode(""+data[0]));
                commuter = commuterService.getCommuterById(commuter_id);
            }
            else {
                commuter = commuterService.getCommuterByMobile(mobile);
            }
            session.setAttribute("commuter", commuter);
            
            if(remember_me) {
                ecCookie = commuterService.base64encode(""+commuter.getCommuter_id()) + "@" + sesid;
                System.out.println("cookied added:"+ecCookie);
                Cookie cookie = new Cookie("ece", ecCookie);
                cookie.setMaxAge(((Long)TimeUnit.DAYS.toMillis(3)).intValue());// 3 days.
                response.addCookie(cookie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @RequestMapping(value = "/my_profile")
    public ModelAndView showUserProfile(ModelAndView model, HttpServletRequest request) {
        System.out.println("showing user profile");
        Commuter commuter = null;
        try {
            Commuter sessionCommuter = getCommuter(request);
            commuter = commuterService.getCommuterById(sessionCommuter.getCommuter_id());
            
            if (commuter.getPassword() != null) {
                String hashPassword = PasswordEncrypter.decryptText(commuter.getPassword());
                commuter.setPassword(hashPassword);
            }

            if (isLoggedIn(request)) {
                System.out.println("loggedin");
                model.addObject("commuter", commuter);
                model.setViewName("/webapp/my_profile");
                return model;
            } else {
                System.out.println("login");
                return redirectToLogin("System error. Couln't login.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return redirectToLogin("System error. Couln't login.");
    }
    
    @RequestMapping(value = "/user/profile/update", method = RequestMethod.POST)
    public ModelAndView updateUserProfile(ModelAndView model, HttpServletRequest request,
            CommuterDTO commuterDTO) {
        try {
//            boolean loggedin = super.isLoggedIn(request);
//            if (!loggedin) {
//                model.setViewName("/login");
//                return model;
//            }
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String mobile = request.getParameter("mobile");
            String password = request.getParameter("password");
            String cpassword = request.getParameter("cpassword");
            
            if ((password != null && cpassword != null) && !(password.equals(cpassword))) {
                model.addObject("error", "Passwords Does Not Match!");
                model.setViewName("/webapp/my_profile");
                return model;
            }
            
            String hashPassword = PasswordEncrypter.encryptText(password);
            
            Commuter commuter = super.getCommuter(request);
            commuterDTO.setCommuter_id(commuter.getCommuter_id());
            commuterDTO.setName(name);
            commuterDTO.setEmail(email);
            commuterDTO.setMobile(mobile);
            commuterDTO.setPassword(hashPassword);
            
            int commuter_id = commuter.getCommuter_id();
            boolean updated = commuterService.updateCommuterDetails(commuterDTO);
            if (updated) {
                commuter = commuterService.getCommuterById(commuter_id);
                if (commuter.getPassword() != null) {
                    String originalPassword = PasswordEncrypter.decryptText(commuter.getPassword());
                    commuter.setPassword(originalPassword);
                }
                model.addObject("commuter", commuter);
                model.addObject("message", "success");
            } else {
                // NOTE: this message is given to user, so be careful
                model.addObject("commuter", commuterDTO);
                model.addObject("message", "failure");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // NOTE: this message is given to user, so be careful
            model.addObject("commuter", commuterDTO);
            model.addObject("message", "failure");
        }

        model.setViewName("/webapp/my_profile");
        return model;
    }

    @RequestMapping(value = "/register")
    public ModelAndView showRegistrationPage(ModelAndView model, HttpServletRequest request) {
        HttpSession session = request.getSession(); //Creates a new Session 
        model.setViewName("/register");
        return model;
    }
    
    @RequestMapping(value = "/registerCommuter", method = RequestMethod.POST)
    public ModelAndView registerCommuter(ModelAndView model, HttpServletRequest request, HttpSession session, CommuterDTO commuterDTO) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String gender = request.getParameter("gender");
        String gcm_reg_id = "";
        String referral_code = "";
        if(request.getParameter("referral_code") != null) {
            referral_code = request.getParameter("referral_code");
        } 
        String device_id = "";
        boolean sms_send_enabled = true;
        
        commuterDTO.setName(name);
        commuterDTO.setEmail(email);
        commuterDTO.setMobile(mobile);
        commuterDTO.setGender(gender);
        commuterDTO.setGcm_reg_id(gcm_reg_id);
        commuterDTO.setReferral_code(referral_code);
        commuterDTO.setDevice_id(device_id);
        commuterDTO.setSms_send_enabled(sms_send_enabled);
        
        session = request.getSession(false);
        if(session.isNew()) {
            model.setViewName("/login");
            return model;
        }
        //session.setAttribute("commuterDTO", commuterDTO);
        
        UserChannelType channelType = UserChannelType.WEB;
        ReturnCodes returnCode = null;
        try {
            returnCode = commuterService.registerCommuter(name, email, mobile, gender, gcm_reg_id, referral_code, device_id, channelType, sms_send_enabled);
            if (returnCode != null) {
                Commuter commuter = commuterService.getCommuterByMobile(mobile);
                //resp.response = commuter;
                model.addObject("commuter", commuter);
                session.setAttribute("commuter", commuter);
            }
            // resp.response = dto;
            model.addObject("returnCode", returnCode.name());
            // TODO: handle the response.
        } catch (Exception e) {
            e.printStackTrace();
            //resp.response = dto;
            //resp.returnCode = returnCode.name();
           // model.addObject("returnCode", returnCode.name());
            return model;
        }
        model.setViewName("/verification");
        return model;
    }
    
    // it will be called only for new users (those are registering, not the
    // existing users as that will happen in login_process method itself)
    @RequestMapping(value = "/verifyCommuter", method = RequestMethod.POST)
    public ModelAndView verifyCommuter(ModelAndView model, HttpServletRequest request, HttpServletResponse response) {
        Integer otp = null;
        String otp_str = request.getParameter("otp");
        if (otp_str != null && !(otp_str.equals("") || otp_str.equals("null"))) {
            otp = Integer.parseInt(request.getParameter("otp"));
        }
        System.out.println("otp:"+otp);
        
        if (otp == null) {
            model.addObject("message", "Please enter a valid OTP");
            model.setViewName("/verification");
            return model;
        }
//        HttpSession session = request.getSession(false);
//        if(session.isNew()) {
//            model.setViewName("/login");
//            return model;
//        }
        //CommuterDTO commuterDTO= (CommuterDTO)session.getAttribute("commuterDTO");
        //commuterDTO.setOtp(otp);
        //session.setAttribute("dto", commuterDTO);
        
        Commuter commuter = getCommuter(request);
        
        ReturnCodes returnCode = ReturnCodes.UNKNOWN_ERROR;
        try {
            returnCode = commuterService.verifyCommuter(commuter.getMobile(), otp, true);
            if (returnCode != null) {
                commuter = commuterService.getCommuterByMobile(commuter.getMobile());
               // resp.response = commuter;
                System.out.println("Comuter Temp1" + commuter);
                //session.setAttribute("commuter_temp", commuter);
                model.addObject("commuter", commuter);
            }
            String message = "";
            if (returnCode == ReturnCodes.USER_ALREADY_VERIFIED) {
                message = "Already verified";
            } else if (returnCode == ReturnCodes.USER_VERIFIED) {
                message = "Verification succesfful";
            } else if (returnCode == ReturnCodes.USER_VERIFICATION_FAILED) {
                message = "Verification failed";
            }
            model.addObject("message", message);
            
            if(message == "Already verified" || message == "Verification succesfful") {
               // return loginProcess(model, request, commuterDTO.getMobile(), response);
                model.setViewName("/set_password");
                return model;
            }
            else {
                model.setViewName("/verification");
            }
           
            model.addObject("returnCode", returnCode.name());
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            model.addObject("returnCode", returnCode.name());
            model.setViewName("/verification");
            return model;
        }
    }

    @RequestMapping(value = "/regenrateOTP", method = RequestMethod.POST)
    public ModelAndView regenrateOTP(ModelAndView model, HttpServletRequest request,
            HttpServletResponse response) {
        BasicConfigurator.configure();
        System.out.println("Got request for regenerate OTP");

        //CommuterDTO commuterDTO= (CommuterDTO)session.getAttribute("commuterDTO");
        String mobile = request.getParameter("mobile");
        
        // trying to get from session, if still dont have then throw him an error.
        Commuter commuter = getCommuter(request);
        if (mobile == null) {
            mobile = commuter.getMobile();
        }

        System.out.println("mobile:" + mobile);
        if (mobile == null || mobile.equals("null") || mobile.equals("")) {
            //mobile = commuterDTO.getMobile();
            model.addObject("error", "Mobile number can't be empty");
            System.out.println("Mobile no. is empty");
            model.setViewName("/verification");
            return model;
        }

        System.out.println("mobile:"+mobile);
        
        boolean status = commuterService.regenrateOTP(mobile);
        System.out.println("status:"+status);
        if (status) {
            model.addObject("message", "OTP generated");
        } else {
        	// in real it is not going to work, as we are making ajax call.
            System.out.println("Informing New User to Register First");
            return new ModelAndView("redirect:/login");
        }
        System.out.println("returning 1model");

        model.setViewName("/verification");
        return model;
    }

    @RequestMapping(value = "/setPassword", method = RequestMethod.POST)
    public ModelAndView setPassword(ModelAndView model, HttpServletRequest request, HttpSession session,
            HttpServletResponse response) {
        
        String password = request.getParameter("password");
        String cpassword = request.getParameter("cpassword");
        
        if (password != null && cpassword != null && !(password.equals(cpassword))) {
            model.addObject("error", "Password and confirm password doesn't match");
            return model;
        }
        
        String hashPassword = PasswordEncrypter.encryptText(password);

        session = request.getSession(false);

        Enumeration emms = session.getAttributeNames();
        while (emms.hasMoreElements()) {
            System.out.println("Session Attributes : " + emms.nextElement());
        }
        Commuter commuter = getCommuter(request);
        // commuter.setPassword(password);
        System.out.println("Commuter: " + commuter);
        boolean isSetPassword = false;

        try {
            isSetPassword = commuterService.updateCommuterPassword(commuter.getCommuter_id(), hashPassword);

            if (isSetPassword) {
                // setting these variable here so they can be set and filter wont block the reqeusts from entering.
                HttpSession session1 = getSession(request);
                session1.setAttribute("authetication_done", true);
                session1.setAttribute("autheticated", true);
                
                model.addObject("message", "Password Set Successfully");
                return loginProcess(model, request, commuter.getMobile(), response);
            } else {
                model.addObject("message", "failed");
                model.setViewName("/set_password");
                return model;
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addObject("message", "failed");
            model.setViewName("/set_password");
            return model;
        }
    }
    
}
