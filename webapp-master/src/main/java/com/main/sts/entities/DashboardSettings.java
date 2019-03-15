package com.main.sts.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.main.sts.common.CommonConstants;

@Entity
@Table(name = "settings")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "settings")
public class DashboardSettings {

    @Id
    private Integer id;
    private String smtp_server_host;
    private String school_name;
    private String smtp_port;
    private String from_email;
    private String smtp_password;
    private Double school_latitude;
    private Double school_longitude;
    private String phone_number_alerts;

    private String about_us;
    private String referral_scheme_desc;

    private String whatsapp_care_number;
    private String customer_care_email;
    private String customer_care_number;
    
    private String current_app_version;
    private String version_check_update_message;
    
    @Column(name="trip_weeekend_exclusion_check")
    private boolean trip_weeekend_exclusion_check_enabled;
    
    private String offer_announcement_message;;
    private boolean offer_announcement_enabled;
    
    // numbers are comma seperated.
    private String sos_alert_numbers;
    // eamils are comma seperated.
    private String sos_alert_emails;
    
    // cancellation allowed before x mins.
    private Integer cancellation_allowed_before_mins = CommonConstants.DEFAULT_CANCELLATION_ALLOWED_BEFORE_X_MINS;
    
    private Integer max_referral_free_rides = CommonConstants.MAX_REFERRAL_FREE_RIDES;
    private Integer credits_after_max_referral = CommonConstants.DEFAULT_CREDITS_AFTER_MAX_REFERRAL;
    
    private Integer referral_free_rides_referred_by = CommonConstants.DEFAULT_REFERRAL_FREE_RIDES;
    private Integer referral_free_rides_referred_to = CommonConstants.DEFAULT_REFERRAL_FREE_RIDES;

    private Integer referral_free_credits_referred_by = CommonConstants.DEFAULT_REFERRAL_FREE_CREDITS;
    private Integer referral_free_credits_referred_to = CommonConstants.DEFAULT_REFERRAL_FREE_CREDITS;
    
    private Boolean referral_free_credits_referred_by_enabled = false;
    private Boolean referral_free_credits_referred_to_enabled = false;
    
    private String referral_message = null;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getSmtp_server_host() {
        return smtp_server_host;
    }
    public void setSmtp_server_host(String smtp_server_host) {
        this.smtp_server_host = smtp_server_host;
    }
    public String getSmtp_port() {
        return smtp_port;
    }
    public void setSmtp_port(String smtp_port) {
        this.smtp_port = smtp_port;
    }
    public String getFrom_email() {
        return from_email;
    }
    public void setFrom_email(String from_email) {
        this.from_email = from_email;
    }
    public String getSmtp_password() {
        return smtp_password;
    }
    public void setSmtp_password(String smtp_password) {
        this.smtp_password = smtp_password;
    }
    public Double getSchool_latitude() {
        return school_latitude;
    }
    public void setSchool_latitude(Double school_latitude) {
        this.school_latitude = school_latitude;
    }
    public Double getSchool_longitude() {
        return school_longitude;
    }
    public void setSchool_longitude(Double school_longitude) {
        this.school_longitude = school_longitude;
    }
    public String getPhone_number_alerts() {
        return phone_number_alerts;
    }
    public void setPhone_number_alerts(String phone_number_alerts) {
        this.phone_number_alerts = phone_number_alerts;
    }

    public String getSchool_name() {
        return school_name;
    }
    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public String getCustomer_care_number() {
        return customer_care_number;
    }
    public void setCustomer_care_number(String customer_care_number) {
        this.customer_care_number = customer_care_number;
    }

    public String getAbout_us() {
        return about_us;
    }
    public void setAbout_us(String about_us) {
        this.about_us = about_us;
    }
    public String getReferral_scheme_desc() {
        return referral_scheme_desc;
    }
    public void setReferral_scheme_desc(String referral_scheme_desc) {
        this.referral_scheme_desc = referral_scheme_desc;
    }

    public String getCustomer_care_email() {
        return customer_care_email;
    }
    public void setCustomer_care_email(String customer_care_email) {
        this.customer_care_email = customer_care_email;
    }
    public String getWhatsapp_care_number() {
        return whatsapp_care_number;
    }
    public void setWhatsapp_care_number(String whatsapp_care_number) {
        this.whatsapp_care_number = whatsapp_care_number;
    }
    
    public String getCurrent_app_version() {
        return current_app_version;
    }
    public void setCurrent_app_version(String current_app_version) {
        this.current_app_version = current_app_version;
    }
    public String getVersion_check_update_message() {
        return version_check_update_message;
    }
    public void setVersion_check_update_message(String version_check_update_message) {
        this.version_check_update_message = version_check_update_message;
    }
    
    public boolean isTrip_weeekend_exclusion_check_enabled() {
        return trip_weeekend_exclusion_check_enabled;
    }
    public void setTrip_weeekend_exclusion_check_enabled(boolean trip_weeekend_exclusion_check_enabled) {
        this.trip_weeekend_exclusion_check_enabled = trip_weeekend_exclusion_check_enabled;
    }
    
    public String getOffer_announcement_message() {
        return offer_announcement_message;
    }
    public void setOffer_announcement_message(String offer_announcement_message) {
        this.offer_announcement_message = offer_announcement_message;
    }
    public boolean isOffer_announcement_enabled() {
        return offer_announcement_enabled;
    }
    public void setOffer_announcement_enabled(boolean offer_announcement_enabled) {
        this.offer_announcement_enabled = offer_announcement_enabled;
    }
    
    public String getSos_alert_numbers() {
        return sos_alert_numbers;
    }
    public void setSos_alert_numbers(String sos_alert_numbers) {
        this.sos_alert_numbers = sos_alert_numbers;
    }
    
    public String[] getSos_alert_numbers_list() {
        return sos_alert_numbers.split(",");
    }
    
    public void setSos_alert_numbers_list(String[] arr) {
        this.setSos_alert_numbers(join(arr));
    }
    
    public String getSos_alert_emails() {
        return sos_alert_emails;
    }
    
    public void setSos_alert_emails(String sos_alert_emails) {
        this.sos_alert_emails = sos_alert_emails;
    }

    public String[] getSos_alert_emails_list() {
        return sos_alert_emails.split(",");
    }

    public void setSos_alert_emails_list(String[] arr) {
        this.setSos_alert_emails(join(arr));
    }
    
    private String join(String[] arr) {
        StringBuilder buf = new StringBuilder();
        int size = arr.length;
        int cnt = 0;
        for (String n : arr) {
            buf.append(n);
            if (cnt < size) {
                buf.append(",");
            }
            cnt++;
        }
        return buf.toString();
    }
    
    public Integer getCancellation_allowed_before_mins() {
        return cancellation_allowed_before_mins;
    }
    public void setCancellation_allowed_before_mins(Integer cancellation_allowed_before_mins) {
        this.cancellation_allowed_before_mins = cancellation_allowed_before_mins;
    }
    
    public Integer getMax_referral_free_rides() {
        return max_referral_free_rides;
    }
    public void setMax_referral_free_rides(Integer max_referral_free_rides) {
        this.max_referral_free_rides = max_referral_free_rides;
    }
    public Integer getCredits_after_max_referral() {
        return credits_after_max_referral;
    }
    public void setCredits_after_max_referral(Integer credits_after_max_referral) {
        this.credits_after_max_referral = credits_after_max_referral;
    }
    public Integer getReferral_free_rides_referred_by() {
        return referral_free_rides_referred_by;
    }
    public void setReferral_free_rides_referred_by(Integer referral_free_rides_referred_by) {
        this.referral_free_rides_referred_by = referral_free_rides_referred_by;
    }
    public Integer getReferral_free_rides_referred_to() {
        return referral_free_rides_referred_to;
    }
    public void setReferral_free_rides_referred_to(Integer referral_free_rides_referred_to) {
        this.referral_free_rides_referred_to = referral_free_rides_referred_to;
    }
    public Integer getReferral_free_credits_referred_by() {
        return referral_free_credits_referred_by;
    }
    public void setReferral_free_credits_referred_by(Integer referral_free_credits_referred_by) {
        this.referral_free_credits_referred_by = referral_free_credits_referred_by;
    }
    public Integer getReferral_free_credits_referred_to() {
        return referral_free_credits_referred_to;
    }
    public void setReferral_free_credits_referred_to(Integer referral_free_credits_referred_to) {
        this.referral_free_credits_referred_to = referral_free_credits_referred_to;
    }
    public Boolean getReferral_free_credits_referred_by_enabled() {
        return referral_free_credits_referred_by_enabled;
    }
    public void setReferral_free_credits_referred_by_enabled(Boolean referral_free_credits_referred_by_enabled) {
        this.referral_free_credits_referred_by_enabled = referral_free_credits_referred_by_enabled;
    }
    public Boolean getReferral_free_credits_referred_to_enabled() {
        return referral_free_credits_referred_to_enabled;
    }
    public void setReferral_free_credits_referred_to_enabled(Boolean referral_free_credits_referred_to_enabled) {
        this.referral_free_credits_referred_to_enabled = referral_free_credits_referred_to_enabled;
    }
    
    public String getReferral_message() {
        return referral_message;
    }
    public void setReferral_message(String referral_message) {
        this.referral_message = referral_message;
    }
    
    @Override
    public String toString() {
        return "DashboardSettings [id=" + id + ", smtp_server_host=" + smtp_server_host + ", school_name="
                + school_name + ", smtp_port=" + smtp_port + ", from_email=" + from_email + ", smtp_password="
                + smtp_password + ", school_latitude=" + school_latitude + ", school_longitude=" + school_longitude
                + ", phone_number_alerts=" + phone_number_alerts + "]";
    }

}
