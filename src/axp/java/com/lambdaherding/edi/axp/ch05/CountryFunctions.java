package com.lambdaherding.edi.axp.ch05;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.minBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

import com.lambdaherding.edi.ch05.Continent;
import com.lambdaherding.edi.ch05.Country;
import com.lambdaherding.edi.ch05.WorldMap;

public class CountryFunctions {
	/**
	 * A mapping of capitals to country names
	 * 
	 * @param countries a list of countries
	 * @return a mapping of String (capital) to String (country)
	 */
	public static Map<String, String> capitals( Stream<Country> countries ) {
		return countries.collect( toMap( Country::capital, Country::name ) );
	}

	/**
	 * Find the continent with the most contries in it
	 * 
	 * @param countries a list of countries
	 * @return the continent that contains the most
	 */
	public static Continent continentWithMostCountries( Stream<Country> countries ) {
		return countries.collect( groupingBy( Country::continent, counting() ) )
					.entrySet()
					.stream()
					.max( comparing( Entry::getValue ) )
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
		return countries.collect( partitioningBy( c -> c.continent().name().startsWith( "A" ), counting() ) )
			.entrySet()
			.stream()
			.mapToLong( e -> e.getValue() * ( e.getKey() ? 1 : -1  ) )
			.sum();
	}

	/**
	 * Given a properties file, fill it with a list of countries for each continent, separated by the colon
	 * character. The countries should be in encounter order. 
	 * 
	 * @param countries a list of countries
	 * @param props an empty properties file
	 */
	public static void writeToProperties( Stream<Country> countries, Properties props ) {
		countries.collect( groupingBy( c -> c.continent().name(), mapping( Country::name, joining( ":" ) ) ) )
			.forEach( props::put );
	}

	/**
	 * Given a properties file, fill it with a list of countries for each continent, separated by the colon
	 * character. The countries should be in alphabetical order.
	 * 
	 * @param countries a list of countries
	 * @param props an empty properties file
	 */
	public static void writeToPropertiesSorted( Stream<Country> countries, Properties props ) {
		countries.collect( groupingBy( c -> c.continent().name(), mapping( Country::name, toCollection( TreeSet::new ) ) ) )
			.forEach( (k, v) -> props.put( k, v.stream().collect( joining( ":" ) ) ) );
	}

	/**
	 * What is the first country in alphabetical order for each continent?
	 * 
	 * @param countries a list of countries
	 * @return a mapping of String (continent name) to {@link Country}
	 */
	public static Map<String, Country> firstAlphabeticallyForEachContinent( Stream<Country> countries ) {
		return countries.collect( groupingBy( c -> c.continent().name(), collectingAndThen(
				minBy( comparing( Country::name ) ), Optional::get ) ) );
	}

	/**
	 * Build a world map from a list of countries
	 * 
	 * @param countries a list of countries
	 * @return a fully-populated {@link WorldMap} object
	 */
	public static WorldMap buildWorldMap( Stream<Country> countries ) {
		return countries.collect( new Collector<Country,WorldMap,WorldMap>() {
			@Override
			public BiConsumer<WorldMap,Country> accumulator() {
				return WorldMap::add;
			}

			@Override
			public Set<Characteristics> characteristics() {
				return Stream.of(
						Characteristics.IDENTITY_FINISH,
						Characteristics.UNORDERED,
						Characteristics.CONCURRENT ).collect( toSet() );
			}

			@Override
			public BinaryOperator<WorldMap> combiner() {
				return (w, v) -> {
					w.mergeDataFrom( v );
					return w;
				};
			}

			@Override
			public Function<WorldMap,WorldMap> finisher() {
				return Function.identity();
			}

			@Override
			public Supplier<WorldMap> supplier() {
				return WorldMap::new;
			}});
	}

	/**
	 * Build a world map from a list of countries (uses reduce)
	 * 
	 * @param countries a list of countries
	 * @return a fully-populated {@link WorldMap} object
	 */
	public static WorldMap buildWorldMapReduce( Stream<Country> countries ) {
		return countries.reduce(
				new WorldMap(),
				(w, c) -> {
					w.add( c );
					return w;
				},
				(w, v) -> {
					w.mergeDataFrom( v );
					return w;
				});
	}
}
