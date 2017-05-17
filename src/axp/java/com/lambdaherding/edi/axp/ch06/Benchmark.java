package com.lambdaherding.edi.axp.ch06;

import java.util.function.Consumer;
import java.util.function.Function;

public class Benchmark {
	public static void benchmark( Runnable runnable ) {
		long time = System.nanoTime();
		runnable.run();
		time = System.nanoTime() - time;
		System.out.printf( "%,d\n", time );
	}

	public static <T> void benchmark( Consumer<T> consumer, T input ) {
		benchmark( () -> consumer.accept( input ) );
	}

	public static <T> void benchmark( Function<T,?> function, T input ) {
		benchmark( () -> function.apply( input ) );
	}

	@SafeVarargs
	public static <T> void benchmark( Consumer<T> consumer, T...input ) {
		for ( int i = 0; i < input.length; i++ ) {
			System.out.println( "Testing case #" + (i + 1) );
			T t = input[i];
			benchmark( () -> consumer.accept( t ) );
		}
	}

	@SafeVarargs
	public static <T> void benchmark( Function<T,?> function, T...input ) {
		for ( int i = 0; i < input.length; i++ ) {
			System.out.println( "Testing case #" + (i + 1) );
			T t = input[i];
			benchmark( () -> function.apply( t ) );
		}
	}
}
