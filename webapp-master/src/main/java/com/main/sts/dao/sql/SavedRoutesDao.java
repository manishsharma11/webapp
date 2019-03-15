package com.main.sts.dao.sql;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.main.sts.entities.SavedRoutes;

@Repository
public class SavedRoutesDao extends BaseDao {

    public List<SavedRoutes> getAllSavedRoutes() {
        String query = "from SavedRoutes";
        return getRecords(SavedRoutes.class, query, null);
    }

    public List<SavedRoutes> getAllSavedRoutes(int commuter_id) {
        String query = "from SavedRoutes where commuter_id= ? order by created_at desc ";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, commuter_id);
        return getRecords(SavedRoutes.class, query, parameters);
    }

    public SavedRoutes getSavedRouteForACommuter(int commuter_id, int from_stop_id, int to_stop_id,
            Integer pickup_time_hours, Integer pickup_time_min, Integer dropoff_time_hours, Integer dropoff_time_min) {

        String query = "from SavedRoutes as b where b.commuter_id=? "
                + " AND b.from_stop_id=? AND b.to_stop_id=? ";
              //  + " AND b.pickup_time_hours=? AND b.pickup_time_min=? "
              //  + " AND b.dropoff_time_hours=? AND b.dropoff_time_min=? ";

        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, commuter_id);
        parameters.put(1, from_stop_id);
        parameters.put(2, to_stop_id);
       /* parameters.put(3, pickup_time_hours);
        parameters.put(4, pickup_time_min);
        parameters.put(5, dropoff_time_hours);
        parameters.put(6, dropoff_time_min); */
        return getSingleRecord(SavedRoutes.class, query, parameters);
    }

    public boolean saveARoute(SavedRoutes savedRoute) {
        savedRoute.setCreated_at(new Date());
        return this.insertEntity(savedRoute);
    }

}
