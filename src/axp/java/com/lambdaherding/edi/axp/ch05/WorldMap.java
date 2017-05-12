package com.lambdaherding.edi.axp.ch05;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;

import com.lambdaherding.edi.ch05.Country;

/**
 * This keeps track of what continent, countries and cities are contained within what.
 * 
 * Now refactored with computeIfAbsent and merge
 */
public class WorldMap {
	private HashMap<String,HashSet<String>> containing = new HashMap<>();
	private static final String WORLD = "WORLD";
	private static final Function<String,HashSet<String>> NEW = s -> new HashSet<>();

	public WorldMap() {
		containing.put( WORLD, new HashSet<>() );
	}

	public void add( Country country ) {
		containing.get( WORLD ).add( country.continent().name() );
		containing.computeIfAbsent( country.continent().name(), NEW ).add( country.name() );
		containing.computeIfAbsent( country.name(), NEW  ).add( country.capital() );
	}

	public boolean contains( String outer, String inner ) {
		return Optional.ofNullable( containing.get( outer ) )
				.map( s -> s.contains( inner ) )
				.orElse( false );
	}

	public boolean containsContinent( String continent ) {
		return contains( WORLD, continent );
	}

	public void mergeDataFrom( WorldMap other ) {
		for ( Entry<String, HashSet<String>> entry : other.containing.entrySet() ) {
			containing.merge( entry.getKey(), entry.getValue(), (s, t) -> {
				s.addAll( t );
				return s;
			});
		}
	}
}
