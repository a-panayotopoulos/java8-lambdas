package com.lambdaherding.edi.axp.ch03;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Test;

@SuppressWarnings("static-method")
public class StreamExampleTest {
	@Test
	public void testAddUp() {
		assertEquals( 2, StreamExample.addUp( Stream.of( 1, 2, 3, -4 ) ) );
		assertEquals( 42, StreamExample.addUp( Stream.of( 42 ) ) );
		assertEquals( 0, StreamExample.addUp( Stream.empty() ) );
	}

	@Test
	public void testCountLower() {
		assertEquals( 21, StreamExample.countLower( "Hello, World. Banana `fizz` {whee}" ) );
		assertEquals( 0, StreamExample.countLower( "I'M ERIC MONKMAN, REPRESENTING WOLFSON COLLEGE, CAMBRIDGE" ) );
	}

	@Test
	public void testMostLowercase() {
		assertEquals( Optional.of( "BaNaNa" ), StreamExample.mostLowercase(
				Arrays.asList( new String[] { "APPLE", "BaNaNa", "CaRRoT" } ) ) );

		assertEquals( Optional.of( "APPLE" ), StreamExample.mostLowercase( Arrays.asList( new String[] { "APPLE" } ) ) );
		assertEquals( Optional.empty(), StreamExample.mostLowercase( Collections.emptyList() ) );
	}

	private void assertStreamContents( Stream<?> stream, Object...expected ) {
		assertNotNull( "Null stream", stream );
		Iterator<?> it = stream.iterator();

		for ( Object value : expected ) {
			assertTrue( "Stream has more items", it.hasNext() );
			assertEquals( value, it.next() );
		}

		assertFalse( "Stream has too many items", it.hasNext() );
	}

	private void assertStreamEmpty( Stream<?> stream ) {
		assertNotNull( "Null stream", stream );
		assertEquals( "Stream empty", 0, stream.count() );
	}

	private Stream<String> sentence() {
		return Stream.of( "The", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog" );
	}

	@Test
	public void testMap() {
		assertStreamContents( StreamExample.map( sentence(), s -> s.length() ), 3, 5, 5, 3, 5, 4, 3, 4, 3 );
		assertStreamContents( StreamExample.map( Stream.of( "Bananarama" ), s -> s.length() ), 10 );
		assertStreamEmpty( StreamExample.map( Stream.<String>empty(), s -> s.length() ) );
	}

	@Test
	public void testFilter() {
		assertStreamContents( StreamExample.filter( sentence(), s -> s.length() == 3 ), "The", "fox", "the", "dog" );
		assertStreamEmpty( StreamExample.filter( sentence(), s -> s.length() == 8 ) );
		assertStreamEmpty( StreamExample.filter( Stream.<String>empty(), s -> s.length() == 3 ) );
	}
}
