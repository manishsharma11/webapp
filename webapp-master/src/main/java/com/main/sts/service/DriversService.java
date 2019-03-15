package com.main.sts.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.sts.dao.sql.DriversDao;
import com.main.sts.entities.Drivers;
import com.main.sts.entities.RfidCards;

@Service
public class DriversService {
	private static final Logger logger = Logger.getLogger(DriversService.class);

	@Autowired
	private DriversDao driversDao;
	@Autowired
	private RfidCardsService cardsService;

	public List<Drivers> listDrivers() {
		return driversDao.getAllDrivers();
	}

	public Drivers addDriver(HttpServletRequest request) {
		Drivers driver = new Drivers();
		driver.setDriver_id(request.getParameter("driver_id"));
		driver.setDriver_name(request.getParameter("driver_name"));
		driver.setRfid_number(request.getParameter("driver_rfid_number"));
		driver.setContact_number(request.getParameter("phone_number"));
		driver.setAvailable(0);
		driver.setActive(0);
		driver.setStreet(request.getParameter("street"));
		driver.setCity(request.getParameter("city"));
		driver.setState(request.getParameter("state"));
		driver.setZip(request.getParameter("zip"));
		driver.setCountry(request.getParameter("country"));

		driversDao.insertDriver(driver); // insert driver
		// update rfid when allocated
		return driver;
	}

	public Drivers getDriver(int id) {

		return driversDao.getDriver(id);
	}

	public void updateDriver(Drivers driver, String new_rfid_number) {

		if (!new_rfid_number.equals("")) {

			cardsService.updateRfidWhenAllocated(driver.getId(), driver.getDriver_name(), new_rfid_number); // allocated
																											// new
																											// rfid
																											// to
																											// driver
			logger.info("Rfid [ " + new_rfid_number + " ] allocated to driver [ " + driver.getDriver_name()
					+ " ] with Driver Id [ " + driver.getDriver_id() + " ] ");
			cardsService.updateRfidWhenDeallocated(driver.getRfid_number()); // de-allocate
																				// current
																				// driver
																				// rfid
			logger.info("Rfid [ " + driver.getRfid_number() + " ] de-allocated from driver [ "
					+ driver.getDriver_name() + " ] with Driver Id [ " + driver.getDriver_id() + " ] ");
			driver.setRfid_number(new_rfid_number);

		}
		driversDao.updateDriver(driver);
		logger.info("Driver [ " + driver.getDriver_name() + " ] with Driver Id [ " + driver.getDriver_id()
				+ " ] has been updated");
	}

	public void deleteDriver(int id) {
		Drivers driver = driversDao.getDriver(id);
		driversDao.deleteDriver(driver);
	}

	public void deleteDriver(Drivers driver) {
		driversDao.deleteDriver(driver);
	}

	public void updateDriverStatus(int did, int status) {
		Drivers driver = getDriver(did);
		driver.setAvailable(status);
		driversDao.updateDriver(driver);

	}

	public List<Drivers> searchDrivers(String str, String type) {

		List<Drivers> cards = driversDao.searchDrivers(str, type);
		return cards;
	}
}
