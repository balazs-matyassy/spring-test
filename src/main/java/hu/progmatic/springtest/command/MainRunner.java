package hu.progmatic.springtest.command;

import org.springframework.boot.CommandLineRunner;

public class MainRunner implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {
        double sum = 0.0; // TODO

        System.out.printf("Összes kiadás: %.2f", sum);
    }
}
