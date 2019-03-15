package com.main.sts.dao.sql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.main.sts.entities.Buses;

@Repository
public class BusesDao extends BaseDao {

    public List<Buses> getAllrecords() {
        return getAllRecords(Buses.class);
    }

    public Buses getBus(String bus_licence_number) {
        String query = "from Buses b where b.bus_licence_number=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, bus_licence_number);
        return getSingleRecord(Buses.class, query, parameters);
    }

    public void insertBus(Buses busEntity) {
        insertEntity(busEntity);
    }

    public Buses getBusById(int bus_id) {
        String query = "from Buses b where b.bus_id=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, bus_id);
        return getSingleRecord(Buses.class, query, parameters);
    }

    public void updateBus(Buses busEntity) {
        updateEntity(busEntity);
    }

    public void deleteBus(Buses bus) {
        deleteEntity(bus);
    }

    public List<Buses> searchBuses(String str, String type) {
        Map<String, Object> restrictions = new HashMap<String, Object>();
        restrictions.put(type, "%" + str + "%");
        return searchRecords(Buses.class, restrictions);
    }

}
