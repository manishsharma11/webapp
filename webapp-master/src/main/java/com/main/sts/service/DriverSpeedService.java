package com.main.sts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.sts.dao.sql.DriverSpeedDao;
import com.main.sts.entities.DriverSpeedEntity;

@Service
public class DriverSpeedService {

    @Autowired
    private DriverSpeedDao driverdao;

    public void insertSpeed(DriverSpeedEntity driver) {
        driverdao.insert(driver);
    }

    public DriverSpeedEntity getdriver(int id) {
        return driverdao.getDriver(id);
    }

    public void updateDriverSpeed(DriverSpeedEntity des) {
        driverdao.updateEntity(des);
    }

    public List<DriverSpeedEntity> getDriverSpeedByIDandDate(int parseInt, String string) {
        return driverdao.getDriverSpeedByIDandDate(parseInt, string);
    }

}
