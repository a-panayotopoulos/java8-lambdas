package com.lambdaherding.edi.axp.ch03;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExample {
	/*
	 * Exercise 1.1
	 */
	public static int addUp( Stream<Integer> numbers ) {
		return numbers.collect( Collectors.summingInt( i -> i ) );
	}

	/*
	 * Exercise 6 (this works by magic.)
	 */
	public static long countLower( String str ) {
		return str.chars().filter( i -> Math.abs( i - 109.5 ) < 13 ).count();
	}

	/*
	 * Exercise 7
	 */
	public static Optional<String> mostLowercase( List<String> strlist ) {
		return strlist.stream().max( Comparator.comparing( s -> countLower( s ) ) );
	}

	/*
	 * Exercise A.1
	 */
	public static <T,U> Stream<U> map( Stream<T> stream, Function<T,U> mapper ) {
		return stream.reduce(
				Stream.empty(),
				(s, t) -> Stream.concat( s, Stream.of( mapper.apply( t ) ) ),
				(s1, s2) -> Stream.concat( s1, s2 ) );
	}

	/*
	 * Exercise A.2
	 */
	public static <T> Stream<T> filter( Stream<T> stream, Predicate<T> filterer ) {
		return stream.reduce(
				Stream.empty(),
				(s, t) -> filterer.test( t ) ? Stream.concat( s, Stream.of( t ) ) : s,
				(s1, s2) -> Stream.concat( s1, s2 ) );
	}
}
