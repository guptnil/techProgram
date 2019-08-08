package com.calc.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.calc.datamanager.DBDataManagerImpl;
import com.calc.datamanager.DataManager;
import com.calc.model.CurrencyPair;

public class TestDBDataManager{
	
	DataManager dataManager;
	
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	@Before
	public void setup() {
		dataManager = new DBDataManagerImpl();
	}
	
	
	@Test(expected=UnsupportedOperationException.class)
	public void testGetAmountPrecision() {
		dataManager.getAmountPrecision("JPY");
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testGetCurrencyConversionMethod() {
		CurrencyPair currencyPair = new CurrencyPair("DKK", "GBP");
		dataManager.getCurrencyConversionMethod(currencyPair);
		
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testGetFXRate() {
		CurrencyPair currencyPair = new CurrencyPair("EUR", "NOK");
		dataManager.getFXRate(currencyPair);
		
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testUpdateAmountPrecision() {
		dataManager.updateAmountPrecision("JPY");
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testUpdateCurrencyConversionMethod() {
		CurrencyPair currencyPair = new CurrencyPair("DKK", "GBP");
		dataManager.updateCurrencyConversionMethod(currencyPair);
		
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testUpdateFXRate() {
		CurrencyPair currencyPair = new CurrencyPair("EUR", "NOK");
		dataManager.updateFXRate(currencyPair);
		
	}
	
	@After
	public void tearDown() {
		dataManager = null;
	}

}
