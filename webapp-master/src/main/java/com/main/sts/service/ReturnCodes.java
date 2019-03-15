package com.main.sts.service;

public enum ReturnCodes {

    // General
    SUCCESS,
    MISSING_PARAMETERS,
    BAD_REQUEST,
    UNKNOWN_ERROR,

    // User
    USER_ALREADY_EXIST,
    USER_CREATED_SUCCESSFULLY,
    USER_ALREADY_VERIFIED,
    USER_VERIFIED,
    USER_EXIST_OTP_GENERATED,
    USER_CREATED_OTP_GENERATED,
    USER_CREATION_FAILED,
    USER_NOT_EXIST,
    USER_VERIFICATION_FAILED,

    USER_OTP_EXPIRED_OTP_GENERATED,
    USER_ACTIVATED,
    USER_INVALID_OTP,

    // Booking
    BOOKING_SEATS_NOT_AVAILABLE,
    BOOKING_NOT_ENOUGH_BALANCE,
    BOOKING_FAILED,
    BOOKING_SUCCESSFUL,
    ALREADY_HAVE_MAX_ACTIVE_BOOKINGS,


    // GCM
    GCM_REGISTRATION_SUCCESSFUL,
    GCM_REGISTRATION_FAILED,

    // Tracking
    USER_UNREGISTERED_SUCCESSFULLY,
    USER_UNREGISTRATION_FAILED,

    VEHICLE_POS_INSERT_SUCCESS,
    VEHICLE_POS_INSERT_FAILED,

    USER_POS_INSERT_SUCCESS,
    USER_POS_INSERT_FAILED,

    // Transaction
    TRANSACION_COMPLETED_SUCCESSFULLY,
    TRANSACION_MANDATORY_PARAMETERS_MISSING,
    FAILED_TRANSACION_UPDATION_FAILED,
    TRANSACION_ACCOUNT_UPDATION_FAILED,
    FAILED_TRANSACION_UPDATION_SUCCESS,

    // Transaction
    SUCCESS_TRANSACION_UPDATION_FAILED,
    TRANSACTION_ADMIN_RECHARGE_UPDATION_FAILED,
    TRANSACTION_SYSTEM_RECHARGE_UPDATION_FAILED,

    // Booking
    BOOKING_CANCELLATION_SUCCESSFUL,
    BOOKING_CANCELLATION_FAILED,
    BOOKING_CANCELLATION_NOT_ALLOWED,    
    BOOKING_ALREADY_CANCELLED_FULL_REFUNDED,
    BOOKING_ALREADY_CANCELLED_NO_REFUND_GIVEN,
    BOOKING_ALREADY_CANCELLED_PARTIAL_REFUNDED,
    BOOKING_ID_NOT_EXIST,
    BOOKING_ALREADY_EXPIRED,

    // Refund
    REFUND_FAILED,
    REFUND_SUCCESS,
    USER_DETAIL_UPDATION_SUCCESS,
    USER_DETAIL_UPDATION_FAILED,
    PAYMENT_TRANSACTION_UPDATION_FAILED,
    PAYMENT_TRANSACTION_UPDATION_SUCCESS,
    EC_DEVICE_ID_GENERATED,
    EC_DEVICE_ID_GENERATION_FAILED,
    
  //Save Routes
    SAVE_ROUTE_ALREADY_EXISTS,
    SAVE_ROUTE_SUCCESS,
    SAVE_ROUTE_FAILED,

    ;

    public ReturnCodes getReturnCode(String name) {
        for (ReturnCodes rc : values()) {
            if (name.equals(rc.name())) {
                return rc;
            }
        }
        return null;
    }

}
