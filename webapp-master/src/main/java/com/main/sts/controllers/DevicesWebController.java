package com.main.sts.controllers;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ec.eventserver.models.DeviceInfo;
import com.ec.eventserver.service.DeviceService;
import com.main.sts.util.DateUtil;
import com.main.sts.util.SystemConstants;

@Controller
@RequestMapping(value = "/school_admin/devices")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_GUEST','ROLE_CUSTOMER_SUPPORT','ROLE_OPERATOR')")
public class DevicesWebController {

    @Autowired
    private DeviceService deviceService;
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView driversHomePage(ModelAndView model, HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
            model.setViewName("redirect:/j_spring_security_logout");
            return model;
        }
        BigInteger count = null;
        Integer offset = (request.getParameter("offset") != null && !request.getParameter("offset").trim().isEmpty())
                ? Integer.parseInt(request.getParameter("offset"))
                : 1;
        Integer limit = (request.getParameter("limit") != null && !request.getParameter("limit").trim().isEmpty())
                ? Integer.parseInt(request.getParameter("limit"))
                : SystemConstants.recordsPerPage;
        count = deviceService.getCountOFRecords("select count(ec_device_id) from device_info");
        List<DeviceInfo> deviceInfos = deviceService.getRecordsByPagination(offset, limit);
        model.setViewName("/school_admin/devices/list");
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        model.addObject("date", formatter.format(new Date()));
        model.addObject("login_name", auth.getName());
        model.addObject("devices", deviceInfos);
        model.addObject("current_page", "devices");
        model.addObject("recordsCount", count);
        model.addObject("limit", limit);
        model.addObject("offset", offset);
        model.addObject("offset", offset);
        model.addObject("recordsPerPage", SystemConstants.recordsPerPage);
        return model;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addNewDevi(ModelAndView model, HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
            model.setViewName("redirect:/j_spring_security_logout");
            return model;
        }

        model.setViewName("/school_admin/devices/add");
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        model.addObject("date", formatter.format(new Date()));
        model.addObject("login_name", auth.getName());
        model.addObject("current_page", "devices");
        return model;
    }

    @RequestMapping(value = "/addaction", method = RequestMethod.POST)
    public ModelAndView addNewDevicedtion(ModelAndView model, HttpServletRequest request) {

        String ec_device_id = request.getParameter("ec_device_id");
        DeviceInfo deviceInfo = null;
        deviceInfo = deviceService.getDeviceInfoByECDeviceId(ec_device_id);
        if (deviceInfo == null) {
            Integer vehicle_id = Integer.parseInt(request.getParameter("vehicle_id"));
            deviceInfo = new DeviceInfo();
            deviceInfo.setCreated_at(DateUtil.getNewDate());
            deviceInfo.setEc_device_id(ec_device_id);
            deviceInfo.setVehicle_id(vehicle_id);
            deviceService.insertDeviceInfo(deviceInfo);
            model.setViewName("redirect:list");
        } else {
            model.addObject("device", deviceInfo);
            model.addObject("error", "device_exists");
            model.setViewName("/school_admin/devices/add");
        }

        model.addObject("current_page", "devices");
        return model;
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ModelAndView updateDevice(ModelAndView model, HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
            model.setViewName("redirect:/j_spring_security_logout");
            return model;
        }
        String device_id = request.getParameter("id");
        DeviceInfo deviceInfo = deviceService.getDeviceInfoByECDeviceId(device_id);
        model.setViewName("/school_admin/devices/update");
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        model.addObject("date", formatter.format(new Date()));
        model.addObject("login_name", auth.getName());
        model.addObject("current_page", "devices");
        model.addObject("device", deviceInfo);
        return model;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete(ModelAndView model, HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
            model.setViewName("redirect:/j_spring_security_logout");
            return model;
        }
        String device_id = request.getParameter("id");
        DeviceInfo deviceInfo = deviceService.getDeviceInfoByECDeviceId(device_id);
        model.setViewName("/school_admin/devices/delete");
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        model.addObject("date", formatter.format(new Date()));
        model.addObject("login_name", auth.getName());
        model.addObject("current_page", "devices");
        model.addObject("device", deviceInfo);
        return model;
    }

    @RequestMapping(value = "/updateaction", method = RequestMethod.POST)
    public ModelAndView updateAction(ModelAndView model, HttpServletRequest request) {

        String ec_device_id = request.getParameter("ec_device_id");
        String old_ec_device_id = request.getParameter("old_ec_device_id");
        DeviceInfo deviceInfo = null;
        Integer vehicle_id = Integer.parseInt(request.getParameter("vehicle_id"));
        String ret = null;
        deviceInfo = deviceService.getDeviceInfoByECDeviceId(ec_device_id);
        if (old_ec_device_id.equals(ec_device_id)) {
            deviceService.updateADeviceInfo(ec_device_id, vehicle_id);
            ret = "redirect:list";
        } else if (deviceInfo != null && deviceInfo.equals(ec_device_id)) {
            deviceInfo.setVehicle_id(vehicle_id);
            model.addObject("device", deviceInfo);
            model.addObject("error", "device_exists");
            ret = "/school_admin/devices/update";
        } else {
            deviceService.updateADeviceInfo(ec_device_id, vehicle_id);
            ret = "redirect:list";
        }
        model.setViewName(ret);
        model.addObject("current_page", "devices");
        return model;
    }

    @RequestMapping(value = "/deleteaction", method = RequestMethod.POST)
    public ModelAndView deleteAction(ModelAndView model, HttpServletRequest request) {

        String old_ec_device_id = request.getParameter("old_ec_device_id");
        deviceService.deleteDeviceInfo(old_ec_device_id);
        model.setViewName("redirect:list");
        model.addObject("current_page", "devices");
        return model;
    }
}
