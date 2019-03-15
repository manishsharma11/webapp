package com.main.sts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.sts.dao.sql.DailyDriverDataDao;
import com.main.sts.entities.DailyDriverData;

@Service
public class DailyDriverDataService {

	@Autowired
	private DailyDriverDataDao dailyDriverDataDao;

	public DailyDriverData getDailyDriverData(int trip_id, String date) {

		return dailyDriverDataDao.getDailyDriverData(trip_id, date);
	}

	public DailyDriverData getDailyDriverData(int trip_id, int driver_id, String date) {

		return dailyDriverDataDao.getDailyDriverData(trip_id, driver_id, date);
	}

	public List<DailyDriverData> getDriverByDate(String date) {
		 
		return dailyDriverDataDao.getDriverData(date);
	}

	public List<DailyDriverData> getDriverDataByDriverIdAndDate(String name, String date) {
		 return dailyDriverDataDao.getDriverDetails(name,date);
	}
}
