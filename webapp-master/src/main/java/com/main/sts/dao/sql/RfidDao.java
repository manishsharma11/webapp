package com.main.sts.dao.sql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.main.sts.entities.RfidCards;

@Repository
public class RfidDao extends BaseDao {

    public List<RfidCards> listRfids(String type) {
        String queryStr = "from RfidCards r where r.type = ?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, type);

        return (List<RfidCards>) getRecords(RfidCards.class, queryStr, parameters, 0);
    }

    public List<RfidCards> listAvailableRfids(String type, int rfid_status) {
        String queryStr = "from RfidCards r where r.type = ? and r.available = ?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, type);
        parameters.put(1, rfid_status);
        return (List<RfidCards>) getRecords(RfidCards.class, queryStr, parameters, 0);
    }

    public RfidCards getRfid(String rfid_number) {
        String queryStr = "from RfidCards u where u.rfid_number = ?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, rfid_number);
        return getSingleRecord(RfidCards.class, queryStr, parameters);
    }

    public RfidCards getRfid(int id) {
        String queryStr = "from RfidCards u where u.id = ?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, id);
        return getSingleRecord(RfidCards.class, queryStr, parameters);
    }

    public void insertRfid(RfidCards cards) {
        insertEntity(cards);
    }

    public void updateRfidNumber(String current_rfid, String new_rfid) {
        RfidCards rfidCards = getRfid(current_rfid);
        rfidCards.setRfid_number(new_rfid);
        updateEntity(rfidCards);
    }

    public void deleteRfid(int id) {
        String queryStr = "from RfidCards r where r.id = ?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, id);
        RfidCards cards = getSingleRecord(RfidCards.class, queryStr, parameters);
        deleteEntity(cards);
    }

    public List<RfidCards> searchRfids(String str, String type) {

        Map<String, Object> restrictions = new HashMap<String, Object>();
        restrictions.put("type", type);
        restrictions.put("rfid_number", "%" + str + "%");
        return searchRecords(RfidCards.class, restrictions);

    }

    public void updateRfid(RfidCards cards) {
        updateEntity(cards);
        /*
         * Session session = openSession(); Transaction tx = null; try { tx =
         * session.beginTransaction();
         * 
         * session.update(cards); tx.commit(); } catch (HibernateException e) {
         * if (tx != null) tx.rollback(); e.printStackTrace(); } finally {
         * session.close(); }
         */
    }
}
