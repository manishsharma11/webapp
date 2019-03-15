package com.main.sts.controllers.webapp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.main.sts.dto.response.AboutUsDTO;
import com.main.sts.service.DashBoardSettingsService;

@Controller
@RequestMapping("/webapp/general")
public class SettingsWebAppController {

    @Autowired
    private DashBoardSettingsService settingsService;

    static final Logger logger = Logger.getLogger(SettingsWebAppController.class);

    @RequestMapping(value = "/about_us", method = RequestMethod.GET)
    public ModelAndView getAboutUs(ModelAndView model) {
        AboutUsDTO aboutus = null;
        try {
            aboutus = settingsService.getAboutUs();
            model.addObject("aboutus", aboutus);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addObject("current_page", "aboutus");
        model.setViewName("/webapp/about_us");
        return model;
    }

}
