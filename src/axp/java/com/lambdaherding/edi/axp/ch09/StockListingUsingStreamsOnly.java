package com.lambdaherding.edi.axp.ch09;

import static com.lambdaherding.edi.axp.ch09.MyStocks.EUR;
import static com.lambdaherding.edi.axp.ch09.MyStocks.GBP;
import static com.lambdaherding.edi.axp.ch09.MyStocks.STOCKS;
import static com.lambdaherding.edi.axp.ch09.MyStocks.USD;

import java.util.ArrayList;
import java.util.Currency;
import java.util.stream.Stream;

import com.lambdaherding.edi.axp.ch09.model.Stock;
import com.lambdaherding.edi.axp.ch09.service.ConversionService;
import com.lambdaherding.edi.axp.ch09.service.DummyConversionService;
import com.lambdaherding.edi.axp.ch09.service.DummyStockService;
import com.lambdaherding.edi.axp.ch09.service.StockService;

/**
 * This implementation shows how we can avoid using {@link Future}s, or in fact any explicit
 * threading model at all, but simply tell streams to go parallel for us.
 * 
 * Advantage: it's far easier to simply say 'parallelStream' instead of 'stream', and not have
 * to worry about any threading issues. In fact, this code is almost identical to the
 * {@link StockListingSequential} example.
 * 
 * Disadvantage: Java streams are very "hands-off"; you don't get to, for example, specify the
 * number of threads in your thread pool. Case in point: I attempted first to call parallelStream
 * on {@link MySTocks#STOCKS} directly, but the JVM decided that the best way to step through
 * this collection was sequentially. So I hacked the below solution to build an {@link ArrayList}
 * first and then parallelStream from that; this successfully runs everything at the same time.
 */
public class StockListingUsingStreamsOnly {
	static StockService valuer = new DummyStockService( 1000L );
	static ConversionService converter = new DummyConversionService( 500L );

	public static void main( String...args ) {
		/*
		 * Ugh! Set streams don't parallelise nicely. Let's put it in an ArrayList temporarily.
		 */
		ArrayList<Stock> stocks = new ArrayList<>( STOCKS );
		
		stocks.parallelStream().forEach( s -> getStockValue( s ) );
		stocks.parallelStream().forEach( s -> getDailyChange( s ) );
		stocks.forEach( s -> valueInCurrencies( s, USD, GBP, EUR ) );
		stocks.forEach( System.out::println );
	}

	public static void valueInCurrencies( Stock stock, Currency...currencies ) {
		Currency base = stock.nativeCurrency();

		Stream.of( currencies )
			.parallel()
			.filter( c -> !c.equals( base ) )
			.forEach( c -> fillCurrency( stock, c ) );
	}

	public static Stock getStockValue( Stock stock ) {
		return stock.value( valuer.getStockValue( stock.code() ).floatValue() );
	}

	public static Stock getDailyChange( Stock stock ) {
		return stock.dailyChange( valuer.getDailyChange( stock.code() ) );
	}

	public static Stock fillCurrency( Stock stock, Currency curr ) {
		return stock.value( curr, converter.getRelativeValue( stock.nativeCurrency(), curr ) );
	}
}
