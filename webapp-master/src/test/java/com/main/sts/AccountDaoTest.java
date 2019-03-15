package com.main.sts;

import javax.annotation.Resource;

import org.junit.Test;

import com.main.sts.dao.sql.AccountDao;
import com.main.sts.entities.Account;

public class AccountDaoTest extends BaseTest {

    @Resource
    private AccountDao accountDao;

    @Test
    public void addCreditsToAccount() {

        int commuter_id = 80;
        // fetching account balance
        Account account = accountDao.fetchAccountByCommuterId(commuter_id);
        System.out.println("account balance:" + account.getCredits_available());

        org.junit.Assert.assertNotNull(account);

        int points = 10;
        int free_rides = 2;
        // Adding credits
        accountDao.addCreditsToAccount(80, points, free_rides);

        // fetching the new Account balance
        Account updatedAccount = accountDao.fetchAccountByCommuterId(commuter_id);

        System.out.println("updatedAccount balance:" + updatedAccount.getCredits_available());
        org.junit.Assert.assertEquals(account.getCredits_available() + points, updatedAccount.getCredits_available());
    }
}
