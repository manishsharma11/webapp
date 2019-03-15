package com.main.sts.entities;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.main.sts.common.CommonConstants;

@Entity
@Table(name = "referral_codes")
public class ReferralCode {

    @Id
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
    // "faq_seq_gen")
    // @SequenceGenerator(name = "faq_seq_gen", sequenceName = "faq_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "referral_codes_seq_gen")
    @SequenceGenerator(name = "referral_codes_seq_gen", sequenceName = "referral_codes_id_seq")
    private int id;
    
    /**
     * It will be commuter who is referring to other guys our application.
     * so the code is of this user. It is the commuter_id from Commuter object.
     */
    private int referred_by;
    
    /**
     *  Referral code of referred_by user.
     */
    private String code;
    /**
     * {@link CommonConstants.BookingStatus}
     */
    private int referral_code_status;

    /**
     * if code is enabled, in some extreme cases, we can disable the referal
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

    public int getReferred_by() {
        return referred_by;
    }

    public void setReferred_by(int referred_by) {
        this.referred_by = referred_by;
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

    public Boolean getEnabled() {
        return enabled;
    }
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    @Override
    public String toString() {
        return "ReferralCode [id=" + id + ", referred_by=" + referred_by + ", code=" + code + ", referral_code_status="
                + referral_code_status + ", created_at=" + created_at + ", updated_at=" + updated_at + "]";
    }

}
