package com.main.sts.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.eventserver.service.DeviceService;
import com.main.sts.dao.sql.TripDetailDao;
import com.main.sts.entities.Buses;
import com.main.sts.entities.RouteStops;
import com.main.sts.entities.TripDetail;

@Service
public class TripDetailService {

    @Autowired
    private TripDetailDao tripDetailDao;

    @Autowired
    private BusesService busservice;

    @Autowired
    private RouteService routeservice;

    @Autowired
    private RouteStopsService routeStopsService;

    @Autowired
    private DeviceService deviceService;

    public List<TripDetail> getAllTripDetails() {
        return tripDetailDao.getTripDetails();
    }

    public List<TripDetail> getAllTrips() {
        return tripDetailDao.getTripDetails();
    }

    public void insertTripDetail(TripDetail tripDetail) {
        tripDetailDao.insertTripDetail(tripDetail);
    }

    public void deleteTripDetail(int trip_detail_id) {
        TripDetail tripDetail = getTripDetail(trip_detail_id);
        tripDetailDao.deleteTripDetail(tripDetail);
    }

    public TripDetail getTripDetail(int trip_detail_id) {
        return tripDetailDao.getTripDetail(trip_detail_id);
    }

    public List<TripDetail> getTripDetails(int id) {
        return tripDetailDao.getTripDetails(id);
    }

    public void updatetrip(int trip_detail_id, TripDetail td) {
        TripDetail tripDetail = getTripDetail(trip_detail_id);
        tripDetail.setBusid((td.getBusid()));
        tripDetail.setRouteid((td.getRouteid()));
        tripDetail.setTrip_end_time_hours(td.getTrip_end_time_hours());
        tripDetail.setTrip_end_time_minutes(td.getTrip_end_time_minutes());
        tripDetail.setTrip_name(td.getTrip_name());
        tripDetail.setTrip_start_time_hours(td.getTrip_start_time_hours());
        tripDetail.setTrip_start_time_minutes(td.getTrip_start_time_minutes());
        tripDetail.setTrip_type(td.getTrip_type());

        tripDetailDao.updateTripDetail(tripDetail);
    }

    public TripDetail getTripDetailsByName(String trip_name) {
        return tripDetailDao.getTripDetailsByName(trip_name);
    }

//    // TODO: not working.
//    public List<TripDetail> getTripsBetweenStops(int from_stop_id, int to_stop_id) {
//        List<RouteStops> routeStopsList = routeStopsService.getAllRouteStops();
//
//        Map<Integer, List<RouteStops>> routeMap = new HashMap<Integer, List<RouteStops>>();
//        List<Integer> routes1 = new ArrayList<Integer>();
//
//        for (RouteStops rs : routeStopsList) {
//            int route_id = rs.getRoute_id();
//            if (routeMap.containsKey(route_id)) {
//                List<RouteStops> rsList = routeMap.get(route_id);
//                rsList.add(rs);
//                routeMap.put(rs.getRoute_id(), rsList);
//            } else {
//                List<RouteStops> rsList = new ArrayList<RouteStops>();
//                rsList.add(rs);
//                routeMap.put(rs.getRoute_id(), rsList);
//            }
//
//            if (rs.getStop_id().equals(from_stop_id)) {
//                routes1.add(rs.getRoute_id());
//            }
//        }
//
//        System.out.println("routes1:" + routes1);
//
//        List<Integer> routes2 = new ArrayList<Integer>();
//        for (RouteStops routeStops : routeStopsList) {
//            if (routeStops.getStop_id().equals(to_stop_id)) {
//                routes2.add(routeStops.getRoute_id());
//                System.out.println("routes2:" + routes2);
//            }
//        }
//
//        System.out.println("routes2:" + routes2);
//
//        routes1.retainAll(routes2);
//
//        System.out.println("routes1:" + routes1);
//        System.out.println("routes2:" + routes2);
//
//        // objective is to find all the routes, those are having those
//        // from_stop_id and to_stop_id
//        // some routes would be basically for drop off so they would be having
//        // from_stop_id and to_stop_id
//        // but there to_stop_id would be having less stop_number (psotion)
//        // compare to from_stop_id.
//
//        for (Integer route_id : routeMap.keySet()) {
//            if (!routes1.contains(route_id)) {
//                continue;
//            }
//            int from_stop_position = -1;
//            int to_stop_position = -1;
//
//            List<RouteStops> rsls = routeMap.get(route_id);
//            // / in stops ascedning number based on stop number (position)
//            Collections.sort(rsls, new Comparator<RouteStops>() {
//
//                @Override
//                public int compare(RouteStops o1, RouteStops o2) {
//                    return o1.getStop_number().compareTo(o2.getStop_number());
//                }
//            });
//            for (RouteStops rs : rsls) {
//                System.out.println("===>>>>>>>>>" + rs.getStop().getStop_name());
//                if (rs.getStop_id() == from_stop_id) {
//                    from_stop_position = rs.getStop_number();
//                }
//
//                if (rs.getStop_id() == to_stop_id) {
//                    to_stop_position = rs.getStop_number();
//                }
//
//                System.out.println("routes--:" + routes1);
//                // if the from_stop_postion is greater than to_stop_position
//                // ideally it shouldnt be greater than
//                System.out.println(from_stop_position + "-------------==" + to_stop_position);
//                if ((from_stop_position != -1 && to_stop_position != -1) && (from_stop_position > to_stop_position)) {
//                    System.out.println("routes--before:" + routes1);
//                    routes1.remove(rs.getRoute_id());
//                    System.out.println("routes--after:" + routes1);
//                }
//            }
//        }
//
//        Set<TripDetail> alltrips = new HashSet<TripDetail>();
//        for (Integer route_id : routes1) {
//            List<TripDetail> tripsList = getTripsByRouteId(route_id);
//            for (TripDetail t : tripsList) {
//
//                RouteStops rs = routeStopsService.getRouteStopsForAStopId(route_id, from_stop_id);
//
//                String time = rs.getStop_time();
//                String[] hhmm = time.split(":");
//                Date current_time = new Date();
//                // if the current time hour is already more than 1hr the stop's
//                // time, =>> why 1 hr: because the bus might be running late. so
//                // it might be
//                // that it already crossed the stop arrival time.
//                System.out.println(current_time.getHours() + 1 + "----" + Integer.parseInt(hhmm[0]));
//                if (current_time.getHours() + 1 > Integer.parseInt(hhmm[0])) {
//                    t.setActive(false);
//                }
//                alltrips.add(t);
//            }
//            // alltrips.addAll(tripsList);
//        }
//
//        List<TripDetail> sortedList = new ArrayList<TripDetail>();
//        sortedList.addAll(alltrips);
//
//        java.util.Collections.sort(sortedList, new Comparator<TripDetail>() {
//
//            @Override
//            public int compare(TripDetail o1, TripDetail o2) {
//                // if (o1.getTrip_running_date() != null &&
//                // o2.getTrip_running_date() != null) {
//                // int res =
//                // o1.getTrip_running_date().compareTo(o2.getTrip_running_date());
//                // if (res != 0) {
//                // return res;
//                // }
//                // }
//
//                int res1 = ((Integer) o1.getTrip_start_time_hours()).compareTo((Integer) o2.getTrip_start_time_hours());
//                if (res1 != 0) {
//                    return res1;
//                }
//
//                int res2 = ((Integer) o1.getTrip_start_time_minutes()).compareTo((Integer) o2
//                        .getTrip_start_time_minutes());
//                if (res2 != 0) {
//                    return res2;
//                }
//
//                return 0;
//            }
//        });
//        return sortedList;
//    }

    public List<TripDetail> getTripDetailByBusId(int bus_id) {
        return tripDetailDao.getTripTripDetailByBus(bus_id);
    }

    // public List<TripDetail> getTripsByBusId(int bus_id) {
    // return tripdao.getTripsByBus(bus_id);
    // }

    public List<TripDetail> getTripsByBusIdAndTripType(int bus_id, String type) {
        return tripDetailDao.getTripsByBusIdAndTripType(bus_id, type);
    }

    public List<TripDetail> getTripsByRouteIdAndTripType(int route_id, String type) {
        return tripDetailDao.getTripsByRouteIdAndTripType(route_id, type);
    }

    public List<TripDetail> getTripsByRouteId(int route_id) {
        return tripDetailDao.getTripsByRouteId(route_id);
    }

    public List<TripDetail> searchTripDetails(String type, String str) {
        return tripDetailDao.search(str, type);
    }

    public TripDetail getTripRouteIdOrBusId(int id, int ch) {
        String query = "";
        if (ch == 0)
            query = "from TripDetails t where t.routeid=?";
        else
            query = "from TripDetails t where t.busid=?";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        parameter.put(0, id);
        return tripDetailDao.getSingleRecord(TripDetail.class, query, parameter);
    }

    public List<RouteStops> getRouteStops(int trip_detail_id) {
        TripDetail trip = getTripDetail(trip_detail_id);
        int route_id = trip.getRouteid();
        List<RouteStops> rsList = routeStopsService.getAllRouteStops(route_id, true);
        return rsList;
    }

    public List<TripDetail> getTripsByVehicleNumber(String vehicle_number) {
        Buses vehicle = busservice.getBusByLicence(vehicle_number);
        return getTripsByVehicleId(vehicle.getBus_id());
    }

    public List<TripDetail> getTripsByVehicleId(int vehicle_id) {
        return tripDetailDao.getTripTripDetailByBus(vehicle_id);
    }

}
