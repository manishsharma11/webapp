package com.main.sts.dao.sql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.main.sts.entities.Address;
import com.main.sts.entities.Alerts;
import com.main.sts.entities.Staff;
import com.main.sts.entities.Transport;
import com.main.sts.util.StaffJspEntity;

@Repository
public class StaffDao extends BaseDao {

	public void insert(Staff staff) {
		insertEntity(staff);
	}

	public List<Staff> getStaff() {
		return getAllRecords(Staff.class);
	}

	public void insertAlert(Alerts alerts) {
		insertEntity(alerts);
	}

	public void insertTransport(Transport transport) {
		insertEntity(transport);
	}

	public void insertAddress(Address address) {
		insertEntity(address);
	}

	public Staff getstaff(String staff_id) {
		String query = "from Staff where staff_id=?";
		Map<Integer, Object> parameter = new HashMap<Integer, Object>();
		parameter.put(0, staff_id);
		return getSingleRecord(Staff.class, query, parameter);

	}

	public Staff getStaff(int id) {
		String query = "from Staff where id=?";
		Map<Integer, Object> parameter = new HashMap<Integer, Object>();
		parameter.put(0, id);
		return getSingleRecord(Staff.class, query, parameter);
	}

	public Address getAddress(int subscriber_id, String type) {
		String query = "from Address where subscriber_id=? and subscriber_type=?";
		Map<Integer, Object> parameter = new HashMap<Integer, Object>();
		parameter.put(0, subscriber_id);
		parameter.put(1, type);
		return getSingleRecord(Address.class, query, parameter);
	}

	public Transport getTransport(int subscriber_id, String trans_type) {
		String query = "from Transport where subscriber_id=? and transport_type=?";
		Map<Integer, Object> parameter = new HashMap<Integer, Object>();
		parameter.put(0, subscriber_id);
		parameter.put(1, trans_type);
		return getSingleRecord(Transport.class, query, parameter);
	}

	public Alerts getAlerts(int subscriber_id, String subscriber_type, String alert_type) {
		String query = "from Alerts b where b.subscriber_id=? and b.subscriber_type=? and b.alert_type=?";
		Map<Integer, Object> parameters = new HashMap<Integer, Object>();
		parameters.put(0, subscriber_id);
		parameters.put(1, subscriber_type);
		parameters.put(2, alert_type);
		return getSingleRecord(Alerts.class, query, parameters);
	}

	public void update(Staff staff) {
		updateEntity(staff);

	}

	public List<StaffJspEntity> getStaffDetails() {
		String query = "select s.id,s.full_name,s.rfid_number,s.mobile_number,s.staff_id,b.bus_licence_number as"
				+ " bus_licence_number_home,b1.bus_licence_number as bus_licence_number_school,"
				+ "st.stop_name as stop_name_home ,st1.stop_name as stop_name_school from staff as s "
				+ "LEFT JOIN transport as t on s.id = t.subscriber_id and t.transport_type='Pick Up' "
				+ "LEFT JOIN transport as t1 on s.id = t1.subscriber_id and t1.transport_type='Drop Off' "
				+ "LEFT JOIN buses as b on t.bus_id =b.bus_id  " + " LEFT JOIN buses as b1 on t1.bus_id =b1.bus_id "
				+ "LEFT JOIN stops as st on t.stop_id =st.id  " + "LEFT JOIN stops as st1 on t1.stop_id =st1.id";
		Map<Integer, Object> parameter = new HashMap<Integer, Object>();
		return getStopsBySqlQuery(StaffJspEntity.class, query, parameter);

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public <T> List<T> getStaffBySqlQuery(Class<T> clz, String queryStr, Map<Integer, Object> parameters) {
		Session session = null;
		SQLQuery query = null;
		try {
			session = openSession();
			query = session.createSQLQuery(queryStr);
			for (Integer k : parameters.keySet()) {
				query.setParameter(k, parameters.get(k));
			}
			query.addEntity(StaffJspEntity.class);
			List<StaffJspEntity> list = query.list();

			return (List<T>) list;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}

	}

	public List<StaffJspEntity> search(String column, String value) {
		 Map< String, Object> restrictions=new HashMap<String, Object>();
		 restrictions.put("full_name", column);
		 restrictions.put("rfid_number", "%" +value+ "%");
		 return searchRecords(StaffJspEntity.class, restrictions);
		
	}

}
