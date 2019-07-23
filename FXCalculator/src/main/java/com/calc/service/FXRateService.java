package com.calc.service;

/**
 * This class is responsible to calculate fx rate for a given currency and given amount.
 * It would apply any business rules during calculation.
 *
 */
public interface FXRateService {
	
	public Double calculateFXRate(String baseCurrency, String termsCurrency ,Double amount);

}
