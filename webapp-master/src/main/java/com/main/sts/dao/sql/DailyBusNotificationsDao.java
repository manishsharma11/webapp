package com.main.sts.dao.sql;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.main.sts.common.CommonConstants.VehicleStatus;
import com.main.sts.entities.DailyBusNotifications;
import com.main.sts.util.DateUtil;
import com.main.sts.util.DateUtil.DateRange;

@Repository
public class DailyBusNotificationsDao extends BaseDao {

    public List<DailyBusNotifications> getDailyNotification(Date running_date, VehicleStatus status) {
        DateRange dateRange = DateUtil.getStartAndEndOfTripDate(running_date);
        Date startOfDay = dateRange.getStartDate();
        Date endOfDay = dateRange.getEndDate();

        String query = "from DailyBusNotifications d where d.running_date BETWEEN ? AND ? and d.vehicle_status=?";
        Map<Integer, Object> param = new HashMap<Integer, Object>();
        param.put(0, startOfDay);
        param.put(1, endOfDay);
        param.put(2, status.getStatusValue());
        return getRecords(DailyBusNotifications.class, query, param);
    }

    public DailyBusNotifications getDailyNotification(Date running_date, VehicleStatus status, int trip_id) {
        DateRange dateRange = DateUtil.getStartAndEndOfTripDate(running_date);
        Date startOfDay = dateRange.getStartDate();
        Date endOfDay = dateRange.getEndDate();
        
        System.out.println("startOfDay:"+startOfDay+"--endDay:"+endOfDay+" running:"+running_date+"--status:"+status+" trip_id:"+trip_id);
        String query = "from DailyBusNotifications d where d.running_date BETWEEN ? AND ? and d.vehicle_status=? and d.trip_id=? order by running_date desc";
        // System.out.println("date trip status "+date+" "+trip_id+" "+status);
        Map<Integer, Object> param = new HashMap<Integer, Object>();
        param.put(0, startOfDay);
        param.put(1, endOfDay);
        param.put(2, status.getStatusValue());
        param.put(3, trip_id);
        return getSingleRecord(DailyBusNotifications.class, query, param);
    }
}
