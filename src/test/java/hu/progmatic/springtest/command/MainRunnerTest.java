package hu.progmatic.springtest.command;

import hu.progmatic.springtest.model.Category;
import hu.progmatic.springtest.model.Currency;
import hu.progmatic.springtest.service.ExpenditureSummarizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MainRunnerTest {

    @Mock
    private ExpenditureSummarizer expenditureSummarizerMock;

    @InjectMocks
    private MainRunner mainRunner;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void run() throws Exception {
        ExpenditureSummarizer expenditureSummarizer = new ExpenditureSummarizer() {
            @Override
            public double getExpenditureSum(Currency currency) {
                return 10000.0;
            }

            @Override
            public double getExpenditureSum(Currency currency, Category category) {
                return 5000.0;
            }

            @Override
            public double getExpenditureSum(Currency currency, LocalDate dateFrom, LocalDate dateTo) {
                return 2500.0;
            }
        };

        MainRunner mainRunner = new MainRunner(expenditureSummarizer);
        mainRunner.run();
    }

    @Test
    void run2() throws Exception {
        // https://www.baeldung.com/mockito-series
        when(expenditureSummarizerMock.getExpenditureSum(Currency.HUF))
                .thenReturn(150000.0);
        mainRunner.run();
    }
}