package com.main.sts.dao.sql;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.main.sts.common.CommonConstants.TripStatus;
import com.main.sts.entities.DailyBusCoordinates;
import com.main.sts.entities.DailyRunningBuses;
import com.main.sts.util.DateUtil;
import com.main.sts.util.DateUtil.DateRange;

@Repository
public class DailyRunningBusesDao extends BaseDao {

    public DailyRunningBuses getDailyBusbyId(int id) {
        String auery = "from DailyRunningBuses d where d.id = ?";
        Map<Integer, Object> param = new HashMap<Integer, Object>();
        param.put(0, id);
        return getSingleRecord(DailyRunningBuses.class, auery, param);
    }

    // TODO: this is also not correct, for time being hardcoding 1 as parameter in db call.
    public DailyRunningBuses getDailyRunningBus(int trip_id, Date running_date, TripStatus trip_status) {
        DateRange dateRange = DateUtil.getStartAndEndOfTripDate(running_date);
        Date today = dateRange.getStartDate();
        Date endOfToday = dateRange.getEndDate();
        
        System.out.println("today:"+today+"----endOfToday:"+endOfToday +"----------"+trip_status.getTripStatusText());

        String queryStr = "from DailyRunningBuses d where d.trip_id = ? and d.running_date BETWEEN ? AND ? and d.trip_status = ?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, trip_id);
        parameters.put(1, today);
        parameters.put(2, endOfToday);
        parameters.put(3, trip_status.getStatusValue());
        return getSingleRecord(DailyRunningBuses.class, queryStr, parameters, 1);
    }

    /**
     * Returns a {@link DailyRunningBuses} object with Running trip status criteria.
     * @param running_date
     * @param trip_id
     * @return
     */
    public DailyRunningBuses getDailyRunningBusWithRunningStatus(Date running_date, int trip_id) {
        // basically we are finding the records between today and today's end like 17th Nov 00.00.00am - 18th Nov 00:00:00am
        DateRange dateRange = DateUtil.getStartAndEndOfTripDate(running_date);
        Date today = dateRange.getStartDate();
        Date endOfToday = dateRange.getEndDate();
        
        System.out.println("today:"+today+"----endOfToday:"+endOfToday);
        String queryStr = "from DailyRunningBuses d where d.trip_id = ?  and running_date BETWEEN ? AND ? and trip_status=? ";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, trip_id);
        parameters.put(1, today);
        parameters.put(2, endOfToday);
        parameters.put(3, TripStatus.RUNNING.getStatusValue());
        return getSingleRecord(DailyRunningBuses.class, queryStr, parameters);
    }
    
    /**
     * Returns a {@link DailyRunningBuses} object without trip status criteria.
     * @param running_date
     * @param trip_id
     * @return
     */
    public DailyRunningBuses getDailyRunningBus(int trip_id,Date running_date) {
        // basically we are finding the records between today and today's end like 17th Nov 00.00.00am - 18th Nov 00:00:00am
        DateRange dateRange = DateUtil.getStartAndEndOfTripDate(running_date);
        Date today = dateRange.getStartDate();
        Date endOfToday = dateRange.getEndDate();
        
        System.out.println("today:"+today+"----endOfToday:"+endOfToday);
        String queryStr = "from DailyRunningBuses d where d.trip_id = ?  and running_date BETWEEN ? AND ?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, trip_id);
        parameters.put(1, today);
        parameters.put(2, endOfToday);
        return getSingleRecord(DailyRunningBuses.class, queryStr, parameters);
    }

    public List<DailyRunningBuses> getDailyRunningBusList(Date running_date, int trip_id) {
        DateRange dateRange = DateUtil.getStartAndEndOfTripDate(running_date);
        Date today = dateRange.getStartDate();
        Date endOfToday = dateRange.getEndDate();
        
        System.out.println("today:"+today+"----endOfToday:"+endOfToday);
        
        String queryStr = "from DailyRunningBuses d where d.trip_id = ?  and running_date BETWEEN ? AND ?  ";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, trip_id);
        parameters.put(1, today);
        parameters.put(2, endOfToday);
        return getRecords(DailyRunningBuses.class, queryStr, parameters);
    }

    public void insertDailyRunningBuses(DailyRunningBuses dailyRunningBuses) {
        insertEntity(dailyRunningBuses);
    }

    public List<DailyRunningBuses> getDailyRunningBus(Date running_date, TripStatus trip_status) {
        DateRange dateRange = DateUtil.getStartAndEndOfTripDate(running_date);
        Date startOfDay = dateRange.getStartDate();
        Date endOfToday = dateRange.getEndDate();
        System.out.println("startOfDay:"+startOfDay+"----endOfToday:"+endOfToday);
        
        String queryStr = " SELECT "
                + " d.id  as id, b.bus_id as vehicle_id , b.bus_licence_number as vehicle_licence_number,d.running_date as running_date,d.trip_id as trip_id, "
                + " d.vehicle_status as vehicle_status,d.current_stop as current_stop,d.arrived_time as arrived_time,d.users_in_bus as users_in_bus, "
                + " d.latitude as latitude , d.longitude as longitude,d.trip_status, d.trip_start_time, d.trip_end_time, d.is_bus_arrived_to_trip_last_stop,d.is_bus_out_of_first_stop,d.is_bus_arrived_to_trip_last_stop, d.is_message_sent_to_first_stop, dri.driver_name as driver_name,dri.id as driver_id "
                + "  from  "
                + " daily_running_buses as d  "
                + " left JOIN trips as t on  t.id = d.trip_id "
                + " left JOIN trip_details as td on  t.trip_detail_id = td.id "
                + " left JOIN buses as b on  b.bus_id = td.busid "
                //+ " left JOIN daily_subscriber_data as daily on daily.trip_id = d.trip_id and daily.date = d.date and out_stop LIKE 'none' "
                + " left join drivers as dri on dri.id = d.driver_id "
                //+ " where d.date BETWEEN ? AND ? "// d.trip_status = ? and
                + " where d.trip_status = ? AND (d.running_date BETWEEN ? AND ?) "// d.trip_status = ? and
                + " GROUP BY dri.id,b.bus_id,d.running_date,d.trip_id,d.vehicle_status,d.current_stop,d.arrived_time,d.latitude,d.longitude,dri.driver_name,d.id ";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, trip_status.getStatusValue());
        parameters.put(1, startOfDay);
        parameters.put(2, endOfToday);
        return getStopsBySqlQuery(DailyRunningBuses.class, queryStr, parameters);
    }

    public List<DailyRunningBuses> getDailyRunningBus(Date running_date) {
        DateRange dateRange = DateUtil.getStartAndEndOfTripDate(running_date);
        Date startOfDay = dateRange.getStartDate();
        Date endOfToday = dateRange.getEndDate();
        
        System.out.println("startOfDay:"+startOfDay+"----endOfToday:"+endOfToday);
        
        String queryStr = "from DailyRunningBuses d where d.running_date BETWEEN ? AND ? ";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, startOfDay);
        parameters.put(1, endOfToday);
        return getRecords(DailyRunningBuses.class, queryStr, parameters);
    }

    public DailyBusCoordinates getLatestBusCoordinates(Date running_date, int trip_id) {
        DateRange dateRange = DateUtil.getStartAndEndOfTripDate(running_date);
        Date startOfDay = dateRange.getStartDate();
        Date endOfToday = dateRange.getEndDate();

        System.out.println("startOfDay:" + startOfDay + "----endOfToday:" + endOfToday);

        String query = "from DailyBusCoordinates d where d.trip_id=? and d.running_date BETWEEN ? AND ? order by id desc";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, trip_id);
        parameters.put(1, startOfDay);
        parameters.put(2, endOfToday);
        DailyBusCoordinates first = getSingleRecord(DailyBusCoordinates.class, query, parameters, 1);
        return first;
    }

    public List<DailyRunningBuses> getBusesByDates(Date running_date1, Date running_date2) {
        System.out.println("date1:"+running_date1+"----date2:"+running_date2);
        
        String querystr = "from DailyRunningBuses d where d.running_date BETWEEN ? AND ?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, running_date1);
        parameters.put(1, running_date2);
        List<DailyRunningBuses> first = getRecords(DailyRunningBuses.class, querystr, parameters);
        if (first.size() != 0)
            return first;
        else
            return null;
    }
}
