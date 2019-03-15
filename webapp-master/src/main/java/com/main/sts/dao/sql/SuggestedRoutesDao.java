package com.main.sts.dao.sql;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.main.sts.entities.Buses;
import com.main.sts.entities.SuggestRoute;

@Repository
public class SuggestedRoutesDao extends BaseDao {

    public BigInteger getSuggestRouteCount() {
        String query = "select count(id) from suggest_routes";
        return getCountOFRecords(query);
    }

    public List<SuggestRoute> getAllSuggestRoutes() {
        String query = "from SuggestRoute";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        return getRecords(SuggestRoute.class, query, parameters);
    }
    
    public List<SuggestRoute> searchSuggestedRoutes(String str, String type) {
        Map<String, Object> restrictions = new HashMap<String, Object>();
        restrictions.put(type, "%" + str + "%");
        return searchRecords(SuggestRoute.class, restrictions);
    }

}