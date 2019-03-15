package com.main.sts.controllers.rest;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.main.sts.dto.AccountDTO;
import com.main.sts.dto.RechargeOptionsRequest;
import com.main.sts.dto.RechargeRequest;
import com.main.sts.dto.RefundDTO;
import com.main.sts.dto.Response;
import com.main.sts.dto.TransactionDTO;
import com.main.sts.dto.response.PaymentTransactionDTO;
import com.main.sts.dto.response.TransactionResponse;
import com.main.sts.entities.PaymentTransaction;
import com.main.sts.entities.RechargeOptions;
import com.main.sts.entities.TransactionInfo;
import com.main.sts.service.PaymentTransactionService;
import com.main.sts.service.RechargeOptionsService;
import com.main.sts.service.ReturnCodes;
import com.main.sts.service.TransactionService;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService txService;

    @Autowired
    private PaymentTransactionService paymentTxService;

    @Autowired
    private RechargeOptionsService rechargeOptionsService;

    static final Logger logger = Logger.getLogger(TransactionController.class);

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<TransactionInfo>> getTransactionHistoryList() {
        List<TransactionInfo> transactions = null;
        try {
            transactions = txService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<List<TransactionInfo>>(transactions, HttpStatus.OK);
    }

    @RequestMapping(value = "/findById/{tx_id}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<TransactionInfo> findById(@PathVariable Integer tx_id) {
        TransactionInfo txInfo = null;
        try {
            txInfo = txService.getTransactionById(tx_id);
            return new ResponseEntity<TransactionInfo>(txInfo, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<TransactionInfo>(txInfo, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    // TODO: Enable it once another method is deleted.
    @RequestMapping(value = "/findByCommuterId", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Response> findByCommuterId(@RequestBody TransactionDTO tx) {
        Response resp = new Response();
        List<TransactionInfo> txs = null;
        try {
            txs = txService.getTransactionsByCommuterId(tx.getCommuter_id(), tx.getOffset(), tx.getLimit());
            resp.response = txs;
            resp.setReturnCode(ReturnCodes.SUCCESS.name());
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setReturnCode(ReturnCodes.UNKNOWN_ERROR.name());
            return new ResponseEntity<Response>(resp, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/user/recharge/success", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<TransactionResponse> userAccountRechargeSuccess(@RequestBody RechargeRequest tx) {
        TransactionResponse txResp = new TransactionResponse();
        try {
            txResp = txService.userRechargeSuccessfulTransaction(tx);
            txResp.setReturnCode(ReturnCodes.SUCCESS);
            return new ResponseEntity<TransactionResponse>(txResp, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            txResp.setReturnCode(ReturnCodes.SUCCESS_TRANSACION_UPDATION_FAILED);
            return new ResponseEntity<TransactionResponse>(txResp, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/user/recharge/failed", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<TransactionResponse> userAccountRechargeFailed(@RequestBody RechargeRequest tx) {
        TransactionResponse txResp = new TransactionResponse();
        try {
            txResp = txService.userRechargeFailedTransaction(tx);
            txResp.setReturnCode(ReturnCodes.SUCCESS);
            return new ResponseEntity<TransactionResponse>(txResp, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            txResp.setReturnCode(ReturnCodes.FAILED_TRANSACION_UPDATION_FAILED);
            return new ResponseEntity<TransactionResponse>(txResp, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/admin/recharge", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<TransactionResponse> adminAccountRecharge(@RequestBody RechargeRequest tx) {
        TransactionResponse txResp = new TransactionResponse();
        try {
            txResp = txService.adminRechargeTransaction(tx);
            txResp.setReturnCode(ReturnCodes.SUCCESS);
            return new ResponseEntity<TransactionResponse>(txResp, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            txResp.setReturnCode(ReturnCodes.TRANSACTION_ADMIN_RECHARGE_UPDATION_FAILED);
            return new ResponseEntity<TransactionResponse>(txResp, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/system/recharge", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<TransactionResponse> systemAccountRecharge(@RequestBody RechargeRequest tx) {
        TransactionResponse txResp = new TransactionResponse();
        try {
            txResp = txService.systemRechargeTransaction(tx);
            txResp.setReturnCode(ReturnCodes.SUCCESS);
            return new ResponseEntity<TransactionResponse>(txResp, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            txResp.setReturnCode(ReturnCodes.TRANSACTION_SYSTEM_RECHARGE_UPDATION_FAILED);
            return new ResponseEntity<TransactionResponse>(txResp, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/refund", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<TransactionResponse> refundAccount(@RequestBody RefundDTO refundReq) {
        TransactionResponse txResp = new TransactionResponse();
        try {
            txResp = txService.refund(refundReq);
            txResp.setReturnCode(ReturnCodes.REFUND_SUCCESS);
            return new ResponseEntity<TransactionResponse>(txResp, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            txResp.setReturnCode(ReturnCodes.REFUND_FAILED);
            return new ResponseEntity<TransactionResponse>(txResp, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/account/balance/{commuter_id}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<AccountDTO> getAccountBalance(@PathVariable int commuter_id) {
        AccountDTO acc = new AccountDTO();
        try {
            acc = txService.getUserAccountBalance(commuter_id);
            acc.setReturnCode(ReturnCodes.SUCCESS);
            return new ResponseEntity<AccountDTO>(acc, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            acc.setReturnCode(ReturnCodes.UNKNOWN_ERROR);
            return new ResponseEntity<AccountDTO>(acc, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/payment/update", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Response> insertUserPaymentDetails(@RequestBody PaymentTransaction tx) {
        Response resp = new Response();
        try {
            PaymentTransactionDTO paymentResp = new PaymentTransactionDTO();
            Integer id = paymentTxService.insertUserPaymentDetails(tx);
            if (id != -1) {
                paymentResp.payment_tx_id = id;
                resp.response = paymentResp;
                resp.setReturnCode(ReturnCodes.PAYMENT_TRANSACTION_UPDATION_SUCCESS.name());
            } else {
                resp.setReturnCode(ReturnCodes.UNKNOWN_ERROR.name());
            }
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp.response = null;
            resp.setReturnCode(ReturnCodes.PAYMENT_TRANSACTION_UPDATION_FAILED.name());
            return new ResponseEntity<Response>(resp, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
    
    @RequestMapping(value = "/get_recharge_options", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Response> getRechargeOptions() {
        Response resp = new Response();
        try {
            List<RechargeOptions> list = rechargeOptionsService.getRechargeOptions(true);
            if (list != null) {
                resp.response = list;
                resp.setReturnCode(ReturnCodes.SUCCESS.name());
            } else {
                resp.setReturnCode(ReturnCodes.UNKNOWN_ERROR.name());
            }
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp.response = null;
            resp.setReturnCode(ReturnCodes.UNKNOWN_ERROR.name());
            return new ResponseEntity<Response>(resp, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
    
    @RequestMapping(value = "/get_recharge_options_by_commuter", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Response> getRechargeOptions(@RequestBody RechargeOptionsRequest req) {
        Response resp = new Response();
        try {
            List<RechargeOptions> list = rechargeOptionsService.getRechargeOptions(req, true);
            if (list != null) {
                resp.response = list;
                resp.setReturnCode(ReturnCodes.SUCCESS.name());
            } else {
                resp.setReturnCode(ReturnCodes.UNKNOWN_ERROR.name());
            }
            return new ResponseEntity<Response>(resp, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp.response = null;
            resp.setReturnCode(ReturnCodes.UNKNOWN_ERROR.name());
            return new ResponseEntity<Response>(resp, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

}
