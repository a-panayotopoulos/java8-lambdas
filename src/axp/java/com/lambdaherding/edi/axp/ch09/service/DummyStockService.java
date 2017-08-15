package com.lambdaherding.edi.axp.ch09.service;

import java.math.BigDecimal;
import java.util.Random;

public class DummyStockService extends DummyService implements StockService {
	public DummyStockService( long delay ) {
		super( delay );
	}

	@Override
	public BigDecimal getStockValue( String stockCode ) {
		spinWheels();

		Random rand = new Random( stockCode.hashCode() );
		BigDecimal value = new BigDecimal( rand.nextInt( 10000 ) ).movePointLeft( 2 );
		System.out.printf( "Stock %s has value %s\n", stockCode, value );
		return value;
	}

	@Override
	public float getDailyChange( String stockCode ) {
		spinWheels();

		Random rand = new Random( stockCode.hashCode() );
		float change = rand.nextFloat() - 0.5f;
		System.out.printf( "Stock %s changed %+.2f%% in the last 24h\n", stockCode, change );
		return change;
	}
}
