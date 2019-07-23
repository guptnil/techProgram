package com.calc.datamanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.calc.model.CurrencyPair;

public class FileDataManagerImpl implements DataManager{
	
	static Logger logger = Logger.getLogger(FileDataManagerImpl.class.getName());
	
	private static Map<String,Map<String,Integer>> precisionMap = new ConcurrentHashMap<String,Map<String,Integer>>();
	private static Map<String,Map<CurrencyPair,Double>> rateMap = new ConcurrentHashMap<String,Map<CurrencyPair,Double>>();
	private static Map<String,Map<CurrencyPair,String>> currencyMatrixMap = new ConcurrentHashMap<String,Map<CurrencyPair,String>>();

	/* 
	 * Retrieves the fx rate for given currency pair from file and stores in a hashmap in jvm so that it doesn't have to parse the file everytime
	 * lookup for fx rate is required. Ideally the jvm cache should store the data against the business date of application or should ideally have an expiry mechanism
	 * but given that is not in the scope of this assignment, hence we are storing the data against system date(without timestamp ofcourse).
	 */
	@Override
	public Double getFXRate(CurrencyPair currencyPair) {
		String businessDate = getBusinessDate();
		if(!rateMap.containsKey(businessDate)) {
			logger.info("Rate map is empty , going to populate it");
			Map<CurrencyPair,Double> map = new HashMap<CurrencyPair,Double>();
			try (BufferedReader br = Files.newBufferedReader(Paths.get("src/main/resources/fxRate.properties"))) {
				map = br.lines().map(line -> line.split(":")).collect(Collectors.toMap(f ->  new CurrencyPair(f[0], f[1]) , f -> Double.valueOf(f[2])));
				rateMap.put(businessDate, map);
		    }
			catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return rateMap.get(businessDate).get(currencyPair);
	}

	/* 
	 * Retrieves the amount precision for a given currency from file and stores in a hashmap in jvm so that it doesn't have to parse the file everytime
	 * lookup for amount precision is required.Ideally the jvm cache should store the data against the business date of application or should ideally have an expiry mechanism
	 * but given that is not in the scope of this assignment, hence we are storing the data against system date(without timestamp ofcourse).
	 */
	@Override
	public Integer getAmountPrecision(String currency) {
		String businessDate = getBusinessDate();
		if(!precisionMap.containsKey(businessDate)) {
			logger.info("Amount Precision map is empty , going to populate it");
			Map<String,Integer> map = new HashMap<String,Integer>();
			try (BufferedReader br = Files.newBufferedReader(Paths.get("src/main/resources/currencyPrecision.properties"))) {
				map = br.lines().map(line -> line.split(":")).collect(Collectors.toMap(f ->  f[0] , f -> Integer.valueOf(f[1])));
				precisionMap.put(businessDate, map);
		    }
			catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return precisionMap.get(businessDate).get(currency);
	}
	
	/* 
	 * Retrieves the currency conversion method for a given currency pair from file and stores in a hashmap in jvm so that it doesn't have to parse the file everytime
	 * lookup for currency conversion method is required.Ideally the jvm cache should store the data against the business date of application or should ideally have an expiry mechanism
	 * but given that is not in the scope of this assignment, hence we are storing the data against system date(without timestamp ofcourse).
	 */
	@Override
	public String getCurrencyConversionMethod(CurrencyPair currencyPair) {
		String businessDate = getBusinessDate();
		if(!currencyMatrixMap.containsKey(businessDate)) {
			logger.info("Currency converion map is empty , going to populate it");
			Map<CurrencyPair,String> map = new HashMap<CurrencyPair,String>();
			try (BufferedReader br = Files.newBufferedReader(Paths.get("src/main/resources/currencyMatrix.properties"))) {
				map = br.lines().map(line -> line.split(":")).collect(Collectors.toMap(f ->  new CurrencyPair(f[0], f[1]) , f -> f[2]));
				currencyMatrixMap.put(businessDate, map);
		    }
			catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return currencyMatrixMap.get(businessDate).get(currencyPair);
	}
	
	/* 
	 * Returns system date as business date for the sake of it
	 */
	private String getBusinessDate() {
		Date date = Calendar.getInstance().getTime(); 
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
        String strDate = dateFormat.format(date);
        return strDate;
	}

	/* 
	 * Placeholder to update fx rate for future requirements
	 */
	@Override
	public void updateFXRate(CurrencyPair currencyPair) {
		// TODO Auto-generated method stub
		
	}

	/* 
	 * Placeholder to update amount precision for future requirements
	 */
	@Override
	public void updateAmountPrecision(String currency) {
		// TODO Auto-generated method stub
		
	}

	/* 
	 * Placeholder to update currency conversion method for future requirements
	 */
	@Override
	public void updateCurrencyConversionMethod(CurrencyPair currencyPair) {
		// TODO Auto-generated method stub
		
	}

}
