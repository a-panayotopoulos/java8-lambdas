package com.lambdaherding.edi.mdw.ch05;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Exercices {

	public static Map<Integer, Integer> start; 

	public static Stream<String> toUppercase( Stream<String> words ) {
		return words.map( String::toUpperCase );
	}
	
	public static <T> Integer countWithreduce( Stream<T> numbers ) {
		//fix to do exercise
		return numbers.reduce( 0, (x, y) -> x + 1, (x, y) -> x + y );
	}

	public static <T> List<T> toConcatWithFlatMap( List<T> objects, List<T> inSpace ) {
		return Stream.of( objects, inSpace ).flatMap( List::stream ).collect(Collectors.toList());
	}

	public Integer fibonnaci( int key ) {
		return start.computeIfAbsent( key, this::getFib  );
	}
	
	public Integer getFib( int key ) {
		return this.fibonnaci( key - 2 ) + this.fibonnaci( key - 1 );
	}
	
	public static void resetFibonnaci() {
		start = new HashMap<Integer, Integer>();
		start.put( 1, 1 );
		start.put( 2, 1 );
	}
	
}
