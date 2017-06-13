package com.lambdaherding.edi.red.ch05;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

	private final Map<Integer, Long> cache = new HashMap<>();

	public Fibonacci() {
		cache.put( 0, 0L );
		cache.put( 1, 1L );
	}

	public long fib( int nth ) {
		return cache.computeIfAbsent( nth, n -> fib(n - 1) + fib(n - 2) );
	}
}
