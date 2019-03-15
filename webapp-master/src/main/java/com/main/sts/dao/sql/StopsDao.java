package com.main.sts.dao.sql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.main.sts.entities.Stops;

@Repository
public class StopsDao extends BaseDao {

    public List<Stops> getStops() {
        return getAllRecords(Stops.class);
    }

    public void insert(Stops stop) {
        insertEntity(stop);
    }

    public Stops getStop(int id) {
        String query = "from Stops s where s.id=?";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        parameter.put(0, id);
        return getSingleRecord(Stops.class, query, parameter, true);
    }

    public Stops getStopByName(String name) {
        String query = "from Stops s where s.stop_name=?";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        parameter.put(0, name);
        return getSingleRecord(Stops.class, query, parameter, true);
    }

    public void deleteStop(Stops stop) {
        deleteEntity(stop);
    }

    public void updateStop(Stops stop) {
        updateEntity(stop);
    }

    public Stops getStop(String latitude, String longitude) {
        String query = "from Stops s where s.latitude=? and s.longitude=?";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        parameter.put(0, latitude);
        parameter.put(1, longitude);
        return getSingleRecord(Stops.class, query, parameter, true);
    }

    public List<Stops> searchStops(String str, String type) {
        Map<String, Object> restrictions = new HashMap<String, Object>();
        restrictions.put(type, "%" + str + "%");
        return searchRecords(Stops.class, restrictions);
    }

}
