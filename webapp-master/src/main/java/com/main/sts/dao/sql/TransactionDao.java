package com.main.sts.dao.sql;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.main.sts.common.CommonConstants.TransactionAction;
import com.main.sts.common.CommonConstants.TransactionBy;
import com.main.sts.common.CommonConstants.TransactionStatus;
import com.main.sts.common.CommonConstants.TransactionType;
import com.main.sts.entities.Booking;
import com.main.sts.entities.TransactionInfo;
import com.main.sts.util.LogUtil;

@Repository
public class TransactionDao extends BaseDao {

    
    @Autowired
    private AccountDao accountDao;
    
    
    public List<TransactionInfo> findAll() {
        return getAllRecords(TransactionInfo.class);
    }

    public TransactionInfo findTransactionById(int tx_id) {
        String query = "from TransactionInfo b where b.id=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, tx_id);
        return getSingleRecord(TransactionInfo.class, query, parameters);
    }

    public List<TransactionInfo> findTransactionByCommuterId(int commuter_id, Integer offset, Integer limit) {
        System.out.println("finding transaction for:"+commuter_id);
        if (limit == null) {
            limit = 10;
        }
        if (offset == null) {
            offset = 0;
        }
        String query = null;
        //if (limit != -1) {
        query = "from TransactionInfo b where b.commuter_id=? order by created_at desc";
        //}
            System.out.println("query:"+query);
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, commuter_id);
        return getRecords(TransactionInfo.class, query, parameters, offset, limit);
    }
    
    public List<TransactionInfo> findTransactionByCommuterId(int commuter_id) {
        System.out.println("finding transaction for:"+commuter_id);
        
        String query = null;
        //if (limit != -1) {
        query = "from TransactionInfo b where b.commuter_id=? order by created_at desc";
        //}
            System.out.println("query:"+query);
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, commuter_id);
        return getRecords(TransactionInfo.class, query, parameters);
    }

//    public List<TransactionInfo> findTransactionByCommuterId(int commuter_id, Integer offset, Integer limit) {
//        return findTransactionByCommuterId(commuter_id, offset, 10);
//    }

    public TransactionInfo findLastTransactionByCommuterId(int commuter_id) {
        String query = "from TransactionInfo b where b.commuter_id=? order by created_at desc";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, commuter_id);
        return getSingleRecord(TransactionInfo.class, query, parameters, 1);
    }

    public boolean insertARecharge(TransactionInfo recharge) {
        recharge.setCreated_at(new Timestamp(new Date().getTime()));
        return insertEntity(recharge);
    }

    // ideally in any case, the users, balance shouldnt be less than
    // zero.if in any case (due to marketing efforts), if we decide,
    // then it can be zero. for e.g. Allow users to book a ride, if
    // he
    // is short of 10credits,
    // like needs 30 credits and he have only 20 credits
    public Integer insertATransaction(TransactionInfo tx) {
        // last
        TransactionInfo lastTx = findLastTransactionByCommuterId(tx.getCommuter_id());
        Integer points_added = tx.getPoints_added();
        Integer points_deduced = tx.getPoints_deducted();
        
        Integer free_rides_added = tx.getFree_rides_added();
        Integer free_rides_deduced = tx.getFree_rides_deducted();

        // Adjusting credit points (credits) balance
        if (tx.getTransaction_type() == TransactionType.CREDIT.getTypeCode()) {
            if (lastTx != null) {
                tx.setPoints_balance(lastTx.getPoints_balance() + points_added);
            } else {
                tx.setPoints_balance(0 + points_added);
            }
        } else if (tx.getTransaction_type() == TransactionType.DEBIT.getTypeCode()) {
            if (lastTx != null) {
                tx.setPoints_balance(lastTx.getPoints_balance() - points_deduced);
            } else {
                tx.setPoints_balance(0 - points_deduced);
            }
        }
        
        // Adjusting free rides balance.
        if (tx.getTransaction_type() == TransactionType.CREDIT.getTypeCode()) {
            System.out.println("lastTx:"+lastTx);
            if (lastTx != null) {
                tx.setFree_rides_balance(lastTx.getFree_rides_balance() + free_rides_added);
            } else {
                tx.setFree_rides_balance(0 + free_rides_added);
            }
        } else if (tx.getTransaction_type() == TransactionType.DEBIT.getTypeCode()) {
            if (lastTx != null) {
                tx.setFree_rides_balance(lastTx.getFree_rides_balance() - free_rides_deduced);
            } else {
                // TODO: think about it more => I think, this should not be the case, as free rides cant be in negative
                // but credit can.
                //tx.setFree_rides_balance(0 - free_rides_deduced);
                // IDEALLY code(flow) should not reach here. 
                throw new IllegalArgumentException("it seems to be a BUG as its not possible to deduct free rides when user dont have any free rides balance available");
            }
        }
        
        //validateTransaction(tx);
        
        tx.setTransaction_action(tx.getTransaction_action());
        tx.setTransaction_desc(tx.getTransaction_desc());
        tx.setCreated_at(new Timestamp(new Date().getTime()));
        return (Integer) storeInDBAndGetId(tx);
    }
    
    private void validateTransaction(TransactionInfo tx){
        if (tx.getFree_rides_added() < 0) {
            throw new IllegalArgumentException("Free rides added cant be negative");
        }
        if (tx.getFree_rides_deducted() < 0) {
            throw new IllegalArgumentException("Free rides deducted cant be negative");
        }
        if (tx.getFree_rides_balance() < 0) {
            throw new IllegalArgumentException("Free rides balance cant be negative");
        }
    }
    
    public boolean insertUserPaymentTransactionHistory(Session session, Booking booking) {

        int commuter_id = booking.getCommuter_id();
        int charged_points = booking.getCharged_fare();
        int charged_free_rides = booking.getCharged_free_rides();
        
        TransactionStatus txStatus = TransactionStatus.SUCCESS;
        TransactionBy txBy = TransactionBy.USER;
        TransactionType txType = TransactionType.DEBIT;
        
        // last 
        TransactionInfo lastTx = findLastTransactionByCommuterId(commuter_id);

        TransactionInfo tx = new TransactionInfo();
        tx.setCommuter_id(commuter_id);
        System.out.println("charing points:"+charged_points);
        System.out.println("lastTx:"+lastTx);
        if (lastTx != null) {
            System.out.println("points balance:" + lastTx.getPoints_balance());
        }

        tx.setPoints_deducted(charged_points);
        tx.setFree_rides_deducted(charged_free_rides);
        
        // Adjusting credit points balance.
        if (lastTx != null) {
            tx.setPoints_balance(lastTx.getPoints_balance() - charged_points);
        } else {
            // ideally in any case, the users, balance shouldnt be less than
            // zero.if in any case (due to marketing efforts), if we decide,
            // then it can be zero. for e.g. Allow users to book a ride, if he
            // is short of 10credits,
            // like needs 30 credits and he have only 20 credits
            tx.setPoints_balance(0 - charged_points);
        }
        
        // Adjusting free rides balance.
        if (lastTx != null) {
            System.out.println("charged_free_rides:"+charged_free_rides+"--lastTx.getFree_rides_balance():"+lastTx.getFree_rides_balance());
            if (lastTx.getFree_rides_balance() != null) {
                tx.setFree_rides_balance(lastTx.getFree_rides_balance() - charged_free_rides);
            } else {
                // ideally it should never come here.
                //tx.setFree_rides_balance(0 - charged_free_rides);
                throw new IllegalArgumentException(
                        "it seems to be a BUG as its not possible to deduct free rides when user dont have any free rides balance available");
            }
        } else {
            // TODO: think about it more => I think, this should not be the
            // case, as free rides cant be in negative
            // but credit can.
            // tx.setFree_rides_balance(0 - charged_free_rides);
            // IDEALLY code(flow) should not reach here.
            throw new IllegalArgumentException(
                    "it seems to be a BUG as its not possible to deduct free rides when user dont have any free rides balance available");
            
        }
        
        //validateTransaction(tx);

        tx.setTransaction_by(txBy.getTypeCode());
        tx.setTransaction_status(txStatus.getTypeCode());
        tx.setTransaction_type(txType.getTypeCode());
        tx.setBooking_id(booking.getId());
        //tx.setTransaction_desc("Booking Done:" + booking.getId());
        tx.setTransaction_action(TransactionAction.BOOKING.getName());
        tx.setTransaction_desc(TransactionAction.BOOKING.getName());
        tx.setCreated_at(new Timestamp(new Date().getTime()));

        return storeInDB(session, tx);
    }
    
    public boolean insertCancelFreeRidesTransactionHistory(Session session, int commuter_id) {

        TransactionStatus txStatus = TransactionStatus.SUCCESS;
        TransactionBy txBy = TransactionBy.SYSTEM;
        TransactionType txType = TransactionType.DEBIT;

        // last
        TransactionInfo lastTx = findLastTransactionByCommuterId(commuter_id);

        TransactionInfo tx = new TransactionInfo();
        tx.setCommuter_id(commuter_id);
        System.out.println("lastTx:" + lastTx);
        if (lastTx != null) {
            System.out.println("points balance:" + lastTx.getPoints_balance());
        }

        tx.setPoints_deducted(0);

        if (lastTx != null) {
            // deducting all his free rides, so he can be upgraded to a bigger
            // offer.
            tx.setFree_rides_deducted(lastTx.getFree_rides_balance());
        }

        // Adjusting credit points balance. -- Giving his the same balance that he was holding.
        if (lastTx != null) {
            tx.setPoints_balance(lastTx.getPoints_balance());
        } else {
            tx.setPoints_balance(0);
        }

        // Adjusting free rides balance.
        tx.setFree_rides_balance(0);

        tx.setTransaction_by(txBy.getTypeCode());
        tx.setTransaction_status(txStatus.getTypeCode());
        tx.setTransaction_type(txType.getTypeCode());
        tx.setBooking_id(-1);
        tx.setTransaction_action(TransactionAction.UPGRADE_OFFER.getName());
        tx.setTransaction_desc("Upgraded to One Month free");
        tx.setCreated_at(new Timestamp(new Date().getTime()));

        return storeInDB(session, tx);
    }
    
    public Serializable insertUserFullRefundTransactionHistory(Session session, Booking booking) {

        int commuter_id = booking.getCommuter_id();
        int charged_points = booking.getCharged_fare();
        int charged_free_rides = booking.getCharged_free_rides();

        TransactionStatus txStatus = TransactionStatus.SUCCESS;
        TransactionBy txBy = TransactionBy.USER;
        TransactionType txType = TransactionType.CREDIT;

        // last 
        TransactionInfo lastTx = findLastTransactionByCommuterId(commuter_id);
        
        // TODO: Add booking_id field in this table.
        // Take these message from request as well. I mean admin wants to write a message for settlement.
        TransactionInfo tx = new TransactionInfo();
        tx.setCommuter_id(commuter_id);
        tx.setPoints_added(charged_points);
        tx.setFree_rides_added(charged_free_rides);
        
        if (lastTx != null) {
            tx.setPoints_balance(lastTx.getPoints_balance() + charged_points);
        } else {
            tx.setPoints_balance(0 + charged_points);
        }
        
        if (lastTx != null) {
            tx.setFree_rides_balance(lastTx.getFree_rides_balance() + charged_free_rides);
        } else {
            tx.setFree_rides_balance(0 + charged_free_rides);
        }
        
        //validateTransaction(tx);

        tx.setTransaction_by(txBy.getTypeCode());
        tx.setTransaction_status(txStatus.getTypeCode());
        tx.setTransaction_type(txType.getTypeCode());
        //tx.setTransaction_desc("Refund history Done:" + booking.getId());
        tx.setBooking_id(booking.getId());
        tx.setTransaction_action(TransactionAction.REFUND.getName());
        tx.setTransaction_desc(TransactionAction.REFUND.getName());
        tx.setCreated_at(new Timestamp(new Date().getTime()));

        Serializable id = storeInDBAndGetId(session, tx);
        return id;

    }
    
    // TODO: incomplete method
    public boolean insertUserPartialRefundTransactionHistory(Session session, Booking booking) {
        int commuter_id = booking.getCommuter_id();
        int charged_points = booking.getCharged_fare();
        int charged_free_rides = booking.getCharged_free_rides();

        TransactionStatus txStatus = TransactionStatus.SUCCESS;
        TransactionBy txBy = TransactionBy.USER;
        TransactionType txType = TransactionType.CREDIT;

        // last 
        TransactionInfo lastTx = findLastTransactionByCommuterId(commuter_id);
        
        // TODO: Add booking_id field in this table.
        // Take these message from request as well. I mean admin wants to write a message for settlement.
        TransactionInfo tx = new TransactionInfo();
        tx.setCommuter_id(commuter_id);
        tx.setPoints_added(charged_points);
        if (lastTx != null) {
            tx.setPoints_balance(lastTx.getPoints_balance() - charged_points);
        } else {
            tx.setPoints_balance(0 - charged_points);
        }
        
        if (lastTx != null) {
            tx.setFree_rides_balance(lastTx.getFree_rides_balance() + charged_free_rides);
        } else {
            tx.setFree_rides_balance(0 + charged_free_rides);
        }
        
        validateTransaction(tx);

        tx.setTransaction_by(txBy.getTypeCode());
        tx.setTransaction_status(txStatus.getTypeCode());
        tx.setTransaction_type(txType.getTypeCode());
        //tx.setTransaction_desc("Refund history Done:" + booking.getId());
        tx.setBooking_id(booking.getId());
        tx.setTransaction_action(TransactionAction.PARTIAL_REFUND.getName());
        tx.setTransaction_desc(TransactionAction.PARTIAL_REFUND.getName());
        tx.setCreated_at(new Timestamp(new Date().getTime()));

        return storeInDB(session, tx);
    }
    
    public boolean storeInDB(Session session, TransactionInfo tx) {
        Serializable id = storeInDBAndGetId(session, tx);
        if (id != null) {
            return true;
        } else {
            return false;
        }
    }
    
    public Serializable storeInDBAndGetId(Session session, TransactionInfo tx) {
        validateTransaction(tx);
        Serializable id = session.save(tx);
        return id;
    }
    
    public Serializable storeInDBAndGetId(TransactionInfo tx) {
        validateTransaction(tx);
        Serializable id = (Serializable) insertEntityAndGetId(tx);
        return id;
    }
    
    public boolean cancelFreeRides(int commuter_id) {
        synchronized (this) {
            Session session = null;
            Transaction tx = null;
            session = openSession();
            boolean status = true;
            try {
                tx = session.beginTransaction();

                LogUtil.logActivity("Cancelling free rides of ", commuter_id);

                if (status) {
                    LogUtil.logActivity("Creating a transaction history entry", commuter_id);

                    status = status && this.insertCancelFreeRidesTransactionHistory(session, commuter_id);
                    if (status) {
                        LogUtil.logActivity("Transaction history created success", commuter_id);
                    } else {
                        LogUtil.logActivity("Transaction history created failed", commuter_id);
                    }
                }

                if (status) {
                    LogUtil.logActivity("Updating user wallet balance", commuter_id);
                    // status = status &&
                    // accountDao.deductCreditsFromCommuterWallet(session,
                    // booking, trip);
                    status = status && accountDao.cancelAllFreeRidesFromCommuterWallet(session, commuter_id);
                    if (status) {
                        LogUtil.logActivity("Updating user wallet balance success", commuter_id);
                    } else {
                        LogUtil.logActivity("Updating user wallet balance failed", commuter_id);
                    }
                }

                if (status) {
                    tx.commit();
                } else {
                    tx.rollback();
                }
                return status;
            } catch (HibernateException e) {
                handleException(e, tx);
                throw e;
            } finally {
                tx = null;
                closeSession(session);
            }
        }
    }

}
