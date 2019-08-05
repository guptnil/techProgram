package com.calc.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.calc.datamanager.FileDataManagerImpl;
import com.calc.service.FXRateService;
import com.calc.service.FXRateServiceImpl;

public class TestFXRateService {
	
	FXRateService fxRateService;
	
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	@Before
	public void setup() {
		fxRateService = new FXRateServiceImpl(new FileDataManagerImpl());
	}
	
	
	@Test
	public void testCalculateFXRateDirectFeed() {
		Double fxRate = fxRateService.calculateFXRate("AUD", "USD", 100.3);
		assertEquals("FX rate should be same", new Double(83.96),fxRate);
		
	}
	
	@Test
	public void testCalculateFXRateInvertedRate() {
		Double fxRate = fxRateService.calculateFXRate("CZK", "EUR", 200.0);
		assertEquals("FX rate should be same",new Double(7.25),fxRate);
		
	}
	
	@Test
	public void testCalculateFXRateCrossCurrency() {
		Double fxRate = fxRateService.calculateFXRate("DKK", "GBP", 80.0);
		assertEquals("FX rate should be same", new Double(8.44),fxRate);
		
	}
	
	@Test
	public void testCalculateFXRateSameCurrencies() {
		Double fxRate = fxRateService.calculateFXRate("AUD", "AUD", 100.3);
		assertEquals("FX rate should be same", new Double(100.3),fxRate);
		
	}
	@Test
	public void testPrecision() {
		Double fxRate = fxRateService.calculateFXRate("DKK", "JPY", 80.0);
		assertEquals("FX rate should be same", new Double(1588.0),fxRate);
		
	}
	
	@Test
	public void testCalculateFXRateRateNotFound() {
		exceptionRule.expect(RuntimeException.class);
		exceptionRule.expectMessage("Unable to find rate for KRW/GBP");
		fxRateService.calculateFXRate("KRW", "GBP", 100.3);
	}
	
	@After
	public void tearDown() {
		fxRateService = null;
	}

}
