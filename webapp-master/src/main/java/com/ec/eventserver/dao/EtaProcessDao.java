package com.ec.eventserver.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ec.eventserver.models.EtaProcess;
import com.main.sts.dao.sql.BaseDao;
import com.main.sts.entities.RouteStops;
import com.main.sts.util.DateUtil;
import com.main.sts.util.DateUtil.DateRange;

@Repository
public class EtaProcessDao extends BaseDao {

    public List<EtaProcess> getEtaByTripIdAndDate(int id, Date date) {
        DateRange dateRange = DateUtil.getStartAndEndOfTripDate(date);
        Date today = dateRange.getStartDate();
        Date endOfToday = dateRange.getEndDate();

        String query = "from EtaProcess e where e.trip_id=? and e.running_date BETWEEN ? AND ?";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        parameter.put(0, id);
        parameter.put(1, today);
        parameter.put(2, endOfToday);
        return getRecords(EtaProcess.class, query, parameter);
    }

    public EtaProcess getEta(Date date, Integer stop_id, int id, int busid) {
        DateRange dateRange = DateUtil.getStartAndEndOfTripDate(date);
        Date today = dateRange.getStartDate();
        Date endOfToday = dateRange.getEndDate();

        String query = "from EtaProcess e where e.trip_id=? and e.running_date BETWEEN ? AND ? and e.bus_id=? and e.stop_id=? order by e.count";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        parameter.put(0, id);
        parameter.put(1, today);
        parameter.put(2, endOfToday);
        parameter.put(3, busid);
        parameter.put(4, stop_id);
        List<EtaProcess> eta = getRecords(EtaProcess.class, query, parameter, 1);
        if (eta.size() != 0)
            return eta.get(0);
        else
            return null;
    }

    public EtaProcess getLastEta(int id, Date date) {
        DateRange dateRange = DateUtil.getStartAndEndOfTripDate(date);
        Date today = dateRange.getStartDate();
        Date endOfToday = dateRange.getEndDate();

        String query = "from EtaProcess e where e.trip_id=? and e.running_date BETWEEN ? AND ? order by e.stop_number desc";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        parameter.put(0, id);
        parameter.put(1, today);
        parameter.put(2, endOfToday);
        List<EtaProcess> eta = getRecords(EtaProcess.class, query, parameter, 1);
        if (eta.size() != 0)
            return eta.get(0);
        else
            return null;
    }

    public int getStopNumber(int routeid, int routestop_id) {
        String query = "from RouteStops r where r.route_id = ? and r.id=?";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        parameter.put(0, routeid);
        parameter.put(1, routestop_id);
        return getSingleRecord(RouteStops.class, query, parameter).getStop_number();
    }

    public int getStopNumberByEta(Date date, int id) {
        DateRange dateRange = DateUtil.getStartAndEndOfTripDate(date);
        Date today = dateRange.getStartDate();
        Date endOfToday = dateRange.getEndDate();

        String query = "from EtaProcess e where e.trip_id=? and e.running_date BETWEEN ? AND ?";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        parameter.put(0, id);
        parameter.put(1, today);
        parameter.put(2, endOfToday);
        return getSingleRecord(EtaProcess.class, query, parameter).getStop_number();
    }

    public EtaProcess getEtaById(int id) {
        String query = "from EtaProcess e where e.id=?";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        parameter.put(0, id);

        return getSingleRecord(EtaProcess.class, query, parameter);
    }
}
