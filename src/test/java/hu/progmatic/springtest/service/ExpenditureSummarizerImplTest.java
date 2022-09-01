package hu.progmatic.springtest.service;

import hu.progmatic.springtest.model.Category;
import hu.progmatic.springtest.model.Currency;
import hu.progmatic.springtest.model.Expenditure;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class ExpenditureSummarizerImplTest {

    @Test
    void getExpenditureSum() {
        ExpenditureStorage expenditureStorage = () -> List.of(
                new Expenditure(LocalDate.now(), Category.LEISURE, Currency.HUF, 50000.0),
                new Expenditure(LocalDate.now(), Category.LEISURE, Currency.EUR, 100.0)
        );

        CurrencyConverter currencyConverter = new CurrencyConverter() {
            @Override
            public double convert(Currency base, Currency quote, double amount) {
                return switch (base) {
                    case HUF -> amount;
                    case EUR -> amount * 500.0;
                    default -> throw new IllegalStateException();
                };
            }
        };

        ExpenditureSummarizer expenditureSummarizer = new ExpenditureSummarizerImpl(
                expenditureStorage,
                currencyConverter
        );

        double sum = expenditureSummarizer.getExpenditureSum(Currency.HUF);

        assertEquals(100000.0, sum);
    }

    @Test
    void getExpenditureSum2() {
        ExpenditureStorage expenditureStorage = new ExpenditureStorageTestImpl();

        CurrencyConverter currencyConverter = new CurrencyConverterTestImpl();

        ExpenditureSummarizer expenditureSummarizer = new ExpenditureSummarizerImpl(
                expenditureStorage,
                currencyConverter
        );

        double sum = expenditureSummarizer.getExpenditureSum(Currency.HUF);

        // jUnit 5 assert-ek
        // assertTrue(true);
        // assertFalse(false);
        // assertEquals(150000.0, sum);

        // Hamcrest
        // https://www.baeldung.com/java-junit-hamcrest-guide
        // https://www.swtestacademy.com/hamcrest-matchers/

        assertThat(sum, is(equalTo(150000.0)));
        // létezik assertNotEquals, de tegyük fel, hogy kicsit összetettebb a példa esetében
        // az assertTrue és assertFalse marad csak sima jUnit 5-ben
        assertFalse(Math.abs(sum - 250000.0) <= 0.0001);
        assertThat(sum, is(not(equalTo(250000.0))));

        assertTrue(Math.abs(sum - 100000.0) <= 0.0001 || Math.abs(sum - 150000.0) <= 0.0001);
        assertThat(sum, anyOf(is(equalTo(100000.0)), is(equalTo(150000.0))));
    }
}