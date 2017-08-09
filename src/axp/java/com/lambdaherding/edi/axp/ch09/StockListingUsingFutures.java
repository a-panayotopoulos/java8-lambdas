package com.lambdaherding.edi.axp.ch09;

import static com.lambdaherding.edi.axp.ch09.MyStocks.EUR;
import static com.lambdaherding.edi.axp.ch09.MyStocks.GBP;
import static com.lambdaherding.edi.axp.ch09.MyStocks.STOCKS;
import static com.lambdaherding.edi.axp.ch09.MyStocks.USD;

import java.util.Currency;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.lambdaherding.edi.axp.ch09.model.Stock;
import com.lambdaherding.edi.axp.ch09.service.ConversionService;
import com.lambdaherding.edi.axp.ch09.service.DummyConversionService;
import com.lambdaherding.edi.axp.ch09.service.DummyStockService;
import com.lambdaherding.edi.axp.ch09.service.StockService;

/**
 * This is an improvement on {@link StockListingSequential}, as it can now
 * submit all of the stocks to the service in parallel. It is a bit messy,
 * though.
 * 
 * TODO: can we clean this up using {@link CompletableFuture} instead of
 * {@link Future}? Even better if we can use the {@link CompletableFuture#thenApply}
 * method to link one step directly to the next.
 */
public class StockListingUsingFutures {
	static StockService valuer = new DummyStockService( 1000L );
	static ConversionService converter = new DummyConversionService( 500L );
	static ExecutorService executor = Executors.newFixedThreadPool( 3 );

	private static <E> E getFuture( Future<E> future ) {
		try {
			/*
			 * We have to wrap this in try-catch because otherwise we can't
			 * use it within a lambda expression!
			 * 
			 * CompletableFuture might have a more appropriate method.
			 */
			return future.get();
		}
		catch ( InterruptedException | ExecutionException e ) {
			throw new RuntimeException( e );
		}
	}

	public static void main( String...args ) {
		STOCKS.stream()
			.map( s -> executor.submit( () -> getStockValue( s ) ) )
			/*
			 * TODO: When we take the {@link java.util.Stream#collect} methods
			 * out, the threading no longer works as expected. Can you explain
			 * why these intermediate methods are necessary?
			 */
			.collect( Collectors.toList() )
			.stream()
			.map( StockListingUsingFutures::getFuture )
			.map( s -> executor.submit( () -> getDailyChange( s ) ) )
			.collect( Collectors.toList() )
			.forEach( StockListingUsingFutures::getFuture );

		STOCKS.forEach( s -> valueInCurrencies( s, USD, GBP, EUR ) );
		STOCKS.forEach( System.out::println );

		executor.shutdownNow();
	}

	public static void valueInCurrencies( Stock stock, Currency...currencies ) {
		Currency base = stock.nativeCurrency();

		Stream.of( currencies )
			.filter( c -> !c.equals( base ) )
			.map( c -> executor.submit( () -> fillCurrency( stock, c ) ) )
			.collect( Collectors.toList() )
			.forEach( StockListingUsingFutures::getFuture );
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
