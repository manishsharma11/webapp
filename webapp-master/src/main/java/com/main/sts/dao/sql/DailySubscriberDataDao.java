package com.main.sts.dao.sql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.main.sts.entities.DailySubscriberData;

@Repository
public class DailySubscriberDataDao extends BaseDao {

	public DailySubscriberData getDailySubscriberData(int id) {
		String query = "from DailySubscriberData b where b.id=?";
		Map<Integer, Object> parameters = new HashMap<Integer, Object>();
		parameters.put(0, id);
		return getSingleRecord(DailySubscriberData.class, query, parameters);
	}

	public DailySubscriberData getDailySubscriberData(int trip_id, String date, int subscriber_id,
			String subscriber_type) {
		String query = "from DailySubscriberData b where b.trip_id=? and b.date = ? and b.subscriber_id = ? and b.subscriber_type = ?";
		Map<Integer, Object> parameters = new HashMap<Integer, Object>();
		parameters.put(0, trip_id);
		parameters.put(1, date);
		parameters.put(2, subscriber_id);
		parameters.put(3, subscriber_type);
		return getSingleRecord(DailySubscriberData.class, query, parameters);
	}

	public List<DailySubscriberData> getDailySubscribersLeftOnBus(int trip_id, String date) {
		String query = "from DailySubscriberData b where b.trip_id=? and b.date = ? ";
		Map<Integer, Object> parameters = new HashMap<Integer, Object>();
		parameters.put(0, trip_id);
		parameters.put(1, date);
		
		return getRecords(DailySubscriberData.class, query, parameters);
	}

	public List<DailySubscriberData> getDailySubscribers(int trip_id, String date) {
		String query = "from DailySubscriberData b where b.trip_id=? and b.date = ? ";
		Map<Integer, Object> parameters = new HashMap<Integer, Object>();
		parameters.put(0, trip_id);
		parameters.put(1, date);

		return getRecords(DailySubscriberData.class, query, parameters);
	}
	
	public List<DailySubscriberData> getDailySubscribers(int trip_id, String date,String type) {
		String query = "from DailySubscriberData b where b.trip_id=? and b.date = ? and b.subscriber_type=?";
		Map<Integer, Object> parameters = new HashMap<Integer, Object>();
		parameters.put(0, trip_id);
		parameters.put(1, date);
		parameters.put(2, type);
		return getRecords(DailySubscriberData.class, query, parameters);
	}

	public List<DailySubscriberData> getDailyStudents(String name, String date, String type) {
		String query = "from DailySubscriberData b where b.subscriber_name=? and b.date = ? and b.subscriber_type=?";
		Map<Integer, Object> parameters = new HashMap<Integer, Object>();
		parameters.put(0, name);
		parameters.put(1, date);
		parameters.put(2, type);
		return getRecords(DailySubscriberData.class, query, parameters);
	}
}
