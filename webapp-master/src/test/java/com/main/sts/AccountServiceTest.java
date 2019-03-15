package com.main.sts;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;

import com.main.sts.common.CommonConstants.TransactionBy;
import com.main.sts.common.CommonConstants.TransactionStatus;
import com.main.sts.common.CommonConstants.TransactionType;
import com.main.sts.dto.response.TransactionResponse;
import com.main.sts.entities.Account;
import com.main.sts.entities.TransactionInfo;
import com.main.sts.service.AccountService;
import com.main.sts.service.TransactionService;

public class AccountServiceTest extends BaseTest {

    @Resource
    private AccountService accountService;
    
    @Resource
    private TransactionService txService;

    @Test
    public void testFindAll() {
        Assert.assertFalse(accountService.findAll().isEmpty());
        for (Account c : accountService.findAll()) {
            System.out.println(c.getCommuter_id() + "--" + c.getCredits_available());
        }
    }

    @Test
    public void testAddCredits() {
        int commuter_id = 80;
        int points = 12;
        TransactionResponse response = null;

        TransactionInfo tx = new TransactionInfo();
        tx.setCommuter_id(commuter_id);
        tx.setPoints_added(points);
        tx.setTransaction_type(TransactionType.CREDIT.getTypeCode());
        tx.setTransaction_status(TransactionStatus.SUCCESS.getTypeCode());
        tx.setTransaction_by(TransactionBy.USER.getTypeCode());
        tx.setTransaction_desc("Payment By User");
        try {
            response = accountService.addCreditsToAccount(tx);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("response:" + response);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getCredits_available());
    }
    
    @Test
    public void testRefundCredits() {
        int commuter_id = 80;
        int points = 12;
        TransactionResponse response = null;
        int tx_id = 344;

        TransactionInfo tx = txService.getTransactionById(tx_id);
        try {
            response = accountService.refundCreditsToAccount(tx);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("response:" + response);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getCredits_available());

    }


}
