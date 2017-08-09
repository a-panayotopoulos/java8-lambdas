package com.lambdaherding.edi.axp.ch09;

import static com.lambdaherding.edi.axp.ch09.MyStocks.EUR;
import static com.lambdaherding.edi.axp.ch09.MyStocks.GBP;
import static com.lambdaherding.edi.axp.ch09.MyStocks.STOCKS;
import static com.lambdaherding.edi.axp.ch09.MyStocks.USD;

import java.util.Currency;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
 * Using {@link CompletableFuture#thenApply} means that we only have to collect
 * to an intermediate result once, which drastically simplifies the code.
 */
public class StockListingUsingPromises {
	static StockService valuer = new DummyStockService( 1000L );
	static ConversionService converter = new DummyConversionService( 500L );
	static ExecutorService executor = Executors.newFixedThreadPool( 3 );

	public static void main( String...args ) {
		STOCKS.stream()
			.map( s -> CompletableFuture.supplyAsync( () -> getStockValue( s ), executor ) )
			.map( f -> f.thenApply( StockListingUsingPromises::getDailyChange ) )
			.flatMap( s -> valueInCurrencies( s, USD, GBP, EUR ) )
			.collect( Collectors.toList() )
			.forEach( CompletableFuture::join );

		STOCKS.forEach( System.out::println );

		executor.shutdownNow();
	}

	public static Stream<CompletableFuture<Stock>> valueInCurrencies( CompletableFuture<Stock> stock, Currency...currencies ) {
		return Stream.of( currencies )
			.map( c -> stock.thenApply( s -> fillCurrency( s, c ) ) );
	}

	public static Stock getStockValue( Stock stock ) {
		return stock.value( valuer.getStockValue( stock.code() ).floatValue() );
	}

	public static Stock getDailyChange( Stock stock ) {
		return stock.dailyChange( valuer.getDailyChange( stock.code() ) );
	}

	public static Stock fillCurrency( Stock stock, Currency curr ) {
		return curr.equals( stock.nativeCurrency() ) ? stock :
			stock.value( curr, converter.getRelativeValue( stock.nativeCurrency(), curr ) );
	}
}
