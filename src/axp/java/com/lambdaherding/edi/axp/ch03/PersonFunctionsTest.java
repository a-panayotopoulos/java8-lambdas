package com.lambdaherding.edi.axp.ch03;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

@SuppressWarnings("static-method")
public class PersonFunctionsTest {
	private static void assertPeopleByName( Iterable<String> expectedNames, Iterable<Person> actualPeople ) {
		Iterator<Person> it = actualPeople.iterator();
		
		for ( String name : expectedNames ) {
			assertTrue( "Expected more people", it.hasNext() );
			assertEquals( name, it.next().firstName() );
		}
		
		assertFalse( "More people than expected", it.hasNext() );
	}
	
	private static void assertPeopleByName( String[] expectedNames, Iterable<Person> actualPeople ) {
		assertPeopleByName( Arrays.asList( expectedNames ), actualPeople );
	}
	
	@Test
	public void testLastNameFirstName() {
		List<String> expected = Arrays.asList( new String[] {
				"Aqua, Alice", "Brown, Bob", "Chartreuse, Carol", "Dandelion, Dave", "Emerald, Eve" } ); 

		assertEquals( expected, PersonFunctions.lastNameFirstName() );
	}

	@Test
	public void testNamesStartingWithVowel() {
		assertPeopleByName( new String[] { "Alice", "Eve" }, PersonFunctions.namesStartingWithVowel() );
	}

	@Test
	public void testAll555PhoneNumbers() {
		List<String> expected = Arrays.asList( new String[] { "555-1234", "555-2345", "555-3456", "555-4567" } );
		assertEquals( expected, PersonFunctions.all555PhoneNumbers() );
	}

	@Test
	public void testShortestNameOverall() {
		assertEquals( "Bob", PersonFunctions.shortestNameOverall().firstName() );
	}

	@Test
	public void testHasBeenWorkingLongest() {
		assertEquals( "Dave", PersonFunctions.hasBeenWorkingLongest().firstName() );
	}
}
