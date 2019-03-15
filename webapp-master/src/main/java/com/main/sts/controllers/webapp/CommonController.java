package com.main.sts.controllers.webapp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.main.sts.entities.Commuter;
import com.main.sts.service.CommuterService;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

@Controller
public class CommonController {
    @Autowired
    private CommuterService commuterService;
    
    public boolean isLoggedIn(HttpServletRequest request) {
        Commuter commuter = null;
        HttpSession session = null;
        try {
            session = request.getSession(false);
            commuter = ((Commuter) session.getAttribute("commuter"));
            System.out.println("commuter obj:"+commuter);
            boolean loggedin = false;
            if (commuter != null) {
                int commuter_id = commuter.getCommuter_id();
                System.out.println("commuter_id:" + commuter_id);
                if (commuter_id > 0) {
                    // setting these variable here so they can be set and filter wont block the reqeusts from entering.
                    HttpSession session1 = getSession(request);
                    session1.setAttribute("authetication_done", true);
                    session1.setAttribute("autheticated", true);
                    loggedin = true;
                }
            }

            if (loggedin) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Commuter getCommuter(HttpServletRequest request) {
        HttpSession session = null;
        try {
            session = request.getSession(false);
            System.out.println("session:"+session);
            Commuter commuter = ((Commuter) session.getAttribute("commuter"));
            //commuter = commuterService.getCommuterById(commuter.getCommuter_id());
            System.out.println("commuter:"+commuter);

            return commuter;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public HttpSession getSession(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        return session;
    }
    
    public HttpSession createNewSession(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        System.out.println("Created new session:"+session);
        return session;
    }
    
    public void addNewObject(HttpServletRequest request, String name, Object value) {
        getSession(request).setAttribute(name, value);
    }
    
    public void addOrUpdateCommuterObject(HttpServletRequest request, Commuter value) {
        addNewObject(request, "commuter", value);
    }
}