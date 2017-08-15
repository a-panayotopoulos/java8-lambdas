package com.lambdaherding.edi.axp.ch09.service;

import java.math.BigDecimal;

public interface StockService {
	public BigDecimal getStockValue( String stockCode );
	public float getDailyChange( String stockCode );
}
