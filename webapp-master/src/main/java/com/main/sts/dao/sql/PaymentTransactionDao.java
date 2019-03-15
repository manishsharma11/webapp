package com.main.sts.dao.sql;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.main.sts.entities.PaymentTransaction;

@Repository
public class PaymentTransactionDao extends BaseDao {

    public List<PaymentTransaction> findAll() {
        return getAllRecords(PaymentTransaction.class);
    }

    public PaymentTransaction findTransactionById(int tx_id) {
        String query = "from PaymentTransaction b where b.id=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, tx_id);
        return getSingleRecord(PaymentTransaction.class, query, parameters);
    }

    public List<PaymentTransaction> findTransactionByCommuterId(int commuter_id, Integer offset, Integer limit) {
        System.out.println("finding transaction for:" + commuter_id);
        if (limit == null) {
            limit = 10;
        }
        if (offset == null) {
            offset = 0;
        }
        String query = null;
        // if (limit != -1) {
        query = "from PaymentTransaction b where b.commuter_id=? order by created_at desc";
        // }
        System.out.println("query:" + query);
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, commuter_id);
        return getRecords(PaymentTransaction.class, query, parameters, offset, limit);
    }

    public List<PaymentTransaction> findTransactionByCommuterId(int commuter_id) {
        System.out.println("finding transaction for:" + commuter_id);

        String query = null;
        // if (limit != -1) {
        query = "from PaymentTransaction b where b.commuter_id=? order by created_at desc";
        // }
        System.out.println("query:" + query);
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, commuter_id);
        return getRecords(PaymentTransaction.class, query, parameters);
    }

    public PaymentTransaction findLastTransactionByCommuterId(int commuter_id) {
        String query = "from PaymentTransaction b where b.commuter_id=? order by created_at desc";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, commuter_id);
        return getSingleRecord(PaymentTransaction.class, query, parameters, 1);
    }

    public Integer insertAPaymentTransaction(PaymentTransaction payment) {
        payment.setCreated_at(new Date());
        return (Integer) insertEntityAndGetId(payment);
    }

}
