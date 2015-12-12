package com.zooplus.challange.hibernate;

import java.util.Date;
import java.util.List;

import com.zooplus.challange.domain.Country;
import com.zooplus.challange.domain.UserData;
import com.zooplus.challange.pojo.ExchangeRate;
import com.zooplus.challange.property.DateTimeUtils;
import com.zooplus.challange.property.PropertiesUtils;

public class DataManager {
	private static DataManager instance;
	IDataService manager;

	private DataManager() {
		try {
			manager = (IDataService) Class.forName(PropertiesUtils.getInstance().getProperty("datamanager.adapter",
					"com.zooplus.challange.hibernate.BuiltinDataService")).newInstance();
		} catch (Exception e) {
			manager = new BuiltinDataService();
		}
	}

	public static synchronized DataManager getInstance() {
		if (instance == null) {
			instance = new DataManager();
		}
		return instance;
	}

	public void createAdmin() {
		manager.createAdmin();
	}

	public String validateUser(String email, String password) {
		return manager.validateUser(email, password);
	}

	public String createUser(UserData userData) {
		return manager.createUser(userData);
	}

	public List<Country> getCountries() {
		return manager.getCountries();
	}

	public List<String> getCurrencies() {
		return manager.getCurrencies();
	}

	public List<ExchangeRate> getCurrentRate(String currencyTo, String currentDate) {
		return manager.getRatesForDate(currencyTo, DateTimeUtils.getInstance().getDate(currentDate));
	}

	public List<ExchangeRate> getRates(int number) {
		return manager.getRates(number);
	}
}
