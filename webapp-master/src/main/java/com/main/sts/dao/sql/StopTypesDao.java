package com.main.sts.dao.sql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.main.sts.entities.StopType;

@Repository
public class StopTypesDao extends BaseDao {

    public List<StopType> getStopTypes() {
        return getAllRecords(StopType.class);
    }

    public void insert(StopType stop) {
        insertEntity(stop);
    }

    public StopType getStopType(int id) {
        String query = "from StopType s where s.id=?";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        parameter.put(0, id);
        return getSingleRecord(StopType.class, query, parameter, true);
    }

    public StopType getStopTypeByName(String type_name) {
        String query = "from StopType s where s.type_name=?";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        parameter.put(0, type_name);
        return getSingleRecord(StopType.class, query, parameter, true);
    }

    public void deleteStopType(StopType stop_type) {
        deleteEntity(stop_type);
    }

    public void updateStopType(StopType stop_type) {
        updateEntity(stop_type);
    }

    public List<StopType> searchStopTypes(String str, String type) {
        Map<String, Object> restrictions = new HashMap<String, Object>();
        restrictions.put(type, "%" + str + "%");
        return searchRecords(StopType.class, restrictions);
    }

}
