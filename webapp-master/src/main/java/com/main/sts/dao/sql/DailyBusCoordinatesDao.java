package com.main.sts.dao.sql;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.main.sts.entities.DailyBusCoordinates;
import com.main.sts.util.DateUtil;
import com.main.sts.util.DateUtil.DateRange;

@Repository
public class DailyBusCoordinatesDao extends BaseDao {

    public void insertDailyBusCoordinates(DailyBusCoordinates dailyBusCoordinates) {
        dailyBusCoordinates.setRunning_date(new Date());
        insertEntity(dailyBusCoordinates);
    }

    public List<DailyBusCoordinates> getTripsCoordinates(int trip_id, Date trip_date) {
        return getTripsCoordinates(trip_id, trip_date, -1);
    }

    public List<DailyBusCoordinates> getTripsCoordinates(int trip_id, Date trip_date, Integer limit) {
        DateRange dateRange = DateUtil.getStartAndEndOfTripDate(trip_date);
        Date today = dateRange.getStartDate();
        Date endOfToday = dateRange.getEndDate();

        System.out.println("trip_id:" + trip_id + " today:" + today + "---------------endOfToday:" + endOfToday);
        String query = "from DailyBusCoordinates d where d.trip_id=? and d.running_date BETWEEN ? AND ? order by d.running_date desc";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        parameter.put(0, trip_id);
        parameter.put(1, today);
        parameter.put(2, endOfToday);
        List<DailyBusCoordinates> list = getRecords(DailyBusCoordinates.class, query, parameter, limit);
        return list;
    }

    public DailyBusCoordinates getFirst(int trip_id, Date trip_date) {
        DateRange dateRange = DateUtil.getStartAndEndOfTripDate(trip_date);
        Date today = dateRange.getStartDate();
        Date endOfToday = dateRange.getEndDate();

        String query = "from DailyBusCoordinates d where d.trip_id=? and d.running_date BETWEEN ? AND ? order by d.running_date asc";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        parameter.put(0, trip_id);
        parameter.put(1, today);
        parameter.put(2, endOfToday);
        List<DailyBusCoordinates> first = getRecords(DailyBusCoordinates.class, query, parameter);
        if (first.size() != 0) {
            // System.out.println("first coordinate in DailyBusCordinateDao "+first.get(0));
            return first.get(0);
        } else
            return null;
    }

    public DailyBusCoordinates getLast(int trip_id, Date trip_date) {

        DateRange dateRange = DateUtil.getStartAndEndOfTripDate(trip_date);
        Date today = dateRange.getStartDate();
        Date endOfToday = dateRange.getEndDate();

        String query = "from DailyBusCoordinates d where d.trip_id=? and d.running_date BETWEEN ? AND ? order by d.running_date desc";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        parameter.put(0, trip_id);
        parameter.put(1, today);
        parameter.put(2, endOfToday);
        List<DailyBusCoordinates> first = getRecords(DailyBusCoordinates.class, query, parameter);
        if (first.size() != 0) {
            // System.out.println("last coordinate in DailyBusCordinateDao "+first.get(0));
            return first.get(0);
        } else
            return null;
    }

}
