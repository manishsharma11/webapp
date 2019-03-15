package com.main.sts.dao.sql;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.main.sts.entities.VehicleGpsData;

@Repository
public class VehiclePositionDao extends BaseDao {

    public boolean insert(VehicleGpsData gpsData) {
        gpsData.setEventTime(new Timestamp(new Date().getTime()));
        return super.insertEntity(gpsData);
    }

    public VehicleGpsData findPosition(int trip_id) {
        VehicleGpsData gpsPos = getVehicleGPS(trip_id);
        return gpsPos;
    }

    public VehicleGpsData getVehicleGPS(int trip_id) {
        String query = "from VehicleGpsData b where b.trip_id=? order by event_time desc, id desc";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, trip_id);
        return getSingleRecord(VehicleGpsData.class, query, parameters, 1);
    }

}
