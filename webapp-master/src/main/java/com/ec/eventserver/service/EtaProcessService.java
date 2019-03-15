package com.ec.eventserver.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.eventserver.dao.EtaProcessDao;
import com.ec.eventserver.dto.request.VehicleGpsDataRequest;
import com.ec.eventserver.models.EtaProcess;
import com.main.sts.entities.DailyBusCoordinates;
import com.main.sts.entities.RouteStops;
import com.main.sts.entities.Trips;
import com.main.sts.service.DailyBusCoordinatesService;
import com.main.sts.util.SystemConstants;

@Service
public class EtaProcessService {

    @Autowired
    private EtaProcessDao etadao;
    @Autowired
    private DailyBusCoordinatesService dailyBusCoordinatesService;

    private static final Logger logger = Logger.getLogger(EtaProcessService.class);

    public List<EtaProcess> getEtaByTripIdAndDate(int id, Date date) {
        return etadao.getEtaByTripIdAndDate(id, date);
    }

    // to check only stps below 5 mins

    public int getTimeForEtaBelowfiveMin(VehicleGpsDataRequest data, RouteStops routeStopsEntity, Trips tripEntity) {
        // logger.info("Enter getEtaCheckTime");
        int diff = 0;
        try {
            String date_time = data.getTime();
            String[] time = date_time.split("\\s+");

            int c_time_in_min = (Integer.parseInt(time[0].split(":")[0]) * 60)
                    + (Integer.parseInt(time[0].split(":")[1]));
            int e_time_in_min = (Integer.parseInt(routeStopsEntity.getStop_time().split(":")[0]) * 60)
                    + (Integer.parseInt(routeStopsEntity.getStop_time().split(":")[1]));

            diff = e_time_in_min - c_time_in_min;

            return diff;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return diff;

    }

    // end

    public int getEtaCheckTime(VehicleGpsDataRequest data, RouteStops routeStopsEntity, Trips tripEntity) {
        // logger.info("Enter getEtaCheckTime");
        EtaProcess stopsEtaMaintenenceEntity = null;
        try {
            stopsEtaMaintenenceEntity = getEtaStopCount(data.getCreated_at(), routeStopsEntity.getStop_id(),
                    tripEntity.getId(), tripEntity.getTripDetail().getBusid());
        } catch (Exception e) {
            System.out.println("First Time Eta calculation....");
        }
        if (stopsEtaMaintenenceEntity == null) {
            // System.out.println("In If of getEtaCheckTime");
            // System.out.println("routestop "+routeStopsEntity);
            String date_time = data.getTime();
            String[] time = date_time.split("\\s+");
            // System.out.println("time "+time[0]);
            // System.out.println("time1 "+time[1]);
            int c_time_in_min = (Integer.parseInt(time[0].split(":")[0]) * 60)
                    + (Integer.parseInt(time[0].split(":")[1]));
            int e_time_in_min = (Integer.parseInt(routeStopsEntity.getStop_time().split(":")[0]) * 60)
                    + (Integer.parseInt(routeStopsEntity.getStop_time().split(":")[1]));
            // logger.info("stop" + routeStopsEntity.getStop().getStop_name() +
            // "stop time is"
            // + routeStopsEntity.getStop_time() + "expected time in minutes" +
            // e_time_in_min);
            int diff = e_time_in_min - c_time_in_min;

            return diff;
        } else { // The bus is already missed actual time first time
            // System.out.println("In else of getEtaCheckTime");
            String date_time = data.getTime();
            String[] time = date_time.split("\\s+");
            int e_time_in_min = 0;
            int c_time_in_min = (Integer.parseInt(time[0].split(":")[0]) * 60)
                    + (Integer.parseInt(time[0].split(":")[1]));
            if (stopsEtaMaintenenceEntity.getExpected_time().equalsIgnoreCase("NA")) {
                e_time_in_min = (Integer.parseInt(routeStopsEntity.getStop_time().split(":")[0]) * 60)
                        + (Integer.parseInt(routeStopsEntity.getStop_time().split(":")[1]));

            } else {
                e_time_in_min = (Integer.parseInt(stopsEtaMaintenenceEntity.getExpected_time().split(":")[0]) * 60)
                        + (Integer.parseInt(stopsEtaMaintenenceEntity.getExpected_time().split(":")[1]));
            }
            int diff = e_time_in_min - c_time_in_min;
            // logger.info("stop" + routeStopsEntity.getStop().getStop_name() +
            // "stop time is"
            // + routeStopsEntity.getStop_time() + "expected time in minutes" +
            // e_time_in_min);
            return diff;
        }

    }

    public EtaProcess getEtaStopCount(Date date, Integer stop_id, int id, int busid) {
        return etadao.getEta(date, stop_id, id, busid);
    }

    public int getAvgSpeed(int trip_id, Date trip_date, EtaFinder etaFinder) {
        // System.out.println("Enter getAvgSpeed");
        //dddd
        int time_travelled = 0, distance_travelled = 0, avg_speed = 0;
        Double dist_travelled = 0.0;
        DailyBusCoordinates dailyBusFirstCoordinates, dailyBusLastCoordinates = null;

        dailyBusFirstCoordinates = dailyBusCoordinatesService.getFirstCordinates(trip_id, trip_date); // taking
                                                                                                 // first
                                                                                                 // coordinates

        dailyBusLastCoordinates = dailyBusCoordinatesService.getLastCordinates(trip_id, trip_date); // taking
                                                                                               // last
                                                                                               // coordinates
        if (dailyBusFirstCoordinates != null) {
            time_travelled = calculateTimeDifferenceUsingTimeStamp(dailyBusFirstCoordinates.getRunning_date(),
                    dailyBusLastCoordinates.getRunning_date());

            dist_travelled = etaFinder.getEta(dailyBusFirstCoordinates.getLatitude(),
                    dailyBusFirstCoordinates.getLongitude(), dailyBusLastCoordinates.getLatitude(),
                    dailyBusLastCoordinates.getLongitude(), 999);
            distance_travelled = (int) Math.round(dist_travelled);
            // logger.info("======================");
            // logger.info("Time travelled" + time_travelled);
            // logger.info("distance_travelled" + distance_travelled);
        }
        // handling exception /by zero if time =0
        if (time_travelled == 0) {
            // System.out.println("distance/time=0");
        } else {
            // System.out.println("distance/time" + distance_travelled /
            // time_travelled);
        }
        // System.out.println("======================");
        if (time_travelled != 0)
            // calculating avg speed based on dist travled so far & time taken
            // to travel that distance
            avg_speed = distance_travelled / time_travelled;
        else
            logger.info("Time travelled Speed is Zero" + time_travelled);
        // logger.info("Exit getAvgSpeed");
        return avg_speed;
    }

    public int calculateTimeDifferenceUsingTimeStamp(Date t1, Date t2) {
        long diff = TimeUnit.MILLISECONDS.toMinutes(t2.getTime()) - TimeUnit.MILLISECONDS.toMinutes(t1.getTime());
        return (int) diff;
    }
    
    public int calculateTimeDifferenceUsingTimeStamp(String time_stamp1, String time_stamp2) {
        //logger.info("Enter calculateTimeDifferenceUsingTimeStamp");
        int diff = 0;
        String date_time1 = time_stamp1;
        String[] time1 = date_time1.split("\\s+");
        String date_time2 = time_stamp2;
        String[] time2 = date_time2.split("\\s+");
        int c_time_in_min = (int) ((int) (Integer.parseInt(time1[0].split(":")[0]) * 60) + (Integer.parseInt(time1[0]
            .split(":")[1])));
        int e_time_in_min = (int) ((int) (Integer.parseInt(time2[0].split(":")[0]) * 60) + (Integer.parseInt(time2[0]
            .split(":")[1])));
        diff = e_time_in_min - c_time_in_min;
        //System.out.println("c_time on 277 " + time1[0].split(":")[0] + ":" + time1[0].split(":")[1]);
        //System.out.println("e_time on 277 " + time2[0].split(":")[0] + ":" + time2[0].split(":")[1]);

        //logger.info("Exit calculateTimeDifferenceUsingTimeStamp: " + (e_time_in_min - c_time_in_min));
        return diff;
      }

    public String calculateExpectedTime(String stop_time, int is_late) throws ParseException {
        // logger.info("Enter calculateExpectedTime");
        String startTime = stop_time;
        if (is_late < 0)
            is_late = Math.abs(is_late);
        int ALLTIME_IN_MINUTES = is_late;
        int hour = ALLTIME_IN_MINUTES / 60 + Integer.parseInt(startTime.split(":")[0]);
        int min = ALLTIME_IN_MINUTES % 60 + Integer.parseInt(startTime.split(":")[1]);
        String timeStr = hour + ":" + min;
        SimpleDateFormat curFormater = new SimpleDateFormat("H:m");
        Date dateObj = curFormater.parse(timeStr);
        SimpleDateFormat postFormater = new SimpleDateFormat("HH:mm");
        String expected_time = postFormater.format(dateObj);
        // logger.info("Exit calculateExpectedTime:" + expected_time);
        return expected_time;
    }

    public EtaProcess getEtaById(int id) {
        return etadao.getEtaById(id);
    }

    public void insertEtaProcess(VehicleGpsDataRequest data, Trips trip, RouteStops rstop, String expected_time,
            int count) {
        EtaProcess stopsEtaMaintenenceEntity = new EtaProcess();
        stopsEtaMaintenenceEntity.setBus_id(data.getVehicle_id());
        // stopsEtaMaintenenceEntity.setBus_licence_number(data.get());
        stopsEtaMaintenenceEntity.setRunning_date(data.getCreated_at());
        stopsEtaMaintenenceEntity.setRoute_id(rstop.getRoute_id());
        stopsEtaMaintenenceEntity.setStop_name(rstop.getStop().getStop_name());
        stopsEtaMaintenenceEntity.setStop_number(rstop.getStop_number());;
        if (trip.getTripDetail().getTrip_type().equals(SystemConstants.PICKUP)) {
            stopsEtaMaintenenceEntity.setActual_time(rstop.getStop_time());
        }
        if (trip.getTripDetail().getTrip_type().equals(SystemConstants.DROPOFF)) {
            stopsEtaMaintenenceEntity.setActual_time(rstop.getStop_time());
        }
        stopsEtaMaintenenceEntity.setExpected_time(expected_time);
        stopsEtaMaintenenceEntity.setTrip_type(trip.getTripDetail().getTrip_type());
        stopsEtaMaintenenceEntity.setStop_id(rstop.getStop_id());
        stopsEtaMaintenenceEntity.setIs_eta_sent(true);
        stopsEtaMaintenenceEntity.setTrip_id(trip.getId());
        count = count + 1;
        stopsEtaMaintenenceEntity.setCount(count);
        etadao.insertEntity(stopsEtaMaintenenceEntity);

    }

    public void updateEtaProcess(VehicleGpsDataRequest data, Trips trip, RouteStops rstop, String expected_time,
            int count, int eta_id) {
        EtaProcess stopsEtaMaintenenceEntity = getEtaById(eta_id);
        stopsEtaMaintenenceEntity.setBus_id(data.getVehicle_id());
        // stopsEtaMaintenenceEntity.setBus_licence_number(data.get);
        stopsEtaMaintenenceEntity.setRunning_date(data.getCreated_at());
        stopsEtaMaintenenceEntity.setRoute_id(rstop.getRoute_id());
        stopsEtaMaintenenceEntity.setStop_name(rstop.getStop().getStop_name());
        stopsEtaMaintenenceEntity.setStop_number(rstop.getStop_number());;
        if (trip.getTripDetail().getTrip_type().equals(SystemConstants.PICKUP)) {
            stopsEtaMaintenenceEntity.setActual_time(rstop.getStop_time());
        }
        if (trip.getTripDetail().getTrip_type().equals(SystemConstants.DROPOFF)) {
            stopsEtaMaintenenceEntity.setActual_time(rstop.getStop_time());
        }
        stopsEtaMaintenenceEntity.setExpected_time(expected_time);
        stopsEtaMaintenenceEntity.setTrip_type(trip.getTripDetail().getTrip_type());
        stopsEtaMaintenenceEntity.setStop_id(rstop.getStop_id());
        stopsEtaMaintenenceEntity.setIs_eta_sent(true);
        stopsEtaMaintenenceEntity.setTrip_id(trip.getId());
        count = count + 1;
        stopsEtaMaintenenceEntity.setCount(count);
        etadao.updateEntity(stopsEtaMaintenenceEntity);

    }

    public EtaProcess getlastEta(int id, Date date) {
        return etadao.getLastEta(id, date);
    }

    public int getStopNumber(int routeid, int routestop_id) {
        return etadao.getStopNumber(routeid, routestop_id);
    }

    public int getStopNumberFromEta(Date date, int id) {
        return etadao.getStopNumberByEta(date, id);
    }
}
