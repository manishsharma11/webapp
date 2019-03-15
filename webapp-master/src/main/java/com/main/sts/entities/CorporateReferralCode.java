package com.main.sts.entities;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.main.sts.common.CommonConstants;

@Entity
@Table(name = "corporate_referral_codes")
public class CorporateReferralCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "corporate_referral_codes_seq_gen")
    @SequenceGenerator(name = "corporate_referral_codes_seq_gen", sequenceName = "corporate_referral_codes_id_seq")
    private int id;
    
    /**
     * It will be commuter who is referring to other guys our application.
     * so the code is of this user. It is the commuter_id from Commuter object.
     */
    private String company;
    
    /**
     *  Referral code of referred_by user.
     */
    private String code;
    /**
     * {@link CommonConstants.BookingStatus}
     */
    private int referral_code_status;

    private Date startTime;
    private Date endTime;// can be null
    
    /**
     * {@link CommonConstants.ReferralOfferType}
     */
    private int referral_offer_type;
    
    /**
     * If referral_offer_type is CommonConstants.ReferralOfferType.DAYS
     */
    private Integer numberOfDays; // can be null
    /**
     * if code is enabled, in some extreme cases, we can disable the referral
     * code.
     */
    private Boolean enabled = true;

    private Timestamp created_at;
    private Timestamp updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getReferral_code_status() {
        return referral_code_status;
    }

    public void setReferral_code_status(int referral_code_status) {
        this.referral_code_status = referral_code_status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getReferral_offer_type() {
        return referral_offer_type;
    }

    public void setReferral_offer_type(int referral_offer_type) {
        this.referral_offer_type = referral_offer_type;
    }

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    

}
