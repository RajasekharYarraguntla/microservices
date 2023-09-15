package com.raja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeProxy proxy;

    @GetMapping("/currency-conversion")
    public CurrencyConversion calculateCurrencyConversion(@RequestBody CurrencyConversion conversion) {

        CurrencyConversion currencyConversion = proxy.retrieveExchangeValue(conversion);
        return new CurrencyConversion(currencyConversion.getId(), conversion.getFrom(), conversion.getTo(), conversion.getQuantity(),
                currencyConversion.getConversionMultiple(), conversion.getQuantity().multiply(currencyConversion.getConversionMultiple()),
                currencyConversion.getEnvironment() + " " + "feign");
    }


}
