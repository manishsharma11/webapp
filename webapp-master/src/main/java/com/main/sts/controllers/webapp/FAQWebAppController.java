package com.main.sts.controllers.webapp;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.main.sts.dto.Response;
import com.main.sts.entities.FAQ;
import com.main.sts.service.FAQService;
import com.main.sts.service.ReturnCodes;

@Controller
@RequestMapping("/webapp")
public class FAQWebAppController {

    @Autowired
    private FAQService faqService;

    static final Logger logger = Logger.getLogger(FAQWebAppController.class);

    @RequestMapping(value = "/faq", method = RequestMethod.GET)
    public ModelAndView getFAQs(ModelAndView model) {
        List<FAQ> faqs = null;
        try {
            faqs = faqService.findAll();
            model.addObject("faqs", faqs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.setViewName("/webapp/faq");
        model.addObject("current_page", "faq");
        return model;
    }

}