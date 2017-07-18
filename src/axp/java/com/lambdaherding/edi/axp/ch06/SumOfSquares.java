package com.lambdaherding.edi.axp.ch06;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

import com.lambdaherding.edi.ch06.Benchmark;

/**
 * Exercise 1: sum of squares
 */
public class SumOfSquares {
	/**
	 * The sequential form of the function
	 * @param range
	 * @return
	 */
	public static int sequentialSumOfSquares( IntStream range ) {
		return range.map( x -> x * x )
				.sum();
	}

	/**
	 * The parallel form of the function
	 * @param range
	 * @return
	 */
	public static int parallelSumOfSquares( IntStream range ) {
		return range.parallel()
				.map( x -> x * x )
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
		int[][] input = randomInput( 100, 1_000, 10_000, 100_000, 1_000_000, 10_000_000 );

		// Throw one away
		sequentialSumOfSquares( Arrays.stream( input[0] ) );
		parallelSumOfSquares( Arrays.stream( input[0] ) );

		System.out.println( "Benchmarking sequential function" );
		Benchmark.benchmark( SumOfSquares::sequentialSumOfSquares, streamify( input ) );

		System.out.println( "Benchmarking parallel function" );
		Benchmark.benchmark( SumOfSquares::parallelSumOfSquares, streamify( input ) );
	}

	public static int[][] randomInput( int limit, int...sizes ) {
		Random rand = new Random();

		return Arrays.stream( sizes )
				.mapToObj( s -> new int[s] )
				.peek( a -> Arrays.parallelSetAll( a,  _i -> rand.nextInt( limit ) ) )
				.toArray( int[][]::new );
	}

	public static IntStream[] streamify( int[][] input ) {
		return Arrays.stream( input )
				.map( a -> Arrays.stream( a ) )
				.toArray( IntStream[]::new );
	}
}
