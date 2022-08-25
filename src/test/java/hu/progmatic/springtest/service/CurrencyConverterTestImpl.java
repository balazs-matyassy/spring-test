package hu.progmatic.springtest.service;

import hu.progmatic.springtest.model.Currency;

public class CurrencyConverterTestImpl implements CurrencyConverter {

    @Override
    public double convert(Currency base, Currency quote, double amount) {
        // teszt implementáció csak forintra tud váltani
        if (quote != Currency.HUF) {
            throw new IllegalStateException();
        }

        return switch (base) {
            case HUF -> amount;
            case EUR -> amount * 500.0;
            case USD -> amount * 400.0;
        };
    }
}
