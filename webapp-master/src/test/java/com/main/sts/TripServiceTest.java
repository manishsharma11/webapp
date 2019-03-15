package com.main.sts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;

import com.main.sts.dto.ShuttleTimingsDTO;
import com.main.sts.dto.TripResponseDTO;
import com.main.sts.entities.TripDetail;
import com.main.sts.entities.Trips;
import com.main.sts.service.TripService;
import com.main.sts.util.DateUtil;

public class TripServiceTest extends BaseTest {

    @Resource
    private TripService tripService;

    @Test
    public void testGetAllTripsBetweenStops() {
        int from_stop_id = 34;//33;
        int to_stop_id = 33;//34;
        List<ShuttleTimingsDTO> tripsList = tripService.getTripsBetweenStops(from_stop_id, to_stop_id, null);
        System.out.println("tripsList:" + tripsList);
        for (ShuttleTimingsDTO t : tripsList) {
            System.out.println(t.getTrip_running_date() + "---" +t.getPickup_stop_time() + ":" + t.getPickup_stop_time_ampm() + "--" + t.getEnabled());
        }
    }

//    @Test
//    public void testGetAllActiveAndComingTripsBetweenStops() {
//        int from_stop_id = 33;
//        int to_stop_id = 34;
//        List<Trips> tripsList = tripService.getTodayAndTomorrowTrips1(from_stop_id, to_stop_id);
//        System.out.println("tripsList:" + tripsList);
//        for (Trips t : tripsList) {
//            TripDetail td = t.getTripDetail();
//            System.out.println(t.getTrip_running_date() + "---" + td.getTrip_start_time_hours() + ":"
//                    + td.getTrip_start_time_minutes() + "--" + t.getEnabled());
//        }
//    }
    
    @Test
    public void testGetAllActiveAndComingTripsBetweenStops_1() {
        int from_stop_id = 12;//12;//14;//129;//33;//128;//34;//33;
        int to_stop_id = 14;//4;//33;//34;//134;//33;
        List<ShuttleTimingsDTO> tripsList = tripService.getTodayAndTomorrowTripsResponses(from_stop_id, to_stop_id);
        System.out.println("tripsList:" + tripsList.size());

        System.out.println("");
        for (ShuttleTimingsDTO t : tripsList) {
            System.out.println("trip:"+t.getTrip_name()+"---"+t.getTrip_running_date() + "---" + t.getPickup_stop_time() + ":"
                    + t.getPickup_stop_time_ampm() + "--" + t.isActive() + "--" + t.getEnabled());
        }
    }
    
    @Test
    public void testInsertDummyTrip(){
        Trips trip = new Trips();
        trip.setSeats_filled(4);
        trip.setTrip_running_date(new Date());
        trip.setTrip_detail_id(1);
        tripService.insert(trip);
    }
    
    @Test
    public void testgetAllNonExpiredTripsByRouteIdList() {
        int[] route_ids = {1, 2, 19, 30, 34, 69, 76, 86, 104, 107};
        List<Integer> route_idsList = new ArrayList<Integer>();
        for (int a : route_ids) {
            route_idsList.add(a);
        }
        List<Trips> trips = tripService.getAllNonExpiredEnabledTripsByRouteId(route_idsList,
                DateUtil.getTodayDateWithOnlyDate(), 5);
        for (Trips t : trips) {
            TripDetail td = t.getTripDetail();
            System.out.println(t.getId() + "--" + t.getTrip_running_date() + "---" + td.getTrip_start_time_hours()
                    + ":" + td.getTrip_start_time_minutes() + "--" + t.getEnabled());
        }
    }
    
    @Test
    public void testgetAllNonExpiredTripsByRouteId() {
        int[] route_ids = {1, 2, 19, 30, 34, 69, 76, 86, 104, 107};
        for (int route_id : route_ids) {
            List<Trips> trips = tripService.getAllNonExpiredEnabledTripsByRouteId(route_id,
                    DateUtil.getTodayDateWithOnlyDate(), 5);
            for (Trips t : trips) {
                TripDetail td = t.getTripDetail();
                System.out.println(t.getId()+"--"+t.getTrip_running_date() + "---" + td.getTrip_start_time_hours() + ":"
                        + td.getTrip_start_time_minutes() + "--" + t.getEnabled());
            }
        }
    }
    
    @Test
    public void testGetRecordsByPagination(){
        List<Trips> trips = tripService.getRecordsByPagination(1, 100);
        for (Trips t : trips) {
            TripDetail td = t.getTripDetail();
            System.out.println(t.getId()+"--"+t.getTrip_running_date() + "---" + td.getTrip_start_time_hours() + ":"
                    + td.getTrip_start_time_minutes() + "--" + t.getEnabled());
        }
    }
}
