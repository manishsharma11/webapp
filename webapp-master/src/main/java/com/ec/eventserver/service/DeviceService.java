package com.ec.eventserver.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.eventserver.dao.DeviceDao;
import com.ec.eventserver.dto.request.DeviceRequest;
import com.ec.eventserver.dto.request.DeviceResponse;
import com.ec.eventserver.models.DeviceInfo;
import com.main.sts.entities.Buses;
import com.main.sts.service.BusesService;

@Service("deviceService")
public class DeviceService {

    @Autowired
    private DeviceDao deviceDao;
    
    @Autowired BusesService vehicleService;

    public BigInteger getCountOFRecords(String query) {
        return deviceDao.getCountOFRecords(query);
    }

    public List<DeviceInfo> getRecordsByPagination(Integer offset, Integer limit) {
        List<DeviceInfo> devices = deviceDao.getRecordsByPagination(DeviceInfo.class, offset, limit);
        for (DeviceInfo device : devices) {
            Buses vehicle = vehicleService.getBusById(device.getVehicle_id());
            if (vehicle != null) {
                device.setVehicle_number(vehicle.getBus_licence_number());
            } else {
                device.setVehicle_number("NA");
            }
        }
        return devices;
    }
    
    public List<DeviceInfo> findAll() {
        return deviceDao.findAll();
    }

    public DeviceInfo getDeviceInfoByECDeviceId(String ec_device_id) {
        return deviceDao.getDeviceInfoByECDeviceId(ec_device_id);
    }

    public DeviceInfo getDeviceInfoByHWDeviceId(String hw_device_id) {
        return deviceDao.getDeviceInfoByHWDeviceId(hw_device_id);
    }

    public DeviceInfo getDeviceInfoByVehicleId(int vehicle_id) {
        return deviceDao.getDeviceInfoByVehicleId(vehicle_id);
    }

    public boolean updateADeviceInfo(String ec_device_id, int vehicle_id) {
        DeviceInfo deviceInfo = getDeviceInfoByECDeviceId(ec_device_id);
        deviceInfo.setVehicle_id(vehicle_id);
        return deviceDao.updateADeviceInfo(deviceInfo);
    }

    public boolean deleteDeviceInfo(String ec_device_id) {
        DeviceInfo deviceInfo = getDeviceInfoByECDeviceId(ec_device_id);
        return deviceDao.deleteDeviceInfo(deviceInfo);
    }

    public static void main(String[] args) {
        System.out.println(Long.toHexString(System.currentTimeMillis()));
    }

    public DeviceResponse insertDeviceInfo(DeviceRequest request) {
        DeviceInfo deviceInfo = new DeviceInfo();
        if (request.getHw_device_id() == null) {
            throw new IllegalArgumentException();
        }
        deviceInfo.setHw_device_id(request.getHw_device_id());
        DeviceInfo newVal = insertDeviceInfo(deviceInfo);

        DeviceResponse resp = new DeviceResponse();
        resp.setEc_device_id(newVal.getEc_device_id());
        resp.setHw_device_id(newVal.getHw_device_id());
        return resp;
    }

    public DeviceInfo insertDeviceInfo(DeviceInfo deviceInfo) {

        DeviceInfo existingDeviceInfo = getDeviceInfoByHWDeviceId(deviceInfo.getHw_device_id());
        // If we have already a HW device in system then return the same.
        if (existingDeviceInfo != null) {
            return existingDeviceInfo;
        }

        int maxtry = 20;
        int numtry = 0;

        String ec_device_id = generateUniqueCode(4);
        while (numtry <= maxtry) {
            existingDeviceInfo = getDeviceInfoByECDeviceId(ec_device_id);

            // this means, the ec_device_id already exist, I should create a new
            // one.
            if (existingDeviceInfo != null) {
                ec_device_id = generateUniqueCode(4);
            } else {
                break;
            }
            numtry++;
        }

        // till here, it should have generated a unique number. as there can be
        // 32 power 4 combination
        deviceInfo.setEc_device_id(ec_device_id);

        System.out.println("deviceInfo:" + deviceInfo);
        boolean status = deviceDao.insertADeviceInfo(deviceInfo);
        if (status) {
            return deviceInfo;
        } else {
            return null;
        }
    }

    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static Random rnd = new Random();

    public String generateUniqueCode(int len) {
        String prefix = "";
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return prefix + sb.toString();
    }

}
