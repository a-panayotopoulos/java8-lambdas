package com.lambdaherding.edi.axp.ch09;

import java.util.Currency;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.lambdaherding.edi.axp.ch09.model.Stock;

public interface MyStocks {
	public static final Currency USD = Currency.getInstance( "USD" );
	public static final Currency GBP = Currency.getInstance( "GBP" );
	public static final Currency EUR = Currency.getInstance( "EUR" );

	public static final Stock GOOGLE = new Stock( "Google", "GOO", USD );
	public static final Stock STARBUCKS = new Stock( "Starbucks", "STA", USD );
	public static final Stock WHITBREAD = new Stock( "Whitbread", "WHT", GBP );

	public static final Set<Stock> STOCKS = Stream.of( GOOGLE, STARBUCKS, WHITBREAD ).collect( Collectors.toSet() );
}
