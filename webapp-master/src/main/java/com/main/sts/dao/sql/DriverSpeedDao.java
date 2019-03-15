package com.main.sts.dao.sql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.main.sts.entities.DriverSpeedEntity;

@Repository
public class DriverSpeedDao extends BaseDao {

    public void insert(DriverSpeedEntity driver) {
        insertEntity(driver);
    }

    public DriverSpeedEntity getDriver(int id) {
        String query = "from DriverSpeedEntity d where d.id=?";
        Map<Integer, Object> param = new HashMap<Integer, Object>();
        param.put(0, id);
        return getSingleRecord(DriverSpeedEntity.class, query, param);
    }

    public List<DriverSpeedEntity> getDriverSpeedByIDandDate(int parseInt, String string) {
        String query = "from DriverSpeedEntity d where d.driver_id=? and d.date=?";
        Map<Integer, Object> param = new HashMap<Integer, Object>();
        param.put(0, parseInt);
        param.put(1, string);
        return getRecords(DriverSpeedEntity.class, query, param);
    }

}
