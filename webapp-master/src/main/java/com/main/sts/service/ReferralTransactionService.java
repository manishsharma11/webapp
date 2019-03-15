package com.main.sts.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.sts.dao.sql.AccountDao;
import com.main.sts.dao.sql.ReferralTransactionDao;
import com.main.sts.entities.ReferralTransactionInfo;

@Service("referralTransactionService")
public class ReferralTransactionService {

    @Autowired
    private ReferralTransactionDao referralAccountDao;
    
    @Autowired
    private AccountDao accountDao;

    // @Autowired
    // private TransactionDao transactionDao;

    public List<ReferralTransactionInfo> findAll() {
        return referralAccountDao.findAll();
    }

    public ReferralTransactionInfo getAccount(int account_id) {
        return referralAccountDao.fetchAccount(account_id);
    }

    public ReferralTransactionInfo getAccountByCommuterId(int commuter_id) {
        return referralAccountDao.fetchAccountByCommuterId(commuter_id);
    }

    public void insert(ReferralTransactionInfo entity) {
        entity.setCreated_at(new Date());
        referralAccountDao.insertEntityAndGetId(entity);
    }

    public ReferralTransactionInfo getTransactionById(int tx_id) {
        return referralAccountDao.findTransactionById(tx_id);
    }

    public List<ReferralTransactionInfo> getTransactionsByCommuterId(int referred_by, Integer offset, Integer limit) {
        return referralAccountDao.findTransactionByReferredBy(referred_by, offset, limit);
    }

    public List<ReferralTransactionInfo> getTransactionsByReferredBy(int referred_by) {
        return referralAccountDao.findTransactionByReferredBy(referred_by);
    }
    
    public List<ReferralTransactionInfo> getTransactionsByReferredBy(int referred_by, boolean referred_to_redeemed_first_ride) {
        return referralAccountDao.findTransactionByReferredBy(referred_by, referred_to_redeemed_first_ride);
    }
    
    public ReferralTransactionInfo findLastTransactionByCommuterId(int commuter_id) {
        return referralAccountDao.findLastTransactionByCommuterId(commuter_id);
    }

    public void updateAccountForOneMonthFreeRide(int userWhoReferredId) {
        accountDao.updateAccountForOneMonthFreeRide(userWhoReferredId);
    }

}
