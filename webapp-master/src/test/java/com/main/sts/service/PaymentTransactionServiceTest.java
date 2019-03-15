package com.main.sts.service;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;

import com.main.sts.BaseTest;
import com.main.sts.entities.PaymentTransaction;

public class PaymentTransactionServiceTest extends BaseTest {

    @Resource
    private PaymentTransactionService paymentTxService;

    @Test
    public void insertPaymentTransaction() {
        PaymentTransaction paymentTx = new PaymentTransaction();
        paymentTx.commuter_id = 80;
        paymentTx.mihpayid = "403993715513773344";
        paymentTx.mode = "CC";
        paymentTx.txnid = "3ac6706bbcc27316a498";
        paymentTx.amount = 10.0;
        paymentTx.email = "mtejaswaroop%40gmail.com";
        paymentTx.mobile = "9494740413";
        paymentTx.status = "success";
        paymentTx.bank_ref_num = "830790561053161";
        paymentTx.bankcode = "CC";
        paymentTx.error = "E000";
        paymentTx.error_message = "No+Error";
        paymentTx.discount = 0.00;
        paymentTx.net_amount_debit = 10;
        int id = paymentTxService.insertUserPaymentDetails(paymentTx);
        System.out.println("id:" + id);
    }
}
