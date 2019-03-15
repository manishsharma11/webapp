package com.main.sts.controllers.webapp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.main.sts.common.CommonConstants.TransactionStatus;
import com.main.sts.common.CommonConstants.TransactionType;
import com.main.sts.dto.AccountDTO;
import com.main.sts.dto.RechargeRequest;
import com.main.sts.dto.Response;
import com.main.sts.dto.response.PaymentTransactionDTO;
import com.main.sts.dto.response.TransactionResponse;
import com.main.sts.entities.Commuter;
import com.main.sts.entities.PaymentTransaction;
import com.main.sts.entities.RechargeOptions;
import com.main.sts.entities.TransactionInfo;
import com.main.sts.service.PaymentTransactionService;
import com.main.sts.service.RechargeOptionsService;
import com.main.sts.service.ReturnCodes;
import com.main.sts.service.TransactionService;
import com.main.sts.util.SystemConstants;

@Controller
@RequestMapping("/webapp/transaction")
public class TransactionWebAppController extends CommonController{

    @Autowired
    private TransactionService txService;

    @Autowired
    private PaymentTransactionService paymentTxService;

    @Autowired
    private RechargeOptionsService rechargeOptionsService;

    static final Logger logger = Logger.getLogger(TransactionWebAppController.class);

    @RequestMapping(value = "/my_transactions", method = RequestMethod.GET)
    public ModelAndView findByCommuterId(ModelAndView model, HttpServletRequest request, HttpSession session) {
        
        session = super.getSession(request);
        Commuter commuter = super.getCommuter(request);
        int commuter_id = commuter.getCommuter_id();
        List<TransactionInfo> txs = null;
        try {
            Integer offset = (request.getParameter("offset") != null
                    && !request.getParameter("offset").trim().isEmpty())
                            ? Integer.parseInt(request.getParameter("offset"))
                            : 0;
            Integer limit = (request.getParameter("limit") != null && !request.getParameter("limit").trim().isEmpty())
                    ? Integer.parseInt(request.getParameter("limit"))
                    : 50;
           
            txs = txService.getTransactionsByCommuterId(commuter_id, offset, limit);
            model.addObject("transactionsList", txs);  
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.setViewName("/webapp/my_transactions");
        model.addObject("current_page", "my_transactions");
        return model;
    }
    
    @RequestMapping(value = "/my_wallet", method = RequestMethod.GET)
    public ModelAndView getMyWallet(ModelAndView model, HttpSession session, HttpServletRequest request) {
        try {
            model = getAccountBalance(model, session, request);
            model = getRechargeOptions(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.setViewName("/webapp/my_wallet");
        return model;
    }
    
    @RequestMapping(value = "/account/balance", method = RequestMethod.GET)
    public ModelAndView getAccountBalance(ModelAndView model, HttpSession session, HttpServletRequest request) {
        AccountDTO acc = new AccountDTO();
        request.getSession(false);
        try {
            acc = txService.getUserAccountBalance(((Commuter)session.getAttribute("commuter")).getCommuter_id());
            acc.setReturnCode(ReturnCodes.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            acc.setReturnCode(ReturnCodes.UNKNOWN_ERROR);
        }
        model.addObject("acc", acc);
        return model;
    }
    
    @RequestMapping(value = "/get_recharge_options")
    public ModelAndView getRechargeOptions(ModelAndView model) {
        Response resp = new Response();
        try {
            List<RechargeOptions> list = rechargeOptionsService.getRechargeOptions(true);
            if (list != null) {
                resp.setReturnCode(ReturnCodes.SUCCESS.name());
                model.addObject("list", list);
                model.addObject("resp",resp);
            } else {
                resp.setReturnCode(ReturnCodes.UNKNOWN_ERROR.name());
                model.addObject("resp", resp);
            }
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            resp.response = null;
            resp.setReturnCode(ReturnCodes.UNKNOWN_ERROR.name());
            model.addObject("resp", resp);
            return model;
        }
        
    }
    
//    String merchant_key="MtLdu1";
//    String salt="VD2Kc8Px";
//    String action1 ="";
//    String base_url="https://test.payu.in";
    
    String merchant_key="Vh10ae";
    String salt="U3XzuivH";
    String action1 ="";
    String base_url="https://secure.payu.in"; 
    
    
    @RequestMapping(value = "/payUMoney", method = RequestMethod.POST)
    public ModelAndView showPayUMoneyPage(ModelAndView model, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes, HttpSession session) {
        
        try{
        session = super.getSession(request);
        Commuter commuter = super.getCommuter(request);
        int commuter_id = commuter.getCommuter_id();
        String txnid = "";
        String recharge_options_id = request.getParameter("recharge_options_id");
        String amount = request.getParameter("amount_selected");
        String firstname = commuter.getName();
        String email = commuter.getEmail();
        String phone = commuter.getMobile();
        String productinfo = "ECRecharge";
        String surl = "https://payu.herokuapp.com/success";
        String furl =  "https://payu.herokuapp.com/failure";
        
        //String url = request.getScheme() + "://"+ request.getServerName();
        String url = "https" + "://"+ request.getServerName();

        System.out.println("url:"+url);
        String contextPath = request.getContextPath();
        
        System.out.println("contextPath:"+contextPath);

        if (contextPath != null && !contextPath.equals("")) {
            if (!url.endsWith("/")) {
                url = url + "/";
            }
            // contextPath:/webapp
            if (contextPath.startsWith("/")) {
                contextPath = contextPath.substring(1);
            }
            url = url + contextPath;
        }
        
        // surl="https://dev.easycommute.co/webapp/webapp/transaction/payUMoney/success";
        // furl="https://dev.easycommute.co/webapp/webapp/transaction/payUMoney/failure";

        if (!url.endsWith("/")) {
            url = url + "/";
        }
        surl = url + "webapp/transaction/payUMoney/success";
        furl = url + "webapp/transaction/payUMoney/failure";

        System.out.println("surl:"+surl);
        System.out.println("furl:"+furl);

        String service_provider = "payu_paisa";
        
        System.out.println("recharge_options_id:"+recharge_options_id);
        
        Random rand = new Random();
        String rndm = Integer.toString(rand.nextInt())+(System.currentTimeMillis() / 1000L);
        txnid=hashCal("SHA-256",rndm).substring(0,20);
        
        Map<String,String> params= new HashMap<String,String>();
        params.put("key", merchant_key);
        params.put("txnid", txnid);
        params.put("amount", amount);
        params.put("firstname", firstname);
        params.put("email", email);
        params.put("phone", phone);
        params.put("productinfo", productinfo);
        params.put("surl", surl);
        params.put("furl", furl);
        params.put("service_provider", service_provider);
        params.put("udf1", recharge_options_id);
        params.put("udf2", txnid);

        params.put("udf3", "" + commuter_id);

        String hash = createHash(params, salt);

        System.out.println("hash:"+hash);
        params.put("hash", hash);

        
        //redirectAttributes.addFlashAttribute("message", "User " + login + " deleted");
//        String action  = test(params);
//        //return "redirect:/webapp/payuform";
//        for (String s : params.keySet()) {
//            redirectAttributes.addFlashAttribute(s, params.get(s));
//            redirectAttributes.addAttribute(s, params.get(s));
//        }
        model.addObject("params", params);
        model.setViewName("/webapp/payuform");

        //System.out.println("action:"+action + "-- params:"+params);
        //return "redirect:"+action;
        
        //request.getParameterMap().putAll(params);
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }
    
    public String createHash(Map<String, String> params, String salt) {
        String hashString = "";
        String hash = "";
        String hashSequence = "key|txnid|amount|productinfo|firstname|email|udf1|udf2|udf3|udf4|udf5|udf6|udf7|udf8|udf9|udf10";
        String[] hashVarSeq = hashSequence.split("\\|");

        for (String part : hashVarSeq) {
            hashString = (empty(params.get(part))) ? hashString.concat("") : hashString.concat(params.get(part));
            hashString = hashString.concat("|");
        }
        hashString = hashString.concat(salt);

        hash = hashCal("SHA-512", hashString);
        return hash;
    }
    
    public String hashCal(String type, String str) {
        byte[] hashseq = str.getBytes();
        StringBuffer hexString = new StringBuffer();
        try {
            MessageDigest algorithm = MessageDigest.getInstance(type);
            algorithm.reset();
            algorithm.update(hashseq);
            byte messageDigest[] = algorithm.digest();

            for (int i = 0; i < messageDigest.length; i++) {
                String hex = Integer.toHexString(0xFF & messageDigest[i]);
                if (hex.length() == 1)
                    hexString.append("0");
                hexString.append(hex);
            }

        } catch (NoSuchAlgorithmException nsae) {
        }

        return hexString.toString();

    }

    public boolean empty(String s) {
        if (s == null || s.trim().equals(""))
            return true;
        else
            return false;
    }
    
    @RequestMapping(value = "/payUMoney/success", method = RequestMethod.POST)
    public String handleSuccessTransaction(ModelAndView model, HttpServletRequest request,
            HttpServletResponse response, HttpSession session) {
        System.out.println("-------------Payumoney success transaction---------------");
        return handleTransaction(model, request, response, session);
    }

    @RequestMapping(value = "/payUMoney/failure", method = RequestMethod.POST)
    public String handleFailedTransaction(ModelAndView model, HttpServletRequest request, HttpServletResponse response,
            HttpSession session) {
        System.out.println("-------------Payumoney failed transaction---------------");
        //return handleTransaction(model, request, response, session);
        return "redirect:/webapp/transaction/my_wallet";
    }

    public String handleTransaction(ModelAndView model, HttpServletRequest request, HttpServletResponse response,
            HttpSession session) {
            
        String status = request.getParameter("status");
        String firstname = request.getParameter("firstname");
        String amount = request.getParameter("amount");
        String txnid = request.getParameter("txnid");
        String posted_hash = request.getParameter("hash");
        String key = request.getParameter("key");
        String productinfo = request.getParameter("productinfo");
        String email = request.getParameter("email");
        String recharge_options_id = request.getParameter("udf1");
        String commuter_id_str = request.getParameter("udf3");

        Enumeration paramNames = request.getParameterNames();
        
        Map<String, String> params = new HashMap<String, String>();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            String paramValue = request.getParameter(paramName);
            params.put(paramName, paramValue);
        }
        //String retHashSeq = "salt|status|udf10|udf9|udf8|udf7|udf6|udf5|udf4|udf3|udf2|udf1|email|firstname|productinfo|amount|txnid|key";
        String retHashSeq = "status|udf10|udf9|udf8|udf7|udf6|udf5|udf4|udf3|udf2|udf1|email|firstname|productinfo|amount|txnid|key";

        String[] hashVarSeq = retHashSeq.split("\\|");
        
        retHashSeq=salt+'|';
        
        for (String part : hashVarSeq) {
            retHashSeq = (empty(params.get(part))) ? retHashSeq.concat("") : retHashSeq.concat(params.get(part));
            retHashSeq = retHashSeq.concat("|");
        }

        System.out.println("retHashSeq:"+retHashSeq);
        
        //String hash = hashCal("SHA-512", retHashSeq);
        retHashSeq = retHashSeq.substring(0,retHashSeq.length()-1);//due to extra pipe (|)
        String hash = hashCal("SHA-512", retHashSeq);

        try {
            System.out.println(hash + "------------------------" + posted_hash);
            if (!hash.equals(posted_hash)) {
                response.getWriter().print(hash + "<br/>");
                response.getWriter().print(posted_hash + "<br/>");
                response.getWriter().print("Invalid Transaction. Please try again");
            } else {
                
                Commuter c = ((Commuter) session.getAttribute("commuter"));
                // giving preference to the sessions's commuter_id;
                int commuter_id = -1;
                if (c != null) {
                    commuter_id = c.getCommuter_id();
                } else {
                    commuter_id = Integer.parseInt(commuter_id_str);
                }

                PaymentTransaction tx = populatePaymentTxFields(params, commuter_id);
                Integer txId = paymentTxService.insertUserPaymentDetails(tx);
                if (txId != -1) {

                    Integer rId = Integer.parseInt(recharge_options_id);
                    RechargeOptions rechargeOptions = rechargeOptionsService.getRechargeOptions(rId);
                    RechargeRequest rechargeReq = new RechargeRequest();

                    rechargeReq.setCommuter_id(commuter_id);
                    rechargeReq.setPoints(rechargeOptions.getNum_credits_offered());
                    rechargeReq.setTx_type(TransactionType.CREDIT.intValue());
                    rechargeReq.setAdmin_id_or_name(null);
                    rechargeReq.setPayment_tx_id(txId);

                    if (status.equals("success")) {
                        rechargeReq.setTx_status(TransactionStatus.SUCCESS.intValue());
                        TransactionResponse txResp = txService.userRechargeSuccessfulTransaction(rechargeReq);
                        System.out.println("Inserted a successful recharge for commuter_id:" + commuter_id + " for amount:"
                                + amount);

                    } else {
                        rechargeReq.setTx_status(TransactionStatus.FAILED.intValue());
                        TransactionResponse txResp = txService.userRechargeFailedTransaction(rechargeReq);
                        System.out.println("Inserted a failed recharge for commuter_id:" + commuter_id + " for amount:"
                                + amount);
                    }
                }
                
                response.getWriter().print("<h3>Thank You. Your order status is " + status + "</h3>");
                response.getWriter().print("<h4>Your Transaction ID for this transaction is " + txnid + "</h4>");
                response.getWriter().print(
                        "<h4>We have received a payment of Rs. " + amount + "Your order will soon be shipped.</h4>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return "redirect:/my_wallet";
        return "redirect:/webapp/transaction/my_wallet";
    }
    
    public PaymentTransaction populatePaymentTxFields(Map<String, String> params, int commuer_id) {
        PaymentTransaction tx = new PaymentTransaction();

        tx.commuter_id = commuer_id;
        tx.mihpayid = params.get("mihpayid");
        tx.mode = params.get("mode");
        tx.txnid = params.get("txnid");
        tx.amount = Double.parseDouble(params.get("amount"));
        tx.email = params.get("email");
        tx.mobile = params.get("phone");
        tx.status = params.get("status");
        tx.bank_ref_num = params.get("bank_ref_num");
        tx.bankcode = params.get("bankcode");
        tx.error = params.get("error");
        tx.error_message = params.get("error_Message");
        tx.discount = Double.parseDouble(params.get("discount"));
        tx.net_amount_debit = (Double.valueOf((Double.parseDouble(params.get("net_amount_debit"))))).intValue();
        tx.created_at = new Date();

        return tx;

    }
    
    public String test(Map<String,String> params){
        
        
        /*  String merchant_key="Vh10ae";
          String salt="U3XzuivH";
          String action1 ="";
          String base_url="https://secure.payu.in"; */
          
          int error=0;
          String hashString="";
          
          
//          Enumeration paramNames = request.getParameterNames();
//          Map<String,String> params= new HashMap<String,String>();
//              while(paramNames.hasMoreElements()) 
//          {
//                  String paramName = (String)paramNames.nextElement();
//              
//                  String paramValue = request.getParameter(paramName);
  //
//            params.put(paramName,paramValue);
//          }
              
              //Map<String,String> params= (HashMap<String,String>)request.getAttribute("params");
          
          String txnid ="";
          if(empty(params.get("txnid"))){
            Random rand = new Random();
            String rndm = Integer.toString(rand.nextInt())+(System.currentTimeMillis() / 1000L);
            txnid=hashCal("SHA-256",rndm).substring(0,20);
            params.put("txnid", txnid);
          }
          else
            txnid=params.get("txnid");
                String udf2 = txnid;
          String txn="abcd";
          String hash="";
          String hashSequence = "key|txnid|amount|productinfo|firstname|email|udf1|udf2|udf3|udf4|udf5|udf6|udf7|udf8|udf9|udf10";
          if(empty(params.get("hash")) && params.size()>0)
          {
            if( empty(params.get("key"))
              || empty(params.get("txnid"))
              || empty(params.get("amount"))
              || empty(params.get("firstname"))
              || empty(params.get("email"))
              || empty(params.get("phone"))
              || empty(params.get("productinfo"))
              || empty(params.get("surl"))
              || empty(params.get("furl"))
              || empty(params.get("service_provider"))
          )
              
              error=1;
            else{
              String[] hashVarSeq=hashSequence.split("\\|");
              
              for(String part : hashVarSeq)
              {
                hashString= (empty(params.get(part)))?hashString.concat(""):hashString.concat(params.get(part));
                hashString=hashString.concat("|");
              }
              hashString=hashString.concat(salt);
              

               hash=hashCal("SHA-512",hashString);
              action1=base_url.concat("/_payment");
            }
          }
          else if(!empty(params.get("hash")))
          {
            hash=params.get("hash");
            action1=base_url.concat("/_payment");
          }
          params.put("hash", hash);
          params.put("udf2", txnid);
          return action1;
      }
      
    
}
