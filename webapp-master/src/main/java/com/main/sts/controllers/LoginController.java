package com.main.sts.controllers;

import java.security.Principal;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.main.sts.entities.Commuter;
import com.main.sts.service.CommuterService;

@Controller
public class LoginController extends SavedRequestAwareAuthenticationSuccessHandler {
    
    @Autowired
    private CommuterService commuterService;
    
//    @RequestMapping(value = "/login1")
//    public String hello(
//            @CookieValue(value = "easycommute1", defaultValue = "0") Long hitCounter,
//            HttpServletResponse response) {
// 
//        // increment hit counter
//        hitCounter++;
// 
//        // create cookie and set it in response
//        Cookie cookie = new Cookie("easycommute1", hitCounter.toString());
//        cookie.setMaxAge(10000000);
//        response.addCookie(cookie);
// 
//        // render hello.jsp page
//        return "redirect:/login2";
//    }
//    
//    @RequestMapping(value = "/login2")
//    public String hello1(HttpServletRequest req,
//            HttpServletResponse response) {
// 
//        System.out.println(req.getCookies());
//        for (Cookie c : req.getCookies()) {
//            System.out.println(c.getName()+"--"+c.getValue());
//        }
// 
//        // render hello.jsp page
//        return "hello";
//    }
//    
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String loginPage(@CookieValue(value="easycommute", required=false) String ecCookie, ModelMap model, Principal principal, HttpSession session, HttpServletRequest request) {
//        // //System.out.println(" DB ==> "+loginService.getAdminDetails("admin",
//        // "admin123"));ecCookie
//        if(ecCookie != null) {
//            System.out.println("Cookie is "+ ecCookie);
//            session = request.getSession();
//            session.setAttribute("ecCookie", ecCookie);
//            return "redirect:/webapp/commuter/login_directly";
//        }
//        else {
//        return "redirect:/login";
//        }
//    }
    
    
//    @RequestMapping(value = "/weblogin", method = RequestMethod.GET)
//    public String loginPage(HttpSession session, HttpServletRequest request) {
//     
//        System.out.println(request.getCookies());
//        for (Cookie c : request.getCookies()) {
//            System.out.println(c.getName() + "--" + c.getValue());
//            String cname = c.getName();
//            if (cname.equals("ece")) {
//                String ecCookie = c.getValue();
//                session = request.getSession();
//                session.setAttribute("ecCookie", ecCookie);
//                return "redirect:/webapp/commuter/login_directly";
//            }
//        }
//        return "redirect:/login";
//    }
    
    @RequestMapping(value = "/")
    public String loginPage(ModelMap model, Principal principal, HttpSession session, HttpServletRequest request) {
       
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                String cname = cookie.getName();
                if (cname.equals("easycommute")) {
                    String ecCookie = cookie.getValue();
                    session = request.getSession();
                    session.setAttribute("ecCookie", ecCookie);
                    return "/webapp/commuter/login_directly";
                }
            }
        }
       
        return "redirect:/login";
    }
    
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String login(HttpSession session, HttpServletRequest request, ModelMap model, Principal principal) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//        if (!(auth instanceof AnonymousAuthenticationToken)) {
//            // System.out.println("test"+auth.getAuthorities());
//            if (request.isUserInRole("ROLE_ADMIN")) {
//                return "redirect:/school_admin/homepage";
//            } else
//                return "redirect:/404";
//        } else
//            return "login";
//    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpSession session, HttpServletRequest request, ModelMap model, Principal principal) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            // System.out.println("test"+auth.getAuthorities());
            if (request.isUserInRole("ROLE_ADMIN")) {
                // return "redirect:/school_admin/homepage";
                return "redirect:/school_admin/homepage";
            } else if (request.isUserInRole("ROLE_USER")) {
                // return "redirect:/school_admin/homepage";
                return "redirect:/webapp/commuter/my_profile";
            } else {
                return "redirect:/404";
            }
        } else {
            return "login";
        }
    }
    
    @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
    public String loginerror(ModelMap model) {
        model.addAttribute("error", "true");
        return "forward:login";

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ModelMap model) {
        model.addAttribute("logout", "successfull");
        return "redirect:login";
    }
    
    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String accessDenied(ModelMap model) {
        return "404";

    }
    
    @RequestMapping(value = "/weblogin", method = RequestMethod.POST)
    public String login(HttpSession session, ModelAndView model, HttpServletRequest request,
            HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        String mobile = request.getParameter("mobile");
        String otp = request.getParameter("otp");
        String password = request.getParameter("password");
        String flag = request.getParameter("signin_flag");
        if (flag.equals("otp")) {

        } else if (flag.equals("password")) {

        }
        return "redirect:/login_process";
    }

}