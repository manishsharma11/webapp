package com.main.sts.controllers.webapp.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.filter.OncePerRequestFilter;

public class SessionCheckSecurityFilter extends OncePerRequestFilter {

    private String[] ignore = {"login_process", "login", "register", "regenrateOTP", "registerCommuter",
            "verifyCommuter", "setPassword"
    };
    
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {

        System.out.println("got request1");

        String url = ((HttpServletRequest) req).getRequestURL().toString();
        String queryString = ((HttpServletRequest) req).getQueryString();
        System.out.println(url + "?" + queryString);

        String appContext = req.getContextPath();
        HttpSession session = req.getSession(false);

        // ignore if url is for login. otherwise it will block from logging in.
        if (session != null) {
            // ignore if these urls should be accessed without user to be
            // loggedin
            boolean checkRequired = true;
            for (String pattern : ignore) {
                if (url.contains(pattern)) {
                    checkRequired = false;
                    break;
                }
            }
            if (checkRequired) {
                Object authetication_done = session.getAttribute("authetication_done");
                System.out.println("authetication_done:" + authetication_done);
                if (authetication_done != null) {
                    System.out.println("authetication_done:1");
                    Object autheticated = session.getAttribute("autheticated");
                    System.out.println("autheticated:" + autheticated);

                    if (autheticated == null) {
                        res.sendRedirect(appContext + "/login");
                        return;
                    }
                } else {
                    System.out.println("authetication_done:2");
                    res.sendRedirect(appContext + "/login");
                    return;
                }
            }
        }

        // final step
        chain.doFilter(req, res);
    }

}