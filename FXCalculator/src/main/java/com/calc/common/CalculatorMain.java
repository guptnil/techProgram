package com.calc.common;

import java.util.InputMismatchException;
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
    
    CommandLineParser parser = new CommandLineParser();
    
    public static void main(String[] args) throws Exception {
    	SpringApplication app = new SpringApplication(CalculatorMain.class);
        app.run(args);
    }
    
   	@Override
    public void run(String... args) throws Exception {
   		try {
			parser.parse(args);
			String baseCurrency = parser.getBaseCurrency();
			String termsCurrency = parser.getTermsCurrency();
			Double amount = parser.getAmount();
			
			Double result = fxRateService.calculateFXRate(baseCurrency, termsCurrency, amount);
			logger.info("FX rate is " +result);
			
			//It would keep prompting the user to enter the input for fx rate or quit the application.
    		run(args);
		}catch (InputMismatchException e) {
			//If user enters input in wrong format , It would prompt the user to either enter the input in correct format or quit the application.
    		run(args);
    	} 
	}

}