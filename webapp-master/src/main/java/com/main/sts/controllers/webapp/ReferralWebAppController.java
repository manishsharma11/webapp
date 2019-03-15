package com.main.sts.controllers.webapp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.main.sts.dto.Response;
import com.main.sts.dto.response.ReferralDTO;
import com.main.sts.entities.Commuter;
import com.main.sts.entities.FAQ;
import com.main.sts.entities.ReferralCode;
import com.main.sts.service.ReferralCodeService;
import com.main.sts.service.ReturnCodes;

@Controller
@RequestMapping("/webapp/referral")
public class ReferralWebAppController extends CommonController{

    @Autowired
    private ReferralCodeService referralCodeService;

    static final Logger logger = Logger.getLogger(ReferralWebAppController.class);

    @RequestMapping(value = "/generate")
    public ModelAndView createReferralCodeForACommuter(ModelAndView model, HttpServletRequest request, HttpSession session) {
        ReferralDTO referralCode = null;
        try {
            session = super.getSession(request);
            Commuter commuter = super.getCommuter(request);
            int commuter_id = commuter.getCommuter_id();
            referralCode = referralCodeService.getReferralCode(commuter_id);
            model.addObject("referralCode", referralCode);
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        model.setViewName("/webapp/refer&earn");
        model.addObject("current_page", "refer&earn");
        return model;
    }

}
