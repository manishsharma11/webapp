package com.main.sts;

import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;

import com.main.sts.dto.BookingDTO;
import com.main.sts.dto.response.BookingCancellationResponse;
import com.main.sts.dto.response.BookingResponse;
import com.main.sts.dto.response.CommuterBookingResponse;
import com.main.sts.dto.response.CommuterResponse;
import com.main.sts.dto.response.PreBookingDetails;
import com.main.sts.dto.response.StopsResponseDTO;
import com.main.sts.entities.Booking;
import com.main.sts.entities.BookingWebDTO;
import com.main.sts.service.BookingService;
import com.main.sts.service.SOSService;

public class SOSServiceTest extends BaseTest {

    @Resource
    private SOSService sosService;

    @Test
    public void testRaiseSOSAlert() {
        int commuter_id = 91;
        commuter_id = 163;
        boolean sendEnabled = false;
        sosService.raiseAlertForSOSHelp(commuter_id, sendEnabled);
    }
}
