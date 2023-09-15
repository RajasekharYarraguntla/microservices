package com.raja;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class CurrencyExchangeController {

    @Autowired
    private CurrencyExchangeRepository repository;

    @Autowired
    private Environment environment;

    @PostMapping("/currency-exchange")
    public CurrencyExchange retrieveExchangeValue(@RequestBody CurrencyExchange exchange) {

        log.info("retrieveExchangeValue called with {} to {}", exchange.getFrom(), exchange.getTo());

        CurrencyExchange currencyExchange = repository.findByFromAndTo(exchange.getFrom(), exchange.getTo());

        if (currencyExchange == null) {
            throw new RuntimeException("Unable to Find data for " + exchange.getFrom() + " to " + exchange.getTo());
        }

        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);
        return currencyExchange;

    }

}