package com.main.sts.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.sts.common.CommonConstants.TransactionBy;
import com.main.sts.common.CommonConstants.TransactionStatus;
import com.main.sts.common.CommonConstants.TransactionType;
import com.main.sts.dao.sql.AccountDao;
import com.main.sts.dao.sql.TransactionDao;
import com.main.sts.dto.response.TransactionResponse;
import com.main.sts.entities.Account;
import com.main.sts.entities.TransactionInfo;

@Service("accountService")
public class AccountService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private TransactionDao transactionDao;

    public List<Account> findAll() {
        return accountDao.findAll();
    }

    public Account getAccount(int account_id) {
        return accountDao.fetchAccount(account_id);
    }

    public Account getAccountByCommuterId(int commuter_id) {
        return accountDao.fetchAccountByCommuterId(commuter_id);
    }

    /**
     * Only Add to Account summary. NOTE: All transactions points added to
     * account_summary should also recorded in transaction table.
     * 
     * @param commuter_id
     * @param points
     * @return
     */
    //@Transactional
    private Account addCreditsToAccount(int commuter_id, int points, int free_rides) {
        Account account = getAccountByCommuterId(commuter_id);
        boolean status = false;
        if (account != null) {
            int rowsAffected = accountDao.addCreditsToAccount(commuter_id, points, free_rides);
            System.out.println("returnCode: Add credits status:" + rowsAffected);
            if (rowsAffected > 0) {
                status = true;
            }
        } else {
            Account newAccount = new Account();
            newAccount.setCommuter_id(commuter_id);
            newAccount.setCredits_available(points);
            newAccount.setFree_rides_available(free_rides);
            newAccount.setCreated_at(new Date());
            
            Serializable id = accountDao.insertEntityAndGetId(newAccount);
//            Serializable id = accountDao.insertEntity(newAccount);

            // commuter_id only as that is pk.
            Integer acc_commuter_id = (Integer) id;
            System.out.println("acc_commuter_id:_" + acc_commuter_id);

            if (id != null) {
                status = true;
            }
        }

        if (status) {
            account = getAccountByCommuterId(commuter_id);
            return account;
        } else {
            System.out.println("returning Account as null");
            return null;
        }
    }

    public Account handleTransaction(TransactionInfo tx) {
        //            if (status && transaction_status.equals(TransactionStatus.SUCCESS)) {

        return null;
    }
    
    public TransactionResponse addCreditsToAccount(TransactionInfo tx) {
        int commuter_id = tx.getCommuter_id();
        int credit_points_added = tx.getPoints_added();
        int free_rides_added = tx.getFree_rides_added();
        Integer transaction_status = tx.getTransaction_status();
        int transactionBy = tx.getTransaction_by();

        TransactionResponse txResponse = new TransactionResponse();

        // validation
        if (transactionBy == TransactionBy.ADMIN.getTypeCode()) {
            String admin_id = tx.getAdmin_id();
            if (admin_id == null) {
                // throw new
                // IllegalArgumentException("Admin Id is required using which transaction is created");
                txResponse.setCommuter_id(commuter_id);
                txResponse.setCredits_available(-1);
                txResponse.setFree_rides_available(-1);
                txResponse.setTransaction_id(-1);
                txResponse.setReturnCode(ReturnCodes.TRANSACION_MANDATORY_PARAMETERS_MISSING);
                txResponse.setMessage("Admin Id is required using which transaction is created");
                return txResponse;
            }
        }
        try {
            // TODO: handle ERROR well.
            Integer txid = transactionDao.insertATransaction(tx);
            if (txid != null && txid != -1) {
                System.out.println("Added to Transaction history for commuter:" + commuter_id);
            } else {
                System.out.println("Failed to add to Transaction history for commuter:" + commuter_id);
            }
            System.out.println("transaction_status.equals(TransactionStatus.SUCCESS)" + transaction_status);
            // Adding to account summary
            if (txid != null && txid != -1) {
                Account account = this.addCreditsToAccount(commuter_id, credit_points_added, free_rides_added);
                System.out.println("account:" + account);
                if (account != null) {
                    System.out.println("updated Account summary for commuter:" + commuter_id);
                    txResponse.setCommuter_id(commuter_id);
                    txResponse.setCredits_available(account.getCredits_available());
                    txResponse.setFree_rides_available(account.getFree_rides_available());
                    txResponse.setTransaction_id(txid);
                    txResponse.setReturnCode(ReturnCodes.TRANSACION_COMPLETED_SUCCESSFULLY);
                    txResponse.setMessage("Transaction completed successfully, Account updated");
                    return txResponse;
                } else {
                    System.out.println("Update to Account summary failed for commuter:" + commuter_id);
                    txResponse.setCommuter_id(commuter_id);
                    txResponse.setCredits_available(-1);
                    txResponse.setFree_rides_available(-1);
                    txResponse.setTransaction_id(txid);
                    txResponse.setReturnCode(ReturnCodes.TRANSACION_ACCOUNT_UPDATION_FAILED);
                    txResponse.setMessage("Transaction faled as, Account updation failed");
                    return txResponse;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            txResponse.setCommuter_id(commuter_id);
            txResponse.setCredits_available(-1);
            txResponse.setFree_rides_available(-1);
            txResponse.setTransaction_id(-1);
            txResponse.setReturnCode(ReturnCodes.FAILED_TRANSACION_UPDATION_FAILED);
            txResponse.setMessage("Transaction updation faled");
            return txResponse;
        }
        return null;
    }
    
    public TransactionResponse refundCreditsToAccount(TransactionInfo tx) {
       return addCreditsToAccount(tx);
    }
    
    public List<Account> findAllWhoDidntUseFreeRides(){
        return accountDao.findAllWhoDidntUseFreeRides();
    }

}
