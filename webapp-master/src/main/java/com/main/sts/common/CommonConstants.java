package com.main.sts.common;

import com.main.sts.common.CommonConstants.BookingStatus;

public interface CommonConstants {

 // "Hello, thanks for signing up!, your referral code is ESY20X";
//    String OTP_MSG = "Hello, thanks for signing up!, your otp code is %s";

    String OTP_MSG = "Dear %s, thank you for signing up!, your otp code is %s";

    String WELCOME_MSG = "Dear %s, Welcome to EasyCommute. We are excited to have you join us and start a new commute experience. We have already "
            + "credited %s free rides in your account. Please use \"Suggest Route\" if we are not serving on your route. Refer your friends using \"Refer & Earn\" and earn free rides! Happy EasyCommuting!";
    
    String WELCOME_MSG_ALREADY_REGISTERED = "Dear %s, Welcome to EasyCommute again. We are excited to have you join us and start a new commute experience. Please use \"Suggest Route\" if we are not serving on your route. Refer your friends using \"Refer & Earn\" and earn free rides! Happy EasyCommuting!";
    
    String NOTIFICATION_WELCOME_MSG_TITLE = "Welcome to EasyCommute";
    
    //String NOTIFICATION_WELCOME_MSG_NAME = "Thank you %s for signing up. You will receive a welcome message shortly.";
    //String NOTIFICATION_WELCOME_MSG = "Thank you for signing up. You will receive a welcome message shortly.";

    String NOTIFICATION_WELCOME_MSG_NAME = "Thank you %s for signing up. Hope you will enjoy our service. Happy EasyCommuting!";
    String NOTIFICATION_WELCOME_MSG = "Thank you for signing up. Hope you will enjoy our service. Happy EasyCommuting!";
    
    String ALERT_MESSAGE = "Emergency Alert! User #name# is in urgent need. Call #gender_type# on #mobile# now.";
    String ALERT_MESSAGE_USER_BOARDED = "#gender_type2# boarded the vehicle #vehicle_type# #vehicle_number# from #pickup_stop_name# to #dropoff_stop_name# at #pickup_stop_time# with booking id #booking_id#. Driver no. is #driver_no#";
    
    int DEFAULT_SIGNUP_POINTS = 0;
    int DEFAULT_SIGNUP_FREE_RIDES = 2;
    int DEFAULT_REFERRAL_FREE_RIDES = 1; // now making it 
    int DEFAULT_REFERRAL_FREE_CREDITS = 30; // now making it 

    int MAX_REFERRAL_FREE_RIDES = 5;// TODO: 5
    int NUMBER_REFERRAL_1MONTH_FREE_RIDES = 5;// TODO: 5
    
    int DEFAULT_CANCELLATION_ALLOWED_BEFORE_X_MINS = 30;

    int DEFAULT_CREDITS_AFTER_MAX_REFERRAL = 10;

    boolean SIGNUP_CREDIT_PROMOTION_SCHEME_ENABLED = true;
    
    public static final String DEFAULT_BOOKING_CONFIRMATION_MESSAGE_FORMAT = "Hi #name#, your booking id for your EasyCommute ride from #pickup_stop_name# to #dropoff_stop_name# with #num_seats# seat(s) is #booking_id#. "
            + "Bus details: #vehicle_type# No: #vehicle_number#. Ride date: #ride_date# The bus depart from #pickup_stop_name#, #pickup_stop_help_text#"
            + " at #pickup_stop_time#. If any queries, call us on #customer_care_no#";
    
    public static final String DEFAULT_BOOKING_CANCELLATION_MESSAGE_FORMAT = "Hi #name#, your booking id #booking_id# for your EasyCommute ride from #pickup_stop_name# to #dropoff_stop_name# on #ride_date# is cancelled."
            + " Your credits will be refunded back as per refund policy. If any queries, call us on #customer_care_no#";
    
    public static final String DEFAULT_SYSTEM_BOOKING_CANCELLATION_MESSAGE_FORMAT = "Hi #name#, your booking id #booking_id# for your EasyCommute ride from #pickup_stop_name# to #dropoff_stop_name# on #ride_date# is cancelled due to operational reasons."
            + " We sincerely regret for the inconvenience caused to you. Your ride credits will be fully refunded back to you. If any queries, call us on #customer_care_no#";

    
    public static final String DEFAULT_NOTIFY_AVAILABILITY_MESSAGE_FORMAT = "Dear user, Seat(s) is available now from #pickup_stop_name# to #dropoff_stop_name# on #ride_date#. You can book now!. If any queries, call us on #customer_care_no#";
    
    public static final String DEFAULT_RIDE_COMPLETED_MESSAGE_FORMAT = "Hi #name#, thank you for riding with us. Please share your experience and suggestions in feedback section in EasyCommute app (http://bit.ly/EasyCommuteApp). We hope to serve you again soon! If any queries, call us on #customer_care_no#";
    
    public static final String DEFAULT_BUS_REACHING_MESSAGE_FORMAT = "Hi #name#, your bus had left #current_stop_name#. It will be reaching to #pickup_stop_name#, #pickup_stop_help_text# shortly. If any queries, call us on #customer_care_no#";

    public static final String DEFAULT_CUSTOMER_CARE_NO = "8099927902";
    
//    public static final String REFERRAL_MESSAGE = "Register EassyCommute app with my code %s and get 2 free rides. Download EasyCommute "
//            + "https://play.google.com/store/apps/details?id=easy.commute.shuttle.app and enjoy rides.";
    
//    public static final String REFERRAL_MESSAGE = "Register in EasyCommute app, Bus Shuttle Service with my code %s and get 1 free ride. Download EasyCommute now at "
//            + "http://bit.ly/EasyCommuteApp and enjoy free rides.";

//    public static final String REFERRAL_MESSAGE = "Register in EasyCommute app, Bus Shuttle Service with my code %s and get 1 extra free ride with 2 free rides. Download EasyCommute now at "
//            + "http://bit.ly/EasyCommuteApp and enjoy free rides.";

//    public static final String REFERRAL_MESSAGE = "Register in EasyCommute app an AC Bus Shuttle Service with Confirmed Seat, Free Wifi(4G), GPS Tracking starting from 15/-Rs with my code %s and get 1 extra free ride with 2 free rides. Download EasyCommute now from "
//            + "http://bit.ly/EasyCommuteApp and enjoy free rides.";
  
    public static final String REFERRAL_MESSAGE = "Register in EasyCommute app an AC Bus Shuttle Service with Confirmed Seat, Free Wifi(4G), GPS Tracking starting from 15/-Rs with my code %s and get 2 free rides. Download EasyCommute now from "
            + "http://bit.ly/EasyCommuteApp and enjoy free rides.";
    
    public static final String FREE_RIDES_ACC_MESSAGE = "You have %s free ride(s)!";
    public static final String FREE_RIDES_ACC_MESSAGE_1MONTH = "You have 2 free ride(s)/day for 1 Month till 10th Jan 2016";

    public static final int MAX_ACTIVE_BOOKINGS_ALLOWED_AT_A_TIME = 5;

    public final static String bus_ontime = "ontime";
    public final static String bus_late = "late";
    public final static String bus_verylate = "verylate";
    public final static String trip_running = "running";
    public final static String trip_over = "over";
    public final static String PICKUP = "Pick Up";
    public final static String DROPOFF = "Drop Off";
    public final static int ETA_LATE_TIME = 0;
    // public final static int ETA_SEND_TIME=5;
    public final static int STOP_DEVICE_DIFF = 5;
    public final static int STOPS_WITHIN_RANGE = 5;
    public final static String ON = "on";
    public final static String ETA_MSG = "There is no arrival time available at the moment for this bus."
        + " Except serious delay. Please contact your school administration for more information.";
    public final static String subscriber_type_student = "student";
    public final static String subscriber_type_staff = "staff";
    public final static String subscriber_type_driver = "driver";
    public final static int ontime = 5, late = 15;
    
    public static enum TransactionType {
        CREDIT(1),
        DEBIT(2);

        int type;

        private TransactionType(int type) {
            this.type = type;
        }

        public int getTypeCode() {
            return type;
        }
        
        public int intValue() {
            return getTypeCode();
        }
        
        public static TransactionType getTransactionType(int id) {
            for (TransactionType bs : values()) {
                if (bs.type == id) {
                    return bs;
                }
            }
            return null;
        }
    }

    public static enum TransactionStatus {
        UNKNOWN(-1),
        SUCCESS(1),
        FAILED(2);

        int type;

        private TransactionStatus(int type) {
            this.type = type;
        }

        public int getTypeCode() {
            return type;
        }
        
        public int intValue() {
            return getTypeCode();
        }
        
        public static TransactionStatus getTransactionStatus(int id) {
            for (TransactionStatus bs : values()) {
                if (bs.type == id) {
                    return bs;
                }
            }
            return null;
        }
    }

    public static enum UserActiveType {
        ACTIVE(0),
        BLOCKED(1),
        SUSPENDED(2);

        int type;

        private UserActiveType(int type) {
            this.type = type;
        }

        public int getTypeCode() {
            return type;
        }
        
        public int intValue() {
            return getTypeCode();
        }
        
        public static UserActiveType getUserActiveType(int id) {
            for (UserActiveType bs : values()) {
                if (bs.type == id) {
                    return bs;
                }
            }
            return null;
        }
    }

    public static enum UserStatusType {
        REGISTED_NOT_ACTIVATED(0),
        VERIFIED_ACTIVATED(1);

        int type;

        private UserStatusType(int type) {
            this.type = type;
        }

        public int getTypeCode() {
            return type;
        }
        
        public int intValue() {
            return getTypeCode();
        }
        
        public static UserStatusType getUserStatusType(int id) {
            for (UserStatusType bs : values()) {
                if (bs.type == id) {
                    return bs;
                }
            }
            return null;
        }
    }

    public static enum TransactionBy {
        SYSTEM(0),
        ADMIN(1),
        USER(2);

        int type;

        private TransactionBy(int type) {
            this.type = type;
        }

        public int getTypeCode() {
            return type;
        }
        
        public int intValue() {
            return getTypeCode();
        }
        
        public static TransactionBy getTransactionBy(int id) {
            for (TransactionBy bs : values()) {
                if (bs.type == id) {
                    return bs;
                }
            }
            return null;
        }
    }

    public static enum BookingStatus {
        RECEIVED(0),
        ACCEPTED(1),
        REJECTED(2),
        CANCELLED_NOREFUND(3),
        CANCELLED_REFUNDED(4),
        CANCELLED_PARTIAL_REFUNDED(5),
        EXPIRED(6), ;

        private int value;
        
        private BookingStatus(int id) {
            this.value = id;
        }

        public int getBookingStatus() {
            return value;
        }

        public int intValue() {
            return getBookingStatus();
        }
        
        public static BookingStatus getBookingStatus(int id) {
            for (BookingStatus bs : values()) {
                if (bs.value == id) {
                    return bs;
                }
            }
            return null;
        }

    }

    public static enum TransactionAction {

        RECHARGE(1),
        BOOKING(2),
        REFUND(3), 
        PARTIAL_REFUND(4),
        SYSTEM_RECHARGE(5),
        UPGRADE_OFFER(6),
        DEDUCT(7),
        SYSTEM_DEDUCT(8),
        EXPIRY(9);

        private int type;
        private TransactionAction(int id) {
            this.type = id;
        }

        public int getTypeCode() {
            return type;
        }
        
        public int intValue() {
            return getTypeCode();
        }
        
        public static TransactionAction getTransactionAction(int id) {
            for (TransactionAction bs : values()) {
                if (bs.type == id) {
                    return bs;
                }
            }
            return null;
        }
        
        public String getName(){
            return name();
        }
    }
    
    public static enum ReferralCodeStatus {
        ACTIVE(0),
        EXPIRED(1), ;

        private int type;
        private ReferralCodeStatus(int id) {
            this.type = id;
        }

        public int getReferralCodeStatus() {
            return type;
        }
        
        public int intValue() {
            return getReferralCodeStatus();
        }

        public static ReferralCodeStatus getReferralCodeStatus(int id) {
            for (ReferralCodeStatus bs : values()) {
                if (bs.type == id) {
                    return bs;
                }
            }
            return null;
        }
        
        public String getName(){
            return name();
        }
    }
    
    public static enum TripStatus {
        NOT_STARTED(0, "not started"),
        RUNNING(1, "running"),
        CANCELLED(2, "cancelled"),
        OVER(3, "over"),
        STARTING_TRIP(4, "starting"),
        ENDING_TRIP(5, "ending"),
        ALREADY_ENDED(6, "already ended"), ;

        private int value;
        private String text;
        private TripStatus(int id, String text) {
            this.value = id;
            this.text = text;
        }

        public int getStatusValue() {
            return value;
        }
        
        public int intValue() {
            return getStatusValue();
        }
        
        public String getTripStatusText() {
            return text;
        }

        public static TripStatus getTripStatus(int value) {
            for (TripStatus bs : values()) {
                if (bs.value == value) {
                    return bs;
                }
            }
            return null;
        }
    }
    
    //// late,verylate,delay,ontime
    public static enum VehicleStatus {
        ONTIME(1, "ontime"),
        LATE(2, "late"),
        VERY_LATE(3, "very late"),
        OUT_OF_SERVICE(4,"outofservice"),
        DELAY(5, "delayed"),;

        private int value;
        private String text;
        private VehicleStatus(int id, String text) {
            this.value = id;
            this.text = text;
        }

        public int getStatusValue() {
            return value;
        }
        
        public int intValue() {
            return getStatusValue();
        }
        
        public String getStatusText() {
            return text;
        }

        public static VehicleStatus getVehicleStatus(int value) {
            for (VehicleStatus bs : values()) {
                if (bs.value == value) {
                    return bs;
                }
            }
            return null;
        }
        
        public static VehicleStatus getVehicleStatus(String text) {
            for (VehicleStatus bs : values()) {
                if (bs.text.equalsIgnoreCase(text)) {
                    return bs;
                }
            }
            return null;
        }
    }
    
    
    // //late,verylate,delay,ontime
    public static enum ReferralOfferType {
        DAYS(1, "Days"), // like 5 days, so means effective from start Time.
        TIME_RANGE(2, "Time Range"), // like 29th Dec to 31st Dec
        ;
        
        private int value;
        private String text;
        private ReferralOfferType(int id, String text) {
            this.value = id;
            this.text = text;
        }

        public int getStatusValue() {
            return value;
        }

        public int intValue() {
            return getStatusValue();
        }

        public String getStatusText() {
            return text;
        }

        public static ReferralOfferType getReferralOfferType(int value) {
            for (ReferralOfferType bs : values()) {
                if (bs.value == value) {
                    return bs;
                }
            }
            return null;
        }

        public static ReferralOfferType getReferralOfferType(String text) {
            for (ReferralOfferType bs : values()) {
                if (bs.text.equalsIgnoreCase(text)) {
                    return bs;
                }
            }
            return null;
        }
    }
   
    
    public static enum UserChannelType {
        MOBILE(1, "Mobile"),
        WEB(2, "WEB"),
        ;
        
        private int value;
        private String text;
        
        private UserChannelType(int id, String text) {
            this.value = id;
            this.text = text;
        }

        public int getStatusValue() {
            return value;
        }

        public int intValue() {
            return getStatusValue();
        }

        public String getStatusText() {
            return text;
        }

        public static UserChannelType getUserChannelType(int value) {
            for (UserChannelType bs : values()) {
                if (bs.value == value) {
                    return bs;
                }
            }
            return null;
        }

        public static UserChannelType getUserChannelType(String text) {
            for (UserChannelType bs : values()) {
                if (bs.text.equalsIgnoreCase(text)) {
                    return bs;
                }
            }
            return null;
        }
    }
   
    
    

}
