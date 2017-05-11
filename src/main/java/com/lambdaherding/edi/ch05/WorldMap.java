package com.lambdaherding.edi.ch05;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

/**
 * This keeps track of what continent, countries and cities are contained within what.
 * 
 * It's not the greatest implementation, perhaps it could be neatened up a little?
 */
public class WorldMap {
	private HashMap<String,HashSet<String>> containing = new HashMap<>();
	private static final String WORLD = "WORLD";

	public WorldMap() {
		containing.put( WORLD, new HashSet<>() );
	}

	public void add( Country country ) {
		if ( !containing.containsKey( country.continent().name() ) ) {
			containing.put( country.continent().name(), new HashSet<>() );
		}

		if ( !containing.containsKey( country.name() ) ) {
			containing.put( country.name(), new HashSet<>() );
		}

		containing.get( WORLD ).add( country.continent().name() );
		containing.get( country.continent().name() ).add( country.name() );
		containing.get( country.name() ).add( country.capital() );
	}

	public boolean contains( String outer, String inner ) {
		if ( !containing.containsKey( outer ) ) {
			return false;
		}

		return containing.get( outer ).contains( inner );
	}

	public boolean containsContinent( String continent ) {
		return contains( WORLD, continent );
	}

	public void mergeDataFrom( WorldMap other ) {
		for ( Entry<String, HashSet<String>> entry : other.containing.entrySet() ) {
			if ( !containing.containsKey( entry.getKey() ) ) {
				containing.put( entry.getKey(), entry.getValue() );
			}
			else {
				containing.get( entry.getKey() ).addAll( entry.getValue() );
			}
		}
	}
}
