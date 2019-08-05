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
			if(!(args.length > 0)) {
				Scanner in = new Scanner(System.in);  
				System.out.print("Please enter input in format -> <ccy1> <amount1> in <ccy2>");  
	            String input = in.nextLine(); 
	            args = input.split(" ");
	            in.close(); 
			}
			
			baseCurrency = args[0];
			amount = Double.valueOf(args[1]);
			termsCurrency = args[3];
			
			logger.info("User entered BaseCurrency = "+ baseCurrency + " and " +termsCurrency + " = "+ " and amount = " +amount);
		 } catch (Exception e) {
			throw new InputMismatchException("Please enter input in format -> <ccy1> <amount1> in <ccy2>");
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
