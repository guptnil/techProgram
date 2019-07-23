package com.calc.model;

import java.io.Serializable;

public class CurrencyPair implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3321378442492878214L;

	/**
	 * Source currency which needs to be converted
	 */
	private String baseCurrency;
	
	/**
	 * Target currency after conversion
	 */
	private String termsCurrency;
	
	public CurrencyPair(String baseCurrency, String termsCurrency) {
		super();
		this.baseCurrency = baseCurrency;
		this.termsCurrency = termsCurrency;
	}

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public String getTermsCurrency() {
		return termsCurrency;
	}

	public void setTermsCurrency(String termsCurrency) {
		this.termsCurrency = termsCurrency;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((baseCurrency == null) ? 0 : baseCurrency.hashCode());
		result = prime * result + ((termsCurrency == null) ? 0 : termsCurrency.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CurrencyPair other = (CurrencyPair) obj;
		if (baseCurrency == null) {
			if (other.baseCurrency != null)
				return false;
		} else if (!baseCurrency.equals(other.baseCurrency))
			return false;
		if (termsCurrency == null) {
			if (other.termsCurrency != null)
				return false;
		} else if (!termsCurrency.equals(other.termsCurrency))
			return false;
		return true;
	}

	
	
	

}
