package com.main.sts.dao.sql;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.main.sts.entities.UserGpsData;

@Repository
public class UserPositionDao extends BaseDao {

    public boolean insert(UserGpsData gpsData) {
        gpsData.setCreated_at(new Timestamp(new Date().getTime()));
        return super.insertEntity(gpsData);
    }

    public UserGpsData getUserGPS(int commuter_id) {
        String query = "from UserGpsData b where b.user_id=? AND gps_lat!=null order by created_at desc, id desc";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, commuter_id);
        return getSingleRecord(UserGpsData.class, query, parameters, 1);
    }
}
