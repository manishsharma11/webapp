package com.main.sts.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "referral_transaction")
public class ReferralTransactionInfo implements Comparable<ReferralTransactionInfo>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "referral_transaction_id_seq_gen")
    @SequenceGenerator(name = "referral_transaction_id_seq_gen", sequenceName = "referral_transaction_id_seq")
    private int id;
    private int referred_by;
    private int referred_to;
    private boolean referred_to_redeemed_first_ride=false;
    
    private Date created_at;
    private Date updated_at;

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

    public int getReferred_to() {
        return referred_to;
    }

    public void setReferred_to(int referred_to) {
        this.referred_to = referred_to;
    }

    public boolean isReferred_to_redeemed_first_ride() {
        return referred_to_redeemed_first_ride;
    }

    public void setReferred_to_redeemed_first_ride(boolean referred_to_redeemed_first_ride) {
        this.referred_to_redeemed_first_ride = referred_to_redeemed_first_ride;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public int compareTo(ReferralTransactionInfo o) {
        return ((Integer) id).compareTo(o.getId());
    }

}
