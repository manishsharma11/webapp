package com.main.sts.dao.sql;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.main.sts.entities.BusFarePriceEntity;

@Repository
public class FareDao extends BaseDao {

    public List<BusFarePriceEntity> findAll() {
        return getAllRecords(BusFarePriceEntity.class);
    }

    public BusFarePriceEntity getFareById(int fare_id) {
        String queryStr = "from BusFarePriceEntity r where r.fare_id =?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, fare_id);
        return getSingleRecord(BusFarePriceEntity.class, queryStr, parameters, 1);
    }

    public void insertFare(BusFarePriceEntity e) {
//        if (e.getSource_stop_id() == e.getDest_stop_id()) {
//            throw new IllegalArgumentException("Source and dest stops cant be same");
//        }
        e.setCreated_at(new Date());
        this.insertEntity(e);
    }

    public void updateFare(BusFarePriceEntity e) {
        e.setUpdated_at(new Date());
        this.updateEntity(e);
    }

    public void deleteFare(BusFarePriceEntity e) {
        this.deleteEntity(e);
    }

    public void deleteFare(int fare_id) {
        BusFarePriceEntity e = getFareById(fare_id);
        this.deleteFare(e);
    }

    public BusFarePriceEntity fetchFare(int source_stop_id, int dest_stop_id) {
        String query = "from BusFarePriceEntity b where b.source_stop_id=? AND b.dest_stop_id=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, source_stop_id);
        parameters.put(1, dest_stop_id);
        return getSingleRecord(BusFarePriceEntity.class, query, parameters, true);
    }

    // basically it check the presence of source_stop_id and dest_stop_id in
    // both columns and gives only the results those are having common due to
    // AND condition between columns. In some cases, where we have two entries
    // defined in db (due to mistakes), it will take only one.
    public BusFarePriceEntity fetchFareMatchAny(int source_stop_id, int dest_stop_id) {
        String query = "from BusFarePriceEntity b where b.source_stop_id in (?,?) AND b.dest_stop_id in (?,?) order by created_at desc";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, source_stop_id);
        parameters.put(1, dest_stop_id);
        parameters.put(2, source_stop_id);
        parameters.put(3, dest_stop_id);
        return getSingleRecord(BusFarePriceEntity.class, query, parameters, 1);
    }

    // public BusFarePriceEntity fetchFroFare(int source_stop_id, int
    // dest_stop_id) {
    // return fetchToFare(source_stop_id, dest_stop_id);
    // }

}
