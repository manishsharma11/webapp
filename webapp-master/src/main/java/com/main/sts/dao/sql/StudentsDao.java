package com.main.sts.dao.sql;

import java.util.ArrayList;
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
import com.main.sts.entities.Parents;
import com.main.sts.entities.Students;
import com.main.sts.entities.Transport;
import com.main.sts.util.StudentsJspEntity;

@Repository
public class StudentsDao extends BaseDao {

	public List<Students> getAllStudents() {
		return getAllRecords(Students.class);
	}

	public void insertStudent(Students student) {
		insertEntity(student);
	}

	public void insertParent(Parents parent) {
		insertEntity(parent);
	}

	public void insertAddress(Address address) {
		insertEntity(address);
	}

	public void insertAlerts(Alerts alerts) {
		insertEntity(alerts);
	}

	public void insertTransport(Transport transport) {
		insertEntity(transport);
	}

	public List<StudentsJspEntity> getStudentsDetails() {

		/*
		 * String query =
		 * "select students.id, students.first_name,students.last_name,students.gr_number,students.rfid_number,stops.stop_name ,"
		 * + "buses.bus_licence_number ,transport.transport_type " +
		 * " from students     INNER JOIN transport  on students.id = transport.subscriber_id "
		 * + " INNER JOIN buses   on transport.bus_id =buses.bus_id " +
		 * " INNER JOIN stops   on transport.stop_id =stops.id ";
		 */

		String query = "select s.id,s.first_name,s.last_name,s.gr_number,s.rfid_number,b.bus_licence_number as "
				+ " bus_licence_number_home,b1.bus_licence_number as bus_licence_number_school, "
				+ " st.stop_name as stop_name_home ,st1.stop_name as stop_name_school " + " from students as s "
				+ " LEFT JOIN transport as t on s.id = t.subscriber_id and t.transport_type='Pick Up' "
				+ " LEFT JOIN transport as t1 on s.id = t1.subscriber_id and t1.transport_type='Drop Off' "
				+ " LEFT JOIN buses as b on t.bus_id =b.bus_id " 
				+ " LEFT JOIN buses as b1 on t1.bus_id =b1.bus_id "
				+ " LEFT JOIN stops as st on t.stop_id =st.id " 
				+ " LEFT JOIN stops as st1 on t1.stop_id =st1.id";

		Map<Integer, Object> parameters = new HashMap<Integer, Object>();

		return getStopsBySqlQuery(StudentsJspEntity.class, query, parameters);
	}
	public List<StudentsJspEntity> getParentStudentsDetails(String fullname) {
		String query="";
		List<StudentsJspEntity> result=new ArrayList<StudentsJspEntity>();
		Map<Integer, Object> parameters = new HashMap<Integer, Object>();
		String query1="from Parents p where p.first_name ||' '|| p.last_name=?";
		Map<Integer, Object> param = new HashMap<Integer, Object>();
		param.put(0, fullname);
		List<Parents> p=getRecords(Parents.class, query1, param);
		if(p.size()!=0)
		{
			//System.out.println(" student ids are "+p);
			for (Parents parents : p) {
			parameters.put(0, parents.getStudent_id());
			query = "select s.id,s.first_name,s.last_name,s.gr_number,s.rfid_number,b.bus_licence_number as "
					+ " bus_licence_number_home,b1.bus_licence_number as bus_licence_number_school, "
					+ " st.stop_name as stop_name_home ,st1.stop_name as stop_name_school " + " from students as s "
					+ " LEFT JOIN transport as t on s.id = t.subscriber_id and t.transport_type='Pick Up' "
					+ " LEFT JOIN transport as t1 on s.id = t1.subscriber_id and t1.transport_type='Drop Off' "
					+ " LEFT JOIN buses as b on t.bus_id =b.bus_id " 
					+ " LEFT JOIN buses as b1 on t1.bus_id =b1.bus_id "
					+ " LEFT JOIN stops as st on t.stop_id =st.id " 
					+ " LEFT JOIN stops as st1 on t1.stop_id =st1.id where s.id=?";
			
			 StudentsJspEntity st=getStudentsBySqlQuery(StudentsJspEntity.class, query, parameters);
			 result.add(st);
		}
		
	}
		
		return result;
	}

	/*@SuppressWarnings("unchecked")
	@Transactional
	public <T> List<T> getStudentsBySqlQuery(Class<T> clz, String queryStr, Map<Integer, Object> parameters) {
		Session session = null;
		SQLQuery query = null;
		try {
			session = openSession();
			query = session.createSQLQuery(queryStr);
			for (Integer k : parameters.keySet()) {
				query.setParameter(k, parameters.get(k));
			}
			query.addEntity(StudentsJspEntity.class);
			List<StudentsJspEntity> list = query.list();

			
			 * for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			 * StudentsJspEntity employee = (StudentsJspEntity) iterator.next();
			 * System.out.print(" Name: " + employee.getFirst_name());
			 * 
			 * }
			 * 
			 * System.out.println(list);
			 
			return (List<T>) list;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}

	}*/

	public Students getStudent(int id) {
		String query = "from Students b where b.id=?";
		Map<Integer, Object> parameters = new HashMap<Integer, Object>();
		parameters.put(0, id);
		return getSingleRecord(Students.class, query, parameters);
	}

	public Parents getParent(int student_id) {
		String query = "from Parents b where b.student_id=?";
		Map<Integer, Object> parameters = new HashMap<Integer, Object>();
		parameters.put(0, student_id);
		return getSingleRecord(Parents.class, query, parameters);
	}

	// subscriber_id
	public Alerts getAlerts(int subscriber_id, String subscriber_type, String alert_type) {
		String query = "from Alerts b where b.subscriber_id=? and b.subscriber_type=? and b.alert_type=?";
		Map<Integer, Object> parameters = new HashMap<Integer, Object>();
		parameters.put(0, subscriber_id);
		parameters.put(1, subscriber_type);
		parameters.put(2, alert_type);
		return getSingleRecord(Alerts.class, query, parameters);
	}

	public Address getAddress(int subscriber_id, String subscriber_type) {
		String query = "from Address b where b.subscriber_id=? and b.subscriber_type=? ";
		Map<Integer, Object> parameters = new HashMap<Integer, Object>();
		parameters.put(0, subscriber_id);
		parameters.put(1, subscriber_type);

		return getSingleRecord(Address.class, query, parameters);
	}

	public Students getStudentByName(String string, String string2) {
		String query = "from Students b where b.first_name=? and b.last_name=?";
		Map<Integer, Object> parameters = new HashMap<Integer, Object>();
		parameters.put(0, string);
		parameters.put(1, string2);
		return getSingleRecord(Students.class, query, parameters);
	}

	public Students getStudentByGrNumber(String string) {
		String query = "from Students b where b.gr_number=?";
		Map<Integer, Object> parameters = new HashMap<Integer, Object>();
		parameters.put(0, string);
		return getSingleRecord(Students.class, query, parameters);
	}

	public Students getStudentRfid(String string) {
		String query = "from Students b where b.rfid_number=?";
		Map<Integer, Object> parameters = new HashMap<Integer, Object>();
		parameters.put(0, string);
		return getSingleRecord(Students.class, query, parameters);
	}

	public Parents getparentByEmail(String string) {
		String query = "from Parents b where b.email=?";
		Map<Integer, Object> parameters = new HashMap<Integer, Object>();
		parameters.put(0, string);
		return getSingleRecord(Parents.class, query, parameters);
		
	}
	public Parents getparentByMobile(String string) {
		String query = "from Parents b where b.mobile=?";
		Map<Integer, Object> parameters = new HashMap<Integer, Object>();
		parameters.put(0, string);
		return getSingleRecord(Parents.class, query, parameters);
		
	}

	public List<Students> search(String str, String searchOption) {
		 Map<String, Object> restriction=new HashMap<String, Object>();
		 restriction.put(searchOption, "% "+str+"%");
		return searchRecords(Students.class, restriction);
	}
}
