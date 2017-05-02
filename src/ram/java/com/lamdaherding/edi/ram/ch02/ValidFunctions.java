package com.lamdaherding.edi.ram.ch02;

import java.util.function.Function;

public class ValidFunctions {
	
	// Compiles
	static Function<Long, Long> increment1 = x -> x + 1;
	
	// Doesn't compile because it expects two params
	// static Function<Long, Long> increment2 = (x, y) -> x + 1;

	// Doesn't compile because it is returning a boolean
	// static Function<Long, Long> increment3 = x -> x == 1;
	
	public static void main(String... args) {
		Long counter = 0L;
		
		System.out.print( "Incrementing counter" );
		System.out.print( " from " + counter);
		counter = increment1.apply(counter);
		System.out.println( " to " + counter);
		
	}

}
