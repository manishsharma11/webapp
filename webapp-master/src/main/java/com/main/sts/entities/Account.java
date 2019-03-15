package com.main.sts.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account_summary")
public class Account implements Comparable<Account>, Serializable {

    @Id
    private int commuter_id;
    private int credits_available;
    private Integer free_rides_available =0;
    private boolean one_month_free_activated = false;
    private Date created_at;
    private Date updated_at;
    private Date one_month_free_activated_at;

    public Integer getCommuter_id() {
        return commuter_id;
    }

    public void setCommuter_id(Integer commuter_id) {
        this.commuter_id = commuter_id;
    }

    public int getCredits_available() {
        return credits_available;
    }

    public void setCredits_available(int credits_available) {
        this.credits_available = credits_available;
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

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public void setCredits_available(Integer credits_available) {
        this.credits_available = credits_available;
    }
    
    public int getFree_rides_available() {
        if (free_rides_available == null) {
            free_rides_available = 0;
        }
        return free_rides_available;
    }

//    public void setFree_rides_available(int free_rides_available) {
//        this.free_rides_available = free_rides_available;
//    }
    
    public boolean isOne_month_free_activated() {
        return one_month_free_activated;
    }

    public void setOne_month_free_activated(boolean one_month_free_activated) {
        this.one_month_free_activated = one_month_free_activated;
    }

    public Date getOne_month_free_activated_at() {
        return one_month_free_activated_at;
    }

    public void setOne_month_free_activated_at(Date one_month_free_activated_at) {
        this.one_month_free_activated_at = one_month_free_activated_at;
    }

    public void setCommuter_id(int commuter_id) {
        this.commuter_id = commuter_id;
    }

    public void setFree_rides_available(Integer free_rides_available) {
        this.free_rides_available = free_rides_available;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public int compareTo(Account o) {
        return ((Integer) commuter_id).compareTo(o.getCommuter_id());
    }

    @Override
    public String toString() {
        return "Account [commuter_id=" + commuter_id + ", credits_available=" + credits_available
                + ", free_rides_available=" + free_rides_available + ", created_at=" + created_at + ", updated_at="
                + updated_at + "]";
    }

 

}
