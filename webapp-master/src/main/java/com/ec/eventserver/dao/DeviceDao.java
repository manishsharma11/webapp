package com.ec.eventserver.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ec.eventserver.models.DeviceInfo;
import com.main.sts.dao.sql.BaseDao;

@Repository
public class DeviceDao extends BaseDao {

    public List<DeviceInfo> findAll() {
        return getAllRecords(DeviceInfo.class);
    }

    // it will return the ec_device_id as return value.
    public boolean insertADeviceInfo(DeviceInfo device_info) {
        device_info.setCreated_at(new Timestamp(new Date().getTime()));
        return insertEntity(device_info);
    }

    public boolean updateADeviceInfo(DeviceInfo device_info) {
        device_info.setUpdated_at(new Timestamp(new Date().getTime()));
        return updateEntity(device_info);
    }

    public boolean deleteDeviceInfo(DeviceInfo device_info) {
        return deleteEntity(device_info);
    }

    public DeviceInfo getDeviceInfoByECDeviceId(String ec_device_id) {
        // // order by created_at desc
        String query = "from DeviceInfo b where b.ec_device_id=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, ec_device_id);
        return getSingleRecord(DeviceInfo.class, query, parameters);
    }

    public DeviceInfo getDeviceInfoByHWDeviceId(String hw_device_id) {
        // // order by created_at desc
        String query = "from DeviceInfo b where b.hw_device_id=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, hw_device_id);
        return getSingleRecord(DeviceInfo.class, query, parameters);
    }

    public DeviceInfo getDeviceInfoByVehicleId(int vehicle_id) {
        // // order by created_at desc
        String query = "from DeviceInfo b where b.vehicle_id=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, vehicle_id);
        return getSingleRecord(DeviceInfo.class, query, parameters);
    }

}
