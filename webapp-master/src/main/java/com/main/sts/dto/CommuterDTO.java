package com.main.sts.dto;

import java.io.Serializable;

public class CommuterDTO implements Serializable {

    // Object as commuter_id can be null for some requests.
    //for backward compatibility (Android device) id is mapped to commuter_id
    Integer id;
    Integer commuter_id;
    String name;
    String email;
    String mobile;
    Integer otp;
    // M:Male, F: Female
    String gender;
    String gcm_reg_id;
    boolean sms_send_enabled = false;
    
    String referral_code;
    String device_id;
    
    // only for web based
    String password;

    public Integer getId() {
        if (id == null) {
            return commuter_id;
        }
        return id;
    }

    public void setId(Integer id) {
        this.id = commuter_id;
        this.commuter_id = id;
    }

    public Integer getCommuter_id() {
        if (id == null) {
            return commuter_id;
        }
        return id;
    }

    public void setCommuter_id(Integer commuter_id) {
        this.id = commuter_id;
        this.commuter_id = commuter_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }

    public String getGcm_reg_id() {
        return gcm_reg_id;
    }

    public void setGcm_reg_id(String gcm_reg_id) {
        this.gcm_reg_id = gcm_reg_id;
    }

    // Added for backward compatibility
    public boolean isSmsSendEnabled() {
        return sms_send_enabled;
    }

    // Added for backward compatibility
    public void setSmsSendEnabled(boolean smsSendEnabled) {
        this.sms_send_enabled = smsSendEnabled;
    }
    
    public String getGender() {
        return gender;
    }

    public boolean isSms_send_enabled() {
        return sms_send_enabled;
    }

    public void setSms_send_enabled(boolean sms_send_enabled) {
        this.sms_send_enabled = sms_send_enabled;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getReferral_code() {
        return referral_code;
    }

    public void setReferral_code(String referral_code) {
        this.referral_code = referral_code;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "CommuterDTO [commuter_id=" + id + ", name=" + name + ", email=" + email + ", mobile=" + mobile
                + ", otp=" + otp + ", gender=" + gender + ", gcm_reg_id=" + gcm_reg_id + ", sms_send_enabled="
                + sms_send_enabled + "]";
    }

}
