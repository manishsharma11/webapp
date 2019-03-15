package com.main.sts.service;

import static com.main.sts.common.CommonConstants.BookingStatus.*;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.CRC32;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.eventserver.models.DeviceInfo;
import com.ec.eventserver.service.DeviceService;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.main.sts.common.CommonConstants.BookingStatus;
import com.main.sts.common.CommonConstants;
import com.main.sts.common.CommonConstants.TripStatus;
import com.main.sts.common.ServiceException;
import com.main.sts.dao.sql.BookingDao;
import com.main.sts.dto.AccountDTO;
import com.main.sts.dto.BookingDTO;
import com.main.sts.dto.EmailDTO;
import com.main.sts.dto.EmailResponse;
import com.main.sts.dto.FareDTO;
import com.main.sts.dto.response.BookingCancellationResponse;
import com.main.sts.dto.response.BookingStopDetailsResponse;
import com.main.sts.dto.response.CommuterBookingResponse;
import com.main.sts.dto.response.BookingHistoryResponse;
import com.main.sts.dto.response.BookingResponse;
import com.main.sts.dto.response.CommuterResponse;
import com.main.sts.dto.response.PreBookingCreditDetails;
import com.main.sts.dto.response.PreBookingDetails;
import com.main.sts.dto.response.StopsResponseDTO;
import com.main.sts.entities.Account;
import com.main.sts.entities.Booking;
import com.main.sts.entities.BookingWebDTO;
import com.main.sts.entities.BusFarePriceEntity;
import com.main.sts.entities.Buses;
import com.main.sts.entities.Commuter;
import com.main.sts.entities.DailyRunningBuses;
import com.main.sts.entities.DashboardSettings;
import com.main.sts.entities.Drivers;
import com.main.sts.entities.PushMessage;
import com.main.sts.entities.RouteStops;
import com.main.sts.entities.Routes;
import com.main.sts.entities.Stops;
import com.main.sts.entities.TransactionInfo;
import com.main.sts.entities.TripDetail;
import com.main.sts.entities.Trips;
import com.main.sts.util.DateUtil;

@Service("bookingService")
public class BookingService {

    @Autowired
    private BookingDao bookingDao;

    @Autowired
    private BusesService vehicleService;

    @Autowired
    private TripService tripService;
    
    @Autowired
    private RouteStopsService routeStopsService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private FareService fareService;

    @Autowired
    private TransactionService txService;

    @Autowired
    private StopsService stopsService;
    
    @Autowired
    private DeviceService deviceService;
    
    @Autowired
    private CommuterService commuterService;
    
    @Autowired
    private Msg91SMSService smsService;
    
    @Autowired
    private DashBoardSettingsService settingsService;
    
    @Autowired
    private SendGridMailProvider emailProvider;
    
    @Autowired
    private DailyRunningBusesService runningBusService;
    
    @Autowired
    private GoogleGCMService googleGCMService;
    
    public List<Booking> findAll() {
        return bookingDao.findAll();
    }
    
    public List<BookingWebDTO> getBookingWithCommuterDetails(){
        List<BookingWebDTO> bookings =  bookingDao.getBookingWithCommuterDetails();
        return bookings;
    }
    
    public List<BookingWebDTO> getBookingWithCommuterDetails(int tripID){
        List<BookingWebDTO> bookings =  bookingDao.getBookingWithCommuterDetails(tripID);
        return bookings;
    }
	
	public DashboardSettings getSettings() {
        return settingsService.getDashBoardSettings();
    }
    
    public Integer getCancellationAllowedBeforeXMinsValue() {
        return getSettings().getCancellation_allowed_before_mins();
    }

    public BookingResponse bookNow(BookingDTO booking,boolean msgSendEnabled) throws Exception {
        System.out.println("booking:"+booking.toString());
        return bookNow(booking.getCommuter_id(), booking.getTrip_id(), booking.getFrom_stop_id(),
                booking.getTo_stop_id(),
                booking.getNum_seats_choosen(), msgSendEnabled);
    }

    public BookingResponse bookNow(int commuter_id, int trip_id, int from_stop, int to_stop, int num_seats_choosen,boolean msgSendEnabled) throws Exception {

        System.out.println("commuter_id:" + commuter_id + "--trip_id:" + trip_id + "--from_stop:" + from_stop + "--"
                + to_stop
                + "--num_seats_choosen" + num_seats_choosen);
        
        Trips trip = tripService.getTrip(trip_id);

        System.out.println("trip:" + trip);
        
        Date booking_travel_date = trip.getTrip_running_date();
        //Date booking_travel_time = null;//new Date();

        System.out.println("commuter_id:" + commuter_id + "--trip_id:" + trip_id + "--from_stop:" + from_stop + "--"
                + to_stop + "--" + booking_travel_date
                + "--num_seats_choosen" + num_seats_choosen);
        
        // Trips

        // Trips trip = tripService.getTrip(trip_id);

        int vehicle_id = trip.getTripDetail().getBusid();
        Buses vehicle = vehicleService.getBusById(vehicle_id);
        int numSeats = vehicle.getBus_allotted();

//        if (!checkIfAlreadyBookedInSameVehicle(vehicle, trip, num_seats_choosen)) {
//            bookingResp.setReturnCode(ReturnCodes.BOOKING_SEATS_NOT_AVAILABLE);
//            return bookingResp;
//        }
        
        // marking all bookings expired, in-case due to some technical glitch they are left over
        // as next check for max active bookings will not allow to have more than that many active bookings at one time.
        markAllBookingsExpiredForACommuter(commuter_id);
        
        BookingResponse bookingResp = new BookingResponse();

        if (!checkIfAlreadyHaveMaxActiveBookings(commuter_id)) {
            bookingResp.setMax_active_bookings_number(CommonConstants.MAX_ACTIVE_BOOKINGS_ALLOWED_AT_A_TIME);
            bookingResp.setReturnCode(ReturnCodes.ALREADY_HAVE_MAX_ACTIVE_BOOKINGS);
            return bookingResp;
        }

        // TODO: check this logic, that should we check balance first or seats
        // first.

        if (!checkIsSeatsAvailable(vehicle, trip, num_seats_choosen)) {
            bookingResp.setReturnCode(ReturnCodes.BOOKING_SEATS_NOT_AVAILABLE);
            return bookingResp;
        }

        int source_stop_id = from_stop;
        int dest_stop_id = to_stop;
        BusFarePriceEntity fare = fareService.fetchFare(source_stop_id, dest_stop_id);

        if (fare == null) {
            throw new IllegalArgumentException("Fare is not defined between source_stop_id:" + source_stop_id
                    + "--dest_stop_id:" + dest_stop_id);
        }
        // TODO: the credits required.
        
        int charged_fare_credis_required;
        int actual_fare_credits_required;
        
        // if discounted fare is enabled. then
        // charged fare is discounted fare and actual fare is charged fare
        // if discount is not enable then
        // charged fare is charged fare and actual fare is actual fare
        if (fare.getDiscounted_fare_enabled() != null && fare.getDiscounted_fare_enabled()) {
            charged_fare_credis_required = fare.getDiscounted_fare();
            actual_fare_credits_required = fare.getCharged_fare();
        } else {
            charged_fare_credis_required = fare.getCharged_fare();
            actual_fare_credits_required = fare.getActual_fare();
        }

        // there may be multiple seats booking in one shot (one booking).
        int total_charged_credits_required = num_seats_choosen * charged_fare_credis_required;
        int total_actual_credits_required = num_seats_choosen * actual_fare_credits_required;
        
        int charged_fare_credits_per_person_required = charged_fare_credis_required;

        //
        if (!isEnoughBalanceAvailable(commuter_id, num_seats_choosen,
                charged_fare_credits_per_person_required, total_charged_credits_required)) {
            
            bookingResp.setReturnCode(ReturnCodes.BOOKING_NOT_ENOUGH_BALANCE);
            return bookingResp;
        }

        // 
        if (booking_travel_date == null) {
            throw new IllegalArgumentException("booking_travel_date shouldn't be null: for trip:" + trip_id);
            // booking_travel_date = new Date();
        }

        int route_id = trip.getTripDetail().getRouteid();
        System.out.println("route_id"+route_id);
        
        RouteStops rs = routeStopsService.getRouteStopsForAStopId(route_id, source_stop_id);
        System.out.println("rs:"+rs);

        String stop_time  = rs.getStop_time();
        System.out.println("stop_time:"+stop_time);
        String[] stop_time_hours = stop_time.split(":");
        
        booking_travel_date.setHours(Integer.parseInt(stop_time_hours[0]));
        booking_travel_date.setMinutes(Integer.parseInt(stop_time_hours[1]));
        booking_travel_date.setSeconds(0);
        
        validateBookingTravelDate(booking_travel_date);

        // /// ------------- ALL validations should happen before it
        // ----------------------///

        synchronized (this) {

            if (!checkIsSeatsAvailable(vehicle, trip, num_seats_choosen)) {
                bookingResp.setReturnCode(ReturnCodes.BOOKING_SEATS_NOT_AVAILABLE);
                // throw new Exception("Seats not available");
                return bookingResp;
            }

            Booking booking = new Booking();
            booking.setBooking_time(new Date());
            booking.setActual_fare(total_actual_credits_required);
            
            // previous code:
            //booking.setCharged_fare(total_charged_credits_required);

            ChargedFare chargedFare = chargeFare(commuter_id, num_seats_choosen, charged_fare_credits_per_person_required);
            
            // NOTE ==> ideally chargedFare shouldn't be null; if it is null,
            // its most likely that its a BUG
            if (chargedFare == null) {
                bookingResp.setReturnCode(ReturnCodes.BOOKING_NOT_ENOUGH_BALANCE);
                return bookingResp;
            } else {
                booking.setCharged_free_rides(chargedFare.getCharged_free_rides());
                booking.setCharged_fare(chargedFare.getCharged_credit_points());
            }

            // Booking a Seat.
            startBooking(commuter_id, trip_id, from_stop, to_stop, booking_travel_date,
                    num_seats_choosen, vehicle_id, booking, trip, bookingResp, msgSendEnabled);
            System.out.println("bookingResp:" + bookingResp);
        }
        return bookingResp;
    }

    private ChargedFare chargeFare(int commuter_id, int num_seats_choosen,
            int charged_fare_credits_per_person_required) {
        Account account = accountService.getAccountByCommuterId(commuter_id);
        ChargedFare chargedFare = new ChargedFare();
        if (account != null) {
            
            Integer free_rides = account.getFree_rides_available();
            Integer available_credits = account.getCredits_available();
            
            // if he have free rides. then check how may free rides he have. should we allow to book
            // or show "not enough balance".
            if (free_rides != null && free_rides > 0) {

                // if number of seats he is asking are less than free rides
                if (num_seats_choosen < free_rides) {
                    chargedFare.charged_free_rides = num_seats_choosen;
                    chargedFare.charged_credit_points = 0;
                    
                    
                } else if (num_seats_choosen == free_rides) {
                    chargedFare.charged_free_rides = num_seats_choosen;
                    chargedFare.charged_credit_points = 0;
                    
                } else {
                    // it means user is asking for more seats then the free
                    // rides he have. so we need to deduct some credits from his
                    // account or show him not enough balance.
                    int num_seats_should_be_paid = num_seats_choosen - free_rides;
                    int total_credits_required = num_seats_should_be_paid * charged_fare_credits_per_person_required;

                    // if he have less credits available. and free rides are goign to over due to number of persons he selected.
                    if (available_credits < total_credits_required) {
                        // we can't do anything. I am hoping he shouldn't be
                        // alloweed to reach here.
                        // as we would be taking him to recharge page by shoing
                        // not enough balance.
                        // return null, so we will take him to "low user balance"
                        return null;
                    } else {
                        // it means he have more or equal available credits.
                        chargedFare.charged_free_rides = free_rides;
                        chargedFare.charged_credit_points = total_credits_required;
                    }
                }
            } else {
                // if he dont have any credits left
                int num_seats_should_be_paid = num_seats_choosen;
                int total_credits_required = num_seats_should_be_paid * charged_fare_credits_per_person_required;
                
                // if he have less credits available. and free rides are goign to over due to number of persons he selected.
                if (available_credits < total_credits_required) {
                    // we can't do anything. I am hoping he shouldn't be
                    // alloweed to reach here.
                    // as we would be taking him to recharge page by shoing
                    // not enough balance.
                    // return null, so we will take him to "low user balance"
                    return null;
                } else {
                    // it means he have more or equal available credits.
                    chargedFare.charged_free_rides = 0;
                    chargedFare.charged_credit_points = total_credits_required;
                }
            }
        } else {
            // there is no entry in accounts table. he should buy some credits.
            // return null, so we will take him to "low user balance"
            return null;
        }
        return chargedFare;
    }
    
    
    private void markAllBookingsExpiredForACommuter(int commuter_id) {
        List<Booking> bookings = this.getActiveBookingByCommuterId(commuter_id);
        if (bookings != null) {
            for (Booking booking : bookings) {

                // we already sent user a thank you message to user at dropoff for giving feedback, no need to send
                // again.
                boolean message_sent_for_dropoff = booking.isMessage_sent_for_dropoff();
                if (message_sent_for_dropoff) {
                    continue;
                }
                
                Calendar current_time = Calendar.getInstance();
                // this is with time also. in hours and mins
                Date travel_date = booking.getBooking_travel_date();

                Calendar travel_date_cal = Calendar.getInstance();
                travel_date_cal.setTime(travel_date);

                System.out.println("markAllBookingsExpiredForACommuter: travel_date_cal:"+travel_date_cal.getTime()+"--------"+current_time.getTime());
                
                if (travel_date_cal.before(current_time)) {
                    boolean updated = bookingDao.updateBookingStatusAsExpiredForActiveBookingsByBookingId(booking.getId(),
                            BookingStatus.EXPIRED);
                    if (updated) {
                        System.out.println("marked booking " + booking.getId() + " expired for commuter id "
                                + commuter_id);
                    }
                }
            }
        }
    }

    private boolean checkIfAlreadyHaveMaxActiveBookings(int commuter_id) {
        List<Booking> bookings = this.getActiveBookingByCommuterId(commuter_id);
        if (bookings.size() >= CommonConstants.MAX_ACTIVE_BOOKINGS_ALLOWED_AT_A_TIME) {
            return false;
        }
        return true;
    }

    public BookingResponse startBooking(int commuter_id, int trip_id, int from_stop, int to_stop,
            Date booking_travel_date, int num_seats_choosen, int vehicle_id, final Booking booking,
            Trips trips, BookingResponse bookingResp, final boolean msgSendEnabled) {

        booking.setCommuter_id(commuter_id);
        booking.setFrom_stop(from_stop);
        booking.setTo_stop(to_stop);
        booking.setStatus(BookingStatus.ACCEPTED.getBookingStatus());
        booking.setBooking_travel_date(booking_travel_date);;
        // trip_id is known as user would have choosen the same.
        if (trip_id > 0) {
            booking.setTrip_id(trip_id);
        } else {
            booking.setTrip_id(-1);// Unknown trip.
        }

        booking.setNum_seats_booked(num_seats_choosen);
        booking.setBus_id(trips.getTripDetail().getBusid());

        Integer booking_id = (Integer) bookingDao.bookARide(booking, trips);

        if (booking_id != null) {
            System.out.println("New booking id " + booking_id + " for:" + commuter_id);
            //----------------------------Sending SMS for booking id ================//
            // Sending booking id in a new thread.
            Thread t = new Thread(new Runnable() {
                
                @Override
                public void run() {
                    // lets user see the same on app first that booking is done.
                    // so making this thread wait for 2 seconds.
//                    try {
//                        Thread.sleep(2000L);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    sendBookingConfirmationSMS(booking, msgSendEnabled);
                }
            });
            t.start();
            //----------------------------End SMS for booking id ================//

            bookingResp.setBooking_id(booking_id);
            bookingResp.setReturnCode(ReturnCodes.BOOKING_SUCCESSFUL);
        } else {
            bookingResp.setBooking_id(-1);
            bookingResp.setReturnCode(ReturnCodes.BOOKING_FAILED);
        }
        return bookingResp;
    }

    String msg_format = "Hi {name}, you";

    String message1 = "Hi Rahul, your booking id for your EasyCommute ride from Vashi Highway to MMRDA office is CF004578ENC05. "
            + "Bus details: Purple Tempo traveller No: C32. Ride date: Wednesday 18-11-2015 The bus depart from Vashi Highway, in front of CIDCO exhibition center"
            + " at 08.55AM. If any queries, call us on 990859937";
    
    public void sendBookingConfirmationSMS(Booking booking, boolean sendEnabled) {
        Commuter c = commuterService.getCommuterById(booking.getCommuter_id());
        
        int pickupPoint = booking.getFrom_stop();
        int dropoffPoint = booking.getTo_stop();

        Stops pickupStop = stopsService.getStop(pickupPoint);
        Stops dropoffStop = stopsService.getStop(dropoffPoint);

        Trips trip = tripService.getTrip(booking.getTrip_id());
        TripDetail td = trip.getTripDetail();
        Buses vehicle = td.getBus();
        Drivers d = vehicle.getDriver();

        String commuterName = c.getName();
        String pickup_stop_name = pickupStop.getDisplay_name();
        String dropoff_stop_name = dropoffStop.getDisplay_name();
        Integer booking_id = booking.getId();
        Integer num_seats_booked = booking.getNum_seats_booked();
        
        String pickup_stop_help_text = pickupStop.getHelp_text();
        
        String vehicle_type = vehicle.getBus_make_model();
        // TODO: make it somethign like this EC32, EC2
        String vehicle_internal_no = vehicle.getBus_licence_number();
        
        String[] dateTime = booking.getBookingTravelDateAndTimeString();
        
        String ride_date = dateTime[0];
        String pickup_stop_time = dateTime[1];
        
        String customer_care_no = CommonConstants.DEFAULT_CUSTOMER_CARE_NO;

        DashboardSettings settings = settingsService.getDashBoardSettings();
        if (settings != null) {
            String customerCareNumber = settings.getCustomer_care_number();
            if (customerCareNumber != null) {
                customer_care_no = customerCareNumber;
            }
        }
        
        String message = CommonConstants.DEFAULT_BOOKING_CONFIRMATION_MESSAGE_FORMAT;

        message = message.replaceAll("#name#", commuterName);
        message = message.replaceAll("#pickup_stop_name#", pickup_stop_name);
        message = message.replaceAll("#dropoff_stop_name#", dropoff_stop_name);
        message = message.replaceAll("#booking_id#", "EC"+booking_id);
        message = message.replaceAll("#vehicle_type#", vehicle_type);        
        message = message.replaceAll("#vehicle_number#", vehicle_internal_no);
        message = message.replaceAll("#ride_date#", ride_date);
        message = message.replaceAll("#num_seats#", ""+num_seats_booked);

        message = message.replaceAll("#pickup_stop_name#", pickup_stop_name);
        message = message.replaceAll("#pickup_stop_help_text#", pickup_stop_help_text);
        message = message.replaceAll("#pickup_stop_time#", pickup_stop_time);
        message = message.replaceAll("#customer_care_no#", customer_care_no);

        System.out.println(message);

        SMSInfo sms = new SMSInfo();
        sms.message = message;
        sms.mobile = c.getMobile();
        // sms.mobile = "9908599937";//c.getMobile();

        sms.sendEnabled = sendEnabled;

        System.out.println("message:" + message);
        boolean bookingMsgSent = smsService.sendSMS(sms);
        if (bookingMsgSent) {
            System.out.println("Booking message sent to:" + commuterName + " for booking_id:" + booking_id);
        } else {
            System.out.println("[ERROR]Booking message coudn't send to:" + commuterName + " for booking_id:" + booking_id);
        }
    }

    public void confirmBookingsForToday() {
        Date todayDate = new Date();
        todayDate.setHours(0);
        todayDate.setMinutes(0);
        todayDate.setSeconds(0);

        Date time = new Date();
        time.setYear(0);
        time.setMonth(0);
        time.setSeconds(0);
        time.setHours(time.getHours() + 1);

        List<Booking> bookings = bookingDao.findBookingByDateAndTime(todayDate, time, BookingStatus.RECEIVED);
        for (Booking booking : bookings) {
            int tripId = booking.getTrip_id();
            Trips trip = tripService.getTrip(tripId);
            Buses vehicle = trip.getTripDetail().getBus();
            if (vehicle != null) {
                booking.setStatus(BookingStatus.ACCEPTED.getBookingStatus());
                bookingDao.updateBookingStatus(booking.getId(), BookingStatus.ACCEPTED.getBookingStatus());
                notifyUserForBookingConfirmation(booking.getCommuter_id());
            } else {
                sendNotificationToAdminForTripVehicleAllotment();
            }
        }
    }

    private void notifyUserForBookingConfirmation(int commuter_id) {
        // Async Task

    }

    private void sendNotificationToAdminForTripVehicleAllotment() {
        // TODO Auto-generated method stub

    }

    private void validateBookingTravelDate(Date booking_travel_date) {
        // TODO Auto-generated method stub

    }

    private void validateBookingTravelTime(Date booking_travel_time) {
        // TODO Auto-generated method stub

    }

    private int generateNextBookingId() {
        int booking_id = 5000;

        return booking_id;
    }

    public boolean checkIsSeatsAvailable(Buses vehicle, Trips trip, int num_seats_choosen) {
        int numSeats = vehicle.getBus_allotted();
        int seatsFilled = trip.getSeats_filled();

        // if total no.of seats in vehicle is less or equal to going to max
        // seats
        // (seatsFilled + num_seats_choosen)

        System.out.println(seatsFilled + "--" + num_seats_choosen + "--" + numSeats);
        if (seatsFilled + num_seats_choosen <= numSeats) {
            // seats available.
            return true;
        } else {
            return false;
        }
    }

//    public boolean isEnoughBalanceAvailable(int commuter_id, int vehicle_id, int trip_id, int credisRequired) {
//        Account account = accountService.getAccountByCommuterId(commuter_id);
//        if (account != null) {
//            // if account does not have enought credits for booking.
//            if (account.getCredits_available() < credisRequired) {
//                return false;
//            }
//        } else {
//            // there is no entry in accounts table. he should buy some credits.
//            return false;
//        }
//        return true;
//    }
    
    /**
     * NOTE: DONT CHANGE WITHOUT EVALUATING ALL SCENERAIOS, AS THIS IS CORE FOR PRICING.
     * @return
     */
    public boolean isEnoughBalanceAvailable(int commuter_id, int num_seats_choosen,
            int charged_fare_credits_per_person_required, int creditsRequired) {
        Account account = accountService.getAccountByCommuterId(commuter_id);
        System.out.println("account:" + account);
        if (account != null) {
            System.out.println("entering to check for balance");
            int free_rides = account.getFree_rides_available();
            int available_credits = account.getCredits_available();
            
            // if he have free rides. then check how may free rides he have. should we allow to book
            // or show "not enough balance".
            if (free_rides > 0) {

                // if number of seats he is asking are less than free rides
                if (num_seats_choosen < free_rides) {
                    return true;
                } else if (num_seats_choosen == free_rides) {
                    return true;
                } else {
                    // it means user is asking for more seats then the free
                    // rides he have. so we need to deduct some credits from his
                    // account or show him not enough balance.
                    int num_seats_should_be_paid = num_seats_choosen - free_rides;
                    int total_credits_required = num_seats_should_be_paid * charged_fare_credits_per_person_required;

                    if (available_credits < total_credits_required) {
                        return false;
                    } else {
                        return true;
                    }
                }
            } else {
                System.out.println("======================= USER dont have any free ride=========");
                // if account does not have enough credits for booking.
                System.out.println("account.getCredits_available():"+account.getCredits_available()+"-------"+creditsRequired);
                if (account.getCredits_available() < creditsRequired) {
                    System.out.println("Returning false as account is low");
                    return false;
                }
            }
        } else {
            // there is no entry in accounts table. he should buy some credits.
            System.out.println("======================= ERROR: as any user should be having a default account while registering=========");
            return false;
        }
        return true;
    }

    public boolean reserveASeat(int commuter_id, int vehicle_id, int trip_id) {
        tripService.incrementTripSeat(trip_id);
        return false;
    }

    public Booking getBookingById(int booking_id) {
        String queryStr = "from Booking b where b.id=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, booking_id);
        return bookingDao.getSingleRecord(Booking.class, queryStr, parameters);
    }
    
    public BookingHistoryResponse getBookingDetailsById(int booking_id) {
        String queryStr = "from Booking b where b.id=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, booking_id);
        Booking booking = bookingDao.getSingleRecord(Booking.class, queryStr, parameters);
        
        Calendar current_time = Calendar.getInstance();
        // this is with time also. in hours and mins
        Date travel_date = booking.getBooking_travel_date();
        
        Calendar travel_date_cal = Calendar.getInstance();
        travel_date_cal.setTime(travel_date);
        //// adding -2 hour as assumption is that before 2 hour bus will not be running 
        //(I mean user booking for 9.30 so bus must be starting after 7.30 for that trip)
        travel_date_cal.add(Calendar.HOUR_OF_DAY, -2);
        
        BookingHistoryResponse resp = null;

        // if its within 2 hours of travel, then check.and booking is accepted,
        // not cancelled and expired.
        if (current_time.after(travel_date_cal) && booking.getStatus() == BookingStatus.ACCEPTED.intValue()) {
            DailyRunningBuses bus = null;
            try {
                Trips trip = tripService.getTrip(booking.getTrip_id());
                bus = runningBusService.getDailyRunningBus(trip.getId(), current_time.getTime(), TripStatus.RUNNING);
                System.out.println("DailyRunningBuses in Booking:" + bus);
            } catch (Exception e) {
                // incase due to any reasons, if it fails, we should not let the whole booking details to go down.
                e.printStackTrace();
            }
            if (bus != null) {
                resp = this.toBookingHisotry(booking, true);
            } else {
                resp = this.toBookingHisotry(booking, false);
            }
        } else {
            resp = this.toBookingHisotry(booking);
        }
        
        return resp;
    }

    public List<Booking> getBookingByCommuterId(int commuter_id) {
        String queryStr = "from Booking b where b.commuter_id=? order by booking_time desc";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, commuter_id);
        return bookingDao.getRecords(Booking.class, queryStr, parameters);
    }
    
    public List<Booking> getActiveBookingByCommuterId(int commuter_id) {
        String queryStr = "from Booking b where b.commuter_id=? AND b.status = ? order by booking_time desc";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, commuter_id);
        parameters.put(1, BookingStatus.ACCEPTED.intValue());
        return bookingDao.getRecords(Booking.class, queryStr, parameters);
    }
    
    public List<Booking> getActiveBookingsOnTodayByCommuterId(int commuter_id) {
        String queryStr = "from Booking b where b.commuter_id=? AND b.status = ? AND b.booking_travel_date >=? AND b.booking_travel_date <? order by booking_time desc";
        
        Date today = DateUtil.getOnlyTodayDate(new Date());
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DATE, 1);
        
        Date tomorrow = c.getTime();
        
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, commuter_id);
        parameters.put(1, BookingStatus.ACCEPTED.intValue());
        parameters.put(2, today);
        parameters.put(3, tomorrow);
        return bookingDao.getRecords(Booking.class, queryStr, parameters);
    }
    
    public List<Booking> getBookingByTripId(int trip_id) {
        String queryStr = "from Booking b where b.trip_id=? order by booking_time desc";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, trip_id);
        return bookingDao.getRecords(Booking.class, queryStr, parameters);
    }

    public List<Booking> getActiveBookingByTripId(int trip_id) {
        String queryStr = "from Booking b where b.trip_id=? AND b.status = ? order by booking_time desc";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, trip_id);
        parameters.put(1, BookingStatus.ACCEPTED.intValue());
        return bookingDao.getRecords(Booking.class, queryStr, parameters);
    }
    
    public BookingCancellationResponse cancelBookingBySystem(int booking_id, boolean cancelExpiredBookings) {
        Booking booking = getBookingById(booking_id);
        if (booking == null) {
            throw new IllegalArgumentException("Booking id does not exist:"+booking_id);
        }
        return cancelBookingBySystem(booking, cancelExpiredBookings);
    }
    
    /**
     * In some cases, due to breakdown or some other issue we need to cancel the trip.
     * so it will be by system trip wide cancellation or a particaulr booking canceallation by system.
     * @param booking
     * @return
     */
    public BookingCancellationResponse cancelBookingBySystem(Booking booking, boolean cancelExpiredBookingsAlso) {
        
        if (booking == null) {
            throw new IllegalArgumentException("Booking does not exist: ");
        }
        
        int booking_id = booking.getId();
        
        BookingCancellationResponse cancellationResp = new BookingCancellationResponse();
        // booking id
        cancellationResp.setBooking_id(booking_id);
        cancellationResp.setCommuter_id(booking.getCommuter_id());
 
        // BOOKING_ALREADY_EXPIRED
        if (!cancelExpiredBookingsAlso && booking.getStatus() == BookingStatus.EXPIRED.intValue()) {
            cancellationResp.setReturnCode(ReturnCodes.BOOKING_ALREADY_EXPIRED);
            return cancellationResp;
        }
        
        //NOTE: => BOOKING ALREADY Cancelled is getting handled in cancelBooking method.
        
        // We are having a check in check in "cancelARideWithFullRefund" that
        // check for already cancelled or expired booking.
        Integer txId = null;

        try {
            // txId = bookingDao.cancelARideWithFullRefund(booking_id, true, 0);
            txId = cancelBooking(booking, true);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof ServiceException) {
                ServiceException se = (ServiceException) e;
                cancellationResp.setReturnCode(se.getReturnCode());
                return cancellationResp;
            }
        }

        if (txId != null) {
            System.out.println("Cancelled booking " + booking_id + " for:" + booking.getCommuter_id());

            TransactionInfo txInfo = txService.getTransactionById(txId);
            cancellationResp.setAccount_balance(txInfo.getPoints_balance());
            cancellationResp.setRefunded_points(txInfo.getPoints_added());
            cancellationResp.setReturnCode(ReturnCodes.BOOKING_CANCELLATION_SUCCESSFUL);
        } else {
            cancellationResp.setReturnCode(ReturnCodes.BOOKING_CANCELLATION_FAILED);
        }
        return cancellationResp;
    }
    
    public BookingCancellationResponse cancelRide(int booking_id) {
        Booking booking = getBookingById(booking_id);
        
        if (booking == null) {
            throw new IllegalArgumentException("Booking id does not exist: " + booking_id);
        }
        
        BookingCancellationResponse cancellationResp = new BookingCancellationResponse();
        // booking id
        cancellationResp.setBooking_id(booking_id);
        cancellationResp.setCommuter_id(booking.getCommuter_id());
 
        
        // BOOKING_ALREADY_EXPIRED
        if (booking.getStatus() == BookingStatus.EXPIRED.intValue()) {
            cancellationResp.setReturnCode(ReturnCodes.BOOKING_ALREADY_EXPIRED);
            return cancellationResp;
        }
        
        //NOTE: => BOOKING ALREADY Cancelled is getting handled in cancelBooking method.
        
        // We are doing this, as it seems that by using x mins method
        // (checkNotAllowingCancellationBeforeXMins), we dont need this.
        // but actually in this we are marking the ticket expired that we can't
        // do in checkNotAllowingCancellationBeforeXMins method
        // as for a user the ticket is active and he would be tracking the bus.
        if (checkNotAllowingCancellationAfterXHours(booking, cancellationResp)) {
            return cancellationResp;
        }

        // BOOKING_CANCELLATION_NOT_ALLOWED
        if (checkNotAllowingCancellationBeforeXMins(booking, cancellationResp)) {
            return cancellationResp;
        }
        
        // We are having a check in check in "cancelARideWithFullRefund" that
        // check for already cancelled or expired booking.
        Integer txId = null;

        try {
            // txId = bookingDao.cancelARideWithFullRefund(booking_id, true, 0);
            txId = cancelBooking(booking, false);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof ServiceException) {
                ServiceException se = (ServiceException) e;
                cancellationResp.setReturnCode(se.getReturnCode());
                return cancellationResp;
            }
        }

        if (txId != null) {
            System.out.println("Cancelled booking " + booking_id + " for:" + booking.getCommuter_id());

            TransactionInfo txInfo = txService.getTransactionById(txId);
            cancellationResp.setAccount_balance(txInfo.getPoints_balance());
            cancellationResp.setRefunded_points(txInfo.getPoints_added());
            
            cancellationResp.setFree_rides_balance(txInfo.getFree_rides_balance());
            cancellationResp.setRefunded_free_rides(txInfo.getFree_rides_added());
            cancellationResp.setReturnCode(ReturnCodes.BOOKING_CANCELLATION_SUCCESSFUL);
        } else {
            cancellationResp.setReturnCode(ReturnCodes.BOOKING_CANCELLATION_FAILED);
        }
        return cancellationResp;
    }
    
    // if by mistake (system glitch), a booking is active after 2 hours, and user tries to cancel
    // marking that booking expired and not allowing user to cancel it.
    private boolean checkNotAllowingCancellationAfterXHours(Booking booking,
            BookingCancellationResponse cancellationResp) {
        
        Date travelDate = booking.getBooking_travel_date();
        // Adding 2 hour to travel date, as assumption is that after 2 hour we must not be allowing cancellation of ticket.
        Calendar after2HourCal = Calendar.getInstance();
        after2HourCal.setTime(travelDate);
        after2HourCal.add(Calendar.HOUR, +2);

        Date after2Hour = after2HourCal.getTime();
        
        Date today = new Date();
        
        System.out.println("cancelRide: today:" + today + "---after1Hour:" + after2Hour);
        if (today.after(after2Hour)) {
            // Marking booking already expired as somehow it seems to be
            // left
            if (booking.getStatus() == BookingStatus.ACCEPTED.intValue()) {
                System.out.println("Marking booking already expired as somehow it seems to be left");
                bookingDao.updateBookingStatus(booking.getId(), BookingStatus.EXPIRED.intValue());
            }

            System.out.println("Now Not allowing to cancel as booking already expired");
            cancellationResp.setReturnCode(ReturnCodes.BOOKING_ALREADY_EXPIRED);
            return true;
        }
        return false;
    }
    
    // Not allowing use to cacnel a Ride before 10 mins.
    private boolean checkNotAllowingCancellationBeforeXMins(Booking booking,
            BookingCancellationResponse cancellationResp) {

        Date travelDate = booking.getBooking_travel_date();
        Date today = new Date();

        // default cancellation allowed time 
        int permit_time_minutes_default = CommonConstants.DEFAULT_CANCELLATION_ALLOWED_BEFORE_X_MINS;;

        // getting cancellation allowed value
        Integer permit_time_minutes = getCancellationAllowedBeforeXMinsValue();
        if (permit_time_minutes == null) {
            permit_time_minutes = permit_time_minutes_default;
        }
        
        Calendar beforeXminsCal = Calendar.getInstance();
        beforeXminsCal.setTime(travelDate);
        // its minus before the value
        beforeXminsCal.add(Calendar.MINUTE, -permit_time_minutes);

        Date beforeXmins = beforeXminsCal.getTime();

        System.out.println("cancelRide: today:" + today + "---beforeXmins:" + beforeXmins);
        if (today.after(beforeXmins)) {
            System.out.println("Now Not allowing to cancel as booking travel date is less than 10 mins");
            cancellationResp.setReturnCode(ReturnCodes.BOOKING_CANCELLATION_NOT_ALLOWED);
            return true;
        }
        return false;
    }
    
    // TODO: handle all combinations of booking cancellation
    private Integer cancelBooking(Booking booking, boolean cancellationBySystem) throws ServiceException {
        if (booking.getStatus() == BookingStatus.CANCELLED_REFUNDED.getBookingStatus()) {
            throw new ServiceException("Booking is already cancelled and full refund is given",
                    ReturnCodes.BOOKING_ALREADY_CANCELLED_FULL_REFUNDED);
        } else if (booking.getStatus() == BookingStatus.CANCELLED_NOREFUND.getBookingStatus()) {
            throw new ServiceException("Booking is already cancelled and no refund is given",
                    ReturnCodes.BOOKING_ALREADY_CANCELLED_NO_REFUND_GIVEN);
        } else if (booking.getStatus() == BookingStatus.CANCELLED_PARTIAL_REFUNDED.getBookingStatus()) {
            throw new ServiceException("Booking is already cancelled and a partial refund is given",
                    ReturnCodes.BOOKING_ALREADY_CANCELLED_PARTIAL_REFUNDED);
        } else if (booking.getStatus() == BookingStatus.EXPIRED.getBookingStatus()) {
            throw new ServiceException("Booking is already expired and can't be cancelled now", ReturnCodes.BOOKING_ALREADY_EXPIRED);
        }
        try {
            Integer txId = bookingDao.cancelARideWithFullRefund(booking, true, 0);
            boolean cancellationSMSToBeSent = true; // TODO: to be change.
            if (txId != null) {
                if (cancellationBySystem) {
                    sendSystemBookingCancellationSMS(booking, cancellationSMSToBeSent);
                } else {
                    sendBookingCancellationSMS(booking, cancellationSMSToBeSent);
                }
                
                // Added for showing notify availability.
                if (!cancellationBySystem) {
                    final int trip_id = booking.getTrip_id();
                    System.out.println("notifyAvailabilityMap: "+notifyAvailabilityMap);
                    final Set<NotifyAvailability> nas = notifyAvailabilityMap.get(trip_id);
                    
                    if (nas != null) {
                        
                        Thread t = new Thread(new Runnable() {
                            
                            public void run() {
                                for (NotifyAvailability na : nas) {
                                    if (na == null) {
                                        continue;
                                    }
                                    System.out.println("Before sending message na variable:" + na);
                                    sendNotifyAvailabilitySMS(na, true);
                                }
                                notifyAvailabilityMap.remove(trip_id);
                            };
                        });
                        t.start();
                    }
                }
            }
            return txId;
        } catch (ServiceException e) {
            e.printStackTrace();
            throw e;
        }
    }
  
    public void sendNotifyAvailabilitySMS(NotifyAvailability na, boolean sendEnabled) {
        System.out.println("--------------------Notify Availability-----------------");
        int trip_id = na.getTrip_id();
        
        System.out.println("trip_id:" + trip_id);
        System.out.println("commuter_id:" + na.getCommuter_id());
        System.out.println("from_stop_id:" + na.getFrom_stop_id() + ", to_stop_id:" + na.getTo_stop_id());
        
        Commuter c = commuterService.getCommuterById(na.getCommuter_id());

        int pickupPoint = na.getFrom_stop_id();
        int dropoffPoint = na.getTo_stop_id();

        Stops pickupStop = stopsService.getStop(pickupPoint);
        Stops dropoffStop = stopsService.getStop(dropoffPoint);

        String commuterName = c.getName();
        String pickup_stop_name = pickupStop.getDisplay_name();
        String dropoff_stop_name = dropoffStop.getDisplay_name();
        
        Date d =  tripService.getTrip(na.getTrip_id()).getTrip_running_date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        
        String ride_date = sdf.format(d);
        
        String customer_care_no = getCustomerCareNo();
        
        String message = CommonConstants.DEFAULT_NOTIFY_AVAILABILITY_MESSAGE_FORMAT;

        message = message.replaceAll("#pickup_stop_name#", pickup_stop_name);
        message = message.replaceAll("#dropoff_stop_name#", dropoff_stop_name);
        message = message.replaceAll("#ride_date#", ride_date);
        message = message.replaceAll("#customer_care_no#", customer_care_no);

        System.out.println(message);

        PushMessage pushMessage = new PushMessage();
        pushMessage.setGcm_regid(c.getGcm_reg_id());
        pushMessage.setTitle("Seats Availability");
        pushMessage.setMessage(message);

        System.out.println("Notification send333------------------->>>");

        Result res = googleGCMService.sendPushNotification(pushMessage);
        System.out.println(res);

        SMSInfo sms = new SMSInfo();
        sms.message = message;
        sms.mobile = c.getMobile();
        // sms.mobile = "9908599937";//c.getMobile();

        sms.sendEnabled = sendEnabled;

        System.out.println("Notify Availability message:" + message);
        boolean bookingMsgSent = smsService.sendSMS(sms);
        if (bookingMsgSent) {
            System.out.println("Notify Availabilitymessage sent to:" + commuterName + " for trip_id:" + trip_id);
        } else {
            System.out.println("[ERROR]Notify Availability message coudn't send to:" + commuterName + " for trip_id:"
                    + trip_id);
        }
    }
    
   public void sendSystemBookingCancellationSMS(Booking booking, boolean sendEnabled) {
        
        Commuter c = commuterService.getCommuterById(booking.getCommuter_id());

        int pickupPoint = booking.getFrom_stop();
        int dropoffPoint = booking.getTo_stop();

        Stops pickupStop = stopsService.getStop(pickupPoint);
        Stops dropoffStop = stopsService.getStop(dropoffPoint);

        String commuterName = c.getName();
        String pickup_stop_name = pickupStop.getDisplay_name();
        String dropoff_stop_name = dropoffStop.getDisplay_name();
        Integer booking_id = booking.getId();
        
        String[] dateTime = booking.getBookingTravelDateAndTimeString();
        
        String ride_date = dateTime[0];
        
        String customer_care_no = getCustomerCareNo();

        String message = CommonConstants.DEFAULT_SYSTEM_BOOKING_CANCELLATION_MESSAGE_FORMAT;

        message = message.replaceAll("#name#", commuterName);
        message = message.replaceAll("#pickup_stop_name#", pickup_stop_name);
        message = message.replaceAll("#dropoff_stop_name#", dropoff_stop_name);
        message = message.replaceAll("#booking_id#", "EC"+booking_id);
        message = message.replaceAll("#ride_date#", ride_date);
        message = message.replaceAll("#customer_care_no#", customer_care_no);

        System.out.println(message);

        SMSInfo sms = new SMSInfo();
        sms.message = message;
        sms.mobile = c.getMobile();
        // sms.mobile = "9908599937";//c.getMobile();

        sms.sendEnabled = sendEnabled;

        System.out.println("System booking cancellation message:" + message);
        boolean bookingMsgSent = smsService.sendSMS(sms);
        if (bookingMsgSent) {
            System.out.println("System Booking cancellation message sent to:" + commuterName + " for booking_id:" + booking_id);
        } else {
            System.out.println("[ERROR]System Booking cancellation message coudn't send to:" + commuterName + " for booking_id:" + booking_id);
        }
    }
   
    public void sendBookingCancellationSMS(Booking booking, boolean sendEnabled) {
        
        Commuter c = commuterService.getCommuterById(booking.getCommuter_id());

        int pickupPoint = booking.getFrom_stop();
        int dropoffPoint = booking.getTo_stop();

        Stops pickupStop = stopsService.getStop(pickupPoint);
        Stops dropoffStop = stopsService.getStop(dropoffPoint);

        String commuterName = c.getName();
        String pickup_stop_name = pickupStop.getDisplay_name();
        String dropoff_stop_name = dropoffStop.getDisplay_name();
        Integer booking_id = booking.getId();
        
        String[] dateTime = booking.getBookingTravelDateAndTimeString();
        
        String ride_date = dateTime[0];
        
        String customer_care_no = getCustomerCareNo();

        String message = CommonConstants.DEFAULT_BOOKING_CANCELLATION_MESSAGE_FORMAT;

        message = message.replaceAll("#name#", commuterName);
        message = message.replaceAll("#pickup_stop_name#", pickup_stop_name);
        message = message.replaceAll("#dropoff_stop_name#", dropoff_stop_name);
        message = message.replaceAll("#booking_id#", "EC"+booking_id);
        message = message.replaceAll("#ride_date#", ride_date);
        message = message.replaceAll("#customer_care_no#", customer_care_no);

        System.out.println(message);

        SMSInfo sms = new SMSInfo();
        sms.message = message;
        sms.mobile = c.getMobile();
        // sms.mobile = "9908599937";//c.getMobile();

        sms.sendEnabled = sendEnabled;

        System.out.println("booking cancellation message:" + message);
        boolean bookingMsgSent = smsService.sendSMS(sms);
        if (bookingMsgSent) {
            System.out.println("Booking cancellation message sent to:" + commuterName + " for booking_id:" + booking_id);
        } else {
            System.out.println("[ERROR]Booking cancellation message coudn't send to:" + commuterName + " for booking_id:" + booking_id);
        }
    }

    public BookingHistoryResponse toBookingHisotry(Booking booking) {
         return toBookingHisotry(booking, false);
    }
    
    public BookingHistoryResponse toBookingHisotry(Booking booking, boolean booking_tracking_enabled) {
        if (booking == null) {
            return new BookingHistoryResponse();
        }
        BookingHistoryResponse resp = new BookingHistoryResponse();
        resp.setCommuter_id(booking.getCommuter_id());
        resp.setBooking_id(booking.getId());
        resp.setBooking_travel_date(booking.getBooking_travel_date());
        resp.setCharged_fare(booking.getCharged_fare());
        resp.setCharged_free_rides(booking.getCharged_free_rides());
        resp.setActual_fare(booking.getActual_fare());

        Stops fromStops = stopsService.getStop(booking.getFrom_stop());
        String from_stop = fromStops.getStop_name();

        Stops toStops = stopsService.getStop(booking.getTo_stop());
        String to_stop = toStops.getStop_name();

        resp.setFrom_stop_id(booking.getFrom_stop());
        resp.setTo_stop_id(booking.getTo_stop());

        resp.setFrom_stop(from_stop);
        resp.setTo_stop(to_stop);

        resp.setNum_seats_booked(booking.getNum_seats_booked());
        resp.setBooking_status(BookingStatus.getBookingStatus(booking.getStatus()));

        resp.setBooking_travel_date(booking.getBooking_travel_date());
        resp.setBooking_travel_date_time(booking.getBooking_travel_date_time());
        
        BusFarePriceEntity farePriceEntity = fareService.fetchFare(booking.getFrom_stop(), booking.getTo_stop());
        // TODO: get these values
        resp.setDistance_km(farePriceEntity.getDistance());
        resp.setGeneral_travel_time(farePriceEntity.getTime_duration());
        resp.setBooking_tracking_enabled(booking_tracking_enabled);
        
        return resp;
    }

    public List<BookingHistoryResponse> toBookingHisotry(List<Booking> bookings) {
        if (bookings == null || bookings.size() == 0) {
            return new ArrayList<BookingHistoryResponse>();
        }
        List<BookingHistoryResponse> history = new ArrayList<BookingHistoryResponse>();
        for (Booking booking : bookings) {
            history.add(toBookingHisotry(booking));
        }
        return history;
    }

//    public PreBookingDetails getPreBookingDetails1(int trip_id, int source_stop_id, int dest_stop_id) throws Exception {
//        List<RouteStops> stops = null;
//        PreBookingDetails prebooking_details = new PreBookingDetails();
//        try {
//            stops = tripService.getRouteStops(trip_id);
//            BusFarePriceEntity fare = fareService.fetchFare(source_stop_id, dest_stop_id);
//
//            FareDTO fareDTO = new FareDTO();
//            fareDTO.setFrom_stop_id(fare.getSource_stop_id());
//            fareDTO.setTo_stop_id(fare.getDest_stop_id());
//
//            fareDTO.setFrom_stop(fare.getSource_stop().getStop_name());
//            fareDTO.setTo_stop(fare.getDest_stop().getStop_name());
//
//            fareDTO.setCharged_fare(fare.getFare());
//            fareDTO.setActual_fare(fare.getActual_fare());
//
//            prebooking_details.setStops(stops);
//            prebooking_details.setFare(fareDTO);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        }
//        return prebooking_details;
//    }
    
    public PreBookingDetails getPreBookingDetails(Integer commuter_id, Integer num_seats_choosen, int trip_id, int source_stop_id, int dest_stop_id) throws Exception {
        List<RouteStops> routeStops = null;
        PreBookingDetails prebooking_details = new PreBookingDetails();
        try {
            routeStops = tripService.getRouteStops(trip_id);
            
            // sorting on ascending order.
            Collections.sort(routeStops, new Comparator<RouteStops>() {

                @Override
                public int compare(RouteStops o1, RouteStops o2) {
                    return o1.getStop_number().compareTo(o2.getStop_number());
                }
            });
            List<StopsResponseDTO> stopsList = new ArrayList<StopsResponseDTO>();
            int first_stop_id = -1;
            for (RouteStops rs : routeStops) {
                StopsResponseDTO sr = new StopsResponseDTO();
                sr.route_stop_id = null;
                Stops s = rs.getStop();
                
                // first stop, only should set one time.
                if (first_stop_id == -1) {
                    first_stop_id = s.getId();
                }

                sr.stop_id = s.getId();
                sr.stop_name = s.getStop_name();
                sr.latitude = s.getLatitude();
                sr.longitude = s.getLongitude();
                sr.stop_number = rs.getStop_number();
                sr.stop_time = rs.getStop_time();
                sr.enabled = s.getEnabled();
                sr.shortcode = s.getShortcode();
                sr.help_text = s.getHelp_text();
                sr.image_path = s.getImage_path();
                sr.display_name = s.getDisplay_name();
                
                if (s.getId() == source_stop_id) {
                    sr.user_boarding_point = true;
                }
                if (s.getId() == dest_stop_id) {
                    sr.user_drop_off_point = true;
                }
                if (s.getId() == first_stop_id) {
                    sr.vehicle_started_point = true;
                }
                stopsList.add(sr);
                // Now we added the stop that is the user's boarding point.
                // we should break the loop
                // we need to only show till the user drop offs
                if(sr.user_drop_off_point==true){
                    break;
                }
            }
            
            BusFarePriceEntity fare = fareService.fetchFare(source_stop_id, dest_stop_id);

            System.out.println("fare:"+fare);
            FareDTO fareDTO = new FareDTO();
            fareDTO.setFrom_stop_id(source_stop_id);
            fareDTO.setTo_stop_id(dest_stop_id);

            System.out.println("source_stop_id:"+source_stop_id +" dest_stop_id:"+dest_stop_id);

            Stops sourceStop = stopsService.getStop(source_stop_id);
            Stops destStop = stopsService.getStop(dest_stop_id);
            
            System.out.println("sourceStop:"+sourceStop +" destStop:"+destStop);
            
            fareDTO.setFrom_stop(sourceStop.getStop_name());
            fareDTO.setTo_stop(destStop.getStop_name());

            int charged_fare_credis_required;
            int actual_fare_credits_required;
            
            // if discounted fare is enabled. then
            // charged fare is discounted fare and actual fare is charged fare
            // if discount is not enable then
            // charged fare is charged fare and actual fare is actual fare
            if (fare.getDiscounted_fare_enabled() != null && fare.getDiscounted_fare_enabled()) {
                charged_fare_credis_required = fare.getDiscounted_fare();
                actual_fare_credits_required = fare.getCharged_fare();
            } else {
                charged_fare_credis_required = fare.getCharged_fare();
                actual_fare_credits_required = fare.getActual_fare();
            }
            
//            System.out.println("fare.getCharged_fare():+"+fare.getCharged_fare());
//            System.out.println("fare.getActual_fare():+"+fare.getActual_fare());
            System.out.println("charged_fare_credis_required:+" + charged_fare_credis_required+", discount enabled:"+fare.getDiscounted_fare_enabled());
            System.out.println("actual_fare_credits_required:+" + actual_fare_credits_required+", discount enabled:"+fare.getDiscounted_fare_enabled());

            // fareDTO.setCharged_fare(fare.getCharged_fare());
            // fareDTO.setActual_fare(fare.getActual_fare());
            fareDTO.setCharged_fare(charged_fare_credis_required);
            fareDTO.setActual_fare(actual_fare_credits_required);
            
            AccountDTO accountDTO = null;
            ChargedFare chargedFare = null;
            if (commuter_id != null) {
                accountDTO = txService.getUserAccountBalance(commuter_id);
                System.out.println("accountDTO:"+accountDTO);
                //int charged_fare_credits_per_person_required = fare.getCharged_fare();
                int charged_fare_credits_per_person_required = charged_fare_credis_required;

                if (num_seats_choosen == null) {
                    num_seats_choosen = 1;
                }
                System.out.println("commuter_id:"+commuter_id+"num_seats_choosen:"+num_seats_choosen+"--charged_fare_credits_per_person_required:"+charged_fare_credits_per_person_required);

//                chargedFare = chargeFare(commuter_id, num_seats_choosen, charged_fare_credits_per_person_required);
//                System.out.println("chargedFare:"+chargedFare);
//                if (chargedFare != null) {
//                    fareDTO.setCharged_fare(chargedFare.getCharged_credit_points());
//                    fareDTO.setCharged_free_rides(chargedFare.getCharged_free_rides());
//                    fareDTO.setActual_fare(fare.getActual_fare());
//                } else {
//                    fareDTO.setCharged_fare(fare.getCharged_fare());
//                    fareDTO.setCharged_free_rides(accountDTO.getFree_rides_balance());
//                    fareDTO.setActual_fare(fare.getActual_fare());
//                }
                
                //fareDTO.setCharged_fare(fare.getCharged_fare());
                fareDTO.setCharged_fare(charged_fare_credis_required);
                fareDTO.setCharged_free_rides(accountDTO.getFree_rides_balance());
                //fareDTO.setActual_fare(fare.getActual_fare());
                fareDTO.setActual_fare(actual_fare_credits_required);

                System.out.println("fareDTO:"+fareDTO);
            }

            prebooking_details.setStops(stopsList);
            prebooking_details.setFare(fareDTO);
            prebooking_details.setAccount(accountDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return prebooking_details;
    }
    
    public PreBookingCreditDetails getPreBookingCreditDetails(Integer commuter_id, Integer num_seats_choosen, int trip_id, int source_stop_id, int dest_stop_id) throws Exception {
        PreBookingCreditDetails prebooking_credit_details = new PreBookingCreditDetails();
        try {
            BusFarePriceEntity fare = fareService.fetchFare(source_stop_id, dest_stop_id);

            System.out.println("fare:"+fare);
            FareDTO fareDTO = new FareDTO();
            fareDTO.setFrom_stop_id(source_stop_id);
            fareDTO.setTo_stop_id(dest_stop_id);

            System.out.println("source_stop_id:"+source_stop_id +" dest_stop_id:"+dest_stop_id);

            Stops sourceStop = stopsService.getStop(source_stop_id);
            Stops destStop = stopsService.getStop(dest_stop_id);
            
            System.out.println("sourceStop:"+sourceStop +" destStop:"+destStop);
            
            fareDTO.setFrom_stop(sourceStop.getStop_name());
            fareDTO.setTo_stop(destStop.getStop_name());
            
            int charged_fare_credis_required;
            int actual_fare_credits_required;
            
            // if discounted fare is enabled. then
            // charged fare is discounted fare and actual fare is charged fare
            // if discount is not enable then
            // charged fare is charged fare and actual fare is actual fare
            if (fare.getDiscounted_fare_enabled() != null && fare.getDiscounted_fare_enabled()) {
                charged_fare_credis_required = fare.getDiscounted_fare();
                actual_fare_credits_required = fare.getCharged_fare();
            } else {
                charged_fare_credis_required = fare.getCharged_fare();
                actual_fare_credits_required = fare.getActual_fare();
            }
            
            //fareDTO.setCharged_fare(fare.getCharged_fare());
            //fareDTO.setActual_fare(fare.getActual_fare());

            fareDTO.setCharged_fare(charged_fare_credis_required);
            fareDTO.setActual_fare(actual_fare_credits_required);
            
            AccountDTO accountDTO = null;
            ChargedFare chargedFare = null;
            if (commuter_id != null) {
                accountDTO = txService.getUserAccountBalance(commuter_id);
                //int charged_fare_credits_per_person_required = fare.getCharged_fare();
                int charged_fare_credits_per_person_required = charged_fare_credis_required;

                if (num_seats_choosen == null) {
                    num_seats_choosen = 1;
                }
                chargedFare = chargeFare(commuter_id, num_seats_choosen, charged_fare_credits_per_person_required);
                fareDTO.setCharged_fare(chargedFare.getCharged_credit_points());
                fareDTO.setCharged_free_rides(chargedFare.getCharged_free_rides());
                //fareDTO.setActual_fare(fare.getActual_fare());
                fareDTO.setActual_fare(actual_fare_credits_required);
            }

            prebooking_credit_details.setFare(fareDTO);
            prebooking_credit_details.setAccount(accountDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return prebooking_credit_details;
    }
    
    // Not working
    public List<CommuterBookingResponse> getAllCommuters(String device_id) {
        DeviceInfo deviceInfo = deviceService.getDeviceInfoByECDeviceId(device_id);
        int vehicle_id = deviceInfo.getVehicle_id();
        //Trips trip = tripService.getTripsByBusId(vehicle_id);
        return getAllCommuters(0);
    }
    
    public List<CommuterBookingResponse> getAllCommuters(int trip_id) {
        Trips trip = tripService.getTrip(trip_id);
        List<Booking> bookings = getBookingByTripId(trip.getId());

        List<CommuterBookingResponse> respList = new ArrayList<CommuterBookingResponse>();

        Routes r = trip.getTripDetail().getRoutes();

        List<RouteStops> route_stops = routeStopsService.getStopsByRouteId(r.getId());
        
        if (route_stops != null) {
            Collections.sort(route_stops, new RouteStopsService.StopNumberComparator());
        }

        // it wil be like this, a tree structure based on stop name and list of
        // all commuters boarding/dropping that stop.
        // 1.stop name (Mindspace)
        // / commutername: Rahul
        // ============> : Mayank
        // 2. stop name (IIIT)
        // ============> : Niranjan
        for (RouteStops rs : route_stops) {

            Stops stop = rs.getStop();
            CommuterBookingResponse bookingResp = new CommuterBookingResponse();
            bookingResp.setStop_name(stop.getDisplay_name());
            bookingResp.setStop_time(rs.getStop_time());
            bookingResp.setStop_number(rs.getStop_number());
            bookingResp.setTrip_id(trip_id);

            List<CommuterResponse> cbresps = new ArrayList<CommuterResponse>();

            for (Booking booking : bookings) {
                int commuter_id = booking.getCommuter_id();
                int pickup_stop_id = booking.getFrom_stop();
                int dropoff_stop_id = booking.getTo_stop();
                int charged_fare = booking.getCharged_fare();
                int booking_status = booking.getStatus();
                int num_seats_booked = booking.getNum_seats_booked();

                if (pickup_stop_id != stop.getId()) {
                    continue;
                }

                // Adding catch so, if any exception comes for a commuter, it
                // will not block other connuter details to be shown.
                try {
                    //Exception Here
                    Commuter commuter = commuterService.getCommuterById(commuter_id);
                    if (commuter == null) {
                        System.out.println("Commuter id:" + commuter_id + " does not exist in system");
                        continue;
                    }
                    CommuterResponse cbresp = new CommuterResponse();
                    cbresp.commuter_id = commuter_id;
                    cbresp.commuter_name = commuter.getName();
                    cbresp.booking_id = booking.getId();
                    cbresp.pickup_stop_id = pickup_stop_id;
                    cbresp.dropoff_stop_id = dropoff_stop_id;
                    cbresp.charged_fare = charged_fare;
                    cbresp.mobile = commuter.getMobile();
                    cbresp.booking_status = booking_status;
                    cbresp.num_seats_booked = num_seats_booked;
                    
                    Stops pickupStop = stopsService.getStop(pickup_stop_id);
                    Stops dropoffStop = stopsService.getStop(dropoff_stop_id);
                    
                    cbresp.pickup_stop_name = pickupStop.getDisplay_name();
                    cbresp.dropoff_stop_name = dropoffStop.getDisplay_name();

                    if (pickup_stop_id == stop.getId()) {
                        cbresp.type = "Pickup";
                        cbresp.is_pickup = true;
                    } else if (dropoff_stop_id == stop.getId()) {
                        cbresp.type = "Dropoff";
                        cbresp.is_pickup = false;
                    }
                    // adding cbresp
                    cbresps.add(cbresp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            bookingResp.setCommuters(cbresps);
            respList.add(bookingResp);
        }
        return respList;
    }
    
    public void markBookingExpired(int trip_id) {
        markBookingExpired(trip_id, true);
    }
    
    public void markBookingExpired(int trip_id, boolean sendEnabled) {
        markBookingExpired(trip_id, null, sendEnabled);
    }
    
    public void markBookingExpired(int trip_id, Integer dropoff_stop_id, boolean sendEnabled) {
        try {
            System.out.println("markting all bookins for a trip:" + trip_id + " as expired");
            List<Integer> bookingStatuses = Arrays.asList(BookingStatus.RECEIVED.intValue(),
                    BookingStatus.ACCEPTED.intValue());

            // marking booking status as expired for all bookings done against
            // this
            // trip id
            List<Booking> bookings = null;
            if (dropoff_stop_id != null) {
                bookings = bookingDao.findBookingByTripIdAndDropOffStopId(trip_id, dropoff_stop_id, bookingStatuses);
            } else {
                bookings = bookingDao.findBookingByTripId(trip_id, bookingStatuses);
            }
            for (Booking booking : bookings) {
                System.out.println("Marking expired booking:" + booking.getId());
                boolean result = bookingDao.updateBookingStatusAsExpiredForActiveBookingsByBookingId(booking.getId(),
                        BookingStatus.EXPIRED);
                System.out.println("booking obj after marked expired :" + booking);
                if (result) {
                    try {
                        sendRideCompletedSMS(booking, sendEnabled);
                        System.out.println("Ride completed SMS sent to commuter id:" + booking.getCommuter_id());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        //sendRideCompletedEmailReceipt(booking, sendEnabled);
                        // TODO: commeting out for today.
                        System.out.println("Ride completed email receipt sent to commuter id:"
                                + booking.getCommuter_id());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Booking cant be maked expired it seems");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void sendRideCompletedSMS(Booking booking, boolean sendEnabled) {

        Commuter c = commuterService.getCommuterById(booking.getCommuter_id());

        if (c == null) {
            System.out.println("Commuter :" + booking.getCommuter_id()
                    + " is not available in system, so cant send ride completed SMS");
            return;
        }
        
        String commuterName = c.getName();
        Integer booking_id = booking.getId();

        String customer_care_no = getCustomerCareNo();

        String message = CommonConstants.DEFAULT_RIDE_COMPLETED_MESSAGE_FORMAT;

        message = message.replaceAll("#name#", commuterName);
        message = message.replaceAll("#customer_care_no#", customer_care_no);

        System.out.println(message);

        SMSInfo sms = new SMSInfo();
        sms.message = message;
        sms.mobile = c.getMobile();
        // sms.mobile = "9908599937";//c.getMobile();

        sms.sendEnabled = sendEnabled;

        System.out.println("trip completed message:" + message);
        boolean bookingMsgSent = smsService.sendSMS(sms);
        if (bookingMsgSent) {
            booking.setMessage_sent_for_dropoff(true);
            System.out.println("trip completed message sent to:" + commuterName + " for booking_id:" + booking_id);
            bookingDao.updateBookingMessageSentForDropoff(booking_id, true);
        } else {
            System.out.println("[ERROR]trip completed message coudn't send to:" + commuterName + " for booking_id:"
                    + booking_id);
        }
    }
    
    public void sendRideCompletedEmailReceipt(Booking booking, boolean sendEnabled) {
        Commuter commuter = commuterService.getCommuterById(booking.getCommuter_id());

        if (commuter != null) {
            String commuterName = commuter.getName();
            Integer booking_id = booking.getId();

            EmailDTO emailObj = new EmailDTO();
            emailObj.setTo(new String[]{commuter.getEmail()});
            emailObj.setSubject(commuterName + ", Your EasyCommute Ride receipt for booking#" + "EC" + booking_id);
            emailObj.setText("EasyCommute Ride receipt for booking#" + "EC" + booking_id);
            EmailResponse emailResp = emailProvider.sendEmail(emailObj);
            System.out.println("ride receipt: emailResp:" + emailResp);
        } else {
            System.out.println("Commuter :" + booking.getCommuter_id()
                    + " is not available in system, so cant send email receipt");
        }
    }
    
    public void sendBusReachingToStop(Stops currentStop, Booking booking, boolean sendEnabled) {

        String current_stop_name = currentStop.getDisplay_name();
        
        Commuter c = commuterService.getCommuterById(booking.getCommuter_id());

        int pickupPoint = booking.getFrom_stop();
        int dropoffPoint = booking.getTo_stop();

        Stops pickupStop = stopsService.getStop(pickupPoint);
        
        String commuterName = c.getName();
        Integer booking_id = booking.getId();

        String customer_care_no = getCustomerCareNo();
        
        String pickup_stop_name = pickupStop.getDisplay_name();
        String pickup_stop_help_text = pickupStop.getHelp_text();

        String message = CommonConstants.DEFAULT_BUS_REACHING_MESSAGE_FORMAT;

        message = message.replaceAll("#name#", commuterName);
        message = message.replaceAll("#current_stop_name#", current_stop_name);
        message = message.replaceAll("#pickup_stop_name#", pickup_stop_name);
        message = message.replaceAll("#pickup_stop_help_text#", pickup_stop_help_text);
        message = message.replaceAll("#customer_care_no#", customer_care_no);

        System.out.println(message);

        SMSInfo sms = new SMSInfo();
        sms.message = message;
        sms.mobile = c.getMobile();
        // sms.mobile = "9908599937";//c.getMobile();

        sms.sendEnabled = sendEnabled;

        System.out.println("bus reaching to your stop shortly message:" + message);
        boolean bookingMsgSent = smsService.sendSMS(sms);
        if (bookingMsgSent) {
            booking.setMessage_sent_for_pickup(true);
            System.out.println("bus reaching to your stop shortly message sent to:" + commuterName + " for booking_id:" + booking_id);
            bookingDao.updateBookingMessageSentForPickup(booking_id, true);
        } else {
            System.out.println("[ERROR]bus reaching to your stop shortly message coudn't send to:" + commuterName + " for booking_id:"
                    + booking_id);
        }
    }

    public void notifyUserAboutPickupPoint(int trip_id, Integer current_stop_id, boolean sendEnabled) {
        try {
            System.out.println("notifyUserAboutPickupPoint for a trip:" + trip_id + " as expired");
            List<Integer> bookingStatuses = Arrays.asList(BookingStatus.RECEIVED.intValue(),
                    BookingStatus.ACCEPTED.intValue());

            List<Booking> bookings = bookingDao.findBookingByTripId(trip_id, bookingStatuses);
            
            Trips trip = tripService.getTrip(trip_id);
            int route_id = trip.getTripDetail().getRouteid();
            Set<RouteStops> routeStops = routeStopsService.getAllAvailableFromStopsSortedByPosition(route_id);
            
            for (Booking booking : bookings) {
                
                // we already sent user a message for pickup, no need to send
                // again.
                boolean message_sent_for_pickup = booking.isMessage_sent_for_pickup();
                if (message_sent_for_pickup) {
                    continue;
                }
                int pickup_stop_id = booking.getFrom_stop();
                
                int before_stops_number = -1;
                for (RouteStops rs : routeStops) {
                    // if current stop is matching with some stop
                    if (rs.getStop().getId() == current_stop_id) {
                        before_stops_number = rs.getStop_number();
                    }
                    
                    int current_stop_number = rs.getStop_number();
                    System.out.println("bsn:" + before_stops_number + "--csn:" + current_stop_number);
                    if (before_stops_number != -1 && (current_stop_number == (before_stops_number + 2))) {
                        // it means the bus is at/near by a stop that is 2 stops
                        // ahead of the user's pickup stop.
                        sendBusReachingToStop(rs.getStop(), booking, sendEnabled);
                        // TODO: fix it here as users are getting multiple SMS
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String getCustomerCareNo() {
        String customer_care_no = CommonConstants.DEFAULT_CUSTOMER_CARE_NO;

        DashboardSettings settings = settingsService.getDashBoardSettings();
        if (settings != null) {
            String customerCareNumber = settings.getCustomer_care_number();
            if (customerCareNumber != null) {
                customer_care_no = customerCareNumber;
            }
        }
        return customer_care_no;
    }
    
    public BookingStopDetailsResponse getBookingStopDetails(int booking_id) {
        Booking b = this.getBookingById(booking_id);
        int from_stop_id = b.getFrom_stop();
        
        Stops stop = stopsService.getStop(from_stop_id);
        
        BookingStopDetailsResponse resp = new BookingStopDetailsResponse();
        resp.setBooking_status(BookingStatus.getBookingStatus(b.getStatus()).name());
        resp.setHelp_text(stop.getHelp_text());
        resp.setStop_image_path(stop.getImage_path());
        return resp;
    }
    
    public boolean registerNotifyAvailability(int commuter_id, int trip_id, int from_stop_id, int to_stop_id) {
        NotifyAvailability na = new NotifyAvailability();
        na.commuter_id = commuter_id;
        na.trip_id = trip_id;
        na.from_stop_id = from_stop_id;
        na.to_stop_id = to_stop_id;
        na.register_date = new Date();
        
        if (notifyAvailabilityMap.containsKey(trip_id)) {
            Set<NotifyAvailability> set = notifyAvailabilityMap.get(trip_id);
            set.add(na);
            notifyAvailabilityMap.put(trip_id, set);
        } else {
            Set<NotifyAvailability> set = new HashSet<BookingService.NotifyAvailability>();
            set.add(na);
            notifyAvailabilityMap.put(trip_id, set);
        }
        
        System.out.println("notifyAvailabilityMap:"+notifyAvailabilityMap);
        
        Thread t = new Thread(new Runnable(){
            
            public void run() {
                for (Set<NotifyAvailability> nas : notifyAvailabilityMap.values()) {
                    for (NotifyAvailability na : nas) {

                        Calendar c = Calendar.getInstance();
                        c.add(Calendar.DATE, -3);
                        Date beforeXDays = c.getTime();

                        if (na.getRegister_date().before(beforeXDays)) {
                            notifyAvailabilityMap.remove(na.getTrip_id());
                        }
                    }
                }
            };
            
        });
        t.start();
        return true;
    }

    private static Map<Integer, Set<NotifyAvailability>> notifyAvailabilityMap = new ConcurrentHashMap<Integer, Set<NotifyAvailability>>();

    public static class NotifyAvailability {
        int trip_id;
        int commuter_id;
        int from_stop_id;
        int to_stop_id;
        Date register_date;
        
        public int getTrip_id() {
            return trip_id;
        }
        public void setTrip_id(int trip_id) {
            this.trip_id = trip_id;
        }
        public int getCommuter_id() {
            return commuter_id;
        }
        public void setCommuter_id(int commuter_id) {
            this.commuter_id = commuter_id;
        }
        public int getFrom_stop_id() {
            return from_stop_id;
        }
        public void setFrom_stop_id(int from_stop_id) {
            this.from_stop_id = from_stop_id;
        }
        public int getTo_stop_id() {
            return to_stop_id;
        }
        public void setTo_stop_id(int to_stop_id) {
            this.to_stop_id = to_stop_id;
        }
        public Date getRegister_date() {
            return register_date;
        }
        public void setRegister_date(Date register_date) {
            this.register_date = register_date;
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + commuter_id;
            result = prime * result + from_stop_id;
            result = prime * result + to_stop_id;
            result = prime * result + trip_id;
            return result;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            NotifyAvailability other = (NotifyAvailability) obj;
            if (commuter_id != other.commuter_id)
                return false;
            if (from_stop_id != other.from_stop_id)
                return false;
            if (to_stop_id != other.to_stop_id)
                return false;
            if (trip_id != other.trip_id)
                return false;
            return true;
        }
        
        @Override
        public String toString() {
            return "NotifyAvailability [trip_id=" + trip_id + ", commuter_id=" + commuter_id + ", from_stop_id="
                    + from_stop_id + ", to_stop_id=" + to_stop_id + ", register_date=" + register_date + "]";
        }
        
    }
    
    
    public static class ChargedFare {
        int charged_credit_points;
        int charged_free_rides;

        public int getCharged_credit_points() {
            return charged_credit_points;
        }
        public void setCharged_credit_points(int charged_credit_points) {
            this.charged_credit_points = charged_credit_points;
        }
        public int getCharged_free_rides() {
            return charged_free_rides;
        }
        public void setCharged_free_rides(int charged_free_rides) {
            this.charged_free_rides = charged_free_rides;
        }
        
        @Override
        public String toString() {
            return "ChargedFare [charged_credit_points=" + charged_credit_points + ", charged_free_rides="
                    + charged_free_rides + "]";
        }
    }
    
}
