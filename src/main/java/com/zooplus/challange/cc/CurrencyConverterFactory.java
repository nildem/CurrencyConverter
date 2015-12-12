package com.zooplus.challange.cc;

import com.zooplus.challange.property.PropertiesUtils;

public class CurrencyConverterFactory {
    public static CurrencyConverterAdaptor getAdaptor() {
        try {
            return (CurrencyConverterAdaptor) Class.forName(
                    PropertiesUtils.getInstance().getProperty("cc.adapter",
                            "com.zooplus.challange.cc.OpenExchangeRatesAdapter")).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
