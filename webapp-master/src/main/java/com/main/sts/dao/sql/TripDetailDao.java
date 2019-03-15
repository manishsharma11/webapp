package com.main.sts.dao.sql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.main.sts.entities.TripDetail;

@Repository
public class TripDetailDao extends BaseDao {

    public List<TripDetail> getTripDetails() {
        return getAllRecords(TripDetail.class);
    }

    public void insertTripDetail(TripDetail tripDetail) {
        insertEntity(tripDetail);
    }

    public void deleteTripDetail(TripDetail trip) {
        deleteEntity(trip);
    }

    public void updateTripDetail(TripDetail trip) {
        updateEntity(trip);
    }

    // public TripDetail getTripDetail(int trip_detail_id) {
    // String query = "from TripDetail t where t.id=?";
    // Map<Integer, Object> parameter = new HashMap<Integer, Object>();
    // parameter.put(0, trip_detail_id);
    // return getSingleRecord(TripDetail.class, query, parameter);
    // }

    public TripDetail getTripDetail(int trip_detail_id) {
        String query = "from TripDetail t where t.id=?";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        parameter.put(0, trip_detail_id);
        return getSingleRecord(TripDetail.class, query, parameter);
    }

    public TripDetail getTripDetailsByName(String trip_name) {
        String query = "from TripDetail t where t.trip_name=?";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        parameter.put(0, trip_name);
        return getSingleRecord(TripDetail.class, query, parameter);
    }

    // public Trips getTripByName(String trip_id) {
    // String query = "from Trips t where t.trip_name=?";
    // Map<Integer, Object> parameter = new HashMap<Integer, Object>();
    // parameter.put(0, trip_id);
    // return getSingleRecord(Trips.class, query, parameter);
    // }

    // public List<TripDetail> getTripDetails(int trip_id) {
    // String query = "from TripDetail t where t.id=?";
    // Map<Integer, Object> parameter = new HashMap<Integer, Object>();
    // parameter.put(0, trip_id);
    // return getRecords(TripDetail.class, query, parameter);
    // }

    public List<TripDetail> getTripDetails(int trip_detail_id) {
        String query = "from TripDetail t where t.id=?";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        parameter.put(0, trip_detail_id);
        return getRecords(TripDetail.class, query, parameter);
    }

    // public List<Trips> getTripsByBus(int bus_id) {
    // String query = "from Trips t where t.tripDetails.busid=?";
    // Map<Integer, Object> parameter = new HashMap<Integer, Object>();
    // parameter.put(0, bus_id);
    // return getRecords(Trips.class, query, parameter);
    // }

    public List<TripDetail> getTripTripDetailByBus(int bus_id) {
        String query = "from TripDetail t where t.busid=?";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        parameter.put(0, bus_id);
        return getRecords(TripDetail.class, query, parameter);
    }

    // Not working
    public List<TripDetail> getActiveTripsByBus(int bus_id) {
        String query = "from Trips t where t.busid=?";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        parameter.put(0, bus_id);
        return getRecords(TripDetail.class, query, parameter);
    }

    public List<TripDetail> getTripsByBusIdAndTripType(int bus_id, String type) {
        String query = "from TripDetail t where t.busid=? and t.trip_type=?";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        parameter.put(0, bus_id);
        parameter.put(1, type);
        return getRecords(TripDetail.class, query, parameter);
    }

    public List<TripDetail> getTripsByRouteIdAndTripType(int route_id, String type) {
        String query = "from TripDetail t where t.routeid=? and t.trip_type=?";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        parameter.put(0, route_id);
        parameter.put(1, type);
        return getRecords(TripDetail.class, query, parameter);
    }

    public List<TripDetail> getTripsByRouteId(int route_id) {
        String query = "from TripDetail t where t.routeid=?";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        parameter.put(0, route_id);
        return getRecords(TripDetail.class, query, parameter);
    }

    public List<TripDetail> search(String str, String type) {
        Map<String, Object> restrictions = new HashMap<String, Object>();
        restrictions.put(type, "%" + str + "%");
        return searchRecords(TripDetail.class, restrictions);
    }

}
