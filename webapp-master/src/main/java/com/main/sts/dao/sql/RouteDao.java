package com.main.sts.dao.sql;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.main.sts.entities.Routes;
import com.main.sts.entities.SuggestRoute;

@Repository
public class RouteDao extends BaseDao {

    public List<Routes> getRoutes() {
        return getAllRecords(Routes.class);
    }

    public List<Routes> getRoutes(boolean onlyEnabledRoutes) {
        if (onlyEnabledRoutes) {
            String query = "from Routes s where s.enabled=?";
            Map<Integer, Object> parameter = new HashMap<Integer, Object>();
            parameter.put(0, onlyEnabledRoutes);
            return getRecords(Routes.class, query, parameter, true);
        } else {
            return getRoutes();
        }
    }

    public void insert(Routes route) {
        insertEntity(route);
    }

    public Routes getRoute(int id) {
        String query = "from Routes s where s.id=?";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        parameter.put(0, id);
        return getSingleRecord(Routes.class, query, parameter);
    }

    public Routes getRouteByName(String name) {
        String query = "from Routes s where s.route_name=?";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        parameter.put(0, name);
        return getSingleRecord(Routes.class, query, parameter);
    }

    public void deleteRoute(Routes route) {
        deleteEntity(route);
    }

    public void updateRoute(Routes route) {
        updateEntity(route);
    }

    public List<Routes> getRouteByType(String name) {
        String query = "from Routes s where s.route_type=?";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        parameter.put(0, name);
        return getRecords(Routes.class, query, parameter);
    }

    public List<Routes> searchRoutes(String str, String type) {
        Map<String, Object> restrictions = new HashMap<String, Object>();
        restrictions.put(type, "%" + str + "%");
        return searchRecords(Routes.class, restrictions);
    }

    public boolean addASuggestedRoute(SuggestRoute suggestedRoute) {
        suggestedRoute.setCreated_at(new Timestamp(new Date().getTime()));
        return insertEntity(suggestedRoute);
    }
}
