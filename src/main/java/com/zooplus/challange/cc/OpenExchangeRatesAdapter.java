package com.zooplus.challange.cc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.zooplus.challange.pojo.ExchangeRate;
import com.zooplus.challange.property.PropertiesUtils;

public class OpenExchangeRatesAdapter implements CurrencyConverterAdaptor {
	public List<ExchangeRate> getRates() {
		String urlStr = PropertiesUtils.getInstance().getProperty("cc.OpenExchangeRatesAdapter.url",
				"https://openexchangerates.org/api/latest.json?app_id=")
				+ PropertiesUtils.getInstance().getProperty("cc.OpenExchangeRatesAdapter.appid",
						"1b708c4083384448ac84194ac0a3bde1");
		try {
			URL converterURL = new URL(urlStr);
			BufferedReader in = new BufferedReader(new InputStreamReader(converterURL.openStream()));
			StringBuffer lines = new StringBuffer();
			String line;
			while ((line = in.readLine()) != null) {
				lines.append(line);
			}
			in.close();
			Gson gson = new Gson();
			OpenExchangeRatesData data = gson.fromJson(lines.toString(), OpenExchangeRatesData.class);
			System.out.println("Timestamp" + data.getTimestamp());
			System.out.println(new Date(Long.valueOf(data.getTimestamp())).toString());
			if (data != null) {
				ArrayList<ExchangeRate> rates = new ArrayList<ExchangeRate>();
				for (String key : data.getRates().keySet()) {
					ExchangeRate erate = new ExchangeRate();
					erate.setCurrencyTo(key);
					erate.setCurrencyFrom(data.getBase());
					erate.setRate(new BigDecimal(data.getRates().get(key)));
					erate.setConvertionDate(new Date(Long.valueOf(data.getTimestamp())));
					rates.add(erate);
				}
				return rates;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getBaseCurrency() {
		return "USD";
	}
}
