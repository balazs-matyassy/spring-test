package hu.progmatic.springtest.command;

import hu.progmatic.springtest.model.Currency;
// import hu.progmatic.springtest.service.CurrencyConverter;
import hu.progmatic.springtest.service.ExpenditureSummarizer;
import org.springframework.boot.CommandLineRunner;

import java.util.function.Consumer;

public class MainRunner implements CommandLineRunner {
    private final ExpenditureSummarizer expenditureSummarizer;

    private final Consumer<String> logger;

    public MainRunner(ExpenditureSummarizer expenditureSummarizer, Consumer<String> logger) {
        this.expenditureSummarizer = expenditureSummarizer;
        this.logger = logger;
    }

    @Override
    public void run(String... args) throws Exception {
        // main-ben nem hivatkozhatunk a test-ben található osztályokra,
        // mivel build esetén eltávolításra kerülnek,
        // tehát nem működne élesben a program
        // CurrencyConverter converter = new CurrencyConverterTestImpl();

        double sum = expenditureSummarizer.getExpenditureSum(Currency.HUF);
        String output = String.format("Összes kiadás: %.2f\n", sum);
        logger.accept(output);
    }
}
