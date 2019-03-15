package com.main.sts.controllers.rest;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.main.sts.dto.Response;
import com.main.sts.entities.FAQ;
import com.main.sts.service.FAQService;
import com.main.sts.service.ReturnCodes;

@Controller
@RequestMapping("/faq")
public class FAQController {

    @Autowired
    private FAQService faqService;

    static final Logger logger = Logger.getLogger(FAQController.class);

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<FAQ>> getFAQs() {
        List<FAQ> faqs = null;
        Response resp = new Response();
        try {
            faqs = faqService.findAll();
            resp.response = faqs;
            resp.setReturnCode(ReturnCodes.SUCCESS.name());
            return new ResponseEntity<List<FAQ>>(faqs, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp.response = null;
            resp.setReturnCode(ReturnCodes.UNKNOWN_ERROR.name());
            return new ResponseEntity<List<FAQ>>(faqs, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

}
