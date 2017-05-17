package com.lambdaherding.edi.dtj.ch03.people;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.lambdaherding.edi.ch03.Person;

/**
 * Fill out these functions to filter our full list of people ({@link Person#peeps()}) down to just the information we need. 
 */
public class PersonFunctions {
	/**
	 * @return all people in ({@link Person#peeps()}) as Strings in the format "Lastname, Firstname"
	 */
	public static List<String> lastNameFirstName() {
		return Person
				.peeps()
				.map( p -> p.lastName() + ", "  + p.firstName() )
				.collect(Collectors.toList());
	}

	/**
	 * @return all people in ({@link Person#peeps()}) whose first name starts with a vowel
	 */
	public static List<Person> namesStartingWithVowel() {
		return Person
				.peeps()
				.filter(p -> p.firstName().matches("^[AEIOU].*") )
				.collect( Collectors.toList() );
	}

	/**
	 * @return all phone numbers of people in ({@link Person#peeps()}) that begin with 555-
	 */
	public static List<String> all555PhoneNumbers() {
		return Person
				.peeps()
				.flatMap(p -> p.phoneNumbers().stream())
				.filter(num -> num.startsWith("555-"))
				.collect(Collectors.toList());
	}

	/**
	 * @return the person in ({@link Person#peeps()}) with the shortest overall name
	 */
	public static Person shortestNameOverall() {
		return Person
				.peeps()
				.min( Comparator.comparing( p -> p.firstName().length() + p.lastName().length() ) )
				.get();
	}

	/**
	 * @return the person in ({@link Person#peeps()}) who's been working with the company longest (and is still working there)
	 */
	public static Person hasBeenWorkingLongest() {
		return Person
				.peeps()
				.filter( Person::isCurrentlyEmployed )
				.max( (p1, p2) -> p2.startedAfter(p1) ? 1 : -1 )
				.get();
	}
}
