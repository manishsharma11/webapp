package com.main.sts.controllers.rest;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.main.sts.common.CommonConstants.UserChannelType;
import com.main.sts.dto.CommuterDTO;
import com.main.sts.dto.Response;
import com.main.sts.entities.Commuter;
import com.main.sts.service.CommuterService;
import com.main.sts.service.ReturnCodes;

@Controller
@RequestMapping("/commuter")
public class CommuterController {

    @Autowired
    private CommuterService commuterService;

    static final Logger logger = Logger.getLogger(CommuterController.class);

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<Commuter> getCommutersList() {
        List<Commuter> commuterList = null;
        try {
            commuterList = commuterService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commuterList;
    }
	
    @RequestMapping(value = "/user/profile/update", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Response> updateUserProfile(@RequestBody CommuterDTO commuterDTO) {
        Commuter commuter = null;
        Response resp = new Response();
        try {
            boolean updated = commuterService.updateCommuterDetails(commuterDTO);
            if (updated) {
                commuter = commuterService.getCommuterById(commuterDTO.getCommuter_id());
                resp.response = commuter;
                resp.setReturnCode(ReturnCodes.USER_DETAIL_UPDATION_SUCCESS.name());
                //resp.message = "Commuter details update successfully";
                // NOTE: this message is given to user, so be careful 
                resp.message = "Your details updated successfully!";
                return new ResponseEntity<Response>(resp, HttpStatus.OK);
            } else {
                // NOTE: this message is given to user, so be careful 
                resp.message = "Failed to update your details. Please try after some time!";
                resp.response = commuterDTO;
                resp.returnCode = ReturnCodes.USER_DETAIL_UPDATION_FAILED.name();
                return new ResponseEntity<Response>(resp, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // NOTE: this message is given to user, so be careful 
            resp.message = "Failed to update your details. Please try after some time!";
            resp.response = commuterDTO;
            resp.returnCode = ReturnCodes.UNKNOWN_ERROR.name();
            return new ResponseEntity<Response>(resp, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/findById/{commuter_id}", method = RequestMethod.GET)
    public @ResponseBody Commuter getCommuter(@PathVariable Integer commuter_id) {
        Commuter commuter = null;
        try {
            commuter = commuterService.getCommuterById(commuter_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commuter;
    }

    @RequestMapping(value = "/findByEmail/{commuter_email}", method = RequestMethod.GET)
    public @ResponseBody Commuter getCommuterByEmail(@PathVariable String commuter_email) {
        Commuter commuter = null;
        try {
            commuter = commuterService.getCommuterByEmail(commuter_email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commuter;
    }

    @RequestMapping(value = "/findByMobile/{mobile}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public @ResponseBody Commuter getCommuter(@PathVariable String mobile) {
        Commuter commuter = null;
        try {
            commuter = commuterService.getCommuterByMobile(mobile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commuter;
    }

    @RequestMapping(value = "/registerCommuter", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<Response> registerCommuter(@RequestBody CommuterDTO dto) {
        ReturnCodes returnCode = null;
        Response resp = new Response();
        try {
            UserChannelType channelType = UserChannelType.MOBILE;
            
            returnCode = commuterService.registerCommuter(dto.getName(), dto.getEmail(), dto.getMobile(),
                    dto.getGender(), dto.getGcm_reg_id(), dto.getReferral_code(), dto.getDevice_id(), channelType, dto.isSms_send_enabled());
            if (returnCode != null) {
                Commuter commuter = commuterService.getCommuterByMobile(dto.getMobile());
                resp.response = commuter;
            }
            // resp.response = dto;
            resp.returnCode = returnCode.name();
            // TODO: handle the response.
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp.response = dto;
            resp.returnCode = returnCode.name();
            return new ResponseEntity<Response>(resp, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
	
    @RequestMapping(value = "/unregisterCommuter/{mobile}", method = RequestMethod.GET)
    public ResponseEntity<Response> unregisterCommuter(@PathVariable String mobile) {
        ReturnCodes returnCode = ReturnCodes.UNKNOWN_ERROR;
        try {
            returnCode = commuterService.unregisterCommuter(mobile);
            Response resp = new Response();
            resp.response = null;
            resp.returnCode = returnCode.name();
            // TODO: handle the response.
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            Response resp = new Response();
            resp.response = null;
            resp.returnCode = returnCode.name();
            return new ResponseEntity<Response>(resp, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

	//@RequestMapping(value = "/verifyCommuter", produces = MediaType.APPLICATION_JSON_VALUE, 
	//		headers = { "Accept=text/xml, application/json" }, method = RequestMethod.POST)
	
    @RequestMapping(value = "/verifyCommuter", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Response> verifyCommuter(@RequestBody CommuterDTO dto) {
        ReturnCodes returnCode = ReturnCodes.UNKNOWN_ERROR;
        Response resp = new Response();
        try {
            returnCode = commuterService.verifyCommuter(dto.getMobile(), dto.getOtp(), dto.isSms_send_enabled());
            if (returnCode != null) {
                Commuter commuter = commuterService.getCommuterByMobile(dto.getMobile());
                resp.response = commuter;
            }
            if (returnCode == ReturnCodes.USER_ALREADY_VERIFIED) {
                resp.message = "Already verified";
            } else if (returnCode == ReturnCodes.USER_VERIFIED) {
                resp.message = "Verification succesfful";
            } else if (returnCode == ReturnCodes.USER_VERIFICATION_FAILED) {
                resp.message = "Verification failed";
            }
            //resp.response = dto;
            resp.returnCode = returnCode.name();
            System.out.println(resp);
            System.out.println("--->>" + new ResponseEntity<Response>(resp, HttpStatus.OK));
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp.returnCode = returnCode.name();
            System.out.println(resp);
            return new ResponseEntity<Response>(resp, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
    
    @RequestMapping(value = "/regenrateOTP", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Response> regenrateOTP(@RequestBody CommuterDTO dto) {
        ReturnCodes returnCode = ReturnCodes.UNKNOWN_ERROR;
        Response resp = new Response();
        try {
            // we are taking mobile no. in consideration, as he might be trying to regenerate after changing his mobile no.
            returnCode = commuterService.regenrateOTP(dto.getMobile(), dto.getCommuter_id(), dto.isSms_send_enabled());
            if (returnCode != null) {
                Commuter commuter = commuterService.getCommuterByMobile(dto.getMobile());
                resp.response = commuter;
            }
            if (returnCode == ReturnCodes.USER_NOT_EXIST) {
                resp.message = "User does not exist with mobile:"+dto.getMobile();
            } else if (returnCode == ReturnCodes.USER_ALREADY_EXIST) {
                resp.message = "User already exist and activated";
            } else if (returnCode == ReturnCodes.USER_EXIST_OTP_GENERATED) {
                resp.message = "User exist and new OTP generated";
            }
            //resp.response = dto;
            resp.returnCode = returnCode.name();
            System.out.println(resp);
            System.out.println("--->>" + new ResponseEntity<Response>(resp, HttpStatus.OK));
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp.returnCode = returnCode.name();
            System.out.println(resp);
            return new ResponseEntity<Response>(resp, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
    
    @RequestMapping(value = "/updateGCMRegistrationId", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Response> updateGCMRegistrationId(@RequestBody CommuterDTO dto) {
        ReturnCodes returnCode = ReturnCodes.UNKNOWN_ERROR;
        Response resp = new Response();
        try {
            returnCode = commuterService.updateGCMRegistrationId(dto.getCommuter_id(), dto.getGcm_reg_id());
            if (returnCode == ReturnCodes.GCM_REGISTRATION_SUCCESSFUL) {
                resp.message = "GCM registration successful";
            } else if (returnCode == ReturnCodes.GCM_REGISTRATION_FAILED) {
                resp.message = "GCM registration failed";
            }
            resp.returnCode = returnCode.name();
            System.out.println(resp);
            System.out.println("--->>" + new ResponseEntity<Response>(resp, HttpStatus.OK));
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp.returnCode = returnCode.name();
            System.out.println(resp);
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        }
    }
    
    
//    public ResponseEntity<Response> runwith(Response resp, RequestCallback requestCallback) {
//        try {
//            System.out.println(resp);
//            System.out.println("--->>" + new ResponseEntity<Response>(resp, HttpStatus.OK));
//            return new ResponseEntity<Response>(resp, HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            resp.returnCode = resp.getReturnCode();
//            System.out.println(resp);
//            return new ResponseEntity<Response>(resp, HttpStatus.OK);
//        }
//    }
//    
//    public interface RequestCallback {
//        
//        requestCallback.execute();
//    }
    
}
