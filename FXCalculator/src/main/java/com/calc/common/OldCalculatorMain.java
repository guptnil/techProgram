package com.calc.common;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.calc.service.FXRateService;
import com.calc.service.FXRateServiceImpl;


/**
 * Main runner to start the application for FX rate calculation.
 * It first parses the command line arguments through Command Line Parser to get currencies and amount
 * and then it calls the underlying service to calculate the fx rate
 * 
 *
 */
public class CalculatorMain {
	
	static Logger logger = Logger.getLogger(CalculatorMain.class.getName());
	
	public static void main(String[] args) {
		try {
			CommandLineParser parser = new CommandLineParser();
			parser.parse(args);
			String baseCurrency = parser.getBaseCurrency();
			String termsCurrency = parser.getTermsCurrency();
			Double amount = parser.getAmount();
			
			FXRateService fxRateService = new FXRateServiceImpl();
			Double result = fxRateService.calculateFXRate(baseCurrency, termsCurrency, amount);
			logger.info("FX rate is " +result);
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		
	}


	

}
