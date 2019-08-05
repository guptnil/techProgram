package com.calc.common;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

import com.calc.datamanager.FileDataManagerImpl;

/**
 * This class is responsible for parsing the inputs supplied by user to get the fx rate.
 *
 */
public class CommandLineParser {
	
	static Logger logger = Logger.getLogger(FileDataManagerImpl.class.getName());
	
	private String baseCurrency;
	
	private String termsCurrency;
	
	private Double amount;

	public void parse(String[] args){
		try {
			Scanner in = null;
			if(args==null || !(args.length > 0)) {
				in = new Scanner(System.in);  
				System.out.print("Please enter input in format -> <ccy1> <amount1> in <ccy2> or type QUIT to exit from the application");  
	            String input = in.nextLine(); 
	            args = input.split(" ");
	        }
			// exit the application if user chooses to quit.
			if (args[0].equalsIgnoreCase("QUIT")) {
				logger.info("Exiting the application");
				in.close();
				System.exit(0);	
			}
			baseCurrency = args[0];
			amount = Double.valueOf(args[1]);
			termsCurrency = args[3];
			logger.info("User entered BaseCurrency = "+ baseCurrency + " and " +termsCurrency + " = "+ " and amount = " +amount);
		 } catch (Exception e) {
			throw new InputMismatchException("Please enter input in format -> <ccy1> <amount1> in <ccy2> or type QUIT to exit from the application");
		 }
	 }
	 
	public String getBaseCurrency() {
		return baseCurrency;
	}

	public String getTermsCurrency() {
		return termsCurrency;
	}

	public Double getAmount() {
		return amount;
	}
}
