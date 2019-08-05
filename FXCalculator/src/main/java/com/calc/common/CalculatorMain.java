package com.calc.common;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.calc.service.FXRateService;

/**
 * Main runner to start the application for FX rate calculation.
 * It first parses the command line arguments through Command Line Parser to get currencies and amount
 * and then it calls the underlying service to calculate the fx rate
 * 
 *
 */
@SpringBootApplication
@ComponentScan("com.calc.*")
public class CalculatorMain implements CommandLineRunner {

	static Logger logger = Logger.getLogger(CalculatorMain.class.getName());
	
    @Autowired
    private FXRateService fxRateService;

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(CalculatorMain.class);
        app.run(args);

    }

	@Override
    public void run(String... args) throws Exception {
		try {
			CommandLineParser parser = new CommandLineParser();
			parser.parse(args);
			String baseCurrency = parser.getBaseCurrency();
			String termsCurrency = parser.getTermsCurrency();
			Double amount = parser.getAmount();
			
			Double result = fxRateService.calculateFXRate(baseCurrency, termsCurrency, amount);
			logger.info("FX rate is " +result);
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}

       
    }

}