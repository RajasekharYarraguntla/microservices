package com.raja;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {

    @PostMapping("/currency-exchange")
    public CurrencyConversion retrieveExchangeValue(@RequestBody CurrencyConversion conversion);

}
