package com.zooplus.challange.hibernate;

import java.util.Date;
import java.util.List;

import com.zooplus.challange.domain.Country;
import com.zooplus.challange.domain.UserData;
import com.zooplus.challange.pojo.ExchangeRate;

public interface IDataService {
	public void createAdmin();

	public String validateUser(String email, String password);

	public String createUser(UserData userData);

	public List<Country> getCountries();

	public List<String> getCurrencies();

	public List<ExchangeRate> getRatesForDate(String currencyTo, Date currentDate);

	public List<ExchangeRate> getRates(int number);
}
