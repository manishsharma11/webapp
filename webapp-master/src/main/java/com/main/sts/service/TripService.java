package com.main.sts.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.eventserver.models.DeviceInfo;
import com.ec.eventserver.service.DeviceService;
import com.main.sts.dao.sql.TripDao;
import com.main.sts.dto.ShuttleTimingsDTO;
import com.main.sts.dto.TripResponseDTO;
import com.main.sts.dto.response.BookingCancellationResponse;
import com.main.sts.entities.Booking;
import com.main.sts.entities.BusFarePriceEntity;
import com.main.sts.entities.Buses;
import com.main.sts.entities.DashboardSettings;
import com.main.sts.entities.RouteStops;
import com.main.sts.entities.SuggestRoute;
import com.main.sts.entities.TripDetail;
import com.main.sts.entities.Trips;
import com.main.sts.util.DateUtil;

@Service
public class TripService {

    @Autowired
    private TripDao tripDao;

    @Autowired
    private TripDetailService tripDetailService;

    @Autowired
    private BusesService busservice;

    @Autowired
    private RouteService routeservice;

    @Autowired
    private RouteStopsService routeStopsService;
    
    @Autowired
    private DashBoardSettingsService settingsService;
    
    @Autowired
    private DeviceService deviceService;
    
    @Autowired
    private BookingService bookingService;
    
    @Autowired
    private FareService fareService;

    public BigInteger getCountOFRecords(boolean onlyActive) {
        return tripDao.getCountOFRecords(onlyActive);
    }

    public List<Trips> getRecordsByPagination(Integer offset, Integer limit) {
        List<Trips> trips = getRecordsByPagination(offset, limit, false);
        Collections.sort(trips, new TripComparator());
        return trips;
    }
    
    public List<Trips> getRecordsByPagination(Integer offset, Integer limit, boolean onlyActive) {
        return tripDao.getRecordsByPagination(offset, limit, onlyActive);
    }
    
    public List<Trips> getAllTrips() {
        return tripDao.getTrips();
    }

    public void insert(Trips trip) {
        trip.setSeats_filled(0);
        tripDao.insertTrip(trip);
    }

    public void deleteTrip(int trip_id) {
        Trips trip = getTrip(trip_id);
        tripDao.deleteTrip(trip);
    }

    public Trips getTrip(int id) {
        return tripDao.getTrip(id);
    }

    public List<Trips> getTrips(int id) {
        return tripDao.getTrips(id);
    }

    public void updatetrip(int id, Trips tripentity) {
        Trips trip = getTrip(id);
        trip.setSeats_filled(tripentity.getSeats_filled());
        trip.setTrip_running_date(tripentity.getTrip_running_date());
        tripDao.updateTrip(trip);
    }

    
    public boolean cancelTrip(int trip_id, boolean cancelExpiredBookingsAlso) {
        Trips trip = getTrip(trip_id);
        trip.setEnabled(false);

        // update trip status
        tripDao.updateTrip(trip);
        
        List<Booking> bookings = null;
        if (cancelExpiredBookingsAlso) {
            bookings = bookingService.getActiveBookingByTripId(trip_id);
        } else {
            bookings = bookingService.getBookingByTripId(trip_id);
        }

        if (bookings != null && bookings.size() > 0) {
            for (Booking booking : bookings) {
                BookingCancellationResponse resp = bookingService.cancelBookingBySystem(booking,
                        cancelExpiredBookingsAlso);
                System.out.println("booking_id:" + resp.getBooking_id() + "--commuter_id:" + resp.getCommuter_id()
                        + "--refunded points:" + resp.getRefunded_points()+ "--acc balance:" + resp.getAccount_balance());
            }
        } else {
            System.out.println("No bookings found for cancellation");
        }
        return true;
    }
    
    public List<TripResponseDTO> getAllTripsResponse() {
        return toTripsResponse(tripDao.getTrips());
    }

    /**
     * To get all the trips (active/enabled) between two stops.
     * 
     * @param from_stop_id
     * @param to_stop_id
     * @return
     */
//    public List<TripResponseDTO> getTripsResponseBetweenStops1(int from_stop_id, int to_stop_id) {
//        return getTodayAndTomorrowTripsResponses1(from_stop_id, to_stop_id);
//    }

    public List<ShuttleTimingsDTO> getTripsResponseBetweenStops(int from_stop_id, int to_stop_id) {
        return getTripsResponseBetweenStops(from_stop_id, to_stop_id, null);
    }
    
    /**
     * To get all the trips (active/enabled) between two stops with a
     * <code>{@link TripsFilter}</code>.
     * 
     * @param from_stop_id
     * @param to_stop_id
     * @param tripsFilter
     * @return
     */
    public List<ShuttleTimingsDTO> getTripsResponseBetweenStops(int from_stop_id, int to_stop_id, TripsFilter tripsFilter) {
        List<ShuttleTimingsDTO> ls = getTripsBetweenStops(from_stop_id, to_stop_id, tripsFilter);
        return ls;
    }

    public List<TripResponseDTO> toTripsResponse(List<Trips> ls) {
        List<TripResponseDTO> tripsResp = new ArrayList<TripResponseDTO>();
        if (ls != null) {
            for (Trips t : ls) {
                TripResponseDTO tr = TripResponseDTO.toTripResponse(t, true);
                tripsResp.add(tr);
            }
        }
        return tripsResp;
    }
    
    /**
     * Return only two days trips only today and tomorrow, excluding weekends
     * and holidays (they would be disabled)
     * 
     * @param from_stop_id
     * @param to_stop_id
     * @return
     */
//    public List<TripResponseDTO> getTodayAndTomorrowTripsResponses1(int from_stop_id, int to_stop_id) {
//        return toTripsResponse1(getTodayAndTomorrowTrips1(from_stop_id, to_stop_id));
//    }
    
    public List<ShuttleTimingsDTO> getTodayAndTomorrowTripsResponses(int from_stop_id, int to_stop_id) {
        return getTodayAndTomorrowTrips(from_stop_id, to_stop_id);
    }

//    public List<Trips> getTodayAndTomorrowTrips1(int from_stop_id, int to_stop_id) {
//        TripsFilter weekendFilter = null;
//        DashboardSettings settings = settingsService.getDashBoardSettings();
//        if (settings.isTrip_weeekend_exclusion_check_enabled()) {
//            weekendFilter = new ExcludeWeekendTripsFilter();
//        }
//        return getTripsBetweenStops1(from_stop_id, to_stop_id, new TodayAndTomorrowTripsFilter(weekendFilter));
//    }
    
    public List<ShuttleTimingsDTO> getTodayAndTomorrowTrips(int from_stop_id, int to_stop_id) {
        TripsFilter weekendFilter = null;
        DashboardSettings settings = settingsService.getDashBoardSettings();
        if (settings.isTrip_weeekend_exclusion_check_enabled()) {
            weekendFilter = new ExcludeWeekendTripsFilter();
        }
        return getTripsBetweenStops(from_stop_id, to_stop_id, new TodayAndTomorrowTripsFilter(weekendFilter));
    }
    
//    public List<Trips> getTripsBetweenStops1(int from_stop_id, int to_stop_id, TripsFilter tripsFilter) {
//        List<RouteStops> routeStopsList = routeStopsService.getAllEnabledRouteStops();
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
//        Set<Trips> alltrips = new HashSet<Trips>();
//        for (Integer route_id : routes1) {
//            Date today = DateUtil.getTodayDateWithOnlyDate();
//
//            List<Trips> tripsList = getAllNonExpiredTripsByRouteId(route_id, today, 4);
//
//            System.out.println("trip before any Filter ");
//            for (Trips t : tripsList) {
//                System.out.println("trip:"+t.getTripDetail().getTrip_name()+"---"+t.getTrip_running_date());
//
//            }
//            
//            if (tripsFilter != null) {
//                tripsFilter.filter(tripsList);
//            }
//            
//            System.out.println("trip after Filters ");
//            for (Trips t : tripsList) {
//                System.out.println("trip:"+t.getTripDetail().getTrip_name()+"---"+t.getTrip_running_date());
//            }
//
//            RouteStops rs = routeStopsService.getRouteStopsForAStopId(route_id, from_stop_id);
//            // today with only date.
//            Calendar today_cal = Calendar.getInstance();
//            today_cal.setTime(DateUtil.getTodayDateWithOnlyDate());
//
//            // today with current time also in date.
//            Calendar curren_time_cal = Calendar.getInstance();
//            Calendar trip_running_date_cal = Calendar.getInstance();
//
//            Date current_time = new Date();
//            for (Trips t : tripsList) {
//
//                //Date trip_running_date = t.getTrip_running_date();
//                Date trip_running_date = t.getTripRunningTime();
//
//                // setting calendar value to the trip running_date basically
//                // reusing the calendar instance
//                trip_running_date_cal.setTime(trip_running_date);
//                
//                System.out.println("trip_running_date:"+trip_running_date+"-------------"+current_time);
//                boolean sameDay = isSameDay(curren_time_cal, trip_running_date_cal);
//                System.out.println("sameDay:"+sameDay);
//                
//                // Checking for same day and different timings
//                if (sameDay) {
//                    String time = rs.getStop_time();
//                    String[] hhmm = time.split(":");
//                    Calendar trip_hour_cal = Calendar.getInstance();
//                    trip_hour_cal.setTime(trip_running_date);
//                    trip_hour_cal.set(Calendar.HOUR, Integer.parseInt(hhmm[0]));
//                    trip_hour_cal.set(Calendar.MINUTE, Integer.parseInt(hhmm[1]));
//
////                  System.out.println("trip_running_date:"+trip_running_date+"-------------"+current_time+"---"+allowing_hour + "----" + trip_hour);
//
//                    long timeDifInMins = TimeUnit.MILLISECONDS.toMinutes(curren_time_cal.getTimeInMillis()) - TimeUnit.MILLISECONDS.toMinutes(trip_hour_cal.getTimeInMillis());
//                    // if the current time hour is already more than 1hr the
//                    // stop's time, =>> why 1 hr: because the bus might be
//                    // running late. so it might be that it already crossed the
//                    // stop arrival time.
//                    // if the diff is already more than 60 then dont show that trip
//                    //if (timeDifInMins > 60) {
//                    if (timeDifInMins > 10) {
//                        t.setActive(false);
//                    } else {
//                        t.setActive(true);
//                    }
////                    int current_hour = current_time.getHours();
////                    int allowing_hour = current_hour;
////                    int trip_hour = Integer.parseInt(hhmm[0]);
////                    System.out.println("trip_running_date:"+trip_running_date+"-------------"+current_time+"---"+allowing_hour + "----" + trip_hour);
////                    if (allowing_hour > trip_hour) {
////                        t.setActive(false);
////                    } else {
////                        t.setActive(true);
////                    }
//                }
//                else if (trip_running_date_cal.before(today_cal)) {
//                    // simply discard all the trips those happen in the past. it
//                    // means before today (based on the day not based on the
//                    // time)
//                    t.setActive(false);
//                    continue;
//                } else {
//                    // All trips those are for tomorrow or coming days.
//                    // Please note: A weekend holiday filter should be applied
//                    // by the caller.
//                    t.setActive(true);
//                }
//                alltrips.add(t);
//            }
//            // alltrips.addAll(tripsList);
//        }
//
//        List<Trips> sortedList = new ArrayList<Trips>();
//        sortedList.addAll(alltrips);
//
//        java.util.Collections.sort(sortedList, new TripComparator());
//        return sortedList;
//    }

//    public List<ShuttleTimingsDTO> getTripsBetweenStops_old(int from_stop_id, int to_stop_id, TripsFilter tripsFilter) {
//        List<RouteStops> routeStopsList = routeStopsService.getAllEnabledRouteStops();
//
//        Map<Integer, List<RouteStops>> routeMap = new HashMap<Integer, List<RouteStops>>();
//        List<Integer> routes1 = new ArrayList<Integer>();
//        List<Integer> routes2 = new ArrayList<Integer>();
//
//        for (RouteStops rs : routeStopsList) {
//            
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
//            
//            if (rs.getStop_id().equals(to_stop_id)) {
//                routes2.add(rs.getRoute_id());
//                //System.out.println("routes2:" + routes2);
//            }
//        }
//
//        System.out.println("routes1:" + routes1);
//
////        List<Integer> routes2 = new ArrayList<Integer>();
////        for (RouteStops routeStops : routeStopsList) {
////            if (routeStops.getStop_id().equals(to_stop_id)) {
////                routes2.add(routeStops.getRoute_id());
////                System.out.println("routes2:" + routes2);
////            }
////        }
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
//        Set<Trips> alltrips = new HashSet<Trips>();
//        Set<ShuttleTimingsDTO> shuttles = new HashSet<ShuttleTimingsDTO>();
//
//        for (Integer route_id : routes1) {
//            Date today = DateUtil.getTodayDateWithOnlyDate();
//
//            List<Trips> tripsList = getAllNonExpiredTripsByRouteId(route_id, today, 4);
//
//            System.out.println("trip before any Filter ");
//            for (Trips t : tripsList) {
//                System.out.println("trip:"+t.getTripDetail().getTrip_name()+"---"+t.getTrip_running_date());
//
//            }
//            
//            if (tripsFilter != null) {
//                tripsFilter.filter(tripsList);
//            }
//            
//            System.out.println("trip after Filters ");
//            for (Trips t : tripsList) {
//                System.out.println("trip:"+t.getTripDetail().getTrip_name()+"---"+t.getTrip_running_date());
//            }
//
//            RouteStops rs = routeStopsService.getRouteStopsForAStopId(route_id, from_stop_id);
//            
//            java.util.Collections.sort(tripsList, new TripComparator());
//
//            for (Trips t : tripsList) {
//                boolean consider = checkIsTripSelectionBetweenStopsActive(t, rs);
//                if (consider) {
//                    alltrips.add(t);
//                    ShuttleTimingsDTO shuttle = ShuttleTimingsDTO.toTripResponse(t, rs, true);
//                    shuttles.add(shuttle);
//                } else {
//                    continue;
//                }
//            }
//        }
//        
//        List<ShuttleTimingsDTO> sortedList = new ArrayList<ShuttleTimingsDTO>();
//        sortedList.addAll(shuttles);
//
//        java.util.Collections.sort(sortedList, new ShuttleComparator());
//        
//        return sortedList;
//    }
    
    
    public List<ShuttleTimingsDTO> getTripsBetweenStops(int from_stop_id, int to_stop_id, TripsFilter tripsFilter) {
        List<RouteStops> routeStopsList = routeStopsService.getAllEnabledRouteStops();

        Map<Integer, List<RouteStops>> routeMap = new HashMap<Integer, List<RouteStops>>();
        List<Integer> from_stops_routes = new ArrayList<Integer>();
        List<Integer> to_stops_routes = new ArrayList<Integer>();

        for (RouteStops rs : routeStopsList) {
            int route_id = rs.getRoute_id();
            if (routeMap.containsKey(route_id)) {
                List<RouteStops> rsList = routeMap.get(route_id);
                rsList.add(rs);
                routeMap.put(rs.getRoute_id(), rsList);
            } else {
                List<RouteStops> rsList = new ArrayList<RouteStops>();
                rsList.add(rs);
                routeMap.put(rs.getRoute_id(), rsList);
            }

            if (rs.getStop_id().equals(from_stop_id)) {
                from_stops_routes.add(rs.getRoute_id());
            }
            
//            if (rs.getStop_id().equals(to_stop_id)) {
//                to_stops_routes.add(rs.getRoute_id());
//            }
        }

        System.out.println("routes1:" + from_stops_routes);

        //List<Integer> routes2 = new ArrayList<Integer>();
        for (RouteStops routeStops : routeStopsList) {
            if (routeStops.getStop_id().equals(to_stop_id)) {
                to_stops_routes.add(routeStops.getRoute_id());
                System.out.println("routes2:" + to_stops_routes);
            }
        }

        System.out.println("routes2:" + to_stops_routes);

        from_stops_routes.retainAll(to_stops_routes);

        System.out.println("routes1:" + from_stops_routes);
        System.out.println("routes2:" + to_stops_routes);

        // objective is to find all the routes, those are having those
        // from_stop_id and to_stop_id
        // some routes would be basically for drop off so they would be having
        // from_stop_id and to_stop_id
        // but there to_stop_id would be having less stop_number (psotion)
        // compare to from_stop_id.

 
        removeAllRouteStopsNotFallingInFromAndToStops(from_stop_id, to_stop_id, routeMap, from_stops_routes);

        // getting all shuttles.
        Set<ShuttleTimingsDTO> shuttles = getShuttles(from_stop_id, to_stop_id, from_stops_routes, tripsFilter);

        List<ShuttleTimingsDTO> sortedList = new ArrayList<ShuttleTimingsDTO>();
        sortedList.addAll(shuttles);

        java.util.Collections.sort(sortedList, new ShuttleComparator());
        
        System.out.println("sortedList shuttle list:"+sortedList);
        return sortedList;
    }
    
    public void removeAllRouteStopsNotFallingInFromAndToStops(int from_stop_id, int to_stop_id,
            Map<Integer, List<RouteStops>> routeMap, List<Integer> from_stops_routes) {
        
        for (Integer route_id : routeMap.keySet()) {
            if (!from_stops_routes.contains(route_id)) {
                continue;
            }
            int from_stop_position = -1;
            int to_stop_position = -1;

            List<RouteStops> rsls = routeMap.get(route_id);
            // / in stops ascedning number based on stop number (position)
            Collections.sort(rsls, new Comparator<RouteStops>() {

                @Override
                public int compare(RouteStops o1, RouteStops o2) {
                    return o1.getStop_number().compareTo(o2.getStop_number());
                }
            });
            for (RouteStops rs : rsls) {
                System.out.println("===>>>>>>>>>" + rs.getStop().getStop_name());
                if (rs.getStop_id() == from_stop_id) {
                    from_stop_position = rs.getStop_number();
                }

                if (rs.getStop_id() == to_stop_id) {
                    to_stop_position = rs.getStop_number();
                }

                //System.out.println("routes--:" + from_stops_routes);
                // if the from_stop_postion is greater than to_stop_position
                // ideally it shouldnt be greater than
                //System.out.println(from_stop_position + "-------------==" + to_stop_position);
                if ((from_stop_position != -1 && to_stop_position != -1) && (from_stop_position > to_stop_position)) {
                    //System.out.println("routes--before:" + from_stops_routes);
                    from_stops_routes.remove(rs.getRoute_id());
                    //System.out.println("routes--after:" + from_stops_routes);
                }
            }
        }
    }
    
    public Set<ShuttleTimingsDTO> getShuttles(int from_stop_id, int to_stop_id, List<Integer> routes1, TripsFilter tripsFilter) {
        Set<Trips> alltrips = new HashSet<Trips>();
        Set<ShuttleTimingsDTO> shuttles = new HashSet<ShuttleTimingsDTO>();

        if (routes1 == null || routes1.size() == 0) {
            return shuttles;
        }
        //for (Integer route_id : routes1) {
            Date today = DateUtil.getTodayDateWithOnlyDate();

            //List<Trips> tripsList = getAllNonExpiredEnabledTripsByRouteId(route_id, today, 4);
            List<Trips> tripsList = getAllNonExpiredEnabledTripsByRouteId(routes1, today, 4);

            if (tripsList == null) {
                return shuttles;
            }
            
            boolean debug = false;
            
            if (debug) {
                System.out.println("trip before any Filter ");
                for (Trips t : tripsList) {
                    System.out.println("trip:" + t.getTripDetail().getTrip_name() + "---" + t.getTripRunningTime());
                }
            }

            if (tripsFilter != null) {
                tripsFilter.filter(tripsList);
            }

            if (debug) {
                System.out.println("trip after Filters ");
                for (Trips t : tripsList) {
                    System.out.println("trip:" + t.getTripDetail().getTrip_name() + "---" + t.getTripRunningTime());
                }
            }

            //RouteStops rs = routeStopsService.getRouteStopsForAStopId(route_id, from_stop_id);

            java.util.Collections.sort(tripsList, new TripComparator());

            for (Trips t : tripsList) {
                TripDetail td = t.getTripDetail();
                RouteStops rs = routeStopsService.getRouteStopsForAStopId(td.getRouteid(), from_stop_id);
                boolean consider = checkIsTripSelectionBetweenStopsActive(t, rs);
                if (consider) {
                    System.out.println("=>>>>>>>>>"+t.getId()+"--"+t.getTripRunningTime()+"------"+t.isActive()+"--"+t.getEnabled());
                    alltrips.add(t);
                    ShuttleTimingsDTO shuttle = ShuttleTimingsDTO.toTripResponse(t, rs, true);
                    try {
                        BusFarePriceEntity fare = fareService.fetchFare(from_stop_id, to_stop_id);
                        if (fare != null) {
                            shuttle.setCharged_fare(fare.getCharged_fare());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    shuttles.add(shuttle);
                } else {
                    continue;
                }
            }
        //}
        return shuttles;
    }
    
    public boolean checkIsTripSelectionBetweenStopsActive(Trips t,  RouteStops rs){

        Calendar trip_running_time_cal = t.getTripRunningTimeCal();

        // today with only date.
        Calendar today_start_absolute_cal = DateUtil.getOnlyTodayDateCal(new Date());

        // today with current time also in date.
        Calendar curren_time_cal = DateUtil.getCalendar();

        // simply discard all the trips those happen in the past. it
        // means before today (based on the day not based on the
        // time)
        if (trip_running_time_cal.before(today_start_absolute_cal)) {
            t.setActive(false);
            System.out.println("returning false for trip:"+trip_running_time_cal.getTime());
            return false;
        }
    
        System.out.println("trip_running_date:" + trip_running_time_cal.getTime() + "-------------" + curren_time_cal.getTime());
        boolean sameDay = isSameDay(curren_time_cal, trip_running_time_cal);
        System.out.println("sameDay:"+sameDay);
        
        // Checking for same day and different timings
        if (sameDay) {
            checkForTodayTrips(t, rs, trip_running_time_cal);
            return true;
        } else {
            // All trips those are for tomorrow or coming days.
            // Please note: A weekend holiday filter should be applied
            // by the caller.
            t.setActive(true);
            return true;
        }
    }
    
    private Calendar getStopTimeCalendar( String stop_time, Calendar trip_running_time_cal){
        System.out.println("stop time:"+stop_time);
        String[] hhmm = stop_time.split(":");
        System.out.println("stop time array:" + Arrays.toString(hhmm));
        Calendar stop_hour_cal = DateUtil.getCalendar();
        stop_hour_cal.setTime(trip_running_time_cal.getTime());
        stop_hour_cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hhmm[0]));
        stop_hour_cal.set(Calendar.MINUTE, Integer.parseInt(hhmm[1]));
        return stop_hour_cal;
    }
    
    private void checkForTodayTrips(Trips t, RouteStops rs, Calendar trip_running_time_cal) {
        String stop_time = rs.getStop_time();
        Calendar stop_hour_cal = getStopTimeCalendar(stop_time, trip_running_time_cal);

        Calendar curren_time_cal = DateUtil.getCalendar();
        // only for testing.
//        curren_time_cal.set(Calendar.HOUR_OF_DAY, 19);
//        curren_time_cal.set(Calendar.MINUTE, 9);

        System.out.println("stop_hour_cal:" + stop_hour_cal.getTime() + "---" + curren_time_cal.getTime());

        long stopTime = TimeUnit.MILLISECONDS.toMinutes(stop_hour_cal.getTimeInMillis());
        long currentTime = TimeUnit.MILLISECONDS.toMinutes(curren_time_cal.getTimeInMillis());

        System.out.println("stopTime:"+stopTime+"---currentTime:"+currentTime);
        int minsBefore = 10;
        // before 10 mins it will close the taking booking for trip.
        // 10.30- 10 mins < 10.21
        if (stopTime - minsBefore < currentTime) {
            System.out.println("1. marking inactive as after stop time and gap mins");
            t.setActive(false);
        } else if (stopTime - minsBefore > currentTime) { // 10.30 -10 > 10.19
            System.out.println("2. marking active as before stop time and gap mins");
            t.setActive(true);
        } else if (stopTime <= currentTime) {
            System.out.println("3. marking inactive as after stop time and gap mins");
            t.setActive(false);
        } else {
            System.out.println("4. marking inactive as in else");
            t.setActive(false);
            System.out.println("stop_hour_cal:" + stop_hour_cal.getTime() + "---" + curren_time_cal.getTime());
            System.out.println("this should not arise any time");
        }
    }

    public Trips getTripByName(String trip_name, Date trip_runing_date) {
        return tripDao.getTripByName(trip_name, trip_runing_date);
    }

    // public List<Trips> getTripsByName(String trip_name) {
    // return tripDao.getTripsByName(trip_name);
    // }

    // public List<Trips> getTripsByBusId(int bus_id, Date date) {
    // return tripDao.getTripsByBus(bus_id);
    // }

    public List<Trips> getTripsByBusId(int bus_id) {
        return tripDao.getTripsByBus(bus_id);
    }

    public List<Trips> getTripsByBusIdAndTripType(int bus_id, String type) {
        return tripDao.getTripsByBusIdAndTripType(bus_id, type);
    }

    public List<Trips> getTripsByRouteIdAndTripType(int route_id, String type) {
        return tripDao.getTripsByRouteIdAndTripType(route_id, type);
    }

    public List<Trips> getTripsByRouteId(int route_id) {
        return tripDao.getTripsByRouteId(route_id);
    }

    public List<Trips> getAllNonExpiredEnabledTripsByRouteId(int route_id, Date todayDate) {
        return tripDao.getAllNonExpiredEnabledTripsByRouteId(route_id, todayDate);
    }
    
    public List<Trips> getAllNonExpiredEnabledTripsByRouteId(int route_id, Date todayDate, int nextNumberOfDays) {
        return tripDao.getAllNonExpiredEnabledTripsByRouteId(route_id, todayDate, nextNumberOfDays);
    }
    
    public List<Trips> getAllNonExpiredEnabledTripsByRouteId(List<Integer> route_ids, Date todayDate,
            int nextNumberOfDays) {
        if (route_ids == null || route_ids.size() == 0) {
            System.out.println("route_ids are null");
            return null;
        }
        return tripDao.getAllNonExpiredEnabledTripsByRouteId(route_ids, todayDate, nextNumberOfDays);
    }

    public void incrementTripSeat(int trip_id) {
        tripDao.incrementTripSeat(trip_id);
    }

    public void decrementTripSeat(int trip_id) {
        tripDao.decrementTripSeat(trip_id);
    }

    public List<Trips> searchTrips(String type, String str) {
        return tripDao.search(str, type);
    }
    
    public List<Trips> searchTrips(String columnName, Integer searchedValue) {
        return tripDao.search(columnName, searchedValue);
    }

    public List<RouteStops> getRouteStops(int trip_id) {
        Trips trip = getTrip(trip_id);
        int route_id = trip.getTripDetail().getRouteid();
        List<RouteStops> rsList = routeStopsService.getAllRouteStops(route_id, true);
        return rsList;
    }

    public List<Trips> getTripsByVehicleNumber(String vehicle_number) {
        Buses vehicle = busservice.getBusByLicence(vehicle_number);
        return getTripsByVehicleId(vehicle.getBus_id());
    }

    public Trips getTripByVehicleIdAndRunningDate(int vehicle_id, Date trip_running_date) {
        return tripDao.getTripByVehicle(vehicle_id, trip_running_date);
    }

    public List<Trips> getTripsByVehicleId(int vehicle_id) {
        return tripDao.getTripsByBus(vehicle_id);
    }

    public List<TripResponseDTO> getTripsAndStopsByVehicleNumber(String vehicle_number) {
        Buses vehicle = busservice.getBusByLicence(vehicle_number);
        return getTodayTripsAndStopsByVehicleId(vehicle.getBus_id());
    }

    // public List<TripResponseDTO> getTripsAndStopsByVehicleNumber(String
    // vehicle_number) {
    // Buses vehicle = busservice.getBusByLicence(vehicle_number);
    // return getTripsAndStopsByVehicleId(vehicle.getBus_id());
    // }

    public List<TripResponseDTO> getTodayTripsAndStopsByVehicleId(int vehicle_id) {
        TripsFilter tripsFilter = new TodayTripsFilter(null);
        return getTripsAndStopsByVehicleId(vehicle_id, tripsFilter);
    }

    public List<TripResponseDTO> getTripsAndStopsByVehicleId(int vehicle_id, TripsFilter tripsFilter) {
        List<Trips> trips = getTripsByVehicleId(vehicle_id);

        Collections.sort(trips, new TripComparator());
        
        if (tripsFilter != null) {
            tripsFilter.filter(trips);
        }

        List<TripResponseDTO> tripsResp = new ArrayList<TripResponseDTO>();
        for (Trips t : trips) {
            TripResponseDTO tr = TripResponseDTO.toTripResponseWithStops(t, routeStopsService);
            tripsResp.add(tr);
        }
        return tripsResp;
    }

    public List<TripResponseDTO> getTripsAndStopsByDeviceId(String device_id) {
        DeviceInfo deviceInfo = deviceService.getDeviceInfoByECDeviceId(device_id);
        return getTodayTripsAndStopsByVehicleId(deviceInfo.getVehicle_id());
    }

    public List<TripResponseDTO> getActiveTripsByVehicleId(int vehicle_id) {
        List<Trips> trips = getTripsByVehicleId(vehicle_id);

        List<TripResponseDTO> tripsResp = new ArrayList<TripResponseDTO>();
        for (Trips t : trips) {
            TripResponseDTO tr = TripResponseDTO.toTripResponse(t);
            tripsResp.add(tr);
        }
        return tripsResp;
    }

    public List<TripResponseDTO> getActiveTripsByDeviceId(String device_id) {
        DeviceInfo deviceInfo = deviceService.getDeviceInfoByECDeviceId(device_id);

        List<Trips> trips = getTripsByVehicleId(deviceInfo.getVehicle_id());

        List<TripResponseDTO> tripsResp = new ArrayList<TripResponseDTO>();
        for (Trips t : trips) {
            TripResponseDTO tr = TripResponseDTO.toTripResponse(t);
            tripsResp.add(tr);
        }
        return tripsResp;
    }

    public boolean isSameDay(Calendar c1, Calendar c2) {
        boolean sameDay = c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH) && c1.get(Calendar.DATE) == c2.get(Calendar.DATE);
        return sameDay;
    }

    public class ShuttleComparator implements Comparator<ShuttleTimingsDTO> {

        @Override
        public int compare(ShuttleTimingsDTO o1, ShuttleTimingsDTO o2) {
            if (o1.getTrip_running_date() != null && o2.getTrip_running_date() != null) {
                int res = o1.getTrip_running_date().compareTo(o2.getTrip_running_date());
                System.out.println("res:"+res);
                if (res != 0) {
                    return res;
                }
            }

            String stop_time_o1 = o1.getPickup_stop_time();
            String stop_time_o2 = o2.getPickup_stop_time();
            
            String[] o1s = stop_time_o1.split(":");
            String[] o2s = stop_time_o2.split(":");

            // hours check
            int res1 = ((Integer) Integer.parseInt(o1s[0])).compareTo((Integer)Integer.parseInt(o2s[0]));
            if (res1 != 0) {
                return res1;
            }

            // mins check
            int res2 = ((Integer) Integer.parseInt(o1s[1])).compareTo((Integer)Integer.parseInt(o2s[1]));
            if (res2 != 0) {
                return res2;
            }

            return 0;
        }
    }
    
    public class TripComparator implements Comparator<Trips> {

        @Override
        public int compare(Trips o1, Trips o2) {
            if (o1.getTrip_running_date() != null && o2.getTrip_running_date() != null) {
                int res = o1.getTrip_running_date().compareTo(o2.getTrip_running_date());
                if (res != 0) {
                    return res;
                }
            }

            int res1 = ((Integer) o1.getTripDetail().getTrip_start_time_hours()).compareTo((Integer) o2.getTripDetail()
                    .getTrip_start_time_hours());
            if (res1 != 0) {
                return res1;
            }

            int res2 = ((Integer) o1.getTripDetail().getTrip_start_time_minutes()).compareTo((Integer) o2
                    .getTripDetail().getTrip_start_time_minutes());
            if (res2 != 0) {
                return res2;
            }

            return 0;
        }
    }

    public interface TripsFilter {
        public void filter(List<Trips> trips);
    }

    public class ExcludeWeekendTripsFilter implements TripsFilter {

        @Override
        public void filter(List<Trips> trips) {
            System.out.println("trip before ExcludeWeekendTripsFilter ");
            for (Trips t : trips) {
                System.out.println("trip:"+t.getTripDetail().getTrip_name()+"---"+t.getTrip_running_date());

            }
            Iterator itr = trips.iterator();
            while (itr.hasNext()) {
                Trips t = (Trips) itr.next();
                Calendar tripRunningDateCal = Calendar.getInstance();
                //tripRunningDateCal.setTime(t.getTrip_running_date());
                tripRunningDateCal.setTime(t.getTripRunningTime());

                int dayOfWeek = tripRunningDateCal.get(Calendar.DAY_OF_WEEK);
                if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
                    System.out.println("removing11:" + tripRunningDateCal.getTime());
                    itr.remove();
                }
            }
        }
    }

    public class TodayAndTomorrowTripsFilter implements TripsFilter {

        private TripsFilter previousFilter;

        public TodayAndTomorrowTripsFilter(TripsFilter previousFilter) {
            this.previousFilter = previousFilter;
        }

        @Override
        public void filter(List<Trips> trips) {
            System.out.println("trip before TodayAndTomorrowTripsFilter");
            for (Trips t : trips) {
                System.out.println("trip:"+t.getTripDetail().getTrip_name()+"---"+t.getTrip_running_date());

            }
            // filtering by using the previous assigned filter
            if (previousFilter != null) {
                previousFilter.filter(trips);
            }

            // today: Dec 26th 00:00:00
            // yday: Dec 25th 00:00:00 (deducting -1)
            Calendar yday = Calendar.getInstance();
            yday.add(Calendar.DATE, -1);
            yday.set(Calendar.HOUR_OF_DAY, 0);
            yday.set(Calendar.MINUTE, 0);
            yday.set(Calendar.SECOND, 0);

            // today: Dec 26th 00:00:00
            // dayAfterTomorrow: Dec 27th 23:59:59 (adding 1)
            Calendar dayAfterTomorrow = Calendar.getInstance();
            dayAfterTomorrow.add(Calendar.DATE, 1);
            dayAfterTomorrow.set(Calendar.HOUR_OF_DAY, 23);
            dayAfterTomorrow.set(Calendar.MINUTE, 59);
            dayAfterTomorrow.set(Calendar.SECOND, 59);

            Iterator itr = trips.iterator();
            while (itr.hasNext()) {
                Trips t = (Trips) itr.next();
                System.out.println("trip:"+t.getTripDetail().getTrip_name()+"---"+t.getTrip_running_date());
                Calendar tripRunningDateCal = DateUtil.getCalendar();
                //tripRunningDate.setTime(t.getTrip_running_date());
                //tripRunningDate.set(Calendar.HOUR, t.getTripDetail().getTrip_start_time_hours());
                //tripRunningDate.set(Calendar.MINUTE, t.getTripDetail().getTrip_start_time_hours());
                
                tripRunningDateCal.setTime(t.getTripRunningTime());

                System.out.println("trip:"+t.getTripDetail().getTrip_name()+"---"+tripRunningDateCal);

                System.out.println("tripRunningDate:" + tripRunningDateCal.getTime());
                // if tomorrow is weekend
                int dayOfWeek = dayAfterTomorrow.get(Calendar.DAY_OF_WEEK);
                // if tomorrow is saturday, then adding two days, so tomorrow
                // will become Monday
                if (dayOfWeek == Calendar.SATURDAY) {
                    dayAfterTomorrow.add(Calendar.DATE, 2);
                }
                // if tomorrow is sunday, then adding 1 day, so tomorrow will
                // become Monday
                if (dayOfWeek == Calendar.SUNDAY) {
                    dayAfterTomorrow.add(Calendar.DATE, 1);
                }

                System.out.println(tripRunningDateCal.getTime() + "--------" + yday.getTime());
                System.out.println(tripRunningDateCal.getTime() + "--------" + dayAfterTomorrow.getTime());

                // remove all trips those are before today
                if (tripRunningDateCal.before(yday)) {
                    System.out.println("removing22:" + t.getTrip_running_date());
                    itr.remove();
                }

                // remove all trips those are after dayAfterTomorrow. as we need
                // to stricly maintain 2 days
                if (tripRunningDateCal.after(dayAfterTomorrow)) {
                    System.out.println("removing33:" + t.getTrip_running_date());
                    itr.remove();
                }
            }
        }
    }

    public class TodayTripsFilter implements TripsFilter {

        private TripsFilter previousFilter;

        public TodayTripsFilter(TripsFilter previousFilter) {
            this.previousFilter = previousFilter;
        }

        @Override
        public void filter(List<Trips> trips) {
            // filtering by using the previous assigned filter
            if (previousFilter != null) {
                previousFilter.filter(trips);
            }

            Calendar today = Calendar.getInstance();

            Iterator itr = trips.iterator();
            while (itr.hasNext()) {
                Trips t = (Trips) itr.next();
                Calendar tripRunningDate = Calendar.getInstance();
                //tripRunningDate.setTime(t.getTrip_running_date());
                tripRunningDate.setTime(t.getTripRunningTime());

                boolean isToday = isSameDay(today, tripRunningDate);
                System.out.println("tripRunningDate:" + tripRunningDate.getTime());
                if (!isToday) {
                    itr.remove();
                }
            }
        }
    }

}
