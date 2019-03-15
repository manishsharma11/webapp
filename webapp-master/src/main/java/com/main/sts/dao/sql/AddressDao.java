package com.main.sts.dao.sql;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.main.sts.entities.Address;

@Repository
public class AddressDao extends BaseDao {

	public void insertAddress(Address address) {
		insertEntity(address);
	}

	public void updateAddress(Address address) {
		updateEntity(address);
	}

	public Address getAddress(int subscriber_id, String subscriber_type) {
		String query = "from Address b where b.subscriber_id=? and b.subscriber_type=? ";
		Map<Integer, Object> parameters = new HashMap<Integer, Object>();
		parameters.put(0, subscriber_id);
		parameters.put(1, subscriber_type);

		return getSingleRecord(Address.class, query, parameters);
	}
}
