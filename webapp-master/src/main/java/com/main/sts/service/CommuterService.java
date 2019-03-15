package com.main.sts.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.criterion.Order;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.sts.common.CommonConstants;
import com.main.sts.common.CommonConstants.TransactionStatus;
import com.main.sts.common.CommonConstants.UserActiveType;
import com.main.sts.common.CommonConstants.UserChannelType;
import com.main.sts.common.CommonConstants.UserStatusType;
import com.main.sts.dao.sql.CommuterDao;
import com.main.sts.dto.CommuterDTO;
import com.main.sts.dto.EmailDTO;
import com.main.sts.dto.EmailResponse;
import com.main.sts.entities.Account;
import com.main.sts.entities.Commuter;
import com.main.sts.entities.CorporateReferralCode;
import com.main.sts.entities.DashboardSettings;
import com.main.sts.entities.PushMessage;
import com.main.sts.entities.ReferralCode;
import com.main.sts.entities.ReferralTransactionInfo;
import com.main.sts.entities.SMSCode;
import com.main.sts.entities.TransactionInfo;
import com.main.sts.util.MD5PassEncryptionClass;
import com.main.sts.util.PasswordEncrypter;

@Service("commuterService")
public class CommuterService {

    private Random rand = new Random(100000);

    @Autowired
    private GoogleGCMService gcmService;

    @Autowired
    private CommuterDao commuterDao;

    // OTP
    @Autowired
    private SmsCodeService smsCodeService;

    @Autowired
    private Msg91SMSService smsService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private SendGridMailProvider emailProvider;
    
    @Autowired
    private ReferralCodeService referralCodeService;
    
    @Autowired
    private TransactionService txService;
    
    @Autowired
    private ReferralTransactionService referralTxService;
    
    //@Autowired
    //private PushNotificationService pushNotificationService;
    
    @Autowired
    private NotificationService notificationService;
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private DashBoardSettingsService settingsService;
    

    public CommuterService() {

    }
    
    public static final String DEFAULT_ENCODING = "UTF-8";

    public static String base64encode(String text) {
        try {
            return new String(Base64.encodeBase64(text.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }// base64encode

    public static String base64decode(String text) {
        try {
            return new String(Base64.decodeBase64(text.getBytes("UTF-8")));
        } catch (IOException e) {
            return null;
        }
    }
    
    public List<Commuter> findAll() {
        return commuterDao.findAll();
    }

    // TODO: fix it to take only active andd verified
    public List<Commuter> findAllActiveAndVerified() {
        return commuterDao.findAllActiveAndVerified();
    }
    
    public List<Commuter> findAllActiveAndVerified(int offset, int limit) {
        return commuterDao.findAllActiveAndVerified(offset, limit);
    }

    public List<Commuter> findAll(int offset, int limit) {
        return commuterDao.findAll();
    }

    public List<Commuter> getCommutersList(Integer offset, Integer limit) {
        return commuterDao.getCommutersList(offset, limit);
    }
    
    public Commuter getCommuterById(int commuter_id) {
        return commuterDao.getCommuter(commuter_id);
    }

    public boolean insertCommuter(Commuter commuter) {
        return commuterDao.insertCommuter(commuter);
    }

    public ReturnCodes registerCommuter(String name, String email, String mobile, String gender, String gcm_regId, String referral_code_used, String device_id,
            UserChannelType channelType, boolean smsSendEnabled) throws ServiceException {
        Commuter c1 = getCommuterByMobile(mobile);
        if (c1 != null && c1.getStatus() == 1) {
            System.out.println("User already exist, again generating an otp");
            generateAndSendOTP(mobile, c1.getCommuter_id(), c1.getName(), smsSendEnabled);

            // Since User exist, generating an another OTP
            c1.setStatus(UserStatusType.REGISTED_NOT_ACTIVATED.getTypeCode());
            boolean status = commuterDao.updateCommuter(c1);
            if (status) {
                System.out.println("Commuter id:" + c1.getCommuter_id() + " inactivated as new OTP generated");
            } else {
                System.out.println("Failed to update in DB for commuter id:" + c1.getCommuter_id()
                        + " inactivated as new OTP generated");
            }
            return ReturnCodes.USER_ALREADY_EXIST;
        } else if (c1 != null && c1.getStatus() == 0) {
            System.out.println("User already exist but not activated due to Otp, resending an otp again");
            generateAndSendOTP(mobile, c1.getCommuter_id(), c1.getName(), smsSendEnabled);
            return ReturnCodes.USER_EXIST_OTP_GENERATED;
        } else {
            Commuter commuter = new Commuter();
            commuter.setName(name);
            commuter.setEmail(email);
            commuter.setMobile(mobile);
            commuter.setGender(gender);
            commuter.setGcm_reg_id(gcm_regId);
            // code by using a user is registering in system.
            commuter.setReferral_code_used(referral_code_used);
            commuter.setDevice_id(device_id);
            // Not verified
            commuter.setStatus(UserStatusType.REGISTED_NOT_ACTIVATED.getTypeCode());
            commuter.setActive(UserActiveType.ACTIVE.getTypeCode());// active
            commuter.setChannel_type(channelType.intValue());
            boolean success = insertCommuter(commuter);
            if (success) {
                System.out.println("Inserted commuter:" + email + " with id:" + commuter.getCommuter_id());
                generateAndSendOTP(mobile, commuter.getCommuter_id(), commuter.getName(), smsSendEnabled);

                return ReturnCodes.USER_CREATED_OTP_GENERATED;
            } else {
                System.out.println("Failed to insert commuter:" + email);
                return ReturnCodes.USER_CREATION_FAILED;
            }
        }
    }

    public ReturnCodes unregisterCommuter(String mobile) {
        Commuter commuter = getCommuterByMobile(mobile);
        if (commuter == null) {
            return ReturnCodes.USER_NOT_EXIST;
        }
        boolean status = deleteCommuter(commuter);
        System.out.println("unregisterCommuter:status:" + status);
        if (status) {
            return ReturnCodes.USER_UNREGISTERED_SUCCESSFULLY;
        } else {
            return ReturnCodes.USER_UNREGISTRATION_FAILED;
        }
    }

    public boolean sendWelcomeSignupMessageSMS(Commuter commuter, int freeRidesGiven, boolean alreadyRegistrationExist, boolean smsSendEnabled) {
        String name = commuter.getName();
        System.out.println("Sending welcome signup message on SMS to " + name);
        SMSInfo sms = new SMSInfo();
        sms.mobile = commuter.getMobile();
        if (alreadyRegistrationExist) {
            sms.message = String.format(CommonConstants.WELCOME_MSG_ALREADY_REGISTERED, name);
        } else {
            // ideally it shouldnt enter in else block as freeRides should be gt zero
            if (freeRidesGiven > 0) {
                sms.message = String.format(CommonConstants.WELCOME_MSG, name, freeRidesGiven);
            } else {
                sms.message = String.format(CommonConstants.WELCOME_MSG, name, CommonConstants.DEFAULT_SIGNUP_FREE_RIDES);
            }
        }
        System.out.println("msg:"+sms.message);
        sms.sendEnabled = smsSendEnabled;
        boolean status = smsService.sendSMS(sms);
        return status;
    }
    
    public void sendWelcomeSignupMessage(Commuter commuter, String gcm_regId) {
        PushMessage pushMessage = new PushMessage();
        pushMessage.setGcm_regid(gcm_regId);
        String name = commuter.getName();
        System.out.println("Sending welcome signup message");
        pushMessage.setTitle(CommonConstants.NOTIFICATION_WELCOME_MSG_TITLE);
        if (name != null) {
            pushMessage.setMessage(String.format(CommonConstants.NOTIFICATION_WELCOME_MSG_NAME, name));
        } else {
            pushMessage.setMessage(String.format(CommonConstants.NOTIFICATION_WELCOME_MSG));
        }
        System.out.println("Sending welcome signup message:" + pushMessage.getMessage());
        //this.sendPushMessage(pushMessage);
        int commuter_id = commuter.getCommuter_id();
        System.out.println("notificationService:"+notificationService);
        this.notificationService.publishNotification(commuter_id, pushMessage);
    }

//    public void sendPushMessage(PushMessage pushMessage) {
//        try {
//            gcmService.sendPushNotification(pushMessage);
//        } catch (Exception e) {
//            // Suppress any exception comes in this.
//            e.printStackTrace();
//        }
//    }

    public ReturnCodes updateGCMRegistrationId(int commuter_id, String gcm_regId) {
        Commuter commuter = getCommuterById(commuter_id);
        commuter.setGcm_reg_id(gcm_regId);
        boolean status = updateCommuter(commuter);
        if (status) {
            return ReturnCodes.GCM_REGISTRATION_SUCCESSFUL;
        } else {
            return ReturnCodes.GCM_REGISTRATION_FAILED;
        }
    }

    private void generateAndSendOTP(String mobile, int commuter_id, String name, boolean smsSendEnabled) {
        int otp = generateOtp();
        boolean sent = sendOTP(mobile, otp, name, smsSendEnabled);
        System.out.println("OTP sent:" + sent + " for :" + commuter_id);
        boolean added = addToSMSCode(commuter_id, otp);
        if (added) {
            System.out.println("Added to SMS code successfully");
        } else {
            System.out.println("Add to SMS code failed");
        }
    }

    public int generateOtp() {
        int otp = rand.nextInt(1000000);
        System.out.println("otp:" + otp);
        return otp;
    }

    public boolean sendOTP(String mobile, int otp, String name, boolean smsSendEnabled) {
        SMSInfo sms = new SMSInfo();
        sms.mobile = mobile;
        sms.message = String.format(CommonConstants.OTP_MSG, name, otp);
        sms.sendEnabled = smsSendEnabled;
        System.out.println("OTP message:"+sms.message);
        boolean status = smsService.sendSMS(sms);
        return status;
    }

    public Commuter getCommuterByMobile(String mobile) {
        return commuterDao.getCommuterByMobile(mobile);
    }

    public Commuter getCommuterByEmail(String email) {
        return commuterDao.getCommuterByEmail(email);
    }

    public boolean addToSMSCode(int commuter_id, int otp) {
        SMSCode smsCode = new SMSCode();
        smsCode.setCommuter_id(commuter_id);
        smsCode.setCode(otp);
        smsCode.setStatus(0);
        boolean status = smsCodeService.insertSMSCode(smsCode);
        if (status) {
            return true;
        }
        return false;
    }

    // We shouldn't allow user to update his email id.
    public boolean updateCommuterDetails(CommuterDTO commuterDTO) {
        
        System.out.println("commuterDTO:"+commuterDTO);
        Commuter commuter = getCommuterById(commuterDTO.getCommuter_id());
        
        if(commuter==null){
            System.out.println("commuter shouldnt be null");
        }
        System.out.println("commuter:"+commuter);

        // updating only these three details. Mobile no. should not be updated at all until unless deemed needed.
        commuter.setName(commuterDTO.getName());
        commuter.setEmail(commuterDTO.getEmail());
        commuter.setGender(commuterDTO.getGender());
        commuter.setPassword(commuterDTO.getPassword());
        return commuterDao.updateCommuter(commuter);
    }
    
    public boolean updateCommuterDetails(int commuter_id, String name, String email, String gender, String password, int status, int active) {
        Commuter commuter = getCommuterById(commuter_id);
        
        if(commuter==null){
            System.out.println("commuter shouldnt be null");
        }
        System.out.println("commuter:"+commuter);

        // updating only these three details. Mobile no. should not be updated
        // at all until unless deemed needed.
        if (isNonEmpty(name)) {
            commuter.setName(name);
        }
        if (isNonEmpty(email)) {
            commuter.setEmail(email);
        }
        if (isNonEmpty(gender)) {
            commuter.setGender(gender);
        }
        if (isNonEmpty(password)) {
            commuter.setPassword(password);
        }

        commuter.setStatus(status);
        commuter.setActive(active);
        
        return commuterDao.updateCommuter(commuter);
    }
    
    private boolean isNonEmpty(String s) {
        if (s != null && s.length() > 0) {
            return true;
        }
        return false;
    }
    
    public boolean updateCommuterPassword(int commuter_id, String password) {
        
        Commuter commuter = getCommuterById(commuter_id);
        commuter.setPassword(password);
        return commuterDao.updateCommuter(commuter);
    }
    
    private boolean updateCommuter(Commuter commuter) {
        return commuterDao.updateCommuter(commuter);
    }

    public boolean deleteCommuter(Commuter commuter) {
        System.out.println("deleting commuter:" + commuter.getCommuter_id());
        return commuterDao.deleteCommuter(commuter);
    }

    public boolean deleteCommuter(int commter_id) {
        Commuter commuter = getCommuterById(commter_id);
        return commuterDao.deleteCommuter(commuter);
    }

    public boolean updateCommuterStatus(Commuter commuter, int status) {
        commuter.setStatus(status);
        return commuterDao.updateCommuter(commuter);
    }

    public List<Commuter> searchCommuters(String type, String str) {
        return commuterDao.searchCommuters(str, type);
    }
   
    public List<Commuter> searchCommuters(String columnName, Integer searchedValue) {
        return commuterDao.searchCommuters(columnName, searchedValue);
    }

    public ReturnCodes verifyCommuter(String mobile, int otpCode, boolean smsSendEnabled) {
        Commuter commuter = getCommuterByMobile(mobile);
        if (commuter == null) {
            System.out.println("Not able to find an entry for commeter mobile:" + mobile);
            return ReturnCodes.USER_NOT_EXIST;
        }
        if (commuter.getStatus() == UserStatusType.VERIFIED_ACTIVATED.getTypeCode()) {
            return ReturnCodes.USER_ALREADY_VERIFIED;
        }
        try {
            Commuter existingCommuterObject = commuter;
            Boolean alreadyActivated = existingCommuterObject.getAlready_activated();
            ReturnCodes returnCode = activateCommuter(mobile, commuter.getCommuter_id(), otpCode, smsSendEnabled);
            if (returnCode.equals(ReturnCodes.USER_VERIFIED)) {
                try {
                    System.out.println("Sending welcome signup message");
                    
                    // Push notification
                    sendWelcomeSignupMessage(commuter, commuter.getGcm_reg_id());
                    
                    // Email
                    // TODO: commenting it for now.
                    //sendWelcomeMessageEmail(commuter);
                    
                    // if user is not already activated or it is false.
                    System.out.println("alreadyActivated:"+alreadyActivated);
                    boolean alreadyRegistrationExist = false;
                    if (alreadyActivated == null || !alreadyActivated) {
                        // as of now sending everytime a user registered with
                        // us. he may be present in system before that.
                        // SMS

                        alreadyRegistrationExist = false;

                        // taking all steps for referral
                        int freeRidesGiven = takeActionForReferral(existingCommuterObject) + CommonConstants.DEFAULT_SIGNUP_FREE_RIDES;
                        
                        // sending welcome message
                        sendWelcomeSignupMessageSMS(existingCommuterObject, freeRidesGiven, alreadyRegistrationExist, smsSendEnabled);

                    } else {
                        alreadyRegistrationExist = true;
                        sendWelcomeSignupMessageSMS(existingCommuterObject, -1, alreadyRegistrationExist, smsSendEnabled);
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                return ReturnCodes.USER_VERIFIED;
            } else {
                System.out.println("Couldn't activate user.");
                return ReturnCodes.USER_VERIFICATION_FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnCodes.USER_VERIFICATION_FAILED;
        }
    }
    
    public int takeActionForReferral(Commuter userWhoGotReferred) {
        String referral_code_used = userWhoGotReferred.getReferral_code_used();

        System.out.println("referral_code_used:" + referral_code_used + " by commuter:" + userWhoGotReferred.getName());

        if (referral_code_used != null && referral_code_used.trim().length() > 0) {
            ReferralCode referralCode = referralCodeService.getReferralCodeByCode(referral_code_used);
            if (referralCode != null) {
                int userWhoReferredId = referralCode.getReferred_by();
                int userWhoGotReferredId = userWhoGotReferred.getCommuter_id();

                Commuter userWhoReferred = this.getCommuterById(userWhoReferredId);

                // Adding free rides
                this.creditFreeRidesToUsers(userWhoReferred, userWhoGotReferred);

                // adding referral history.
                this.addReferralHisotry(userWhoReferredId, userWhoGotReferredId);

                // check for upgrading to his one month free offer.
                this.checkForUpgradingToOneMonthFreeOffer(userWhoReferredId, userWhoGotReferredId);
                
                Integer freeRideReferredTo = getReferralFreeRidesReferredTo();
                return freeRideReferredTo;//CommonConstants.DEFAULT_REFERRAL_FREE_RIDES;
            } else {
                CorporateReferralCode corporateReferralCode = referralCodeService
                        .getCorporateReferralCodeByCode(referral_code_used);
                if (corporateReferralCode != null) {
                    System.out.println("user signing up with corporate referral code:" + referral_code_used);
                } else {
                    System.out.println("referralCode object is null, mostly referral_code_used does not exist:"
                            + referral_code_used);
                }
            }
        } else {
            System.out.println("referral_code_used is empty:" + referral_code_used);
        }
        // returning default signup credits.
        return 0;
    }

    public void addReferralHisotry(int userWhoReferredId, int userWhoGotReferredId) {

        ReferralTransactionInfo rtx = new ReferralTransactionInfo();
        rtx.setReferred_by(userWhoReferredId);
        rtx.setReferred_to(userWhoGotReferredId);
        rtx.setReferred_to_redeemed_first_ride(false);

        this.referralTxService.insert(rtx);
    }
    
    public void checkForUpgradingToOneMonthFreeOffer(int userWhoReferredId, int userWhoGotReferredId) {
        
        List<ReferralTransactionInfo> referralsRedeemedFirstRide = referralTxService
                .getTransactionsByReferredBy(userWhoReferredId, true);
        System.out.println("referrals:"+referralsRedeemedFirstRide);
        
        if (referralsRedeemedFirstRide != null) {
            if (referralsRedeemedFirstRide.size() < CommonConstants.NUMBER_REFERRAL_1MONTH_FREE_RIDES) {
                System.out.println("Not taking any action as number of referrals are less");
                // on 5th referral user should be upgraded to 1 month free ride.
            } else if (referralsRedeemedFirstRide.size() >= CommonConstants.NUMBER_REFERRAL_1MONTH_FREE_RIDES) {
                Account account = accountService.getAccountByCommuterId(userWhoReferredId);

                if (account != null && !account.isOne_month_free_activated()) {
                    System.out.println("One month free is not activated for:" + userWhoGotReferredId
                            + ", checking for pre-conditions");

                    boolean status = txService.cancelFreeRides(userWhoReferredId);
                    if (status) {
                        this.referralTxService.updateAccountForOneMonthFreeRide(userWhoReferredId);
                    } else {
                        System.out
                                .println("User free rides cancelling failed--------------- so not updating his account for one month free ride");
                    }
                } else {
                    System.out.println("One month free is already activated for:" + userWhoGotReferredId);
                }
            }
//            } else if (referrals.size() > CommonConstants.NUMBER_REFERRAL_1MONTH_FREE_RIDES) {
//                this.txService.systemRecharge(userWhoReferredId, CommonConstants.DEFAULT_CREDITS_AFTER_MAX_REFERRAL, 0,
//                        TransactionStatus.SUCCESS, "REFERRAL_OF-" + userWhoGotReferredId);
//            }
        }
    }
    
    /**
     * 
     * @param referredBy
     *            : userWhoReferred
     * @param referredTo
     *            : userWhoGotReferred
     */
    private void creditFreeRidesToUsers(Commuter referredBy, Commuter referredTo) {

//        int credit_points_referred_by = 0;
//        int credit_points_referred_to = 0;
//
//        int free_rides_referred_by = CommonConstants.DEFAULT_REFERRAL_FREE_RIDES;
//        int free_rides_referred_to = CommonConstants.DEFAULT_REFERRAL_FREE_RIDES;
//
//        int userWhoReferredId = referredBy.getCommuter_id();
//        int userWhoGotReferredId = referredTo.getCommuter_id();
//        
//        System.out.println("Adding rides to "+referredBy.getCommuter_id());
        
        
        creditFreeRidesOrCreditsToReferredByUser(referredBy, referredTo);
        
//        // adding a message that indicates that he referred which user.
//        String referral_of_transaction_desc = getReferralOfTxDesc(userWhoGotReferredId);//"REFERRAL_OF-"+referredTo.getCommuter_id();
//        
//        List<ReferralTransactionInfo> allReferrals = referralTxService.getTransactionsByReferredBy(userWhoReferredId);
//
//        boolean reachedMaxFreeRidesReferrals = false;
//        if (allReferrals != null) {
//            // after 5
//            if (allReferrals.size() > CommonConstants.MAX_REFERRAL_FREE_RIDES) {
//                reachedMaxFreeRidesReferrals = true;
//            } else {
//                System.out.println("Referrals redeemed are :" + allReferrals.size() + " less than the no.of "
//                        + CommonConstants.MAX_REFERRAL_FREE_RIDES);
//            }
//        } else {
//            System.out.println("No Referrals found ");
//        }
//        
//        if (!reachedMaxFreeRidesReferrals) {
//            // to the user who referred his friends
//            this.txService.systemRecharge(referredBy.getCommuter_id(), credit_points_referred_by,
//                    free_rides_referred_by, TransactionStatus.SUCCESS, referral_of_transaction_desc);
//        } else {
//            // Adding only credits to the person, not the free rides.
//            this.txService.systemRecharge(userWhoReferredId, CommonConstants.DEFAULT_CREDITS_AFTER_MAX_REFERRAL, 0,
//                    TransactionStatus.SUCCESS, "REFERRAL_OF-" + userWhoGotReferredId);
//        }
        
//        //==================Adding Rides to the Person who got referred=======================
//        System.out.println("Adding rides to "+referredTo.getCommuter_id());
//
//        // adding a message that indicates that he got referred by which user.
//        String referred_by_transaction_desc = getReferredByTxDesc(userWhoReferredId);//"REFERRED_BY-"+referredBy.getCommuter_id();
//
//        // to the user who got referred. The user who is registering now.
//        txService.systemRecharge(referredTo.getCommuter_id(), credit_points_referred_to, free_rides_referred_to,
//                TransactionStatus.SUCCESS, referred_by_transaction_desc);
        
        creditFreeRidesOrCreditsToReferredToUser(referredBy, referredTo);
    }
    
    
    private void creditFreeRidesOrCreditsToReferredByUser(Commuter referredBy, Commuter referredTo) {

        //boolean referral_credits_referred_by_enabled = this.isReferralCreditsToReferredByEnabled();
        int referral_free_rides_referred_by = this.getReferralFreeRidesReferredBy();
        
        int max_referral_free_rides = this.getMaxReferralFreeRides();
        int credits_after_max_referral = this.getDefaultCreditsAfterMaxReferral();

//        int free_rides_referred_by = CommonConstants.DEFAULT_REFERRAL_FREE_RIDES;
        int free_rides_referred_by = referral_free_rides_referred_by;
        
        int credit_points_referred_by = this.getReferralFreeCreditsReferredBy();
        
        int userWhoReferredId = referredBy.getCommuter_id();
        int userWhoGotReferredId = referredTo.getCommuter_id();

        // adding a message that indicates that he referred which user.
        String referral_of_transaction_desc = getReferralOfTxDesc(userWhoGotReferredId);// "REFERRAL_OF-"+referredTo.getCommuter_id();

        List<ReferralTransactionInfo> allReferrals = referralTxService.getTransactionsByReferredBy(userWhoReferredId);

        boolean reachedMaxFreeRidesReferrals = false;
        if (allReferrals != null) {
            // after 5
            if (allReferrals.size() >= max_referral_free_rides) {
                reachedMaxFreeRidesReferrals = true;
            } else {
                System.out.println("Referrals redeemed are :" + allReferrals.size() + " less than the no.of "
                        + max_referral_free_rides);
            }
        } else {
            System.out.println("No Referrals found ");
        }

        if (!reachedMaxFreeRidesReferrals) {
            // to the user who referred his friends
            this.txService.systemRecharge(userWhoReferredId, credit_points_referred_by, free_rides_referred_by,
                    TransactionStatus.SUCCESS, referral_of_transaction_desc);
        } else {
            // Adding only credits to the person, not the free rides.
            this.txService.systemRecharge(userWhoReferredId, credits_after_max_referral, 0,
                    TransactionStatus.SUCCESS, "REFERRAL_OF-" + userWhoGotReferredId);
        }
    }
    
    private Integer getMaxReferralFreeRides() {
        Integer value = settingsService.getDashBoardSettings().getMax_referral_free_rides();
        if (value == null) {
            return CommonConstants.MAX_REFERRAL_FREE_RIDES;
        }
        return value;
    }

    private Integer getDefaultCreditsAfterMaxReferral() {
        Integer value = settingsService.getDashBoardSettings().getCredits_after_max_referral();
        if (value == null) {
            return CommonConstants.DEFAULT_CREDITS_AFTER_MAX_REFERRAL;
        }
        return value;
    }

    private Integer getReferralFreeRidesReferredBy() {
        Integer value = settingsService.getDashBoardSettings().getReferral_free_rides_referred_by();
        if (value == null) {
            return CommonConstants.DEFAULT_REFERRAL_FREE_RIDES;
        }
        return value;
    }

    private Integer getReferralFreeRidesReferredTo() {
        Integer value = settingsService.getDashBoardSettings().getReferral_free_rides_referred_to();
        if (value == null) {
            return CommonConstants.DEFAULT_REFERRAL_FREE_RIDES;
        }
        return value;
    }

    private Integer getReferralFreeCreditsReferredBy() {
        Integer value = settingsService.getDashBoardSettings().getReferral_free_credits_referred_by();
        if (value == null) {
            return CommonConstants.DEFAULT_REFERRAL_FREE_CREDITS;
        }
        return value;
    }

    private Integer getReferralFreeCreditsReferredTo() {
        Integer value = settingsService.getDashBoardSettings().getReferral_free_credits_referred_to();
        if (value == null) {
            return CommonConstants.DEFAULT_REFERRAL_FREE_CREDITS;
        }
        return value;
    }
    
    private Boolean isReferralCreditsToReferredByEnabled() {
        Boolean value = settingsService.getDashBoardSettings().getReferral_free_credits_referred_by_enabled();
        if (value == null) {
            return false;
        }
        return value;
    }

    private Boolean isReferralCreditsToReferredToEnabled() {
        Boolean value = settingsService.getDashBoardSettings().getReferral_free_credits_referred_to_enabled();
        if (value == null) {
            return false;
        }
        return value;
    }
    
    private void creditFreeRidesOrCreditsToReferredToUser(Commuter referredBy, Commuter referredTo){
        
        //boolean referral_credits_referred_to_enabled = this.isReferralCreditsToReferredToEnabled();

        int credit_points_referred_to = this.getReferralFreeCreditsReferredTo();
        int referral_free_rides_referred_to = this.getReferralFreeRidesReferredTo();
        
        //int free_rides_referred_to = CommonConstants.DEFAULT_REFERRAL_FREE_RIDES;
        int free_rides_referred_to = referral_free_rides_referred_to;

        int userWhoReferredId = referredBy.getCommuter_id();
        int userWhoGotReferredId = referredTo.getCommuter_id();
        
        //==================Adding Rides to the Person who got referred=======================
        System.out.println("Adding rides to "+userWhoGotReferredId);

        // adding a message that indicates that he got referred by which user.
        String referred_by_transaction_desc = getReferredByTxDesc(userWhoReferredId);//"REFERRED_BY-"+referredBy.getCommuter_id();
        
//        // if only giving credits are enabled then we will give only credits not
//        // free rides.
//        // in other condition we will free rides, not credits.
//        if (referral_credits_referred_to_enabled) {
//
//        }

        // to the user who got referred. The user who is registering now.
        txService.systemRecharge(userWhoGotReferredId, credit_points_referred_to, free_rides_referred_to,
                TransactionStatus.SUCCESS, referred_by_transaction_desc);
    }
    
    private String getReferredByTxDesc(int userWhoReferredId) {
        return "REFERRED_BY-" + userWhoReferredId;
    }

    private String getReferralOfTxDesc(int userWhoGotReferredId) {
        return "REFERRAL_OF-" + userWhoGotReferredId;
    }

    public void sendWelcomeMessageEmail(Commuter commuter){
        EmailDTO emailObj = new EmailDTO();
        emailObj.setTo(new String[]{commuter.getEmail()});
        emailObj.setSubject(commuter.getName() + ", Welcome to EasyCommute");
        emailObj.setText(commuter.getName() + ", Thank you for signing up for EasyCommute");
        EmailResponse emailResp = emailProvider.sendEmail(emailObj);
        System.out.println("emailResp:" + emailResp);
    }

    // we are taking mobile no. in consideration, as he might be trying to regenerate after changing his mobile no.
    // so if we get mobile no.
    public ReturnCodes regenrateOTP(String mobile, int commuter_id, boolean smsSendEnabled) {
        Commuter c = getCommuterById(commuter_id);
        if (c == null) {
            System.out.println("Not able to find an entry for commeter id:" + commuter_id);

            if (mobile != null) {
                c = getCommuterByMobile(mobile);
                if (c == null) {
                    System.out.println("Not able to find an entry for commeter mobile:" + mobile);
                    return ReturnCodes.USER_NOT_EXIST;
                }
            } else {
                System.out.println("Not able to find an entry for commeter id:" + commuter_id
                        + " and mobile no is null in request parameter");
                return ReturnCodes.USER_NOT_EXIST;
            }
        }
        
        // if user already exist and activated, we shouldnt goof up ourself that we allow him to change his mobile no.
        if (c != null && c.getStatus() == 1) {
            System.out.println("User already exist, again generating an otp, in regenrateOTP");
            // here it shouldnt be the mobile no that we are getting in request.
            // it should the mobile no. that we have.
            generateAndSendOTP(c.getMobile(), c.getCommuter_id(), c.getName(), smsSendEnabled);

            // Since User exist, generating an another OTP
            c.setStatus(UserStatusType.REGISTED_NOT_ACTIVATED.getTypeCode());
            boolean status = commuterDao.updateCommuter(c);
            if (status) {
                System.out.println("Commuter id:" + c.getCommuter_id() + " inactivated as new OTP generated in regenrateOTP");
            } else {
                System.out.println("Failed to update in DB for commuter id:" + c.getCommuter_id()
                        + " inactivated as new OTP generated");
            }
            return ReturnCodes.USER_ALREADY_EXIST;
        }
        
        //================ user shoudnt reach here if he is already activated.=============
        // as that will cause him to allow to change the mobile no.
        
        boolean updated = false;
        // if the mobile no. we are getting in request is not empty and its different then previous no. entered.
        if (mobile != null && !(mobile.equals(c.getMobile()))) {
            c.setMobile(mobile);
            updated = updateCommuter(c);
            if (updated) {
                System.out
                        .println("Updating his new mobile no. in system and generated an OTP on new number: updated status: success");
            } else {
                System.out
                        .println("Updating his new mobile no. in system and generated an OTP on new number: updated status: failed");
            }
        } else {
            mobile = c.getMobile();
        }
        System.out.println("User already exist but not activated so again generating an otp");
        generateAndSendOTP(mobile, commuter_id, c.getName(), smsSendEnabled);
        return ReturnCodes.USER_EXIST_OTP_GENERATED;
    }
    
    public ReturnCodes activateCommuter(String mobile, int commuter_id, int otpCode, boolean smsSendEnabled) {
        // Commuter commuter = commuterDao.activateCommuter(commuter_id,
        // otpCode);
        boolean status = false;
        // if (commuter != null) {
        // status = true;
        // } else {
        // status = false;
        // }
        Object[] arr = commuterDao.getCommuterWithOTPCode(commuter_id);
        System.out.println("arr:" + arr);
        if (arr == null) {
            throw new IllegalArgumentException("It seems commuter_id is not available");
        }
        Commuter commuter = (Commuter) arr[0];
        SMSCode smsCode = (SMSCode) arr[1];
        System.out.println("Commuter:" + commuter);
        System.out.println("smsCode:" + smsCode);

        if (commuter != null && smsCode != null) {
            int code = smsCode.getCode();
            if (code == otpCode) {
                Timestamp sentTime = smsCode.getCreated_at();
                long sentTimeVal = sentTime.getTime();

                Date currentTime = new Date();
                long currentTimeVal = currentTime.getTime();

                long diff = TimeUnit.MILLISECONDS.toMinutes(currentTimeVal)
                        - TimeUnit.MILLISECONDS.toMinutes(sentTimeVal);
                if (diff < 30) {
                    status = true;
                } else {
                    status = false;
                    System.out.println("Code expired for commuter_id:" + commuter_id + "--diff:" + diff);
                    generateAndSendOTP(mobile, commuter_id, commuter.getName(), smsSendEnabled);
                    return ReturnCodes.USER_OTP_EXPIRED_OTP_GENERATED;
                }
            }
        } else {
            System.out.println("Commuter:" + commuter);
            System.out.println("smsCode:" + smsCode);
        }

        if (status) {
            Commuter existingDetails = commuterDao.getCommuter(commuter_id);
            Boolean alreadyActivated = existingDetails.getAlready_activated();
            boolean activated = activateCommuterStatus(existingDetails);
            System.out.println("commuter:" + commuter.getCommuter_id() + "--" + " activated successfully");
            System.out.println("is alreadyActivated:"+alreadyActivated +" ---"+commuter.getCommuter_id() );
            if (activated) {
                // credit free rides
                // If user is already activated (if he tries to remove the app data and try again and agin), then
                // ensuring that system is not giving him credits again and again.
                // Read more the comment on the field here : => {@link: Commuter.already_activated }
                if (alreadyActivated == null || !alreadyActivated) {
                    this.creditFreeRides(commuter_id, TransactionStatus.SUCCESS);
                } else {
                    System.out.println("User was already there in system, so not giving him again credits");
                }
                System.out.println("removing entry for commuter:" + commuter.getCommuter_id());
                boolean deleted = commuterDao.deleteSMSCodeByCommuterId(commuter_id);
                if (deleted) {
                    System.out.println("Removed entry for existing sms codes for commuter:" + commuter_id);
                }
                return ReturnCodes.USER_VERIFIED;
            } else {
                return ReturnCodes.USER_VERIFICATION_FAILED;
            }   
        } else {
            return ReturnCodes.USER_INVALID_OTP;
            //throw new ServiceException("Invalid OTP");
        }
    }

    private void creditFreeRides(int commuter_id, TransactionStatus status) {
        if (CommonConstants.SIGNUP_CREDIT_PROMOTION_SCHEME_ENABLED) {
            try {
                transactionService.systemRecharge(commuter_id, CommonConstants.DEFAULT_SIGNUP_POINTS,
                        CommonConstants.DEFAULT_SIGNUP_FREE_RIDES, TransactionStatus.SUCCESS, "SIGNUP CREDIT");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // TODO: improve it as we need to update only a single field.
    // Can we write direct query for it.
    public boolean activateCommuterStatus(Commuter commuter) {
        commuter.setStatus(UserStatusType.VERIFIED_ACTIVATED.getTypeCode());
        commuter.setVerified_at(new Timestamp(System.currentTimeMillis()));
        commuter.setAlready_activated(true);
        boolean success = commuterDao.updateEntity(commuter);
        return success;
    }

    public boolean isCommuterExist(String mobile) {
        Commuter c1 = getCommuterByMobile(mobile);
        if (c1 != null) {
            return true;
        }
        return false;
    }

    public BigInteger getCommutersCount() {
        return commuterDao.getCommutersCount();
    }
	
	public int[] getActiveAndVerifiedCommuterIds() {
        List<Commuter> commuters = this.findAllActiveAndVerified();
        int[] newIds = new int[commuters.size()];
        for (int i = 0; i < commuters.size(); i++) {
            newIds[i] = commuters.get(i).getCommuter_id();
        }
        return newIds;
    }
    
    public boolean validateUserByPassword(String mobile, String password) {
        Commuter c = getCommuterByMobile(mobile);
        if (c == null) {
            return false;
        }

        String password_db = c.getPassword();
        
        // if there is no password in db.
        if (password_db == null) {
            return false;
        } else {
            // if there is password already set then compare.
            String originalPassword = PasswordEncrypter.decryptText(password_db);

            if (password != null && password.equals(originalPassword)) {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean regenrateOTP(String mobile) {
        Commuter c = getCommuterByMobile(mobile);
        if (c == null) {
            return false;
        }
        boolean smsSendEnabled = true;
        generateAndSendOTP(c.getMobile(), c.getCommuter_id(), c.getName(), smsSendEnabled);
        return true;
    }
    
    public ReturnCodes validateUserByOTP(String mobile, Integer otpCode) {
        Commuter c = getCommuterByMobile(mobile);
        if (c == null) {
            return null;
        }
        int commuter_id = c.getCommuter_id();
        boolean smsSendEnabled = true;
     // Commuter commuter = commuterDao.activateCommuter(commuter_id,
        // otpCode);
        boolean status = false;
        // if (commuter != null) {
        // status = true;
        // } else {
        // status = false;
        // }
        Object[] arr = commuterDao.getCommuterWithOTPCode(commuter_id);
        System.out.println("arr:" + arr);
        if (arr == null) {
            throw new IllegalArgumentException("It seems commuter_id is not available");
        }
        Commuter commuter = (Commuter) arr[0];
        SMSCode smsCode = (SMSCode) arr[1];
        System.out.println("Commuter:" + commuter);
        System.out.println("smsCode:" + smsCode);

        if (commuter != null && smsCode != null) {
            int code = smsCode.getCode();
            if (code == otpCode) {
                Timestamp sentTime = smsCode.getCreated_at();
                long sentTimeVal = sentTime.getTime();

                Date currentTime = new Date();
                long currentTimeVal = currentTime.getTime();

                long diff = TimeUnit.MILLISECONDS.toMinutes(currentTimeVal)
                        - TimeUnit.MILLISECONDS.toMinutes(sentTimeVal);
                if (diff < 30) {
                    status = true;
                } else {
                    status = false;
                    System.out.println("Code expired for commuter_id:" + commuter_id + "--diff:" + diff);
                    generateAndSendOTP(mobile, commuter_id, commuter.getName(), smsSendEnabled);
                    return ReturnCodes.USER_OTP_EXPIRED_OTP_GENERATED;
                }
            }
        } else {
            System.out.println("Commuter:" + commuter);
            System.out.println("smsCode:" + smsCode);
        }

        if (status) {
            System.out.println("removing entry for commuter:" + commuter.getCommuter_id());
            boolean deleted = commuterDao.deleteSMSCodeByCommuterId(commuter_id);
            if (deleted) {
                System.out.println("Removed entry for existing sms codes for commuter:" + commuter_id);
            }
            return ReturnCodes.USER_VERIFIED;
        } else {
            return ReturnCodes.USER_VERIFICATION_FAILED;
        }
    }

}
