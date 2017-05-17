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
	 * The slow form of the function
	 * @param range
	 * @return
	 */
	public static int slowSumOfSquares( List<Integer> linkedListOfNumbers ) {
		return linkedListOfNumbers.parallelStream()
				.map(x -> x * x)
				.reduce(0, (acc, x) -> acc + x);
	}

	/**
	 * The faster form of the function
	 * 
	 * - Use mapToInt instead of map
	 * 
	 * @param range
	 * @return
	 */
	public static int fasterSumOfSquares( List<Integer> linkedListOfNumbers ) {
		return linkedListOfNumbers.parallelStream()
				.mapToInt( x -> x * x )
				.reduce(0, (acc, x) -> acc + x);
	}

	/**
	 * The fastest form of the function
	 * 
	 * - Use sum instead of reduce
	 * 
	 * @param range
	 * @return
	 */
	public static int fastestSumOfSquares( List<Integer> linkedListOfNumbers ) {
		return linkedListOfNumbers.parallelStream()
				.mapToInt( x -> x * x )
				.sum();
	}

	/**
	 * Benchmark both forms
	 * 
	 * Result:
	 * | input size | Sequential | Parallel  |
	 * +------------+------------+-----------+
	 * |      1,000 |     90,902 |   108,496 |
	 * |     10,000 |    498,497 |    74,042 |
	 * |    100,000 |  1,958,432 |    79,173 |
	 * |  1,000,000 |  3,817,898 |   256,579 |
	 * | 10,000,000 | 11,032,170 | 2,097,351 |
	 * 
	 * As you can see, it's not worth parallelising if you've got less than
	 * a thousand inputs. Also, the sequential performance is a lot more
	 * linear than the parallel performance.
	 * 
	 * @param args
	 */
	public static void main( String[] args ) {
		List<Integer>[] inputt = randommInput( 100, 1_000, 10_000, 100_000, 1_000_000, 10_000_000, 100_000_000 );

		// Throw one away
		slowSumOfSquares( inputt[0] );
		fasterSumOfSquares( inputt[0] );
		fastestSumOfSquares( inputt[0] );

		System.out.println( "Benchmarking slow form of function" );
		Benchmark.benchmark( SumOfSquaresRedux::slowSumOfSquares, inputt );

		System.out.println( "Benchmarking faster form of function" );
		Benchmark.benchmark( SumOfSquaresRedux::fasterSumOfSquares, inputt );

		System.out.println( "Benchmarking fastest form of function" );
		Benchmark.benchmark( SumOfSquaresRedux::fastestSumOfSquares, inputt );
	}

	@SuppressWarnings("unchecked")
	public static List<Integer>[] randommInput( int limit, int...sizes ) {
		Random rand = new Random();

		return Arrays.stream( sizes )
			.mapToObj( s -> IntStream.range( 0, s )
					.mapToObj( _i -> rand.nextInt( limit ) )
					.collect( Collectors.toList() ) )
			.toArray( List[]::new );
	}
}
