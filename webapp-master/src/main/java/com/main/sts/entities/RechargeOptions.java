package com.main.sts.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "recharge_options")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "recharge_options")
public class RechargeOptions implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "recharge_options_seq_gen")
    @SequenceGenerator(name = "recharge_options_seq_gen", sequenceName = "recharge_options_id_seq")
    private int id;

    private int recharge_amount;
    private int num_credits_offered;
    private boolean enabled;

    private Date created_at;
    private Date updated_at;

    // user specific recharges.
    private Integer commuter_group_id;

    public int getRecharge_amount() {
        return recharge_amount;
    }
    public void setRecharge_amount(int recharge_amount) {
        this.recharge_amount = recharge_amount;
    }
    public int getNum_credits_offered() {
        return num_credits_offered;
    }
    public void setNum_credits_offered(int num_credits_offered) {
        this.num_credits_offered = num_credits_offered;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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
    public Integer getCommuter_group_id() {
        return commuter_group_id;
    }
    public void setCommuter_group_id(Integer commuter_group_id) {
        this.commuter_group_id = commuter_group_id;
    }

}
