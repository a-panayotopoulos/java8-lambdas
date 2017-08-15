package com.lambdaherding.edi.axp.ch09;

import static com.lambdaherding.edi.axp.ch09.MyStocks.EUR;
import static com.lambdaherding.edi.axp.ch09.MyStocks.GBP;
import static com.lambdaherding.edi.axp.ch09.MyStocks.STOCKS;
import static com.lambdaherding.edi.axp.ch09.MyStocks.USD;

import java.util.Currency;
import java.util.stream.Stream;

import com.lambdaherding.edi.axp.ch09.model.Stock;
import com.lambdaherding.edi.axp.ch09.service.ConversionService;
import com.lambdaherding.edi.axp.ch09.service.DummyConversionService;
import com.lambdaherding.edi.axp.ch09.service.DummyStockService;
import com.lambdaherding.edi.axp.ch09.service.StockService;

/**
 * This shows the desired functionality of the stock lister -- get the value of
 * each of my three stocks, then the daily change of that stock, then finally
 * translate the value into three currencies (excluding its native currency).
 * 
 * The problem is one of speed; because the stock service takes 1 second to respond
 * and the conversion service 0.5s, that's 9 seconds for this program to run.
 * 
 * See {@link StockListingUsingFutures} for a version which uses java Futures
 * for threading.
 * 
 * TODO: as an alternative to futures, is it possible to make this threaded purely
 * via stream methods? What are the advantages and disadvantages of each technique?
 */
public class StockListingSequential {
	static StockService valuer = new DummyStockService( 1000L );
	static ConversionService converter = new DummyConversionService( 500L );

	public static void main( String...args ) {
		STOCKS.forEach( s -> getStockValue( s ) );
		STOCKS.forEach( s -> getDailyChange( s ) );
		STOCKS.forEach( s -> valueInCurrencies( s, USD, GBP, EUR ) );
		STOCKS.forEach( System.out::println );
	}

	public static void valueInCurrencies( Stock stock, Currency...currencies ) {
		Currency base = stock.nativeCurrency();

		Stream.of( currencies )
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
