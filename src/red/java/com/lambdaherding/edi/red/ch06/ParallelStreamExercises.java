package com.lambdaherding.edi.red.ch06;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.lambdaherding.edi.ch06.Benchmark;

public class ParallelStreamExercises {

	// Ex 1.
	public static int sumOfSquares(IntStream range) {
		return range.parallel()
			.map( x -> x * x )
			.sum();
	}

	// Ex 2.
	public static int multiplyThrough(List<Integer> ls) {
		return ls.parallelStream()
			.reduce(1, (a, b) -> a * b) * 5;
	}

	// Slow solution, from the book
	public static int slowSumOfSquares( List<Integer> ls ) {
		return ls.parallelStream()
			.map( x -> x * x )
			.reduce( 0, (a, b) -> a + b );
	}

	// Ex 3.
	public static int moreSumOfSquares( List<Integer> ls ) {
		return ls.parallelStream()
			.mapToInt( x -> x * x )
			.sum();
	}

	public static void main( String[] args ) {
		List<Integer> ls = new LinkedList<>(
			IntStream.iterate( 1, i -> i )
			.limit( 1_000_000 )
			.mapToObj( i -> i )
			.collect( Collectors.toList() ) );

		Benchmark.benchmark( ParallelStreamExercises::slowSumOfSquares, ls );
		Benchmark.benchmark( ParallelStreamExercises::moreSumOfSquares, ls );
	}
}
