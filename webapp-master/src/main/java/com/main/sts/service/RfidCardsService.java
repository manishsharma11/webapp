package com.main.sts.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.sts.dao.sql.RfidDao;
import com.main.sts.entities.RfidCards;

@Service
public class RfidCardsService {

	@Autowired
	private RfidDao rfidDao;

	public List<RfidCards> getRfidsByType(String type) {

		List<RfidCards> cards = rfidDao.listRfids(type);
		return cards;
	}

	public RfidCards getRfidCard(String rfid_number) {

		return rfidDao.getRfid(rfid_number);
	}

	public RfidCards getRfidCard(int id) {

		return rfidDao.getRfid(id);
	}

	public List<RfidCards> getAvailableRfids(String type, int rfid_status) {

		List<RfidCards> cards = rfidDao.listAvailableRfids(type, rfid_status);
		return cards;
	}

	public void addRfid(String rfid_number, String type) {

		RfidCards cards = new RfidCards();
		cards.setAllocated_time("none");
		cards.setAvailable(0);
		cards.setRfid_number(rfid_number);
		cards.setType(type);
		cards.setAllocated_person_name("none");
		cards.setActive(0);
		rfidDao.insertRfid(cards);
	}

	public boolean rfidExists(String rfid_number) {

		RfidCards card = rfidDao.getRfid(rfid_number);
		if (card == null)
			return false;
		else
			return true;
	}

	public boolean updateRfid(String current_rfid, String new_rfid) {
		boolean ret = false;
		try {

			if (rfidExists(new_rfid)) {
				ret = false;
			} else {
				rfidDao.updateRfidNumber(current_rfid, new_rfid);
				ret = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public boolean deleteRfid(String rfid_number) {
		try {
			RfidCards cards = rfidDao.getRfid(rfid_number);
			rfidDao.deleteRfid(cards.getId());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public List<RfidCards> searchRfids(String str, String type) {

		List<RfidCards> cards = rfidDao.searchRfids(str, type);
		return cards;
	}

	public void updateRfidWhenAllocated(int allocated_to, String allocated_person_name, String rfid_number) {

		RfidCards cards = getRfidCard(rfid_number);
		cards.setAllocated_person_name(allocated_person_name);
		cards.setAllocated_time(new Date().toString());
		cards.setAvailable(1);
		cards.setAllocated_to(allocated_to);
		rfidDao.updateRfid(cards);
	}

	public void updateRfidWhenDeallocated(String rfid_number) {

		RfidCards cards = getRfidCard(rfid_number);
		cards.setAllocated_person_name("none");
		cards.setAllocated_time("none");
		cards.setAvailable(0);
		cards.setAllocated_to(0);
		rfidDao.updateRfid(cards);
	}

	public void deleteRfid(RfidCards rfidCard) {
		rfidDao.deleteEntity(rfidCard);
	}
}
