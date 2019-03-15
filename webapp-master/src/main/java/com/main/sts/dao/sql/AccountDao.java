package com.main.sts.dao.sql;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.main.sts.entities.Account;
import com.main.sts.entities.Booking;
import com.main.sts.entities.Commuter;
import com.main.sts.entities.Trips;

@Repository
public class AccountDao extends BaseDao {

    public List<Account> findAll() {
        return getAllRecords(Account.class);
    }

    public Account fetchAccount(int id) {
        String query = "from Account b where b.id=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, id);
        return getSingleRecord(Account.class, query, parameters);
    }

    public Account fetchAccountByCommuterId(int commuter_id) {
        String query = "from Account b where b.commuter_id=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, commuter_id);
        return getSingleRecord(Account.class, query, parameters);
    }

    public boolean createAccount(Account account) {
        account.setCreated_at(new Timestamp(new Date().getTime()));
        return insertEntity(account);
    }
    
    public int addCreditsToAccount(int commuter_id, int credit_points, int free_rides_added) {
        if (credit_points < 0) {
            throw new IllegalArgumentException("Points can not be less than zero:" + credit_points);
        }
        if (free_rides_added < 0) {
            throw new IllegalArgumentException("Free rides can not be less than zero:" + free_rides_added);
        }
//        String query = "update Account b SET b.credits_available = b.credits_available  + " + credit_points
//                + ", b.free_rides_available = b.free_rides_available  + " + free_rides_added
//                + " , b.updated_at = :updated_at where b.commuter_id = :commuter_id";
  
        String query = "update Account b SET b.credits_available = b.credits_available + %s "
                + " , b.free_rides_available = b.free_rides_available + %s"
                + " , b.updated_at = :updated_at where b.commuter_id = :commuter_id";
        
        query = String.format(query, credit_points, free_rides_added);

        System.out.println("query:" + query);
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("updated_at", new Date());
        parameters.put("commuter_id", commuter_id);

        int rowsAffected = updateEntities(Account.class, query, parameters);
        return rowsAffected;
    }

    // SQL injection
    public int deductCreditsFromAccount(int commuter_id, int credit_points, int free_rides_tobe_deducted) {
        if (credit_points < 0) {
            throw new IllegalArgumentException("Points can not be less than zero:" + credit_points);
        }
        if (free_rides_tobe_deducted < 0) {
            throw new IllegalArgumentException("Free rides can not be less than zero:" + free_rides_tobe_deducted);
        }
//        String query = "update Account b set b.credits_available = b.credits_available - " + credit_points
//                + ", b.free_rides_available = b.free_rides_available  - " + free_rides_tobe_deducted
//                + " where b.commuter_id = ?";
        
        String query = "update Account b set b.credits_available = b.credits_available - %s"
                + ", b.free_rides_available = b.free_rides_available  - %s "
                + ", b.updated_at = :updated_at  where b.commuter_id = :commuter_id";
        
        query = String.format(query, credit_points, free_rides_tobe_deducted);
        System.out.println("query:" + query);
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("updated_at", new Date());
        parameters.put("commuter_id", commuter_id);
        int rowsAffected = updateEntities(Account.class, query, parameters);
        return rowsAffected;
    }

    // deducting points
    // Making private so we will know this method's usage, as this method depreciates now.
    private boolean deductCreditsFromCommuterWallet(Session session, Booking booking, Trips trip) {
        // if there are 3 person booking under one booking, then this is
        // combined of all 3.
        int charged_points = booking.getCharged_fare();
        int commuter_id = booking.getCommuter_id();

        String query = "update Account b set b.credits_available = b.credits_available - " + charged_points
                + " where b.commuter_id = :commuter_id";
        System.out.println("query:" + query);
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("updated_at", new Date());
        parameters.put("commuter_id", commuter_id);

        int rowsAffected = updateEntities(session, Account.class, query, parameters);
        if (rowsAffected > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean deductCreditsAndORFreeRidesFromCommuterWallet(Session session, Booking booking, Trips trip) {
        // if there are 3 person booking under one booking, then this is
        // combined of all 3.
        int charged_points = booking.getCharged_fare();
        int charged_free_rides = booking.getCharged_free_rides();
        int commuter_id = booking.getCommuter_id();

        if (charged_points < 0) {
            throw new IllegalArgumentException("Points can not be less than zero:" + charged_points);
        }
        if (charged_free_rides < 0) {
            throw new IllegalArgumentException("Free rides can not be less than zero:" + charged_free_rides);
        }
        
//        String query = "update Account b set b.credits_available = b.credits_available - " + charged_points
//                + " where b.commuter_id = :commuter_id";
        
        String query = "update Account b set b.credits_available = b.credits_available - %s"
                + ", b.free_rides_available = b.free_rides_available - %s "
                + ", b.updated_at = :updated_at where b.commuter_id = :commuter_id";
        
        query = String.format(query, charged_points, charged_free_rides);
        System.out.println("query:" + query);
        
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("updated_at", new Date());
        parameters.put("commuter_id", commuter_id);

        int rowsAffected = updateEntities(session, Account.class, query, parameters);
        if (rowsAffected > 0) {
            return true;
        } else {
            return false;
        }
    }

    // adding(refund) points from the booking made.
    // Making private so we will know this method's usage, as this method depreciates now.
    private boolean refundCreditsToCommuterWallet(Session session, Booking booking, Trips trip) {
        // if there are 3 person booking under one booking, then this is
        // combined of all 3.
        int charged_points = booking.getCharged_fare();
        int commuter_id = booking.getCommuter_id();

        String query = "update Account b set b.credits_available = b.credits_available + " + charged_points
                + " where b.commuter_id = :commuter_id";
        System.out.println("query:" + query);
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("updated_at", new Date());
        parameters.put("commuter_id", commuter_id);
        
        int rowsAffected = updateEntities(session, Account.class, query, parameters);
        if (rowsAffected > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    // adding(refund) points from the booking made.
    public boolean refundCreditsAndORFreeRidesToCommuterWallet(Session session, Booking booking, Trips trip) {
        // if we charged fare or charged free rides, we will giving him to whole refund as a part of booking.
        int charged_points = booking.getCharged_fare();
        int charged_free_rides = booking.getCharged_free_rides();
        int commuter_id = booking.getCommuter_id();

//        String query = "update Account b set b.credits_available = b.credits_available + " + charged_points
//                + " where b.commuter_id = :commuter_id";
        
        String query = "update Account b set b.credits_available = b.credits_available + %s"
                + ", b.free_rides_available = b.free_rides_available  + %s "
                + ", b.updated_at = :updated_at  where b.commuter_id = :commuter_id";
        
        query = String.format(query, charged_points, charged_free_rides);
        System.out.println("query:" + query);

        System.out.println("query:" + query);
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("updated_at", new Date());
        parameters.put("commuter_id", commuter_id);

        int rowsAffected = updateEntities(session, Account.class, query, parameters);
        if (rowsAffected > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * For one month things
     * @param session
     * @param commuter
     * @return
     */
    public boolean cancelAllFreeRidesFromCommuterWallet(Session session, int commuter_id) {
        String query = "update Account b set b.free_rides_available = 0 "
                + ", b.updated_at = :updated_at where b.commuter_id = :commuter_id";

        query = String.format(query);
        System.out.println("query:" + query);

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("updated_at", new Date());
        parameters.put("commuter_id", commuter_id);

        int rowsAffected = updateEntities(session, Account.class, query, parameters);
        if (rowsAffected > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean updateAccountForOneMonthFreeRide(int commuter_id) {
        Account account = fetchAccountByCommuterId(commuter_id);
        account.setOne_month_free_activated(true);
        account.setOne_month_free_activated_at(new Date());
        return updateEntity(account);
    }
    
    public List<Account> findAllWhoDidntUseFreeRides() {
        String query = "from Account b where free_rides_available >0 order by b.created_at asc";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        return getRecords(Account.class, query, parameters);
    }
    
    public List<Account> findAllWhoRegisteredBefore15thJanAndDidntUseFreeRides() {
        String query = "from Account b where free_rides_available >0 ";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        return getRecords(Account.class, query, parameters);
    }

}
