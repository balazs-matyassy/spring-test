package hu.progmatic.springtest.service;

import hu.progmatic.springtest.model.Category;
import hu.progmatic.springtest.model.Currency;
import hu.progmatic.springtest.model.Expenditure;

import java.time.LocalDate;
import java.util.List;

public class ExpenditureStorageTestImpl implements ExpenditureStorage {
    @Override
    public List<Expenditure> loadExpenditures() {
        return List.of(
                new Expenditure(LocalDate.now(), Category.LEISURE, Currency.HUF, 20000.0),
                new Expenditure(LocalDate.now(), Category.LEISURE, Currency.USD, 200.0),
                new Expenditure(LocalDate.now(), Category.LEISURE, Currency.EUR, 100.0)
        );
    }
}
