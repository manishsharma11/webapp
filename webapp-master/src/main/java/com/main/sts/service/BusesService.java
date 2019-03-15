package com.main.sts.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.sts.dao.sql.BusesDao;
import com.main.sts.entities.Buses;

@Service
public class BusesService {

    @Autowired
    private BusesDao busesDao;

    public List<Buses> getBuses() {
        return busesDao.getAllrecords();
    }

    public Buses getBusByLicence(String bus_licence_number) {
        return busesDao.getBus(bus_licence_number);
    }

    public void insertBus(Buses busEntity) {
        busEntity.setBus_allotted(0);
        busesDao.insertBus(busEntity);
    }

    public Buses getBusById(int bus_id) {
        return busesDao.getBusById(bus_id);
    }

    public void updateBus(Buses busEntity) {
        // busEntity.setBus_allotted(busEntity.getBus_allotted());
        busesDao.updateBus(busEntity);
    }

    public void deleteBus(int busId) {
        Buses bus = getBusById(busId);
        busesDao.deleteBus(bus);
    }

    public void updateBusStatus(Buses busEntity, int status) {
        busEntity.setBus_allotted(status);
        busesDao.updateBus(busEntity);
    }
    public List<Buses> searchBuses(String type, String str) {
        return busesDao.searchBuses(str, type);
    }

    public Buses getBusByDriverId(int id) {
        String queryStr = "from Buses b where b.driverId=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, id);
        return busesDao.getSingleRecord(Buses.class, queryStr, parameters);
    }
}
