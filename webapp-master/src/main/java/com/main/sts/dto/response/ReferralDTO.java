package com.main.sts.dto.response;

import java.io.Serializable;

public class ReferralDTO implements Serializable {

    public int id;
    //public int referred_by;
    public String code;
    //public int referral_code_status;
    //public Timestamp created_at;
    //public Timestamp updated_at;

    // this might be coming from differnet service.
    public String referral_message;
    public String referral_scheme_desc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReferral_message() {
        return referral_message;
    }

    public void setReferral_message(String referral_message) {
        this.referral_message = referral_message;
    }

    public String getReferral_scheme_desc() {
        return referral_scheme_desc;
    }

    public void setReferral_scheme_desc(String referral_scheme_desc) {
        this.referral_scheme_desc = referral_scheme_desc;
    }

   

}
