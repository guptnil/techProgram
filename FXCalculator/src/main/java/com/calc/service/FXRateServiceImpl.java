package com.calc.service;

import java.util.logging.Logger;

import com.calc.datamanager.DataManager;
import com.calc.datamanager.DataManagerFactory;
import com.calc.model.CurrencyPair;

public class FXRateServiceImpl implements FXRateService{
	
	static Logger logger = Logger.getLogger(FXRateServiceImpl.class.getName());
	
	private static final String DIRECT_FEED = "D";
	
	private static final String INVERTED = "Inv";
	
	private static final String UNITY = "Unity";
	
	DataManager dataManager ;
	
	public FXRateServiceImpl() {
		super();
		this.dataManager = DataManagerFactory.getDataManager(DataManagerFactory.FILE);
	}

	/**
	 * It returns the amount as is if the baseCurrency and termsCurrency are same.
	 * Else it lookup for the currency conversion method in currency matrix for given currencies and accordingly calculates the fx rate
	 * e.g if the currency conversion method is "D", it directly fetches the fx rate for currency pair
	 * if currency conversion method is "Inv", it fetches the fx rate by flipping the base and terms currencies
	 * if currency conversion method is another currency , that indicates we need to cross via this currency e.g
	 * if we have to find AUDJPY rate and currenct conversion matrix gives "USD" as value then we need to 
	 *  use the AUDUSD and USDJPY rates. This works by first converting AUD to USD, and then USD to JPY.
	 *  Finally the rate is formatted as per the precision of terms currency configured.
	 */
	@Override
	public Double calculateFXRate(String baseCurrency, String termsCurrency, Double amount) {
		Double result = amount;
		CurrencyPair currencyPair = new CurrencyPair(baseCurrency, termsCurrency);
		try {
			String matrixValue = dataManager.getCurrencyConversionMethod(currencyPair);
			logger.info("Matrix value for " + baseCurrency + " and " +termsCurrency + " is " +matrixValue);
			Double fxRate = 0.0;
			switch (matrixValue) {
				case DIRECT_FEED:
					fxRate = dataManager.getFXRate(currencyPair);
					result = fxRate * amount;
					break;
				case INVERTED:
					fxRate = dataManager.getFXRate(new CurrencyPair(termsCurrency, baseCurrency));
					result = 1/fxRate * amount;
					break;
				case UNITY:
					result = amount;
					break;
				default:
					result = calculateFXRate(baseCurrency,matrixValue, result);
					result = calculateFXRate(matrixValue,termsCurrency, result);
					break;
			}
			
			int precision = dataManager.getAmountPrecision(termsCurrency);
			result = format(result, precision);
		} catch (Exception e) {
			throw new RuntimeException("Unable to find rate for " +baseCurrency + "/" +termsCurrency);
		}
		return result;
	}
	
	/**
	 * This method formats the given number in a given precision
	 * @param value
	 * @param position
	 * @return
	 */
	private double format(double value, int position)
    {
        double temp = Math.pow(10.0, position);
        value *= temp;
        value = Math.round(value);
        return (value / (double)temp);
    }

}
