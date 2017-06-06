package com.lambdaherding.edi.mdw.ch03;

import static java.time.Month.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * A person with a first name, last name, and collection of phone numbers.
 * 
 * The dates they started and finished at the company are protected, but you can query if they
 * started after another person, and whether they are still employed by the company.
 * 
 * Don't edit this class.
 */
public class Person {
	private String firstName;
	private String lastName;
	private String[] phoneNumbers = {};
	private LocalDate started;
	private LocalDate finished;

	public String firstName() {
		return firstName;
	}

	public Person firstName( String firstName ) {
		this.firstName = firstName;
		return this;
	}

	public String lastName() {
		return lastName;
	}

	public Person lastName( String lastName ) {
		this.lastName = lastName;
		return this;
	}

	public List<String> phoneNumbers() {
		return Arrays.asList( phoneNumbers );
	}

	public Person phoneNumbers( String...phoneNumbers ) {
		this.phoneNumbers = phoneNumbers;
		return this;
	}

	private Person started( int startYear, Month startMonth ) {
		started = LocalDate.of( startYear, startMonth, 1 );
		return this;
	}

	private Person finished( int endYear, Month endMonth ) {
		finished = LocalDate.of( endYear, endMonth, 1 );
		return this;
	}

	public boolean isCurrentlyEmployed() {
		return finished == null;
	}

	public boolean startedAfter( Person other ) {
		return started.isAfter( other.started );
	}

	/**
	 * A bunch of people
	 * @return
	 */
	public static Stream<Person> peeps() {
		return Stream.of(
			new Person().firstName( "Alice" ).lastName( "Aqua" ).phoneNumbers( "555-1234" ).started( 2007, MARCH ),
			new Person().firstName( "Bob" ).lastName( "Brown" ).started( 2001, Month.JANUARY ).finished( 2010, NOVEMBER ),
			new Person().firstName( "Carol" ).lastName( "Chartreuse" ).phoneNumbers( "555-2345", "444-1010" ).started( 2005, MAY ),
			new Person().firstName( "Dave" ).lastName( "Dandelion" ).phoneNumbers( "555-3456" ).started( 2003, JULY ),
			new Person().firstName( "Eve" ).lastName( "Emerald" ).phoneNumbers( "555-4567", "444-2020" ).started( 2003, AUGUST )
			);
	}
}
