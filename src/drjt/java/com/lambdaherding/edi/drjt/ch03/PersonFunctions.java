package com.lambdaherding.edi.drjt.ch03;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.lambdaherding.edi.ch03.Person;


/**
 * Fill out these functions to filter our full list of people ({@link Person#peeps()}) down to just the information we need. 
 */
public class PersonFunctions {

	static Set<Character> VOWELS = new HashSet<>( Arrays.asList( 'A', 'E', 'I', 'O', 'U' ) );

	/**
	 * @return all people in ({@link Person#peeps()}) as Strings in the format "Lastname, Firstname"
	 */
	public static List<String> lastNameFirstName() {
		return Person.peeps()
				.map( person -> String.format( "%s, %s", person.lastName(), person.firstName() ) )
				.collect( Collectors.toList() );
	}

	/**
	 * @return all people in ({@link Person#peeps()}) whose first name starts with a vowel
	 */
	public static List<Person> namesStartingWithVowel() {
		return Person.peeps()
				.filter( guy -> VOWELS.contains( guy.firstName().charAt( 0 ) ) )
				.collect( Collectors.toList() );
	}

	/**
	 * @return all phone numbers of people in ({@link Person#peeps()}) that begin with 555-
	 */
	public static List<String> all555PhoneNumbers() {
		return Person.peeps()
				.flatMap( (Function<Person, Stream<String>>) body -> body.phoneNumbers().stream() )
				.filter( number -> number.startsWith( "555-" ) )
				.collect( Collectors.toList() );
	}

	/**
	 * @return the person in ({@link Person#peeps()}) with the shortest overall name
	 */
	public static Person shortestNameOverall() {
		return Person.peeps()
				.min( Comparator.comparing( person -> person.firstName().length() + person.lastName().length() ) )
				.get();
	}

	/**
	 * @return the person in ({@link Person#peeps()}) who's been working with the company longest (and is still working there)
	 */
	public static Person hasBeenWorkingLongest() {
		return Person.peeps()
				.filter( person -> person.isCurrentlyEmployed() )
				.reduce( ( a, b ) -> a.startedAfter( b ) ? b : a )
				.get();
	}
}
