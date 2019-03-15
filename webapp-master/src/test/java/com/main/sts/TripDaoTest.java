package com.main.sts;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.main.sts.dao.sql.TripDao;
import com.main.sts.dao.sql.TripDetailDao;
import com.main.sts.entities.TripDetail;
import com.main.sts.entities.Trips;

public class TripDaoTest extends BaseTest {

    @Resource
    private TripDetailDao tripDetailDao;
    
    @Resource
    private TripDao tripDao;

    @Test
    public void testGetAllTrips() {
        List<TripDetail> tripsList = tripDetailDao.getTripDetails();;
        System.out.println("tripsList:" + tripsList);
        for (TripDetail trip : tripsList) {
            System.out.println(trip.getTrip_name() + ":" + trip.getTrip_display_name());
        }
    }
    
    @Test
    public void testSelectedTrip() {
        String vehicle_number= "AP281234";
        int vehicle_id= 740;
        Date trip_running_date = new Date();
        Trips trips = tripDao.getTripByVehicle(vehicle_id, trip_running_date);;
        System.out.println("tripsList:" + trips);
    }
}
