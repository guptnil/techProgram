package com.calc.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.calc.datamanager.DataManager;
import com.calc.datamanager.DataManagerFactory;
import com.calc.model.CurrencyPair;

public class TestFileDataManager{
	
	DataManager dataManager;
	
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	@Before
	public void setup() {
		dataManager = DataManagerFactory.getDataManager(DataManagerFactory.FILE);
	}
	
	
	@Test
	public void testGetAmountPrecision() {
		Integer precision = dataManager.getAmountPrecision("JPY");
		assertEquals("Amount Precision should be same",new Integer(0), precision);
		
	}
	
	@Test
	public void testGetCurrencyConversionMethod() {
		CurrencyPair currencyPair = new CurrencyPair("DKK", "GBP");
		String value = dataManager.getCurrencyConversionMethod(currencyPair);
		assertEquals("Currency conversion method should be same","USD", value);
		
	}
	
	@Test
	public void testGetCurrencyConversionMethodDirect() {
		CurrencyPair currencyPair = new CurrencyPair("CAD", "USD");
		String value = dataManager.getCurrencyConversionMethod(currencyPair);
		assertEquals("Currency conversion method should be same","D", value);
		
	}
	
	@Test
	public void testGetCurrencyConversionMethodInverted() {
		CurrencyPair currencyPair = new CurrencyPair("NOK", "EUR");
		String value = dataManager.getCurrencyConversionMethod(currencyPair);
		assertEquals("Currency conversion method should be same","Inv", value);
		
	}
	
	@Test
	public void testGetFXRate() {
		CurrencyPair currencyPair = new CurrencyPair("EUR", "NOK");
		Double value = dataManager.getFXRate(currencyPair);
		assertEquals("FX rate should be same",new Double(8.6651), value);
		
	}
	
	
	@After
	public void tearDown() {
		dataManager = null;
	}

}
