package com.main.sts.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.main.sts.dto.Response;
import com.main.sts.dto.SavedRouteDTO;
import com.main.sts.dto.SavedRouteResponse;
import com.main.sts.service.ReturnCodes;
import com.main.sts.service.SavedRoutesService;

@Controller
@RequestMapping("/routes")
public class SavedRouteController {

    @Autowired
    private SavedRoutesService savedRoutesService;

    @RequestMapping(value = "/save_route", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Response> saveTheRoute(@RequestBody SavedRouteDTO savedRoute) {
        Response resp = new Response();
        ReturnCodes returnCode = null;
        try {
            returnCode = savedRoutesService.saveARoute(savedRoute);
            resp.response = null;
            resp.returnCode = returnCode.name();
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp.response = null;
            resp.returnCode = returnCode.name();
            return new ResponseEntity<Response>(resp, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/getSavedRoutesByCommuterId/{commuter_id}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Response> getSavedRoutesByCommuterId(@PathVariable Integer commuter_id) {
        Response resp = new Response();
        List<SavedRouteResponse> list = null;
        try {
            list = savedRoutesService.getAllSavedRoutesResponse(commuter_id);
            resp.response = list;
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        } catch (Exception e) {
            resp.response = null;
            return new ResponseEntity<Response>(resp, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
