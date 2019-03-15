package com.main.sts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.sts.dao.sql.UserPositionDao;
import com.main.sts.dto.UserGPSDataDTO;
import com.main.sts.entities.UserGpsData;

@Service("userTrackingService")
public class UserTrackingService {

    @Autowired
    private UserPositionDao userPosDao;

    public ReturnCodes storeUserPos(UserGPSDataDTO dto) {
        UserGpsData gpsData = new UserGpsData();
        gpsData.setUser_id(dto.getCommuter_id());
        gpsData.setGps_lat(dto.getGps_lat());
        gpsData.setGps_long(dto.getGps_long());

        boolean status = userPosDao.insert(gpsData);
        if (status) {
            return ReturnCodes.USER_POS_INSERT_SUCCESS;
        } else {
            return ReturnCodes.USER_POS_INSERT_FAILED;
        }
    }

}
