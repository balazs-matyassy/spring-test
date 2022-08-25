package hu.progmatic.springtest.command;

import hu.progmatic.springtest.model.Currency;
// import hu.progmatic.springtest.service.CurrencyConverter;
import hu.progmatic.springtest.service.ExpenditureSummarizer;
import org.springframework.boot.CommandLineRunner;

public class MainRunner implements CommandLineRunner {
    private final ExpenditureSummarizer expenditureSummarizer;

    public MainRunner(ExpenditureSummarizer expenditureSummarizer) {
        this.expenditureSummarizer = expenditureSummarizer;
    }

    @Override
    public void run(String... args) throws Exception {
        // main-ben nem hivatkozhatunk a test-ben található osztályokra,
        // mivel build esetén eltávolításra kerülnek,
        // tehát nem működne élesben a program
        // CurrencyConverter converter = new CurrencyConverterTestImpl();

        double sum = expenditureSummarizer.getExpenditureSum(Currency.HUF);
        System.out.printf("Összes kiadás: %.2f\n", sum);
    }
}
