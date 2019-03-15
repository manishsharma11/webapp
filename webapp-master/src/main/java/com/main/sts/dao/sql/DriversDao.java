package com.main.sts.dao.sql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.main.sts.entities.Drivers;
import com.main.sts.entities.RfidCards;

@Repository
public class DriversDao extends BaseDao {

	public List<Drivers> getAllDrivers() {
		return getAllRecords(Drivers.class);
	}

	public void insertDriver(Drivers drivers) {
		insertEntity(drivers);
	}

	public Drivers getDriver(int id) {
		String queryStr = "from Drivers d where d.id = ? ";
		Map<Integer, Object> parameters = new HashMap<Integer, Object>();
		parameters.put(0, id);

		return getSingleRecord(Drivers.class, queryStr, parameters);
	}

	public void updateDriver(Drivers driver) {
		updateEntity(driver);
	}

	public void deleteDriver(Drivers driver) {
		deleteEntity(driver);
	}

	public List<Drivers> searchDrivers(String str, String type) {

		Map<String, Object> restrictions = new HashMap<String, Object>();
		restrictions.put(type, "%" + str + "%");
		return searchRecords(Drivers.class, restrictions);

		
	}
}
