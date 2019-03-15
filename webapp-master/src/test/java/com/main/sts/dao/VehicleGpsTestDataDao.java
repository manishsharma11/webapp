package com.main.sts.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.main.sts.dao.sql.BaseDao;
import com.main.sts.entities.UserGpsData;

// preparing test data dao, so I can query the already existing test_bus_gps_data table to simulate the event tracking
@Repository
public class VehicleGpsTestDataDao extends BaseDao {

    public boolean insert(VehicleGpsTestData gpsData) {
        gpsData.setCreated_at(new Timestamp(new Date().getTime()));
        return super.insertEntity(gpsData);
    }

    public boolean insert(UserGpsData gpsData) {
        gpsData.setCreated_at(new Timestamp(new Date().getTime()));
        return super.insertEntity(gpsData);
    }

    public VehicleGpsTestData findPosition(int trip_id) {
        VehicleGpsTestData gpsPos = getVehicleGPS(trip_id);
        return gpsPos;
    }

    public VehicleGpsTestData getVehicleGPS(int trip_id) {
        String query = "from VehicleGpsTestData b where b.trip_id=? order by created_at desc, id desc";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, trip_id);
        return getSingleRecord(VehicleGpsTestData.class, query, parameters, 1);
    }

    public UserGpsData getUserGPS(int commuter_id) {
        String query = "from UserGpsData b where b.user_id=? AND gps_lat!=null order by created_at desc, id desc";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, commuter_id);
        return getSingleRecord(UserGpsData.class, query, parameters, 1);
    }
}
