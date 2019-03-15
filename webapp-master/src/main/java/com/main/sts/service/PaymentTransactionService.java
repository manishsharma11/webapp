package com.main.sts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.sts.dao.sql.PaymentTransactionDao;
import com.main.sts.entities.PaymentTransaction;

@Service("paymentTransactionService")
public class PaymentTransactionService {

    @Autowired
    private PaymentTransactionDao paymentTxDao;

    public List<PaymentTransaction> findAll() {
        return paymentTxDao.findAll();
    }

    public PaymentTransaction getTransactionById(int tx_id) {
        return paymentTxDao.findTransactionById(tx_id);
    }

    public List<PaymentTransaction> getTransactionsByCommuterId(int commuter_id, Integer offset, Integer limit) {
        return paymentTxDao.findTransactionByCommuterId(commuter_id, offset, limit);
    }

    public List<PaymentTransaction> getTransactionsByCommuterId(int commuter_id) {
        return paymentTxDao.findTransactionByCommuterId(commuter_id);
    }
    public PaymentTransaction findLastTransactionByCommuterId(int commuter_id) {
        return paymentTxDao.findLastTransactionByCommuterId(commuter_id);
    }

    public Integer insertUserPaymentDetails(PaymentTransaction tx) {
        return paymentTxDao.insertAPaymentTransaction(tx);
    }

}
