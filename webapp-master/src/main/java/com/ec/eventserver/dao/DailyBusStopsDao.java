package com.ec.eventserver.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ec.eventserver.models.DailyBusStops;
import com.main.sts.dao.sql.BaseDao;
import com.main.sts.util.DateUtil;
import com.main.sts.util.DateUtil.DateRange;

@Repository
public class DailyBusStopsDao extends BaseDao {

    public DailyBusStops getDailyBusStop(int trip_id, Date date, int routestop_id) {
        DateRange dateRange = DateUtil.getStartAndEndOfTripDate(date);
        Date today = dateRange.getStartDate();
        Date endOfToday = dateRange.getEndDate();

        String queryStr = "from DailyBusStops r where r.trip_id = ? and r.running_date BETWEEN ? AND ? and routestop_id=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, trip_id);
        parameters.put(1, today);
        parameters.put(2, endOfToday);
        parameters.put(3, routestop_id);
        return getSingleRecord(DailyBusStops.class, queryStr, parameters);
    }

    public DailyBusStops getDailyBusStop(int id) {
        String queryStr = "from DailyBusStops r where r.id = ? ";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, id);
        return getSingleRecord(DailyBusStops.class, queryStr, parameters);

    }

    public DailyBusStops getDailyLastBusStop(Date date, int id) {
        DateRange dateRange = DateUtil.getStartAndEndOfTripDate(date);
        Date today = dateRange.getStartDate();
        Date endOfToday = dateRange.getEndDate();

        String queryStr = "from DailyBusStops r where r.trip_id = ? and r.running_date BETWEEN ? AND ?  order by r.arrived_time desc";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, id);
        parameters.put(1, today);
        parameters.put(2, endOfToday);

        List<DailyBusStops> ls = getRecords(DailyBusStops.class, queryStr, parameters, 1);
        if (ls.size() != 0)
            return ls.get(0);
        else
            return null;
    }

    public List<DailyBusStops> getAllDailyBusStops(int trip_id, Date date) {
        DateRange dateRange = DateUtil.getStartAndEndOfTripDate(date);
        Date today = dateRange.getStartDate();
        Date endOfToday = dateRange.getEndDate();

        String queryStr = "from DailyBusStops r where r.trip_id = ? and r.running_date BETWEEN ? AND ?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, trip_id);
        parameters.put(1, today);
        parameters.put(2, endOfToday);

        return getRecords(DailyBusStops.class, queryStr, parameters);
    }

    public int getCount(int i) {
        String queryStr = "from DailyBusStops r where r.trip_id = ?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, i);
        List<DailyBusStops> all = getRecords(DailyBusStops.class, queryStr, parameters);
        if (all.size() != 0)
            return all.size();
        else
            return 0;
    }

}
