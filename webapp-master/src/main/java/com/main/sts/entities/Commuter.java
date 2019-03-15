package com.main.sts.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.main.sts.util.PasswordEncrypter;

@Entity
@Table(name = "commuters")
public class Commuter implements Comparable<Commuter>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "commuters_id_seq_gen")
    @SequenceGenerator(name = "commuters_id_seq_gen", sequenceName = "commuters_id_seq")
    @Column(name = "id")
    private int commuter_id;
    private String name;
    private String email;
    private String mobile;

    // M:Male, F: Female
    private String gender;
    private String apikey;

    @Column(name = "gcm_regid")
    private String gcm_reg_id;

    // Not verified:0, Verified: 1
    private int status;
    // is user is active, Active:0, Blocked:1, Suspended:2
    public int active;
    private Timestamp created_at;
    private Timestamp verified_at;
    private Timestamp updated_at;
    
    // this field is to protect those cases, where user is already exist in system but he reinstall the app, clears the data
    // and retry to register, so system will identify that user exist and system will generate an OTP and 
    //once verified, return the same commuter_id to him. While verification we assign default credits to to user.
    // this check is to prevent the case where user would be getting same set of credits again and again , every time he do this.
    private Boolean already_activated;
    
    private Integer commuter_group_id = null;
    
    private Integer channel_type;
    
    /**
     * Code by which a user is referred by someone. Usually it wil be while registration.
     * this code is the code of the user has referred this user to system. for e.g. 
     * Ram referres Shayam to our system, so this code is of Ram's referral code.
     * it can be null;
     */
    private String referral_code_used;
    
    /**
     * Device Id of the commuter.
     */
    private String device_id;
    
    @Transient
    private Integer credits_available;

    @Transient
    private Integer free_rides_available = 0;
    
    private String password;

    public int getCommuter_id() {
        return commuter_id;
    }

    public void setCommuter_id(int commuter_id) {
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

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getGcm_reg_id() {
        return gcm_reg_id;
    }

    public void setGcm_reg_id(String gcm_reg_id) {
        this.gcm_reg_id = gcm_reg_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Timestamp getVerified_at() {
        return verified_at;
    }

    public void setVerified_at(Timestamp verified_at) {
        this.verified_at = verified_at;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
    
    public Boolean getAlready_activated() {
        return already_activated;
    }

    public void setAlready_activated(Boolean already_activated) {
        this.already_activated = already_activated;
    }
    
    public String getReferral_code_used() {
        return referral_code_used;
    }

    public void setReferral_code_used(String referral_code_used) {
        this.referral_code_used = referral_code_used;
    }
    
    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public Integer getCredits_available() {
        return credits_available;
    }

    public void setCredits_available(Integer credits_available) {
        this.credits_available = credits_available;
    }

    public Integer getFree_rides_available() {
        return free_rides_available;
    }

    public void setFree_rides_available(Integer free_rides_available) {
        this.free_rides_available = free_rides_available;
    }

    public Integer getCommuter_group_id() {
        return commuter_group_id;
    }

    public void setCommuter_group_id(Integer commuter_group_id) {
        this.commuter_group_id = commuter_group_id;
    }
	    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Integer getChannel_type() {
        return channel_type;
    }

    public void setChannel_type(Integer channel_type) {
        this.channel_type = channel_type;
    }

    @Override
    public String toString() {
        return "Commuter [commuter_id=" + commuter_id + ", name=" + name + ", email=" + email + ", mobile=" + mobile
                + ", password=" + password + ", gender=" + gender + ", apikey=" + apikey + ", gcm_reg_id=" + gcm_reg_id
                + ", status=" + status + ", active=" + active + ", created_at=" + created_at + ", verified_at="
                + verified_at + ", updated_at=" + updated_at + ", already_activated=" + already_activated
                + ", referral_code_used=" + referral_code_used + ", device_id=" + device_id + "]";
    }

    @Override
    public int compareTo(Commuter o) {
        return ((Integer) commuter_id).compareTo(o.getCommuter_id());
    }

}
