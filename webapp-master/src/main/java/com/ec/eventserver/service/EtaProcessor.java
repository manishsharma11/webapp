package com.ec.eventserver.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ec.eventserver.dto.request.VehicleGpsDataRequest;
import com.ec.eventserver.models.DailyBusStops;
import com.ec.eventserver.models.EtaProcess;
import com.main.sts.entities.DailyRunningBuses;
import com.main.sts.entities.RouteStops;
import com.main.sts.entities.Stops;
import com.main.sts.entities.Trips;
import com.main.sts.service.DailyRunningBusesService;
import com.main.sts.service.DashBoardSettingsService;
import com.main.sts.service.RouteStopsService;
import com.main.sts.service.StopsService;
import com.main.sts.service.TripService;
import com.main.sts.util.SystemConstants;

@Component
public class EtaProcessor {

    @Autowired
    private DailyRunningBusesService dailyRunningBusesService;

    @Autowired
    private TripService tripService;
   
    @Autowired
    private StopsService stopservice;
    
    @Autowired
    private EtaFinder etaFinder;
    
    @Autowired
    private SystemProperties systemProperties;
    
    @Autowired
    private DashBoardSettingsService dashBoardSettingsService;
    // @Autowired
    // private SendMail sendmail;
    // @Autowired
    // private SendSms sendsms;
    @Autowired
    private RouteStopsService routeStopsService;
    
    @Autowired
    private DailyBusStopService dailyBusStops;
    
    @Autowired
    private EtaProcessService etaservice;

    private static final Logger logger = Logger.getLogger(EtaProcess.class);

    public void procesEta(VehicleGpsDataRequest data, Trips trip, DailyRunningBuses dailyRunningBuses) {

        // logger.info("Eta Process Starts....");
        String expected_time = "";
        int eta_time = systemProperties.getEta_send_time();
        // DashboardSettings admin =
        // dashBoardSettingsService.getDashBoardSettings();
        int current_stop_number = 0;
        int check_eta_time = 0;
        // DailyBusStops current_stop = null;
        RouteStops route_stop = null;
        String msg = "";
        // int i=0;
        try {
            List<RouteStops> all_stops_inroute = routeStopsService.getAllRouteStops(trip.getTripDetail().getRouteid());
            // logger.info("all stops " + all_stops_inroute);

            List<DailyBusStops> daily_stop = dailyBusStops.getAllDailyBusStops(trip.getId(), data.getCreated_at());
            // logger.info("daily stops "+daily_stop);
            List<EtaProcess> count_value = etaservice.getEtaByTripIdAndDate(trip.getId(), data.getCreated_at());
            if (daily_stop.size() == 0 && count_value.size() == 0) {
                // System.out.println("trip id is "+trip.getRouteid());
                route_stop = routeStopsService.getRouteStopByRouteIdAndStopNo(trip.getTripDetail().getRouteid(), 1);
                // logger.info(" first stop "+route_stop);
                check_eta_time = etaservice.getEtaCheckTime(data, route_stop, trip);
            }

            else {
                // System.out.println("else part");

                DailyBusStops last_stop = dailyBusStops.getLastStopsByTripAndDate(data.getCreated_at(), trip.getId());
                // System.out.println("last stop "+last_stop);

                if (last_stop == null) {
                    /*
                     * EtaProcess et=etaservice.getlastEta(trip.getId(),
                     * data.getDate()); System.out.println("eta stop "+et);
                     */
                    current_stop_number = 0;
                } else {
                    int cs = etaservice.getStopNumber(trip.getTripDetail().getRouteid(), last_stop.getRoutestop_id());
                    // RouteStops cs =
                    // routeStopsService.getRouteStopNumber(trip.getRouteid(),
                    // last_stop.getRoutestop_id());
                    // /System.out.println("i value "+cs);
                    if (cs > current_stop_number)
                        current_stop_number = cs;
                }
                // System.out.println("current stop " + current_stop_number);
            }
            for (RouteStops routeStopscheck : all_stops_inroute) {
                int eta_diff = etaservice.getTimeForEtaBelowfiveMin(data, routeStopscheck, trip);
                if (routeStopscheck.getStop_number() > current_stop_number && eta_diff <= 5) {
                    check_eta_time = etaservice.getEtaCheckTime(data, routeStopscheck, trip);
                    route_stop = routeStopscheck;
                    // if (check_eta_time == eta_time)
                    // {
                    // logger.info("break the loop");
                    // break;
                    // logger.info("check etatime "+check_eta_time+" eta time "+eta_time);
                    if ((check_eta_time <= eta_time && check_eta_time > 0) || (check_eta_time <= 0)) {
//dd
                        int avg_speed = etaservice.getAvgSpeed(trip.getId(), data.getCreated_at(), etaFinder);
                        System.out.println("the avg speed is " + avg_speed +" meter/min");
                        if (avg_speed == 0)
                            avg_speed = (int) data.getVehicle_speed();
                        Stops stop = stopservice.getStop(route_stop.getStop_id());
                        double eta = etaFinder.getEta(data.getGps_lat(), data.getGps_long(),
                                Double.parseDouble(stop.getLatitude()), Double.parseDouble(stop.getLongitude()),
                                avg_speed);
                        System.out.println("the eta to bus stop " + stop.getStop_name() + " is " + eta);
                        int late_time = etaservice.calculateTimeDifferenceUsingTimeStamp(data.getTime(),
                                route_stop.getStop_time());
                         System.out.println("late time " + late_time);

                        if ((late_time > SystemConstants.ETA_LATE_TIME) || late_time < 0) {
                            String etaTime = etaservice.calculateExpectedTime(route_stop.getStop_time(), late_time);
                            /*
                             * if (eta == 0){ expected_time = "NA";
                             * msg=SystemConstants.ETA_MSG; }
                             * 
                             * else {
                             */
                            expected_time = etaTime;
                            msg = "Bus " + data.getVehicle_id() + " will be late at stop "
                                    + route_stop.getStop().getStop_name() + "  and expected time to reach is  "
                                    + expected_time;
                            System.out.println("msg:"+msg);
                            // }
                            if (eta > 0) {
                                EtaProcess etadata = etaservice.getEtaStopCount(data.getCreated_at(),
                                        route_stop.getStop_id(), trip.getId(), trip.getTripDetail().getBusid());
                                if (etadata == null) {
                                    // sendmail here
                                    // sendmail.sendMail(trip.getId(),msg,routeStopscheck.getStop_id());
                                    // sendmail.sendSms(trip.getId(), msg,
                                    // routeStopscheck.getStop_id());
                                    etaservice.insertEtaProcess(data, trip, route_stop, expected_time, 0);
                                    logger.info("Eta Inserted");
                                } else {
                                    if (!(expected_time.equals(etadata.getExpected_time()))) {
                                        // sendEmailAndSms(routeStopsEntity,
                                        // data, sendMail,
                                        // sendSms,
                                        // expected_time,tripEntity.getId());
                                        // sendmail.sendMail(trip.getId(),msg,routeStopscheck.getStop_id());
                                        etaservice.updateEtaProcess(data, trip, route_stop, expected_time,
                                                etadata.getCount(), etadata.getId());
                                        // sendmail.sendSms(trip.getId(), msg,
                                        // routeStopscheck.getStop_id());
                                        logger.info("Eta Updated " + etadata.getStop_name() + " with expected time "
                                                + expected_time);
                                    }
                                }
                            }
                        } else {
                            logger.info("Bus " + data.getVehicle_id() + " is on Time....So ETA has not been Sent");
                        }
                    }

                    // }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
