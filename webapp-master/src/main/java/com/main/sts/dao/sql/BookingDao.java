package com.main.sts.dao.sql;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.main.sts.common.CommonConstants;
import com.main.sts.common.CommonConstants.BookingStatus;
import com.main.sts.common.CommonConstants.TransactionAction;
import com.main.sts.common.ServiceException;
import com.main.sts.entities.Booking;
import com.main.sts.entities.BookingWebDTO;
import com.main.sts.entities.RouteStops;
import com.main.sts.entities.Trips;
import com.main.sts.service.ReturnCodes;
import com.main.sts.util.DateUtil;
import com.main.sts.util.LogUtil;

@Repository
public class BookingDao extends BaseDao {

    @Autowired
    private TransactionDao txDao;
    
    @Autowired
    private AccountDao accountDao;
    
    @Autowired
    private TripDao tripDao;
   
    
    public List<Booking> findAll() {
      String query = "from Booking b order by booking_time desc";
        return getRecords(Booking.class, query, null);
    }
    
    public List<BookingWebDTO> getBookingWithCommuterDetails() {
        return getBookingWithCommuterDetails(null);
    }
    
    public List<BookingWebDTO> getBookingWithCommuterDetails(Integer tripID){ 
        Date rangeEndDate = DateUtil.getTodayDateWithOnlyDate();
        String query = "";
       
        query = "SELECT b.id AS booking_id, b.status, b.booking_time, "
              + "b.booking_travel_date, b.num_seats_booked, b.actual_fare, b.charged_fare, b.charged_free_rides, "
              + "b.booking_expirary_date, b.booking_cancellation_date, b.message_sent_for_pickup, b.message_sent_for_dropoff, "
              + "c.id AS commuter_id, c.name, c.mobile, c.gender, "
              + "fs.id AS from_stop_id, ts.id AS to_stop_id, fs.stop_name AS from_stop_name, ts.stop_name AS to_stop_name, "
              + "t.id AS trip_id, "
              + "td.trip_name, td.trip_type , td.busid "
              + "FROM booking AS b "
              + "LEFT OUTER JOIN commuters AS c ON c.id =  b.commuter_id "
              + "LEFT OUTER JOIN trips AS t ON t.id = b.trip_id "
              + "LEFT OUTER JOIN trip_details AS td ON t.trip_detail_id = td.id "
              + "LEFT OUTER JOIN stops AS fs ON fs.id = b.from_stop "
              + "LEFT OUTER JOIN stops AS ts ON ts.id = b.to_stop ";
        
        boolean conditionRequired = false;
        
        if (conditionRequired) {

            query = query + "WHERE ";

            if (tripID != null) {
                query = query + "t.id = " + tripID;
            }

            query = query + " AND booking_travel_date >= '" + rangeEndDate + "' ";
        }
        
        query = query + "ORDER BY t.id, b.status, booking_travel_date asc";
        List<Object[]> list = executeSQLQuery(query, false);
        List<BookingWebDTO> bookings = new ArrayList<BookingWebDTO>();
        System.out.println("objects:"+list);
        for (Object[] objects : list) {
            Object[] arr = objects;
            System.out.println("arr:"+arr);
            BookingWebDTO b = new BookingWebDTO();
            int cnt=0;
            //booking
            b.id = (Integer)arr[cnt++];
            b.status = (Integer)arr[cnt++];
            b.booking_time = (Date)arr[cnt++];
            b.booking_travel_date = (Date)arr[cnt++];
            b.num_seats_booked = (Integer)arr[cnt++];
            b.actual_fare = (Integer)arr[cnt++];
            b.charged_fare = (Integer)arr[cnt++];
            b.charged_free_rides = (Integer)arr[cnt++];

            b.booking_expirary_date = (Date)arr[cnt++];
            b.booking_cancellation_date = (Date)arr[cnt++];
            b.message_sent_for_pickup = (Boolean)arr[cnt++];
            b.message_sent_for_dropoff = (Boolean)arr[cnt++];
            
            Integer cid = (Integer) arr[cnt++];
            if (cid == null) {
                continue;
            }
            // commuter
            b.commuter_id = cid;
            b.commuter_name = (String)arr[cnt++];
            b.mobile = (String)arr[cnt++];
            b.gender = Character.toString((Character)arr[cnt++]);

            // stops
            b.from_stop = (Integer)arr[cnt++];
            b.to_stop = (Integer)arr[cnt++];
            b.from_stop_name = (String)arr[cnt++];
            b.to_stop_name = (String)arr[cnt++];
            
            // trip name
            b.trip_id = (Integer)arr[cnt++];
            b.trip_name = (String)arr[cnt++];
            b.trip_type = (String)arr[cnt++];
            
            // vehicle
            b.bus_id = (Integer)arr[cnt++];

            bookings.add(b);
        }
        System.out.println("bookings:"+bookings);
        return bookings;
    }
    
    
    public Booking findBookingById(int booking_id) {
        String query = "from Booking b where b.id=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, booking_id);
        return getSingleRecord(Booking.class, query, parameters);
    }

    public List<Booking> findBookingByCommuterId(int commuter_id) {
        String query = "from Booking b where b.commuter_id=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, commuter_id);
        return getRecords(Booking.class, query, parameters);
    }
    
    public List<Booking> findBookingByTripId(int trip_id) {
        String query = "from Booking b where b.trip_id=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, trip_id);
        return getRecords(Booking.class, query, parameters);
    }

    public List<Booking> findBookingByTripId(int trip_id, List<Integer> bookingStatusesAsofNow) {
        String query = "from Booking b where b.trip_id = (:trip_id) AND b.status IN (:ids)";
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("trip_id", trip_id);
        parameters.put("ids", bookingStatusesAsofNow);
        return getRecordsListWithNamedQuery(Booking.class, query, parameters, -1, false);
    }
    
    public List<Booking> findBookingByTripIdAndDropOffStopId(int trip_id, int dropoff_stop_id,
            List<Integer> bookingStatusesAsofNow) {
        String query = "from Booking b where b.trip_id = (:trip_id) AND b.to_stop = (:to_stop) AND b.status IN (:ids)";
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("trip_id", trip_id);
        parameters.put("to_stop", dropoff_stop_id);
        parameters.put("ids", bookingStatusesAsofNow);
        return getRecordsListWithNamedQuery(Booking.class, query, parameters, -1, false);
    }
    
    public Booking findLastBookingByCommuterId(int commuter_id) {
        String query = "from Booking b where b.commuter_id=? order by booking_time desc";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, commuter_id);
        return getSingleRecord(Booking.class, query, parameters);
    }

    public List<Booking> findBookingByDateAndTime(Date date, Date time, BookingStatus bookingStatus) {
        String query = "from Booking b where b.booking_travel_date=? AND b.booking_travel_time < ? ";
        if (bookingStatus != null) {
            query = query + " AND b.status=? ";
        }
        System.out.println("Firing query:" + query);
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, date);
        parameters.put(1, time);
        if (bookingStatus != null) {
            parameters.put(2, bookingStatus.getBookingStatus());
        }
        return getRecords(Booking.class, query, parameters);
    }

    public List<Booking> findBookingByDate(Date date) {
        String query = "from Booking b where b.booking_travel_date=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, date);
        return getRecords(Booking.class, query, parameters);
    }

    public boolean updateBookingStatus(int booking_id, int bookingStatus) {
        String query = "update Booking b set b.status = ? where b.id = ?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, bookingStatus);
        parameters.put(1, booking_id);
        //return getSingleRecord(Booking.class, query, parameters);
        
        int rowsAffected = updateEntitys(Booking.class, query, parameters);
        if (rowsAffected > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    // Message sent to use for notifying that your bus is reaching to you shortly.
    // so he 
    public boolean updateBookingMessageSentForPickup(int booking_id, boolean message_sent_for_pickup) {
        String query = "update Booking b set b.message_sent_for_pickup = ? where b.id = ?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, message_sent_for_pickup);
        parameters.put(1, booking_id);
        //return getSingleRecord(Booking.class, query, parameters);
        
        int rowsAffected = updateEntitys(Booking.class, query, parameters);
        if (rowsAffected > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean updateBookingMessageSentForDropoff(int booking_id, boolean message_sent_for_dropoff) {
        String query = "update Booking b set b.message_sent_for_dropoff = ? where b.id = ?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, message_sent_for_dropoff);
        parameters.put(1, booking_id);
        //return getSingleRecord(Booking.class, query, parameters);
        
        int rowsAffected = updateEntitys(Booking.class, query, parameters);
        if (rowsAffected > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean updateBookingStatusByTripId(int trip_id, int bookingStatus) {
        String query = "update Booking b set b.status = ? where b.trip_id = ?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, bookingStatus);
        parameters.put(1, trip_id);
        //return getRecords(Booking.class, query, parameters);
        
        int rowsAffected = updateEntitys(Booking.class, query, parameters);
        if (rowsAffected > 0) {
            return true;
        } else {
            return false;
        }
    }
    
//    public List<RouteStops> getAllRouteStopsForRoute(List<Integer> selectedRoutes) {
//        String queryStr = "from RouteStops r where r.route_id IN (:ids) ";
//        Map<String, Object> parameters = new HashMap<String, Object>();
//        parameters.put("ids", selectedRoutes);
//        return getRecordsListWithNamedQuery(RouteStops.class, queryStr, parameters, -1, true);
//    }
    
    public boolean updateBookingStatusAsExpiredForActiveBookingsByBookingId(int booking_id,
            BookingStatus bookingStatusToBeUpdated) {
        String query = "update Booking b set b.status = (:status), b.booking_expirary_date = (:booking_expirary_date) where b.id = (:booking_id)";
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("status", bookingStatusToBeUpdated.getBookingStatus());
        parameters.put("booking_id", booking_id);
        parameters.put("booking_expirary_date", new Date());
        // return getSingleRecordWithNamedQuery(Booking.class, query,
        // parameters, false);
        int rowsAffected = updateEntities(Booking.class, query, parameters);
        if (rowsAffected > 0) {
            return true;
        } else {
            return false;
        }
    }

    // updating booking status, meant to be part of a Atomic Transaction
    public boolean updateBookingStatus(Session session, int booking_id, int bookingStatus) {
        
        boolean cancellationStatus = ((bookingStatus == BookingStatus.CANCELLED_NOREFUND.intValue())
                || (bookingStatus == BookingStatus.CANCELLED_PARTIAL_REFUNDED.intValue()) || (bookingStatus == BookingStatus.CANCELLED_REFUNDED
                .intValue()));

        String query = null;
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("bookingStatus", bookingStatus);
        parameters.put("id", booking_id);
        
        if (cancellationStatus) {
            query = "update Booking b set b.status = :bookingStatus, b.booking_cancellation_date = :booking_cancellation_date where b.id = :id";
            System.out.println("query:" + query);
            parameters.put("booking_cancellation_date", new Date());
        } else {
            query = "update Booking b set b.status = :bookingStatus where b.id = :id";
            System.out.println("query:" + query);
        }

        int rowsAffected = updateEntities(session, Booking.class, query, parameters);
        if (rowsAffected > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public Serializable bookARide(Booking booking, Trips trip) {
        synchronized (this) {

            Session session = null;
            Transaction tx = null;
            session = openSession();
            boolean status = true;
            try {
                tx = session.beginTransaction();

                int commuter_id = booking.getCommuter_id();
                LogUtil.logActivity("Inserting a booking ", commuter_id);
                // inserting a booking
                Serializable booking_id = session.save(booking);

                LogUtil.logActivity("Booking inserted ", commuter_id);

                // updating trip seats filled
                if (status) {
                    LogUtil.logActivity("Updating trip seats filled", commuter_id);
                    status = status && tripDao.occupyTripsSeats(session, booking, trip);
                    if (status) {
                        LogUtil.logActivity("Updating trip seats filled success", commuter_id);
                    } else {
                        LogUtil.logActivity("Updating trip seats filled failed", commuter_id);
                    }
                }

                if (status) {
                    LogUtil.logActivity("Creating a transaction history entry", commuter_id);

                    status = status && txDao.insertUserPaymentTransactionHistory(session, booking);
                    if (status) {
                        LogUtil.logActivity("Transaction history created success", commuter_id);
                    } else {
                        LogUtil.logActivity("Transaction history created failed", commuter_id);
                    }
                }

                if (status) {
                    LogUtil.logActivity("Updating user wallet balance", commuter_id);
                    //status = status && accountDao.deductCreditsFromCommuterWallet(session, booking, trip);
                    status = status && accountDao.deductCreditsAndORFreeRidesFromCommuterWallet(session, booking, trip);
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
                return booking_id;
            } catch (HibernateException e) {
                handleException(e, tx);
                throw e;
            } finally {
                tx = null;
                closeSession(session);
            }
        }
    }
    
    // public Serializable cancelARide(int booking_id, boolean toBeRefunded, int
    // deductionPercentage) {
    //
    // }
    
    @Transactional
    public Integer cancelARideWithFullRefund(Booking booking, boolean toBeRefunded, int pointsDeducted) throws ServiceException {
        Trips trip = tripDao.getTrip(booking.getTrip_id());

        synchronized (this) {

            Session session = null;
            Transaction tx = null;
            session = openSession();
            boolean status = true;
            Integer txId =  null;
            try {
                tx = session.beginTransaction();

                int commuter_id = booking.getCommuter_id();
                LogUtil.logActivity("Inserting a booking ", commuter_id);
               
                // updating booking status
                status = updateBookingStatus(session, booking.getId(), CommonConstants.BookingStatus.CANCELLED_REFUNDED.getBookingStatus());

                LogUtil.logActivity("Booking status updated ", commuter_id);

                // updating trip seats vacating
                if (status) {
                    LogUtil.logActivity("Updating trip seats vacating", commuter_id);
                    status = status && tripDao.vacateTripsSeats(session, booking, trip);
                    if (status) {
                        LogUtil.logActivity("Updating trip seats vacating success", commuter_id);
                    } else {
                        LogUtil.logActivity("Updating trip seats vacating failed", commuter_id);
                    }
                }

                if (status) {
                    LogUtil.logActivity("Creating a transaction history entry", commuter_id);

                    txId = (Integer) txDao.insertUserFullRefundTransactionHistory(session, booking);
                    if (txId != null) {
                        status = status && true;
                    } else {
                        status = status && false;
                    }
                    if (status) {
                        LogUtil.logActivity("Transaction history created success", commuter_id);
                    } else {
                        LogUtil.logActivity("Transaction history created failed", commuter_id);
                    }
                }

                if (status) {
                    LogUtil.logActivity("Updating user wallet balance", commuter_id);
//                    status = status && accountDao.refundCreditsToCommuterWallet(session, booking, trip);
                    status = status && accountDao.refundCreditsAndORFreeRidesToCommuterWallet(session, booking, trip);
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
                return txId;
            } catch (HibernateException e) {
                // rolling back if any transaction is running.
                handleException(e, tx);
                throw e;
            } finally {
                tx = null;
                closeSession(session);
            }
        }
    }

//    // updating the seats filled.
//    public boolean updateTripsSeats(Session session, Booking booking, Trips trip) {
//        String query = "update Trips b set b.seats_filled = b.seats_filled + " + booking.getNum_seats_booked()
//                + " where b.id = :id";
//        System.out.println("query:" + query);
//        Map<String, Object> parameters = new HashMap<String, Object>();
//        parameters.put("id", trip.getId());
//        int tripRowsUpdated = updateEntities(session, Trips.class, query, parameters);
//        if (tripRowsUpdated > 0) {
//            return true;
//        } else {
//            return false;
//        }
//    }

//    // deducting points
//    public boolean updateCommueterWalletBalance(Session session, Booking booking, Trips trip) {
//        // if there are 3 person booking under one booking, then this is
//        // combined of all 3.
//        int charged_points = booking.getCharged_fare();
//        int commuter_id = booking.getCommuter_id();
//
//        String query = "update Account b set b.credits_available = b.credits_available - " + charged_points
//                + " where b.commuter_id = :commuter_id";
//        System.out.println("query:" + query);
//        Map<String, Object> parameters = new HashMap<String, Object>();
//        parameters.put("commuter_id", commuter_id);
//
//        int rowsAffected = updateEntities(session, Account.class, query, parameters);
//        if (rowsAffected > 0) {
//            return true;
//        } else {
//            return false;
//        }
//    }

//    public boolean createTransactionHistory(Session session, Booking booking) {
//
//        int commuter_id = booking.getCommuter_id();
//        int charged_points = booking.getCharged_fare();
//        TransactionStatus txStatus = TransactionStatus.SUCCESS;
//        TransactionBy txBy = TransactionBy.USER;
//        TransactionType txType = TransactionType.DEBIT;
//
//        TransactionInfo tx = new TransactionInfo();
//        tx.setCommuter_id(commuter_id);
//        tx.setPoints(charged_points);
//        tx.setTransaction_by(txBy.getTypeCode());
//        tx.setTransaction_status(txStatus.getTypeCode());
//        tx.setTransaction_type(txType.getTypeCode());
//        tx.setTransaction_desc("Booking Done:" + booking.getId());
//
//        Serializable id = session.save(tx);
//        if (id != null) {
//            return true;
//        } else {
//            return false;
//        }
//    }
}
