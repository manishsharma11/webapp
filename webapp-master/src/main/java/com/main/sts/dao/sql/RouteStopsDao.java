package com.main.sts.dao.sql;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.main.sts.entities.RouteStops;
import com.main.sts.entities.Stops;

@Repository
public class RouteStopsDao extends BaseDao {

	public void insertRouteStop(RouteStops entity) {
		insertEntity(entity);
	}

	public List<RouteStops> getAllRouteStops() {
		return getAllRecords(RouteStops.class);
	}
	
    public List<RouteStops> getAllRouteStops(boolean onlyEnabledStops) {
        String queryStr = null;
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();

        if (onlyEnabledStops) {
            queryStr = "from RouteStops r where r.enabled = ? AND r.stop.enabled = ? order by r.stop.stop_name asc";
            System.out.println("queryStr:" + queryStr);
            parameters.put(0, onlyEnabledStops);
            parameters.put(1, true);// adding a check that all the stops should be enabled. other wise dont show.
        } else {
            queryStr = "from RouteStops r order by r.stop.stop_name asc";
        }
        return getRecords(RouteStops.class, queryStr, parameters, true);
    }
	
    public List<RouteStops> getAllRouteStopsForRoute(Collection<Integer> selectedRoutes) {
        String queryStr = "from RouteStops r where r.route_id IN (:ids) order by r.stop.stop_name asc";
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("ids", selectedRoutes);
        return getRecordsListWithNamedQuery(RouteStops.class, queryStr, parameters, -1, true);
    }

    public List<RouteStops> getRouteStops(Integer route_id) {
        // String queryStr = "from RouteStops r where r.route_id = ? ";
        // Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        // parameters.put(0, route_id);
        // return getRecords(RouteStops.class, queryStr, parameters);
        return getRouteStops(route_id, false);
    }
    
    public RouteStops getRouteStopsForAStopId(int route_id, int stop_id) {
        String queryStr = "from RouteStops r where r.route_id =(:route_id) AND r.stop_id =(:stop_id) ";
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("route_id", route_id);
        parameters.put("stop_id", stop_id);
        return getSingleRecordWithNamedQuery(RouteStops.class, queryStr, parameters, true);
    }

    public List<RouteStops> getRouteStops(Integer route_id, boolean onlyEnabledRouteStops) {
        String queryStr = null;
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();

        if (onlyEnabledRouteStops) {
            queryStr = "from RouteStops r where r.route_id = ? AND r.enabled = ?";
            System.out.println("queryStr:"+queryStr);
            parameters.put(0, route_id);
            parameters.put(1, onlyEnabledRouteStops);
        } else {
            queryStr = "from RouteStops r where r.route_id = ? ";
            parameters.put(0, route_id);
        }
        return getRecords(RouteStops.class, queryStr, parameters, true);
    }

//    public List<RouteStops> getAllRouteStops(List[] stop_ids, boolean onlyEnabledStopsInRoute) {
//        String queryStr = null;
//        if (onlyEnabledStopsInRoute) {
//            queryStr = "from RouteStops r where r.stop_id = ? AND r.enabled!='FALSE'";
//        } else {
//            queryStr = "from RouteStops r where r.stop_id = ? ";
//        }
//        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
//        parameters.put(0, stop_id);
//        
//        return getRecords(RouteStops.class, queryStr, parameters);
//    }
	   
    public List<RouteStops> getAllRouteStopsForAStop(Integer stop_id, boolean orderedByStopsPosition) {
        String queryStr = "from RouteStops r where r.stop_id = ? order by stop_number asc";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, stop_id);
        return getRecords(RouteStops.class, queryStr, parameters, true);
    }

    public List<RouteStops> getAllEnabledRouteStopsForAStop(Integer stop_id) {
        String queryStr = "from RouteStops r where r.stop_id = ? AND r.enabled = ? AND r.stop.enabled = ? order by r.stop.stop_name asc";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, stop_id);
        parameters.put(1, true);//r.enabled 
        parameters.put(2, true);//r.stop.enabled
        return getRecords(RouteStops.class, queryStr, parameters, true);
    }
    
    public RouteStops getRouteStop(Integer id) {
        String queryStr = "from RouteStops r where r.id = ? ";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, id);
        return getSingleRecord(RouteStops.class, queryStr, parameters, true);
    }

    public RouteStops getRouteStop(int stop_number, Integer route_id) {
        String queryStr = "from RouteStops r where r.route_id = ? and r.stop_number=? ";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, route_id);
        parameters.put(1, stop_number);
        return getSingleRecord(RouteStops.class, queryStr, parameters, true);
    }

    public void incrementStopNumbers(Integer route_id, int stop_number) {
        String queryStr = "update RouteStops r set r.stop_number = r.stop_number + 1 where r.route_id = ? and r.stop_number >  ? ";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, route_id);
        parameters.put(1, stop_number - 1);
        // System.out.println(parameters);
        updateEntitys(RouteStops.class, queryStr, parameters);
    }

    public void decrementStopNumbers(Integer route_id, int stop_number) {
        String queryStr = "update RouteStops r set r.stop_number = r.stop_number - 1 where r.route_id = ? and r.stop_number >  ? ";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, route_id);
        parameters.put(1, stop_number);
        // System.out.println(parameters);
        updateEntitys(RouteStops.class, queryStr, parameters);
    }

    public void decrementStopNumbersBelow(Integer route_id, int stop_number) {
        String queryStr = "update RouteStops r set r.stop_number = r.stop_number - 1 where r.route_id = ? and r.stop_number <  ? ";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, route_id);
        parameters.put(1, stop_number);
        // System.out.println(parameters);
        updateEntitys(RouteStops.class, queryStr, parameters);
    }

    public void removeRouteStop(RouteStops routeStop) {
        deleteEntity(routeStop);
    }

    public List<Stops> getStopDetails(int route_id) {
        String query = "select stops.stop_name,stops.id,stops.latitude,stops.longitude,stops.geofence,stops.isAssigned, stops.enabled, display_name, help_text,image_path, type, tags, sibling_stop_id, shortcode "
                + "from route_stops_list Inner JOIN stops on route_stops_list.stop_id  = stops.id and route_stops_list.route_id=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, route_id);
        return getStopsBySqlQuery(Stops.class, query, parameters, true);
    }

    // public List<Stops> getAllRouteStopsList() {
    // String query =
    // "select stops.stop_name,stops.id,stops.latitude,stops.longitude,stops.geofence,stops.isAssigned, stops.enabled from route_stops_list Inner JOIN stops on route_stops_list.stop_id  = stops.id";
    // Map<Integer, Object> parameters = new HashMap<Integer, Object>();
    // return getStopsBySqlQuery(Stops.class, query, parameters);
    // }

    public List<Stops> getAllStopsList() {
        String query = "select stops.stop_name,stops.id,stops.latitude,stops.longitude,stops.geofence,stops.isAssigned, stops.enabled, stops.tags from stops";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        return getStopsBySqlQuery(Stops.class, query, parameters, true);
    }
}
