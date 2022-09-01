package hu.progmatic.springtest.command;

import hu.progmatic.springtest.model.Category;
import hu.progmatic.springtest.model.Currency;
import hu.progmatic.springtest.service.ExpenditureSummarizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.function.Consumer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
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

        // metódus referencia
        // System.out::println kompatibilis a Consumer<String> funkcionális interfésszel
        MainRunner mainRunner = new MainRunner(expenditureSummarizer, System.out::println);
        mainRunner.run();
    }

    @Test
    void run2() throws Exception {
        // https://www.baeldung.com/mockito-series
        when(expenditureSummarizerMock.getExpenditureSum(Currency.HUF))
                .thenReturn(150000.0);
        mainRunner.run();
    }

    @Test
    void run3() throws Exception {
        // Minden tesztesetnél újra be kell állítani a metódusokat.
        when(expenditureSummarizerMock.getExpenditureSum(Currency.HUF))
                .thenReturn(150000.0);
        when(expenditureSummarizerMock.getExpenditureSum(Currency.EUR))
                .thenReturn(300.0);
        // Paramétertől függetlenül 12345.0 lesz a visszatérési érték.
        // when(expenditureSummarizerMock.getExpenditureSum(any(Currency.class)))
        //        .thenReturn(12345.0);

        // Természetesen továbbra is lehetőség van a konstruktoron keresztül átadni a mock objektumokat
        // függőségként. :)
        // Az @InjectMocks annotáció könnyebbé teszi az életünket,
        // illetve működésre jobban hasonlít a springre.

        // Lehet helyette a @Mock annotációt is használni
        Consumer<String> logger = Mockito.mock(Consumer.class);

        MainRunner mainRunner = new MainRunner(expenditureSummarizerMock, logger);

        mainRunner.run();

        verify(logger).accept("Összes kiadás: 10000,00");

        // Közvetlenül is "használható" a mock objektum,
        System.out.println(expenditureSummarizerMock.getExpenditureSum(Currency.HUF));
        System.out.println(expenditureSummarizerMock.getExpenditureSum(Currency.EUR));
        System.out.println(expenditureSummarizerMock.getExpenditureSum(Currency.USD));
        System.out.println(expenditureSummarizerMock.getExpenditureSum(Currency.EUR));
    }
}