package com.main.sts.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.sts.common.CommonConstants;
import com.main.sts.dto.EmailDTO;
import com.main.sts.dto.EmailResponse;
import com.main.sts.entities.Booking;
import com.main.sts.entities.Buses;
import com.main.sts.entities.Commuter;
import com.main.sts.entities.DashboardSettings;
import com.main.sts.entities.Drivers;
import com.main.sts.entities.Stops;
import com.main.sts.entities.TripDetail;
import com.main.sts.entities.Trips;

@Service("sosService")
public class SOSService {

    @Autowired
    private Msg91SMSService smsService;

    @Autowired
    private DashBoardSettingsService settingsService;

    @Autowired
    private SendGridMailProvider emailProvider;

    @Autowired
    private CommuterService commuterService;
    
    @Autowired
    private BookingService bookingService;
    
    @Autowired
    private TripService tripService;
    
    @Autowired
    private StopsService stopsService;

    public String[] getSOSAlertNumbers() {
        String[] numbers = settingsService.getDashBoardSettings().getSos_alert_numbers_list();
        return numbers;
    }
    
    public String[] getSOSAlertEmails() {
        String[] emails = settingsService.getDashBoardSettings().getSos_alert_emails_list();
        return emails;
    }
    
    
    public boolean raiseAlertForSOSHelp(int commuter_id, boolean sendEnabled) {
        Commuter commuter = commuterService.getCommuterById(commuter_id);
        String commuterName = commuter.getName();
        String gender = commuter.getGender();
        String mobile = commuter.getMobile();
        
        List<Booking> bookings = bookingService.getActiveBookingsOnTodayByCommuterId(commuter_id);
        
        Booking userBooking = null;
        
        //Emergency Alert! User Vinod Angari is in urgent need. Call Him on 8008547906 now.
        //He having booking id EC98 boardrd the vehicle Tempo Traveller AP28TB5304 from JNTU to WIPRO. driver no. is 9652224187

        if (bookings != null && bookings.size()>0) {
            Date currentTime = new Date();
            Calendar fromCal = Calendar.getInstance();
            fromCal.setTime(currentTime);
            fromCal.add(Calendar.HOUR, -2);
            
            Calendar toCal = Calendar.getInstance();
            toCal.setTime(currentTime);
            toCal.add(Calendar.HOUR, 2);
            
            Date from = fromCal.getTime();
            Date to = toCal.getTime();
            
            for (Booking b : bookings) {
                if (b.getBooking_travel_date().after(from) && b.getBooking_travel_date().before(to)) {
                    userBooking = b;
                    break;
                }
            }
        }
        
        String gender_type = null;
        String gender_type2 = null;

        if (gender.equals("M")) {
            gender_type = "Him";
            gender_type2 = "He";
        } else if (commuter.getGender().equals("F")) {
            gender_type = "Her";
            gender_type2 = "She";
        }
        
        String message = CommonConstants.ALERT_MESSAGE;

        message = message.replaceAll("#name#", commuterName);
        message = message.replaceAll("#gender_type#", gender_type);
        message = message.replaceAll("#mobile#", mobile);
        
        String email_subject = message;
        
        if (userBooking != null) {
            Booking b = userBooking;
            int booking_id = b.getId();
            
            Trips trip = tripService.getTrip(b.getTrip_id());
            TripDetail td = trip.getTripDetail();
            Buses vehicle = td.getBus();
            Drivers d = vehicle.getDriver();
            
            String driver_no = d.getContact_number();
            
            int pickupPoint = b.getFrom_stop();
            int dropoffPoint = b.getTo_stop();

            Stops pickupStop = stopsService.getStop(pickupPoint);
            Stops dropoffStop = stopsService.getStop(dropoffPoint);
            
            String pickup_stop_name = pickupStop.getDisplay_name();
            String dropoff_stop_name = dropoffStop.getDisplay_name();
            Integer num_seats_booked = b.getNum_seats_booked();
            
            String vehicle_type = vehicle.getBus_make_model();
            // TODO: make it somethign like this EC32, EC2
            String vehicle_internal_no = vehicle.getBus_licence_number();
            
            String[] dateTime = b.getBookingTravelDateAndTimeString();
            
            String ride_date = dateTime[0];
            String pickup_stop_time = dateTime[1];
            
            String message1 = CommonConstants.ALERT_MESSAGE_USER_BOARDED;
            
            message1 = message1.replaceAll("#gender_type2#", gender_type2);
            message1 = message1.replaceAll("#booking_id#", "EC"+booking_id);
            message1 = message1.replaceAll("#vehicle_type#", vehicle_type);        
            message1 = message1.replaceAll("#vehicle_number#", vehicle_internal_no);
            message1 = message1.replaceAll("#pickup_stop_name#", pickup_stop_name);
            message1 = message1.replaceAll("#dropoff_stop_name#", dropoff_stop_name);
            message1 = message1.replaceAll("#pickup_stop_time#", pickup_stop_time);
            message1 = message1.replaceAll("#driver_no#", driver_no);

            //message1 = message1.replaceAll("#ride_date#", ride_date);
            //message1 = message1.replaceAll("#num_seats#", ""+num_seats_booked);
            
            message = message + " "+message1;
        }

        System.out.println(message);
        
        boolean notified = false;
        try {
            // SMS
            String[] securityNumbers = getSOSAlertNumbers();
            this.sendSOSSMS(securityNumbers, message, sendEnabled);
            notified = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // EMAIL
            String[] securityEmails = getSOSAlertEmails();
            this.sendSOSEmail(securityEmails, email_subject, message, sendEnabled);
            notified = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return notified;
    }
    
    public void sendSOSSMS(String[] securityNumbers, String message, boolean sendEnabled) {
        for (String number : securityNumbers) {
            SMSInfo sms = new SMSInfo();
            sms.message = message;
            sms.mobile = number;
            sms.sendEnabled = sendEnabled;
            boolean alertMsgSent = smsService.sendSMS(sms);
            if (alertMsgSent) {
                System.out.println("Alert message sent to " + number);
            } else {
                System.out.println("[ERROR]Alert message sent to " + number);
            }
        }
    }
    
    public void sendSOSEmail(String[] securityEmails, String subject, String message, boolean sendEnabled) {
        for (String email : securityEmails) {
            EmailDTO emailObj = new EmailDTO();
            emailObj.setTo(new String[]{email});
            emailObj.setSubject(subject);
            emailObj.setText(message);
            if (sendEnabled) {
                EmailResponse emailResp = emailProvider.sendEmail(emailObj);
                System.out.println("alert message: emailResp:" + emailResp);
            } else {
                System.out.println("alert message: didnt sent as not enabled:" + emailObj);
            }
        }
    }

}
