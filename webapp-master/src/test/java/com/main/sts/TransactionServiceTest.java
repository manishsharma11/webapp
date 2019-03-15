package com.main.sts;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.main.sts.dto.AccountDTO;
import com.main.sts.dto.RefundDTO;
import com.main.sts.dto.response.TransactionResponse;
import com.main.sts.entities.TransactionInfo;
import com.main.sts.service.TransactionService;

public class TransactionServiceTest extends BaseTest {

    @Resource
    private TransactionService txService;

    @Test
    public void findAll() {
        List<TransactionInfo> list = txService.getTransactionsByCommuterId(80, 0, 1);
        for (TransactionInfo tx : list) {
            System.out.println(tx.getId());
        }

        List<TransactionInfo> list1 = txService.getTransactionsByCommuterId(80, 1, 2);
        for (TransactionInfo tx : list1) {
            System.out.println(tx.getId());
        }
    }

    @Test
    public void findAccountBalance() {
        int commuter_id = 80;
        AccountDTO accountDTO = txService.getUserAccountBalance(commuter_id);
        System.out.println(accountDTO);
    }
    
    @Test
    public void refund(){
        int commuter_id = 766;
        RefundDTO refundDTO = new RefundDTO();
        refundDTO.setCommuter_id(commuter_id);
        refundDTO.setAdmin_id_or_name("admin");
        refundDTO.setRefund_points(25);
        refundDTO.setRefund_free_rides(1);
        refundDTO.setRefund_reason("testing");
        TransactionResponse txResp = txService.refund(refundDTO);
        System.out.println(txResp);
    }
}
