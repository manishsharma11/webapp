package com.main.sts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.sts.dao.sql.StopTypesDao;
import com.main.sts.entities.StopType;

@Service
public class StopTypesService {

    @Autowired
    private StopTypesDao stopTypesDao;

    @Autowired
    public List<StopType> getAllStopTypes() {
        return stopTypesDao.getStopTypes();
    }

    public void insertStop(StopType stop_type) {
        stopTypesDao.insert(stop_type);
    }

    public StopType getStopType(int id) {
        return stopTypesDao.getStopType(id);
    }

    public void deleteStop(int id) {
        StopType stop = getStopType(id);
        stopTypesDao.deleteStopType(stop);
    }

    public void deleteStopType(StopType stop_type) {
        stopTypesDao.deleteStopType(stop_type);
    }

    public StopType getStopTypeByName(String stop_type_name) {
        return stopTypesDao.getStopTypeByName(stop_type_name);
    }

    public void updateStopType(int id, StopType stop_type) {
        StopType stopType = getStopType(id);
        stopType.setStop_icon_path(stop_type.getStop_icon_path());
        stopType.setStop_type_name(stop_type.getStop_type_name());
        stopType.setEnabled(stop_type.getEnabled());
        stopTypesDao.updateStopType(stopType);
    }

    public void updateStopType(StopType stop_type) {
        stopTypesDao.updateStopType(stop_type);
    }

    public List<StopType> searchStopTypes(String type, String str) {
        return stopTypesDao.searchStopTypes(str, type);
    }

}
