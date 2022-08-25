package hu.progmatic.springtest.command;

import hu.progmatic.springtest.model.Currency;
import hu.progmatic.springtest.service.ExpenditureSummarizer;
import org.springframework.boot.CommandLineRunner;

public class MainRunner implements CommandLineRunner {
    private final ExpenditureSummarizer expenditureSummarizer;

    public MainRunner(ExpenditureSummarizer expenditureSummarizer) {
        this.expenditureSummarizer = expenditureSummarizer;
    }

    @Override
    public void run(String... args) throws Exception {
        double sum = expenditureSummarizer.getExpenditureSum(Currency.HUF);
        System.out.printf("Összes kiadás: %.2f", sum);
    }
}
