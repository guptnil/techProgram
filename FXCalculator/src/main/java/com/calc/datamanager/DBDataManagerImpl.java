package com.calc.datamanager;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.calc.model.CurrencyPair;

/**
 * This class is responsible for getting the data from underlying database but given using external libraries is out of scope for this application at the moment,
 * hence db operations are not currently supported.
 * In an ideal production application , data should be stored and retrieved from database 
 *
 */
@Service
@Qualifier("dbDataManager")
public class DBDataManagerImpl implements DataManager{

	@Override
	public Double getFXRate(CurrencyPair currencyPair) {
		throw new UnsupportedOperationException("Database operations currently not supported");
	}

	@Override
	public Integer getAmountPrecision(String currency) {
		throw new UnsupportedOperationException("Database operations currently not supported");
	}

	@Override
	public String getCurrencyConversionMethod(CurrencyPair currencyPair) {
		throw new UnsupportedOperationException("Database operations currently not supported");
	}

	@Override
	public void updateFXRate(CurrencyPair currencyPair) {
		throw new UnsupportedOperationException("Database operations currently not supported");
	}

	@Override
	public void updateAmountPrecision(String currency) {
		throw new UnsupportedOperationException("Database operations currently not supported");
	}

	@Override
	public void updateCurrencyConversionMethod(CurrencyPair currencyPair) {
		throw new UnsupportedOperationException("Database operations currently not supported");
	}

	

}
