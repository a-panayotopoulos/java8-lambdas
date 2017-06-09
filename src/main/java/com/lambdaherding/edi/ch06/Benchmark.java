package com.lambdaherding.edi.ch06;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Benchmark some runnable method.
 */
public class Benchmark {
	/**
	 * Benchmark a method which requires no input
	 * @param runnable the method to test
	 */
	public static void benchmark( Runnable runnable ) {
		long time = System.nanoTime();
		runnable.run();
		time = System.nanoTime() - time;
		System.out.printf( "%,d\n", time );
	}

	/**
	 * Benchmark a consumer against some input
	 * @param consumer the method to test
	 * @param input input to the method
	 */
	public static <T> void benchmark( Consumer<T> consumer, T input ) {
		benchmark( () -> consumer.accept( input ) );
	}

	/**
	 * Benchmark a function against some input
	 * @param function the method to test
	 * @param input input to the method
	 */
	public static <T> void benchmark( Function<T,?> function, T input ) {
		benchmark( () -> function.apply( input ) );
	}

	/**
	 * Benchmark a consumer against a range of input
	 * @param consumer the method to test
	 * @param input an array of possible inputs
	 */
	@SafeVarargs
	public static <T> void benchmark( Consumer<T> consumer, T...input ) {
		for ( int i = 0; i < input.length; i++ ) {
			System.out.println( "Testing case #" + (i + 1) );
			T t = input[i];
			benchmark( () -> consumer.accept( t ) );
		}
	}

	/**
	 * Benchmark a function against some input
	 * @param function the method to test
	 * @param input an array of possible inputs
	 */
	@SafeVarargs
	public static <T> void benchmark( Function<T,?> function, T...input ) {
		for ( int i = 0; i < input.length; i++ ) {
			System.out.println( "Testing case #" + (i + 1) );
			T t = input[i];
			benchmark( () -> function.apply( t ) );
		}
	}
}
