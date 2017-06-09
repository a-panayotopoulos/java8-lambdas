package com.lambdaherding.edi.axp.ch06;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Exercise 3: sum of squares, redux
 */

public class SumOfSquaresRedux {
	/**
	 * The slow form of the function, using boxed integers
	 * @param range
	 * @return
	 */
	public static int boxedSumOfSquares( List<Integer> linkedListOfNumbers ) {
		return linkedListOfNumbers.parallelStream()
				.map(x -> x * x)
				.reduce(0, (acc, x) -> acc + x);
	}

	/**
	 * The faster form of the function, unboxed using mapToInt
	 * @param range
	 * @return
	 */
	public static int unboxedSumOfSquares( List<Integer> linkedListOfNumbers ) {
		return linkedListOfNumbers.parallelStream()
				.mapToInt( x -> x * x )
				.reduce(0, (acc, x) -> acc + x);
	}

	/**
	 * The fastest form of the function, using sum instead of reduce
	 * @param range
	 * @return
	 */
	public static int noReduceSumOfSquares( List<Integer> linkedListOfNumbers ) {
		return linkedListOfNumbers.parallelStream()
				.mapToInt( x -> x * x )
				.sum();
	}

	/**
	 * Benchmark all versions
	 * 
	 * Result:
	 * | input size  | boxed         | unboxed    | using sum  |
	 * +-------------+---------------+------------+------------+
	 * |       1,000 |       248,515 |    212,594 |     89,069 |
	 * |      10,000 |     3,305,473 |    715,123 |     50,949 |
	 * |     100,000 |     2,548,931 |  4,223,659 |    182,172 |
	 * |   1,000,000 |    15,055,331 | 18,514,385 |    506,560 |
	 * |  10,000,000 |   296,424,056 |  4,294,035 |  4,563,444 |
	 * | 100,000,000 | 1,776,488,773 | 40,166,366 | 39,608,489 |
	 * 
	 * Unboxed functions quickly become more efficient than boxed ones, and using "sum" instead of
	 * "reduce" makes it even faster. Interestingly, however, this distinction starts to go away at
	 * higher numbers. (This is not just a fluke of this test run-- it shows the same behaviour on
	 * multiple runs.)
	 * 
	 * @param args
	 */
	public static void main( String[] args ) {
		List<Integer>[] input = randomInput( 100, 1_000, 10_000, 100_000, 1_000_000, 10_000_000, 100_000_000 );

		// Throw one away
		boxedSumOfSquares( input[0] );
		unboxedSumOfSquares( input[0] );
		noReduceSumOfSquares( input[0] );

		System.out.println( "Benchmarking boxed form of function" );
		Benchmark.benchmark( SumOfSquaresRedux::boxedSumOfSquares, input );

		System.out.println( "Benchmarking unboxed form of function" );
		Benchmark.benchmark( SumOfSquaresRedux::unboxedSumOfSquares, input );

		System.out.println( "Benchmarking no-reduce form of function" );
		Benchmark.benchmark( SumOfSquaresRedux::noReduceSumOfSquares, input );
	}

	@SuppressWarnings("unchecked")
	public static List<Integer>[] randomInput( int limit, int...sizes ) {
		Random rand = new Random();

		return Arrays.stream( sizes )
			.mapToObj( s -> IntStream.range( 0, s )
					.mapToObj( _i -> rand.nextInt( limit ) )
					.collect( Collectors.toList() ) )
			.toArray( List[]::new );
	}
}
