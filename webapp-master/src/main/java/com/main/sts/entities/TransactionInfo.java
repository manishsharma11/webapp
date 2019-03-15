package com.main.sts.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.main.sts.common.CommonConstants.TransactionAction;

// Integer means that those values can be null

@Entity
@Table(name = "transaction")
public class TransactionInfo implements Comparable<TransactionInfo>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "transaction_id_seq_gen")
    @SequenceGenerator(name = "transaction_id_seq_gen", sequenceName = "transaction_id_seq")
    private int id;
    private int commuter_id;
    private Integer points_added = 0;
    private Integer points_deducted = 0;
    private int points_balance;

    // credit, debit
    private int transaction_type;

    // Success: 1, Failed:2
    private int transaction_status;

    // system, admin, user (commuter himself)
    private int transaction_by;

    private String admin_id;

    @Column(name = "transaction_desc")
    private String transaction_desc;
    
    //RECHARGE, BOOKING, REFUND
    private String transaction_action;
    
    // DONT CHNAGE THE VARIABLE NAME, AS USED IN APP, IT IS TrANSACTION TIME
    private Date created_at;
    
    private Integer booking_id = -1;
    
    //the transaction id for the payment made on payumoney or any other system.
    private Integer payment_tx_id =-1;

    private Integer free_rides_added = 0;
    private Integer free_rides_deducted = 0;
    private Integer free_rides_balance = 0;
    
    @Transient
    private String transaction_action_desc; 
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommuter_id() {
        return commuter_id;
    }

    public void setCommuter_id(int commuter_id) {
        this.commuter_id = commuter_id;
    }

    public Integer getPoints_added() {
        return points_added;
    }

    public void setPoints_added(Integer points_added) {
        this.points_added = points_added;
    }

    public Integer getPoints_deducted() {
        return points_deducted;
    }

    public void setPoints_deducted(Integer points_deducted) {
        this.points_deducted = points_deducted;
    }

    public int getPoints_balance() {
        return points_balance;
    }

    public void setPoints_balance(int points_balance) {
        this.points_balance = points_balance;
    }

    public int getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(int transaction_type) {
        this.transaction_type = transaction_type;
    }

    public int getTransaction_status() {
        return transaction_status;
    }

    public void setTransaction_status(int transaction_status) {
        this.transaction_status = transaction_status;
    }

    public int getTransaction_by() {
        return transaction_by;
    }

    public void setTransaction_by(int transaction_by) {
        this.transaction_by = transaction_by;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getTransaction_desc() {
        return transaction_desc;
    }

    public void setTransaction_desc(String transaction_desc) {
        this.transaction_desc = transaction_desc;
    }

    public Integer getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(Integer booking_id) {
        this.booking_id = booking_id;
    }
    
    public String getTransaction_action() {
        return transaction_action;
    }

    public void setTransaction_action(String transaction_action) {
        this.transaction_action = transaction_action;
    }
    
    public Integer getPayment_tx_id() {
        return payment_tx_id;
    }

    public void setPayment_tx_id(Integer payment_tx_id) {
        this.payment_tx_id = payment_tx_id;
    }
    
    public Integer getFree_rides_added() {
        if (free_rides_added == null) {
            free_rides_added = 0;
        }
        return free_rides_added;
    }

    public void setFree_rides_added(Integer free_rides_added) {
        this.free_rides_added = free_rides_added;
    }

    public Integer getFree_rides_deducted() {
        if (free_rides_deducted == null) {
            free_rides_deducted = 0;
        }
        return free_rides_deducted;
    }

    public void setFree_rides_deducted(Integer free_rides_deducted) {
        this.free_rides_deducted = free_rides_deducted;
    }

    public Integer getFree_rides_balance() {
        if (free_rides_balance == null) {
            free_rides_balance = 0;
        }
        return free_rides_balance;
    }

    public void setFree_rides_balance(Integer free_rides_balance) {
        this.free_rides_balance = free_rides_balance;
    }
    
    public String getTransaction_action_desc() {
        String desc = getTransaction_action();
        if (desc.equals("SYSTEM_RECHARGE")) {
            desc = "SYSTEM RECHARGE";
        }
        return desc;
    }

    public void setTransaction_action_desc(String transaction_action_desc) {
        this.transaction_action_desc = transaction_action_desc;
    }

    @Override
    public int compareTo(TransactionInfo o) {
        return ((Integer) id).compareTo(o.getId());
    }

    @Override
    public String toString() {
        return "TransactionInfo [id=" + id + ", commuter_id=" + commuter_id + ", points_added=" + points_added
                + ", points_deducted=" + points_deducted + ", points_balance=" + points_balance + ", transaction_type="
                + transaction_type + ", transaction_status=" + transaction_status + ", transaction_by="
                + transaction_by + ", admin_id=" + admin_id + ", transaction_desc=" + transaction_desc
                + ", transaction_action=" + transaction_action + ", created_at=" + created_at + ", booking_id="
                + booking_id + ", payment_tx_id=" + payment_tx_id + ", free_rides_added=" + free_rides_added
                + ", free_rides_deducted=" + free_rides_deducted + ", free_rides_balance=" + free_rides_balance + "]";
    }
    
}
