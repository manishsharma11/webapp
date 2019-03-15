package com.main.sts.dto.response;

import java.io.Serializable;

import com.main.sts.service.ReturnCodes;

public class PaymentTransactionDTO implements Serializable {

    public int payment_tx_id;

    public int getPayment_tx_id() {
        return payment_tx_id;
    }

    public void setPayment_tx_id(int payment_tx_id) {
        this.payment_tx_id = payment_tx_id;
    }

}
