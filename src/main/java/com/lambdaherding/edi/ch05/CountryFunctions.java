package com.lambdaherding.edi.ch05;

import java.util.Map;
import java.util.Properties;
import java.util.stream.Stream;

/**
 * A collection of functions over countries.
 *
 * TODO: all of this
 */
public class CountryFunctions {
	/**
	 * A mapping of capitals to country names
	 * 
	 * @param countries a list of countries
	 * @return a mapping of String (capital) to String (country)
	 */
	public static Map<String, String> capitals( Stream<Country> countries ) {
		return null; // TODO
	}

	/**
	 * Find the continent with the most contries in it
	 * 
	 * @param countries a list of countries
	 * @return the continent that contains the most
	 */
	public static Continent continentWithMostCountries( Stream<Country> countries ) {
		return null; // TODO
	}

	/**
	 * How many more countries are in continents beginning with 'A' (Africa, Asia) than the rest of the continents
	 * put together?
	 * 
	 * @param countries a list of countries
	 * @return number of African and Asian countries minus number of all other countries
	 */
	public static long howManyMoreAContinentCountries( Stream<Country> countries ) {
		return -1; // TODO
	}

	/**
	 * Given a properties file, fill it with a list of countries for each continent, separated by the colon
	 * character. The countries should be in encounter order. 
	 * 
	 * @param countries a list of countries
	 * @param props an empty properties file
	 */
	public static void writeToProperties( Stream<Country> countries, Properties props ) {
		// TODO
	}

	/**
	 * Given a properties file, fill it with a list of countries for each continent, separated by the colon
	 * character. The countries should be in alphabetical order.
	 * 
	 * @param countries a list of countries
	 * @param props an empty properties file
	 */
	public static void writeToPropertiesSorted( Stream<Country> countries, Properties props ) {
		// TODO
	}

	/**
	 * What is the first country in alphabetical order for each continent?
	 * 
	 * @param countries a list of countries
	 * @return a mapping of String (continent name) to {@link Country}
	 */
	public static Map<String, Country> firstAlphabeticallyForEachContinent( Stream<Country> countries ) {
		return null; // TODO
	}

	/**
	 * Build a world map from a list of countries
	 * 
	 * @param countries a list of countries
	 * @return a fully-populated {@link WorldMap} object
	 */
	public static WorldMap buildWorldMap( Stream<Country> countries ) {
		return null; // TODO
	}
}
