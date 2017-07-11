package com.lambdaherding.edi.mdw.ch05;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class ExercicesTest {

	@Test
	public void testToUppercase() {
		Stream<String> words = Stream.of( "test", "one", "two", "three" );
		assertArrayEquals( Stream.of( "TEST", "ONE", "TWO", "THREE" ).toArray(), Exercices.toUppercase( words ).toArray());
	}
	
	@Test
	public void testFilterWithreduce() {
		Stream<Integer> numbers = Stream.of( 1, 2, 3, 4 );
		assert( 4 == Exercices.countWithreduce( numbers ));
		numbers = Stream.of( 1, 2, 3, 4, 4, 5, 6, 21 );
		assert( 8 == Exercices.countWithreduce( numbers ));
	}
	
	@Test
	public void testConcatWithFlatMap() {
		List<String> words = new ArrayList<String> ( );
		words.add( "test" );
		words.add( "one" );
		words.add( "two" );
		words.add( "three" );
		List<String> moreWords = new ArrayList<String> ( );
		moreWords.add( "TEST" );
		moreWords.add( "ONE" );
		moreWords.add( "TWO" );
		moreWords.add( "THREE" );
		moreWords.add( "DONE" );
		List<String> expected = new ArrayList<String> ( );
		expected.add( "test" );
		expected.add( "one" );
		expected.add( "two" );
		expected.add( "three" );
		expected.add( "TEST" );
		expected.add( "ONE" );
		expected.add( "TWO" );
		expected.add( "THREE" );
		expected.add( "DONE" );
		assertEquals( expected, Exercices.toConcatWithFlatMap( words, moreWords ));
	}
	
	@Test
	public void testCollectLongestName() {
		Stream<String> names = Stream.of("John Lennon", "Paul McCartney",
		    "George Harrison", "Ringo Starr", "Pete Best", "Stuart Sutcliffe");
		assertEquals( "Stuart Sutcliffe", names.collect( new CollectLongestName() ));
	}
	
	@Test
	public void testCountNameApperences() {
		Stream<String> names = Stream.of("John", "Paul", "George", "John",
        "Paul", "John");
		Map<String, Integer> expected = new HashMap<String, Integer>();
		expected.put( "John", 3 );
		expected.put( "Paul", 2 );
		expected.put( "George", 1 );
		assertEquals( expected, names.collect( new CountNameApperences() ));
	}
	
	@Test
	public void testFibonnaci() {
		Exercices exercise = new Exercices();
		Exercices.resetFibonnaci();
		assert( 8 == exercise.fibonnaci(6));
		assert( 13 == exercise.fibonnaci(7));
		Exercices.resetFibonnaci();
		assert( 21 == exercise.fibonnaci(8));
	}
	
	@Test
	public void testGroupingBy() {
		Stream<String> names = Stream.of("John", "Paul", "George", "Ringo", "Pete", "David");
		Map<Integer, List<String>> expected = new HashMap<Integer, List<String>>();
		List<String> four = new  ArrayList<String>();
		four.add( "John" );
		four.add( "Paul" );
		four.add( "Pete" );
		List<String> five = new  ArrayList<String>();
		five.add( "Ringo" );
		five.add( "David" );
		List<String> six = new ArrayList<String>();
		six.add( "George" );
		expected.put( 4, four );
		expected.put( 5, five );
		expected.put( 6, six );
		assertEquals( expected, names.collect( Collectors.groupingBy(x -> x.length() ) ));
	}
}
