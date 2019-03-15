package com.main.sts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.sts.common.CommonConstants;
import com.main.sts.common.CommonConstants.TransactionAction;
import com.main.sts.common.CommonConstants.TransactionBy;
import com.main.sts.common.CommonConstants.TransactionStatus;
import com.main.sts.common.CommonConstants.TransactionType;
import com.main.sts.dao.sql.TransactionDao;
import com.main.sts.dto.AccountDTO;
import com.main.sts.dto.RefundDTO;
import com.main.sts.dto.RechargeRequest;
import com.main.sts.dto.response.TransactionResponse;
import com.main.sts.entities.Account;
import com.main.sts.entities.Commuter;
import com.main.sts.entities.TransactionInfo;

@Service("transactionService")
public class TransactionService {

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private AccountService accountService;

    public List<TransactionInfo> findAll() {
        return transactionDao.findAll();
    }

    public TransactionInfo getTransactionById(int tx_id) {
        return transactionDao.findTransactionById(tx_id);
    }

    public List<TransactionInfo> getTransactionsByCommuterId(int commuter_id, Integer offset, Integer limit) {
        return transactionDao.findTransactionByCommuterId(commuter_id, offset, limit);
    }
    
    public List<TransactionInfo> getTransactionsByCommuterId(int commuter_id) {
        return transactionDao.findTransactionByCommuterId(commuter_id);
    }
    public TransactionInfo findLastTransactionByCommuterId(int commuter_id) {
        return transactionDao.findLastTransactionByCommuterId(commuter_id);
    }

    // private boolean recharge1(TransactionInfo recharge) {
    // return transactionDao.insertARecharge(recharge);
    // }

    public TransactionStatus getTransactionStatus(int tx_status_code) {
        TransactionStatus txStatus = TransactionStatus.UNKNOWN;
        if (tx_status_code == TransactionStatus.SUCCESS.getTypeCode()) {
            txStatus = TransactionStatus.SUCCESS;
        } else if (tx_status_code == TransactionStatus.FAILED.getTypeCode()) {
            txStatus = TransactionStatus.FAILED;
        } else if (tx_status_code == TransactionStatus.UNKNOWN.getTypeCode()) {
            txStatus = TransactionStatus.UNKNOWN;
        }
        return txStatus;
    }

//    public TransactionResponse systemRecharge(int commuter_id, int credit_points, int free_rides, TransactionStatus status) {
//        return systemRecharge(commuter_id, credit_points, free_rides, status, null);
//    }
    
    public TransactionResponse systemRecharge(int commuter_id, int credit_points, int free_rides,
            TransactionStatus status, String transaction_desc) {
        System.out.println("SYSTEM RECHARGE: commuter_id:" + commuter_id + "credit points:" + credit_points
                + "--free_rides:" + free_rides + "--status:" + status + "--tx desc:" + transaction_desc);

        return adminRecharge(commuter_id, "SYSTEM", credit_points, free_rides, status,
                TransactionAction.SYSTEM_RECHARGE, transaction_desc);
    }

    public TransactionResponse adminRecharge(int commuter_id, String admin_id_or_name, int credit_points,
            int free_rides, TransactionStatus status, TransactionAction txAction,String transaction_desc) {
        TransactionStatus transaction_status;
        if (status == null) {
            transaction_status = TransactionStatus.UNKNOWN;
        } else {
            transaction_status = status;
        }
        if (txAction == null) {
            txAction = TransactionAction.RECHARGE;
        }
        TransactionResponse txResp = rechargeTransaction(commuter_id, admin_id_or_name, credit_points, free_rides,
                null, transaction_status, TransactionBy.ADMIN, txAction, transaction_desc);
        return txResp;
    }

    public TransactionResponse userRechargeSuccessfulTransaction(RechargeRequest txDTO) {
        TransactionStatus txStatus = getTransactionStatus(txDTO.getTx_status());
        return rechargeTransaction(txDTO.getCommuter_id(), null, txDTO.getPoints(), 0, txDTO.getPayment_tx_id(), txStatus,
                TransactionBy.USER, TransactionAction.RECHARGE, null);
    }

    public TransactionResponse userRechargeFailedTransaction(RechargeRequest txDTO) {
        if (txDTO.getTx_status() != TransactionStatus.FAILED.getTypeCode()) {
            throw new IllegalArgumentException(
                    "Transaction status can not be set other than FAILED for a failed transaction request. ");
        }
        TransactionStatus txStatus = getTransactionStatus(txDTO.getTx_status());
        return rechargeTransaction(txDTO.getCommuter_id(), null, txDTO.getPoints(), 0, txDTO.getPayment_tx_id(), txStatus,
                TransactionBy.USER, TransactionAction.RECHARGE, null);
    }

    public TransactionResponse adminRechargeTransaction(RechargeRequest txDTO) {
        TransactionStatus txStatus = getTransactionStatus(txDTO.getTx_status());
        return rechargeTransaction(txDTO.getCommuter_id(), txDTO.getAdmin_id_or_name(), txDTO.getPoints(), 0, null, txStatus,
                TransactionBy.ADMIN, TransactionAction.RECHARGE, null);
    }

    public TransactionResponse systemRechargeTransaction(RechargeRequest txDTO) {
        TransactionStatus txStatus = getTransactionStatus(txDTO.getTx_status());
        return rechargeTransaction(txDTO.getCommuter_id(), "SYSTEM", txDTO.getPoints(), 0, null, txStatus,
                TransactionBy.SYSTEM, TransactionAction.RECHARGE, null);
    }

//    private TransactionInfo buildRechargeTransactionInfo(RechargeRequest rechargeReq, TransactionBy txBy) {
//        TransactionStatus txStatus = TransactionStatus.getTransactionStatus(rechargeReq.getTx_status());
//        TransactionType txType = TransactionType.CREDIT;
//        TransactionAction txAction = TransactionAction.RECHARGE;
//
//        TransactionInfo tx = buildTransactionInfo1(rechargeReq, txBy, txType, txAction);
//        return tx;
//    }
    
//    private TransactionInfo buildTransactionInfo1(RechargeRequest rechargeReq, TransactionBy txBy,
//            TransactionType txType, TransactionAction txAction) {
//
//        int commuter_id = rechargeReq.getCommuter_id();
//        String admin_id_or_name = rechargeReq.getAdmin_id_or_name();
//        int points = rechargeReq.getPoints();
//
//        TransactionStatus txStatus = TransactionStatus.getTransactionStatus(rechargeReq.getTx_status());
//
//        TransactionInfo tx = new TransactionInfo();
//        tx.setCommuter_id(commuter_id);
//        if (txType.equals(TransactionType.CREDIT)) {
//            tx.setPoints_added(points);
//        } else if (txType.equals(TransactionType.DEBIT)) {
//            tx.setPoints_deducted(points);
//        }
//        tx.setTransaction_type(txType.getTypeCode());
//        tx.setTransaction_status(txStatus.getTypeCode());
//        tx.setTransaction_by(txBy.getTypeCode());
//        tx.setTransaction_action(txAction.name());
//        tx.setAdmin_id(admin_id_or_name);
//        tx.setPayment_tx_id(rechargeReq.getPayment_tx_id());
//        return tx;
//    }
    
    private TransactionInfo buildTransactionInfo(int commuter_id, String admin_id_or_name, int credit_points, int free_rides, Integer payment_tx_id,
            TransactionStatus txStatus, TransactionBy transaction_by, TransactionType type, TransactionAction txAction, String transaction_desc) {
        TransactionInfo tx = new TransactionInfo();
        tx.setCommuter_id(commuter_id);
        if (type.equals(TransactionType.CREDIT)) {
            tx.setPoints_added(credit_points);
        } else if (type.equals(TransactionType.DEBIT)) {
            tx.setPoints_deducted(credit_points);
        }
        
        if (type.equals(TransactionType.CREDIT)) {
            tx.setFree_rides_added(free_rides);
        } else if (type.equals(TransactionType.DEBIT)) {
            tx.setFree_rides_deducted(free_rides);
        }
        
        tx.setTransaction_type(type.getTypeCode());
        tx.setTransaction_status(txStatus.getTypeCode());
        tx.setTransaction_by(transaction_by.getTypeCode());
        tx.setTransaction_action(txAction.name());
        tx.setAdmin_id(admin_id_or_name);
        tx.setPayment_tx_id(payment_tx_id);
        tx.setTransaction_desc(transaction_desc);
        return tx;
    }
    
//    private TransactionInfo buildTransactionInfo(TransactionInfo txInfo, String message) {
//        txInfo.setTransaction_desc(message);
//        return txInfo;
//    }

    public TransactionResponse rechargeTransaction(int commuter_id, String admin_id_or_name, int credit_points, int free_rides, Integer payment_tx_id,
            TransactionStatus txStatus, TransactionBy transaction_by, TransactionAction txAction, String transaction_desc) {

        TransactionInfo tx = buildTransactionInfo(commuter_id, admin_id_or_name, credit_points, free_rides, payment_tx_id, txStatus, transaction_by,
                TransactionType.CREDIT, txAction, transaction_desc);
        if (txStatus.equals(TransactionStatus.FAILED)) {
            return insertFailedRechargeTransaction(tx);
        } else if (txStatus.equals(TransactionStatus.SUCCESS)) {
            return insertSuccessfulRechargeTransaction(tx);
        } else {
            throw new IllegalArgumentException("TransactionStatus not set:getting" + txStatus);
        }
    }
    
    public TransactionResponse insertSuccessfulRechargeTransaction(TransactionInfo tx) {
        try {
            TransactionResponse txResp = accountService.addCreditsToAccount(tx);
            return txResp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // transaction failed at mobile client for payment, doing an entry for
    // showing later to user.
    public TransactionResponse insertFailedRechargeTransaction(TransactionInfo tx) {
        TransactionResponse txResponse = new TransactionResponse();
        try {
            Integer txid = transactionDao.insertATransaction(tx);
            txResponse.setCommuter_id(tx.getCommuter_id());
            txResponse.setCredits_available(-1);
            txResponse.setTransaction_id(txid);
            txResponse.setReturnCode(ReturnCodes.FAILED_TRANSACION_UPDATION_SUCCESS);
            txResponse.setMessage("Failed Transaction update success");
            return txResponse;
        } catch (Exception e) {
            e.printStackTrace();
            txResponse.setCommuter_id(tx.getCommuter_id());
            txResponse.setCredits_available(-1);
            txResponse.setTransaction_id(-1);
            txResponse.setReturnCode(ReturnCodes.FAILED_TRANSACION_UPDATION_FAILED);
            txResponse.setMessage("Transaction updation faled");
            return txResponse;
        }
    }

    public TransactionResponse refund(RefundDTO refundReq) {
        TransactionInfo txInfo = buildTransactionInfo(refundReq.getCommuter_id(), refundReq.getAdmin_id_or_name(),
                refundReq.getRefund_points(), refundReq.getRefund_free_rides(), null, TransactionStatus.SUCCESS, TransactionBy.ADMIN,
                TransactionType.CREDIT, TransactionAction.REFUND, refundReq.getRefund_reason());

        return accountService.refundCreditsToAccount(txInfo);
    }
    
    public Account getAccountBalance(int commuter_id) {
        Account account = accountService.getAccountByCommuterId(commuter_id);
        return account;
    }
    
    public AccountDTO getUserAccountBalance(int commuter_id) {
        Account account = this.getAccountBalance(commuter_id);
        AccountDTO accDTO = new AccountDTO();
        accDTO.setCommuter_id(commuter_id);
        if (account != null) {
            System.out.println("account:"+account);
            accDTO.setPoints_balance(account.getCredits_available());
            accDTO.setFree_rides_balance(account.getFree_rides_available());
            String free_rides_account_message = String.format(CommonConstants.FREE_RIDES_ACC_MESSAGE, account.getFree_rides_available());
            // TODO: Add this for 1month pack: FREE_RIDES_ACC_MESSAGE_1MONTH
            
            accDTO.setFree_rides_account_message(free_rides_account_message);
            accDTO.setReturnCode(ReturnCodes.SUCCESS);
        } else {
            accDTO.setPoints_balance(-1);
            accDTO.setReturnCode(ReturnCodes.UNKNOWN_ERROR);
        }
        return accDTO;
    }
    
    public boolean cancelFreeRides(int commuter_id) {
        return transactionDao.cancelFreeRides(commuter_id);
    }

}
