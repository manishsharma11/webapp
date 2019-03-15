package com.main.sts.dao.sql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.main.sts.entities.DailyDriverData;

@Repository
public class DailyDriverDataDao extends BaseDao {

	public DailyDriverData getDailyDriverData(int id) {

		String queryStr = "from DailyDriverData d where d.id = ? ";
		Map<Integer, Object> parameters = new HashMap<Integer, Object>();
		parameters.put(0, id);

		return getSingleRecord(DailyDriverData.class, queryStr, parameters);
	}

	public void insertDailyDriverData(DailyDriverData dailyDriverData) {
		insertEntity(dailyDriverData);
	}

	public DailyDriverData getDailyDriverData(int trip_id, String date) {

		String queryStr = "from DailyDriverData d where d.trip_id = ? and date = ? ";
		Map<Integer, Object> parameters = new HashMap<Integer, Object>();
		parameters.put(0, trip_id);
		parameters.put(1, date);
		return getSingleRecord(DailyDriverData.class, queryStr, parameters);
	}

	public DailyDriverData getDailyDriverData(int trip_id, int driver_id, String date) {

		String queryStr = "from DailyDriverData d where d.trip_id = ? and date = ? and in_driver_id = ?";
		Map<Integer, Object> parameters = new HashMap<Integer, Object>();
		parameters.put(0, trip_id);
		parameters.put(1, date);
		parameters.put(2, driver_id);
		return getSingleRecord(DailyDriverData.class, queryStr, parameters);
	}

	public List<DailyDriverData> getDriverData(String date) {
		String queryStr = "from DailyDriverData d where d.date = ?";
		Map<Integer, Object> parameters = new HashMap<Integer, Object>();
		parameters.put(0, date);
		return getRecords(DailyDriverData.class, queryStr, parameters);
	}

	public List<DailyDriverData> getDriverDetails(String name, String date) {
		String queryStr = "from DailyDriverData d where d.date = ? and out_driver_name = ?";
		Map<Integer, Object> parameters = new HashMap<Integer, Object>();
		 
		parameters.put(0, date);
		parameters.put(1, name);
		return getRecords(DailyDriverData.class, queryStr, parameters);
	}
}
