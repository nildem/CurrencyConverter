package com.zooplus.challange.cc;

import java.util.List;

import com.zooplus.challange.pojo.ExchangeRate;

public interface CurrencyConverterAdaptor {
    public List<ExchangeRate> getRates();

    public String getBaseCurrency();
}
