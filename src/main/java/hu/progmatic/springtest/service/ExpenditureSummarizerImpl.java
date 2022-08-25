package hu.progmatic.springtest.service;

import hu.progmatic.springtest.model.Category;
import hu.progmatic.springtest.model.Currency;
import hu.progmatic.springtest.model.Expenditure;

import java.time.LocalDate;
import java.util.List;

public class ExpenditureSummarizerImpl implements ExpenditureSummarizer {

    private final ExpenditureStorage expenditureStorage;

    private final CurrencyConverter currencyConverter;

    public ExpenditureSummarizerImpl(
            ExpenditureStorage expenditureStorage,
            CurrencyConverter currencyConverter
    ) {
        this.expenditureStorage = expenditureStorage;
        this.currencyConverter = currencyConverter;
    }


    public double getExpenditureSum(Currency currency) {
        List<Expenditure> expenditures = expenditureStorage.loadExpenditures();

        double sum = 0.0;

        for (Expenditure expenditure : expenditures) {
            sum += currencyConverter.convert(
                    expenditure.getCurrency(), // pl. EUR
                    currency, // HUF?
                    expenditure.getAmount() // 100
            );
        }

        return sum;
    }

    public double getExpenditureSum(
            Currency currency,
            Category category
    ) {
        throw new UnsupportedOperationException();
    }

    public double getExpenditureSum(
            Currency currency,
            LocalDate dateFrom,
            LocalDate dateTo
    ) {
        throw new UnsupportedOperationException();
    }
}
