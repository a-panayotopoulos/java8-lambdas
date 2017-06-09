package com.lambdaherding.edi.red.ch03;

import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Fill out these functions to filter our full list of people ({@link Person#peeps()}) down to just the information we need.
 */
public class PersonFunctions {
	private static final Pattern VOWEL_NAME = Pattern.compile( "^[aeiouגדהוזחטיךכלםמןנסעףפץרשתûü]", Pattern.CASE_INSENSITIVE );
	/**
	 * @return all people in ({@link Person#peeps()}) as Strings in the format "Lastname, Firstname"
	 */
	public static List<String> lastNameFirstName() {
		return Person.peeps()
			.map( p -> p.lastName() + ", " + p.firstName() )
			.collect( Collectors.toList() );
	}

	/**
	 * @return all people in ({@link Person#peeps()}) whose first name starts with a vowel
	 */
	public static List<Person> namesStartingWithVowel() {
		return Person.peeps()
			.filter( p -> VOWEL_NAME.matcher( p.firstName() ).find() )
			.collect( Collectors.toList() );
	}

	/**
	 * @return all phone numbers of people in ({@link Person#peeps()}) that begin with 555-
	 */
	public static List<String> all555PhoneNumbers() {
		return Person.peeps()
			.flatMap( p -> p.phoneNumbers().stream() )
			.filter( n -> n.startsWith( "555" ) )
			.collect( Collectors.toList() );
	}

	/**
	 * @return the person in ({@link Person#peeps()}) with the shortest overall name
	 */
	public static Person shortestNameOverall() {
		return Person.peeps()
			.min( Comparator.comparing( p -> p.firstName().length() + p.lastName().length() ) )
			.orElseThrow( () -> new IllegalStateException("No persons in set") );
	}

	/**
	 * @return the person in ({@link Person#peeps()}) who's been working with the company longest (and is still working there)
	 */
	public static Person hasBeenWorkingLongest() {
		return Person.peeps()
			.filter( Person::isCurrentlyEmployed )
			.reduce( (a, b) -> a.startedAfter( b ) ? b : a )
			.orElseThrow( () -> new IllegalStateException("No persons in set") );
	}
}
