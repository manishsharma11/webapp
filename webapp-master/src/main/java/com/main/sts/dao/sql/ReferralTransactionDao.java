package com.main.sts.dao.sql;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.main.sts.entities.ReferralTransactionInfo;
import com.main.sts.entities.Booking;
import com.main.sts.entities.ReferralTransactionInfo;
import com.main.sts.entities.Trips;

@Repository
public class ReferralTransactionDao extends BaseDao {

    public void insert(ReferralTransactionInfo entity) {
        entity.setCreated_at(new Date());
        this.insertEntityAndGetId(entity);
    }
    
    public List<ReferralTransactionInfo> findAll() {
        return getAllRecords(ReferralTransactionInfo.class);
    }

    public ReferralTransactionInfo fetchAccount(int id) {
        String query = "from ReferralTransactionInfo b where b.id=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, id);
        return getSingleRecord(ReferralTransactionInfo.class, query, parameters);
    }

    public ReferralTransactionInfo fetchAccountByCommuterId(int commuter_id) {
        String query = "from ReferralTransactionInfo b where b.commuter_id=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, commuter_id);
        return getSingleRecord(ReferralTransactionInfo.class, query, parameters);
    }

    public boolean createAccount(ReferralTransactionInfo referralAccount) {
        referralAccount.setCreated_at(new Timestamp(new Date().getTime()));
        return insertEntity(referralAccount);
    }

    public ReferralTransactionInfo findTransactionById(int tx_id) {
        String query = "from ReferralTransactionInfo b where b.id=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, tx_id);
        return getSingleRecord(ReferralTransactionInfo.class, query, parameters);
    }

    public List<ReferralTransactionInfo> findTransactionByReferredBy(int referred_by, Integer offset, Integer limit) {
        System.out.println("finding transaction for:"+referred_by);
        if (limit == null) {
            limit = 10;
        }
        if (offset == null) {
            offset = 0;
        }
        String query = null;
        //if (limit != -1) {
        query = "from ReferralTransactionInfo b where b.referred_by=? order by created_at desc";
        //}
            System.out.println("query:"+query);
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, referred_by);
        return getRecords(ReferralTransactionInfo.class, query, parameters, offset, limit);
    }
    
    public List<ReferralTransactionInfo> findTransactionByReferredBy(int referred_by,
            boolean referred_to_redeemed_first_ride) {
        System.out.println("finding transaction for:" + referred_by);
        String query = null;
        query = "from ReferralTransactionInfo b where b.referred_by=? AND b.referred_to_redeemed_first_ride = ? order by created_at desc";
        System.out.println("query:" + query);
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, referred_by);
        parameters.put(1, referred_to_redeemed_first_ride);
        return getRecords(ReferralTransactionInfo.class, query, parameters);
    }

    public List<ReferralTransactionInfo> findTransactionByReferredBy(int referred_by) {
        System.out.println("finding transaction for:" + referred_by);

        String query = null;
        query = "from ReferralTransactionInfo b where b.referred_by=? order by created_at desc";
        System.out.println("query:" + query);
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, referred_by);
        return getRecords(ReferralTransactionInfo.class, query, parameters);
    }

//    public List<ReferralTransactionInfo> findTransactionByCommuterId(int commuter_id, Integer offset, Integer limit) {
//        return findTransactionByCommuterId(commuter_id, offset, 10);
//    }

    public ReferralTransactionInfo findLastTransactionByCommuterId(int commuter_id) {
        String query = "from ReferralTransactionInfo b where b.commuter_id=? order by created_at desc";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, commuter_id);
        return getSingleRecord(ReferralTransactionInfo.class, query, parameters, 1);
    }

}
