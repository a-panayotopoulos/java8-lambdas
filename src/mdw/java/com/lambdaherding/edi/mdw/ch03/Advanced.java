package com.lambdaherding.edi.mdw.ch03;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Advanced {

	public static <T, R>Stream<R> mapWithreduce( Stream<T> numbers, Function<T, R> lambda ) {
		return numbers.reduce( Stream.empty(), (x, y) -> Stream.concat( x, Stream.of( lambda.apply( y ))), (x, y)  -> Stream.concat(  x  , y ) );
	}
	
	public static <T>Stream<T> filterWithreduce( Stream<T> numbers, Predicate<T> lambda ) {
		return numbers.reduce( Stream.empty(), (x, y) -> (lambda.test( y ) ? Stream.concat( x, Stream.of( y)) : x), (x, y)  -> Stream.concat(  x  , y ) );
	}
}
