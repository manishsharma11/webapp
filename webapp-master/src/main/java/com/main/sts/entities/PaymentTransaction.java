package com.main.sts.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "payment_transaction")
public class PaymentTransaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "payment_transaction_seq_gen")
    @SequenceGenerator(name = "payment_transaction_seq_gen", sequenceName = "payment_transaction_id_seq")
    private int id;

    public int commuter_id;

    // mihpayid=403993715513773344
    public String mihpayid;

    // mode=CC
    public String mode;

    // txnid=3ac6706bbcc27316a498
    public String txnid;

    // amount=10.0
    public double amount;

    public String email;

    public String mobile;

    // success, or failure
    public String status;

    // bank_ref_num=830790561053161
    public String bank_ref_num;

    // bankcode=CC
    public String bankcode;

    // error=E000
    public String error;

    // error_Message=No+Error
    public String error_message;

    // discount=0.00
    public double discount;

    // net_amount_debit
    public Integer net_amount_debit;

    public Date created_at;

    private String payment_provider = "payumoney";

    public String getMihpayid() {
        return mihpayid;
    }

    public void setMihpayid(String mihpayid) {
        this.mihpayid = mihpayid;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getTxnid() {
        return txnid;
    }

    public void setTxnid(String txnid) {
        this.txnid = txnid;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBank_ref_num() {
        return bank_ref_num;
    }

    public void setBank_ref_num(String bank_ref_num) {
        this.bank_ref_num = bank_ref_num;
    }

    public String getBankcode() {
        return bankcode;
    }

    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Integer getNet_amount_debit() {
        return net_amount_debit;
    }

    public void setNet_amount_debit(Integer net_amount_debit) {
        this.net_amount_debit = net_amount_debit;
    }

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

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

}
