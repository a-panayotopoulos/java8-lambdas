package com.lambdaherding.edi.axp.ch03;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import com.lambdaherding.edi.ch03.Person;

public class PersonFunctions {
	public static List<String> lastNameFirstName() {
		return Person.peeps()
				.map( m -> String.format( "%s, %s", m.lastName(), m.firstName() ) )
				.collect( Collectors.toList() );
	}
	
	public static List<Person> namesStartingWithVowel() {
		return Person.peeps()
				.filter( m -> "AEIOU".contains( m.firstName().substring( 0, 1 ) ) )
				.collect( Collectors.toList() );
	}
	
	public static List<String> all555PhoneNumbers() {
		return Person.peeps()
				.flatMap( m -> m.phoneNumbers().stream() )
				.filter( p -> p.startsWith( "555" ) )
				.collect( Collectors.toList() );
	}
	
	public static Person shortestNameOverall() {
		return Person.peeps()
				.min( Comparator.comparing( p -> p.firstName().length() + p.lastName().length() ) )
				.get();
	}
	
	public static Person hasBeenWorkingLongest() {
		return Person.peeps()
				.filter( p -> p.isCurrentlyEmployed() )
				.reduce( null, (p1, p2) -> ( ( p1 == null ) || p1.startedAfter( p2 ) ) ? p2 : p1 );
	}
}
