package com.zooplus.challange.hibernate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.zooplus.challange.cc.CurrencyConverterAdaptor;
import com.zooplus.challange.cc.CurrencyConverterFactory;
import com.zooplus.challange.dao.ExchangeRateDAO;
import com.zooplus.challange.dao.UserDAO;
import com.zooplus.challange.domain.Country;
import com.zooplus.challange.domain.UserData;
import com.zooplus.challange.pojo.ExchangeRate;
import com.zooplus.challange.pojo.User;
import com.zooplus.challange.property.DateTimeUtils;
import com.zooplus.challange.util.CryptoUtil;
import com.zooplus.challange.util.ErrorManager;
import com.zooplus.challange.util.TextUtils;

public class BuiltinDataService implements IDataService {
	private UserDAO userDao;
	private ExchangeRateDAO rateDao;

	public BuiltinDataService() {
		userDao = new UserDAO();
		rateDao = new ExchangeRateDAO();
	}

	public void createAdmin() {
		User s = new User();
		s.setName("Nildem");
		s.setSurname("Ucok");
		s.setEmail("nildemdemir@yahoo.com");
		s.setPassHash(CryptoUtil.getBase64ofFIMDigest("123456"));
		s.setAddressLine1("Line 1 blabla");
		s.setAddressLine2("Line 2 tatata");
		s.setCity("Istanbul");
		s.setCountry("TR");
		s.setPostalCode("ABC123");
		s = userDao.addUser(s);
	}

	public String validateUser(String email, String password) {
		User user = userDao.findUserByEmail(email);
		if (user == null) {
			return ErrorManager.USERNOTFOUND;
		}
		if (!TextUtils.getNotNull(user.getPassHash()).equals(CryptoUtil.getBase64ofFIMDigest(password))) {
			return ErrorManager.INVALIDPASSWORD;
		}
		return "";
	}

	public String createUser(UserData userData) {
		User existing = userDao.findUserByEmail(userData.getEmail());
		if (existing != null) {
			return ErrorManager.USEREXISTS;
		}
		User s = new User();
		s.setName(userData.getName());
		s.setSurname(userData.getSurname());
		s.setEmail(userData.getEmail());
		s.setPassHash(CryptoUtil.getBase64ofFIMDigest(userData.getPassword()));
		s.setAddressLine1(userData.getAddressLine1());
		s.setAddressLine2(userData.getAddressLine2());
		s.setCity(userData.getCity());
		s.setCountry(userData.getCountry());
		s.setPostalCode(userData.getPostalCode());

		s = userDao.addUser(s);
		if (s != null)
			return "";
		return ErrorManager.SIGNUPERROR;
	}

	public List<Country> getCountries() {
		List<Country> countries = new ArrayList<Country>();
		countries.add(new Country("US", "United States"));
		countries.add(new Country("DE", "Germany"));
		countries.add(new Country("FR", "France"));
		countries.add(new Country("IT", "Italy"));
		countries.add(new Country("TR", "Turkey"));
		return countries;
	}

	public List<String> getCurrencies() {
		List<String> countries = new ArrayList<String>();
		countries.add("EUR");
		countries.add("USD");
		countries.add("GBP");
		countries.add("NZD");
		countries.add("AUD");
		return countries;
	}

	public List<ExchangeRate> getRatesForDate(String currencyTo, Date currentDate) {
		Date now = DateTimeUtils.getInstance().getDateFromTime(DateTimeUtils.getInstance().getUTCNowDateTime());
		CurrencyConverterAdaptor adaptor = CurrencyConverterFactory.getAdaptor();
		if (currentDate.compareTo(now) == 0) {
			List<ExchangeRate> list = adaptor.getRates();
			for (ExchangeRate exchangeRate : list) {
				if (currencyTo.equals(exchangeRate.getCurrencyTo())) {
					rateDao.addExchangeRate(exchangeRate);
					List<ExchangeRate> rates = new ArrayList<ExchangeRate>();
					rates.add(exchangeRate);
					return rates;
				}
			}
		} else {
			return rateDao.listRatesBetweenDates(adaptor.getBaseCurrency(), currencyTo, currentDate,
					DateTimeUtils.getInstance().addDifference(currentDate, Calendar.DAY_OF_MONTH, 1));
		}
		return null;
	}

	public List<ExchangeRate> getRates(int number) {
		return rateDao.listRates(number);
	}
}
