package com.main.sts.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.sts.dao.sql.DailySubscriberDataDao;
import com.main.sts.entities.DailySubscriberData;

@Service
public class DailySubscriberDataService {

    private static final Logger logger = Logger.getLogger(DailySubscriberDataService.class);

    @Autowired
    private DailySubscriberDataDao dailySubscriberDataDao;
    @Autowired
    private BusesService busesService;
    @Autowired
    private TransportService transportService;
    @Autowired
    private TripService tripService;
    @Autowired
    private StopsService stopsService;
    @Autowired
    private DailyRunningBusesService dailyRunningBusesService;

    public List<DailySubscriberData> getDailySubscribers(int trip_id, String date) {
        return dailySubscriberDataDao.getDailySubscribersLeftOnBus(trip_id, date);
    }

    public List<DailySubscriberData> getDailyStudentSubscribers(int trip, String date) {
        return dailySubscriberDataDao.getDailySubscribers(trip, date, "student");
    }

    public List<DailySubscriberData> getStudentByNameAndDate(String name, String date) {
        return dailySubscriberDataDao.getDailyStudents(name, date, "student");
    }

}
