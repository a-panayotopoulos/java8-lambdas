package com.lambdaherding.edi.mdw.ch03;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Fill out these functions to filter our full list of people ({@link Person#peeps()}) down to just the information we need. 
 */
public class PersonFunctions {
	/**
	 * @return all people in ({@link Person#peeps()}) as Strings in the format "Lastname, Firstname"
	 */
	public static List<String> lastNameFirstName() {
			return Person.peeps().map( x -> x.lastName() + ", " + x.firstName() ).collect( Collectors.toList() );
	}

	/**
	 * @return all people in ({@link Person#peeps()}) whose first name starts with a vowel
	 */
	public static List<Person> namesStartingWithVowel() {
		return Person.peeps().filter( x -> "AEIOUaeiou".indexOf( x.firstName().charAt( 1 ) ) < 0 ).collect( Collectors.toList() );
	}

	/**
	 * @return all phone numbers of people in ({@link Person#peeps()}) that begin with 555-
	 */
	public static List<String> all555PhoneNumbers() {
		return Person.peeps()
						.flatMap( x -> x.phoneNumbers().stream() )
						.filter( x -> x.startsWith( "555" ))
						.collect( Collectors.toList() )
						;
	}

	/**
	 * @return the person in ({@link Person#peeps()}) with the shortest overall name
	 */
	public static Person shortestNameOverall() {
		return Person.peeps()
						.min( Comparator.comparing( x -> x.firstName().length() + x.lastName().length() ) )
						.get();
	}

	/**
	 * @return the person in ({@link Person#peeps()}) who's been working with the company longest (and is still working there)
	 */
	public static Person hasBeenWorkingLongest() {
		return Person.peeps()
				.filter( x -> x.isCurrentlyEmployed() )
				.reduce( null, (x, y) -> {if ( x == null || x.startedAfter( y ) ) { return y; } else { return x; }});
	}
}
