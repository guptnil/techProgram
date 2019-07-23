package com.calc.datamanager;

import com.calc.model.CurrencyPair;

/**
 * This interface is responsible to retrieve the data from underlying data sources (db or file)
 * Currently it has got two implementations file and db but it can be extended in future.
 *
 */
public interface DataManager {
	
	/**
	 * Retrieves the fx rate for a given currency pair
	 * @param currencyPair
	 */
	public Double getFXRate(CurrencyPair currencyPair);
	
	/**
	 * Retrieves the precision for a given currenct
	 * @param currency
	 */
	public Integer getAmountPrecision(String currency);
	
	/**
	 * Retrieves the currency conversion method for a given currency pair
	 * @param currencyPair
	 */
	public String getCurrencyConversionMethod(CurrencyPair currencyPair);
	
	/**
	 * Updates the fx rate for a given currency pair
	 * @param currencyPair
	 */
	public void updateFXRate(CurrencyPair currencyPair);
	
	/**
	 * Updates the amount precision for a given currency 
	 * @param currencyPair
	 */
	public void updateAmountPrecision(String currency);
	
	/**
	 * Updates the fx currency conversion method for a given currency pair
	 * @param currencyPair
	 */
	public void updateCurrencyConversionMethod(CurrencyPair currencyPair);
	


}
