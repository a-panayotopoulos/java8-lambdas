package com.lambdaherding.edi.red.ch05;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.minBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toMap;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Stream;

import com.lambdaherding.edi.ch05.Continent;
import com.lambdaherding.edi.ch05.Country;
import com.lambdaherding.edi.ch05.WorldMap;

/**
 * A collection of functions over countries.
 *
 */
public class CountryFunctions {
	/**
	 * A mapping of capitals to country names
	 *
	 * @param countries a list of countries
	 * @return a mapping of String (capital) to String (country)
	 */
	public static Map<String, String> capitals( Stream<Country> countries ) {
		return countries.collect( toMap( Country::capital, Country::name ));
	}

	/**
	 * Find the continent with the most contries in it
	 *
	 * @param countries a list of countries
	 * @return the continent that contains the most
	 */
	public static Continent continentWithMostCountries( Stream<Country> countries ) {

		// TODO: there's probably a cleaner way to do this; custom maxBy distinct collector maybe?
		return countries.collect( groupingBy( Country::continent, counting() ) )
			.entrySet()
			.stream()
			.max( Comparator.comparing(Entry::getValue) )
			.get()
			.getKey();
	}

	/**
	 * How many more countries are in continents beginning with 'A' (Africa, Asia) than the rest of the continents
	 * put together?
	 *
	 * @param countries a list of countries
	 * @return number of African and Asian countries minus number of all other countries
	 */
	public static long howManyMoreAContinentCountries( Stream<Country> countries ) {
		Map<Boolean, Long> a = countries.collect( partitioningBy(
			c -> c.continent().name().startsWith("A"),
			counting() ) );

		return a.getOrDefault( true, 0L ) -
			a.getOrDefault( false, 0L );
	}

	/**
	 * Given a properties file, fill it with a list of countries for each continent, separated by the colon
	 * character. The countries should be in encounter order.
	 *
	 * @param countries a list of countries
	 * @param props an empty properties file
	 */
	public static void writeToProperties( Stream<Country> countries, Properties props ) {
		countries.collect(groupingBy(Country::continent, mapping(Country::name, joining(":"))))
			.forEach( (continent, ls) -> props.put(continent.name(), ls) );
	}

	/**
	 * Given a properties file, fill it with a list of countries for each continent, separated by the colon
	 * character. The countries should be in alphabetical order.
	 *
	 * @param countries a list of countries
	 * @param props an empty properties file
	 */
	public static void writeToPropertiesSorted( Stream<Country> countries, Properties props ) {
		countries.sorted(Comparator.comparing(Country::name))
			.collect(groupingBy(Country::continent, mapping(Country::name, joining(":"))))
			.forEach( (continent, ls) -> props.put(continent.name(), ls) );
	}

	/**
	 * What is the first country in alphabetical order for each continent?
	 *
	 * @param countries a list of countries
	 * @return a mapping of String (continent name) to {@link Country}
	 */
	public static Map<String, Country> firstAlphabeticallyForEachContinent( Stream<Country> countries ) {
		return countries.collect( groupingBy(c -> c.continent().name(),
			collectingAndThen( minBy( Comparator.comparing(Country::name) ), Optional::get ) ) );
	}

	/**
	 * Build a world map from a list of countries
	 *
	 * @param countries a list of countries
	 * @return a fully-populated {@link WorldMap} object
	 */
	public static WorldMap buildWorldMap( Stream<Country> countries ) {
		return countries.collect( new WorldMapCollector() );
	}
}
