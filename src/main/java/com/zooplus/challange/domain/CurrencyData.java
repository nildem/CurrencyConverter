package com.zooplus.challange.domain;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.zooplus.challange.cc.CurrencyConverterAdaptor;
import com.zooplus.challange.cc.CurrencyConverterFactory;
import com.zooplus.challange.pojo.ExchangeRate;

public class CurrencyData {
	private List<ExchangeRate> rates;
	@NotNull
	@NotBlank
	private String currentCurrency;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull
	@NotBlank
	private String currentDate;
	private ExchangeRate result;
	private String currencyFrom;

	public List<ExchangeRate> getRates() {
		return rates;
	}

	public void setRates(List<ExchangeRate> rates) {
		this.rates = rates;
	}

	public String getCurrentCurrency() {
		return currentCurrency;
	}

	public void setCurrentCurrency(String currentCurrency) {
		this.currentCurrency = currentCurrency;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentData) {
		this.currentDate = currentData;
	}

	public ExchangeRate getResult() {
		return result;
	}

	public void setResult(ExchangeRate result) {
		this.result = result;
	}

	public String getCurrencyFrom() {
		return CurrencyConverterFactory.getAdaptor().getBaseCurrency();
	}

}
