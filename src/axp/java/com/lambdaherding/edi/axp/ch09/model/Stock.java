package com.lambdaherding.edi.axp.ch09.model;

import java.util.Comparator;
import java.util.Currency;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Stock {
	private final String name;
	private final String code;
	private final Currency nativeCurrency;

	private Map<Currency,Float> valuations = new TreeMap<>( Comparator.comparing( Currency::getCurrencyCode ) );
	private Optional<Float> dailyChange = Optional.empty();

	public Stock( String name, String code, Currency nativeCurrency ) {
		this.name = name;
		this.code = code;
		this.nativeCurrency = nativeCurrency;
	}

	public String code() {
		return code;
	}

	public Currency nativeCurrency() {
		return nativeCurrency;
	}

	public Stock value( float value ) {
		return value( nativeCurrency, value );
	}

	public Stock value( Currency curr, float value ) {
		valuations.put( curr, value );
		return this;
	}

	public Stock dailyChange( float dailyChange ) {
		this.dailyChange = Optional.of( dailyChange );
		return this;
	}

	public String toString() {
		return new StringBuilder()
			.append( String.format( "%10s (%s): ", name, code ) )
			.append( valuations.entrySet().stream()
				.map( e -> String.format( "%.2f %s", e.getValue(), e.getKey().getCurrencyCode() ) )
				.map( s -> String.format( "%10s", s ) )
				.collect( Collectors.joining( ", " ) ) )
			.append( dailyChange.map( c -> String.format( "  [%+.2f%%]", c ) ).orElse( "" ) )
			.toString();
	}
}
