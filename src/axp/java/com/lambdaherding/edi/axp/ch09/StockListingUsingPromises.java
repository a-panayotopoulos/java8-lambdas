package com.lambdaherding.edi.axp.ch09;

import static com.lambdaherding.edi.axp.ch09.MyStocks.EUR;
import static com.lambdaherding.edi.axp.ch09.MyStocks.GBP;
import static com.lambdaherding.edi.axp.ch09.MyStocks.STOCKS;
import static com.lambdaherding.edi.axp.ch09.MyStocks.USD;

import java.util.Currency;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.lambdaherding.edi.axp.ch09.model.Stock;
import com.lambdaherding.edi.axp.ch09.service.ConversionService;
import com.lambdaherding.edi.axp.ch09.service.DummyConversionService;
import com.lambdaherding.edi.axp.ch09.service.DummyStockService;
import com.lambdaherding.edi.axp.ch09.service.StockService;

/**
 * Migrate {@link StockListingUsingFutures} to {@link CompletableFuture}s.
 * 
 * We haven't really touched on the power of completable futures yet.
 */
public class StockListingUsingPromises {
	static StockService valuer = new DummyStockService( 1000L );
	static ConversionService converter = new DummyConversionService( 500L );
	static ExecutorService executor = Executors.newFixedThreadPool( 3 );

	private static <E> CompletableFuture<E> getPromise( Supplier<E> supplier ) {
		return CompletableFuture.supplyAsync( supplier, executor );
	}

	public static void main( String...args ) {
		STOCKS.stream()
			.map( s -> getPromise( () -> getStockValue( s ) ) )
			.collect( Collectors.toList() )
			.stream()
			.map( CompletableFuture::join )
			.map( s -> getPromise( () -> getDailyChange( s ) ) )
			.collect( Collectors.toList() )
			.forEach( CompletableFuture::join );

		STOCKS.forEach( s -> valueInCurrencies( s, USD, GBP, EUR ) );
		STOCKS.forEach( System.out::println );

		executor.shutdownNow();
	}

	public static void valueInCurrencies( Stock stock, Currency...currencies ) {
		Currency base = stock.nativeCurrency();

		Stream.of( currencies )
			.filter( c -> !c.equals( base ) )
			.map( c -> getPromise( () -> fillCurrency( stock, c ) ) )
			.collect( Collectors.toList() )
			.forEach( CompletableFuture::join );
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
