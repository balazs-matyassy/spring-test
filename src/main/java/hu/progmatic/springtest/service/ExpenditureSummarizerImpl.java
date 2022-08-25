package hu.progmatic.springtest.service;

import hu.progmatic.springtest.model.Category;
import hu.progmatic.springtest.model.Currency;
import hu.progmatic.springtest.model.Expenditure;

import java.time.LocalDate;
import java.util.List;

public class ExpenditureSummarizerImpl implements ExpenditureSummarizer {
    public double getExpenditureSum(List<Expenditure> expenditures, Currency currency) {
        return 0.0;
    }

    public double getExpenditureSum(
            List<Expenditure> expenditures,
            Currency currency,
            Category category
    ) {
        return 0.0;
    }

    public double getExpenditureSum(
            List<Expenditure> expenditures,
            Currency currency,
            LocalDate dateFrom,
            LocalDate dateTo
    ) {
        return 0.0;
    }
}
