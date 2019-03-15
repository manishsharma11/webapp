package com.main.sts.dao.sql;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.main.sts.entities.Booking;
import com.main.sts.entities.SuggestRoute;
import com.main.sts.entities.Trips;
import com.main.sts.util.DateUtil;

@Repository
public class TripDao extends BaseDao {

    public BigInteger getCountOFRecords(boolean onlyActive){
        String query = null;
        Date today = DateUtil.getOnlyTodayDate(new Date());
        if (onlyActive) {
            query = "select count(id) from trips where trip_running_date >= '" + today+"'";
        } else {
            query = "select count(id) from trips";
        }
        //Integer totalResult = ((Number)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
        return getCountOFRecords(query);
    }
    
    public List<Trips> getRecordsByPagination(Integer offset, Integer limit, boolean onlyActive) {
        Date today = DateUtil.getOnlyTodayDate(new Date());
        Criterion crit = Restrictions.ge("trip_running_date", today);
        Order order[] = new Order[2];
        order[0] = Order.asc("trip_running_date");
        order[1] = Order.asc("id");
        
        return getRecordsByPaginationWithOrder(Trips.class, new Criterion[]{crit}, null, order, offset, limit);
    }
    
    public List<Trips> getTrips() {
        return getAllRecords(Trips.class);
    }

    public void insertTrip(Trips trip) {
        insertEntity(trip); 
    }

    public void deleteTrip(Trips trip) {
        deleteEntity(trip);
    }

    public void updateTrip(Trips trip) {
        updateEntity(trip);
    }

    public void incrementTripSeat(int trip_id) {
        int seats = getTrip(trip_id).getSeats_filled();
        System.out.println("trip_dao 36" + trip_id + " and seats " + seats);
        String q = "update Trips SET seats_filled = ? where id=?";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        parameter.put(0, (seats + 1));
        parameter.put(1, trip_id);
        // Session session = null;
        // SQLQuery query = null;
        try {
            updateEntitys(Trips.class, q, parameter);
        } catch (HibernateException e) {
            e.printStackTrace();

        } finally {
            seats = getTrip(trip_id).getSeats_filled();
            System.out.println("trip_id " + trip_id + " and seats " + seats);

        }
    }

    public void decrementTripSeat(int trip_id) {
        int seats = getTrip(trip_id).getSeats_filled();
        System.out.println("trip_dao-58 " + trip_id + " and seats " + seats);
        String q = "update Trips SET seats_filled = ? where id=?";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        parameter.put(0, (seats - 1));
        parameter.put(1, trip_id);
        // Session session = null;
        // SQLQuery query = null;
        try {
            updateEntitys(Trips.class, q, parameter);
        } catch (HibernateException e) {
            e.printStackTrace();

        }
    }
    public Trips getTrip(int trip_id) {
        String query = "from Trips t where t.id=?";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        parameter.put(0, trip_id);
        return getSingleRecord(Trips.class, query, parameter);
    }

    // public Trips getTripByName(String trip_id) {
    // String query = "from Trips t where t.trip_name=?";
    // Map<Integer, Object> parameter = new HashMap<Integer, Object>();
    // parameter.put(0, trip_id);
    // return getSingleRecord(Trips.class, query, parameter);
    // }

    public Trips getTripByName(String trip_name, Date trip_running_date) {
        String query = "from Trips t where t.trip_name=? AND t.trip_running_date=?";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        parameter.put(0, trip_name);
        parameter.put(1, trip_running_date);
        return getSingleRecord(Trips.class, query, parameter);
    }

    public List<Trips> getTrips(int trip_id) {
        String query = "from Trips t where t.id=?";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        parameter.put(0, trip_id);
        return getRecords(Trips.class, query, parameter);
    }

    // TODO: this method needs a join query I think =======>> Please check
    public Trips getTripByVehicle(int bus_id, Date trip_running_date) {
        
        Date today = trip_running_date;
        // today.setHours(0);
        // today.setMinutes(0);
        // today.setSeconds(0);
        
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DATE, 1);
        Date tomorrow = c.getTime();
        
        // currentHour should be between the trip start time hour and trip end time hour.
        // e.g. start is 8.58 and end is 9.58 then current hour (starting trip) should be >=8 and <= 9
        // e.g. start is 9.30 and end is 10.28 then current hour (starting trip) should be >=9 and <= 10
        // e.g. start is 9.38 and end is 9.55 then current hour (starting trip) should be >=9 and <= 9 ====> 
        // it will creates issues when at the same hour two trips will fall as
        // that will cause two trips to return. like 9.0 to 9.25 and 9.35 - 9.55

        int currentHour = today.getHours();
        int currentHourMins = today.getMinutes();
        
        System.out.println("today:"+today+"----tomorrow:"+tomorrow);
        String query = "from Trips t where t.tripDetail.busid=?  AND t.trip_running_date > ? AND t.trip_running_date < ? "
                + "AND t.tripDetail.trip_start_time_hours >= ? AND t.tripDetail.trip_end_time_hours <= ? order by t.tripDetail.trip_start_time_hours, t.tripDetail.trip_start_time_minutes";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        System.out.println("bus_id:"+bus_id+"---trip_running_date:"+trip_running_date);
        parameter.put(0, bus_id);
        parameter.put(1, today);
        parameter.put(2, tomorrow);
        parameter.put(3, currentHour);

        List<Trips> ls = getRecords(Trips.class, query, parameter);
        for (Trips t : ls) {
            Calendar trip_running_date_cal = Calendar.getInstance();
            Date d = t.getTrip_running_date();
            trip_running_date_cal.setTime(d);
            trip_running_date_cal.set(Calendar.HOUR, t.getTripDetail().getTrip_start_time_hours());
            trip_running_date_cal.set(Calendar.MINUTE, t.getTripDetail().getTrip_start_time_minutes());

        }
        return getSingleRecord(Trips.class, query, parameter, 1);
    }
    
    public List<Trips> getTripsByBus(int bus_id) {
        String query = "from Trips t where t.tripDetail.busid=?";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        parameter.put(0, bus_id);
        return getRecords(Trips.class, query, parameter);
    }

    // Not working
    public List<Trips> getActiveTripsByBus(int bus_id) {
        String query = "from Trips t where t.tripDetail.busid=?";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        parameter.put(0, bus_id);
        return getRecords(Trips.class, query, parameter);
    }

    public List<Trips> getTripsByBusIdAndTripType(int bus_id, String type) {
        String query = "from Trips t where t.tripDetail.busid=? and t.tripDetail.trip_type=?";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        parameter.put(0, bus_id);
        parameter.put(1, type);
        return getRecords(Trips.class, query, parameter);
    }

    public List<Trips> getTripsByRouteIdAndTripType(int route_id, String type) {
        String query = "from Trips t where t.tripDetail.routeid=? and t..tripDetail.trip_type=?";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        parameter.put(0, route_id);
        parameter.put(1, type);
        return getRecords(Trips.class, query, parameter);
    }

    public List<Trips> getTripsByRouteId(int route_id) {
        String query = "from Trips t where t.tripDetail.routeid=?";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        parameter.put(0, route_id);
        return getRecords(Trips.class, query, parameter);
    }
    
    public List<Trips> getAllNonExpiredEnabledTripsByRouteId(List<Integer> route_ids, Date todayDate, int nextNumberOfDays) {
        Date today = DateUtil.getOnlyTodayDate(todayDate);
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DATE, nextNumberOfDays);
        
        Date tillThen = c.getTime();
        
        System.out.println("today:" + today + "---tillThen:" + tillThen);
        
//        String query = "from Trips t where t.enabled=true AND t.tripDetail.routeid=? AND t.trip_running_date >= ? AND t.trip_running_date <= ?";

        String query = "from Trips t where t.enabled = true AND t.tripDetail.routeid IN (:ids) AND t.trip_running_date >= (:startDate) AND t.trip_running_date <= (:endDate) order by t.trip_running_date asc ";
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("ids", route_ids);
        parameter.put("startDate", today);
        parameter.put("endDate", tillThen);
        return getRecordsListWithNamedQuery(Trips.class, query, parameter, -1, true);
    }
    
    public List<Trips> getAllNonExpiredEnabledTripsByRouteId(int route_id, Date todayDate, int nextNumberOfDays) {
        Date today = DateUtil.getOnlyTodayDate(todayDate);
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DATE, nextNumberOfDays);
        
        Date tillThen = c.getTime();
        
        System.out.println("today:" + today + "---tillThen:" + tillThen);
        
        String query = "from Trips t where t.enabled = true AND t.tripDetail.routeid=? AND t.trip_running_date >= ? AND t.trip_running_date <= ? order by t.trip_running_date asc ";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        parameter.put(0, route_id);
        parameter.put(1, today);
        parameter.put(2, tillThen);
        return getRecords(Trips.class, query, parameter);
    }
    
    public List<Trips> getAllNonExpiredEnabledTripsByRouteId(int route_id, Date todayDate) {
        Date today = DateUtil.getOnlyTodayDate(todayDate);
        String query = "from Trips t where t.enabled = true AND t.tripDetail.routeid=? AND t.trip_running_date >= ? order by t.trip_running_date asc ";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        parameter.put(0, route_id);
        parameter.put(1, today);
        return getRecords(Trips.class, query, parameter);
    }

    public List<Trips> search(String str, String type) {
        Map<String, Object> restrictions = new HashMap<String, Object>();
        restrictions.put(type, "%" + str + "%");
        return searchRecords(Trips.class, restrictions);
    }
    
    public List<Trips> search(String columnName, Integer val) {
        Map<String, Object> restrictions = new HashMap<String, Object>();
        restrictions.put(columnName, val);
        return searchRecords(Trips.class, restrictions);
    }

    // updating the seats filled.
    public boolean occupyTripsSeats(Session session, Booking booking, Trips trip) {
        String query = "update Trips b SET b.seats_filled = b.seats_filled + " + booking.getNum_seats_booked()
                + " where b.id = :id";
        System.out.println("query:" + query);
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", trip.getId());
        int tripRowsUpdated = updateEntities(session, Trips.class, query, parameters);
        if (tripRowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }

    // vacating seats due to a cancellation
    public boolean vacateTripsSeats(Session session, Booking booking, Trips trip) {
        String query = "update Trips b set b.seats_filled = b.seats_filled - " + booking.getNum_seats_booked()
                + " where b.id = :id";
        System.out.println("query:" + query);
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", trip.getId());
        int tripRowsUpdated = updateEntities(session, Trips.class, query, parameters);
        if (tripRowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }
}
