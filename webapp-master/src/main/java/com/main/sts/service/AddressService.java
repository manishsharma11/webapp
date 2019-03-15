package com.main.sts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.sts.dao.sql.AddressDao;
import com.main.sts.entities.Address;

@Service
public class AddressService {

	@Autowired
	private AddressDao addressDao;
	
	
	public void insertAddress(Address address)
	{
		addressDao.insertAddress(address);
	}
	
	public void updateAddress(Address address)
	{
		addressDao.updateAddress(address);
	}
	public Address getAddress(int subscriber_id, String subscriber_type) {
		return addressDao.getAddress(subscriber_id, subscriber_type);
	}
}
